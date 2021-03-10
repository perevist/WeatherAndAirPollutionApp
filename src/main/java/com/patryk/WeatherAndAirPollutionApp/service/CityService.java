package com.patryk.WeatherAndAirPollutionApp.service;

import com.patryk.WeatherAndAirPollutionApp.SearchRequest;
import com.patryk.WeatherAndAirPollutionApp.model.CityDto;
import com.patryk.WeatherAndAirPollutionApp.webclient.openweather.dto.OpenWeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final OpenWeatherClient openWeatherClient;

    public List<CityDto> getData(SearchRequest searchRequest) {
        List<CityDto> cityDtos = new ArrayList<>();
        String name = searchRequest.getName();
        String type = searchRequest.getType();

        CityDto cityDto = getCityDto(name);
        cityDtos.add(cityDto);

        return cityDtos;
    }

    private CityDto getCityDto(String cityName) {
        return openWeatherClient.getDataForCity(cityName);
    }
}
