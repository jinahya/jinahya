#!/bin/sh
mvn -e -q -Dmaven.test.skip=true clean deploy site site:deploy
