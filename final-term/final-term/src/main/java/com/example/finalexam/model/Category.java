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
public class Category {
	@Id
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "category")
	private List<Product> products;
	
}
