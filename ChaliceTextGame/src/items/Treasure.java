package items;

public class Treasure extends Item{
	public Treasure() {
		setName("Goblin Head");
		setVal(100);
	}

	public Treasure(String format, int i) {
		// TODO Auto-generated constructor stub
		setName(format);
		setVal(i);
	}
}
