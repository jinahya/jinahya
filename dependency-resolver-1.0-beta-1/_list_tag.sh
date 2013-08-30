#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=dependency-resolver
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
