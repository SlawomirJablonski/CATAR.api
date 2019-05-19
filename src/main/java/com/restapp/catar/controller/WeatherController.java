package com.restapp.catar.controller;

import com.restapp.catar.domain.weather.OneDayWeather;
import com.restapp.catar.domain.weather.client.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

    private WeatherClient weatherClient;

    @Autowired
    public WeatherController(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @GetMapping(value = "/getWeather")
    public List<OneDayWeather> getWeather(@RequestParam String city) {

        return weatherClient.getConsolidatedWeather(city).getConsolidatedWeather();

        //return weatherClient.getConsolidatedWeatherForWarsaw().getConsolidatedWeather();

        /*for (OneDayWeather w:lists) {
            System.out.println(w.getWeatherDate()+", "+w.getTempOfDay());

        }*/

    }

}
