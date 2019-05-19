package com.restapp.catar.domain.weather.client;

import com.restapp.catar.domain.weather.ConsolidatedWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class WeatherClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherClient.class);
    private static final String WARSAW = "location/523920/";
    private static final String BERLIN = "location/638242/";
    private static final String PARIS = "location/615702/";

    private final String weatherApiEndpoint;
    private final RestTemplate restTemplate;

    @Autowired
    public WeatherClient(@Value("${weather.api}") String weatherApiEndpoint, RestTemplate restTemplate) {
        this.weatherApiEndpoint = weatherApiEndpoint;
        this.restTemplate = restTemplate;
    }

    public ConsolidatedWeather getConsolidatedWeather(String city){

        URI url = buildWarsawUrl(city);

        try{
            return restTemplate.getForObject(
                    url,ConsolidatedWeather.class);
        }catch(RuntimeException e){
            LOGGER.error(e.getMessage(), e);
            return new ConsolidatedWeather();
        }
    }

    private URI buildWarsawUrl(String city){
        return UriComponentsBuilder.fromHttpUrl(weatherApiEndpoint + city)
                .build().encode().toUri();
    }
}