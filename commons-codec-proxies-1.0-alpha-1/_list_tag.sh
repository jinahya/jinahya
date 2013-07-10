#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=commons-codec-proxies
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
