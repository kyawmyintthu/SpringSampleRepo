package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.data1.Person;
import com.spring.data1.TestRepo;
import com.spring.data2.Car;
import com.spring.data2.TestRepo2;

@Service(value="SpringService")
public class SpringService {
	
	@Autowired
	TestRepo repo;
	
	@Autowired
	TestRepo2 repo2;
	
	public void Test() {
		System.out.println("SpringService");
		Person p = new Person();
		p.setName("test");
		repo.save(p);
		Car c = new Car();
		c.setName("test");
		repo2.save(c);
	}

}
