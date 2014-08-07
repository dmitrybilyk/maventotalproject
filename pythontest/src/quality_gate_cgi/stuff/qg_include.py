#!/usr/bin/env python2.5
'''
Script to find all py and php files in a directory tree and remove the
@codingStandardsIgnoreFile header mark.

@package: symmetrics_saasrepo_qualitygate/stuff
@copyright: 2010 symmetrics gmbh. All rights reserved.
@author: Oktay Acikalin <ok@symmetrics.de>
'''

import os
import sys
from subprocess import Popen, PIPE


_py_files = None
_php_files = None


def _get_py_files(build_folder):
    '''
    Finds all .py files within given folder
    
    @type  build_folder: string
    @param build_folder: folder to search in
    
    @return: a list of py files which have been marked
    '''
    global _py_files
    if _py_files is not None:
        return _py_files
    cmd = 'find %r -iname "*.py"' % build_folder
    res, err = Popen([cmd], shell=True, stdout=PIPE, stderr=PIPE).communicate()
    _py_files = res.strip().split('\n')
    for file_ in _py_files[:]:
        if not len(file_):
            _py_files.remove(file_)
        else:
            fh = open(file_, 'rU')
            found = False
            for line in range(0, 10):
                line = fh.readline()
                if line is None:
                    break
                if '@codingStandardsIgnoreFile' in line:
                    found = True
                    # print 'respect file %s' % file_
                    break
            if not found:
                _py_files.remove(file_)
            fh.close()
    return _py_files


def _get_php_files(build_folder):
    '''
    Finds all .php files within given folder
    
    @type  build_folder: string
    @param build_folder: folder to search in
    
    @return: a list of php files which have been marked
    '''
    global _php_files
    if _php_files is not None:
        return _php_files
    cmd = 'find %r -iname "*.php"' % build_folder
    res, err = Popen([cmd], shell=True, stdout=PIPE, stderr=PIPE).communicate()
    _php_files = res.strip().split('\n')
    for file_ in _php_files[:]:
        if not len(file_):
            _php_files.remove(file_)
        else:
            fh = open(file_, 'rU')
            found = False
            for line in range(0, 10):
                line = fh.readline()
                if line is None:
                    break
                if '@codingStandardsIgnoreFile' in line:
                    found = True
                    # print 'respect file %s' % file_
                    break
            if not found:
                _php_files.remove(file_)
            fh.close()
    return _php_files


def _read_file(filename):
    '''
    Reads content of a file and returns the closed file handle and its data
    
    @type  filename: string
    @param filename: file to read
    
    @return: (file handle, data string)
    '''
    fh = open(filename, 'rU')
    data = fh.read().split('\n')
    fh.close()
    return fh, data


def _write_file(fh, data):
    '''
    Writes data into file abbriviated from given file handle
    
    @type    fh: file handle
    @param   fh: file handle to get filename from
    @type  data: string
    @param data: data to write into file
    
    @return: None
    '''
    newlines = fh.newlines
    if newlines is None:
        eol = '\n'
    elif isinstance(newlines, tuple):
        eol = newlines[0]
    else:
        eol = newlines
    fh = open(fh.name, 'w')
    fh.write(eol.join(data))
    fh.close()


if __name__ == '__main__':
    folder = sys.argv[1]
    # print folder
    py_files = _get_py_files(folder)
    php_files = _get_php_files(folder)
    # print py_files
    # print php_files

    for filename in py_files:
        print 'Modifying: %s' % filename
        fh, data = _read_file(filename)
        for line in range(0, 10):
            if '# @codingStandardsIgnoreFile' in data[line]:
                del data[line]
                break
        _write_file(fh, data)

    for filename in php_files:
        print 'Modifying: %s' % filename
        fh, data = _read_file(filename)
        for line in range(0, 10):
            if '// @codingStandardsIgnoreFile' in data[line]:
                del data[line]
                break
        _write_file(fh, data)
