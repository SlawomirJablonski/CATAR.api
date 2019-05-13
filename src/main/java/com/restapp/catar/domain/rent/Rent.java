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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rent")
public class Rent {

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

    @Column(name = "from")
    private LocalDateTime fromDate;

    @Column(name = "till")
    private LocalDateTime tillDate;

    @Column(name = "is_paid")
    private boolean paid;

}
