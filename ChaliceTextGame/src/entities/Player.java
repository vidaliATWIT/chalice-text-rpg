package entities;
import java.util.ArrayList;

import items.Item;
import items.Weapon;
import items.Armor;
import items.Treasure;
public class Player extends Entity {
	/*
	private int curHP;
	private int maxHP;
	private String name;
	private int DMG;
	private int AC;
	*/
	private int loc = 0; //location id
	private String prof;
	private int str, dex, wis;
	private ArrayList<Item> inventory = new ArrayList<>();
	private boolean hasWeapon = false;
	private boolean hasTreasure;
	private Weapon on_hand;
	private Armor fit;
	public Player(String name, int i) {
		super();
		setName(name);
		setProf(i);
		setHasTreasure(false);
	}
	
	public Player() { //auto call
		super();
	}

	@Override
	public void setMaxHP() {
		// TODO Auto-generated method stub
		this.maxHP = 10 + this.str;
		this.curHP=maxHP;
	}
	
	public void setMaxHP(int HP) {
		this.maxHP=HP;
	}
	
	public void setCurHP(int HP) {
		this.curHP=HP;
	}

	@Override
	public void setDMG() {
		// TODO Auto-generated method stub
	}
	
	public void setDMG(int DMG) { //debug
		this.DMG=DMG;
	}
	
	public void setAC(int AC) { //debug
		
	}
	
	public void setStr(int str) {
		this.str = str;
	}
	
	public void setDex(int dex) {
		this.dex = dex;
	}
	
	public void setWis(int wis) {
		this.wis = wis;
	}
	
	public void setLoc(int loc) {
		this.loc = loc;
	}
	
	public void setProf(String name) {
		this.prof=name;
		on_hand = new Weapon();
		inventory.add(on_hand);
		setDMG(on_hand.getDMG());
		fit = new Armor();
		inventory.add(fit);
		setAC(fit.getAC());
		this.setHasWeapon(true);
	}
	
	public void setProf(int i) {
		if (i==1) {
			setProf(9, 16, 13, 10, "thief");
		} else if (i==2) {
			setProf(16, 13, 9, 14, "fighter");
		} else if (i==3) {
			setProf(13, 9, 16, 8, "magic-user");
		} else if (i==4) {
			setProf(3, 3, 3, 3, "damned");
		} else {
			setProf(9, 9, 9, 5, "deprived");
		}
		setCurHP(this.maxHP);
	}
	
	public void setProf(int str, int dex, int wis, int HP, String prof) {
		setStr(str);
		setDex(dex);
		setWis(wis);
		setMaxHP(HP);
		setProf(prof);
	}
	
	public void checkInventory() {
		//to-do fill out
	}
	
	public void addItem(Item item) {
		checkInventory();
		if (item instanceof Weapon) {
			this.inventory.remove(on_hand);
			this.inventory.add((Weapon)item);
			this.on_hand = (Weapon)item;
		} else if (item instanceof Armor) {
			this.inventory.remove(fit);
			this.inventory.add((Armor)item);
			this.fit = (Armor)item;
		} else if (item instanceof Treasure) {
			this.inventory.add((Treasure)item);
			this.setHasTreasure(true);
		} else
			this.inventory.add(item);
	}
	
	public void removeItem(Item item) {
		this.inventory.remove(item);
	}
	
	public int getMaxHP() {
		return this.maxHP;
	}
	
	public int getCurHP() {
		return this.curHP;
	}
	
	public int getDMG() {
		return this.on_hand.getDMG();
	}
	
	public int getAC() {
		return this.fit.getAC();
	}
	
	public int getStr() {
		return this.str;
	}
	
	public int getDex() {
		return this.dex;
	}
	
	public int getWis() {
		return this.wis;
	}
	
	public int getLoc() {
		return this.loc;
	}
	
	public String getProf() {
		return this.prof;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String printWeap() {
		return this.on_hand.toString();
	}
	
	public String printArm() {
		return this.fit.toString();
	}
	
	public String printName(Item item) {
		return item.toString();
	}
	
	public String printSheet() {
		return String.format("name: %s%nclass: %s%nstr: %d%ndex: %d%nwis: %d%n", this.getName(), this.getProf(), this.getStr(), this.getDex(), this.getWis());
	}
	
	public ArrayList<Item> getInventory() {
		return this.inventory;
	}
	
	public StringBuilder printInv() {
		StringBuilder inv = new StringBuilder();
		for (Item i:inventory) {
			inv.append(i.toString() + "\n");
		}
		return inv;
	}

	public boolean isHasTreasure() {
		return hasTreasure;
	}

	public void setHasTreasure(boolean hasTreasure) {
		this.hasTreasure = hasTreasure;
	}

	public boolean isHasWeapon() {
		return hasWeapon;
	}

	public void setHasWeapon(boolean hasWeapon) {
		this.hasWeapon = hasWeapon;
	}
}
