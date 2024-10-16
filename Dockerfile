FROM maven:3.9.8-amazoncorretto-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:22-jdk

WORKDIR /app

COPY --from=build /app/target/projectmanagement-0.1.jar /app/projectmanagement.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/projectmanagement.jar"]
