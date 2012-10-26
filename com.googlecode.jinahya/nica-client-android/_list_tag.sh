#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=nica-android
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
