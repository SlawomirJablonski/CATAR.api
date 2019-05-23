package com.restapp.catar.service;

import com.restapp.catar.domain.city.City;
import com.restapp.catar.domain.driver.Driver;
import com.restapp.catar.domain.rent.BasicRent;
import com.restapp.catar.repository.CityRepository;
import com.restapp.catar.repository.DriverRepository;
import com.restapp.catar.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatarService {

    private final RentRepository rentRepository;
    private final CityRepository cityRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public CatarService(RentRepository rentRepository, CityRepository cityRepository, DriverRepository driverRepository) {
        this.rentRepository = rentRepository;
        this.cityRepository = cityRepository;
        this.driverRepository = driverRepository;
    }

    public Optional<City> getCityById(final Long id){
        return cityRepository.findById(id);
    }

    public List<City> getCities(){
        return cityRepository.findAll();
    }

    public City saveCity(final City city){return cityRepository.save(city);}

    public Optional<BasicRent> getRentById(final Long id){
        return rentRepository.findById(id);
    }

    public List<BasicRent> getRents() {
        return rentRepository.findAll();
    }

    public BasicRent saveRent(final BasicRent basicRent){
        return rentRepository.save(basicRent);
    }

    public Optional<City> getCityByName(final String name){
        return cityRepository.findByName(name);
    }

    public Optional<Driver> getDriverById(final Long id){
        return driverRepository.findById(id);
    }

    public Driver saveDriver(final Driver driver){
        return driverRepository.save(driver);
    }

}
