#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=database-metadata
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
