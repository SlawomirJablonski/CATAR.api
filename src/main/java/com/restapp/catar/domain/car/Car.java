package com.restapp.catar.domain.car;

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
@Entity(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id")
    private Long carId;

    @Column(name = "name")
    private String name;

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
    private List<Rent> rentsForCar = new ArrayList<>();

    public static class CarBuilder{
        private String name;
        private int seatsQty;
        private int doorsQty;
        private Laguage laguage;
        private boolean airCondition;
        private GearBox gearBox;
        private List<Rent> rentsForCar = new ArrayList<>();

        public CarBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CarBuilder seatsQty(int seatsQty) {
            this.seatsQty = seatsQty;
            return this;
        }

        public CarBuilder doorsQty(int doorsQty) {
            this.doorsQty = doorsQty;
            return this;
        }

        public CarBuilder laguage(Laguage laguage) {
            this.laguage = laguage;
            return this;
        }

        public CarBuilder airCondition(boolean airCondition) {
            this.airCondition = airCondition;
            return this;
        }

        public CarBuilder gearBox(GearBox gearBox) {
            this.gearBox = gearBox;
            return this;
        }

        public CarBuilder rentsForCar(Rent rent) {
            rentsForCar.add(rent);
            return this;
        }

        public Car build() {
            return new Car(name,seatsQty,doorsQty,laguage,airCondition,gearBox,rentsForCar);
        }
    }

    private Car(String name, int seatsQty, int doorsQty, Laguage laguage,
                boolean airCondition, GearBox gearBox, List<Rent> rentsForCar) {
        this.name = name;
        this.seatsQty = seatsQty;
        this.doorsQty = doorsQty;
        this.laguage = laguage;
        this.airCondition = airCondition;
        this.gearBox = gearBox;
        this.rentsForCar = rentsForCar;
    }

    //Wyposażenie dodatkowe

}
