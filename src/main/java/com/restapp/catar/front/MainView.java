package com.restapp.catar.front;

import com.restapp.catar.controller.CatarController;
import com.restapp.catar.domain.city.City;
import com.restapp.catar.domain.city.Weather;
import com.restapp.catar.domain.rent.Rent;
import com.restapp.catar.repository.CityRepository;
import com.restapp.catar.repository.RentRepository;
import com.restapp.catar.service.CatarService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("ala")
public class MainView extends VerticalLayout {


    private CityRepository cityRepository;
    private Grid<City> grid;
    //final TextField filter;

    public MainView(CityRepository cityRepo) {
        this.cityRepository = cityRepo;
        this.grid = new Grid<>(City.class);
        //this.filter = new TextField();

        HorizontalLayout actions = new HorizontalLayout();
        add(actions, grid);

        grid.setHeight("200px");
        grid.setColumns("name", "airport", "weather", "temperature");

        //grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        //"cityId", , "rentsForTheCity"

        refresh ();

    }

    public void refresh () {
        grid.setItems(cityRepository.findAll());
    }
}
