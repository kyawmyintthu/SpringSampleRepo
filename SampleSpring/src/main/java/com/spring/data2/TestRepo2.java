package com.spring.data2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.data2.Car;

@Repository
public interface TestRepo2 extends CrudRepository<Car, Integer> {

}
