package com.patryk.WeatherAndAirPollutionApp.controller;

import com.patryk.WeatherAndAirPollutionApp.SearchRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MapController {

    @GetMapping
    public String getMainPage(Model model) {
        model.addAttribute("searchRequest", new SearchRequest());
        return "map";
    }

    @PostMapping
    public String search(@ModelAttribute("searchRequest") SearchRequest searchRequest, Model model) {
        System.out.println(searchRequest.getName());
        System.out.println(searchRequest.getType());
        model.addAttribute("searchRequest", new SearchRequest());
        return "map";
    }
}
