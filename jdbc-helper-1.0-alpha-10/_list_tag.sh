#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=jdbc-helper
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
