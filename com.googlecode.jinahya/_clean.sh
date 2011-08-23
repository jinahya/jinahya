#!/bin/sh
wd=`pwd`
for i in `find . -name pom.xml -type f -printf '%h\n'`
do
    if [ ! -d $i/target ]; then
        continue
    fi
    echo "[[[[[ " $i " ]]]]]"
    cd -P $i
    mvn clean
    cd $wd
done
exit 0
