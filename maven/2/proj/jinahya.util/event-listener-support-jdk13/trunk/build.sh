#!/bin/sh
mvn clean install deploy site site:deploy
maven -P jdk12 install deploy
maven -P jdk13 install deploy
maven -P jdk14 install deploy
