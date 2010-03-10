#!/bin/sh
mvn clean install deploy site site:deploy
mvn -P jdk16 clean install deploy
