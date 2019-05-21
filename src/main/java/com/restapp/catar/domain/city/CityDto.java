package com.restapp.catar.domain.city;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CityDto {
    private Long cityId;
    private String name;
    private String airport;
    private String weather;
    private double temperature;

    public static class CityDtoBuilder{
        private Long cityId;
        private String name;
        private String airport;
        private String weather;
        private double temperature;

        public CityDtoBuilder cityId(Long cityId) {
            this.cityId = cityId;
            return this;
        }

        public CityDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CityDtoBuilder airport(String airport) {
            this.airport = airport;
            return this;
        }

        public CityDtoBuilder weather(String weather) {
            this.weather = weather;
            return this;
        }

        public CityDtoBuilder temperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public CityDto build(){
            return new CityDto(cityId,name,airport,weather,temperature);
        }
    }

    public CityDto(Long cityId, String name, String airport, String weather, double temperature) {
        this.cityId = cityId;
        this.name = name;
        this.airport = airport;
        this.weather = weather;
        this.temperature = temperature;
    }
}
