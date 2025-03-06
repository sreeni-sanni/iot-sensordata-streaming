package com.iot.streamingprocessor.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class SensorData {

    @NotBlank
    private String sensorName;
    @NotNull
    private MetricType metricType;
    @NotNull
    private double metricValue;
    @NotBlank
    private String timeStamp;
}
