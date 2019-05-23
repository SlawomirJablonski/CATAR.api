package com.restapp.catar.exception;

public class RentNotFoundException extends RuntimeException {
    public RentNotFoundException(Long rentId){
        super(String.format("There is no rent with id equals: %s in Data Base", rentId));
    }
}