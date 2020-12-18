package entities;

public abstract class Entity {
	protected int maxHP;
	protected int curHP;
	protected String name;
	protected int DMG;
	
	public Entity (int HP, String name, int DMG) {
		this.maxHP=HP;
		this.curHP=maxHP;
		this.name=name;
		this.DMG=DMG;
	}
	
	public Entity(int HP, String name) {
		this.maxHP=HP;
		this.curHP=HP;
		this.name=name;
	}
	
	public Entity() {
		this(0, null, 0);
	}
	
	abstract public void setMaxHP();
	
	public void setCurHP(int HP) {
		this.curHP = HP;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	abstract public void setDMG();
	
	public int getMaxHP() {
		return this.maxHP;
	}
	
	public int getCurHP() {
		return this.curHP;
	}
	
	public String getName() {
		return this.name;
	}	
	
	public int getDMG() {
		return this.DMG;
	}
	
}
