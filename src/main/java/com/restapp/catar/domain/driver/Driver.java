package com.restapp.catar.domain.driver;

import com.restapp.catar.domain.rent.Rent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "title")
    private Title title;

    @Column(name = "name")
    private String name;

    @Column(name = "surName")
    private String surName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "token")
    private Long token;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "driver",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Rent> rentsForTheDriver = new ArrayList<>();

    public static class DriverBuilder{
        private Title title;
        private String name;
        private String surName;
        private String email;
        private String phone;
        private Long token;
        private List<Rent> rentsForTheDriver = new ArrayList<>();

        public DriverBuilder title(Title title){
            this.title = title;
            return this;
        }
        public DriverBuilder name(String name){
            this.name = name;
            return this;
        }
        public DriverBuilder surName(String surName){
            this.surName = surName;
            return this;
        }
        public DriverBuilder email(String email){
            this.email = email;
            return this;
        }
        public DriverBuilder phone(String phone){
            this.phone = phone;
            return this;
        }
        public DriverBuilder token(Long token){
            this.token = token;
            return this;
        }
        public DriverBuilder rentsForTheDriver(Rent rent){
            rentsForTheDriver.add(rent);
            return this;
        }

        public Driver build(){
            return new Driver(title, name, surName, email, phone, token, rentsForTheDriver);
        }

    }

    private Driver(Title title, String name, String surName, String email, String phone, Long token, List<Rent> rentsForTheDriver) {
        this.title = title;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.phone = phone;
        this.rentsForTheDriver = rentsForTheDriver;
    }
}