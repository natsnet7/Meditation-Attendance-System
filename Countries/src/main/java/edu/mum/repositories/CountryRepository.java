package edu.mum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

}
