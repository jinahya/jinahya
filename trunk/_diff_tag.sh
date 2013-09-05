#!/bin/sh
groupId=$1
artifactId=$2
version=$3
svn diff --old=http://jinahya.googlecode.com/svn/tags/$groupId/$artifactId-$version --new http://jinahya.googlecode.com/svn/trunk/$groupId/$artifactId
