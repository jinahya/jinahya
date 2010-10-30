#!/bin/sh
svn up
mvn -Pjsr14 clean deploy;mvn clean deploy site-deploy
svn ci -m updated
