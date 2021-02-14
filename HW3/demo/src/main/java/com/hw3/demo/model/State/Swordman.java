package com.hw3.demo.model.State;

public class Swordman implements MyState {
	int agi = 7;
	int atk = 13;
	int def = 5;
	@Override
	public void increaseDefense(int increment) {
		// TODO Auto-generated method stub
		this.def = def + increment;
	}

	@Override
	public void speedUp(int increment) {
		// TODO Auto-generated method stub
		this.atk = this.atk + 2*increment;
		this.agi += increment;
		
	}

	@Override
	public void increaseAttack(int increment) {
		// TODO Auto-generated method stub
		this.atk = this.atk + increment;
		this.def = this.def - (int)0.3 * increment;
	}

	@Override
	public void printStates() {
		// TODO Auto-generated method stub
		System.out.println("Agi-atk-def: "+agi+"-"+atk+"-"+def);
		
	}

}
