#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=inca-java
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
