#!/bin/sh
#
# Wrapper for use in the shell (also writes logged line into stdout).
# Copy this into your path and set the SYMMETRICS_QUALITYGATE_PROJECT_ROOT
# environment variable accordingly.
# 
# @package: symmetrics_saasrepo_qualitygate/stuff
# @copyright: 2010 symmetrics gmbh. All rights reserved.
# @author: Oktay Acikalin <ok@symmetrics.de>
#

/home/bid/Dev/projects/maventotalproject/pythontest/src/quality_gate_cgi/build/main.py -v $@ $(pwd)

