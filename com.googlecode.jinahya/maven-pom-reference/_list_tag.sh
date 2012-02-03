#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=maven-pom-reference
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
