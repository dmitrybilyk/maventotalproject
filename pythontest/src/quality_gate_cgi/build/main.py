#!/usr/bin/env python
'''
Main entry point and validation unit

@package: symmetrics_saasrepo_qualitygate
@copyright: 2010-2014 symmetrics gmbh. All rights reserved.
@author: Oktay Acikalin <ok@symmetrics.de>
@author: Roman Romenskyi <roman.romenskyi@symmetrics.de>
'''

from __future__ import with_statement
import os
import sys
import copy
from subprocess import Popen, PIPE
from glob import glob
import re
import tempfile
import time
import fnmatch

import settings
from lib.parser import Parser
from lib.saas_repo import SaaSRepo


_config = {}
_readme_files = None
_py_files = None
_php_files = None
_xml_files = None
_java_files = None
_js_files = None
_css_files = None
_ignore_files = []
_verbose = False
_benchmark = False
_logged_messages = []
_content_filter_regex = []
_cached_db_conn = None


class Failure(Exception):
    pass


class NoOpException(Exception):
    pass


def _msg(msg):
    '''
    Writes message to stdout if verbose output has been enabled

    @type  msg: string
    @param msg: message to print

    @return: None
    '''
    if _verbose:
        print(msg)


def _log(status, message):
    '''
    Writes log message with given values

    @type   status: boolean
    @param  status: True or False whether all tests succeeded
    @type  message: string
    @param message: message to write (should be "OK" or full reason)

    @return: None
    '''
    repo = _config['reponame']
    user = _config['author']
    node = _config['node']
    branch = _config['branch']
    row = ['%(timestamp)s %(hostname)s %(service)s:' % dict(
        timestamp=time.strftime('%Y-%m-%d %H:%M:%S'),
        hostname=os.uname()[1],
        service=settings.LOG_SERVICE_NAME,
    )]
    data = dict(
        repository=repo,
        user=user,
        node=node,
        branch=branch,
        status=status,
    )
    for key, val in data.iteritems():
        val = data[key] = str(val).strip()
        if ' ' in val:
            val = '"%s"' % val.replace('"', '\"')
        row.append('%s=%s' % (key, val))
    row = ' '.join(row) + (' message="%s"' % message.replace('"', '\"')) + '\n'
    log_row = ' '.join(row[1:]) + (' msg="%s"' % message.replace('"', '\"'))
    if log_row not in _logged_messages:
        fh = open(settings.LOG_FILE, 'a')
        fh.write(row)
        fh.close()
        _msg(row)
        _logged_messages.append(log_row)


def _hg_extract_revision(revision):
    '''
    Extracts a revision of a hg repo and returns the folder path

    @type  revision: string
    @param revision: revision or tag to extract

    @return: string with path of temp_dir created
    '''
    temp_dir = str(tempfile.mkdtemp())
    cmd = settings.HG_EXTRACT_CMD % dict(revision=revision, temp_dir=temp_dir)
    # print 'executing: %s' % cmd
    if os.system(cmd):  # if something unexpected happened
        # remove temp dir with contents
        os.system('rm -rf %r' % temp_dir)
        raise Failure('Could not extract revision without errors.')
    os.remove(os.path.join(temp_dir, '.hg_archival.txt'))
    return temp_dir


def _git_extract_revision(revision):
    '''
    Extracts a revision of a git repo and returns the folder path

    @type  revision: string
    @param revision: revision or tag to extract

    @return: string with path of temp_dir created
    '''
    temp_dir = str(tempfile.mkdtemp())
    cmd = settings.GIT_EXTRACT_CMD % dict(revision=revision, temp_dir=temp_dir)
    # print 'executing: %s' % cmd
    if os.system(cmd):  # if something unexpected happened
        # remove temp dir with contents
        os.system('rm -rf %r' % temp_dir)
        raise Failure('Could not extract revision without errors.')
    # os.remove(os.path.join(temp_dir, '.hg_archival.txt'))
    return temp_dir


def _gen_config():
    '''
    Generates a config dict from current environment

    @return: dict with config
    '''
    cleanup = False
    if os.getenv('HG_NODE') is not None:
        # os.system('env|grep HG')
        keys = ('author', 'tags', 'node', 'branch')
        template = '{author}\n{tags}\n{node}\n{branches}'
        cmd = '${HG} log -r tip --template "%s"' % template
        res, err = list(Popen([cmd], shell=True, stdout=PIPE,
                              stderr=PIPE).communicate())  # list for pylint
        res = dict(zip(keys, res.split('\n')))
        author = res['author']
        node = res['node']
        branch = res['branch'] if res['branch'] else settings.HG_DEFAULT_BRANCH
        if settings.HG_ONLY_BRANCHES:
            choices = map(lambda item: re.compile(r'^%s$' % item),
                          settings.HG_ONLY_BRANCHES)
            results = filter(lambda item: item,
                             map(lambda regex: regex.match(branch), choices))
            if not results:
                raise NoOpException('Untested: Branch "%s" not in list.' %
                                    branch)
        basedir = _hg_extract_revision('tip')
        reponame = os.path.split(os.getcwd())[-1]
        # print 'BEGIN config'
        # print basedir
        # print reponame
        # print 'END config'
        cleanup = True
    elif os.getenv('GIT_NODE') is not None:
        keys = ('author', 'node')
        node = os.getenv('GIT_NODE')
        template = '%an <%ae>\n%H', node
        cmd = 'git log -n 1 --pretty="format:%s" %s' % template
        res, err = list(Popen([cmd], shell=True, stdout=PIPE,
                              stderr=PIPE).communicate())  # list for pylint
        res = dict(zip(keys, res.split('\n')))
        author = res['author']
        branch = os.getenv('GIT_REF').split('/')[-1]
        if settings.GIT_ONLY_BRANCHES:
            choices = map(lambda item: re.compile(r'^%s$' % item),
                          settings.GIT_ONLY_BRANCHES)
            results = filter(lambda item: item,
                             map(lambda regex: regex.match(branch), choices))
            if not results:
                raise NoOpException('Untested: Branch "%s" not in list.' %
                                    branch)
        basedir = _git_extract_revision(node)
        reponame = os.path.split(os.getcwd())[-1]
        # print 'BEGIN config'
        # print basedir
        # print reponame
        # print branch
        # print node
        # print author
        # print 'END config'
        cleanup = True
    elif len(sys.argv) > 1:
        basedir = os.path.abspath(sys.argv[1])
        reponame = os.path.split(basedir)[-1]
        author = settings.LOCAL_USER
        node = settings.LOCAL_NODE
        branch = settings.LOCAL_BRANCH
    else:
        raise Failure('Cannot determine which folder to scan.')
    # print res
    info_py = os.path.join(basedir, 'info.py')
    build = os.path.join(basedir, settings.BUILD_PATH)
    doc = os.path.join(basedir, settings.DOC_PATH)
    auth_user = os.getenv(settings.AUTH_USER_ENV_VAR)
    return dict(
        basedir=basedir,
        reponame=reponame,
        info_py=info_py,
        build=build,
        doc=doc,
        cleanup=cleanup,
        author=author,
        node=node,
        branch=branch,
        auth_user=auth_user,
    )


