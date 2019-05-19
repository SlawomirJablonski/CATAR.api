package com.restapp.catar.exception;

public class CityByNameNotFoundException extends RuntimeException{
    public CityByNameNotFoundException(String name) {
        super(String.format("There is no city with name: %s in Data Base", name));
    }
}
