FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /src
COPY . .
RUN mvn clean install

FROM openjdk:17-oracle AS document-service
WORKDIR /app
COPY --from=builder /src/target/document-service.jar /app/document-service.jar
ENTRYPOINT ["java","-jar","document-service.jar"]