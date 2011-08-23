#!/bin/sh
if [ $# -eq 0 ]; then
    echo no golas
    exit 1
fi
wd=`pwd`
pd=
for i in `find . -name pom.xml -type f`
do
    pd=${i%/*}
    echo "[[[[[ " $pd " ]]]]]"
    cd -P $pd
    mvn $*
    cd $wd
done
exit 0
