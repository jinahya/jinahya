#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=rfc-4648
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
