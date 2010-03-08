#!/bin/sh
mvn clean install deploy site site:deploy
mvn -P jdk16 install deploy
