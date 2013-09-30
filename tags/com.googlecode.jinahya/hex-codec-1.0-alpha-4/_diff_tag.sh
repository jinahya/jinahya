#!/bin/sh
groupId=com.googlecode.jinahya
artifactId=hex-codec
svn diff --old=http://jinahya.googlecode.com/svn/tags/$groupId/$artifactId-$1 --new http://jinahya.googlecode.com/svn/trunk/$groupId/$artifactId
