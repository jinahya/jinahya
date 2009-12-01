#!/bin/sh
mvn -Dmaven.test.skip=true clean package deploy site site:deploy
