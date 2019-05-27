package com.restapp.catar.controller;

import com.restapp.catar.domain.city.City;
import com.restapp.catar.domain.city.CityDto;
import com.restapp.catar.domain.driver.Driver;
import com.restapp.catar.domain.driver.DriverDto;
import com.restapp.catar.domain.driver.TokenService;
import com.restapp.catar.domain.rent.*;
import com.restapp.catar.exception.CityByNameNotFoundException;
import com.restapp.catar.exception.CityNotFoundException;
import com.restapp.catar.exception.DriverNotFoundException;
import com.restapp.catar.exception.RentNotFoundException;
import com.restapp.catar.mapper.CatarMapper;
import com.restapp.catar.service.CatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/catar")
public class CatarController {

    private final CatarService catarService;
    private final CatarMapper catarMapper;
    private final WeatherController weatherController;
    private final TokenService tokenService;

    @Autowired
    public CatarController(CatarService catarService, CatarMapper catarMapper, WeatherController weatherController, TokenService tokenService) {
        this.catarService = catarService;
        this.catarMapper = catarMapper;
        this.weatherController = weatherController;
        this.tokenService = tokenService;
    }

    @GetMapping(value = "/rents")
    public List<BasicRentDto> getRents() {
        return catarMapper.mapToRentDtoList(catarService.getRents());
    }

    @PostMapping(value = "/rents", consumes = APPLICATION_JSON_VALUE)
    public void createRent(@RequestBody BasicRentDto basicRentDto){
        catarService.saveRent(catarMapper.mapToRent(basicRentDto));
    }

    @PutMapping("/rents/{rent}{option}")
    public BasicRentDto updateRent(@RequestBody BasicRentDto basicRentDto, @RequestParam String option)throws RentNotFoundException {
        BasicRent foundRent = catarService.getRentById(basicRentDto.getRentId())
                .orElseThrow(() -> new RentNotFoundException(basicRentDto.getRentId()));

        Rent theRent = new BasicRent.RentBuilder()
                .driver(foundRent.getDriver())
                .car(foundRent.getCar())
                .fromDate(foundRent.getFromDate())
                .tillDate(foundRent.getTillDate())
                .paid(foundRent.isPaid())
                .build();

        switch (option) {
            case "child":
                theRent = new BasicRentChildDecorator(theRent);
            case "driver":
                theRent = new BasicRentDriverDecorator(theRent);
            case "gps":
                theRent = new BasicRentGpsDecorator(theRent);
            case "booster":
                theRent = new BasicRentBoosterDecorator(theRent);
            case "baby":
                theRent = new BasicRentBabyDecorator(theRent);
        }

        BigDecimal newCost = theRent.getCost();
        String newOptions = theRent.getOptions();
        foundRent.setRentCost(newCost);
        foundRent.setRentOptions(newOptions);
        catarService.saveRent(foundRent);

        return catarMapper.mapToRentDto(foundRent);
    }

    @GetMapping(value = "/cities")
    public List<City> getCities() {
        return catarService.getCities();
    }

    @GetMapping(value = "/cities/{city}")
    public CityDto getCity(@RequestParam String city)throws CityNotFoundException {
        return catarMapper.mapToCityDto(catarService.getCityByName(city).orElseThrow(() -> new CityByNameNotFoundException(city)));
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

    @PutMapping("/cities")
    public CityDto updateCity(@RequestBody CityDto cityDto)throws CityNotFoundException {
        City city = catarService.getCityById(cityDto.getCityId())
                .orElseThrow(() -> new CityNotFoundException(cityDto.getCityId()));
        city.setName(cityDto.getName());
        //

        catarService.saveCity(city);
        return catarMapper.mapToCityDto(city);
    }

    @PutMapping("/cities/weather")
    public void updateCityWeather(@RequestParam String city){
        double currentTemperature = weatherController.getWeather(determineLocation(city)).get(0).getTempOfDay();
        String currentWeatherDescription = weatherController.getWeather(determineLocation(city)).get(0).getWeatherDescription();
        City theCity = catarService.getCityByName(city).orElseThrow(()->new CityByNameNotFoundException(city));
        theCity.setTemperature(currentTemperature);
        theCity.setWeather(currentWeatherDescription);
        catarService.saveCity(theCity);
    }

    @PutMapping("/mycities/weather")
    public void updateMyCitiesWeather(){
        updateCityWeather("Warsaw");
        updateCityWeather("Berlin");
        updateCityWeather("Paris");
    }

    @PostMapping("/drivers")
    public void createDriver(@RequestBody DriverDto driverDto) {
        catarService.saveDriver(catarMapper.mapToDriver(driverDto));
    }

    @PutMapping(value = "generateToken")
    public void generateToken(@RequestParam Long driverId) throws DriverNotFoundException, ExecutionException {
        Driver theDriver = catarService.getDriverById(driverId).orElseThrow(()->new DriverNotFoundException(driverId));
        theDriver.setToken(tokenService.getDriverCache().get(theDriver));
    }

    private String determineLocation(String city){
        switch (city) {
            case "Warsaw":
                return City.WARSAW;
            case "Berlin":
                return City.BERLIN;
            case "Paris":
                return City.PARIS;
        }
        return "";
    }
}
