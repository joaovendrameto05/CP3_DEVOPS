FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app
ENV APP_VERSION="1.0"
RUN groupadd -r dimdim-group && useradd -r -g dimdim-group jv-user
COPY --from=build /app/target/*.jar app.jar
RUN chown jv-user:dimdim-group app.jar
USER jv-user
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]