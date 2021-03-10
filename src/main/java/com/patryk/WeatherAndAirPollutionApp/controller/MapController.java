package com.patryk.WeatherAndAirPollutionApp.controller;

import com.patryk.WeatherAndAirPollutionApp.model.SearchRequest;
import com.patryk.WeatherAndAirPollutionApp.model.CityDto;
import com.patryk.WeatherAndAirPollutionApp.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MapController {

    private final CityService cityService;

    @GetMapping
    public String getMainPage(Model model) {
        model.addAttribute("searchRequest", new SearchRequest());
        return "map";
    }

    @PostMapping
    public String search(@ModelAttribute("searchRequest") SearchRequest searchRequest, Model model) {
        List<CityDto> cities = cityService.getData(searchRequest);
        model.addAttribute("cities", cities);
        model.addAttribute("searchRequest", new SearchRequest());
        return "map";
    }
}
