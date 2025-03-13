FROM ubuntu:latest AS build

RUN apt update
RUN apt install openjdk-21-jdk -y
COPY . .

RUN java -version

RUN ./gradlew bootJar --no-daemon

FROM openjdk:21-jdk-slim

EXPOSE 8080

COPY --from=build /build/libs/accounter-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]