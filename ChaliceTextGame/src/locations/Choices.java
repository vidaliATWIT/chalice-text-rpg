package locations;

import entities.Monster;
import items.Actor;
import items.Item;

public class Choices{
	private String name;
	private String choice;
	private int id;
	private Object subject;
	
	public Choices(int id, Object subject) {
		if (id==1) {
			this.subject = (Actor)subject;
			this.name=String.format("talk to %s", ((Actor)this.subject).getName());
		} else if(id==2) {
			this.subject = (Location)subject;
			this.name=String.format("go to %s", ((Location)this.subject).getName());
		} else if (id==3) {
			this.subject = (Item)subject;
			this.name=String.format("pick up %s", ((Item)this.subject).getName());
		} else if (id==4) {
			this.subject = (Monster)subject;
			this.name=String.format("fight %s", ((Monster)this.subject).getName());
		} else if (id==5) {
			this.subject = (Location)subject;
			this.name=String.format("run");
		} else if (id==6) {
			this.subject=(Item)subject;
			this.name=String.format("give %s", ((Item)this.subject).getName());
		} else if (id==7) {
			this.subject=(Actor)subject;
			this.name=String.format("get mission from %s", ((Actor)this.subject).getName());
		} else if (id==9) {
			this.subject=(Monster)subject;
			this.name=String.format("attack %s", (Monster)this.subject);
		}
		this.id=id;
	}
	
	public Choices (int id) {
		if (id==8) {
			this.name=String.format("check your backpack");
		} else if (id==9) {
			this.name=String.format("check your character sheet");
		}
		this.id=id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChoice() {
		return this.choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Object getSubject() {
		return this.subject;
	}
 	
	public String toString() {
		return this.name;
	}
}
