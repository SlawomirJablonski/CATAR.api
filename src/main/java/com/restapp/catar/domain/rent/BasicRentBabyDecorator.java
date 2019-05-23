package com.restapp.catar.domain.rent;

import java.math.BigDecimal;

public class BasicRentBabyDecorator extends AbstractRentDecorator {
    public BasicRentBabyDecorator(Rent rent) {
        super(rent);
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(20));
    }

    @Override
    public String getOptions() {
        return super.getOptions()+"/baby seat";
    }
}
