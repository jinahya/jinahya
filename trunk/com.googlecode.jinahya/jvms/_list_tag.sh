#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=jvms
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