def _apply_repository_overrides(config):
    '''
    If a repository specific override is present, overwrite the default
    settings.

    @type  config: dict
    @param config: Dict with current configuration.

    @return: None
    '''
    if config['reponame'] in settings.REPOSITORY_OVERRIDES:
        overrides = settings.REPOSITORY_OVERRIDES[config['reponame']]
        for key, val in overrides.iteritems():
            setattr(settings, key, val)


def _read_qgignore_file(config):
    '''
    Reads a central qgignore file and puts all patterns into _ignore_files var.

    @type  config: dict
    @param config: Dict with current configuration.

    @return: None
    '''
    global _ignore_files
    qgignore_file = os.path.join(config['basedir'], settings.QG_IGNORE_FILE)
    if os.path.exists(qgignore_file):
        fh = open(qgignore_file, 'rU')
        files = fh.read().strip().split('\n')
        files = [(os.path.join(config['basedir'], pat), pat) for pat in files]
        _ignore_files += files


def _get_readme_files(doc_folder):
    '''
    Finds all readme files within given folder

    @type  doc_folder: string
    @param doc_folder: folder to scan

    @return: list with readme files found
    '''
    global _readme_files
    if _readme_files is not None:
        return _readme_files
    files = glob(os.path.join(doc_folder, settings.README_GLOB_PATTERN))
    regex = re.compile(settings.README_REGEX_PATTERN)
    for file_ in files:
        if not regex.match(file_) or not os.path.getsize(file_):
            files.remove(file_)
            continue
    _readme_files = files
    return _readme_files


def _get_file_md5(filename):
    '''
    Reads a file chunk-wise and returns its MD5 as a hex digest.

    @type  filename: string
    @param filename: File to work with.

    @rtype:  string
    @return: Digest as a hex string.
    '''
    import hashlib
    md5 = hashlib.md5()
    with open(filename, 'rb') as file_:
        for chunk in iter(lambda: file_.read(8192), ''):
            md5.update(chunk)
    return md5.hexdigest()


def _get_db_conn():
    '''
    Returns a connection to the working cache db.

    @rtype:  sqlite3.Connection
    @return: Connection object to work with.
    '''
    global _cached_db_conn
    if _cached_db_conn is not None:
        return _cached_db_conn
    import sqlite3
    db_filename = settings.FILE_STATE_CACHE_DB % _config
    if not os.path.exists(db_filename):
        conn = sqlite3.connect(db_filename)
        cur = conn.cursor()
        cur.execute('CREATE TABLE state (filename text, md5 text)')
        conn.commit()
    else:
        conn = sqlite3.connect(db_filename)
    _cached_db_conn = conn
    return conn


def _get_cached_file_md5(filename):
    '''
    Tries to get last known MD5 of a file from the cache db.

    @type  filename: string
    @param filename: File to work with.

    @rtype:  string|None
    @return: MD5 or None
    '''
    conn = _get_db_conn()
    cur = conn.cursor()
    args = (filename, )
    cur.execute('SELECT md5 FROM state WHERE filename = ?', args)
    row = cur.fetchone()
    return row[0] if row is not None else None


def _set_cached_file_md5(filename, md5):
    '''
    Updates or creates a record with filename and MD5 in the cache db.

    @type  filename: string
    @param filename: File to work with.
    @type       md5: string
    @param      md5: Checksum of the file.

    @return: None
    '''
    conn = _get_db_conn()
    cur = conn.cursor()
    # print 'UPDATE'
    args = (md5, filename)
    cur.execute('UPDATE state SET md5 = ? WHERE filename = ?', args)
    if cur.rowcount < 1:
        # print 'INSERT'
        args = (filename, md5)
        cur.execute('INSERT INTO state VALUES (?, ?)', args)
        # print cur.rowcount
    conn.commit()


def _command(filename, command, args):
    '''
    Executes a command with arguments and checks output

    @type  filename: string
    @param filename: file to work with
    @type   command: string
    @param  command: command to execute
    @type      args: string
    @param     args: arguments for the command

    @return: None
    '''
    if filename.startswith('/tmp/'):
        hash_filename = filename[len(_config['basedir']):]
    else:
        hash_filename = filename
    command_filename = '[%s] %s' % (command, hash_filename)
    if settings.FILE_STATE_CACHE_DB:
        md5 = _get_file_md5(filename)
        # print hash_filename, md5
        if md5 == _get_cached_file_md5(command_filename):
            # print 'skipping unchanged', filename, md5, '[%s]' % command
            return  # Nothing changed in file - skip it.
        # print 'inspecting', hash_filename, md5, '[%s]' % command

    basedir = os.path.dirname(filename)
    fname = os.path.basename(filename)
    cmd = 'cd %r && %s %s %r' % (basedir, command, args, fname)
    res, err = list(Popen([cmd], shell=True, stdout=PIPE,
                          stderr=PIPE).communicate())  # list for pylint
    res = res.strip('\n')
    if 'ERROR' in res:
        command = os.path.basename(command)
        raise Failure('%s found problems in %s.\n%s' % (command, filename,
                                                        res))
    err = err.strip('\n')
    lerr = err.lower()
    err_strings = (
        'command not found',
        'error',
        'failed',
        'warning',
        'traceback',
    )
    if lerr and filter(lambda fragment: fragment in lerr, err_strings):
        command = os.path.basename(command)
        raise Failure('%s found problems in %s.\n%s' % (command, filename,
                                                        err))

    if settings.FILE_STATE_CACHE_DB:
        # File seems to be ok - note that down.
        _set_cached_file_md5(command_filename, md5)


