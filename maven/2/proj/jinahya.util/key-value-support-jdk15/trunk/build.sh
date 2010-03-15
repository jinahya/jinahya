#!/bin/sh
mvn -P jdk16 clean install deploy
if [ "$?" -ne "0" ]; then exit "$?"; fi
mvn clean install deploy site site:deploy
if [ "$?" -ne "0" ]; then exit "$?"; fi

