# userTransactionManagement

## Create user transaction
```curl --location 'localhost:8080/rewards/recordTransaction' \
--header 'Content-Type: application/json' \
--data '{

"customerId":123,
"transactionAmount": 130.0
}'
```
## Get the monthly reward and transaction
```
curl --location 'localhost:8080/rewards/monthlyRewardPoints/123?requestedMonth=2024-01'
```

## Get the reward in date range

```
curl --location 'localhost:8080/rewards/customer/123?startDate=2024-01-01&endDate=2024-02-10'
```