def _pylint_file(filename):
    '''
    Tests given filename using pylint against errors

    @type  filename: string
    @param filename: file to check

    @return: None
    '''
    _command(filename, settings.PYLINT_CMD, settings.PYLINT_ARGS)


def _pep8_file(filename):
    '''
    Tests given filename using pep8 against errors

    @type  filename: string
    @param filename: file to check

    @return: None
    '''
    _command(filename, settings.PEP8_CMD, settings.PEP8_ARGS)


def _utf8_file(filename):
    '''
    Tests given filename if it is compatible to UTF-8 and does not have a
    BOM header

    @type  filename: string
    @param filename: file to check

    @return: None
    '''
    try:
        fh = open(filename, 'r')
        data = fh.read()
        fh.close()
    except Exception as excp:
        raise Failure('File not found: %s' % filename)
    try:
        data.decode('utf-8').encode('utf-8')
    except Exception as excp:
        raise Failure('File %s is not in UTF-8.\n%s' % (filename, excp))

    if len(data) < 4:
        return  # ignore smaller files

    bom_dict = {  # bytepattern : name
        (0x00, 0x00, 0xFE, 0xFF): 'UTF-32-BE',
        (0xFF, 0xFE, 0x00, 0x00): 'UTF-32-LE',
        (0xFE, 0xFF, None, None): 'UTF-16-BE',
        (0xFF, 0xFE, None, None): 'UTF-16-LE',
        (0xEF, 0xBB, 0xBF, None): 'UTF-8',
    }

    (byte1, byte2, byte3, byte4) = tuple(map(ord, data[:4]))
    # try bom detection using 4 bytes, 3 bytes, or 2 bytes
    bom = bom_dict.get((byte1, byte2, byte3, byte4))
    if not bom:
        bom = bom_dict.get((byte1, byte2, byte3, None))
        if not bom:
            bom = bom_dict.get((byte1, byte2, None, None))

    if bom:
        raise Failure('File %s seems to be in UTF-8 but has an %s BOM header. '
                      'BOM headers are forbidden.' % (filename, bom))


def meta_info_py(config):
    '''
    Tests if info.py exists

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    if not os.path.exists(config['info_py']):
        raise Failure('info.py not found in repository root.')


def meta_pylint(config):
    '''
    Tests info.py using pylint against errors

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    _pylint_file(config['info_py'])


def meta_pep8(config):
    '''
    Tests info.py using pep8 against errors

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    _pep8_file(config['info_py'])


def meta_utf8(config):
    '''
    Tests if info.py is compatible to UTF-8

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    _utf8_file(config['info_py'])


def _bad_content_file(filename):
    '''
    Tests given filename if it contains bad content

    @type  filename: string
    @param filename: file to check

    @return: None
    '''
    global _content_filter_regex
    # first cache filters
    filter_dir = settings.CONTENT_FILTER_DIR
    if not _content_filter_regex:
        entries = os.listdir(filter_dir)
        entries = [entry for entry in entries if not entry.startswith('.')]
        entries.sort()
        # print entries
        p_join = os.path.join
        cfr_append = _content_filter_regex.append
        for entry in entries:
            entry_path = p_join(filter_dir, entry)
            if os.path.isdir(entry_path) or not entry.endswith('.txt'):
                continue
            fh = open(entry_path, 'rU')
            pattern = fh.read().strip()
            fh.close()
            cfr_append((entry, re.compile(pattern), pattern))
        # from pprint import pprint
        # pprint(_content_filter_regex)
    # now read file
    # print 'checking file:', filename
    fh = open(filename, 'rU')
    content = fh.read()
    fh.close()
    # and check content against filters
    re_flags = [
        re.S | re.U | re.M | re.I,
        re.U | re.M | re.I,
        re.M | re.I,
    ]
    for name, regex, pattern in _content_filter_regex:
        found = False
        line = 0
        for flags in re_flags:
            result = regex.search(content, flags)
            if result:
                start_pos = result.start(0)
                piece = content[:start_pos].replace('\r\n', '\n')
                line = len(piece.split('\n'))
                found = True
                break
        if found:
            raise Failure('Bad content filter %s matches file %s.\n'
                          'Match found at line: %d\nRegex used: %s' % (
                          name, filename, line, pattern))


def meta_bad_content(config):
    '''
    Tests if info.py contains bad content

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    _bad_content_file(config['info_py'])


def _load_info_py(info_py, path):
    '''
    Loads an info.py and returns the loaded module

    @type  info_py: string
    @param info_py: name of the info module to load
    @type     path: string
    @param    path: where to find the module to load

    @return: module object
    '''
    try:
        if info_py in sys.modules:
            del(sys.modules[info_py])
        sys.path.insert(0, path)
        module = __import__(info_py, None, None, ['*'], 0)
        return module
    except Exception as excp:
        raise Failure('Could not load info.py. Reason: %s' % excp)


def _unload_info_py(info_py):
    '''
    Unloads named info.py

    @type  info_py: string
    @param info_py: name of the info module to unload

    @return: None
    '''
    try:
        sys.path.pop(0)
        # del(module)
        del(sys.modules[info_py])
    except Exception as excp:
        raise Failure('Could not unload info.py. Reason: %s' % excp)


def meta_import(config):
    '''
    Tests if info.py can be imported

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    path = os.path.dirname(config['info_py'])
    info_py = os.path.splitext(os.path.basename(config['info_py']))[0]

    _load_info_py(info_py, path)
    _unload_info_py(info_py)


def _extract_vars_from_info_module(module):
    '''
    Extracts public variables from a module

    @type  module: module object
    @param module: module to scan for public vars

    @return: dict with public module vars
    '''
    vars_ = {}
    keys = dir(module)
    for key in keys:
        if not key.startswith('_'):
            vars_[key.upper()] = getattr(module, key)
    return vars_


