#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=jdbc-realm-persistence
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
