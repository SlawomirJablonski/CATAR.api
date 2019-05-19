package com.restapp.catar.exception;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(Long cityId) {
        super(String.format("There is no city with id equals: %s in Data Base", cityId));
    }
}
