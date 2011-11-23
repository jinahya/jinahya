#!/bin/sh
#current=`pwd`
#echo $current
#svn up $current
sh ./fix_newlines.sh
python ./svn_apply_autoprops.py
svn ci -m reset
