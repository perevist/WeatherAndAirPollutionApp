package com.patryk.WeatherAndAirPollutionApp.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CityDto {
    private String name;
    private float lon;
    private float lat;
    private float temperature;
    private int pressure;
    private int humidity;
    private float windSpeed;
    private int airQuality;
}
