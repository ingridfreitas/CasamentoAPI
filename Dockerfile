# Etapa 1: build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: execução
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/CasamentoAPI-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
