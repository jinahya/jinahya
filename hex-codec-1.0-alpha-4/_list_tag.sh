#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=hex-codec
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
