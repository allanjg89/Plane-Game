package planeGame;

import java.awt.Image;

public class PlayerBullet extends GameObj {
	
	
	int playerNum;
	public Player itsPlayer;
	private int xspeed;
	private int yspeed;
	
	public PlayerBullet(Image img, int x, int y, int xspeed,int yspeed, 
			int damage, Player itsPlayer) {
		this.itsPlayer = itsPlayer;
		//this.playerNum = playerNum;
		this.img = img;
		this.x = x;
		this.y = y;
		this.xspeed = xspeed;
		this.yspeed = yspeed;
		this.health = damage;
		this.width = img.getWidth(null);
        this.height = img.getHeight(null);
	 }
	public void delete(){
		img =null;
	}

	@Override
	public void update() {
		y -= yspeed;
		x -= xspeed;
		
		if(y<0 || x<0 || x>screenWidth || y>screenHeight){
	         this.img= null;
	    }
		
	}
	public void setToNull(){
		img = null;
	}
	
	public int getPlayNum(){
		return playerNum;
	}
}
