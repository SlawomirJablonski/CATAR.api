package com.restapp.catar.domain.weather.client;

import com.restapp.catar.domain.weather.ConsolidatedWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Component
public class WeatherClient {

    private static final String WARSAW = "location/523920/";

    private final String weatherApiEndpoint;

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherClient(@Value("${weather.api}") String weatherApiEndpoint, RestTemplate restTemplate) {
        this.weatherApiEndpoint = weatherApiEndpoint;
        this.restTemplate = restTemplate;
    }

    public ConsolidatedWeather getConsolidatedWeatherForWarsaw(){
        URI url = buildWarsawUrl();

        try{
            ConsolidatedWeather apiResponse = restTemplate.getForObject(
                    url,ConsolidatedWeather.class);
            return apiResponse;
        }catch(RuntimeException e){
            //zmien na logger
            System.out.println("type of RestClientResponseException: " + e);
            return new ConsolidatedWeather();
        }
    }

    private URI buildWarsawUrl(){
        return UriComponentsBuilder.fromHttpUrl(weatherApiEndpoint + WARSAW)
                .build().encode().toUri();
    }
}
