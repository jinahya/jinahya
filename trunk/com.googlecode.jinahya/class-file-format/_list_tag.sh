#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=class-file-format
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
