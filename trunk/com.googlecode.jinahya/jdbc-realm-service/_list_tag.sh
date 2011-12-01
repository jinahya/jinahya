#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=jdbc-realm-service
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
