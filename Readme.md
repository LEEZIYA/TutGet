# TutGet
A tutor-student matching service with QnA board for interactivity and discovery!

# Installation & Running the app

Prerequisites:
- Java 17
- Maven

1. Navigate to APPS > ui
2. Open cmd and run `mvn spring-boot:run`. UI server will run in `localhost:8080`
3. Navigate to whichever service you're looking to run in APPS > service-xx
4. Open a separate cmd window and run `mvn spring-boot:run`. Each service's ports are listed in src/main/resources/application.properties file.