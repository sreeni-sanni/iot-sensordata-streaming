FROM openjdk:17-alpine

EXPOSE 8090
WORKDIR app
COPY target/iot-streaming-processor-0.0.1-SNAPSHOT.jar app/iot-streaming-processor.jar
CMD ["java","-jar","app/iot-streaming-processor.jar"]