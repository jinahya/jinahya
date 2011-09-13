#!/bin/bash
#
 svn up
#
sh ./fix_newlines.sh
#
sh ./svn_apply_autoprops.py
#
svn ci -m reset

