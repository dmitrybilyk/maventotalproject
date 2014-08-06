__author__ = 'bid'

import my

params = {"server":"mpilgrim", "database":"master", "uid":"sa", "pwd":"secret"}

print my.buildConnectionString(params)

print my.buildConnectionString.__doc__
