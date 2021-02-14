package com.hw3.demo.model.Decortator;

public class Weapon extends CharacterDecorator{
	Character c;
	public Weapon(Character c) {
		this.c = c;
	}
	@Override
	public String getLore() {
		// TODO Auto-generated method stub
		return this.c.getLore() + ", with weapon";
	}
	@Override
	public double attack() {
		// TODO Auto-generated method stub
		return 10+this.c.attack();
	}

}
