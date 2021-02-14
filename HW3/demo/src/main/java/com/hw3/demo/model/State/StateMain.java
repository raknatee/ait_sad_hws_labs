package com.hw3.demo.model.State;

public class StateMain {
	public static void runMe() {
		Swordman sm = new Swordman();
		sm.increaseAttack(4);
		sm.speedUp(3);
		sm.increaseDefense(1);
		sm.speedUp(2);
		System.out.println("Character present state:");
		sm.printStates();
	}
}
