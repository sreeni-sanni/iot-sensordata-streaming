package com.iot.streamingdataapi.model;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record SensorDataRequest( List<String> sensorNames, @NotBlank String startDate, @NotBlank String endDate) {
}
