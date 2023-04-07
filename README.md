# Simple SNS

## 🖥️ Develop Enviroment
<p>
<img src="https://img.shields.io/badge/Java 11-1E8CBE?style=for-the-badge&logo=&logoColor=white"> 
<img src="https://img.shields.io/badge/springboot 2.7.1-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white">
<img src="https://img.shields.io/badge/Gradle 2.7.1-02303A?style=for-the-badge&logo=Gradle&logoColor=white">
<img src="https://img.shields.io/badge/Intellij IDEA Ultimate-000000?style=for-the-badge&logo=Intellij IDEA&logoColor=white">
</p>

----

## 📂 Detail Stack
<p>
<img src="https://img.shields.io/badge/JAVA 11-3776AB?style=for-the-badge&logo=Java&logoColor=white">
<img src="https://img.shields.io/badge/Spring DATA JPA-6DB33F?style=for-the-badge&logo=Codeforces&logoColor=white">
<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white">
</p>
<p>
<img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=Postgresql&logoColor=white">
<img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white">
</p>

<img src="https://img.shields.io/badge/Kafka-231F20?style=for-the-badge&logo=ApacheKafka&logoColor=white">

<p>
<img src="https://img.shields.io/badge/PostMan-FF6C37?style=for-the-badge&logo=Postman&logoColor=white">
<img src="https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=JUnit5&logoColor=white">
</p>

<p>
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
<img src="https://img.shields.io/badge/AWS ECR-FF9900?style=for-the-badge&logo=Amazon EKS&logoColor=white">
<img src="https://img.shields.io/badge/AWS ECS-FF9900?style=for-the-badge&logo=Amazon ECS&logoColor=white">
</p>

<p>
<img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/Github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/gitkraken-179287?style=for-the-badge&logo=gitkraken&logoColor=white">
</p>

----

## [🔗 ERD]()

----

## Getting Start
```shell
$ git clone https://github.com/Gonue/sqs-lambda-trigger-ex.git
```
```shell
$ docker-compose -f docker-compose-local-test.yaml
```

### OR

```shell
$ docker pull ghcr.io/gonue/application-server:latest
```

### [🔗 Docker Hub](https://hub.docker.com/repository/docker/gonue/application-server/general)
### [🔗 Packages Link](https://github.com/Gonue/sqs-lambda-trigger-ex/pkgs/container/application-server)

## 🐳 docker-compose Detail

### Docker-compose-local-test.yml
```yaml
version: "3.8"

services:

  postgresql:
    image: postgres
    container_name: sns-test-postgres
    ports:
      - "5432:5432"
    environment:
      POSTGRES_DB: sns
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 1234
    volumes:
      - /var/lib/postgresql/data

  zookeeper:
    image: wurstmeister/zookeeper
    container_name: sns-test-zookeeper
    ports:
      - "2181:2181"
  kafka:
    image: wurstmeister/kafka:2.12-2.5.0
    container_name: sns-test-kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_HOST_NAME: 127.0.0.1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_CREATE_TOPICS : "alarm:1:1" //Topics Setting
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock

  redis:
    image: redis:latest
    container_name: sns-test-redis
    ports:
      - "6379:6379"

  gonue-sns-server:
    container_name: gonue-sns-app
    build: .
    depends_on:
      - sns-test-postgres
      - sns-test-zookeeper
      - sns-test-kafka
      - sns-test-redis
    image: gonue/application-server
    ports:
      - "8080:8080"
    restart: always
```

----
## 📑 Reference

### [🔗 API Docs]() & [🔗 API Endpoint]()

### [🔗 Application Architecture]()

----







