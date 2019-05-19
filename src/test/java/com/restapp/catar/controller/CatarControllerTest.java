package com.restapp.catar.controller;

import com.google.gson.Gson;
import com.restapp.catar.domain.city.City;
import com.restapp.catar.domain.city.CityDto;
import com.restapp.catar.mapper.CatarMapper;
import com.restapp.catar.service.CatarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CatarController.class)
public class CatarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatarService catarService;

    @MockBean
    private CatarMapper catarMapper;

    @Test
    public void shouldCreateCity()throws Exception{
        //Given
        City city = new City.CityBuilder()
                .name("Poznan")
                .build();

        Gson gson = new Gson();
        String jsonContent = gson.toJson(city);

        //When & Then
        mockMvc.perform(post("/v1/catar/cities").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}
