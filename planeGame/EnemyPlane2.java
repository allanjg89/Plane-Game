package planeGame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class EnemyPlane2 extends Enemy {
	Random generator = new Random(1234567);
	Random gen;
	int currBullImg;
	double theta;
	static double[] thetas = {Math.PI/5,0,-Math.PI/5};
	
	

    EnemyPlane2(Image img, Random gen) {
    	//this.gameEvents = gmEvnt;
    	this.health = enemy2Health;
    	this.theta = thetas[0];
    	currBullImg = 0;
    	bullImg = Animation.animations.get("bullets").elementAt(currBullImg);
    	this.notBullet = true;
    	this.points = 20;
    	explosionType = "explosion2";
    	enemyType = 1;
    	this.framesSinceLastBullet = 0;
    	this.framesPerBullet = 40;
        this.img = img;
        this.x = Math.abs(gen.nextInt() % (screenWidth - 30));
        this.y = -20;
        this.yspeed = 2;
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
        	
			if(currBullImg<Animation.animations.get("bullets").size()){
        		bullImg = Animation.animations.get("bullets").elementAt(currBullImg);
        		theta = thetas[currBullImg];
        		currBullImg++;
        		
        	}else{
        		currBullImg = 0;
        		bullImg = Animation.animations.get("bullets").elementAt(currBullImg);
        		theta = thetas[currBullImg];
        	}
        	enmBullets.add(new EnemyBullet(bullImg, x, y, bullSpeed*1.5*Math.sin(theta)+xspeed, 
        			bullSpeed*1.5*Math.cos(theta)+yspeed, bullet2Health,12));
        }
        
        
    
    }

}
