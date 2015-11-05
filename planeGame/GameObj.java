package planeGame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

public abstract class GameObj {
	public static Vector<GameObj> players = new Vector<GameObj>();//an array of the players of in the game
	public static Vector<GameObj> enemies = new Vector<GameObj>();// and array of the enemies in the game
	public static Vector<GameObj> plyBullets = new Vector<GameObj>();
	public static Vector<GameObj> enmBullets = new Vector<GameObj>();
	public static Vector<GameObj> animations = new  Vector<GameObj>();
	public static Vector<GameObj> scenery = new Vector<GameObj>();
	
	//static Vector<GameObj> scoreImages = new Vector<GameObj>();
	public static Graphics2D g2;
	public static GameEvents gameEvents;
	public static int screenWidth,screenHeight;
	protected static int maxHealth = 100;
	static int playerDamg1 = 1;
	static int playerDamg2 = 3;
	
	protected VariableImg healthBar, lives;
	
	
    protected Rectangle bbox;
    public int health;
    
	
    protected Player player;
    protected Enemy enemy;
    protected EnemyBullet enmBull;
    protected PlayerBullet plyBull;
    protected String explosionType;
    
    
	public Image img;
    protected int x;
	protected int y;
	protected int speed;
	protected int width;
	protected int height;
    
    public abstract void update();
  
    
    public boolean collision(int x, int y, int w, int h) {
        bbox = new Rectangle(this.x, this.y, this.width, this.height);
        Rectangle otherBBox = new Rectangle (x,y,w,h);
        if(this.bbox.intersects(otherBBox)) { 
            return true;
        }
        return false;
    }
    
    public static void updateGameObj(int w, int h){
    	screenWidth = w;
    	screenHeight = h;
    }
    
    public void draw(ImageObserver obs,Graphics2D g2) {
        g2.drawImage(img, x, y, obs);
    }
    
    public boolean isNull(){
		if(img == null){
			return true;
		}else{
			return false;
		}
	}
    
    public static void clear(){
    	players.clear();
    	enemies.clear();
    	plyBullets.clear();
    	enmBullets.clear();
    	animations.clear(); 
    	scenery.clear();
    
    }
}
