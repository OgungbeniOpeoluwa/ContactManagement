FROM maven:3.9.4-eclipse-temurin-21-alpine as build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk
COPY --from=build /target/ContactApp-1.0-SNAPSHOT.jar ContactApp.jar
EXPOSE 9060
ENTRYPOINT ["java", "-jar", "ContactApp.jar"]