package com.restapp.catar.domain.city;

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
@Entity(name = "city")
public class City {

    private static final String WAW_PORT_1 = "Warsaw Chopin Airport (WAW)";
    private static final String WAW_PORT_2 = "Warsaw Modlin Airport (WMI)";
    private static final String WRO_PORT = "Wroclaw Airport (WRO)";
    private static final String POZ_PORT = "Poznan Airport (POZ)";
    private static final String KRK_PORT = "Krakow Airport (KRK)";
    private static final String KTW_PORT = "Katowice Airport (KTW)";
    private static final String LOD_PORT = "Lodz Airport (LCJ)";
    private static final String GDN_PORT = "Gdansk Lech Walesa Airport (GDN)";
    private static final String BZG_PORT = "Bydgoszcz Airport (BZG)";
    private static final String RZE_PORT = "Rzeszow Airport (RZE)";
    private static final String SZZ_PORT = "Solidarity Szczecin-Goeniow Airport (SZZ)";
    private static final String ZIG_PORT = "Zielona Gora Airport (IEG)";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "name")
    private String name;

    @Column(name = "airport")
    private String airport;

    @Column(name = "weather")
    private String weather;

    @Column(name = "temperature")
    private double temperature;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "city",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
            //fetch = FetchType.LAZY
    )
    private List<Rent> rentsForTheCity = new ArrayList<>();

    public static class CityBuilder{
        private String name;
        private String airport;
        private String weather;
        private double temperature;
        private List<Rent> rentsForTheCity = new ArrayList<>();

        public CityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CityBuilder airport(String airport) {
            this.airport = airport;
            return this;
        }

        public CityBuilder weather(String weather) {
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

    private City(String name, String airport, String weather, double temperature, List<Rent> rentsForTheCity) {
        this.name = name;
        this.airport = airport;
        this.weather = weather;
        this.temperature = temperature;
        this.rentsForTheCity = rentsForTheCity;
    }
}
