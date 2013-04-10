#!/bin/sh

curl -s -H "Accept: application/xml" http://localhost:58080/imageSuffixes | xmllint --format - > imageSuffixes.xml
cat imageSuffixes.xml
read -p "Press any key to continue... "
curl -X PUT -H "Content-Type: application/xml" http://localhost:58080/imageSuffixes --data "@imageSuffixes.xml"
read -p "Press any key to continue... "

curl -s -H "Accept: application/xml" http://localhost:58080/imageSuffixes/png | xmllint --format - > imageSuffix.xml
cat imageSuffix.xml
read -p "Press any key to continue... "
curl -X PUT -H "Content-Type: application/xml" http://localhost:58080/imageSuffixes/png --data "@imageSuffix.xml"
read -p "Press any key to continue... "

curl -s -H "Accept: application/json" http://localhost:58080/imageSuffixes | python -mjson.tool > imageSuffixes.json
cat imageSuffixes.json
read -p "Press any key to continue... "
curl -X PUT -H "Content-Type: application/json" http://localhost:58080/imageSuffixes --data "@imageSuffixes.json"
read -p "Press any key to continue... "

curl -s -H "Accept: application/json" http://localhost:58080/imageSuffixes/png | python -mjson.tool > imageSuffix.json
cat imageSuffix.json
read -p "Press any key to continue... "
curl -X PUT -H "Content-Type: application/json" http://localhost:58080/imageSuffixes/png --data "@imageSuffix.json"


#rm imageSuffixes.xml
#rm imageSuffixes.json
#rm imageSuffix.xml
#rm imageSuffix.json

