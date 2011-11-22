#!/bin/sh
mvn -Dsign=true clean source:jar javadoc:jar deploy
mvn -Dsign=true -Pjsr14 clean deploy
