package com.patryk.WeatherAndAirPollutionApp.webclient.openweather.dto.city;

import lombok.Getter;

@Getter
public class OpenWeatherMainDto {
    private float temp;
    private int pressure;
    private int humidity;
}
