#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=servlet-helper
tag=$1
svn diff --old=http://jinahya.googlecode.com/svn/tags/$groupId/$artifactId-$tag --new http://jinahya.googlecode.com/svn/trunk/$groupId/$artifactId
