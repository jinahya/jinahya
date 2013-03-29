#!/bin/sh
echo ---------------------------------------------------------------------------
echo 'CREATE -> POST /items'
echo ---------------------------------------------------------------------------
curl -v -X POST -H "Content-type: application/xml" -T src/test/resources/item.xml http://localhost:58080/basic-restful-webapp/items
echo ---------------------------------------------------------------------------
echo 'READ -> GET /items/0'
echo ---------------------------------------------------------------------------
curl -v -H "Accept: application/json" http://localhost:58080/basic-restful-webapp/items/0 | python -m json.tool
echo ---------------------------------------------------------------------------
echo 'CREATE -> POST /items'
echo ---------------------------------------------------------------------------
curl -i -X POST -H "Content-type: application/json" -T src/test/resources/item.json http://localhost:58080/basic-restful-webapp/items
echo ---------------------------------------------------------------------------
echo 'READ -> GET /items/1'
echo ---------------------------------------------------------------------------
curl -v -H "Accept: application/xml" http://localhost:58080/basic-restful-webapp/items/1 | xmllint --format -
echo ---------------------------------------------------------------------------
echo 'UPDATE -> PUT /items/0'
echo ---------------------------------------------------------------------------
curl -v -X PUT -H "Content-type: application/xml" -T src/test/resources/item.xml.update http://localhost:58080/basic-restful-webapp/items/0
echo ---------------------------------------------------------------------------
echo 'READ -> GET /items/0'
echo ---------------------------------------------------------------------------
curl -v -H "Accept: application/json" http://localhost:58080/basic-restful-webapp/items/0 | python -m json.tool
echo ---------------------------------------------------------------------------
echo 'UPDATE -> PUT /items/1'
echo ---------------------------------------------------------------------------
curl -v -X PUT -H "Content-type: application/json" -T src/test/resources/item.json.update http://localhost:58080/basic-restful-webapp/items/1
echo ---------------------------------------------------------------------------
echo 'READ -> GET /items/1'
echo ---------------------------------------------------------------------------
curl -v -H "Accept: application/xml" http://localhost:58080/basic-restful-webapp/items/1 | xmllint --format -
echo ---------------------------------------------------------------------------
echo 'DELETE -> items/0'
echo ---------------------------------------------------------------------------
curl -v -X DELETE http://localhost:58080/basic-restful-webapp/items/0
echo ---------------------------------------------------------------------------
echo 'DELETE -> items/1'
echo ---------------------------------------------------------------------------
curl -v -X DELETE http://localhost:58080/basic-restful-webapp/items/1

