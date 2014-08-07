'''
Wrapper and API for the saas repo

@package: symmetrics_saasrepo_docs.docs
@copyright: 2009 symmetrics gmbh. All rights reserved.
@author: Oktay Acikalin <ok@symmetrics.de>
'''

import os
import time
import tempfile
import json
import urllib
import urllib2
import logging


logger = logging.getLogger()


class SaaSRepo(object):
    '''Wrapper for saas repo'''

    def __init__(self, headers, list_url, overview_url, info_url,
                 download_url):
        '''
        @param headers: dict with auth info
        @param list_url: string with url
        @param overview_url: string with url
        @param info_url: string with url
        @param download_url: string with url
        '''
        self.headers = headers
        self.list_url = list_url
        self.overview_url = overview_url
        self.info_url = info_url
        self.download_url = download_url
        self.extract_cmd = 'tar -xz -C %(temp_dir)s -f %(temp_file)s'

    def _get_url_data(self, url, data=None):
        '''
        Tries to download content from an url
        @param url: string with url
        @param data: dict for post request (optional)
        @return: string
        '''
        logger.debug('getting url data from: %s' % url)
        if data is not None:
            data = urllib.urlencode(data)
        request = urllib2.Request(url=url, headers=self.headers)
        max_tries = 3
        cur_try = 0
        done = False
        while not done:
            try:
                data = urllib2.urlopen(request, data).read()
                done = True
                if 'error' in data[:10].lower():
                    raise Exception('Could not download data.')
            except:
                cur_try += 1
                if cur_try < max_tries:
                    time.sleep(1)
                    print('Retrying download...')
                else:
                    raise
        return data

    def list(self):
        '''
        Downloads module list
        @return: list with dicts
        '''
        url = self.list_url
        response = self._get_url_data(url)
        response = json.loads(response)
        return response

    def overview(self, name):
        '''
        Downloads module meta data
        @param name: string
        @return: dict
        '''
        url = self.overview_url % name
        response = self._get_url_data(url)
        response = json.loads(response)
        return response

    def info(self, name, version):
        '''
        Downloads extended module data
        @param name: string
        @param version: string
        @return: dict
        '''
        url = self.info_url % ('%s-%s' % (name, version))
        response = self._get_url_data(url)
        response = json.loads(response)
        return response

    def download(self, name, version):
        '''
        Downloads a package and returns the file path
        @param name: string
        @param version: string
        @return: string with file path
        '''
        # logger.debug('download_data(%r, %r)' % (name, version))
        url = self.download_url % ('%s-%s' % (name, version))
        # logger.info('query module data from: %s' % url)
        data = self._get_url_data(url)
        fdsc, temp_file = tempfile.mkstemp()
        os.write(fdsc, data)
        return temp_file

    def extract(self, name, version):
        '''
        Downloads a package, extracts it and returns the folder path
        @param name: string
        @param version: string
        @return: string with folder path
        '''
        # logger.debug('extract(%r, %r)' % (name, version))
        temp_file = self.download(name, version)
        temp_dir = tempfile.mkdtemp()
        cmd = self.extract_cmd % dict(temp_file=temp_file, temp_dir=temp_dir)
        # logger.info('executing: %s' % cmd)
        if os.system(cmd):  # if something unexpected happened
            os.remove(temp_file)
            os.system('rm -rf %r' % temp_dir)
            raise Exception('Could not extract package without errors.')
        os.remove(temp_file)
        return temp_dir
