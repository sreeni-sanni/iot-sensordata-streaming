package com.iot.sensordataapi.model;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record SensorDataRequest(@NotBlank List<String> sensorNames, @NotBlank String startDate, @NotBlank String endDate) {
}
