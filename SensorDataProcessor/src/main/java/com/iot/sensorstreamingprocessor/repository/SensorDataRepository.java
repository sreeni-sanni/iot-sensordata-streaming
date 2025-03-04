package com.iot.sensorstreamingprocessor.repository;

import com.iot.sensorstreamingprocessor.model.SensorData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDataRepository extends MongoRepository<SensorData, String> {
}
