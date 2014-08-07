__author__ = 'bid'
# -*- coding: utf-8 -*-

import re

s = "100 ROAD BROAD ROAD APT. 3"
print re.sub(r'\bROAD\b', 'RD.', s)