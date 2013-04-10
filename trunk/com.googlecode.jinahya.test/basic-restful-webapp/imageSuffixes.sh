#!/bin/sh

curl -s -H "Accept: application/xml" http://localhost:58080/imageSuffixes | xmllint --format - > imageSuffixes.xml
curl -X PUT -H "Content-Type: application/xml" http://localhost:58080/imageSuffixes --data "@imageSuffixes.xml"

curl -s -H "Accept: application/xml" http://localhost:58080/imageSuffixes/png | xmllint --format - > imageSuffix.xml
curl -X PUT -H "Content-Type: application/xml" http://localhost:58080/imageSuffixes/png --data "@imageSuffix.xml"

curl -s -H "Accept: application/json" http://localhost:58080/imageSuffixes | python -mjson.tool > imageSuffixes.json
curl -X PUT -H "Content-Type: application/json" http://localhost:58080/imageSuffixes --data "@imageSuffixes.json"

curl -s -H "Accept: application/json" http://localhost:58080/imageSuffixes/png | python -mjson.tool > imageSuffix.json
curl -X PUT -H "Content-Type: application/json" http://localhost:58080/imageSuffixes/png --data "@imageSuffix.json"

rm imageSuffixes.xml
rm imageSuffixes.json
rm imageSuffix.xml
rm imageSuffix.json

