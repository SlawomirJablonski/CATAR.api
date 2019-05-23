package com.restapp.catar.domain.rent;

import com.restapp.catar.domain.car.Car;
import com.restapp.catar.domain.city.City;
import com.restapp.catar.domain.driver.Driver;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@Entity(name = "rent")
public class BasicRent implements Rent{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long rentId;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "fromDate")
    private LocalDateTime fromDate;

    @Column(name = "tillDate")
    private LocalDateTime tillDate;

    @Column(name = "is_paid")
    private boolean paid;

    @Column(name = "rentCost")
    private BigDecimal rentCost;

    @Column(name = "rentOptions")
    private String rentOptions;

    @Override
    public BigDecimal getCost() {
        return new BigDecimal(150);
    }

    @Override
    public String getOptions() {
        return "";
    }

    public static class RentBuilder{
        private Driver driver;
        private Car car;
        private City city;
        private LocalDateTime fromDate;
        private LocalDateTime tillDate;
        private boolean paid;
        private BigDecimal rentCost;
        private String rentOptions;

        public RentBuilder driver(Driver driver){
            this.driver=driver;
            return this;
        }
        public RentBuilder car(Car car){
            this.car=car;
            return this;
        }
        public RentBuilder city(City city){
            this.city=city;
            return this;
        }
        public RentBuilder fromDate(LocalDateTime fromDate){
            this.fromDate=fromDate;
            return this;
        }
        public RentBuilder tillDate(LocalDateTime tillDate){
            this.tillDate=tillDate;
            return this;
        }
        public RentBuilder paid(boolean paid){
            this.paid=paid;
            return this;
        }

        public RentBuilder rentCost(BigDecimal rentCost){
            this.rentCost=rentCost;
            return this;
        }

        public RentBuilder rentOptions(String rentOptions){
            this.rentOptions=rentOptions;
            return this;
        }

        public BasicRent build(){
            return new BasicRent(driver, car, city, fromDate, tillDate, paid, rentCost, rentOptions);
        }

    }

    private BasicRent(Driver driver, Car car, City city, LocalDateTime fromDate,
                      LocalDateTime tillDate, boolean paid, BigDecimal rentCost, String rentOptions) {
        this.driver = driver;
        this.car = car;
        this.city = city;
        this.fromDate = fromDate;
        this.tillDate = tillDate;
        this.paid = paid;
        this.rentCost = rentCost;
        this.rentOptions = rentOptions;
    }
}
