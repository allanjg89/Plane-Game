package planeGame;

import java.awt.Image;
import java.util.HashMap;
import java.util.Vector;

public abstract class Animation extends GameObj {
	public static HashMap<String, Vector<Image>> animations = 
			new HashMap<String,Vector<Image>>();
	public  int currImage;
	public int numOfImages;
	protected int framesSinceInit;
	protected int numFramesPerImg;
	protected Vector<Image> images;
}
