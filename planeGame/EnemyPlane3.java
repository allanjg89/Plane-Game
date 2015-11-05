package planeGame;

import java.awt.Image;
import java.util.Random;

public class EnemyPlane3 extends Enemy {
	Random generator = new Random(1234567);
	Random gen;
	int currBullImg;
	int speed;
	double theta;
	static double[] thetas = {Math.PI/5,0,-Math.PI/5};
	
	EnemyPlane3(Image img, Random gen) {
    	//this.gameEvents = gmEvnt;
		this.speed =2;
    	this.health = enemy3Health;
    	this.theta = thetas[0];
    	currBullImg = 1;
    	bullImg = Animation.animations.get("bullets").elementAt(currBullImg);
    	this.notBullet = true;
    	this.points = 100;
    	explosionType = "explosion2";
    	enemyType = 1;
    	this.framesSinceLastBullet = 0;
    	this.framesPerBullet = 30;
        this.img = img;
        this.x = Math.abs(gen.nextInt() % (screenWidth - 30));
        this.y = -20;
        this.yspeed = 2;
        this.xspeed = speed;
        this.gen = gen;
        //this.show = true;
        width = img.getWidth(null);
        height = img.getHeight(null);
        //System.out.println("w:" + width + " y:" + height);
        healthBar = new VariableImg("healthBar", (int)(screenWidth/1.5),20);
        GameObj.animations.add(healthBar);
   }
    public void reset() {
        this.x = Math.abs(generator.nextInt() % (600 - 30));
        this.y = -10;
    }
    
    public void update() {
    	healthBar.update(this.health/((double)enemy3Health) < 
        		(1-healthBar.currImage/((double)healthBar.numOfImages) -
        				1/(2*(double)healthBar.numOfImages)));
    	
        y += yspeed;
        x += xspeed;
        framesSinceLastBullet++;
        checkCollision();
        
        if(x>screenWidth-30){
        	this.xspeed = -speed;
        	//currBullImg = 0;
        }else if(x<30){
        	this.xspeed = speed;
        	//currBullImg = 2;
        }
        
        if(y>70){
        	this.yspeed = 0;
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
