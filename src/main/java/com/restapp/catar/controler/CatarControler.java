package com.restapp.catar.controler;

import com.restapp.catar.domain.rent.RentDto;
import com.restapp.catar.mapper.CatarMapper;
import com.restapp.catar.service.CatarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/catar")
public class CatarControler {

    private final CatarService catarService;
    private final CatarMapper catarMapper;

    public CatarControler(CatarService catarService, CatarMapper catarMapper) {
        this.catarService = catarService;
        this.catarMapper = catarMapper;
    }

    @GetMapping(value = "/rents")
    public List<RentDto> getRents() {
        return catarMapper.mapToRentDtoList(catarService.getRents());
    }

    @PostMapping(value = "/rents", consumes = APPLICATION_JSON_VALUE)
    public void createRent(@RequestBody RentDto rentDto){
        catarService.saveRent(catarMapper.mapToRent(rentDto));
    }
}
