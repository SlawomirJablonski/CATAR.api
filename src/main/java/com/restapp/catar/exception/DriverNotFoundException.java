package com.restapp.catar.exception;

public class DriverNotFoundException extends RuntimeException{
    public DriverNotFoundException(Long driverId){
        super(String.format("There is no driver with id equals: %s in Data Base", driverId));
    }
}