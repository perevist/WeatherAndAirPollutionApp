package com.patryk.WeatherAndAirPollutionApp.webclient.geoDb.dto;

import lombok.Getter;

@Getter
public class GeoDbDataDto {
    private String city;

    @Override
    public String toString() {
        return "GeoDbCitiesDataDto{" +
                "city='" + city + '\'' +
                '}';
    }
}
