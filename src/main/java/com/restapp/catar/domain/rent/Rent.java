package com.restapp.catar.domain.rent;

import com.restapp.catar.domain.car.Car;
import com.restapp.catar.domain.city.City;
import com.restapp.catar.domain.driver.Driver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rent")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long rentId;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    public Driver driver;

    @ManyToOne
    @JoinColumn(name = "car_id")
    public Car car;

    @ManyToOne
    @JoinColumn(name = "city_id")
    public City city;

    @Column(name = "fromDate")
    public LocalDateTime fromDate;

    @Column(name = "tillDate")
    public LocalDateTime tillDate;

    @Column(name = "is_paid")
    public boolean paid;

    public Rent(Driver driver, Car car, City city, LocalDateTime fromDate, LocalDateTime tillDate, boolean paid) {
        this.driver = driver;
        this.car = car;
        this.city = city;
        this.fromDate = fromDate;
        this.tillDate = tillDate;
        this.paid = paid;
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

    public boolean isPaid() {
        return paid;
    }
}
