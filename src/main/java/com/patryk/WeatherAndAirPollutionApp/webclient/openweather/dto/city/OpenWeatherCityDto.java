package com.patryk.WeatherAndAirPollutionApp.webclient.openweather.dto.city;

import lombok.Getter;

@Getter
public class OpenWeatherCityDto {
    private OpenWeatherCoordDto coord;
    private OpenWeatherMainDto main;
    private OpenWeatherWindDto wind;
}
