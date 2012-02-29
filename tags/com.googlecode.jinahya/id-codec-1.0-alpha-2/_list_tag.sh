#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=id-codec
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
