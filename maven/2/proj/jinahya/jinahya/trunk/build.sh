#!/bin/sh
mvn dependency:purge-local-repository -Pjsr14 clean install deploy;mvn clean install deploy
