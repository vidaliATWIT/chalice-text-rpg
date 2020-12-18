package gameLoop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import entities.Monster;
import entities.Player;
import items.Actor;
import items.Item;
import items.Treasure;
import items.Weapon;
import locations.Choices;
import locations.Dungeon;
import locations.Guildhall;
import locations.Location;
import locations.Road;
import locations.Trove;
import gameLoop.SoundLoop;
import ui.UserInterface;

import java.util.Random;

public class Game implements ActionListener {
	UserInterface ui = new UserInterface();
	boolean gameOver = false;
	ArrayList<Location> world = new ArrayList<Location>();
	Player PC = new Player();
	Choices playerChoice;
	Monster Enemy = new Monster();
	Actor Guard = new Actor("Crawler");
	Weapon sword = new Weapon("Sword", 6, 5);
	Location curLoc;
	StringBuilder textDumpBuf;
	StringBuilder choiceTextBuf;
	StringBuilder statusTextBuf = new StringBuilder("");
	Monster curEnemy;
	Dungeon curDungeon;
	StringBuilder combatChoices = new StringBuilder("");
	StringBuilder combatState;
	String charCreateChoices;
	String charCreateState;
	ArrayList<String> choiceNamesBuf = new ArrayList<>();
	ArrayList<String> combatChoiceNames = new ArrayList<>();
	String playSound =".//res//ChaliceAudioLoop.wav";
	SoundLoop sl = new SoundLoop();
	int PCState = 3;

	public Game(String name, int i) {
		charCreate();
		this.world = createWorld();
		configWorld(); 
	}

	public Game() {
		initActionListener();
		this.world = createWorld();
		configWorld();
		refresh();
	}
	
	private ArrayList<Location> createWorld() {
		ArrayList<Location> World = new ArrayList<>();
		World.add(new Road("Main Road", 1));
		World.add(new Dungeon("The Cave of Polyhedron", 2, this.Enemy, this.Enemy.getHead()));
		World.add(new Guildhall("Adventurer's Outpost", 3, Guard = new Actor(World.get(1).getEnemy().getName())));
		World.add(new Trove("The Bush", 4));
		return World;
	}

	private void configWorld() {
		for (int i = 0; i < this.world.size(); i++) {
			if (this.world.get(i) instanceof Road) {
				((Road) this.world.get(i)).addLocation(this.world.get(1));
				((Road) this.world.get(i)).addLocation(this.world.get(2));
				((Road) this.world.get(i)).addLocation(this.world.get(3));
				((Road) this.world.get(i)).updateChoices();
				((Road) this.world.get(i)).setChoiceText();
				((Road) this.world.get(i)).setTextDump();
			} else if (this.world.get(i) instanceof Dungeon) {
				((Dungeon) this.world.get(i)).addLocation(this.world.get(0));
				((Dungeon) this.world.get(i)).updateChoices();
				((Dungeon) this.world.get(i)).setChoiceText();
				((Dungeon) this.world.get(i)).setTextDump();
			} else if (this.world.get(i) instanceof Guildhall) {
				((Guildhall) this.world.get(i)).addLocation(this.world.get(0));
				((Guildhall) this.world.get(i)).updateChoices();
				((Guildhall) this.world.get(i)).setChoiceText();
				((Guildhall) this.world.get(i)).setTextDump();
			} else if (this.world.get(i) instanceof Trove) {
				((Trove) this.world.get(i)).addLocation(this.world.get(0));
				((Trove) this.world.get(i)).updateChoices();
				((Trove) this.world.get(i)).setChoiceText();
				((Trove) this.world.get(i)).setTextDump();
			}
		}
		setCurLoc(this.world.get(2)); // initializes the first location
	}

	public void death(Dungeon dun) {
		setTextDumpBuf(new StringBuilder(
				String.format("You were killed in %s by a %s", dun.getName(), dun.getEnemy().getName())));
	}

	public void win(Dungeon dun) {
		setTextDumpBuf(
				new StringBuilder(String.format("You defeated the %s of %s", dun.getEnemy().getName(), dun.getName())));
	}

	public void textCheck() {
		if (getPCState() == 0) {
			setTextDumpBuf(this.curLoc.getTextDump());
			setChoiceTextBuf(this.curLoc.getChoiceText());
		} else if (getPCState() == 2) {
			setTextDumpBuf(combatState);
			setChoiceTextBuf(combatChoices);
		} else if (getPCState() == 3) {
			setTextDumpBuf(this.charCreateState);
			setChoiceTextBuf(this.charCreateChoices);
		}
	}

