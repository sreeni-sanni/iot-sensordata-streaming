package com.iot.sensorstreamingprocessor.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorData {

    @NotBlank
    private String sensorName;
    @NotBlank
    private MetricType metricType;
    @NotBlank
    private double metricValue;
    @NotBlank
    private String timeStamp;
}
