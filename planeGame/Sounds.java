package planeGame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Sounds{
	public static HashMap<String, Sounds> audioClips 
		= new HashMap<String,Sounds>();
	
	URL URLSoundClip;
	AudioClip clip;
	
	public Sounds(String filename){
		try {
			URLSoundClip = new File(filename).toURI().toURL();
			clip = Applet.newAudioClip(URLSoundClip);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void play() {
		clip.play();
		
	}
	
}
