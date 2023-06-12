FROM eclipse-temurin:17-jdk-alpine

RUN apk add file
COPY ./target/*.jar navigation-app.jar
COPY . ./home/

ENTRYPOINT ["java","-jar","./navigation-app.jar"]
EXPOSE 8081