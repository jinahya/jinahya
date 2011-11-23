#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=jdbc-accessible
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
