#!/bin/sh
if [ $# -eq 0 ]; then
    echo no golas
    exit 1
fi
wd=`pwd`
for i in `find . -name pom.xml -type f`
do
    cd -P ${i%/*}
    mvn $*
    cd $wd
done
exit 0
