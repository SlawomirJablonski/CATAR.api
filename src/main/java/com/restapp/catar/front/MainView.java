package com.restapp.catar.front;

import com.restapp.catar.controller.AutoController;
import com.restapp.catar.controller.CatarController;
import com.restapp.catar.domain.auto.Mark;
import com.restapp.catar.domain.auto.Model;
import com.restapp.catar.domain.city.City;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


import java.time.LocalDate;


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

        NativeButton button = new NativeButton("Navigate to company");
        button.addClickListener( e-> {
            button.getUI().ifPresent(ui -> ui.navigate("company"));
        });



        HorizontalLayout logoLayout = new HorizontalLayout();
        Alignment down = Alignment.CENTER;
        logoLayout.setAlignItems(down);
        Icon iconF = VaadinIcon.FLIGHT_LANDING.create();
        iconF.setSize("100px");
        Icon iconC = VaadinIcon.CAR.create();
        iconC.setSize("40px");
        Label top = new Label("CATAR - Car At The Airport Rental :)");

        logoLayout.add(iconF,iconC,top);

        HorizontalLayout rentAndGrid = new HorizontalLayout();
        rentAndGrid.setWidth("100%");
        VerticalLayout rentLayout = new VerticalLayout();
        rentLayout.setMaxWidth("50%");

        HorizontalLayout nameLayout = new HorizontalLayout();
        TextField titleField = new TextField("Title");
        titleField.setMaxWidth("15%");
        titleField.setPlaceholder("Mrs/Mr");
        TextField firstNameField = new TextField("First name");
        firstNameField.setMaxWidth("40%");
        firstNameField.setPlaceholder("John");
        TextField lastNameField = new TextField("Last name");
        lastNameField.setMaxWidth("40%");
        lastNameField.setPlaceholder("Doe");
        nameLayout.add(titleField, firstNameField, lastNameField);

        HorizontalLayout emailLayout = new HorizontalLayout();
        TextField phone = new TextField("phone");
        phone.setValueChangeMode(ValueChangeMode.EAGER);
        TextField email = new TextField("email");
        email.setValueChangeMode(ValueChangeMode.EAGER);
        emailLayout.add(phone,email);

        HorizontalLayout dateLayout = new HorizontalLayout();
        DatePicker rentDate = new DatePicker("BasicRent from date:");
        rentDate.setMaxWidth("20%");
        rentDate.setPlaceholder(LocalDate.now().toString());
        TimePicker hFrom = new TimePicker("hour");
        hFrom.setMaxWidth("15%");
        hFrom.setPlaceholder("10:00");
        DatePicker returnDate = new DatePicker("BasicRent till date:");
        returnDate.setMaxWidth("20%");
        returnDate.setPlaceholder(LocalDate.now().plusDays(1).toString());
        TimePicker hTill = new TimePicker("hour");
        hTill.setMaxWidth("15%");
        hTill.setPlaceholder("16:00");
        dateLayout.add(rentDate,hFrom,returnDate,hTill);

        rentLayout.add(nameLayout,emailLayout,dateLayout);

        VerticalLayout gridLayout = new VerticalLayout();
        gridLayout.add(grid);

        /*nameLayout.setResponsiveSteps(

                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("21em", 2),
                new FormLayout.ResponsiveStep("22em", 3));*/

        rentAndGrid.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        rentAndGrid.add(rentLayout,gridLayout);

        add(logoLayout, rentAndGrid, comboBox);


        grid.setHeight("200px");
        //grid.setWidth("800px");

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
