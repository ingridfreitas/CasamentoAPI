# Etapa 1: build da aplicação
FROM maven:3.9.6-eclipse-temurin-24 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: imagem de execução
FROM eclipse-temurin:24-jre
WORKDIR /app
COPY --from=build /app/target/CasamentoAPI-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
