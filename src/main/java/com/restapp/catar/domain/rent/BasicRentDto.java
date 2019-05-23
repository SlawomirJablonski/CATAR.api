package com.restapp.catar.domain.rent;

import com.restapp.catar.domain.car.Car;
import com.restapp.catar.domain.city.City;
import com.restapp.catar.domain.driver.Driver;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class BasicRentDto {
    private Long rentId;
    private Driver driver;
    private Car car;
    private City city;
    private LocalDateTime fromDate;
    private LocalDateTime tillDate;

    public static class RentDtoBuilder{
        private Long rentId;
        private Driver driver;
        private Car car;
        private City city;
        private LocalDateTime fromDate;
        private LocalDateTime tillDate;

        public RentDtoBuilder rentId(Long rentId){
            this.rentId=rentId;
            return this;
        }
        public RentDtoBuilder driver(Driver driver){
            this.driver=driver;
            return this;
        }
        public RentDtoBuilder car(Car car){
            this.car=car;
            return this;
        }
        public RentDtoBuilder city(City city){
            this.city=city;
            return this;
        }
        public RentDtoBuilder fromDate(LocalDateTime fromDate){
            this.fromDate=fromDate;
            return this;
        }
        public RentDtoBuilder tillDate(LocalDateTime tillDate){
            this.tillDate=tillDate;
            return this;
        }

        public BasicRentDto build(){
            return new BasicRentDto(rentId, driver, car, city, fromDate, tillDate);
        }
    }

    private BasicRentDto(Long rentId, Driver driver, Car car, City city, LocalDateTime fromDate, LocalDateTime tillDate) {
        this.rentId = rentId;
        this.driver = driver;
        this.car = car;
        this.city = city;
        this.fromDate = fromDate;
        this.tillDate = tillDate;
    }
}