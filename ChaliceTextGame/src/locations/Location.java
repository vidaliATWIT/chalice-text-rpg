package locations;

import java.util.ArrayList;

import entities.Monster;

import items.Item;
import items.Treasure;
import items.Weapon;
import items.Actor;
import items.Armor;

abstract public class Location {
	private String name;
	private int id;
	private Monster enemy;
	private ArrayList<Item> items;
	private ArrayList<Actor> actors;
	private StringBuilder textDump;
	private StringBuilder choiceText;
	private ArrayList<Choices> choices;
	private ArrayList<Location> locations;
	private boolean hasBeen = false;
	private ArrayList<String> choiceNames = new ArrayList<>();
	
	public boolean isHasBeen() {
		return hasBeen;
	}

	public void setHasBeen(boolean hasBeen) {
		this.hasBeen = hasBeen;
	}

	public Location() {
		this.name="";
		this.id=0;
		this.locations= new ArrayList<>();
		this.enemy=null;
		this.items= new ArrayList<>();
		this.actors= new ArrayList<>();
		this.choices = new ArrayList<Choices>();
		this.textDump=null;
		this.choiceText=null;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Monster getEnemy() {
		return this.enemy;
	}

	public void setEnemy(Monster enemy) {
		this.enemy = enemy;
	}

	public ArrayList<Item> getItems() {
		return this.items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public void setItems(Item item) {
		if (item instanceof Treasure) {
			this.items.add((Treasure)item);
		} else if (item instanceof Weapon) {
			this.items.add((Weapon)item);
		} else if (item instanceof Armor) {
			this.items.add((Armor)item);
		}
	}
	
	public void addItems(Item it) {
		this.items.add(it);
	}

	public ArrayList<Actor> getActors() {
		return this.actors;
	}

	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}
	
	public void addActors(Actor actor) {
		this.actors.add(actor);
	}

	public StringBuilder getTextDump() {
		return this.textDump;
	}

	public void setTextDump(StringBuilder textDump) {
		this.textDump = textDump;
	}
	
	public void addTextDump(String append) {
		this.textDump.append(append);
	}

	public StringBuilder getChoiceText() {
		return this.choiceText;
	}

	public void setChoiceText(StringBuilder buffer) {
		this.choiceText = buffer;
	}
	
	public void addChoice(Choices choice) {
		this.choices.add(choice);
	}
	
	public void removeChoice(Choices choice) {
		this.choices.remove(choice);
	}
	
	public void setChoices(ArrayList<Choices> choices) {
		this.choices=choices;
	}
	
	public ArrayList<Choices> getChoices() {
		return this.choices;
	}
	
	public void setLocations(ArrayList<Location> loc) {
		this.locations=loc;
	}
	
	public ArrayList<Location> getLocations() {
		return this.locations;
	}
	
	public void addLocation(Location loc) {
		this.locations.add(loc);
	}
	
	public void updateChoices(ArrayList<Choices> buffer) {
		for (int i=0; i<this.locations.size(); i++) {
			buffer.add(new Choices(2, getLocations().get(i)));
		}
		/*buffer.add(new Choices(8));
		buffer.add(new Choices(9));*/
	}
	
	public void setChoiceNames() {
		this.choiceNames.clear();
		for (int i=0; i<choices.size();i++) {
			this.choiceNames.add(choices.get(i).getName());
		}
	}
	
	public ArrayList<String> getChoiceNames() {
		return this.choiceNames;
	}
	
	abstract public void updateChoices();
	abstract public void setChoiceText();
	abstract public void setTextDump();
	
	public void removeItem(Item item) {
		this.items.remove(item);
	}
}