def _check_info_field(key, vars_, reponame):
    '''
    Checks named field from vars_ dict

    @type       key: string
    @param      key: which key to test from der vars_ dict
    @type     vars_: dict
    @param    vars_: variables for testing against
    @type  reponame: string
    @param reponame: name of repository (for testing against NAME field)

    @return: None
    '''
    val = vars_.get(key)
    if key == 'NAME':
        if not isinstance(val, basestring):
            raise Failure('Field %s in info.py is not a string.' % key)
        if val != reponame:
            raise Failure('Value of field %s in info.py does not match '
                          'repository name.' % key)
    elif key == 'TAGS':
        if not isinstance(val, tuple):
            raise Failure('Field %s in info.py is not a tuple.' % key)
        if len(val) < 3:
            raise Failure('Field %s in info.py has to have at '
                          'least 3 items.' % key)
    elif key == 'LICENSE':
        if not isinstance(val, basestring):
            raise Failure('Field %s in info.py is not a string.' % key)
    elif key == 'HOMEPAGE':
        if not isinstance(val, basestring):
            raise Failure('Field %s in info.py is not a string.' % key)
    elif key in ('TEAM_LEADER', 'MAINTAINER', 'AUTHORS'):
        if not isinstance(val, dict):
            raise Failure('Field %s in info.py has to be a dict.' % key)
        for key_, val_ in val.iteritems():
            if not isinstance(val_, basestring):
                raise Failure('Value of key %s of dict field %s in info.py '
                              'has to be a string.' % (key_, key))
    elif key == 'INFO':
        if not isinstance(val, basestring):
            raise Failure('Field %s in info.py is not a string.' % key)
    elif key == 'SUMMARY':
        if not isinstance(val, basestring):
            raise Failure('Field %s in info.py is not a string.' % key)
    elif key == 'COMPATIBLE_WITH':
        if not isinstance(val, dict):
            raise Failure('Field %s in info.py has to be a dict.' % key)
        for key_, val_ in val.iteritems():
            if not isinstance(val_, list):
                raise Failure('Value of key %s of dict field %s in info.py '
                              'has to be a list.' % (key_, key))
            for item in val_:
                if not isinstance(item, basestring):
                    raise Failure('Value in list of key %s of dict field %s '
                                  'in info.py has to be a '
                                  'string.' % (key_, key))
    elif key == 'INSTALL_PATH':
        if not isinstance(val, basestring):
            raise Failure('Field %s in info.py is not a string.' % key)
    elif key == 'NOTES':
        if not isinstance(val, basestring):
            raise Failure('Field %s in info.py is not a string.' % key)
    elif key in ('EXCLUDES', 'REQUIRES'):
        if isinstance(val, dict):
            for key_, val_ in val.iteritems():
                if not isinstance(val_, basestring):
                    raise Failure('Value of key %s of dict field %s in '
                                  'info.py has to be a string.' % (key_, key))
        elif isinstance(val, list):
            for item in val:
                if not isinstance(item, dict):
                    raise Failure('List field %s in info.py can only contain '
                                  'dicts.' % key)
                for key_, val_ in item.iteritems():
                    if not isinstance(val_, basestring):
                        raise Failure('Value of key %s of dict in list field '
                                      '%s in info.py has to be a string.' % (
                                      key_, key))
        else:
            raise Failure('Field %s in info.py has to be either a dict or '
                          'a list.' % key)
    elif key == 'VIRTUAL':
        if not isinstance(val, dict):
            raise Failure('Field %s in info.py has to be a dict.' % key)
        for key_, val_ in val.iteritems():
            if not isinstance(val_, basestring):
                raise Failure('Value of key %s of dict field %s in info.py '
                              'has to be a string.' % (key_, key))
    elif key == 'DEPENDS_ON_FILES':
        if not isinstance(val, tuple):
            raise Failure('Field %s in info.py has to be a tuple.' % key)
        for item in val:
            if not isinstance(item, basestring):
                raise Failure('Tuple field %s in info.py can only contain '
                              'strings.' % key)
    elif key == 'PEAR_KEY':
        if not isinstance(val, basestring):
            raise Failure('Field %s in info.py is not a string.' % key)
    else:
        raise Failure('Field %s has not been implemented yet.' % key)


def meta_fields(config):
    '''
    Tests info.py if
    - all required fields are correctly filled
    - all optional fields are correctly filled if present
    - there are at least 3 tags given
    - depends_on_files is in correct format

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    path = os.path.dirname(config['info_py'])
    info_py = os.path.splitext(os.path.basename(config['info_py']))[0]
    module = _load_info_py(info_py, path)
    vars_ = _extract_vars_from_info_module(module)

    for key in settings.META_REQUIRED_FIELDS:
        if key not in vars_:
            raise Failure('Missing field in info.py: %s' % key)
        val = vars_.get(key)
        if isinstance(val, basestring) and len(val) < 1:
                raise Failure('Field %s in info.py is empty.' % key)

    for key in settings.META_REQUIRED_FIELDS:
        _check_info_field(key, vars_, config['reponame'])

    for key in settings.META_OPTIONAL_FIELDS:
        if key in vars_:
            _check_info_field(key, vars_, config['reponame'])

    del(module)
    _unload_info_py(info_py)


def meta_dependencies(config):
    '''
    Tests if all requirements of the info.py can be met

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    path = os.path.dirname(config['info_py'])
    info_py = os.path.splitext(os.path.basename(config['info_py']))[0]
    module = _load_info_py(info_py, path)
    vars_ = _extract_vars_from_info_module(module)

    requires = vars_.get('REQUIRES')
    # print requires

    saasrepo = SaaSRepo(
        settings.SR_HEADERS,
        settings.SR_LIST_URL,
        settings.SR_OVERVIEW_URL,
        settings.SR_INFO_URL,
        settings.SR_DOWNLOAD_URL,
    )

    if isinstance(requires, dict):
        for key_, val_ in requires.iteritems():
            if val_ == '*':
                module = key_
            else:
                module = '%s-%s' % (key_, val_)
            res = saasrepo.overview(module)
            try:
                if isinstance(res, list):
                    res = res[0]
                if res['name'] != key_:
                    raise Exception('dummy')
            except:
                raise Failure('Requirement %s cannot be satisfied.' % module)
    elif isinstance(requires, list):
        for item in requires:
            found = False
            for key_, val_ in item.iteritems():
                if val_ == '*':
                    module = key_
                else:
                    module = '%s-%s' % (key_, val_)
                res = saasrepo.overview(module)
                try:
                    if isinstance(res, list):
                        res = res[0]
                    if res['name'] == key_:
                        found = True
                except:
                    pass
            if not found:
                raise Failure('Requirement %s cannot be satisfied.' % item)

    del(module)
    _unload_info_py(info_py)


