package gameLoop;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundLoop {
	String playSound = ".//res//ChaliceAudioLoop.wav";
	Clip clip;
	
	public void setFile(String soundFileName) {
		
		try {
			File file = new File(soundFileName);
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);
			this.clip = AudioSystem.getClip();
			this.clip.open(sound);
		} catch(Exception e) {
			System.out.println("Your file is wack'd kid!");
			System.exit(0);
		}
	}
	
	public void play() {
		this.clip.setFramePosition(0);
		this.clip.start();
		this.clip.loop(666);
	}
	
	public String getPlaySound() {
		return this.playSound;
	}
}
