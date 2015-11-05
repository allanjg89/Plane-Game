package planeGame;


import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Plane extends Player {
	int hx,hy,lx,ly,sx,sy;
	 Plane(Image img, int x, int y, int speed, int playerNum) {
		//this.gameEvents = gE
		 hy = screenHeight-70; ly =10; sy =screenHeight - 200;
		 if(playerNum==2){
			 hx = 25;  lx =hx;  sx = -10; 
		 }else{
			 hx = screenWidth-130; lx = screenWidth-40; sx = screenWidth - 200;
		 }
		healthBar = new VariableImg("healthBar", hx, hy);
		lives = new VariableImg("lives", lx,ly);
		scoreImg = new ScoreImg("Player "+playerNum+" Score: "+score,sx, sy);
		GameObj.animations.add(healthBar);
		GameObj.animations.add(lives);
		GameObj.animations.add(scoreImg);
		//GameObj.scoreImages.add(scoreImg);
		this.score = 0;
		this.playerNum = playerNum;
		this.health = maxHealth;
		this.powerUp = false;
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        boom = false;
        try {
			this.bullImg = ImageIO.read(new File("Resources/bullet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	 protected void geEvent1(GameEvents ge){
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
            	  plyBullets.add(new PlayerBullet(bullImg, x+(int)(this.width/3.5), y, 0, 10,damage, this));
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
            	  plyBullets.add(new PlayerBullet(bullImg, x+(int)(this.width/3.5), y, 0, 10,damage, this)); 
              }
              
            }
        	
        }
	}

	

	
}
