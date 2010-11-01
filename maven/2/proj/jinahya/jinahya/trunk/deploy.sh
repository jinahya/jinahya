#!/bin/sh
svn up
mvn clean test
if [ "$?" == "0" ]; then
    svn ci -m updated
else
    echo "--- NG ---"
    exit $?
fi
mvn -Pjsr14 clean deploy
mvn clean jar:jar javadoc:jar source:jar deploy
exit 0
