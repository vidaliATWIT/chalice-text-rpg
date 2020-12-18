package items;

public class Armor extends Item{
	int AC;
	
	public Armor() {
		setVal(0);
		setName("Gambeson");
		setAC(2);
	}
	
	public Armor(int value, String name, int AC) {
		this.value = value;
		this.name = name;
		this.AC = AC;
	}
	
	public void setAC (int AC) {
		this.AC = AC;
	}
	
	public int getAC() {
		return this.AC;
	}
}
