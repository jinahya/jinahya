#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=android-signature-2.3.3
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
