#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=nica-java-client
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
