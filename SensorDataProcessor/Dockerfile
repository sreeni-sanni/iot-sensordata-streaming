FROM openjdk:17-alpine

EXPOSE 8080
WORKDIR app
COPY target/SensorDataProcessor-0.0.1-SNAPSHOT.jar app/sensordataprocessor.jar
CMD ["java","-jar","app/sensordataprocessor.jar"]