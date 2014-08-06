__author__ = 'bid'

params = {"server":"mpilgrim", "database":"master", "uid":"sa", "pwd":"secret"}

list = ";".join(["%s=%s" % (k, v) for k, v in params.items()])
print list

def printSplited():
    if __name__ == "__main__":
        splited = list.split(";")
        print splited

printSplited()