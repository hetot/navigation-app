FROM eclipse-temurin:17-jdk-alpine

RUN apk add file
COPY target/navigation-app-0.0.1-SNAPSHOT.jar navigation-app.jar

ENTRYPOINT ["java","-jar","/navigation-app.jar"]