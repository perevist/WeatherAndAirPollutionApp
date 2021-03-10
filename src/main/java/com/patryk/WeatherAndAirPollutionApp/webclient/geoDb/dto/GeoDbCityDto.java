package com.patryk.WeatherAndAirPollutionApp.webclient.geoDb.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GeoDbCityDto {
    private List<GeoDbDataDto> data;

    @Override
    public String toString() {
        return "GeoDbCitiesCityDto{" +
                "data=" + data +
                '}';
    }
}
