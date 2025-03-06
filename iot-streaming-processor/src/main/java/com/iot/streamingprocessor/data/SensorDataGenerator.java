package com.iot.streamingprocessor.data;

import com.iot.streamingprocessor.constants.Constants;
import com.iot.streamingprocessor.model.MetricType;
import com.iot.streamingprocessor.model.SensorData;
import com.iot.streamingprocessor.service.StreamDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

    /*
    SensorDataGenerator will generate simulated data for THERMOSTAT HEART_RATE_METER FUEL_READING sensors
     */
@Slf4j
@Component
@RequiredArgsConstructor
public class SensorDataGenerator {

    private final StreamDataService streamDataService;
    private static final String[] SENSOR_NAMES = Constants.SENSOR_NAMES;
    private static final Random RANDOM = new Random();

    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    ScheduledFuture<?> task;

    private void generateData() {
        String sensorName = SENSOR_NAMES[RANDOM.nextInt(SENSOR_NAMES.length)];
        MetricType metric = generateMetricType(sensorName);
        Double metricValue = generateMetricValue(metric);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String timestamp = LocalDateTime.now().format(format);

        SensorData sensorData = SensorData.builder()
                .sensorName(sensorName)
                .metricType(metric)
                .metricValue(metricValue)
                .timeStamp(timestamp)
                .build();

        streamDataService.publish(sensorData);
    }

    private MetricType generateMetricType(String sensorName) {
        return switch (sensorName) {
            case "HEART_RATE_METER" -> MetricType.HEART_RATE;
            case "FUEL_READING" -> MetricType.FUEL_LEVEL;
            case "THERMOSTAT" -> MetricType.TEMPERATURE;
            default -> throw new IllegalStateException("Unexpected value: " + sensorName);
        };
    }

    private Double generateMetricValue(MetricType metric) {
        return switch (metric) {
            case TEMPERATURE -> generateTemperature();
            case HEART_RATE -> (double) generateHeartRate();
            case FUEL_LEVEL -> (double) generateFuelLevel();
        };
    }

    private double generateTemperature() {
        // Random value between 0 and 100 degrees Celsius
        return RANDOM.nextDouble() * 100;
    }

    private int generateHeartRate() {
        // Random value between 60 and 120 beats per minute
        return RANDOM.nextInt(61) + 60;
    }

    private int generateFuelLevel() {
        // Random value between 0 and 100
        return RANDOM.nextInt(101);
    }


    public synchronized void startStreamGenerator() {
        task = scheduledExecutorService.scheduleAtFixedRate(
                this::generateData, 0, 1000, TimeUnit.MILLISECONDS);

        log.info("Stream Data Generator task scheduled successfully.");
    }

    public synchronized void stopStreamGenerator() {
        if (task != null) {
            task.cancel(false);
            log.info("Stream Data Generator task stopped successfully.");
        }
    }

}
