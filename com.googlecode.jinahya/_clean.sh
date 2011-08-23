#!/bin/sh
wd=`pwd`
for i in `find . -name pom.xml -type f`
do
    if [ ! -d ${i%/*}/target ]; then
        continue
    fi
    cd -P ${i%/*}
    mvn clean
    cd $wd
done
exit 0
