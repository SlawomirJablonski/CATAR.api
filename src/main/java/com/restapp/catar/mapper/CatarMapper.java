package com.restapp.catar.mapper;

import com.restapp.catar.domain.rent.Rent;
import com.restapp.catar.domain.rent.RentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CatarMapper {

    public Rent mapToRent(RentDto rentDto){
        return new Rent(rentDto.getDriver(),rentDto.getCar(),rentDto.getCity(),rentDto.getFromDate(),rentDto.getTillDate(),true);
    }

    public List<RentDto> mapToRentDtoList(List<Rent> rents) {
        return rents.stream()
                .map(rent -> new RentDto(rent.getRentId(),rent.getDriver(),rent.getCar(),rent.getCity(),rent.getFromDate(),rent.getTillDate()))
                .collect(Collectors.toList());
    }
}