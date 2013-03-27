#!/bin/sh
echo --------------------------------------------------
echo POST /items
echo Content-Type: application/xml
echo
cat src/test/resources/item.xml | xmllint --format -
echo
curl -i -X POST -H "Content-type: application/xml" -T src/test/resources/item.xml http://localhost:58080/basic-restful-webapp/items
echo --------------------------------------------------
echo GET /items/0
echo Accept: application/json
echo
curl -H "Accept: application/json" http://localhost:58080/basic-restful-webapp/items/0 | python -mjson.tool
echo --------------------------------------------------
echo POST /items
echo Content-Type: application/json
echo
cat src/test/resources/item.json | python -mjson.tool
echo
curl -i -X POST -H "Content-type: application/json" -T src/test/resources/item.json http://localhost:58080/basic-restful-webapp/items
echo --------------------------------------------------
echo GET /items/1
echo Accept: application/xml
echo
curl -H "Accept: application/xml" http://localhost:58080/basic-restful-webapp/items/1 | xmllint --format -
echo --------------------------------------------------
echo PUT /items/0
echo Content-Type: application/xml
echo
cat src/test/resources/item.xml.update | xmllint --format -
curl -i -X PUT -H "Content-type: application/xml" -T src/test/resources/item.xml.update http://localhost:58080/basic-restful-webapp/items/0
echo --------------------------------------------------
echo GET /items/0
echo Accept: application/json
echo
curl -H "Accept: application/json" http://localhost:58080/basic-restful-webapp/items/0 | python -mjson.tool
echo --------------------------------------------------
echo PUT /items/1
echo Content-Type: application/json
echo
cat src/test/resources/item.json.update | python -mjson.tool
curl -i -X PUT -H "Content-type: application/json" -T src/test/resources/item.json.update http://localhost:58080/basic-restful-webapp/items/1
echo --------------------------------------------------
echo GET /items/1
echo Accept: application/xml
echo
curl -H "Accept: application/xml" http://localhost:58080/basic-restful-webapp/items/1 | xmllint --format -
echo --------------------------------------------------
echo DELETE items/0
curl -i -X DELETE http://localhost:58080/basic-restful-webapp/items/0
echo --------------------------------------------------
echo DELETE items/1
curl -i -X DELETE http://localhost:58080/basic-restful-webapp/items/1

