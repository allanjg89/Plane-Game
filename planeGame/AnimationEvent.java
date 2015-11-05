package planeGame;

import java.awt.Image;
import java.util.Random;
import java.util.Vector;

public class AnimationEvent extends Animation {
	 
	public AnimationEvent(String animationType, int x, int y) {
		 images = animations.get(animationType);
         currImage = 0;
         framesSinceInit = 1;
         numFramesPerImg = 3;
         img = images.elementAt(currImage);
         numOfImages = images.size();
         this.x = x;
         this.y = y;
         //this.speed = 0;
     }

	@Override
	public void update() {
		if(currImage < numOfImages ){
			framesSinceInit++;
			//System.out.println(currImage);
			img = images.elementAt(currImage);
			if((framesSinceInit % numFramesPerImg)==0){
				currImage++;
			}
			
		}else{
			img = null;
		}
	}
	
	
}

