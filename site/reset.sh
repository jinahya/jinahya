#!/bin/bash
svn up
./fix_newlines.sh
./svn_apply_autoprops.py
svn ci -m reset

