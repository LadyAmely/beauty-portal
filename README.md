# Getting Started

### Requirements

 - Java 17 (JDK 17)
 - Gradle wrapper is included (./gradlew / gradlew.bat)

### Run the application

```
./gradlew bootRun
```
App running at http://localhost:9090.

### Run tests

```
./gradlew test
```

### Run checkstyle

```
./gradlew style
```
## SALES CHANNELS module

---

## Testing the Sales-Channels API with cURL

### Swagger:

UI: http://localhost:9090/swagger-ui

Spec: http://localhost:9090/v3/api-docs

All examples below assume the controller base path: /api/v1/sales-channels.

#### 1) Report Monthly SKU Sales

```
curl -i -v -X POST "http://localhost:9090/api/v1/sales-channels/monthly-sku-sales" \
  -H "Content-Type: application/json" \
  -d '{
    "distributorId":"11111111-1111-1111-1111-111111111111",
    "year":2025,"month":8,
    "lines":[
      {"skuCode":"SKU-123-ABC","channel":"PHARMACY","quantity":42.000,"netValueInputCcy":1234.56,"currency":"PLN"}
    ]
  }'

```

#### 2) Report Inventory Snapshot

```
curl -i -X POST "http://localhost:9090/api/v1/sales-channels/inventory-snapshots" \
  -H "Content-Type: application/json" \
  -d '{
    "distributorId": "11111111-1111-1111-1111-111111111111",
    "snapshotDate": "2025-09-15T00:00:00",
    "lines": [
      { "skuCode": "SKU-123-ABC", "quantity": 1300.000, "location": "WH-01-C" }
    ]
  }'
  
```
#### 3) Create Quarterly Report 

```
curl -i -X POST "http://localhost:9090/api/v1/sales-channels/quarter-reports" \
  -H "Content-Type: application/json" \
  -d '{
    "distributorId": "11111111-1111-1111-1111-111111111111",
    "year": 2025,
    "quarter": 3,
    "inputCurrency": "PLN",
    "newClients": 5,
    "lines": [
      { "month": 7, "channel": "PHARMACY",       "amountInputCcy": 1234.56 },
      { "month": 8, "channel": "ECOMMERCE_B2C",  "amountInputCcy": 9876.50 },
      { "month": 9, "channel": "PHARMACY",       "amountInputCcy": 4321.00 }
    ]
  }'

```

#### 4) List Clients by Channel

```
curl -i -G "http://localhost:9090/api/v1/sales-channels/clients/11111111-1111-1111-1111-111111111111/by-channel" \
  -H "Accept: application/json" \
  --data-urlencode "channel=PHARMACY" \
  --data-urlencode "page=0" \
  --data-urlencode "size=20"

```