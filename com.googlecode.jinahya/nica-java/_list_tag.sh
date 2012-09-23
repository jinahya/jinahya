#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=nica-java
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
