package com.patryk.WeatherAndAirPollutionApp.webclient.openweather.dto;

import com.patryk.WeatherAndAirPollutionApp.model.CityDto;
import com.patryk.WeatherAndAirPollutionApp.webclient.openweather.dto.airpollution.OpenWeatherAirPollutionDto;
import com.patryk.WeatherAndAirPollutionApp.webclient.openweather.dto.city.OpenWeatherCityDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherClient {

    private final static String WEATHER_URL = "http://api.openweathermap.org/data/2.5/";
    @Value("${openweather.api.key}")
    private String API_KEY;

    private RestTemplate restTemplate = new RestTemplate();

    public CityDto getDataForCity(String cityName) {
        OpenWeatherCityDto openWeatherCityDto = callGetMethod("weather?q={cityName}&appid={apiKey}&units=metric&lang=pl",
                OpenWeatherCityDto.class,
                cityName,
                API_KEY
        );

        float lon = openWeatherCityDto.getCoord().getLon();
        float lat = openWeatherCityDto.getCoord().getLat();

        OpenWeatherAirPollutionDto openWeatherAirPollutionDto = callGetMethod("air_pollution?lon={lon}&lat={lat}&appid={apiKey}",
                OpenWeatherAirPollutionDto.class,
                lon,
                lat,
                API_KEY
        );

        CityDto cityDto = CityDto.builder()
                .name(cityName)
                .lon(openWeatherCityDto.getCoord().getLon())
                .lat(openWeatherCityDto.getCoord().getLat())
                .temperature(openWeatherCityDto.getMain().getTemp())
                .pressure(openWeatherCityDto.getMain().getPressure())
                .humidity(openWeatherCityDto.getMain().getHumidity())
                .windSpeed(openWeatherCityDto.getWind().getSpeed())
                .airQuality(openWeatherAirPollutionDto.getList().get(0).getMain().getAqi())
                .build();
        return cityDto;
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(WEATHER_URL + url,
                responseType, objects);
    }
}
