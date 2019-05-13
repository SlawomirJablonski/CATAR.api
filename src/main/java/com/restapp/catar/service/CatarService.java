package com.restapp.catar.service;

import com.restapp.catar.domain.rent.Rent;
import com.restapp.catar.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class CatarService {

    private final RentRepository rentRepository;

    @Autowired
    public CatarService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public List<Rent> getRents() {
        return rentRepository.findAll();
    }

    public Rent saveRent(final Rent rent){
        return rentRepository.save(rent);
    }


}
