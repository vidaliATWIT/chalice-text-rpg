package entities;

import items.Treasure;

public class Monster extends Entity {

	private int HD;
	Treasure head;
	
	public Monster (int HD, String name) {
		setHD(HD);
		setName(name);
		setMaxHP(HD);
		setDMG();
		setHead();
	}
	
	public Monster() {
		setHD(3);
		setName("Crawler");
		setMaxHP(HD);
		setDMG();
		setHead();
	}
	
	@Override
	public void setMaxHP() {
		// TODO Auto-generated method stub
		if (this.HD==1) {
			this.setMaxHP(1);
		} else if (this.HD==2) {
			this.setMaxHP(2);
		} else if (this.HD==3) {
			this.setMaxHP(3);
		} else if (this.HD==4) {
			this.setMaxHP(4);
		} else if (this.HD==5) {
			this.setMaxHP(5);
		}
		this.curHP=maxHP;
	}
	
	public void setMaxHP(int d) {
		this.maxHP = d*4;
		this.curHP=this.maxHP;
	}

	@Override
	public void setDMG() {
		// TODO Auto-generated method stub
		if (HD==1) {
			this.DMG=4;
		} else if (HD==2) {
			this.DMG=6;
		} else if (HD==3) {
			this.DMG=8;
		} else if (HD==4) {
			this.DMG=10;
		} else if (HD==5) {
			this.DMG=12;
		}
	}
	
	public void setHD(int HD) {
		this.HD = HD;
	}
	
	public int getHD() {
		return this.HD;
	}
	
	public void setHead() {
		this.head = new Treasure(String.format("%s head", this.getName()), 100);
	}
	
	public Treasure getHead() {
		return this.head;
	}
}
