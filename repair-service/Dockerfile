FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /src
COPY . .
RUN mvn clean install

FROM openjdk:17-oracle AS repair-service
WORKDIR /app
COPY --from=builder /src/target/repair-service.jar /app/repair-service.jar
ENTRYPOINT ["java","-jar","repair-service.jar"]