#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=jinahya-accessible
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
