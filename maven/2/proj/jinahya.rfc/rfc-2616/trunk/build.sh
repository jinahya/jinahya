#!/bin/sh
mvn clean deploy
mvn -Pjsr14 clean deploy
mvn -Pjdk14 clean deploy
mvn -Pjdk13 clean deploy