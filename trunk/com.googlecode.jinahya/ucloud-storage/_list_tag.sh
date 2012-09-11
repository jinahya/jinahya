#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=ucloud-storage
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
