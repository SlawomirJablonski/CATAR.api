package com.restapp.catar.controller;

import com.restapp.catar.domain.city.City;
import com.restapp.catar.domain.city.CityDto;
import com.restapp.catar.domain.city.Weather;
import com.restapp.catar.domain.rent.Rent;
import com.restapp.catar.domain.rent.RentDto;
import com.restapp.catar.domain.weather.client.WeatherClient;
import com.restapp.catar.exception.CityByNameNotFoundException;
import com.restapp.catar.exception.CityNotFoundException;
import com.restapp.catar.mapper.CatarMapper;
import com.restapp.catar.service.CatarService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/catar")
public class CatarController {

    private static final String WARSAW = "location/523920/";
    private static final String BERLIN = "location/638242/";
    private static final String PARIS = "location/615702/";

    private final CatarService catarService;
    private final CatarMapper catarMapper;
    private final WeatherController weatherController;

    public CatarController(CatarService catarService, CatarMapper catarMapper, WeatherController weatherController) {
        this.catarService = catarService;
        this.catarMapper = catarMapper;
        this.weatherController = weatherController;
    }

    @GetMapping(value = "/rents")
    public List<RentDto> getRents() {
        return catarMapper.mapToRentDtoList(catarService.getRents());
    }

    @PostMapping(value = "/rents", consumes = APPLICATION_JSON_VALUE)
    public void createRent(@RequestBody RentDto rentDto){
        catarService.saveRent(catarMapper.mapToRent(rentDto));
    }

    @GetMapping(value = "/cities")
    public List<City> getCities() {
        return catarService.getCities();
    }

    @GetMapping(value = "/cities/{cityName}")
    public CityDto getCity(@RequestParam String cityName)throws CityNotFoundException {
        return catarMapper.mapToCityDto(catarService.getCityByName(cityName).orElseThrow(() -> new CityByNameNotFoundException(cityName)));
    }

    @PostMapping("/cities")
    public void createCity(@RequestBody City city){
        catarService.saveCity(city);
    }

    @PostMapping("/mycities")
    public void createCity(){

        City wa = new City.CityBuilder()
                .name("Warsaw")
                .airport("(WAW) Chopin Airport")
                .build();
        City be = new City.CityBuilder()
                .name("Berlin")
                .airport("(SXF) Schonefeld Airport")
                .build();
        City pa = new City.CityBuilder()
                .name("Paris")
                .airport("(CDG) Charles de Gaulle")
                .build();

        catarService.saveCity(wa);
        catarService.saveCity(be);
        catarService.saveCity(pa);
    }

    @PutMapping("/updateCity")
    public CityDto updateCity(@RequestBody CityDto cityDto)throws CityNotFoundException {
        City city = catarService.getCityById(cityDto.getCityId())
                .orElseThrow(() -> new CityNotFoundException(cityDto.getCityId()));
        city.setName(cityDto.getName());
        //

        catarService.saveCity(city);
        return catarMapper.mapToCityDto(city);
    }

    @PutMapping("/updateCityWeather")
    public void updateCityWeather(@RequestParam String city){
        double currentTemperature = weatherController.getWeather(determineLocation(city)).get(0).getTempOfDay();
        String currentWeatherDescription = weatherController.getWeather(determineLocation(city)).get(0).getWeatherDescription();
        City theCity = catarService.getCityByName(city).orElseThrow(()->new CityByNameNotFoundException(city));
        theCity.setTemperature(currentTemperature);
        theCity.setWeather(currentWeatherDescription);
        catarService.saveCity(theCity);
    }

    @PutMapping("/updateMyCitiesWeather")
    public void updateMyCitiesWeather(){
        updateCityWeather("Warsaw");
        updateCityWeather("Berlin");
        updateCityWeather("Paris");
    }

    private String determineLocation(String city){
        String location="";
        switch (city) {
            case "Warsaw": location= "location/523920/";
                break;
            case "Berlin": location= "location/638242/";
                break;
            case "Paris": location= "location/615702/";
                break;
        }
        return location;
    }

}
