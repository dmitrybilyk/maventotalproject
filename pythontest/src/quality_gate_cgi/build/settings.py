import os
import base64


# which tests to execute (in order)
TESTS = [
    #'meta_info_py',
    #'meta_pylint',
    #'meta_pep8',
    #'meta_utf8',
    #'meta_bad_content',
    #'meta_import',
    #'meta_fields',
    #'meta_dependencies',

    #'package_build',
    #'package_readmes',
    # 'package_mandatory_readmes',
    #'package_readmes_parseable',
    #'package_utf8',
    #'package_bad_content',
    #'package_pylint',
    #'package_pep8',
    #'package_phplint',
    #'package_phpcodesniffer',
    #'package_xml_valid',
    'package_java_checkstyle',
    # 'package_jslint',
    #'package_jshint',
    #'package_csslint',
    # 'package_djangolint',
    # 'package_phpmd',
]

PROJECT_ROOT = os.path.dirname(__file__)

# Enable this for faster scanning. QG will skip files with known MD5s.
FILE_STATE_CACHE_DB = None
# FILE_STATE_CACHE_DB = os.path.join(PROJECT_ROOT, 'file_state_cache-%(reponame)s-%(branch)s.sqlite3')

LOCAL_USER = os.getenv('USER')
LOCAL_NODE = '[tip]'
LOCAL_BRANCH = '[local]'
HG_DEFAULT_BRANCH = 'default'
GIT_DEFAULT_BRANCH = 'master'
BUILD_PATH = 'build'
DOC_PATH = 'doc'

# env var for username (usally who has authenticated)
AUTH_USER_ENV_VAR = 'SSH_USER'
# if the env var above is set and it's present in this list, the quality gate
# will always set a status code of 0 (normally an error would result in 1)
UNBLOCKABLE_USERS = []

# log settings
LOG_SERVICE_NAME = 'saasrepo_qualitygate'
LOG_FILE = os.path.join(PROJECT_ROOT, 'history.log')

# how to extract a version out of a source dir
HG_EXTRACT_CMD = 'hg -q archive -r %(revision)s %(temp_dir)s'
GIT_EXTRACT_CMD = 'unset GIT_DIR && cd .. && git clone -lqsn . %(temp_dir)s ' \
                  '&& cd %(temp_dir)s && git checkout %(revision)s'

# if not empty only these branches are being extracted and checked.
HG_ONLY_BRANCHES = None
GIT_ONLY_BRANCHES = None

# find settings
QG_IGNORE_FILE = '.qgignore'
FIND_PY_FILES_REGEX = r'.*.py$'
FIND_PHP_FILES_REGEX = r'.*.php$'
FIND_XML_FILES_REGEX = r'.*.xml$'
FIND_JAVA_FILES_REGEX = r'.*.java$'
FIND_JS_FILES_REGEX = r'.*.js$'
FIND_CSS_FILES_REGEX = r'.*.css$'
CODING_STANDARDS_IGNORE_PATH = '.codingStandardsIgnorePath'
CODING_STANDARDS_IGNORE_FILE = '@codingStandardsIgnoreFile'

# content filter settings
CONTENT_FILTER_DIR = os.path.join(PROJECT_ROOT, 'content_filters')

# pylint settings
_PYLINT_DISABLED_MSGS = (
    'E1002',  # Use super on an old style class
    'E1101',  # Instance of '%s' has no '%s' member
)
PYLINT_CMD = 'pylint'
PYLINT_ARGS = '-E --include-ids=y --output-format=parseable --persistent=n ' \
              '--disable=%s' % ','.join(_PYLINT_DISABLED_MSGS)

# pep8 settings
PEP8_CMD = os.path.join(PROJECT_ROOT, 'external', 'pep8', 'pep8.py')
PEP8_ARGS = '--ignore=W --repeat'

# php lint settings
PHPLINT_CMD = 'php'
PHPLINT_ARGS = '-l %r'

# php code sniffer settings
PHPCS_CMD = 'phpcs'
PHPCS_ARGS = '-- --standard=Symmetrics -n'

# php mess detector settings
PHPMD_CMD = 'phpmd'
PHPMD_ARGS = '%s text symmetrics'

# tidy settings
TIDY_XML_CMD = 'tidy'
TIDY_XML_ARGS = '-xml -utf8 -e -q'

# java checkstyle settings
JAVA_CHECKSTYLE_CMD = 'checkstyle'
JAVA_CHECKSTYLE_ARGS = '-c %s' % os.path.join(PROJECT_ROOT,
                                               'external',
                                               'checkstyle',
                                               'hybris_checkstyle_checks.xml')
JAVA_CHECKSTYLE_BATCH_SIZE = 200

# django lint settings
DJANGOLINT_FILES = ('manage.py', 'models.py', 'urls.py')
DJANGOLINT_CMD = os.path.join(PROJECT_ROOT, 'external', 'django-lint',
                              'django-lint') + ' -e %s'

# jslint code sniffer settings
JSLINT_CMD = 'jslint'
JSLINT_ARGS = ' '

# jshint code sniffer settings
JSHINT_CMD = 'jshint'
JSHINT_ARGS = ' --config %s' % os.path.join(PROJECT_ROOT,
                                            'external',
                                            'jshint',
                                            'jshintrc')

# css code linter settings
css_rules_path = os.path.join(PROJECT_ROOT, 'external', 'csslint', 'csslintrc')
with open(css_rules_path, 'r') as config: css_rules = config.readlines()[0]
CSSLINT_CMD = 'csslint'
CSSLINT_ARGS = ' --quiet --format=compact %s' % css_rules

# meta settings
META_REQUIRED_FIELDS = ['NAME', 'TAGS', 'LICENSE', 'HOMEPAGE', 'TEAM_LEADER',
                        'MAINTAINER', 'AUTHORS', 'INFO', 'SUMMARY',
                        'REQUIRES', 'COMPATIBLE_WITH']
META_OPTIONAL_FIELDS = ['INSTALL_PATH', 'NOTES', 'EXCLUDES', 'VIRTUAL',
                        'DEPENDS_ON_FILES', 'PEAR_KEY']

# settings for accessing the saas repo
_AUTH_USERNAME = 'xxx'
_AUTH_PASSWORD = 'yyy'
_AUTH_KEY = base64.standard_b64encode(':'.join((_AUTH_USERNAME,
                                                _AUTH_PASSWORD)))
SR_HEADERS = dict(
    Accept='application/json',
    Authorization='Basic %s' % _AUTH_KEY)
SR_BASE_URL = 'https://saasrepo.symmetrics.de/api/'
SR_LIST_URL = SR_BASE_URL + 'modules/'
SR_OVERVIEW_URL = SR_BASE_URL + 'module/%s/'
SR_INFO_URL = SR_BASE_URL + 'module/info/%s/'
SR_DOWNLOAD_URL = SR_BASE_URL + 'module/export/%s/'

# readme settings
README_GLOB_PATTERN = 'README-*.txt'
README_REGEX_PATTERN = r'.+/doc/README-[A-Z]{2,3}.txt$'
README_MANDATORY_FILES = ['README-EN.txt']
README_LEVELS = ['chapter', 'section', 'subsection']  # define depth of schema
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

REPOSITORY_OVERRIDES = {
    # 'lv_shop': {
    #     'PHPLINT_CMD': 'php-5.3.1',
    #     'PHPCS_CMD': 'phpcs531',
    # },
}

# try to import host specific settings
try:
    from local_settings import *
except ImportError:
    pass
