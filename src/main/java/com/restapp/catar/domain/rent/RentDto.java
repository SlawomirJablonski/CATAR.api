package com.restapp.catar.domain.rent;

import com.restapp.catar.domain.car.Car;
import com.restapp.catar.domain.city.City;
import com.restapp.catar.domain.driver.Driver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
public class RentDto {
    private Long rentId;
    private Driver driver;
    private Car car;
    private City city;
    private LocalDateTime fromDate;
    private LocalDateTime tillDate;
    //private boolean paid;


    public RentDto(Long rentId, Driver driver, Car car, City city, LocalDateTime fromDate, LocalDateTime tillDate) {
        this.rentId = rentId;
        this.driver = driver;
        this.car = car;
        this.city = city;
        this.fromDate = fromDate;
        this.tillDate = tillDate;
    }

    public Long getRentId() {
        return rentId;
    }

    public Driver getDriver() {
        return driver;
    }

    public Car getCar() {
        return car;
    }

    public City getCity() {
        return city;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getTillDate() {
        return tillDate;
    }
}