	private void setTextDumpBuf(String s) {
		this.textDumpBuf = new StringBuilder(s);
	}

	private void setChoiceTextBuf(String s) {
		this.choiceTextBuf = new StringBuilder(s);
	}

	public void stateCheck() {
		choiceNamesBuf.clear();
		// location state check
		curLoc.updateChoices();
		curLoc.setChoiceText();
		curLoc.setChoiceNames();
		
		if (getPCState()==0){
			this.ui.buttonCheck(curLoc.getChoices().size());
			choiceNamesBuf = curLoc.getChoiceNames();
		}
		curLoc.setTextDump();
		if (getPCState() == 2) {
			this.ui.buttonCheck(2);
			updateCombatState();
			choiceNamesBuf = combatChoiceNames;
		} 
	}

	public void loop() {
			stateCheck();
			textCheck();
			render();
	}
	
	public void charCreate() {
			charCreateState();
			setTextDumpBuf(this.charCreateState);
			setChoiceTextBuf(this.charCreateChoices);
			titleScreenRender();
			refresh();
	}
	
	public void titleScreenRender() {
		String s = this.textDumpBuf.toString();
		this.ui.setMainTextArea(s);
	}

	public void render() {
		this.ui.setMainTextArea(textDumpBuf.toString());
		this.ui.setStatus(statusTextBuf.toString());
		this.ui.buttonName(choiceNamesBuf);
		this.ui.locName(this.curLoc.getName());
		/**
		System.out.println(statusTextBuf);
		System.out.println(textDumpBuf);
		System.out.println(choiceTextBuf);
		**/
	}

	public void charCreateState() {
		if (this.PC.getName()==null) {
			this.charCreateState = String.format(
					"These are dark lands... %n%nAdventurer, what is your name?");
			this.charCreateChoices = "";
		} else if (this.PC.getProf()==null) {
			this.charCreateState = String.format("%s please choose your class: ", this.PC.getName());
			this.charCreateChoices = String.format("1. Thief%n2. Fighter%n3. Magic-User");
		} else {
			this.charCreateState = "You are ready...";
			this.charCreateChoices = "";
			setPCState(0);
		}

	}
	
	public void exploreProcess(String i) {
		if (i.equals("i")) {
			playerInventory();
		} else if (i.equals("c")) {
			playerSheet();
		} 
		
		try {
			int in = Integer.parseInt(i);
			setPlayerChoice(curLoc.getChoices().get(in - 1)); // allocates player choice
		} catch (IndexOutOfBoundsException | IllegalArgumentException ex) {
			return;
		}
		if (getPlayerChoice().getId() == 1) { // talk
			playerTalk();
		} else if (getPlayerChoice().getId() == 2) { // move
			playerMove();
		} else if (getPlayerChoice().getId() == 3) { // pick up
			playerPick();
		} else if (getPlayerChoice().getId() == 4) { // fight
			playerFight();
		} else if (getPlayerChoice().getId() == 5) { // run away
			
		} else if (getPlayerChoice().getId() == 6) { // give
			playerGive();
		} else if (getPlayerChoice().getId() == 7) { // get mission from
			playerMission();
		} 
		
		else {
			statusHandle(new StringBuilder(""));
		}
	}

	public void combatProcess(String in) {
		if (in.equals("1")) { // attack
			attack(this.PC.getStr(), this.PC.getDMG());
		} else if (in.equals("2")) { // run
			setPCState(0);
		}
		dodge(this.PC.getDex());

		if (this.PC.getCurHP() <= 0) { // death
			endScreen(String.format("%s was killed by a %s in the dank halls of %s%n%nGame Over!%n", this.PC.getName(),
					curEnemy.getName(), this.curLoc.getName()));
			setGameOver(true);
		}
		if (this.Enemy.getCurHP() <= 0) {
			statusHandle(String.format("you have killed the %s", curEnemy.getName()));
			((Dungeon) curLoc).setEnemyDead(true);
			setPCState(0);
		}
	}

	public void statusHandle(StringBuilder s) {
		setStatusTextBuf(s);
	}

	public void statusAppend(String s) {
		this.statusTextBuf.append(s);
	}

