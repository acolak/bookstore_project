## BookStore Project - Ahmet Colak
This project consist of simple BookStore project that have below features:

- Persist New Customer
- Persist a new order
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
- Junit 5, Mockito

### 1.1 How To Install
##### Note: you should have installed docker. https://www.docker.com/products/docker-desktop
##### Note2 : You should have minimum Java 11 version and Maven.

first clone or download project then

`cd bookstore_project-main`

### 1.2 How to Run
Run **runProject.sh** file in root directory

    > chmod +x runProject.sh 
    
    > ./runProject.sh

*You don't need to mongodb separately, docker file contains mongodb image, and it can set required settings as well.

Run tests
```
$ cd bookstore_project-main
$ mvn test
```

## 1.3 Usage

#### Postman Collection
https://www.getpostman.com/collections/8915cc28b202a888f0a7

You can download postman collection above link, controllers grouped by domains. 
You should need to call first **login-authenticate** request to get bearer token.

##### Note: Bearer Authorization Header automatically added all requests in Postman, So you can call other requests without any action needed. 


#### Swagger
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/


