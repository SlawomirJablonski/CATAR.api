package com.restapp.catar.domain.city;

import com.restapp.catar.domain.rent.Rent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "city")
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

    @Column(name = "temperature")
    private double temperature;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "city",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Rent> rentsForTheCity = new ArrayList<>();

    public static class CityBuilder{
        private String name;
        private String airport;
        private Weather weather;
        private double temperature;
        private List<Rent> rentsForTheCity = new ArrayList<>();;

        public CityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CityBuilder airport(String airport) {
            this.airport = airport;
            return this;
        }

        public CityBuilder weather(Weather weather) {
            this.weather = weather;
            return this;
        }

        public CityBuilder temperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public CityBuilder rentsForTheCity(Rent rent) {
            rentsForTheCity.add(rent);
            return this;
        }

        public City build(){
            return new City(name,airport,weather,temperature,rentsForTheCity);
        }
    }

    private City(String name, String airport, Weather weather, double temperature, List<Rent> rentsForTheCity) {
        this.name = name;
        this.airport = airport;
        this.weather = weather;
        this.temperature = temperature;
        this.rentsForTheCity = rentsForTheCity;
    }
}
