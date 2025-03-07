package com.iot.streamingprocessor.repository;

import com.iot.streamingprocessor.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingProcessorRepository extends MongoRepository<Event, String> {
}
