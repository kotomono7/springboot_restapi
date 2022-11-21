FROM maven:3.8.6 AS build
WORKDIR /restapi
COPY pom.xml /restapi
RUN mvn dependency:resolve
COPY . /restapi
RUN mvn clean
RUN mvn package -DskipTests

FROM openjdk:17-jdk-alpine
COPY --from=build /restapi/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]