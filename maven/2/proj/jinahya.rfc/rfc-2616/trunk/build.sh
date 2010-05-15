#!/bin/sh
mvn clean install deploy
mvn -Pjsr14 clean install deploy
mvn -Pjdk14 clean install deploy
mvn -Pjdk13 clean install deploy