#!/bin/sh
svn up
mvn dependency:purge-local-repository clean test
if [ "$?" == "0" ]; then
    mvn clean
    svn status | grep '?' | sed 's/^.* /svn add /' | bash
    svn ci -m updated
else
    echo "--- NG ---"
    exit $?
fi
#mvn -Pjsr14 clean deploy
mvn clean javadoc:jar source:jar deploy
exit 0
