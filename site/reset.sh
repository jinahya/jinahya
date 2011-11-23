#!/bin/sh
current=`pwd`
echo $current
svn up $current
$current/fix_newlines.sh
$current/svn_apply_autoprops.py
svn ci -m reset $current
