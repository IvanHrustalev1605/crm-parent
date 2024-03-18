FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /src
COPY . .
RUN mvn clean install

FROM openjdk:17-oracle AS administration-service
WORKDIR /app
COPY --from=builder /src/target/administration-service.jar /app/administration-service.jar
ENTRYPOINT ["java","-jar","administration-service.jar"]