package com.restapp.catar.domain.auto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mark {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("codigo")
    private String code;

    /*public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }*/
}
