package com.hw3.demo.model.Decortator;

public abstract class Character {
	String lore;
	public String getLore() {
		return this.lore;
	}
	public abstract double attack();
}
