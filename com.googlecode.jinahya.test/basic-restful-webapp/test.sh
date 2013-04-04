#!/bin/sh
cat src/test/resources/item.xml
curl -i -X POST http://localhost:58080/items -H "Content-type: application/xml" --data "@src/test/resources/item.xml"
curl -s http://localhost:58080/items/0 -H "Accept: application/xml" | xmllint --format -
curl -s http://localhost:58080/items/0 -H "Accept: application/json" | python -m json.tool
cat src/test/resources/item.json
curl -i -X PUT http://localhost:58080/items/0 -H "Content-type: application/json" --data "@src/test/resources/item.json"
curl -s http://localhost:58080/items/0 -H "Accept: application/xml" | xmllint --format -
curl -s http://localhost:58080/items/0 -H "Accept: application/json" | python -m json.tool
curl -s http://localhost:58080/items -H "Accept: application/xml" | xmllint --format -
curl -s http://localhost:58080/items -H "Accept: application/json" | python -m json.tool
curl -i -X DELETE http://localhost:58080/items/0
curl -s http://localhost:58080/items -H "Accept: application/xml" | xmllint --format -
curl -s http://localhost:58080/items -H "Accept: application/json" | python -m json.tool
#curl -s http://localhost:58080/items.xsd -H "Accept: application/xml" | xmllint --format -
#curl -s http://localhost:58080/items.jsd -H "Accept: application/json" | python -m json.tool
curl -s http://localhost:58080/items.xsd | xmllint --format -
curl -s http://localhost:58080/items.jsd | python -m json.tool

