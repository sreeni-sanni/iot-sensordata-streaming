package com.iot.streamingdataapi.constants;

import java.util.Set;

public class Constants {
    public static final Set<String> SENSOR_NAMES = Set.of("THERMOSTAT", "HEART_RATE_METER", "FUEL_READING");
    public static final String ALGORITHM = "HmacSHA256";
    public static final String ROLES = "roles";
    public static final String INVALID_JWT_TOKEN="Invalid JWT token";
    public static final String TOKEN_EXPIRED="JWT token has expired";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER="Bearer ";
    public static final String TOKEN_MISSING = "Authorization code is missing in the request";
    public static final String AUTH_TOKEN = "/auth/token";
    public static final String SWAGGER_UI="/swagger-ui";
    public static final String SWAGGER_V3_API="/v3/api-docs";
    public static final String USER_NOT_FOUND="The specified username was not found. Please check the username and try again.";
    public static final String SENSOR_NAME ="sensorName";
    public static final String METRIC_VALUE ="metricValue";
    public static final String VALUE="value";
    public static final String TIMESTAMP="timeStamp";
    public static final String SENSORDATA="sensorData";
    public static final String ID="_id";
    public static final String MINVALUE="minValue";
    public static final String MIXVALUE="maxValue";
    public static final String AVGVALUE="avgValue";
    public static final String MEDIANVALUE="medianValue";
    public static final String DATETIME_EXCEPTION_MESSAGE="Start date should be before end date";
    public static final String DATETIME_FORMAT="yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String EXCEPTION_OCCURRED="Exception occurred: {}";
    public static final String ACCESS_DENIED = "You do not have the required permissions to access this resource.";
}
