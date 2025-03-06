#                         IOT-Streaming-Sensor-Data
* ****
## Overview
* ****
 This project is a real-time IOT sensor data processing pipeline built using Spring Boot, Kafka and MongoDB.
 The application:
- Simulates multiple IOT devices sending sensor readings every second.
- Stream and process data using Kafka (Streams).
- Store sensor reading in MongoDB
- Provides a REST API to query sensor data(min/max/avg reading for specific or group of sensors for a time range)
## Design
* ****
![IOT-Streaming-Application.png](IOT-Streaming-Application.png)
##  Technologies Used
- Java17
- Maven
- Spring Boot 3(Spring web,Spring Data,Spring security, Sparing kafka)
- Kafka 
- MongoDB
- Docker and Docker Compose
- JWT

## Getting Started
* ****
### Requirements
- Docker
- Java 17
### Build & Run
- Run docker compose command to build and deploy both applications.

  Note: Build and deploying all application in single docker compose file for assignment purpose. 

```
docker-compose -f "docker-compose.yml" up --build -d
```
All containers are running and can see them as below :

 ![img.png](img.png)
* ****

## API Endpoints
* ****
### Base URL
- `http://localhost:8080/`
- Swagger-UI URL 'http://localhost:8080/swagger-ui/index.html'
- Health 'http://localhost:8080/actuator/health'

### Security
The API uses Bearer Authentication (JWT). Include a valid token in the `Authorization` header of your requests to access the secure endpoints.

Example:
```
Authorization: Bearer <JWT_TOKEN>
```
### 1. Generate Authentication Token
- **Endpoint:** **POST**  'http://localhost:8080/api/v1/auth/token`
- **Description:** Generate a JWT token using for authenticated access to the API.
- **Request Body:**
```json
{
  "username": "datastream",
   "password":"datastream!"
}
```
- **Response:**
```json
"JWT_TOKEN"
``` 
### 2.Min
- **Endpoint:** **POST** 'http://localhost:8080/api/v1/min`
- **Description:** Calculate min value for specific sensor or group sensors for a specific timeframe.
- **Request Body:**
```json
{
 "sensorNames": ["FUEL_READING","THERMOSTAT"],
 "startDate": "2025-03-03T18:45:51.611",
 "endDate":"2025-03-03T18:46:10.616"
}
```
- **Response:**
```json
[
 {
  "sensorName": "FUEL_READING",
  "MinValue": 2.5
},
 {
  "sensorName": "THERMOSTAT",
  "MinValue": 1
 }
]
```
### 3.Max
- **Endpoint:** **POST** 'http://localhost:8080/api/v1/max`
- **Description:** Calculate max value for specific sensor or group sensors for a specific timeframe.
- **Request Body:**
```json
{
 "sensorNames": ["FUEL_READING","THERMOSTAT"],
 "startDate": "2025-03-03T18:45:51.611",
 "endDate":"2025-03-03T18:46:10.616"
}
```
- **Response:**
```json
[
 {
  "sensorName": "FUEL_READING",
  "maxValue": 2.5
},
 {
  "sensorName": "THERMOSTAT",
  "maxValue": 1
 }
]
```
### 4.Average
- **Endpoint:** **POST** 'http://localhost:8080/api/v1/avg`
- **Description:** Calculate avg value for specific sensor or group sensors for a specific timeframe.
- **Request Body:**
```json
{
 "sensorNames": ["FUEL_READING","THERMOSTAT"],
 "startDate": "2025-03-03T18:45:51.611",
 "endDate":"2025-03-03T18:46:10.616"
}
```
- **Response:**
```json
[
 {
  "sensorName": "FUEL_READING",
  "avgValue": 2.5
},
 {
  "sensorName": "THERMOSTAT",
  "avgValue": 1
 }
]
```
### 4.Median
- **Endpoint:** **POST** 'http://localhost:8080/api/v1/median`
- **Description:** Calculate median value for specific sensor or group sensors for a specific timeframe.
- **Request Body:**
```json
{
 "sensorNames": ["FUEL_READING","THERMOSTAT"],
 "startDate": "2025-03-03T18:45:51.611",
 "endDate":"2025-03-03T18:46:10.616"
}
```
- **Response:**
```json
[
 {
  "sensorName": "FUEL_READING",
  "medianValue": 2.5
},
 {
  "sensorName": "THERMOSTAT",
  "medianValue": 1
 }
]
```