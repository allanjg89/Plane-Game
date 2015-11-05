package planeGame;



import java.awt.Image;
import java.util.Random;

import planeGame.GameObj;

public class Scenery extends GameObj {
	 Random gen;

     public Scenery(Image img, int x, int y, int speed, Random gen) {
    	 this.img = img;
         this.x = x;
         this.y = y;
         this.speed = speed;
         this.gen = gen;
     }

	@Override
	public void update() {
		y += speed;
        if (y >= screenHeight) {
            y = -100;
            x = Math.abs(gen.nextInt() % (screenWidth- 30));
        }
		
	}

}


