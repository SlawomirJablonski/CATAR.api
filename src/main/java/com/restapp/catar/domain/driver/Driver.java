package com.restapp.catar.domain.driver;

import com.restapp.catar.domain.rent.Rent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "driver")
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

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "driver",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Rent> rentsForTheDriver = new ArrayList<>();
}
