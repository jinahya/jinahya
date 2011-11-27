#!/bin/sh
wd=`pwd`
for i in `find . -name pom.xml -type f`
do
    cd -P ${i%/*}
    mvn clean site-deploy clean
    cd $wd
done
exit 0
