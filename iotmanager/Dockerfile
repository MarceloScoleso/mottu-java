# ------------------------------
# Etapa 1: Build da aplicação
# ------------------------------
FROM maven:3.9.8-eclipse-temurin-17 AS builder

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto
COPY pom.xml .
COPY src ./src

# Gera o JAR (sem rodar testes)
RUN mvn clean package -DskipTests


# ------------------------------
# Etapa 2: Runtime (rodar o app)
# ------------------------------
FROM eclipse-temurin:17-jdk

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=builder /app/target/iotmanager-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta usada pelo Spring Boot
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java","-jar","app.jar"]
