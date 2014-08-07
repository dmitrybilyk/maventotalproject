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

${SYMMETRICS_QUALITYGATE_PROJECT_ROOT}/main.py -v $@ $(pwd)

