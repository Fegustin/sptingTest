package com.testproject.dermo.repository;

import com.testproject.dermo.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    List<Person> findByName(String name);
}
