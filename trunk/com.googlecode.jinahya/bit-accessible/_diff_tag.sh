#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=bit-accessible
svn diff --old=http://jinahya.googlecode.com/svn/tags/$groupId/$artifactId-$1 --new http://jinahya.googlecode.com/svn/trunk/$groupId/$artifactId
