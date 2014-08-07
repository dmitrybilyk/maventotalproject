#!/usr/bin/env python
'''
Stripped down version of the Quality Gate for validating readmes only.

@package: symmetrics_saasrepo_qualitygate
@copyright: 2011 symmetrics gmbh. All rights reserved.
@author: Oktay Acikalin <ok@symmetrics.de>
'''

from __future__ import with_statement
import sys
import copy
import re
import time


_config = {}
_readme_files = None
_verbose = False
_benchmark = False


class Settings(object):

    TESTS = ['package_readmes_parseable']
    README_LEVELS = ['chapter', 'section', 'subsection']  # depth of schema
    README_SCHEMA = [
        dict(name='DOCUMENTATION', mandatory=True, content=False, children=[
            dict(name='INSTALLATION',
                 mandatory=True,
                 content=True,
                 children=None,
            ),
            dict(name='USAGE',
                 mandatory=True,
                 content=True,
                 children=None,
            ),
            dict(name='FUNCTIONALITY',
                 mandatory=True,
                 content=False,
                 children=dict(name=r'^[A-Za-z0-9]{1,2}$',  # template mode
                               mandatory=True,
                               content=True,
                               children=None,
                 ),
            ),
            dict(name='TECHNICAL',
                 mandatory=True,
                 content=True,
                 children=None,
            ),
            dict(name='ENVIRONMENT',
                 mandatory=False,
                 content=True,
                 children=dict(name=r'^[A-Za-z0-9]{1,2}$',  # template mode
                               mandatory=False,
                               content=True,
                               children=None,
                 ),
            ),
            dict(name='PROBLEMS',
                 mandatory=False,
                 content=True,
                 children=None,
            ),
        ]),
        dict(name='TESTCASES', mandatory=True, content=False, children=[
            dict(name='BASIC',
                 mandatory=True,
                 content=False,
                 children=dict(name=r'^[A-Za-z0-9]{1,2}$',  # template mode
                               mandatory=True,
                               content=True,
                               children=None,
                 ),
            ),
            dict(name='CATCHABLE',
                 mandatory=False,
                 content=False,
                 children=dict(name=r'^[A-Za-z0-9]{1,2}$',  # template mode
                               mandatory=False,
                               content=True,
                               children=None,
                 ),
            ),
            dict(name='STRESS',
                 mandatory=False,
                 content=False,
                 children=dict(name=r'^[A-Za-z0-9]{1,2}$',  # template mode
                               mandatory=False,
                               content=True,
                               children=None,
                 ),
            ),
        ]),
    ]
    README_BODY_MIN_CHARS = 8
    README_BODY_MIN_WORDS = 3
    README_BODY_WORD_BALANCE_FACTOR = 2.0 / 3.0
    README_BODY_WORD_COUNT_FACTOR = 2 / 3
    README_BODY_CHAR_COUNT_FACTOR = 2 / 6
    README_BALANCE_CHILDREN = [
        'DOCUMENTATION.FUNCTIONALITY',
        'TESTCASES.BASIC',
        'TESTCASES.CATCHABLE',
        'TESTCASES.STRESS',
    ]
    README_BALANCE_MASTER = 'DOCUMENTATION.FUNCTIONALITY'

settings = Settings()


