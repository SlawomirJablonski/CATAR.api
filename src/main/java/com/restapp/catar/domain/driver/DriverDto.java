package com.restapp.catar.domain.driver;

import com.restapp.catar.domain.rent.BasicRent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriverDto {
    private Long id;
    private Title title;
    private String name;
    private String surName;
    private String email;
    private String phone;
    private List<BasicRent> rentsForTheDriver = new ArrayList<>();

    public DriverDto(Title title, String name, String surName, String email) {
        this.title = title;
        this.name = name;
        this.surName = surName;
        this.email = email;
    }
}
