package com.restapp.catar.domain.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OneDayWeather {

    @JsonProperty("id")
    private long id;

    @JsonProperty("applicable_date")
    private LocalDate weatherDate;

    @JsonProperty("weather_state_name")
    private String weatherDescription;

    @JsonProperty("weather_state_abbr")
    private String weatherAbbr;

    @JsonProperty("the_temp")
    private double tempOfDay;

}
