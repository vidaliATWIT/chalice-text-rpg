package locations;

import java.util.ArrayList;

import items.Actor;

public class Guildhall extends Location {
	private int QuestCount = 0;
	private boolean hasQuest = false;
	
	public Guildhall(String name, int id, Actor guard) {
		super();
		setName(name);
		setId(id);
		addActors(guard);
	}
	
	public void setTextDump() {
		StringBuilder buffer = new StringBuilder("");
		if (isHasBeen()) {
			buffer.append(String.format("You can hear the noise from inside the town.%nAs you walk up to the gates the %s waves at you.%n", this.getActors().get(0).getName()));
		} else if (!isHasBeen()) {
			buffer.append(String.format("You have arrived at the %s.%nThe %s motions you to speak to him.%n", this.getName(), this.getActors().get(0).getName()));
		}
		setTextDump(buffer);
	}
	
	public void updateChoices() {
		ArrayList<Choices> choices = new ArrayList<>();
			if (!hasQuest) {
				choices.add(new Choices(7, this.getActors().get(0)));
			} else if (hasQuest) {
				choices.add(new Choices(1, this.getActors().get(0)));
			}
			super.updateChoices(choices);
			setChoices(choices);
	}
	
	public void setChoiceText() {
		StringBuilder buffer = new StringBuilder("");
		ArrayList<Choices> choices = getChoices();
		for (int i=0; i<getChoices().size(); i++) {
			buffer.append(String.format("%d. %s%n", i+1, choices.get(i)));
		}
		setChoiceText(buffer);
	}

	public int getQuestCount() {
		return this.QuestCount;
	}

	public void setQuestCount(int questCount) {
		this.QuestCount = questCount+1;
	}

	public boolean isHasQuest() {
		return this.hasQuest;
	}

	public void setHasQuest(boolean hasQuest) {
		this.hasQuest = hasQuest;
	}
}
