package com.example.securitysad.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	@NotBlank(message = "This field is required")
	private String username;
	
	@Column(nullable = false)
	@NotBlank(message = "This field is required")
	private String password;
	
	@NotBlank(message = "This field is required")
	@Transient
	private String passwordConfirm;
	
	@Column(nullable = false)
	@NotBlank(message = "This field is required")
	@Email(message="Invalid email")
	private String email;
	
	private boolean active;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JsonBackReference
	private Set<Role> roles;
}
