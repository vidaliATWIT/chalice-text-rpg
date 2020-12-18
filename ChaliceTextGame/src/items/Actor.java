package items;

public class Actor {
	private String name;
	private int id;
	private StringBuilder greeting;
	private StringBuilder missionProvide;
	private StringBuilder missionSuccess;
	
	public Actor(String monster) {
		setName("Guildmaster");
		setId(32);
		setGreeting(new StringBuilder("Hey there traveller!"));
		setMissionProvide(new StringBuilder("You must provide me the head of a "+monster+"..."));
		setMissionSuccess(new StringBuilder(String.format("Congratulations on defeating the " +monster+", you are now a member of the guild!%n%nGAME OVER!")));
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
	public StringBuilder getGreeting() {
		return this.greeting;
	}
	public void setGreeting(StringBuilder greeting) {
		this.greeting = greeting;
	}
	public StringBuilder getMissionSuccess() {
		return this.missionSuccess;
	}
	public void setMissionSuccess(StringBuilder missionSuccess) {
		this.missionSuccess = missionSuccess;
	}
	
	public void catchItem() {
		
	}

	public StringBuilder getMissionProvide() {
		return missionProvide;
	}

	public void setMissionProvide(StringBuilder missionProvide) {
		this.missionProvide = missionProvide;
	}
	
	
	
}
