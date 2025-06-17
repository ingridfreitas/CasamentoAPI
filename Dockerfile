# Use uma imagem base oficial do OpenJDK para Java e Maven
# Escolha a versão do Java que seu projeto usa (ex: openjdk:17-jdk, openjdk:21-jdk)
# Para este exemplo, vou usar a versão 21, mas ajuste se sua API usa outra.
FROM openjdk:24-jdk-slim

# Instala o Maven dentro do container
# Isso garante que o comando 'mvn' estará disponível
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar os arquivos de build do Maven para aproveitar o cache (se dependências não mudarem)
# Isso copia apenas o pom.xml para que o Maven possa baixar as dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar todo o resto do projeto
COPY . .

# Comando para compilar o projeto
# Agora o 'mvn' será encontrado
RUN mvn clean install -DskipTests

# Expor a porta que sua aplicação Spring Boot vai usar (geralmente 8080)
EXPOSE 8080

# Comando para executar a aplicação JAR quando o container iniciar
# O comando para iniciar seu JAR. Ele deve ter sido gerado na pasta target.
# O '*' é um curinga para o nome exato do JAR, que pode incluir a versão.
CMD ["java", "-jar", "target/*.jar"]