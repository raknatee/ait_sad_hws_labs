package com.hw3.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hw3.demo.model.Factory.Role;
import com.hw3.demo.model.Factory.RoleFactory;

@RestController
@RequestMapping("/factor")
public class FactorController {
	private RoleFactory f;
	
	@Autowired
	public FactorController(RoleFactory f) {
		this.f= f;
	}
	
	@PostMapping("addRole/{type}/{name}")
	public Role addRole(@PathVariable String type,@PathVariable String name) {
		Role r = this.f.createRole(type);
		r.setName(name);
		r.checkAccess();
		return r;
	}
	

}
