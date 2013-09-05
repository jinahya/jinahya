#!/bin/sh
groupId=$1
artifactId=$2
path=../tags/$groupId/
svn up $path
ls -l $path | grep $artifactId
