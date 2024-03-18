FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /src
COPY . .
RUN mvn clean install

FROM openjdk:17-oracle AS repair-parts-service
WORKDIR /app
COPY --from=builder /src/target/repair-parts-service.jar /app/repair-parts-service.jar
ENTRYPOINT ["java","-jar","repair-parts-service.jar"]