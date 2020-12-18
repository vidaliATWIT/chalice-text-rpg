package locations;

import java.util.ArrayList;

import entities.Monster;
import items.Treasure;

public class Dungeon extends Location {

	private boolean isEnemyDead=false;
	private boolean isFight;
	private boolean hasHead=false;
	private Treasure head;
	private Choices[] fightChoices = {new Choices(9, this.getEnemy()), new Choices(5, this)};
	
	public Dungeon(String name, int id, Monster enemy, Treasure head) {
		super();
		setName(name);
		setId(id);
		setEnemy(enemy);
		setItems(this.getEnemy().getHead());
	}

	public boolean isEnemyDead() {
		return this.isEnemyDead;
	}

	public void setEnemyDead(boolean isEnemyDead) {
		this.isEnemyDead = isEnemyDead;
	}

	public boolean isFight() {
		return this.isFight;
	}

	public void setFight(boolean isFight) {
		this.isFight = isFight;
	}
	
	public void setTextDump() {
		StringBuilder buffer = new StringBuilder("");
			buffer.append(String.format("You have entered the dark halls of %s%n", getName()));
		if (isEnemyDead) {
			buffer.append(String.format("You stand over the corpse of the %s%nWhat do you do?%n", getEnemy().getName()));
		} else {
			buffer.append(String.format("You can see the outline of a %s in the shadows...%nWhat do you do?%n", getEnemy().getName()));
		}
		setTextDump(buffer);
	}
	
	public void setChoiceText() {
		StringBuilder buffer = new StringBuilder("");
		ArrayList<Choices>choices = getChoices();
		for (int i = 0; i<getChoices().size(); i++) {
			buffer.append(String.format("%d. %s%n", i+1, choices.get(i)));
		}
		setChoiceText(buffer);
	}
	
	public void updateChoices() {
		ArrayList<Choices> choices = new ArrayList<>();
		if (isEnemyDead&&(hasHead==false)) {
			setHead(this.getEnemy().getHead());
			choices.add(new Choices(3, this.getItems().get(0)));
		} else if (!isEnemyDead) {
			choices.add(new Choices(4, getEnemy()));
		}
		super.updateChoices(choices);
			setChoices(choices);
	}
	
	public boolean isHasQuest() {
		// TODO Auto-generated method stub
		return false;
	}

	public Choices[] getFightChoices() {
		return fightChoices;
	}

	public void setFightChoices(Choices[] fightChoices) {
		this.fightChoices = fightChoices;
	}

	public boolean isHasHead() {
		return hasHead;
	}

	public void setHasHead(boolean hasHead) {
		this.hasHead = hasHead;
	}

	public Treasure getHead() {
		return head;
	}

	public void setHead(Treasure head) {
		this.head = head;
	}
	
	
	
	
}