	private void statusHandle(String format) {
		setStatusTextBuf(new StringBuilder(format));
	}

	public void playerTalk() { // handles talk action
		if (curLoc.getActors().get(0) instanceof Actor && curLoc instanceof Guildhall) {
			isHead(this.PC, curLoc);
		} else {
			statusHandle(curLoc.getActors().get(0).getGreeting());
		}
	}

	public void playerMove() { // handles move action
		curLoc.setHasBeen(true);
		setCurLoc((Location) getPlayerChoice().getSubject());
		statusHandle(new StringBuilder(""));
	}

	public void playerPick() { // handles pickup action
		statusHandle(String.format("You have picked up the %s ", (Item) getPlayerChoice().getSubject()));
		this.PC.addItem((Item) getPlayerChoice().getSubject());
		curLoc.removeItem((Item) getPlayerChoice().getSubject());
		if ((Item) getPlayerChoice().getSubject() instanceof Treasure) {
			((Dungeon) curLoc).setHasHead(true);
		}
	}

	public void playerFight() { // handles initiate fight action
		initCombat(curLoc.getEnemy(), (Dungeon) curLoc);
	}

	public void playerRun() { // handles exit fight action
		
	}

	public void playerGive() { // handles give item action
		this.PC.removeItem((Item) getPlayerChoice().getSubject());
	}

	public void playerMission() { // handles taking mission
		statusHandle(curLoc.getActors().get(0).getMissionProvide());
		((Guildhall) curLoc).setHasQuest(true);
	}

	public void playerInventory() { // handles showing inventory
		statusHandle(this.PC.printInv());
	}
	
	public void playerSheet() { //handles showing character sheet
		statusHandle(this.PC.printSheet());
	}

	public void isHead(Player pc, Location guild) {
		Treasure t = null;
		for (int i = 0; i < pc.getInventory().size(); i++) {
			if (pc.getInventory().get(i) instanceof Treasure) {
				t = (Treasure) pc.getInventory().get(i);
				pc.setHasTreasure(true);
			}
		}
		if (pc.isHasTreasure()) {
			((Guildhall) guild).setHasQuest(false);
			((Guildhall) guild).setQuestCount(1);
			pc.removeItem(t);
			endScreen(guild.getActors().get(0).getMissionSuccess().toString());
		} else {
			statusHandle(new StringBuilder(String.format("Get out of here kid, you've got a %s to kill.", this.Enemy.getName())));
		}
	}

	public void initCombat(Monster enemy, Dungeon loc) {
		setPCState(2);
		setCurEnemy(enemy);
		statusHandle(String.format("The %s lumbers forward from the shadows...%n", enemy.getName()));
	}

	public void updateCombatState() {
		combatChoiceNames.add("fight");
		combatChoiceNames.add("run");
		setCombatState(new StringBuilder(String.format("your hp: %d%n%s hp: %d%n%n",
				this.PC.getCurHP(), curEnemy.getName(), curEnemy.getCurHP())));
	}

	public int roll(int max) {
		Random rollRan = new Random();
		return rollRan.nextInt(max - 1) + 1;
	}

	public void attack(int stat, int DMG) {
		if (roll(20) < stat) {
			this.curEnemy.setCurHP(this.curEnemy.getCurHP() - roll(DMG));
			statusHandle(String.format("You slashed at the enemy with your weapon!"));
		} else {
			statusHandle("Your attack missed!");
		}
	}

	public void dodge(int stat) {
		int dmg = 0;
		if (roll(20) >= stat) {
			dmg = roll(curEnemy.getDMG());
			this.PC.setCurHP(this.PC.getCurHP() - dmg);
			statusAppend(String.format("%nYou were hit for %d damage", dmg));
		} else {
			statusAppend(String.format("%nYou dodged the %s's attack", curEnemy.getName()));
		}
	}
	public void uiInit() {
		this.ui.initialize();
	}
	
	public void refresh() {
		this.ui.refresh();
	}
	
	public void endScreen(String s) {
		setGameOver(true);
		this.ui.hideButtons();
		this.ui.setMainTextArea(s);
		this.ui.setStatus("");
		refresh();
	}
		
