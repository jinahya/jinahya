#!/bin/sh
if [ $# -eq 0 ]; then
    echo no golas
    exit 1
fi
wd=`pwd`
for i in `find . -name pom.xml -type f -printf '%h\n'`
do
    echo "[[[[[ " $i " ]]]]]"
    cd -P $i
    mvn $*
    cd $wd
done
exit 0
