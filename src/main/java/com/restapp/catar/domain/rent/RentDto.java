package com.restapp.catar.domain.rent;

import com.restapp.catar.domain.car.Car;
import com.restapp.catar.domain.city.City;
import com.restapp.catar.domain.driver.Driver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
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
}
