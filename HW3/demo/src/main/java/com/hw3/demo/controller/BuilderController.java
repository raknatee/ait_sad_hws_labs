package com.hw3.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hw3.demo.model.Builder.User;
import com.hw3.demo.model.Builder.UserBuilder;
import com.hw3.demo.model.Builder.UserLombok;

@RestController
@RequestMapping("/builder")
public class BuilderController {
	
	@GetMapping("user1")
	public String getUser1() {
		User u  = new UserBuilder()
				.setEmail("chaklam@ait.asia")
				.setName("Chaky")
				.setNationality("Hong Kong")
				.setUid(999)
				.build();
		System.out.println(u.toString());
		return "created!";
	}
	
	@GetMapping("userAuto")
	public String getUserAuto() {
		UserLombok u = UserLombok.builder()
				.email("chaklam@ait.asia")
				.name("Auto")
				.nationality("Hong Kong")
				.uid(998)
				.build();
		System.out.println(u);
		return "created!";
	}
}
