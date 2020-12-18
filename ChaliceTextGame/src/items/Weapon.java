package items;

public class Weapon extends Item{
	private int DMG;
	
	public Weapon(String name, int DMG, int value) {
		setName(name);
		setDMG(DMG);
		setVal(value);
	}
	
	public Weapon() {
		setName("Knife");
		setDMG(4);
		setVal(2);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDMG(int DMG) {
		this.DMG=DMG;
	}
	
	public int getDMG() {
		return this.DMG;
	}
}
