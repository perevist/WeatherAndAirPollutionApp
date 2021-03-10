package com.patryk.WeatherAndAirPollutionApp.service;

import com.neovisionaries.i18n.CountryCode;
import com.patryk.WeatherAndAirPollutionApp.SearchRequest;
import com.patryk.WeatherAndAirPollutionApp.model.CityDto;
import com.patryk.WeatherAndAirPollutionApp.webclient.geoDb.GeoDbClient;
import com.patryk.WeatherAndAirPollutionApp.webclient.openweather.dto.OpenWeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final OpenWeatherClient openWeatherClient;
    private final GeoDbClient geoDbClient;

    public List<CityDto> getData(SearchRequest searchRequest) {
        List<CityDto> cityDtos = new ArrayList<>();
        String name = searchRequest.getName();
        String type = searchRequest.getType();

        if (type.equals("country")) {
            String countryCode = CountryCode.findByName(name).get(0).name();
            HashSet<String> cityNames = geoDbClient.getCityNamesInCountry(countryCode);
            cityNames.forEach(cityName -> {
                CityDto cityDto = getCityDto(cityName);
                cityDtos.add(cityDto);
            });
        } else {
            cityDtos.add(getCityDto(name));
        }
        return cityDtos;
    }

    private CityDto getCityDto(String cityName) {
        return openWeatherClient.getDataForCity(cityName);
    }
}
