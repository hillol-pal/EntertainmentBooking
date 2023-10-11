FROM maven:3.6.3-jdk-11-slim AS build
WORKDIR /theaterService
COPY src src
COPY pom.xml .
RUN mvn -f pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:11-jre-slim-buster
ARG JAR_FILE=target/theaterService-1.0.0.jar
COPY --from=build /theaterService/${JAR_FILE} theaterService-1.0.0.jar
ENTRYPOINT ["java","-jar","/theaterService-1.0.0.jar"]