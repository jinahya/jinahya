#!/bin/sh
mvn -P jdk14 install deploy
if [ "$?" -ne "0" ]; then exit "$?"; fi
mvn -P jdk13 install deploy
if [ "$?" -ne "0" ]; then exit "$?"; fi
mvn -P jdk12 install deploy
if [ "$?" -ne "0" ]; then exit "$?"; fi
mvn clean install deploy site site:deploy
if [ "$?" -ne "0" ]; then exit "$?"; fi

