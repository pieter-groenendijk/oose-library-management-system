FROM maven:3.9-eclipse-temurin-21

WORKDIR /app

COPY core/pom.xml .
RUN mvn dependency:go-offline # Download dependencies so we don't have to download them every compile

COPY core/src src

CMD ["mvn", "clean", "test"]