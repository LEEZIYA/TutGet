# TutGet
A tutor-student matching service with QnA board for interactivity and discovery!

# Installation & Running the app

Prerequisites:
- Java 17
- Maven

1. Make sure you have Java 17 in your pc's environment variables. Check with `java -version`
2. STARTING THE MAIN FRONTEND
2.0. ONE TIME SETUP for npm dependencies: go to `tutget\APPS\tutget-main\tutget-ui` and run `npm install`
2.1. Run the frontend: go to `tutget\APPS\tutget-main\tutget-ui` and run `npm run build`
2.2. Run the backend: go to `tutget\APPS\tutget-main` and run `mvn spring-boot:run` or through your IDE
2.3. Verify: go to your browser and type `localhost:8080`. You should see the TutGet website with images and text.
3. STARTING INDIVIDUAL SERVICES
3.1. Run the backend: go to `tutget\APPS\service-qna` and run `mvn spring-boot:run` or through your IDE
3.2. Verify: go to your browser and type `localhost:XXXX`, where XXXX is the port stated in `src/main/resources/application.properties` file. You should see a Whitelable Error page.

# How to start developing?

- I recommend starting with frontend first, because Angular has a great tutorial: https://angular.io/tutorial/tour-of-heroes/toh-pt1 . Can follow along while building your pages.
- After developing frontend, you'll need to use Spring to send and process data. Can try to follow existing files to mimic how it works.
- I know its ass to have to work on this project after doing full working hours :(. Dont hesitate to contact me (sigmund) for help or if youre stuck
