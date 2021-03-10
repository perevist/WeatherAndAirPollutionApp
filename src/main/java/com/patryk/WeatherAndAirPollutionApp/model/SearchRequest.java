package com.patryk.WeatherAndAirPollutionApp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {
    private String name;
    private String type;
}
