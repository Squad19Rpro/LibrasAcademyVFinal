FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/LibrasAcademyVFinal-1.0.jar app2.jar

ENTRYPOINT [ "java", "-jar", "app2.jar" ]