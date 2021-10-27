## BookStore Project - Ahmet Colak
This project aims simple BookStore project that have below features:

- Creating New Customer
- Creating a new order
- Add/Update Book 
- Viewing the stock of books
- Listing all orders of customer
- Viewing the order details
- Query Monthly Statistics

### TOOLS & TECH
- Java 11
- Spring Boot 2.5.6
    - Spring Security
    - JWT
    - Spring Data
- MongoDB
- Docker
- SpringDoc OpenApi
- Lombok
- Maven

### 1.1 How To Install
##### Note: you should have installed docker. https://www.docker.com/products/docker-desktop
##### Note2 : You should have minimum Java 11 version.

first clone or download project then

`cd ReadingIsGood`

`mvn clean install`
### 1.2 How to Run
`docker-compose up --build`

Run tests
```
$ cd ReadingIsGood
$ mvn test
```

## 1.3 Usage
Show application ui in Swagger
http://localhost:7001/ty_url_service/swagger-ui.html

#### Swagger
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

#### Postman Collection
https://www.getpostman.com/collections/8915cc28b202a888f0a7
