package com.example.finalexam.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Company {
	@Id
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "company")
	private List<Product> products;
}
