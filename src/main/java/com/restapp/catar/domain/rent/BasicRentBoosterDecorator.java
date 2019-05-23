package com.restapp.catar.domain.rent;

import java.math.BigDecimal;

public class BasicRentBoosterDecorator extends AbstractRentDecorator{
    public BasicRentBoosterDecorator(Rent rent) {
        super(rent);
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(12));
    }

    @Override
    public String getOptions() {
        return super.getOptions()+"/booster seat";
    }
}
