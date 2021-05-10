package com.example.finalexam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.finalexam.Service.ProductService;

@SpringBootTest
class FinalExamApplicationTests {
	
	@Autowired
	private ProductService productService;

	@Test
	void contextLoads() {
	}

	@Test
	void testPessimisticLocking() {
		
	}
	
	@Test
	void testOptimisticLocking() {
		
	}
}
