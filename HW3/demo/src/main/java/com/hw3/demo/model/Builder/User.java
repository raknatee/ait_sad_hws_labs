package com.hw3.demo.model.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private int id;

	private String name;
	private String nationality;
	private String email;
	public User(int id, String name, String nationality, String email) {
		super();
		this.id = id;
		this.name = name;
		this.nationality = nationality;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", nationality=" + nationality + ", email=" + email + "]";
	}
	
}
