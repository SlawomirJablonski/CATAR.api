package com.restapp.catar.mapper;

import com.restapp.catar.domain.city.City;
import com.restapp.catar.domain.city.CityDto;
import com.restapp.catar.domain.driver.Driver;
import com.restapp.catar.domain.driver.DriverDto;
import com.restapp.catar.domain.rent.BasicRent;
import com.restapp.catar.domain.rent.BasicRentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CatarMapper {

    public BasicRent mapToRent(BasicRentDto basicRentDto) {
        return new BasicRent.RentBuilder()
                .driver(basicRentDto.getDriver())
                .car(basicRentDto.getCar())
                .city(basicRentDto.getCity())
                .fromDate(basicRentDto.getFromDate())
                .tillDate(basicRentDto.getTillDate())
                .paid(true)
                .build();
    }

    /*private Long rentId;
    private Driver driver;
    private Car car;
    private City city;
    private LocalDateTime fromDate;
    private LocalDateTime tillDate;*/

    public BasicRentDto mapToRentDto(BasicRent basicRent){
        return new BasicRentDto.RentDtoBuilder()
                .rentId(basicRent.getRentId())
                .driver(basicRent.getDriver())
                .car(basicRent.getCar())
                .city(basicRent.getCity())
                .fromDate(basicRent.getFromDate())
                .tillDate(basicRent.getTillDate())
                .build();
    }

    public List<BasicRentDto> mapToRentDtoList(List<BasicRent> basicRents) {
        return basicRents.stream()
                .map(rent -> new BasicRentDto.RentDtoBuilder()
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

    public Driver mapToDriver(DriverDto driverDto){
        return new Driver.DriverBuilder()
                .title(driverDto.getTitle())
                .name(driverDto.getName())
                .surName(driverDto.getSurName())
                .email(driverDto.getEmail())
                .build();
    }
    public DriverDto mapToDroverDto(Driver driver){
        return new DriverDto(driver.getTitle(),driver.getName(),driver.getSurName(),driver.getEmail());
    }
}