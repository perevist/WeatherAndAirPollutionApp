package com.patryk.WeatherAndAirPollutionApp.webclient.openweather.dto.airpollution;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OpenWeatherAirPollutionDto {
    private List<OpenWeatherListDto> list = new ArrayList<>();
}
