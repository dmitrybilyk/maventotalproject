__author__ = 'bid'

myList = [3,5,67, "dimon"]
myList2 = [3,5,67, "dimon"]

myList.extend(myList2)

myList.append("appendedDima")
myList.append(myList2)

myList.insert(2, "ruslan")


print myList

print myList.pop(3)

print myList

myList3 = myList + myList2

print myList3


print myList2
print myList2[:-2] * 4

print range.__doc__
