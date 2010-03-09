#!/bin/sh
mvn clean test
if [ "$?" -ne "0" ]; then exit "$?"; fi
mvn -P jdk16 install deploy
mvn clean install deploy site site:deploy

