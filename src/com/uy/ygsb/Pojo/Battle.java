package com.uy.ygsb.Pojo;

public class Battle {

	private String info;
	private Attack attack, attacked;

	public void setAttack(Attack attack) {
		this.attack = attack;
	}

	public Attack getAttack() {
		return attack;
	}

	public void setAttacked(Attack attacked) {
		this.attacked = attacked;
	}

	public Attack getAttacked() {
		return attacked;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

}
