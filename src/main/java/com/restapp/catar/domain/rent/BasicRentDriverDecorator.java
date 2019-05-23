package com.restapp.catar.domain.rent;

import java.math.BigDecimal;

public class BasicRentDriverDecorator extends AbstractRentDecorator{
    public BasicRentDriverDecorator(Rent rent) {
        super(rent);
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(95));
    }

    @Override
    public String getOptions() {
        return super.getOptions()+ "/additional driver";
    }
}
