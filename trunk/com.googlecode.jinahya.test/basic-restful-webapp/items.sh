#!/bin/sh
item_xml_size=$(stat -c%s "src/test/resources/item.xml")
item_json_size=$(stat -c%s "src/test/resources/item.json")

echo ------------------------------------ creating on /items with an xml content
curl -i -X POST http://localhost:58080/items -H "Content-type: application/xml" --data "@src/test/resources/item.xml"
#echo -------------------------------------------- reading /items/1 in xml format
#curl -s http://localhost:58080/items/1 -H "Accept: application/xml" | xmllint --format -
echo ------------------------------------------- reading /items/1 in json format
curl -s http://localhost:58080/items/1 -H "Accept: application/json" | python -m json.tool
echo ------------------------------------- updating /items/1 with a json content
curl -i -X PUT http://localhost:58080/items/1 -H "Content-type: application/json" --data "@src/test/resources/item.json"
echo -------------------------------------------- reading /items/1 in xml format
curl -s http://localhost:58080/items/1 -H "Accept: application/xml" | xmllint --format -
#echo ------------------------------------------- reading /items/1 in json format
#curl -s http://localhost:58080/items/1 -H "Accept: application/json" | python -m json.tool
curl -s http://localhost:58080/items -H "Accept: application/json" | python -m json.tool

echo ------------------------------------ creating on /items with a json content
curl -i -X POST http://localhost:58080/items -H "Content-type: application/json" --data "@src/test/resources/item.json"
#echo -------------------------------------------- reading /items/2 in xml format
#curl -s http://localhost:58080/items/2 -H "Accept: application/xml" | xmllint --format -
echo ------------------------------------------- reading /items/2 in json format
curl -s http://localhost:58080/items/2 -H "Accept: application/json" | python -m json.tool
echo ------------------------------------- updating /items/2 with an xml content
curl -i -X PUT http://localhost:58080/items/2 -H "Content-type: application/xml" --data "@src/test/resources/item.xml"
echo -------------------------------------------- reading /items/2 in xml format
curl -s http://localhost:58080/items/2 -H "Accept: application/xml" | xmllint --format -
#echo ------------------------------------------- reading /items/2 in json format
#curl -s http://localhost:58080/items/2 -H "Accept: application/json" | python -m json.tool

echo ------------------------------------------- reading all items in xml format
curl -s http://localhost:58080/items -H "Accept: application/xml" | xmllint --format -
curl -s http://localhost:58080/items -H "Accept: application/xml" | xmllint --format - > items.xml
echo ------------------------------------------ reading all items in json format
curl -s http://localhost:58080/items -H "Accept: application/json" | python -m json.tool
curl -s http://localhost:58080/items -H "Accept: application/json" | python -m json.tool > items.json

echo --------------------------------------------------------- deleting /items/0
curl -i -X DELETE http://localhost:58080/items/1
echo --------------------------------------------------- trying to read /items/1
curl --fail http://localhost:58080/items/1
echo --------------------------------------------------------- deleting /items/2
curl -i -X DELETE http://localhost:58080/items/2
echo --------------------------------------------------- trying to read /items/2
curl --fail http://localhost:58080/items/2

echo ------------------------------------------- reading all items in xml format
curl -s http://localhost:58080/items -H "Accept: application/xml" | xmllint --format -
echo ------------------------------------------ reading all items in json format
curl -s http://localhost:58080/items -H "Accept: application/json" | python -m json.tool

curl -s http://localhost:58080/items.xsd | xmllint --format -
curl -s http://localhost:58080/items.jsd | python -m json.tool

