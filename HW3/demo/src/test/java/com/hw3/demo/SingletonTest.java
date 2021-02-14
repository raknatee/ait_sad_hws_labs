package com.hw3.demo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hw3.demo.model.Singleton.SingletonLab;
import com.hw3.demo.model.Singleton.SpringSingleton;

@SpringBootTest
public class SingletonTest {
	
//	@Autowired
//	SingletonLab ss1;
//	
//	@Autowired
//	SpringSingleton ss2;
	
	@Test
	void contextLoads() {
		SingletonLab ss1 = SingletonLab.getInstance();
		SingletonLab ss2 = SingletonLab.getInstance();
		Assertions.assertSame(ss1,ss2);

	}
	
}
