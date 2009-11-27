#!/bin/sh
svn ci -m ^^*
mvn clean install deploy site site:deploy
