package planeGame;


import java.awt.Image;


public abstract class Enemy extends GameObj {
	public static int enemy1Health=playerDamg1;
	public static int enemy2Health=playerDamg1*2;
	public static int enemy3Health=Player.maxHealth;
	public static int bullet1Health = 5;
	public static int bullet2Health = 10;
	public static int bullet3Health = Player.maxHealth/3;
    protected double xspeed, yspeed;
    protected int framesSinceLastBullet;
    protected int framesPerBullet;
    protected Image bullImg;
    protected int bullSpeed = 2;
    protected int enemyType;
    protected boolean notBullet;
    protected int points;

    
    public void checkCollision(){
    	for (int i = 0; i < players.size(); i++) {
    		player = (Player)players.get(i);
    		
	        if(player.collision(x, y, width, height)) {
	        	
				gameEvents.setValue("collision" + enemyType + player.playerNum);
	            //gameEvents.setValue("");
				if(healthCheck(player)){
					player.score += this.points;
				}
	        }
	        else 
	        	gameEvents.setValue("");
	    }
    	
    	if(notBullet){
	    	for (int i = 0; i < plyBullets.size(); i++) {
	    		plyBull = (PlayerBullet)plyBullets.get(i);
	
		        if(plyBull.collision(x, y, width, height)) {
		        	
		        	if(healthCheck(plyBull)&&players.size()>0){
		        		/*for (int j = 0; j < players.size(); j++){
		        			player = (Player)players.get(j);
		        			if( player.playerNum == plyBull.playerNum ){
		        				player.score += this.points;
		        			}
		        		}*/
		        		plyBull.itsPlayer.score += this.points;
		        	}
		        	plyBull.img=null;
		        	plyBullets.remove(i);
		        	
					
		        }
		        else 
		        	gameEvents.setValue("");
		    }
    
    	}
    }
    
    public boolean healthCheck(GameObj obj){
    	this.health -= obj.health;
		if(this.health<=0){
            animations.add(new AnimationEvent(this.explosionType,x,y));
            this.img = null;
            if(Sounds.audioClips.containsKey(this.explosionType)){
	            Sounds.audioClips.get(this.explosionType).play();
            }
            return true;
		}
		return false;
    }
    
        
}
