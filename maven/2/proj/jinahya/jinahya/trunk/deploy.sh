#!/bin/sh
svn up
mvn clean test
if [ "$?" == "0" ]; then
    svn ci -m updated
else
    echo "--- NG ---"
    exit $?
fi
svn ci -m updated
mvn -Pjsr14 clean deploy
mvn clean javadoc:jar source:jar deploy site-deploy
exit 0