	public void initActionListener() {
		this.ui.getStartButton().addActionListener(this);
		if (getPCState()==0) {
			this.ui.getChoice1().addActionListener(this);
			this.ui.getChoice2().addActionListener(this);
			this.ui.getChoice3().addActionListener(this);
			this.ui.getChoice4().addActionListener(this);
			this.ui.getChoice5().addActionListener(this);
			this.ui.getInv().addActionListener(this);
			this.ui.getCharSheet().addActionListener(this);
		}
	}
	
	public void handleStart() {
		this.sl.setFile(playSound);
		this.sl.play();
		this.ui.initializeCharCreateScreen();
		this.ui.getNameField().addActionListener(this);
		charCreate();
	}
	
	public void handleEnd() {
		this.ui.initializeGameScreen();
		setPCState(0);
		initActionListener();
		loop();
	}
	
	public void initNameField() {
		this.ui.getNameFieldPanel().setVisible(false);
		this.ui.getClassPanel().setVisible(true);
		this.ui.getFighterButton().addActionListener(this);
		this.ui.getThiefButton().addActionListener(this);
		this.ui.getMagicUserButton().addActionListener(this);
		charCreate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (getPCState()==0) {
			if (e.getSource()==this.ui.getChoice1()) {
				exploreProcess("1");
			} else if (e.getSource()==this.ui.getChoice2()) {
				exploreProcess("2");
			} else if (e.getSource()==this.ui.getChoice3()) {
				exploreProcess("3");
			} else if (e.getSource()==this.ui.getChoice4()) {
				exploreProcess("4");
			} else if (e.getSource()==this.ui.getChoice5()) {
				exploreProcess("5");
			} else if (e.getSource()==this.ui.getInv()) {
				exploreProcess("i");
			} else if (e.getSource()==this.ui.getCharSheet()) {
				exploreProcess("c");
			}
		} else if (getPCState()==2) {
			if (e.getSource()==this.ui.getChoice1()) {
				combatProcess("1");
			} else if (e.getSource()==this.ui.getChoice2()) {
				combatProcess("2");
			}
		} else if (getPCState()==3) {
			if (e.getSource()==this.ui.getStartButton()) {
				handleStart();
			} else if (e.getSource()==this.ui.getFighterButton()) {
				this.PC.setProf(2);
				handleEnd();
			} else if (e.getSource()==this.ui.getThiefButton()) {
				this.PC.setProf(1);
				handleEnd();
			} else if (e.getSource()==this.ui.getMagicUserButton()) {
				this.PC.setProf(3);
				handleEnd();
			} else if (e.getSource()==this.ui.getNameField()) {
				this.PC.setName(this.ui.getNameField().getText());
				initNameField();
			}
			charCreateState();
		} 
		if (!gameOver &&((getPCState()==0)||(getPCState()==2))) {
			loop();
		} 
	}
	
	//the well of misery-- getters and setters!

	public void setPlayerChoice(Choices choice) {
		this.playerChoice = choice;
	}

	public Choices getPlayerChoice() {
		return this.playerChoice;
	}

	public StringBuilder getTextDumpBuf() {
		return textDumpBuf;
	}

	public void setTextDumpBuf(StringBuilder textDumpBuf) {
		this.textDumpBuf = textDumpBuf;
	}

	public StringBuilder getChoiceTextBuf() {
		return choiceTextBuf;
	}

	public void setChoiceTextBuf(StringBuilder choiceTextBuf) {
		this.choiceTextBuf = choiceTextBuf;
	}

	public Location getCurLoc() {
		return curLoc;
	}

	public void setCurLoc(Location curLoc) {
		this.curLoc = curLoc;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public StringBuilder getStatusTextBuf() {
		return statusTextBuf;
	}

	public void setStatusTextBuf(StringBuilder statusTextBuf) {
		this.statusTextBuf = statusTextBuf;
	}

	public Monster getCurEnemy() {
		return curEnemy;
	}

	public void setCurEnemy(Monster curEnemy) {
		this.curEnemy = curEnemy;
	}

	public StringBuilder getCombatChoices() {
		return combatChoices;
	}

	public void setCombatChoices(StringBuilder combatChoices) {
		this.combatChoices = combatChoices;
	}

	public StringBuilder getCombatState() {
		return combatState;
	}

	public void setCombatState(StringBuilder combatState) {
		this.combatState = combatState;
	}

	public int getPCState() {
		return this.PCState;
	}

	public void setPCState(int pCState) {
		this.PCState = pCState;
	}
	

}


