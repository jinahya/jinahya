#!/bin/sh

url=$1
if [ -z "$url" ]; then
    url=http://localhost:58080
fi
echo url: $url

echo --------------------------------------------- reading /imageSuffixes as XML
curl --connect-timeout 3 -s -H "Accept: application/xml" $url/imageSuffixes | xmllint --format - > target/imageSuffixes.xml
cat target/imageSuffixes.xml
echo ------------------------------------------ updating /imageSuffixes with XML
curl --connect-timeout 3 -X PUT -H "Content-Type: application/xml" $url/imageSuffixes --data "@target/imageSuffixes.xml"

echo ----------------------------------------- reading /imageSuffixes/png as XML
curl --connect-timeout 3 -s -H "Accept: application/xml" $url/imageSuffixes/png | xmllint --format - > target/imageSuffix.xml
cat target/imageSuffix.xml
echo -------------------------------------- updating /imageSuffixes/png with XML
curl --connect-timeout 3 -X PUT -H "Content-Type: application/xml" $url/imageSuffixes/png --data "@target/imageSuffix.xml"


echo -------------------------------------------- reading /imageSuffixes as JSON
curl --connect-timeout 3 -s -H "Accept: application/json" $url/imageSuffixes | python -mjson.tool > target/imageSuffixes.json
cat target/imageSuffixes.json
echo ----------------------------------------- updating /imageSuffixes with JSON
curl --connect-timeout 3 -X PUT -H "Content-Type: application/json" $url/imageSuffixes --data "@target/imageSuffixes.json"

echo ---------------------------------------- reading /imageSuffixes/png as JSON
curl --connect-timeout 3 -s -H "Accept: application/json" $url/imageSuffixes/png | python -mjson.tool > target/imageSuffix.json
cat target/imageSuffix.json
echo ------------------------------------- updating /imageSuffixes/png with JSON
curl --connect-timeout 3 -X PUT -H "Content-Type: application/json" $url/imageSuffixes/png --data "@target/imageSuffix.json"

