#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=epost-openapi-client
path=../../../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
