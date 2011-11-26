#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=servlet-helper
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
