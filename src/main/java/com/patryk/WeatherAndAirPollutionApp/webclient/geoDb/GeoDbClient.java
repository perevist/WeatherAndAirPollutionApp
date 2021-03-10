package com.patryk.WeatherAndAirPollutionApp.webclient.geoDb;

import com.patryk.WeatherAndAirPollutionApp.webclient.geoDb.dto.GeoDbCityDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class GeoDbClient {

    private final static String GEO_DB_URL = "https://wft-geo-db.p.rapidapi.com/v1/geo/";
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${geo.db.api.key}")
    private String apiKey;

    public HashSet<String> getCityNamesInCountry(String countryCode) {
        String url = "cities?limit=10&countryIds=" + countryCode + "&minPopulation=250000&sort=-population&types=CITY";

        GeoDbCityDto geoDbCityDto = callGetMethod(url, GeoDbCityDto.class);
        HashSet<String> cityNames = geoDbCityDto.getData().stream()
                .map(value -> value.getCity())
                .collect(Collectors.toCollection(HashSet::new));

        return cityNames;
    }

    private <T> T callGetMethod(String url, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-rapidapi-key", apiKey);
        headers.set("x-rapidapi-host", "wft-geo-db.p.rapidapi.com");
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange(GEO_DB_URL + url, HttpMethod.GET, entity, responseType).getBody();
    }
}
