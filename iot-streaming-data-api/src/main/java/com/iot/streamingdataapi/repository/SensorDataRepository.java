package com.iot.streamingdataapi.repository;

import com.iot.streamingdataapi.model.SensorData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDataRepository extends MongoRepository<SensorData,String> {

    List<SensorData> findBySensorNameInAndTimeStampBetween(List<String> sensorName, String startDate, String endDate);

}
