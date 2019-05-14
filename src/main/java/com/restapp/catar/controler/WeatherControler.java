package com.restapp.catar.controler;

import com.restapp.catar.domain.weather.OneDayWeather;
import com.restapp.catar.weather.client.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/weather")
public class WeatherControler {


    private WeatherClient weatherClient;

    @Autowired
    public WeatherControler(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @GetMapping(value = "/getWeather")
    public void getWeather() {

        List<OneDayWeather> lists = weatherClient.getConsolidatedWeatherForWarsaw().getConsolidatedWeather();



        //return weatherClient.getConsolidatedWeatherForWarsaw().getConsolidatedWeather();


        for (OneDayWeather w:lists) {
            System.out.println(w.getWeatherDate()+", "+w.getTempOfDay());

        }

    }

}
