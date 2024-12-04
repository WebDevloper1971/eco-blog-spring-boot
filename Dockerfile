# FROM maven:3.8.5-openjdk-17 AS build
# COPY . .
# RUN mvn clean package -DskipTests


# FROM openjdk:17.0.1-jdk-slim
# COPY --from=build target/blog-api-0.0.1-SNAPSHOT.jar demo.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "demo.jar"]

# Use Maven to build the Spring Boot application

FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
# Copy the project's pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B
# Copy the entire project and build it
COPY src ./src
RUN mvn clean package -DskipTests


# Use Eclipse Temurin JRE to run the application
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copy the built jar file from the previous stage
COPY --from=build /app/target/blog-api-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "blog-api-0.0.1-SNAPSHOT.jar"]
