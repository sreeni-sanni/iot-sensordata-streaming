FROM openjdk:17-alpine

EXPOSE 8080
WORKDIR app
COPY target/SensorDataAPI-0.0.1-SNAPSHOT.jar app/sensordataapi.jar
CMD ["java","-jar","app/sensordataapi.jar"]