class Parser(object):
    '''
    Parser for readme files built according to symmetrics readme style
    standards

    @package: symmetrics_saasrepo_docs.docs
    @copyright: 2009 symmetrics gmbh. All rights reserved.
    @author: Oktay Acikalin <ok@symmetrics.de>
    '''

    def __init__(self):
        super(Parser, self).__init__()
        self.chapters = []
        self.sections = []
        self.subsections = []
        self.markers = dict(
            chapters=r'^[*]{1} (?P<name>\w+)',
            sections=r'^[*]{2} (?P<name>\w+)',
            subsections=r'^[*]{3} (?P<name>\w+):?',
            invsections=r'^[*]{4,} (?P<name>\w+):?',
        )
        for cname, cpattern in self.markers.iteritems():
            self.markers[cname] = re.compile(cpattern, re.M | re.S)

    def get_parts(self, regex, content):
        '''
        Finds all parts
        @param regex: raw string with regex
        @param content: string
        @return: list with found parts
        '''
        matches = regex.finditer(content)
        parts = []
        for match in matches:
            start, stop = match.span()
            parts.append(dict(name=match.group(1), start=start, stop=stop,
                                 content_start=start + len(match.group(0))))
            if len(parts):
                last = parts[len(parts) - 2]
                last['stop'] = start - 1
        if len(parts):
            last = parts[len(parts) - 1]
            last['stop'] = len(content)
            for part in parts:
                _text = content[part['content_start']:part['stop']]
                content_part = _text.strip()
                part['content'] = content_part
                # print 'content_part =', content_part
                inner_content = ''
                for line in content_part.split('\n'):
                    if not line.startswith('*'):
                        inner_content += line + '\n'
                    else:
                        break
                part['inner_content'] = inner_content. \
                                        decode('UTF-8', 'replace'). \
                                        encode('UTF-8').strip()
        return parts

    def parse(self, content):
        '''
        Parses some text and returns it's structure
        @param content: string
        @return: list with structure
        '''
        _struct = []
        children = _struct
        chapters = self.get_parts(self.markers['chapters'], content)
        for chapter in chapters:
            # print 'CONTENT %s = %s' % (chapter['name'],
            #                            chapter['inner_content'])
            _children = []
            children.append(dict(name=chapter['name'],
                                 content=chapter['inner_content'],
                                 children=_children))
            content = chapter['content']
            sections = self.get_parts(self.markers['sections'], content)
            for section in sections:
                # print 'SECTION %s = %s' % (section['name'],
                #                            section['inner_content'])
                __children = []
                _children.append(dict(name=section['name'],
                                      content=section['inner_content'],
                                      children=__children))
                content = section['content']
                subsections = self.get_parts(self.markers['subsections'],
                                             content)
                for subsection in subsections:
                    # print 'SUBSECTION %s = %s' % (subsection['name'],
                    #     subsection['inner_content'])
                    ___children = []
                    __children.append(dict(name=subsection['name'],
                        content=subsection['inner_content'],
                        children=___children))
                    content = subsection['content']
                    invsections = self.get_parts(self.markers['invsections'],
                                                 content)
                    for invsection in invsections:
                        # print 'INVSECTION %s = %s' % (invsection['name'],
                        #     invsection['inner_content'])
                        ___children.append(dict(name=invsection['name'],
                            content=invsection['inner_content']))
        return _struct


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
        print msg


def _get_readme_files():
    '''
    Returns list of readme files.
    
    @return: List with readme files.
    '''
    return _readme_files


def _utf8_file(filename):
    '''
    Tests given filename if it is compatible to UTF-8 and does not have a
    BOM header
    
    @type  filename: string
    @param filename: file to check
    
    @return: None
    '''
    try:
        fh = file(filename, 'r')
        data = fh.read()
        fh.close()
    except Exception, e:
        raise Failure('File not found: %s' % filename)
    try:
        data.decode('utf-8').encode('utf-8')
    except Exception, e:
        raise Failure('File %s is not in UTF-8.\n%s' % (filename, e))
    
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
                    _validate_readme_structure_pattern(wanted_children,
                        given_children, level + 1, join_branch(given_name))
                else:
                    _validate_readme_structure(wanted_children, given_children,
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
            raise Exception('Unexpected %s %s found.' % (level_name,
                join_branch(given_name)))
    
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
                raise Exception('Missing mandatory %s %s.' % (level_name,
                    join_branch(wanted_name)))
    
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
            raise Exception('Expected %s %s but found %s.' % (level_name,
                join_branch(wanted_name), join_branch(given_name)))
        
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
                    _validate_readme_structure_pattern(wanted_children,
                        given_children, level + 1, join_branch(given_name))
                else:
                    _validate_readme_structure(wanted_children, given_children,
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
    files = _get_readme_files()
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
        except Exception, e:
            raise Failure('Could not parse readme file %s. Reason: %s' % (
                          file_, e))


if __name__ == '__main__':
    for item in ('-v', '--verbose'):
        if item in sys.argv:
            _verbose = True
            sys.argv.remove(item)
    for item in ('-b', '--benchmark'):
        if item in sys.argv:
            _benchmark = True
            sys.argv.remove(item)
    _readme_files = sys.argv[1:]
    test_times = []
    test_sum = 0
    try:
        vars_ = globals()
        for test in settings.TESTS:
            test_start = time.time()
            vars_.get(test)(_config)
            test_stop = time.time()
            test_dur = test_stop - test_start
            test_times.append((test, test_dur))
            test_sum += test_dur
    except Failure, e:
        print 'ERROR: %s' % e
        sys.exit(1)
    finally:
        if _benchmark:
            print 'Execution times:'
            for name, dur in test_times:
                print '%30s: %8s secs' % (name, '%.4f' % dur)
            print '%30s: %8s secs' % ('all', '%.4f' % test_sum)
            print
