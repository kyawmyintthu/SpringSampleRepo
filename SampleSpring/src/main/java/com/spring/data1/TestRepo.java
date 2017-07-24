package com.spring.data1;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.data1.Person;

@Repository
public interface TestRepo extends CrudRepository<Person, Integer> {

}
