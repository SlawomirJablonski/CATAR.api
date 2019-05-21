package com.restapp.catar.front;

import com.restapp.catar.controller.AutoController;
import com.restapp.catar.controller.CatarController;
import com.restapp.catar.domain.auto.Mark;
import com.restapp.catar.domain.auto.Model;
import com.restapp.catar.domain.city.City;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("catar")
public class MainView extends VerticalLayout {


    private CatarController catarController;
    private AutoController autoController;
    private Grid<City> grid;
    private ComboBox<City> comboBox;

    public MainView(CatarController catarController, AutoController autoController) {
        this.catarController = catarController;
        this.autoController = autoController;
        this.grid = new Grid<>(City.class);
        this.comboBox = new ComboBox<>("Select your city airport");

        HorizontalLayout logoLayout = new HorizontalLayout();
        Alignment down = Alignment.BASELINE;
        logoLayout.setAlignItems(down);
        Icon iconF = VaadinIcon.FLIGHT_LANDING.create();
        iconF.setSize("100px");
        Icon iconC = VaadinIcon.CAR.create();
        iconC.setSize("40px");
        Label top = new Label("CATAR - Car At The Airport Rental :)");
        logoLayout.add(iconF,iconC,top);

        add(logoLayout, grid, comboBox);

        grid.setHeight("200px");
        grid.setWidth("800px");

        grid.setColumns("name", "airport", "weather", "temperature");
        grid.getColumnByKey("name").setHeader("City").setFlexGrow(15);
        grid.getColumnByKey("airport").setFlexGrow(50);
        grid.getColumnByKey("weather").setFlexGrow(20);
        grid.getColumnByKey("temperature").setFlexGrow(15);

        refresh ();

        comboBox.setItems(catarController.getCities());
        comboBox.setPlaceholder("No city airport selected");
        comboBox.setItemLabelGenerator(item -> item.getName()+" "+item.getAirport());
        comboBox.setWidth("300px");

        ComboBox<Mark> comboBoxMark = new ComboBox<>("Select car brand");
        comboBox.addValueChangeListener(event -> {

            City theCity = comboBox.getValue();

            comboBoxMark.setItems(autoController.getMarks());
            comboBox.setPlaceholder("No brand selected");
            comboBox.setItemLabelGenerator(City::getName);
            add(comboBoxMark);

            ComboBox<Model> modelComboBox = new ComboBox<>("Select brand model");
            comboBoxMark.addValueChangeListener(event1 -> {
                String code = comboBoxMark.getValue().getCode();
                modelComboBox.setItems(autoController.getModels(code));
                comboBoxMark.setPlaceholder("No model selected");
                comboBoxMark.setItemLabelGenerator(Mark::getName);
                modelComboBox.setWidth("300px");
                add(modelComboBox);

                modelComboBox.addValueChangeListener(event2 -> {
                    //set własciwosci wybranego modelu do dto
                    Label bottom = new Label("Additional car option");
                    add(bottom);

                    HorizontalLayout carOptions = new HorizontalLayout();

                    Checkbox gps = new Checkbox("GPS");
                    Checkbox addDriver = new Checkbox("Additional Driver");
                    Checkbox cSeat = new Checkbox("Child Seat");
                    Checkbox booSeat = new Checkbox("Booster Seat");
                    Checkbox baSeat = new Checkbox("Baby Seat");

                    carOptions.add(gps,addDriver,cSeat,booSeat,baSeat);
                    add(carOptions);

                });
            });
        });
    }

    public void refresh () {
        grid.setItems(catarController.getCities());
    }
}
