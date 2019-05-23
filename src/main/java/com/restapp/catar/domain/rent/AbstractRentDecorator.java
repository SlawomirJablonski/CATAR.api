package com.restapp.catar.domain.rent;

import java.math.BigDecimal;

public abstract class AbstractRentDecorator implements Rent{
    private final Rent rent;

    public AbstractRentDecorator(Rent rent) {
        this.rent = rent;
    }

    @Override
    public BigDecimal getCost() {
        return rent.getCost();
    }

    @Override
    public String getOptions() {
        return rent.getOptions();
    }
}
