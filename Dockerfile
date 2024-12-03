FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
RUN mvn test

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/ToDoPro-0.0.1-SNAPSHOT.jar ToDoPro.jar
COPY wait-for-db.sh /app/wait-for-db.sh
RUN chmod +x /app/wait-for-db.sh
ENTRYPOINT ["java", "-jar", "ToDoPro.jar"]