#!/bin/sh
#current=`pwd`
#echo $current
#svn up $current
./fix_newlines.sh
./svn_apply_autoprops.py
svn ci -m reset
