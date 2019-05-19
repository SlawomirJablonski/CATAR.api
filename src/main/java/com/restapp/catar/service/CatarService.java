package com.restapp.catar.service;

import com.restapp.catar.domain.city.City;
import com.restapp.catar.domain.rent.Rent;
import com.restapp.catar.repository.CityRepository;
import com.restapp.catar.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatarService {

    private final RentRepository rentRepository;
    private final CityRepository cityRepository;

    @Autowired
    public CatarService(RentRepository rentRepository, CityRepository cityRepository) {
        this.rentRepository = rentRepository;
        this.cityRepository = cityRepository;
    }

    public Optional<City> getCityById(final Long id){
        return cityRepository.findById(id);
    }

    public List<City> getCities(){
        return cityRepository.findAll();
    }

    public City saveCity(final City city){return cityRepository.save(city);}

    public List<Rent> getRents() {
        return rentRepository.findAll();
    }

    public Rent saveRent(final Rent rent){
        return rentRepository.save(rent);
    }

    public Optional<City> getCityByName(final String name){
        return cityRepository.findByName(name);
    }


}
