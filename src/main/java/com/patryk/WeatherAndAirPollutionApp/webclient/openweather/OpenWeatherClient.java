package com.patryk.WeatherAndAirPollutionApp.webclient.openweather;

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
    private String apiKey;

    private RestTemplate restTemplate = new RestTemplate();

    public CityDto getDataForCity(String cityName) {
        OpenWeatherCityDto openWeatherCityDto = callGetMethod(
                "weather?q={cityName}&appid={apiKey}&units=metric&lang=pl",
                OpenWeatherCityDto.class,
                cityName,
                apiKey
        );

        float lon = openWeatherCityDto.getCoord().getLon();
        float lat = openWeatherCityDto.getCoord().getLat();

        OpenWeatherAirPollutionDto openWeatherAirPollutionDto = callGetMethod(
                "air_pollution?lon={lon}&lat={lat}&appid={apiKey}",
                OpenWeatherAirPollutionDto.class,
                lon,
                lat,
                apiKey
        );

        CityDto cityDto = mapDataFromOpenWeatherToCityDto(cityName, openWeatherCityDto, openWeatherAirPollutionDto);
        return cityDto;
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(WEATHER_URL + url,
                responseType, objects);
    }

    private CityDto mapDataFromOpenWeatherToCityDto(String cityName,
                                                    OpenWeatherCityDto cityDto,
                                                    OpenWeatherAirPollutionDto airPollutionDto) {
        return CityDto.builder()
                .name(cityName)
                .lon(cityDto.getCoord().getLon())
                .lat(cityDto.getCoord().getLat())
                .temperature(cityDto.getMain().getTemp())
                .pressure(cityDto.getMain().getPressure())
                .humidity(cityDto.getMain().getHumidity())
                .windSpeed(cityDto.getWind().getSpeed())
                .airQuality(airPollutionDto.getList().get(0).getMain().getAqi())
                .build();
    }
}
