# stock-application
Sample application using react, spring boot (both rest-api and messages), kafka and firebase. Tested with cucumber and spring-test.
Docker, docker-compose, lucidchart and other technologies.

# Build state
A github action workflow is in place showing the status of the 4 different sub-projects.

![BUILD](https://github.com/carlospatinos/stock-application/actions/workflows/gradle-build.yml/badge.svg)


# Ideal State
Here you will have a mix of projects living under the same home (same git repo). Besides this is not the real production structure, the idea here is just to demonstrate different spring boot applications working together sending REST-API requests, Kafka Messages and interacting with a Front-end application written in React.

![Architecture diagram](https://github.com/carlospatinos/stock-application/blob/main/doc/SpringBootEnterpriseApp.png?raw=true)

# Requirements 
- Java 8 installed
- Gradle (alternatively you can use the wrapper here gradlew)
- Docker 
- Docker compose 


# Ports required

```mermaid
flowchart
    frontendService["`<3000>
    FrontEnd Service`"]

    stockService["`<8080>
    Stock Service`"]
    
    paymentService["`<8081>
    Payment Service`"]
    
    notificationService["`<8082>
    Notification Service`"]
```

# Run

For the required infrastructure, a docker-compose file has been created inside docker folder which will pull images for kafka, zookeeper and schema registry. This needs to be up and running before any of the sub-projects is started as some of them will require kafka to work.

```sh
cd docker
docker-compose up
```

Make sure the project compiles. Go into the different sub-projects and compile them and start each one of them.

```sh
./gradlew build
./gradlew bootRun
```

There is a specific profile (prod) for those services using kafka. As default they have kafka disabled to make sure CucumberTesting and SprigBoot testing run fast. 

```sh
./gradlew bootrun --args='--spring.profiles.active=prod'
```

# API Documentation

OpenAPI package has been enabled to provide high level swagger docs. Each SpringBoot app exposing REST-Endpoints contains basic details. Those have to be improved going forward.

- http://localhost:8080/api/swagger-ui/index.html#/ 
- http://localhost:8082/api/swagger-ui/index.html#/

## Kafka Registry
- http://localhost:8091/subjects/stocktopic-value/versions/1

# Monitoring

Each microservice exposes some metrics in the following endpoint: 
- http://localhost:8080/api/actuator 
- http://localhost:8081/api/actuator 
- http://localhost:8082/api/actuator 

# TODOs
- [x] Cucumber 
- [x] OpenAPI
- [x] FrondEnd consuming stockApp api
- [x] Kafka message for buying operation