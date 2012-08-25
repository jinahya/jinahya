#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=ucloud-storage-client
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
