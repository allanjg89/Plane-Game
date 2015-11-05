package planeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import TankGame.myPlaneGame;

public abstract class Player extends GameObj implements Observer  {
	Rectangle bbox;
    protected boolean boom;
    public int playerNum;
    public int score;
    protected int framesSinceLastBullet;
    protected int damage;
    public boolean powerUp;
    
    protected Image bullImg;
    protected ScoreImg scoreImg;
    
    
   
    

    
    
    @Override
	public void update(Observable obj, Object arg) {
       
    	if(!this.isNull()){
	    	GameEvents ge = (GameEvents) arg;
	        
	        healthBar.update(this.health/((double)maxHealth) < 
	        		(1-healthBar.currImage/((double)healthBar.numOfImages) -
	        				1/(2*(double)healthBar.numOfImages)));
	        //lives.update(this.health<=0);
	        if(lives.img==null){
	        	this.img = null;
	        	healthBar.img = null;
	        	animations.add(new AnimationEvent("explosion2",x,y));
	        	Sounds.audioClips.get("explosion2").play();
	        }else if(healthBar.currImage==healthBar.numOfImages){
	        	lives.update(true);
	        	healthBar.currImage = 0;
	        	myPlaneGame.screenObj.add(healthBar);
	        	health = maxHealth;
	        }
	        scoreImg.update("Player "+this.playerNum+" Score: "+score);
	        
	        //try {
	      	  if(powerUp){
	      		 
	      		  damage = playerDamg2;
	      		  //bullImg = ImageIO.read(new File("Resources/bigBullet.png"));
					
	      	    }else{
	      	      damage = playerDamg1;
	      	    	//bullImg = ImageIO.read(new File("Resources/bullet.png"));
	      	    }
	    	/*} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
	    	}*/
	        
	        if(!this.isNull()){
		        if(ge.type == 1) {
		            geEvent1(ge);
		        	
		        }
		        else if(ge.type == 2) {
		        	geEvent2(ge);
		        }
	        }
    	}
    }

    


	/*public int getPlyNum() {
		return playerNum;
	}*/
	
	 protected abstract void geEvent1(GameEvents ge);/*{
		KeyEvent e = (KeyEvent) ge.event;
        if(playerNum==1){
            switch (e.getKeyCode()) {    
            case KeyEvent.VK_LEFT:
                    x -= speed;
        	break; 
                case KeyEvent.VK_RIGHT:
                    x += speed;
        	break;
                case KeyEvent.VK_UP:
                    y -= speed;
        	break; 
                case KeyEvent.VK_DOWN:
                    y += speed;
        	break;
                default:
              if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            	  plyBullets.add(new PlayerBullet(bullImg, x+(int)(this.width/3.5), y, 10,damage, this));
                    //System.out.println("Fire");  
              }
              
            }
            
        }else{
        	switch (e.getKeyCode()) {    
            case KeyEvent.VK_A:
                    x -= speed;
        	break; 
                case KeyEvent.VK_D:
                    x += speed;
        	break;
                case KeyEvent.VK_W:
                    y -= speed;
        	break; 
                case KeyEvent.VK_X:
                    y += speed;
        	break;
                default:
              if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
            	  plyBullets.add(new PlayerBullet(bullImg, x+(int)(this.width/3.5), y, 10,damage, this)); 
              }
              
            }
        	
        }
	}*/
	
	private void geEvent2(GameEvents ge){
		String msg = (String)ge.event;
        if(msg.equals("collision1" + playerNum)) {
            //System.out.println("Explosion! Reduce Health");
        	health = health-Enemy.enemy1Health;
        	

        }
        if(msg.equals("collision11" + playerNum)) {
            //System.out.println("Explosion! Reduce Health");
        	health = health-Enemy.bullet1Health;
        }
        if(msg.equals("collision12" + playerNum)) {
            //System.out.println("Explosion! Reduce Health");
        	health = health-Enemy.bullet2Health;
        }
        
	}
	
	
	
}
