package com.restapp.catar.domain.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsolidatedWeather {

    @JsonProperty("consolidated_weather")
    private List<OneDayWeather> consolidatedWeather;

    public List<OneDayWeather> getConsolidatedWeather() {
        return consolidatedWeather;
    }
}
