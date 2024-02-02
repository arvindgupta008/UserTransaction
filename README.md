# userTransactionManagement

## Create user transaction
```curl --location --request POST 'localhost:8080/rewards/recordTransaction' \
--header 'Content-Type: application/json' \
--data '{

"customerId":{customerId},
"transactionAmount": 130.0
}'
```
## Get the monthly reward and transaction
```
curl --location --request GET 'localhost:8080/rewards/monthlyRewardPoints/{customerId}?requestedMonth=2024-01'
```

## Get the reward in date range

```
curl --location --request GET 'localhost:8080/rewards/customer/{customerId}?startDate=2024-01-01&endDate=2024-02-10'
```

