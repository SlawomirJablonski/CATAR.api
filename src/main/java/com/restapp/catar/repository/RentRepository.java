package com.restapp.catar.repository;

import com.restapp.catar.domain.rent.BasicRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<BasicRent, Long> {
}
