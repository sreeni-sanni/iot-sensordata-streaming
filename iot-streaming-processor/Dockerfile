FROM maven:3.9.6-eclipse-temurin-17 as build
WORKDIR /app
COPY iot-streaming-processor/pom.xml .
COPY iot-streaming-processor/src ./src
RUN mvn clean install


FROM eclipse-temurin:17 as builder
COPY --from=build /app/target/*.jar appData/iot-streaming-processor.jar
WORKDIR /appData
EXPOSE 8080
CMD ["java","-jar","iot-streaming-processor.jar"]