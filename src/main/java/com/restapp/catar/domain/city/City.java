package com.restapp.catar.domain.city;

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
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "name")
    private String name;

    @Column(name = "airport")
    private String airport;

    @Column(name = "weather")
    private Weather weather;

    @Column(name = "teperature")
    private double teperature;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "city",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Rent> rentsForTheCity = new ArrayList<>();


}
