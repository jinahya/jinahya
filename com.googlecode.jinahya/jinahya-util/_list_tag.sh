#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=jinahya-util
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
