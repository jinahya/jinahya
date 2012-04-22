#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=rfc-3986
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
