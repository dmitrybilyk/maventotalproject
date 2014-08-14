__author__ = 'bid'
# -*- coding: utf-8 -*-

import re

s = "100 ROAD BROAD ROAD APT. 3"
print re.sub(r'\bROAD\b', 'RD.', s)


pattern = '^M?M?M?M?(CM|CD|D?C?C?C?)(XC|XL|L?X?X?X?)$'

re.search(pattern, 'MCMXL')