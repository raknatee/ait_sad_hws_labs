package com.hw3.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hw3.demo.model.Adapter.AdapterMain;
import com.hw3.demo.model.Decortator.DecortatorMain;
import com.hw3.demo.model.State.StateMain;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		AdapterMain.runMe();
		DecortatorMain.runMe();
		StateMain.runMe();
	}

}
