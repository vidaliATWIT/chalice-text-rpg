package items;

public class Item {
	protected String name;
	protected int value;
	
	public Item () {
		this.name=null;
		this.value=0;
	}

	public void setVal(int val) {
		this.value=val;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return String.format("%s", name);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getVal() {
		return this.value;
	}
}