def package_build(config):
    '''
    Tests if build folder, build.tar.bz2 or build.tar.gz exists

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    if not os.path.exists(config['build']):
        if not os.path.exists(config['build'] + '.tar.bz2'):
            if not os.path.exists(config['build'] + '.tar.gz'):
                raise Failure('Neither build folder, build.tar.bz2 nor '
                              'build.tar.gz found in repository root.')


def package_readmes(config):
    '''
    Tests if the doc folder is present and readmes with content are being
    provided

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    if not os.path.exists(config['doc']):
        raise Failure('doc folder not found in repository root.')
    files = _get_readme_files(config['doc'])
    if not files:
        raise Failure('doc folder does not contain any valid readme files.')


def package_mandatory_readmes(config):
    '''
    Tests if the mandatory readmes have been provided

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    files = _get_readme_files(config['doc'])
    required_files = settings.README_MANDATORY_FILES
    for filename in required_files:
        found = False
        for file_ in files:
            if file_.endswith(filename):
                found = True
                break
        if not found:
            raise Failure('doc folder does not contain the mandatory readme '
                          'file %s.' % filename)


def _validate_readme_content(level_name, given_name, given_content):
    '''
    Tests given content against too few words, too few chars, too many
    repeatitions of a word or a character.

    @type     level_name: string
    @param    level_name: name of the level in the structure
    @type     given_name: string
    @param    given_name: name of the item
    @type  given_content: string
    @param given_content: text to validate

    @return: None
    '''
    words = {}
    word_count = 0
    for word in re.findall(r'\w+', given_content):
        word = word.lower()
        word_count += 1
        try:
            words[word] += 1
        except KeyError:
            words[word] = 1
    # print 'words =', words

    chars = {}
    char_count = 0
    for char in re.findall(r'\w', given_content):
        char = char.lower()
        char_count += 1
        try:
            chars[char] += 1
        except KeyError:
            chars[char] = 1
    # print 'chars =', chars

    if len(chars) < settings.README_BODY_MIN_CHARS:
        raise Exception('Body of %s %s contains too few chars: %s' % (
            level_name, given_name, given_content))

    if len(words) < settings.README_BODY_MIN_WORDS:
        raise Exception('Body of %s %s contains too few words: %s' % (
            level_name, given_name, given_content))

    for word, count in words.iteritems():
        # print count, len(words) * settings.README_BODY_WORD_BALANCE_FACTOR
        if count >= len(words) * settings.README_BODY_WORD_BALANCE_FACTOR:
            raise Exception('Body of %s %s contains inbalanced words: %s' % (
                level_name, given_name, given_content))

    # print given_content
    # print len(words), word_count * settings.README_BODY_WORD_COUNT_FACTOR
    if len(words) < word_count * settings.README_BODY_WORD_COUNT_FACTOR:
        raise Exception('Body of %s %s contains too few words: %s' % (
            level_name, given_name, given_content))

    # print given_content
    # print len(chars), char_count * settings.README_BODY_CHAR_COUNT_FACTOR
    if len(chars) < char_count * settings.README_BODY_CHAR_COUNT_FACTOR:
        raise Exception('Body of %s %s contains too few chars: %s' % (
            level_name, given_name, given_content))


def _validate_readme_structure_pattern(schema, struct, level=0, branch=''):
    '''
    Tests given readme structure against given schema

    @type  schema: list
    @param schema: a tree structure describing the mandatory structure
    @type  struct: list
    @param struct: parsed structure of the readme
    @type   level: int
    @param  level: depth in path to validate
    @type  branch: string
    @param branch: current branch in struct

    @return: None
    '''
    def join_branch(leaf):
        if branch:
            return '%s.%s' % (branch, leaf)
        else:
            return leaf

    try:
        # level_name = '.'.join(settings.README_LEVELS[0:level + 1])
        level_name = settings.README_LEVELS[level]
    except IndexError:
        raise Exception('Depth of %d not allowed in structure.' % level)

    # print '_validate_readme_structure_pattern()'
    # print 'level_name =', level_name
    # print 'branch =', branch
    # print 'schema =', schema
    # print 'struct =', struct

    for pos, given in enumerate(struct):
        # print 'given =', given
        given_name = given.get('name', None)
        given_content = given.get('content', False)
        given_children = given.get('children', None)

        wanted = schema

        wanted_pattern = wanted.get('name', None)
        wanted_content = wanted.get('content', False)
        wanted_children = wanted.get('children', None)
        # wanted_mandatory = wanted.get('mandatory', False)

        # check name
        if not re.match(wanted_pattern, given_name):
            raise Exception('Expected %s %s but found %s.' % (level_name,
                            wanted_pattern, join_branch(given_name)))

        # check content
        if wanted_content:
            if not given_content:
                raise Exception('Body of %s %s must not be empty.' % (
                    level_name, join_branch(given_name)))
            # check if content makes sense somehow
            _validate_readme_content(level_name, given_name, given_content)
        else:
            if given_content:
                raise Exception('Body of %s %s has to be empty.' % (
                    level_name, join_branch(given_name)))

        # check children
        if wanted_children:
            if not given_children:
                raise Exception('Missing children for %s %s.' % (
                    level_name, join_branch(given_name)))
            else:
                if wanted_children is True:
                    pass  # content of children don't matter - pass on
                elif isinstance(wanted_children, dict):
                    _validate_readme_structure_pattern(
                        wanted_children, given_children,
                        level + 1, join_branch(given_name))
                else:
                    _validate_readme_structure(
                        wanted_children, given_children,
                        level + 1, join_branch(given_name))
        else:
            if given_children:
                raise Exception('Children for %s %s are not allowed.' % (
                    level_name, join_branch(given_name)))


def _validate_readme_structure(schema, struct, level=0, branch=''):
    '''
    Tests given readme structure against given schema

    @type  schema: list
    @param schema: a tree structure describing the mandatory structure
    @type  struct: list
    @param struct: parsed structure of the readme
    @type   level: int
    @param  level: depth in path to validate
    @type  branch: string
    @param branch: current branch in struct

    @return: None
    '''
    def join_branch(leaf):
        if branch:
            return '%s.%s' % (branch, leaf)
        else:
            return leaf

    try:
        # level_name = '.'.join(settings.README_LEVELS[0:level + 1])
        level_name = settings.README_LEVELS[level]
    except IndexError:
        raise Exception('Depth of %d not allowed in structure.' % level)

    # print '_validate_readme_structure()'
    # print 'level_name =', level_name
    # print 'branch =', branch
    # print 'schema =', schema
    # print 'struct =', struct

    # copy schema for later modification
    schema = copy.deepcopy(schema)

    # create shallow index of schema
    index = dict([(item['name'], item) for item in schema])

    # ensure that struct does not contain unknown items
    for pos, given in enumerate(struct):
        given_name = given['name']
        if given_name not in index:
            raise Exception('Unexpected %s %s found.' % (
                level_name, join_branch(given_name)))

    # create shallow index of struct
    index = dict([(item['name'], item) for item in struct])

    # find entries from schema which are not in struct and not mandatory
    irrelevant_entries = []
    for wanted in schema:
        wanted_name = wanted['name']
        wanted_mandatory = wanted['mandatory']
        if wanted_name not in index:
            if not wanted_mandatory:
                irrelevant_entries.append(wanted)  # remove optional entry
            else:
                raise Exception('Missing mandatory %s %s.' % (
                    level_name, join_branch(wanted_name)))

    # remove irrelevant entries from schema
    for item in irrelevant_entries:
        schema.remove(item)

    for pos, wanted in enumerate(schema):
        wanted_name = wanted.get('name', None)
        wanted_content = wanted.get('content', False)
        wanted_children = wanted.get('children', None)
        wanted_mandatory = wanted.get('mandatory', False)

        given = struct[pos]

        given_name = given.get('name', None)
        given_content = given.get('content', False)
        given_children = given.get('children', None)

        # check name
        if given_name != wanted_name:
            raise Exception('Expected %s %s but found %s.' % (
                level_name, join_branch(wanted_name), join_branch(given_name)))

        # check content
        if wanted_content:
            if not given_content:
                raise Exception('Body of %s %s must not be empty.' % (
                    level_name, join_branch(given_name)))
            # check if content makes sense somehow
            _validate_readme_content(level_name, given_name, given_content)
        else:
            if given_content:
                raise Exception('Body of %s %s has to be empty.' % (
                    level_name, join_branch(given_name)))

        # check children
        if wanted_children:
            if not given_children:
                raise Exception('Missing children for %s %s.' % (
                    level_name, join_branch(given_name)))
            else:
                if wanted_children is True:
                    pass  # content of children don't matter - pass on
                elif isinstance(wanted_children, dict):
                    _validate_readme_structure_pattern(
                        wanted_children, given_children,
                        level + 1, join_branch(given_name))
                else:
                    _validate_readme_structure(
                        wanted_children, given_children,
                        level + 1, join_branch(given_name))
        else:
            if given_children:
                raise Exception('Children for %s %s are not allowed.' % (
                    level_name, join_branch(given_name)))


def _traverse_path(path, item):
    '''
    Traverses given path through item from readme structure

    @type  path: string
    @param path: path to traverse into
    @type  item: list
    @param item: children from readme item

    @return: child found through traversal or None
    '''
    path = path.split('.')
    for part in path:
        # print 'part =', part
        found = False
        for struct_child in item:
            if struct_child['name'] == part:
                item = struct_child['children']
                found = True
                break
        if not found:
            item = None
    return item


def _validate_readme_structure_children(schema, struct):
    '''
    Tests given readme structure children against given schema

    @type  schema: list
    @param schema: a tree structure describing the mandatory structure
    @type  struct: list
    @param struct: parsed structure of the readme

    @return: None
    '''
    # print 'index =', index
    balance_children = {}
    for child_path in settings.README_BALANCE_CHILDREN[:]:
        # print 'child_path =', child_path
        item = _traverse_path(child_path, struct)
        # print 'item =', item
        if item is not None:
            balance_children[child_path] = [obj['name'] for obj in item]
    # print 'balance_children =', balance_children

    master_items = balance_children[settings.README_BALANCE_MASTER]
    for path, children in balance_children.iteritems():
        if path != settings.README_BALANCE_MASTER:
            schema_item = _traverse_path(path, schema)
            if schema_item and schema_item.get('mandatory'):
                if len(children) != len(master_items):
                    raise Exception('Count of children of path %s does '
                                    'not match children of %s' % (path,
                                    settings.README_BALANCE_MASTER))
            additional_children = []
            for item in children:
                if item not in master_items:
                    additional_children.append(item)
            missing_children = []
            for item in master_items:
                if item not in children:
                    missing_children.append(item)
            if additional_children:
                raise Exception('Additional children in path %s: %s' % (
                    path, ', '.join(additional_children)))
            if missing_children:
                if schema_item and schema_item.get('mandatory'):
                    raise Exception('Missing children in path %s: %s' % (
                        path, ', '.join(missing_children)))


def package_readmes_parseable(config):
    '''
    Tests if all readmes are parseable (UTF-8, syntax, structure)

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    files = _get_readme_files(config['doc'])
    for file_ in files:
        _utf8_file(file_)
        try:
            parser = Parser()
            struct = parser.parse(open(file_, 'r').read())
            # from pprint import pprint
            # pprint(struct)
            if not struct:
                raise Exception('Invalid document structure. Result is empty.')
            _validate_readme_structure(settings.README_SCHEMA, struct)
            _validate_readme_structure_children(settings.README_SCHEMA, struct)
        except Exception as excp:
            raise Failure('Could not parse readme file %s. Reason: %s' % (
                          file_, excp))


