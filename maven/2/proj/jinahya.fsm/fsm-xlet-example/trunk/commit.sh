#!/bin/sh
svn ci -m ^^*
mvn -Dmaven.test.skip=true clean install deploy site site:deploy
