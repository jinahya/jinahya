#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=hex-encoder
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
