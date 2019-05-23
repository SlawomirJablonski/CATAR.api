package com.restapp.catar.domain.rent;

import java.math.BigDecimal;

public class BasicRentGpsDecorator extends AbstractRentDecorator {

    public BasicRentGpsDecorator(Rent rent) {
        super(rent);
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(12));
    }

    @Override
    public String getOptions() {
        return super.getOptions()+"/GPS";
    }
}
