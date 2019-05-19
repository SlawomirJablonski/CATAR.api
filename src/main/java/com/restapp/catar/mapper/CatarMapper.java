package com.restapp.catar.mapper;

import com.restapp.catar.domain.city.City;
import com.restapp.catar.domain.city.CityDto;
import com.restapp.catar.domain.rent.Rent;
import com.restapp.catar.domain.rent.RentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CatarMapper {

    public Rent mapToRent(RentDto rentDto) {
        return new Rent.RentBuilder()
                .driver(rentDto.getDriver())
                .car(rentDto.getCar())
                .city(rentDto.getCity())
                .fromDate(rentDto.getFromDate())
                .tillDate(rentDto.getTillDate())
                .paid(true)
                .build();
    }

    public List<RentDto> mapToRentDtoList(List<Rent> rents) {
        return rents.stream()
                .map(rent -> new RentDto.RentDtoBuilder()
                        .rentId(rent.getRentId())
                        .driver(rent.getDriver())
                        .car(rent.getCar())
                        .city(rent.getCity())
                        .fromDate(rent.getFromDate())
                        .tillDate(rent.getTillDate())
                        .build())
                .collect(Collectors.toList());
    }

    public CityDto mapToCityDto(City city){
        return new CityDto.CityDtoBuilder()
                .cityId(city.getCityId())
                .name(city.getName())
                .airport(city.getAirport())
                .weather(city.getWeather())
                .temperature(city.getTemperature())
                .build();

    }
}