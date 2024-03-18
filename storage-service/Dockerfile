FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /src
COPY . .
RUN mvn clean install

FROM openjdk:17-oracle AS storage-service
WORKDIR /app
COPY --from=builder /src/target/storage-service.jar /app/storage-service.jar
ENTRYPOINT ["java","-jar","storage-service.jar"]