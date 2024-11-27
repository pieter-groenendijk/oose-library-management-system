# <maven-version>-<java-vendor>-<java-version>
FROM maven:3.9-eclipse-temurin-21

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline # Download dependencies so we don't have to download them every compile

COPY src src

CMD ["mvn", "clean", "compile", "exec:java"]