package ui;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class UserInterface extends JFrame{
	
	private static final long serialVersionUID = 3L;
	JFrame window;
	Container con;
	JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, locNamePanel, playerOptionPanel, statusPanel, nameFieldPanel, classPanel;
	JLabel titleNameLabel, locName1;
	JTextField nameField;

	Font gobbyTitle;
	Font gobbySubTitle;
	Font gobbyChoice;
	Font gobbyLoc;
	Font gobbyStatus;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 80);
	Font subTitleFont = new Font("Times New Roman", Font.PLAIN, 25);
	Font choiceFont = new Font("Times New Roman", Font.PLAIN, 15);
	Font locFont = new Font("Times New Roman", Font.BOLD, 30);
	Font statusFont = new Font("Times New Roman", Font.BOLD, 15);

	
	JButton startButton, fighterButton, thiefButton, magicUserButton, choice1, choice2, choice3, choice4, choice5, inv, charSheet;

	JTextArea mainTextArea, locName, statusArea;

	ArrayList<JPanel> panelBuffer = new ArrayList<JPanel>();
	
	ArrayList<JButton> buttons = new ArrayList<>();
	
	public UserInterface() {
		initialize();
	}
	
	public void initialize() {
		try {
			gobbyTitle = Font.createFont(Font.TRUETYPE_FONT, new File(".//res//OldSchoolAdventures-42j9.ttf")).deriveFont(50f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(gobbyTitle);
			gobbySubTitle = Font.createFont(Font.TRUETYPE_FONT, new File(".//res//OldSchoolAdventures-42j9.ttf")).deriveFont(15f);
			ge.registerFont(gobbySubTitle);
			gobbyChoice = Font.createFont(Font.TRUETYPE_FONT, new File(".//res//OldSchoolAdventures-42j9.ttf")).deriveFont(8f);
			ge.registerFont(gobbyChoice);
			gobbyLoc = Font.createFont(Font.TRUETYPE_FONT, new File(".//res//OldSchoolAdventures-42j9.ttf")).deriveFont(13f);
			ge.registerFont(gobbyLoc);
			gobbyStatus = Font.createFont(Font.TRUETYPE_FONT, new File(".//res//OldSchoolAdventures-42j9.ttf")).deriveFont(9f);
			ge.registerFont(gobbyStatus);
			
		} catch (IOException | FontFormatException ex) {
			
		}
 		window = new JFrame();
		window.setSize(640, 480);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setVisible(true);
		window.setResizable(false);
		con = window.getContentPane();
		initializeTitleScreen();
	}
	
	

	public void initializeTitleScreen() {
		this.titleNamePanel = new JPanel();
		this.titleNamePanel.setBounds(70, 100, 480, 100);
		this.titleNamePanel.setBackground(Color.black);
		this.titleNameLabel = new JLabel("CHALICE");
		this.titleNameLabel.setForeground(Color.white);
		this.titleNameLabel.setFont(gobbyTitle);
		
		this.startButtonPanel = new JPanel();
		this.startButtonPanel.setBounds(210, 270, 200, 100);
		this.startButtonPanel.setBackground(Color.black);
		
		this.startButton = new JButton("START");
		this.startButton.setBackground(Color.black);
		this.startButton.setForeground(Color.white);
		this.startButton.setFont(gobbySubTitle);
		this.startButton.setFocusPainted(false);
		
		this.titleNamePanel.add(titleNameLabel);
		this.startButtonPanel.add(startButton);
		
		this.panelBuffer.add(titleNamePanel);
		this.panelBuffer.add(startButtonPanel);
		
		this.con.add(titleNamePanel);
		this.con.add(startButtonPanel);
	}
	
	public void initializeCharCreateScreen() {
		initializeMainTextPanel();
		initializeNamePanel();
		initializeClassPanel();
		mainTextArea = new JTextArea(String.format(
				"These are dark lands... %nYou've travelled much through thicket and wild to arrive to these lands... %nPray, tell me... what is your name?"));
		mainTextArea.setBounds(70, 100, 480, 250);
		mainTextArea.setBackground(Color.black);
		mainTextArea.setForeground(Color.white);
		mainTextArea.setFont(gobbySubTitle);
		mainTextArea.setLineWrap(true);
		mainTextArea.setWrapStyleWord(true);
		mainTextArea.setEditable(false);
				
		fighterButton = new JButton("Fighter");
		fighterButton.setBackground(Color.black);
		fighterButton.setForeground(Color.white);
		fighterButton.setFont(gobbySubTitle);
		fighterButton.setFocusPainted(false);
		classPanel.add(fighterButton);
		
		thiefButton = new JButton("Thief");
		thiefButton.setBackground(Color.black);
		thiefButton.setForeground(Color.white);
		thiefButton.setFont(gobbySubTitle);
		thiefButton.setFocusPainted(false);
		classPanel.add(thiefButton);
		
		magicUserButton = new JButton("Magic-User");
		magicUserButton.setBackground(Color.black);
		magicUserButton.setForeground(Color.white);
		magicUserButton.setFont(gobbySubTitle);
		magicUserButton.setFocusPainted(false);
		classPanel.add(magicUserButton);

		mainTextPanel.add(mainTextArea);
	}
	
	public void initializeGameScreen() {
		bufferClear();
		mainTextPanel = new JPanel();
		mainTextPanel.setBounds(80, 90, 320, 180);
		mainTextPanel.setBackground(Color.black);
		con.add(mainTextPanel);
		
		mainTextArea = new JTextArea(String.format(""));
		mainTextArea.setBounds(80, 90, 320, 180);
		mainTextArea.setBackground(Color.black);
		mainTextArea.setForeground(Color.white);
		mainTextArea.setFont(gobbySubTitle);
		mainTextArea.setLineWrap(true);
		mainTextArea.setWrapStyleWord(true);
		mainTextArea.setEditable(false);
		
		mainTextPanel.add(mainTextArea);
		
		choiceButtonPanel = new JPanel();
		choiceButtonPanel.setBounds(80, 300, 320+160, 120);
		choiceButtonPanel.setBackground(Color.black);
		choiceButtonPanel.setLayout(new GridLayout(4, 1));
		con.add(choiceButtonPanel);
		
		choice1 = new JButton();
		choice1.setBackground(Color.black);
		choice1.setForeground(Color.white);
		choice1.setFont(gobbyChoice);
		choice1.setFocusPainted(false);
		buttons.add(choice1);
		choiceButtonPanel.add(choice1);
		
		choice2 = new JButton();
		choice2.setBackground(Color.black);
		choice2.setForeground(Color.white);
		choice2.setFont(gobbyChoice);
		choice2.setFocusPainted(false);
		buttons.add(choice2);
		choiceButtonPanel.add(choice2);
		
		choice3 = new JButton();
		choice3.setBackground(Color.black);
		choice3.setForeground(Color.white);
		choice3.setFont(gobbyChoice);
		choice3.setFocusPainted(false);
		buttons.add(choice3);
		choiceButtonPanel.add(choice3);
		
		choice4 = new JButton();
		choice4.setBackground(Color.black);
		choice4.setForeground(Color.white);
		choice4.setFont(gobbyChoice);
		choice4.setFocusPainted(false);
		buttons.add(choice4);
		choiceButtonPanel.add(choice4);
		
		choice5 = new JButton();
		choice5.setBackground(Color.black);
		choice5.setForeground(Color.white);
		choice5.setFont(gobbyChoice);
		choice5.setFocusPainted(false);
		buttons.add(choice5);
		choiceButtonPanel.add(choice5);
		
		playerOptionPanel = new JPanel();
		playerOptionPanel.setBounds(440, 0, 120, 60);
		playerOptionPanel.setBackground(Color.red);
		playerOptionPanel.setLayout(new GridLayout(1,2));
		con.add(playerOptionPanel);
		
		locNamePanel = new JPanel();
		locNamePanel.setBounds(80, 10, 320, 60);
		locNamePanel.setBackground(Color.black);
		con.add(locNamePanel);
		
		locName1 = new JLabel();
		locName1.setBounds(80, 0, 320, 60);
		locName1.setFont(gobbyLoc);
		locName1.setForeground(Color.GREEN);
		locNamePanel.add(locName1);
		
		inv = new JButton("[I]");
		inv.setBackground(Color.black);
		inv.setForeground(Color.white);
		inv.setFont(gobbyStatus);
		inv.setFocusPainted(false);
		playerOptionPanel.add(inv);
		
		charSheet = new JButton("[C]");
		charSheet.setBackground(Color.black);
		charSheet.setForeground(Color.white);
		charSheet.setFont(gobbyStatus);
		charSheet.setFocusPainted(false);
		playerOptionPanel.add(charSheet);
		
		statusPanel = new JPanel();
		statusPanel.setBounds(440, 90, 120, 180);
		statusPanel.setBackground(Color.black);
		con.add(statusPanel);
		
		statusArea = new JTextArea();
		statusArea.setBackground(Color.black);
		statusArea.setForeground(Color.white);
		statusArea.setFont(gobbyStatus);
		statusArea.setEditable(false);
		statusArea.setLineWrap(true);
		statusArea.setWrapStyleWord(true);
		statusPanel.add(statusArea);
	} 
	
	public void buttonCheck(int i) {
		for (int x=0; x<buttons.size();x++) {
			buttons.get(x).setVisible(false);
		}
		for (int j=0; j<i; j++) {
			buttons.get(j).setVisible(true);
		}
	}
	
	public void buttonName(ArrayList<String> arrayList) {
		for (int j=0; j<arrayList.size();j++) {
			buttons.get(j).setText(arrayList.get(j));
		}
	}
	
	public void locName(String name) {
		this.locName1.setText(name);
	}
	
	public void initializeCombatScreen() {
		
	}
	
	public void setMainTextArea(String t) {
		this.mainTextArea.setText(t);
		refresh();
	}
	
	public void initializeMainTextPanel() {
		bufferClear();
		mainTextPanel = new JPanel();
		mainTextPanel.setBounds(70, 70, 480, 200);
		mainTextPanel.setBackground(Color.black);
		con.add(mainTextPanel);
		this.panelBuffer.add(mainTextPanel);
	}
	
	public void initializeClassPanel() {
		classPanel = new JPanel();
		classPanel.setBounds(70, 275, 480, 100);
		classPanel.setBackground(Color.black);
		classPanel.setLayout(new GridLayout(4, 1));
		con.add(classPanel);
		this.panelBuffer.add(classPanel);
		classPanel.setVisible(false);
	}
	
	public void initializeNamePanel() {
		nameFieldPanel = new JPanel();
		nameFieldPanel.setBounds(70, 275, 480, 50);
		nameFieldPanel.setBackground(Color.black);
		con.add(nameFieldPanel);
		this.panelBuffer.add(nameFieldPanel);
		
		nameField = new JTextField(20);
		nameField.setBackground(Color.DARK_GRAY);
		nameField.setForeground(Color.white);
		nameField.setFont(gobbySubTitle);
		nameField.setEditable(true);
		nameFieldPanel.add(nameField);
	}
	
	public void refresh() {
		this.con.repaint();
		this.con.revalidate();
		//System.out.println("Screen refreshed...");
	}
	
	public void bufferClear() {
		for (int i=0; i<panelBuffer.size(); i+=0) {
			hidePanel(panelBuffer.get(i));
			panelBuffer.remove(i);
		}
	}
	
	public String replaceText(String text) {
		return text;
	}
	
	public StringBuilder replaceText(StringBuilder text) {
		return text;
	}
	
	public void hideButtons() {
		this.choiceButtonPanel.setVisible(false);
	}
	
	public void hidePlayerOptions() {
		this.playerOptionPanel.setVisible(false);
	}
	
	public void hidePanel(JPanel jp) {
		jp.setVisible(false);
	}
	
	public void showPanel(JPanel jp) {
		jp.setVisible(true);
	}
	
	public void setStatus(String s) {
		this.statusArea.setText(s);
	}
	
	public JFrame getWindow() {
		return window;
	}

	public Container getCon() {
		return con;
	}

	public JPanel getTitleNamePanel() {
		return titleNamePanel;
	}

	public JPanel getStartButtonPanel() {
		return startButtonPanel;
	}

	public JPanel getMainTextPanel() {
		return mainTextPanel;
	}

	public JPanel getChoiceButtonPanel() {
		return choiceButtonPanel;
	}

	public JPanel getLocNamePanel() {
		return locNamePanel;
	}

	public JPanel getPlayerOptionPanel() {
		return playerOptionPanel;
	}

	public JPanel getStatusPanel() {
		return statusPanel;
	}

	public JLabel getTitleNameLabel() {
		return titleNameLabel;
	}

	public JLabel getLocName1() {
		return locName1;
	}

	public Font getGobbyTitle() {
		return gobbyTitle;
	}

	public Font getGobbySubTitle() {
		return gobbySubTitle;
	}

	public Font getGobbyChoice() {
		return gobbyChoice;
	}

	public Font getGobbyLoc() {
		return gobbyLoc;
	}

	public Font getGobbyStatus() {
		return gobbyStatus;
	}

	public Font getTitleFont() {
		return titleFont;
	}

	public Font getSubTitleFont() {
		return subTitleFont;
	}

	public Font getChoiceFont() {
		return choiceFont;
	}

	public Font getLocFont() {
		return locFont;
	}

	public Font getStatusFont() {
		return statusFont;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public JButton getChoice1() {
		return choice1;
	}

	public JButton getChoice2() {
		return choice2;
	}

	public JButton getChoice3() {
		return choice3;
	}

	public JButton getChoice4() {
		return choice4;
	}

	public JButton getChoice5() {
		return choice5;
	}

	public JButton getInv() {
		return inv;
	}

	public JButton getCharSheet() {
		return charSheet;
	}

	public JTextArea getMainTextArea() {
		return mainTextArea;
	}

	public JTextArea getLocName() {
		return locName;
	}

	public JTextArea getStatusArea() {
		return statusArea;
	}

	public ArrayList<JPanel> getPanelBuffer() {
		return panelBuffer;
	}

	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	
	public JPanel getNameFieldPanel() {
		return nameFieldPanel;
	}

	public JPanel getClassPanel() {
		return classPanel;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JButton getFighterButton() {
		return fighterButton;
	}

	public JButton getThiefButton() {
		return thiefButton;
	}

	public JButton getMagicUserButton() {
		return magicUserButton;
	}
}
