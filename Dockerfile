
# Stage 1: Build the application using Maven and JDK 17

FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy Maven configuration and source code
COPY pom.xml .
COPY src ./src

# Build the application (skip tests to reduce build time)
RUN mvn clean package -DskipTests


# Stage 2: Lightweight runtime image (JRE only)

FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the JAR produced in Stage 1
COPY --from=build /app/target/*.jar sms.jar

# Application runs on port 8100
EXPOSE 8100

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "sms.jar"]
