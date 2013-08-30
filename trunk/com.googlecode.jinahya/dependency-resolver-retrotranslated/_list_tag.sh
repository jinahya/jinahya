#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=dependency-resolver-retrotranslated
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
