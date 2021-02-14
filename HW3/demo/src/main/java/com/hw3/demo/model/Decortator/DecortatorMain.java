package com.hw3.demo.model.Decortator;

public class DecortatorMain {
	public static void runMe() {
		Character mage = new Mage();
		System.out.println(mage.getLore()+" Attack!!! "+mage.attack());
		
		mage = new Weapon(mage);
		System.out.println(mage.getLore()+" Attack!!! "+mage.attack());
		
		mage = new Armor(mage);
		System.out.println(mage.getLore()+" Attack!!! "+mage.attack());
	}
}
