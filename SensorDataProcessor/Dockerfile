FROM openjdk:17-alpine

EXPOSE 8090
WORKDIR app
COPY target/sensor-streaming-processor-0.0.1-SNAPSHOT.jar app/sensor-streaming-processor.jar
CMD ["java","-jar","app/sensor-streaming-processor.jar"]