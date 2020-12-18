package locations;

import java.util.ArrayList;

public class Road extends Location{
	
	public Road () {
		super();
	}
	
	/*
	 * public Dungeon(String name, int id, ArrayList<Location> loc, Monster enemy, Treasure head) {
		super();
		setName(name);
		setId(id);
		setLocations(loc);
		setEnemy(enemy);
		setItems(head);
		updateChoices();
		setTextDump();
		setChoiceText();
	}
	 */
	
	public Road(String name, int id) {
		super();
		setName(name);
		setId(id);
	}
	
	public void setTextDump() {
		StringBuilder buffer = new StringBuilder("");
			buffer.append(String.format("You have arrived at the %s%n", this.getName()));
		setTextDump(buffer);
	}
	
	public void updateChoices() {
		ArrayList<Choices> choices = new ArrayList<>();
		super.updateChoices(choices); //check for locations 
		setChoices(choices); //set this.choices to be the buffer
	}
	
	public void setChoiceText() {
		StringBuilder buffer = new StringBuilder("");
		ArrayList<Choices>choices = getChoices();
		for (int i = 0; i<getChoices().size(); i++) {
			buffer.append(String.format("%d. %s%n", i+1, choices.get(i)));
		}
		setChoiceText(buffer);
	}
}
