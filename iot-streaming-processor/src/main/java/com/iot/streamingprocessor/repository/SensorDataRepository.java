package com.iot.streamingprocessor.repository;

import com.iot.streamingprocessor.model.SensorData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDataRepository extends MongoRepository<SensorData, String> {
}
