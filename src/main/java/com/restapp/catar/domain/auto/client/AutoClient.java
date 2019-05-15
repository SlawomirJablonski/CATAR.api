package com.restapp.catar.domain.auto.client;

import com.restapp.catar.domain.auto.AutoPark;
import com.restapp.catar.domain.auto.Mark;
import com.restapp.catar.domain.auto.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class AutoClient {
    private static final String MODELS = "/modelos";

    private final String autoApiEndpoint;

    private final RestTemplate restTemplate;

    @Autowired
    public AutoClient(@Value("${auto.api}")String autoApiEndpoint, RestTemplate restTemplate) {
        this.autoApiEndpoint = autoApiEndpoint;
        this.restTemplate = restTemplate;
    }

    public List<Model> getModels(String code){
        URI url = buildUrlModels(code);

        try{
            AutoPark apiResponse = restTemplate.getForObject(
                    url,AutoPark.class);
            return Arrays.asList(apiResponse.getModels());
        }catch(RuntimeException e){
            System.out.println("type of RestClientResponseException: " + e);
            return Collections.emptyList();
        }
    }

    public List<Mark> getMarks(){
        URI url = buildUrlMarks();

        try{
            Mark[] apiResponse = restTemplate.getForObject(
                    url,Mark[].class);
            return Arrays.asList(apiResponse);
        }catch(RuntimeException e){
            System.out.println("type of RestClientResponseException: " + e);
            return Collections.emptyList();
        }
    }


    private URI buildUrlModels(String code){
        return UriComponentsBuilder.fromHttpUrl(String.format("%s/%s%s", autoApiEndpoint, code, MODELS))
                .build().encode().toUri();
    }

    private URI buildUrlMarks(){
        return UriComponentsBuilder.fromHttpUrl(autoApiEndpoint)
                .build().encode().toUri();
    }
}
