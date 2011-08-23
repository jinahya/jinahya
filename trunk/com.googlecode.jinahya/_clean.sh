#!/bin/sh
wd=`pwd`
#for i in `find . -name pom.xml -type f -printf '%h\n'`
pd=
for i in `find . -name pom.xml -type f`
do
    pd=${i%/*}
    if [ ! -d $pd/target ]; then
        continue
    fi
    echo "[[[[[ " $pd " ]]]]]"
    cd -P $pd
    mvn clean
    cd $wd
done
exit 0
