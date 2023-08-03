### Problem statement
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Given a record of every transaction during a three-month period, calculate the reward points earned for each customer per month and total.

•	Solve using Spring Boot

•	Create a RESTful endpoint

•	Make up a data set to best demonstrate your solution

•	Check solution into GitHub

-------------------------------

### Used Tools/Software
Java JDK 17

Apache maven

H2 DB

Postman/cURL/Browser

-------------------------------

### Code Setup

Open terminal & type code

```
    git clone https://github.com/navpreet2022/rewards.git
    
    cd rewards
````
![image](/images/ProjectSetup.png)

-------------------------------

### Run Test Cases
mvn clean

mvn test

![image](/images/AppTestCases.png)

-------------------------------

### Run Application
mvn spring-boot:run

![image](/images/AppServerStart.png)

-------------------------------

### Host
**HOST**  - http://localhost:8080

![image](/images/AppRunningStatus.png)

-------------------------------

### DB Connection
**URL** - {{HOST}}/h2

**Credientials** - admin/admin

![image](/images/H2LoginTestConnection.png)

Customer Table

![image](/images/H2DB-CustomerTable.png)


Transaction Table

![image](/images/H2DB-TransactionTable.png)

-------------------------------

### API

**GET {{HOST}}/rewards** - get all rewards for customers

**GET {{HOST}}/rewards/{customerId}** - get all rewards for specified customerId

**GET {{HOST}}/transactions** - get all transactions

**GET {{HOST}}/transactions/customer/{customerId}** - get all transactions for specified customerId


-------------------------------

### Examples

-------------------------------

**1. Get all rewards** 

**_GET /rewards_**

Request:
``` 
curl --location --request 
GET 'http://localhost:8080/rewards'
```

Response:
```
[{"id":1,"name":"Adam","rewardPointsPerMonth":{"2022-09":0,"2022-10":300,"2022-08":0},"lastThreeMonthsRewardPoints":[300,0,0],"totalRewardPoints":300},{"id":2,"name":"Bob","rewardPointsPerMonth":{"2022-09":130,"2022-10":180,"2022-07":90,"2022-08":70},"lastThreeMonthsRewardPoints":[180,130,70],"totalRewardPoints":470},{"id":3,"name":"Charle","rewardPointsPerMonth":{"2022-08":5},"lastThreeMonthsRewardPoints":[5],"totalRewardPoints":5},{"id":4,"name":"Danny","rewardPointsPerMonth":{"2022-09":60},"lastThreeMonthsRewardPoints":[60],"totalRewardPoints":60},{"id":5,"name":"Edward","rewardPointsPerMonth":{"2022-08":0},"lastThreeMonthsRewardPoints":[0],"totalRewardPoints":0},{"id":6,"name":"Finn","rewardPointsPerMonth":{"2010-06":250},"lastThreeMonthsRewardPoints":[250],"totalRewardPoints":250},{"id":7,"name":"Grason","rewardPointsPerMonth":{"2022-09":50},"lastThreeMonthsRewardPoints":[50],"totalRewardPoints":50},{"id":8,"name":"Henry","rewardPointsPerMonth":{},"lastThreeMonthsRewardPoints":[],"totalRewardPoints":0}]
```
-------------------------------

**2. Get rewards for a specified customer**

**_GET /rewards/{customerId}_**

```
curl --location --request 
GET 'http://localhost:8080/rewards/1'

``` 
```
Response:

{"id":1,"name":"Adam","rewardPointsPerMonth":{"2022-09":0,"2022-10":300,"2022-08":0},"lastThreeMonthsRewardPoints":[300,0,0],"totalRewardPoints":300}

```
-------------------------------

**3. Get all transactions**

**_GET /transactions_**

```
curl --location --request 
GET 'http://localhost:8080/transactions'
``` 
``` 
Response: 
[{"id":1001,"customerId":1,"transactionAmount":50,"creationDate":"2022-08-29"},{"id":1002,"customerId":1,"transactionAmount":10,"creationDate":"2022-09-14"},{"id":1003,"customerId":1,"transactionAmount":150,"creationDate":"2022-10-04"},{"id":1004,"customerId":1,"transactionAmount":150,"creationDate":"2022-10-22"},{"id":1005,"customerId":1,"transactionAmount":50,"creationDate":"2022-10-30"},{"id":1006,"customerId":2,"transactionAmount":120,"creationDate":"2022-07-01"},{"id":1007,"customerId":2,"transactionAmount":110,"creationDate":"2022-08-02"},{"id":1008,"customerId":2,"transactionAmount":140,"creationDate":"2022-09-03"},{"id":1009,"customerId":2,"transactionAmount":120,"creationDate":"2022-10-04"},{"id":1010,"customerId":2,"transactionAmount":120,"creationDate":"2022-10-19"},{"id":1011,"customerId":3,"transactionAmount":55,"creationDate":"2022-08-20"},{"id":1012,"customerId":4,"transactionAmount":105,"creationDate":"2022-09-30"},{"id":1013,"customerId":5,"transactionAmount":35,"creationDate":"2022-08-31"},{"id":1014,"customerId":6,"transactionAmount":200,"creationDate":"2010-06-13"},{"id":1015,"customerId":7,"transactionAmount":100,"creationDate":"2022-09-25"}]

```

-------------------------------

**4. Get transactions for a specified customer**

**_GET /transactions/customer/{customerId}_**

```
curl --location --request 
GET 'http://localhost:8080/transactions/customer/1'
``` 
``` 
Response: 
[{"id":1001,"customerId":1,"transactionAmount":50,"creationDate":"2022-08-29"},{"id":1002,"customerId":1,"transactionAmount":10,"creationDate":"2022-09-14"},{"id":1003,"customerId":1,"transactionAmount":150,"creationDate":"2022-10-04"},{"id":1004,"customerId":1,"transactionAmount":150,"creationDate":"2022-10-22"},{"id":1005,"customerId":1,"transactionAmount":50,"creationDate":"2022-10-30"}]

``` 

-------------------------------

### Screenshots

## Application --

Project Structure

![image](/images/AppProjectStructure.png)


Project Unit Test

![image](/images/AppTestCases.png)

Project Server Start

![image](/images/AppServerStart.png)

-------------------------------

## Rewards ---

All Rewards

![image](/images/AllRewards.png)


Rewards by Customer Id

![image](/images/RewardsByCustomerId.png)


Rewards Before Adding new Transaction

![image](/images/CustomerRewardPointsBeforeUpdate.png)

Add new transaction

![image](/images/H2DB-TransactionTable.png)

![image](/images/H2DB-TransactionTableInsertRecord.png)

Rewards After Adding new Transaction

![image](/images/CustomerRewardPointsAfterAddingNewTransaction.png)

-------------------------------

## Transactions ---

All Transaction Records

![image](/images/AllTransactions.png)


Transaction Records By customer Id

![image](/images/TransactionByCustomerId.png)

-------------------------------

## Bad Request Handling

![image](/images/BadRequest.png)
