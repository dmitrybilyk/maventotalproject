#!/usr/bin/env python

import fnmatch
import os

for file in os.listdir('.'):
	file = os.path.join('.', file) 
	print file + " "
	print fnmatch.fnmatch(file, ".")