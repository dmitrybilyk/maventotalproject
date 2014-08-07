__author__ = 'bid'


import os

for k, v in os.environ.items():
     print "%s=%s" % (k, v)

list = [1, 4, 5, "4f"]

for elem in list:
    if type(elem)!=int:
        print elem
