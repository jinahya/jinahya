#!/bin/sh
svn up
mvn clean compile
if [ "$?" == "0" ]; then
    svn ci -m updated
else
    echo "--- NG ---"
    exit $?
fi
svn ci -m updated
mvn clean deploy site-deploy
exit 0
