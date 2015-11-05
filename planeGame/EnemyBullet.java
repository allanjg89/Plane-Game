package planeGame;

import java.awt.Image;

public class EnemyBullet extends Enemy {
	
	
	public EnemyBullet(Image img, int x, int y, double xspeed,double yspeed, int damage, int bullType) {
		 this.notBullet = false;
		 this.explosionType = "null";
		 this.enemyType = bullType;
		 this.explosionType = "null";
		 this.img = img;
		 this.x = x;
		 this.y = y;
		 this.xspeed = xspeed;
		 this.yspeed = yspeed;
		 this.health = damage;
		 this.width = img.getWidth(null);
         this.height = img.getHeight(null);
	 }

	@Override
	public void update() {
		y += yspeed;	
        x += xspeed;
        
        checkCollision();
        
        if(y<0 || x<0 || x>screenWidth || y>screenHeight){
         this.img= null;
        }
		
	}

}
