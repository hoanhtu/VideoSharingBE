# VideoSharingBE

## Overview
VideoSharingBE is a backend application for a video sharing platform. It is built using Java and Spring Boot, and it uses Maven for dependency management. The application provides functionalities for sharing videos, managing users, and sending notifications.

## Features
- User authentication and authorization using JWT.
- Video sharing and management.
- Notification service for user activities.

## Technologies Used
- Java
- Spring Boot
- Maven
- JPA (Java Persistence API)
- Mockito (for testing)

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.6.0 or higher

### Installation

2. Build the project using Maven:
    ```sh
    mvn clean install - DskipTests
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Running Tests
To run the tests, use the following command:
```sh
mvn test# VideoSharingBE

## Running Tests
    run the following command to build the docker image
    docker-compose up --build