def _find_files(folder, regex, check_content=False):
    '''
    Recursively finds all files matching regex within given folder

    @type         folder: string
    @param        folder: folder to search in
    @type          regex: string or compiled regex object
    @param         regex: regex pattern or compiled object (faster)
    @type  check_content: boolean
    @param check_content: whether to check files for ignore-file-string

    @return: list with files
    '''
    os_exists = os.path.exists
    os_join = os.path.join
    ignore_path_file = settings.CODING_STANDARDS_IGNORE_PATH
    ignore_file_string = settings.CODING_STANDARDS_IGNORE_FILE

    if isinstance(regex, basestring):
        regex = re.compile(regex)

    filelist = set()
    filelist_update = filelist.update
    filelist_add = filelist.add

    if not os_exists(folder):
        return filelist

    def path_is_ignored(path):
        for ignore, rule in _ignore_files:
            if fnmatch.fnmatch(path, ignore):
                _log(True, 'Path is marked as to be ignored: %s\n'
                     '.qgignore rule: %s' %
                     (path, rule))
                return True
        else:
            return False

    entries = os.listdir(folder)
    for entry in entries:
        entry = os_join(folder, entry)
        if path_is_ignored(entry):
            continue
        if os.path.isdir(entry):
            folder_entry = os_join(folder, entry)
            if not os_exists(os_join(folder_entry, ignore_path_file)):
                filelist_update(_find_files(folder_entry, regex,
                                            check_content))
            else:
                _log(True, 'Path is marked as to be ignored: %s' %
                     folder_entry)
        elif regex.match(entry, re.U | re.I) or regex.match(entry, re.I):
            found = False
            if check_content:
                fh = open(entry, 'rU')
                for line in range(0, 10):
                    line = fh.readline()
                    if line is None:
                        break
                    elif ignore_file_string in line:
                        # print 'ignore file %s' % file_
                        found = True
                        break
                fh.close()
            if not found:
                filelist_add(entry)

    return filelist


