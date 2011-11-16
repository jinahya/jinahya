#!/bin/sh
mvn install:install-file -DgroupId=com.altibase -DartifactId=jdbc-driver -Dpackaging=jar -Dversion=5.1.5.78 -Dfile=Altibase.jar -DgeneratePom=true
