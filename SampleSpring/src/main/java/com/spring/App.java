package com.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class,MainConfiguration2.class);
		SpringService obj = (SpringService) context.getBean("SpringService");
		obj.Test();
	}
}
