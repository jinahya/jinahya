#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=nica-servlet
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
