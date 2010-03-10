#!/bin/sh
mvn -P jdk14 clean install deploy
if [ "$?" -ne "0" ]; then exit "$?"; fi
mvn -P jdk13 clean install deploy
if [ "$?" -ne "0" ]; then exit "$?"; fi
mvn -P jdk12 clean install deploy
if [ "$?" -ne "0" ]; then exit "$?"; fi
mvn clean install deploy site site:deploy
if [ "$?" -ne "0" ]; then exit "$?"; fi