def _get_xxx_files(build_folder, regex):
    '''
    Finds all files for given regex within given folder

    @type  build_folder: string
    @param build_folder: folder to search in
    @type         regex: string
    @param        regex: regex pattern

    @return: list with unmarked files
    '''
    build_ignore_file = os.path.join(build_folder,
                                     settings.CODING_STANDARDS_IGNORE_PATH)
    if os.path.exists(build_ignore_file):
        raise Failure('Folder build is marked to be ignored but this is not '
                      'allowed.')
    files = list(_find_files(build_folder, regex, True))
    # print 'files =', files
    return files


def _get_py_files(build_folder):
    '''
    Finds all .py files within given folder

    @type  build_folder: string
    @param build_folder: folder to search in

    @return: list with unmarked py files
    '''
    global _py_files
    if _py_files is not None:
        return _py_files
    _py_files = _get_xxx_files(build_folder, settings.FIND_PY_FILES_REGEX)
    return _py_files


def _get_php_files(build_folder):
    '''
    Finds all .php files within given folder

    @type  build_folder: string
    @param build_folder: folder to search in

    @return: list with unmarked php files
    '''
    global _php_files
    if _php_files is not None:
        return _php_files
    _php_files = _get_xxx_files(build_folder, settings.FIND_PHP_FILES_REGEX)
    return _php_files


def _get_xml_files(build_folder):
    '''
    Finds all .xml files within given folder

    @type  build_folder: string
    @param build_folder: folder to search in

    @return: list with unmarked xml files
    '''
    global _xml_files
    if _xml_files is not None:
        return _xml_files
    _xml_files = _get_xxx_files(build_folder, settings.FIND_XML_FILES_REGEX)
    return _xml_files


def _get_java_files(build_folder):
    '''
    Finds all .java files within given folder

    @type  build_folder: string
    @param build_folder: folder to search in

    @return: list with unmarked java files
    '''
    global _java_files
    if _java_files is not None:
        return _java_files
    _java_files = _get_xxx_files(build_folder, settings.FIND_JAVA_FILES_REGEX)
    return _java_files


def _get_js_files(build_folder):
    '''
    Finds all .js files within given folder

    @type  build_folder: string
    @param build_folder: folder to search in

    @return: list with unmarked js files
    '''
    global _js_files
    if _js_files is not None:
        return _js_files
    build_ignore_file = os.path.join(build_folder,
                                     settings.CODING_STANDARDS_IGNORE_PATH)
    if os.path.exists(build_ignore_file):
        raise Failure('Folder build is marked to be ignored but this is not '
                      'allowed.')
    _js_files = list(_find_files(build_folder, settings.FIND_JS_FILES_REGEX,
                                 True))
    # Filter out minified files.
    _js_files = filter(lambda filename: not filename.endswith('.min.js'), _js_files)
    return _js_files


def _get_css_files(build_folder):
    '''
    Finds all .css files within given folder

    @type  build_folder: string
    @param build_folder: folder to search in

    @return: list with unmarked css files
    '''
    global _css_files
    if _css_files is not None:
        return _css_files
    build_ignore_file = os.path.join(build_folder,
                                     settings.CODING_STANDARDS_IGNORE_PATH)
    if os.path.exists(build_ignore_file):
        raise Failure('Folder build is marked to be ignored but this is not '
                      'allowed.')
    _css_files = list(_find_files(build_folder, settings.FIND_CSS_FILES_REGEX,
                                  True))
    # Filter out minified files.
    _css_files = filter(lambda filename: not filename.endswith('.min.css'), _css_files)
    return _css_files


def package_utf8(config):
    '''
    Tests if all Python and PHP files are compatible to UTF-8

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    py_files = _get_py_files(config['build'])
    php_files = _get_php_files(config['build'])
    for file_ in py_files + php_files:
        _utf8_file(file_)


def package_bad_content(config):
    '''
    Tests if all Python and PHP files do not contain bad content

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    py_files = _get_py_files(config['build'])
    php_files = _get_php_files(config['build'])
    # patterns = settings.CONTENT_FILTER_DIR
    for file_ in py_files + php_files:
        _bad_content_file(file_)


def package_pylint(config):
    '''
    Tests all Python files using pylint against errors

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    py_files = _get_py_files(config['build'])
    for file_ in py_files:
        _pylint_file(file_)


def package_pep8(config):
    '''
    Tests all Python files using pep8 against errors

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    py_files = _get_py_files(config['build'])
    for file_ in py_files:
        _pep8_file(file_)


