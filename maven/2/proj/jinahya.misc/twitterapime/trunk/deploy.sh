#!/bin/sh
svn up
mvn -q clean install deploy site site:deploy
