#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=xmlpull-accessible
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
