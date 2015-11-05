package planeGame;


import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class EnemyPlane1 extends Enemy {
	Random generator = new Random(1234567);
	Random gen;
	
	

    EnemyPlane1(Image img, Random gen) {
    	//this.gameEvents = gmEvnt;
    	this.health = enemy1Health;
    	bullImg = Animation.animations.get("bullets").elementAt(1);
    	this.notBullet = true;
    	this.points = 10;
    	explosionType = "explosion1";
    	enemyType = 1;
    	this.framesSinceLastBullet = 0;
    	this.framesPerBullet = 65;
        this.img = img;
        this.x = Math.abs(gen.nextInt() % (screenWidth - 30));
        this.y = -20;
        this.yspeed = 1;
        this.xspeed = 0;
        this.gen = gen;
        //this.show = true;
        width = img.getWidth(null);
        height = img.getHeight(null);
        //System.out.println("w:" + width + " y:" + height);
   }
    public void reset() {
        this.x = Math.abs(generator.nextInt() % (600 - 30));
        this.y = -10;
    }
    
    public void update() {
    	
        y += yspeed;
        x += xspeed;
        framesSinceLastBullet++;
        checkCollision();
        
        if(y>screenHeight){
        	this.reset();
        }
        
        if(framesSinceLastBullet % framesPerBullet == 0){
        	enmBullets.add(new EnemyBullet(bullImg, x, y, 0, bullSpeed, bullet1Health,11));
        }
        
        
    
    }
}
