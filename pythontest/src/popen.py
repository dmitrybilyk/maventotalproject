__author__ = 'bid'

import subprocess

p = subprocess.Popen(["echo", "hello world"], stdout=subprocess.PIPE)

print p.communicate()
