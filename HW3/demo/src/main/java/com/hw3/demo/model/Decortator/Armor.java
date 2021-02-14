package com.hw3.demo.model.Decortator;

public class Armor extends CharacterDecorator{
	Character c;
	public Armor(Character c) {
		this.c = c;
	}
	@Override
	public String getLore() {
		// TODO Auto-generated method stub
		return this.c.getLore() + ", with armor";
	}
	@Override
	public double attack() {
		// TODO Auto-generated method stub
		return 2+this.c.attack();
	}

}
