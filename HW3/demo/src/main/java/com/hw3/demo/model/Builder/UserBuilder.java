package com.hw3.demo.model.Builder;

public class UserBuilder {
	private int uid;
	private String name;
	private String nationality;
	private String email;
	
	public UserBuilder setUid(int uid) {
		this.uid = uid;
		return this;
	}
	public UserBuilder setName(String name) {
		this.name =name;
		return this;
	}
	public UserBuilder setNationality(String nationality) {
		this.nationality=nationality;
		return this;
	}
	public UserBuilder setEmail(String e) {
		this.email=e;
		return this;
	}
	public User build() {
		return new User(this.uid,this.name,this.nationality,this.email);
	}
}
