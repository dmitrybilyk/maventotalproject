__author__ = 'bid'

params = {"server":"mpilgrim", "database":"master", "uid":"sa", "pwd":"secret"}

nums = [1, 3, 5, 7]

list = [element * 3 for element in nums]
list2 = ["%s=%s" % (k,v) for k,v in params.items()]


print list2