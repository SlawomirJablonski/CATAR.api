package com.restapp.catar.domain.car;

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
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id")
    private Long carId;

    @Column(name = "name")
    public String name;

    @Column(name = "seats")
    private int seatsQty;

    @Column(name = "doors")
    private int doorsQty;

    @Column(name = "laguage")
    private Laguage laguage;

    @Column(name = "air_condition")
    private boolean airCondition;

    @Column(name = "gear_box")
    private GearBox gearBox;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "car",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Rent> productsInGroup = new ArrayList<>();

    //Wyposażenie dodatkowe

}
