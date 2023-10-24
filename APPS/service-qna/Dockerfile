FROM maven:3.8.4-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml package spring-boot:repackage --fail-never

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /home/app/target/TutgetQna.jar /usr/local/lib/app.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]