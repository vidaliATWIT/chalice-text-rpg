package locations;

import java.util.ArrayList;

import items.Weapon;
public class Trove extends Location{
	
	public Trove(String name, int id) {
		setName(name);
		setId(id);
		setItems(new Weapon("Sword", 6, 10));
	}
	
	@Override
	public void setTextDump() {
		StringBuilder buffer = new StringBuilder("");
			buffer.append(String.format("You arrive at a small trove hidden in %s.%n", getName()));
		if (this.getItems().isEmpty()) {
			buffer.append(String.format("The trove has been emptied...%n"));
		} else {
			buffer.append(String.format("You find a %s%nWhat do you do?%n", this.getItems().get(0).getName()));
		}
		setTextDump(buffer);
	}
	
	@Override
	public void updateChoices() {
		// TODO Auto-generated method stub
			ArrayList<Choices> choices = new ArrayList<>();
			if (!(this.getItems().isEmpty())) {
				choices.add(new Choices(3, this.getItems().get(0)));
			} 
			super.updateChoices(choices);
				setChoices(choices);
	}

	@Override
	public void setChoiceText() {
		// TODO Auto-generated method stub
		StringBuilder buffer = new StringBuilder("");
		ArrayList<Choices>choices = getChoices();
		for (int i = 0; i<getChoices().size(); i++) {
			buffer.append(String.format("%d. %s%n", i+1, choices.get(i)));
		}
		setChoiceText(buffer);
	}
}
