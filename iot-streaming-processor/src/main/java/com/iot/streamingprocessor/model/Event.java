package com.iot.streamingprocessor.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    @NotBlank
    private String sensorName;
    @NotNull
    private MetricType metricType;
    @NotNull
    private double metricValue;
    @NotBlank
    private String timeStamp;
}
