#!/usr/bin/env python
'''
Log entry forwarder, requires statistics UI API

@package: symmetrics_saasrepo_qualitygate/forwarder
@copyright: 2010 symmetrics gmbh. All rights reserved.
@author: Oktay Acikalin <ok@symmetrics.de>
'''

import os
import sys
import re
import time
import json
import urllib
import urllib2

import config
import apache_log


class QGLogParser(apache_log.ApacheLog):

    def register_field_parsers(self):
        '''
        Registers own timestamp parser

        @return: None
        '''
        # self.register_field_parser('timestamp',
        #                            self._parse_field_datetime_to_struct)
        self.register_field_parser('timestamp',
                                   self._parse_field_datetime_to_timestamp)

    def _parse_field_datetime_to_struct(self, data, key, val):
        '''
        Reformats given datetime string and adds it as a struct to data

        @type  data: list
        @param data: list with tuples to append to
        @type   key: string
        @param  key: name of field
        @type   val: string
        @param  val: value of field

        @return: None
        '''
        format = '%Y-%m-%d %H:%M:%S'
        struct = time.strptime(val, format)
        data.append((key, struct))

    def _parse_field_datetime_to_timestamp(self, data, key, val):
        '''
        Reformats given datetime string and adds it as a timestamp to data

        @type  data: list
        @param data: list with tuples to append to
        @type   key: string
        @param  key: name of field
        @type   val: string
        @param  val: value of field

        @return: None
        '''
        format = '%Y-%m-%d %H:%M:%S'
        struct = time.strptime(val, format)
        timestamp = int(time.mktime(struct))
        data.append(('timestamp', timestamp))

    def parse_all(self, content):
        '''
        Searches in content, parses matches and puts it into a result list

        @type content: text
        @param content: text to search within

        @return: list with resulting tuples
        '''
        parsers = self._field_parsers
        matches = re.findall(self.pattern, content, re.M | re.U | re.S)
        # print matches
        results = []
        for match in matches:
            data = []
            for n, key in enumerate(self.keys):
                val = match[n]
                parser = parsers.get(key, self._parse_field_default)
                parser(data, key, val)
            results.append(tuple(data))
        return results


if __name__ == '__main__':
    apache_log.MAPPING = {
        r'%t': ('timestamp', r'(?P<timestamp>\d+-\d+-\d+ \d+:\d+:\d+)'),
        r'%h': ('host', r'(?P<host>[\d\w.]+)'),
        r'%d:': ('service', r'(?P<service>[\w\d]+):'),
        r'%s': ('status', r'status=(?P<status>\w+)'),
        r'%n': ('node', r'node=["]?(?P<node>.+?)["]?'),
        r'%u': ('user', r'user=["]?(?P<user>.+?)["]?'),
        r'%r': ('repository', r'repository=(?P<repository>[\d\w]+)'),
        r'%b': ('branch', r'branch=["]?(?P<branch>.+?)["]?'),
        r'%m': ('message', r'message="(?P<message>.+?)"'),
    }
    pattern = r'%t %h %d: %s %n %u %r %b %m'
    parser = QGLogParser(pattern)

    pos = 0
    if os.path.exists(config.LOG_POS_FILE):
        fh = open(config.LOG_POS_FILE, 'rb')
        pos = int(fh.readline().strip())
        fh.close()

    filesize = os.stat(config.LOG_FILE)[6]
    if filesize < pos:
        print('File has shrinked - probably due to rotation or deletion.')
        print('Setting pos to: 0')
        pos = 0

    fh = open(config.LOG_FILE, 'rb')
    if pos > 0:
        print('Skipping to pos: %d' % pos)
        fh.seek(pos)
    data = fh.read()
    pos = fh.tell()
    fh.close()

    # print 'pattern =', parser.pattern
    print('Current pos: %s' % pos)

    matches = parser.parse_all(data)
    print('Matches found = %d' % len(matches))
    for num, item in enumerate(matches):
        matches[num] = dict(item)
        matches[num]['status'] = 'true' in matches[num]['status'].lower()

    from pprint import pprint
    pprint(matches)

    if not matches:
        fh = open(config.LOG_POS_FILE, 'wb')
        fh.write(str(pos))
        fh.close()
        sys.exit()

    data = json.dumps(matches)
    data = urllib.urlencode(dict(data=data))
    request = urllib2.Request(url=config.REST_POST_URL, headers=config.HEADERS)
    ret = urllib2.urlopen(request, data).read()
    print('ret = %s' % ret)

    if 'ok' not in ret.lower():
        sys.exit(1)

    fh = open(config.LOG_POS_FILE, 'wb')
    fh.write(str(pos))
    fh.close()
