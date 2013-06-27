#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=bit-io
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
