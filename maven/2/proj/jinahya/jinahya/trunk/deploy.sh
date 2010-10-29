#!/bin/sh
svn up
mvn -Pjsr14 clean deploy;mvn clean deploy
svn ci -m updated
