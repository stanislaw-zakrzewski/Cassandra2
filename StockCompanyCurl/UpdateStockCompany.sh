curl -X PUT  http://localhost:8082/stockCompany   -H 'Cache-Control: no-cache'   -H 'Content-Type: application/json'   -d '{
  "key":{"stock":"nasdaq","symbol":"AAPL"},
  "company_name": "Apple Inc.."
}'