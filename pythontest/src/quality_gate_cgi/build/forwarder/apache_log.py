'''
Apache log parser

@package: tacko/loganalyzer.lib
@license: GPL v2
@copyright: 2010 Oktay Acikalin
@author: Oktay Acikalin <ok@ryotic.de>
'''

import re
import time


# Format copied and pasted from apache conf. Use raw string + single quotes.
# LOG_FORMAT_VHOST_COMBINED = r'%v:%p %{X-Forwarded-For}i %l %u %t \"%r\" ' \
#                             r'%>s %b \"%{Referer}i\" \"%{User-Agent}i\"'
LOG_FORMAT_COMBINED = r'%h %l %u %t \"%r\" %>s %b \"%{Referer}i\" ' \
                      r'\"%{User-Agent}i\"'
LOG_FORMAT_COMMON = r'%h %l %u %t \"%r\" %>s %b'
# LOG_FORMAT_REFERER = r'%{Referer}i -> %U'
LOG_FORMAT_AGENT = r'%{User-agent}i'
LOG_FORMAT_COMBINED_PROXY = r'%{X-Forwarded-For}i %l %u %t \"%r\" %>s %b ' \
                            r'\"%{Referer}i\" \"%{User-Agent}i\"'

LOG_VAR_SIGN = '%'

RE_START = r'^'
RE_SPACER = r'\s+'
RE_STOP = r'$'

MAPPING = {
    r'%h': ('ips', r'(?P<host>[\d\w.]+)'),
    r'%{X-Forwarded-For}i': ('hosts', r'(?P<hosts>[\d\w., ]+)'),
    r'%l': ('ident', r'(?P<ident>.+)'),
    r'%u': ('user', r'(?P<user>.+)'),
    r'%t': ('datetime', r'\[(?P<datetime>[\d/\w: -+]+)\]'),
    r'\"%r\"': ('query', r'"(?P<query>.*)"'),
    r'%>s': ('status', r'(?P<status>\d+)'),
    r'%b': ('bytes', r'(?P<bytes>[\d-]+)'),
    r'%B': ('bytes', r'(?P<bytes>\d+)'),
    r'\"%{Referer}i\"': ('referer', r'"(?P<referer>.*)"'),
    r'\"%{User-Agent}i\"': ('useragent', r'"(?P<useragent>.*)"'),
    
    # TODO unimplemented
    # r'%v:%p': ('', r''),
    # r'%U': ('', r''),
}

FIELD_PARSER_DATETIME_MONTHS = {
    'Jan': '01',
    'Feb': '02',
    'Mar': '03',
    'Apr': '04',
    'May': '05',
    'Jun': '06',
    'Jul': '07',
    'Aug': '08',
    'Sep': '09',
    'Oct': '10',
    'Nov': '11',
    'Dec': '12',
}


class ApacheLog(object):
    
    def __init__(self, format):
        '''
        Initializes the object and precompiles regex according to format
        
        @type format: string
        @param format: format string with variables from mapping table
        
        @return: None
        '''
        super(ApacheLog, self).__init__()
        pattern = []
        keys = []
        for part in format.split(' '):
            if part == '':
                pass
            elif LOG_VAR_SIGN not in part:
                pattern.append(part)
                # skip value since no var found in part
            elif MAPPING.has_key(part):
                name, pat = MAPPING.get(part)
                pattern.append(pat)
                keys.append(name)
            else:
                raise Exception('Pattern for key %s not known in mapping '
                                'table.' % part)
        pattern = RE_START + RE_SPACER.join(pattern) + RE_STOP
        regex = re.compile(pattern)
        # print 'pattern =', pattern
        
        self.format = format
        self.pattern = pattern
        self.keys = keys
        self.regex = regex
        self._field_parsers = {}
        
        self.register_field_parsers()
    
    def register_field_parsers(self):
        '''
        Registers field parsers for further abstraction
        
        @return: None
        '''
        self.register_field_parser('hosts', self._parse_field_hosts)
        self.register_field_parser('datetime',
                                   self._parse_field_datetime_to_tuple)
        # self.register_field_parser('datetime',
        #                            self._parse_field_datetime_to_struct)
        # self.register_field_parser('datetime',
        #                            self._parse_field_datetime_to_timestamp)
        self.register_field_parser('query', self._parse_field_query)
    
    def register_field_parser(self, name, method):
        '''
        Puts given parser into internal table
        
        @type    name: string
        @param   name: key for internal table
        @type  method: bound method or function object
        @param method: some callable object with args data, key, val
        
        @return: None
        '''
        self._field_parsers[name] = method
    
    def _parse_field_hosts(self, data, key, val):
        '''
        Reformats given string and adds it as a host list to data
        
        @type  data: list
        @param data: list with tuples to append to
        @type   key: string
        @param  key: name of field
        @type   val: string
        @param  val: value of field
        
        @return: None
        '''
        val = [v.strip() for v in val.split(',')]
        data.append((key, val))
    
    def _parse_field_datetime_to_tuple(self, data, key, val):
        '''
        Reformats given datetime string and adds it as a tuple to data
        
        @type  data: list
        @param data: list with tuples to append to
        @type   key: string
        @param  key: name of field
        @type   val: string
        @param  val: value of field
        
        @return: None
        '''
        elems = [
            val[7:11],
            FIELD_PARSER_DATETIME_MONTHS[val[3:6]],
            val[0:2],
            val[12:14],
            val[15:17],
            val[18:20],
            ]
        dt = ''.join(elems)
        tz = val[21:]
        data.append((key, (dt, tz)))
    
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
        format = '%d/%b/%Y:%H:%M:%S'
        struct = time.strptime(val[:-6], format)
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
        format = '%d/%b/%Y:%H:%M:%S'
        struct = time.strptime(val[:-6], format)
        timestamp = int(time.mktime(struct))
        data.append(('timestamp', timestamp))
    
    def _parse_field_query(self, data, key, val):
        '''
        Reformats given query string and adds it as a tuple to data
        
        @type  data: list
        @param data: list with tuples to append to
        @type   key: string
        @param  key: name of field
        @type   val: string
        @param  val: value of field
        
        @return: None
        '''
        try:
            parts = val.split(' ')
            req_type = parts[0]
            req_protocol, req_protocol_version = parts[-1].split('/')
            data.append(('type', req_type))
            data.append(('protocol', req_protocol))
            data.append(('protocol_version', req_protocol_version))
            val = val[len(parts[0]):-len(parts[-1])].strip()
            data.append((key, val))
        except:
            data.append(('type', 'unknown'))
            data.append(('protocol', 'unknown'))
            data.append(('protocol_version', 'unknown'))
            data.append((key, val))
    
    def _parse_field_default(self, data, key, val):
        '''
        Reformats nothing (default) and adds it as is to data
        
        @type  data: list
        @param data: list with tuples to append to
        @type   key: string
        @param  key: name of field
        @type   val: string
        @param  val: value of field
        
        @return: None
        '''
        data.append((key, val))
    
    def parse(self, line):
        '''
        Searches in line, parses matches and puts it into a result tuple
        
        @type  line: text
        @param line: row to search within
        
        @return: resulting tuple
        '''
        parsers = self._field_parsers
        match = self.regex.match(line)
        # print match.groupdict()
        data = []
        for n, key in enumerate(self.keys):
            val = match.group(n + 1)
            parser = parsers.get(key, self._parse_field_default)
            parser(data, key, val)
        return tuple(data)
