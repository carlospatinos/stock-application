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
- Firebase configuration
  - Please set up your project following these instructions: 
    - https://youtu.be/s8dFprr7COo  
    - https://youtu.be/uI4mVtxiwnY 
  - You will need firebase credentials to send notifications.


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
- [stockService](http://localhost:8080/api/actuator)
- [paymentService](http://localhost:8081/api/actuator)
- [notificationService](http://localhost:8082/api/actuator)

On top of that, you can enable ELK for log gathering. A docker-compose.yml file has been created to start logstash, kafka and kibana and the data flows as below:

![Log Flow](https://github.com/carlospatinos/stock-application/blob/main/doc/elk.png?raw=true)

Please modify the .env file inside docker/elk to point to the absolute path location of your application logs. 

You can check data is being sent to logstash here 
- http://localhost:9200/_cat/indices

You must configure kibana to show your data, creating indexes for all the services you are pulling data from. 

Go here to configure the indexes per application 
- http://localhost:5601/app/management/kibana/indexPatterns

On clicking "Create index pattern", enter the index name as you have mentioned under logstash.conf file’s "index" field i.e order-service and payment-service. Timestamp is optional and then click on "Create index pattern".

Finally go to here to see the data coming from your applications.
- http://localhost:5601/app/discover# 



# TODOs
- [x] Cucumber 
- [x] OpenAPI
- [x] FrondEnd consuming stockApp api
- [x] Kafka message for buying operation