import base64


LOG_FILE = '../history.log'
LOG_POS_FILE = 'history.pos'

_AUTH_USERNAME = 'xxx'
_AUTH_PASSWORD = 'yyy'
_AUTH_KEY = base64.standard_b64encode(':'.join((_AUTH_USERNAME,
                                                _AUTH_PASSWORD)))

HEADERS = dict(
    Accept='application/json',
    Authorization='Basic %s' % _AUTH_KEY)

REST_POST_URL = 'https://mcc.symmetrics.de/api/qgate/post_stats'


# try to import host specific settings
try:
    from local_config import *
except ImportError:
    pass
