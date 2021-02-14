package com.hw3.demo.model.Factory;

public class MemberRole implements Role{
	private String name;
	public MemberRole() {
		this(null);
	}
	public MemberRole(String name) {
		this.name=name;
	}
	@Override
	public void setName(String name) {
		this.name=name;
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return "Member";
	}
	@Override
	public void checkAccess() {
		// TODO Auto-generated method stub
		System.out.println("Granted access level: "+this.getRole());
		
	}
	
}
