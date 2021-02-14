package com.hw3.demo.model.Factory;

public class AdminRole implements Role{
	private String name;
	
	public AdminRole() {
		this(null);
	}
	public AdminRole(String name) {
		this.name=name;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name=name;
	}

	
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}


	public String getRole() {
		// TODO Auto-generated method stub
		return "Admin";
	}

	
	public void checkAccess() {
		// TODO Auto-generated method stub
		System.out.println("Granted access level: "+this.getRole());
		
	}

	
}
