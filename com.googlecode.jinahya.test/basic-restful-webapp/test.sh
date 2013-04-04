#!/bin/sh
item_xml_size=$(stat -c%s "src/test/resources/item.xml")
item_json_size=$(stat -c%s "src/test/resources/item.json")

echo ------------------------------------ creating on /items with an xml content
curl -i -X POST http://localhost:58080/items -H "Content-type: application/xml" --data "@src/test/resources/item.xml"
echo -------------------------------------------- reading /items/0 in xml format
curl -s http://localhost:58080/items/0 -H "Accept: application/xml" | xmllint --format -
echo ------------------------------------------- reading /items/0 in json format
curl -s http://localhost:58080/items/0 -H "Accept: application/json" | python -m json.tool
echo ------------------------------------- updating /items/0 with a json content
curl -i -X PUT http://localhost:58080/items/0 -H "Content-type: application/json" --data "@src/test/resources/item.json"
echo -------------------------------------------- reading /items/0 in xml format
curl -s http://localhost:58080/items/0 -H "Accept: application/xml" | xmllint --format -
echo ------------------------------------------- reading /items/0 in json format
curl -s http://localhost:58080/items/0 -H "Accept: application/json" | python -m json.tool

echo ------------------------------------ creating on /items with a json content
curl -i -X POST http://localhost:58080/items -H "Content-type: application/json" --data "@src/test/resources/item.json"
echo -------------------------------------------- reading /items/1 in xml format
curl -s http://localhost:58080/items/1 -H "Accept: application/xml" | xmllint --format -
echo ------------------------------------------- reading /items/1 in json format
curl -s http://localhost:58080/items/1 -H "Accept: application/json" | python -m json.tool
echo ------------------------------------- updating /items/1 with an xml content
curl -i -X PUT http://localhost:58080/items/1 -H "Content-type: application/xml" --data "@src/test/resources/item.xml"
echo -------------------------------------------- reading /items/1 in xml format
curl -s http://localhost:58080/items/1 -H "Accept: application/xml" | xmllint --format -
echo ------------------------------------------- reading /items/1 in json format
curl -s http://localhost:58080/items/1 -H "Accept: application/json" | python -m json.tool

echo ------------------------------------------- reading all items in xml format
curl -s http://localhost:58080/items -H "Accept: application/xml" | xmllint --format -
echo ------------------------------------------ reading all items in json format
curl -s http://localhost:58080/items -H "Accept: application/json" | python -m json.tool

echo --------------------------------------------------------------- deleting /0
curl -i -X DELETE http://localhost:58080/items/0
echo --------------------------------------------------------- trying to read /0
curl --fail http://localhost:58080/items/0
echo --------------------------------------------------------------- deleting /1
curl -i -X DELETE http://localhost:58080/items/1
echo --------------------------------------------------------- trying to read /1
curl --fail http://localhost:58080/items/1

echo ------------------------------------------- reading all items in xml format
curl -s http://localhost:58080/items -H "Accept: application/xml" | xmllint --format -
echo ------------------------------------------ reading all items in json format
curl -s http://localhost:58080/items -H "Accept: application/json" | python -m json.tool

curl -s http://localhost:58080/items.xsd | xmllint --format -
curl -s http://localhost:58080/items.jsd | python -m json.tool

