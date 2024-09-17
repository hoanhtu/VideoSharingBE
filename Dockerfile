# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
#FROM eclipse-temurin:17-jre
#WORKDIR /app
#COPY --from=build /app/target/YouTubeSharingApp-0.0.1-SNAPSHOT.jar /app/YouTubeSharingApp.jar
#EXPOSE 8080
#CMD ["java", "-jar", "/app/YouTubeSharingApp.jar"]