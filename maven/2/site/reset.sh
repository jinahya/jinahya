#!/bin/sh
svn revert --depth infinity *
./fix_newlines.sh
./svn_apply_autoprops.py
svn ci -m reset

