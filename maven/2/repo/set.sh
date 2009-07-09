#!/bin/sh
./fix_newlines.sh
./svn_apply_autoprops.py
svn i -m ^^*

