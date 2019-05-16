package com.restapp.catar.controler;

import com.restapp.catar.domain.auto.Mark;
import com.restapp.catar.domain.auto.Model;
import com.restapp.catar.domain.auto.client.AutoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/auto")
public class AutoController {

    private static final String VW_MODELS = "59";

    private AutoClient autoClient;

    @Autowired
    public AutoController(AutoClient autoClient) {
        this.autoClient = autoClient;
    }

    @GetMapping(value = "/getModels")
    public void getModels() {

        List<Model> lists = autoClient.getModels(VW_MODELS);

        //return weatherClient.getConsolidatedWeatherForWarsaw().getConsolidatedWeather();

        for (Model m:lists) {
            System.out.println(m.getName());
        }
    }

    @GetMapping(value = "/getMarks")
    public void getMarks() {

        List<Mark> lists = autoClient.getMarks();

        //return weatherClient.getConsolidatedWeatherForWarsaw().getConsolidatedWeather();

        for (Mark mk:lists) {
            System.out.println(mk.getName());
        }
    }
}
