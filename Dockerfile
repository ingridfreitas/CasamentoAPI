FROM maven:3.9.6-eclipse-temurin-24 AS build

RUN apt-get update && apt-get install openjdk-24-jdk maven -y

COPY . .

RUN mvn clean install

FROM openjdk:24-jdk-slim
EXPOSE 8080

COPY --from=build /target/CasamentoAPI-0.0.1-SNAPSHOT.jar /app/CasamentoAPI.jar

ENTRYPOINT ["java", "-jar", "/app/CasamentoAPI.jar"]