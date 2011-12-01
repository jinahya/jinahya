#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=jinahya-ee
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
