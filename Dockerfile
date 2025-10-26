FROM eclipse-temurin:21-jdk-alpine AS builder 

RUN apk update && apk add maven

WORKDIR /app

COPY pom.xml .
COPY src /app/src

RUN mvn dependency:go-offline
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

ARG JAR_FILE=target/spring-0.0.1-SNAPSHOT.jar 
COPY --from=builder /app/${JAR_FILE} app.jar

EXPOSE 9091
ENTRYPOINT ["java", "-jar", "app.jar"]