def package_phplint(config):
    '''
    Tests all PHP files using phplint against errors

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    php_files = _get_php_files(config['build'])
    for file_ in php_files:
        cmd = settings.PHPLINT_CMD
        cmd += ' ' + settings.PHPLINT_ARGS % file_
        res, err = list(Popen([cmd], shell=True, stdout=PIPE,
                              stderr=PIPE).communicate())  # list for pylint
        if not res.lower().startswith('no syntax errors detected'):
            raise Failure('phplint found errors in %s.\n%s' % (file_, res))


def package_phpcodesniffer(config):
    '''
    Tests all PHP files using PHPCodeSniffer against errors

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    cmd = settings.PHPCS_CMD
    args = settings.PHPCS_ARGS
    php_files = _get_php_files(config['build'])
    for file_ in php_files:
        _command(file_, cmd, args)


def package_xml_valid(config):
    '''
    Tests all XML files using tidy against errors

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    xml_files = _get_xml_files(config['build'])
    for filename in xml_files:
        _command(filename, settings.TIDY_XML_CMD, settings.TIDY_XML_ARGS)


def package_java_checkstyle(config):
    '''
    Tests all java files using checkstyle

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    def chunks(files_list, batch_size):
        '''
        Split list into chunks.

        @type  files_list: list
        @param files_list: list with current files.
        @type  batch_size: int
        @param batch_size: list with current files.

        @return: None
        '''
        for i in xrange(0, len(files_list), batch_size):
            yield files_list[i:i+batch_size]

    java_files = _get_java_files(config['build'])
    for files in chunks(java_files, settings.JAVA_CHECKSTYLE_BATCH_SIZE):
        cmd = '%s %s %s' % (settings.JAVA_CHECKSTYLE_CMD,
                            settings.JAVA_CHECKSTYLE_ARGS,
                            ' '.join(files))
        res, err = list(Popen([cmd], shell=True, stdout=PIPE,
                              stderr=PIPE).communicate())
        if 'warning:' in res:
            raise Failure('Java checkstyle found warnings in \n%s' % (res))


def package_djangolint(config):
    '''
    Tests the project using DjangoLint against errors

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    path = settings.BUILD_PATH

    found = False
    for django_file in settings.DJANGOLINT_FILES:
        if os.path.exists(os.path.join(path, django_file)):
            found = True
            break

    if found:
        cmd = settings.DJANGOLINT_CMD % path
        res, err = Popen([cmd], shell=True, stdout=PIPE,
                         stderr=PIPE).communicate()
        if res:
            raise Failure('djangolint found errors in build folder.\n%s' % res)
        if err:
            raise Failure('djangolint found errors in build folder.\n%s' % err)


def package_phpmd(config):
    '''
    Tests the project using PHPMD against violations.

    @type  config: dict
    @param config: Dict with current configuration.

    @return: None
    '''
    php_files = _get_php_files(config['build'])
    for file_ in php_files:
        cmd = settings.PHPMD_CMD
        cmd += ' ' + settings.PHPMD_ARGS % file_
        res, err = list(Popen([cmd], shell=True, stdout=PIPE,
                              stderr=PIPE).communicate())  # list for pylint
        if res.strip('\n'):
            raise Failure('phpmd found errors in %s.\n%s' % (file_, res))


def package_jslint(config):
    '''
    Test all JS files using jslint

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''
    js_files = _get_js_files(config['build'])
    for filename in js_files:
        basedir = os.path.dirname(filename)
        fname = os.path.basename(filename)
        cmd = 'cd %r && %s %s %r' % (basedir, settings.JSLINT_CMD,
                                     settings.JSLINT_ARGS, fname)
        res, err = list(Popen([cmd], shell=True, stdout=PIPE,
                              stderr=PIPE).communicate())  # list for jslint
        parts = filename.split('/')
        if not parts[-1] + ' is OK.' in res:   # check success msg instead of checking errors
            raise Failure('jslint found errors in %s.\n%s' % (filename, res))


def package_csslint(config):
    '''
    Test all CSS files using csslint

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''

    css_files = _get_css_files(config['build'])

    for filename in css_files:
        basedir = os.path.dirname(filename)
        fname = os.path.basename(filename)
        cmd = 'cd %r && %s %s %r' % (basedir, settings.CSSLINT_CMD,
                                     settings.CSSLINT_ARGS, fname)
        res, err = list(Popen([cmd], shell=True, stdout=PIPE,
                              stderr=PIPE).communicate())  # list for csslint
        if res:   # check empty msg on success
            raise Failure('csslint found errors in %s.\n%s' % (filename, res))


def package_jshint(config):
    '''
    Test all JS files using jshint

    @type  config: dict
    @param config: dict with current configuration

    @return: None
    '''

    js_files = _get_js_files(config['build'])

    for filename in js_files:
        basedir = os.path.dirname(filename)
        fname = os.path.basename(filename)
        cmd = 'cd %r && %s %s %r' % (basedir, settings.JSHINT_CMD,
                                     settings.JSHINT_ARGS, fname)
        res, err = list(Popen([cmd], shell=True, stdout=PIPE,
                              stderr=PIPE).communicate())  # list for jshint
        if res:   # check empty msg on success
            raise Failure('jshint found errors in %s.\n%s' % (filename, res))


if __name__ == '__main__':
    for item in ('-v', '--verbose'):
        if item in sys.argv:
            _verbose = True
            sys.argv.remove(item)
    for item in ('-b', '--benchmark'):
        if item in sys.argv:
            _benchmark = True
            sys.argv.remove(item)
    try:
        _config.update(_gen_config())
        _apply_repository_overrides(_config)
        _read_qgignore_file(_config)
    except NoOpException as excp:
        print('INFO: %s' % excp)
        sys.exit(0)
    test_times = []
    test_sum = 0
    try:
        vars_ = globals()
        for test in settings.TESTS:
            test_start = time.time()
            vars_[test](_config)
            test_stop = time.time()
            test_dur = test_stop - test_start
            test_times.append((test, test_dur))
            test_sum += test_dur
        _log(True, 'OK')
    except Failure as excp:
        _log(False, str(excp))
        print('ERROR: %s' % excp)
        if _config['auth_user'] in settings.UNBLOCKABLE_USERS:
            sys.exit(0)
        else:
            sys.exit(1)
    finally:
        if _benchmark:
            print('Execution times:')
            for name, dur in test_times:
                print('%30s: %8s secs' % (name, '%.4f' % dur))
            print('%30s: %8s secs' % ('all', '%.4f' % test_sum))
            print()
        if _config['cleanup']:
            # print 'rm -rf %r' % config['basedir']
            os.system('rm -rf %r' % _config['basedir'])
