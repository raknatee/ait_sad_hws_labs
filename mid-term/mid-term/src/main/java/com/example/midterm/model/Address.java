package com.example.midterm.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter

public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	@NotBlank(message = "This field is requried.")
	private String city;

	@Column(nullable = false)
	@NotBlank(message = "This field is requried.")
	private String street;

	@Column(nullable = false)
	@NotBlank(message = "This field is requried.")
	private String houseNo;

	@Column(nullable = false)
	@NotBlank(message = "This field is requried.")
	private String zipcode;

	@ManyToMany(mappedBy = "addresses")
	private Set<Employee> employees;

}
