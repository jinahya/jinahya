#!/bin/sh
wd=`pwd`
for i in `find . -name pom.xml -type f`
do
    cd -P ${i%/*}
    mvn site-deploy
    cd $wd
done
exit 0
