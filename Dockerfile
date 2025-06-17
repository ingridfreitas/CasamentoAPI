FROM openjdk:24-jdk-slim

RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY . .

RUN mvn clean install -DskipTests

# ADICIONE ESTA LINHA: Garante que o JAR é executável
RUN chmod +x target/*.jar

EXPOSE 8080

# Mude esta linha para a forma "exec" (lista de strings)
CMD ["java", "-jar", "target/*.jar"]