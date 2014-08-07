'''
Parser for readme files built according to symmetrics readme style standards

@package: symmetrics_saasrepo_docs.docs
@copyright: 2009 symmetrics gmbh. All rights reserved.
@author: Oktay Acikalin <ok@symmetrics.de>
'''

import re


class Parser(object):
    '''Readme parser'''

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


if __name__ == '__main__':
    parser = Parser()
    struct = parser.parse(open('README-DE.txt', 'r').read())

    from pprint import pprint
    pprint(struct)
