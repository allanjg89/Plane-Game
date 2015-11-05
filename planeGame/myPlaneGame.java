/* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planeGame;


//import wingman.game1942WithoutObserver;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Allan
 */
public class myPlaneGame extends JApplet implements Runnable {

    private Thread thread;
    Image sea;
    Image myPlane,enemyImg1, enemyImg2, enemyImg3, gameOver, youWin;
    private BufferedImage bimg;
    Graphics2D g2;
    int speed = 1, move = 0;
    Random generator = new Random(1234567);
    Scenery I1, I2, I3;
    Plane m, m2;
    int w = 640, h = 480; // fixed size window game 
    Enemy e1;
    GameEvents gameEvents;
    int threadSleep = 25;
    int numOfUpdates;
    int numOfSecs;
    int numOfSegments;
    int numOfUpdatesPerSec = 1000/threadSleep;
    int numOfSecsPerSegment = 10;
    Sequencer sequencer;
    boolean boss = true;
    public static Vector<GameObj> screenObj = GameObj.animations;
    
       
    public void init() {
        
    	GameObj.clear();
    	URL URLBackSound;
		try {
			
			URLBackSound = new File("Resources/background.mid").toURI().toURL();
			Sequence sequence = MidiSystem.getSequence(URLBackSound);
	        sequencer = MidiSystem.getSequencer();
	        sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
	        sequencer.open();
	        sequencer.setSequence(sequence);
	        
	        
	        

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
        setBackground(Color.white);
        this.setFocusable(true);
        Image island1, island2, island3, enemyImg;
        try {
        sea = ImageIO.read(new File("Resources/water.png"));
        island1 = ImageIO.read(new File("Resources/island1.png"));
        island2 = ImageIO.read(new File("Resources/island2.png"));
        island3 = ImageIO.read(new File("Resources/island3.png"));
        gameOver = ImageIO.read(new File("Resources/gameOver.png"));
        youWin = ImageIO.read(new File("Resources/youWin.png"));
        myPlane = ImageIO.read(new File("Resources/myplane_1.png"));
        enemyImg1 = ImageIO.read(new File("Resources/enemy1_1.png"));
        enemyImg2 = ImageIO.read(new File("Resources/enemy2_1.png"));
        enemyImg3 = ImageIO.read(new File("Resources/enemy3_1.png"));
        
        //this is the explosion type for bullets
        Vector<Image> nullImg = new Vector<Image>();
        nullImg.add(null);
        Animation.animations.put("null",nullImg);
        
        //adding explosion1
        Vector<Image> explosion1 = new Vector<Image>();
        String temp;
        for (int i = 1; i < 7; i++){
        	temp = "Resources/explosion1_"+i+".png";
        	explosion1.add(ImageIO.read(new File(temp)));
        }
        Animation.animations.put("explosion1", explosion1);
        
      //adding different bullets
        Vector<Image> bullets = new Vector<Image>();
        bullets.add(ImageIO.read(new File("Resources/bulletLeft.png")));
        bullets.add(ImageIO.read(new File("Resources/bullet.png")));
        bullets.add(ImageIO.read(new File("Resources/bulletRight.png")));
        Animation.animations.put("bullets", bullets);
        
        
      //adding explosion2
        Vector<Image> explosion2 = new Vector<Image>();
        for (int i = 1; i < 8; i++){
        	temp = "Resources/explosion2_"+i+".png";
        	explosion2.add(ImageIO.read(new File(temp)));
        }
        Animation.animations.put("explosion2", explosion2);
       
        //adding health bar. Note that this is not really an animation
        Vector<Image> healthBar = new Vector<Image>();
        for (int i = 0; i <= 3; i++){
        	temp = "Resources/health"+i+".png";
        	healthBar.add(ImageIO.read(new File(temp)));
        }
        Animation.animations.put("healthBar", healthBar);
        
      //adding health bar. Note that this is not really an animation
        Vector<Image> lives = new Vector<Image>();
        for (int i = 3; i >= 1; i--){
        	temp = "Resources/life"+i+".png";
        	lives.add(ImageIO.read(new File(temp)));
        }
        Animation.animations.put("lives", lives);
        
        //adding the explosion sounds
        Sounds tempS;
        for (int i = 1; i < 3; i++){
        	tempS = new Sounds("Resources/snd_explosion"+i+".wav");
        	Sounds.audioClips.put("explosion"+i,tempS);
        }
        
        
        gameEvents = new GameEvents();        
        KeyControl key = new KeyControl();
        addKeyListener(key);
        
        GameObj.g2 = g2;
        GameObj.gameEvents = gameEvents;
        GameObj.screenHeight = h;
        GameObj.screenWidth = w;

        I1 = new Scenery(island1, 100, 100, speed, generator);
        I2 = new Scenery(island2, 200, 400, speed, generator);
        I3 = new Scenery(island3, 300, 200, speed, generator);
        m = new Plane(myPlane, w-70, 300, 20,1);
        m2 = new Plane(myPlane, 70, 300, 20,2);
        
        GameObj.scenery.add(I1);GameObj.scenery.add(I2);GameObj.scenery.add(I3);
        GameObj.players.add(m2);GameObj.players.add(m);
        
        gameEvents.addObserver(m);gameEvents.addObserver(m2);
        
        }
        catch (Exception e) {
            System.out.print("No resources are found");
        }
    }
    
    public void drawBackGroundWithTileImage() {
        int TileWidth = sea.getWidth(this);
        int TileHeight = sea.getHeight(this);

        int NumberX = (int) (w / TileWidth);
        int NumberY = (int) (h / TileHeight);

        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                g2.drawImage(sea, j * TileWidth, 
                        i * TileHeight + (move % TileHeight), TileWidth, 
                        TileHeight, this);
            }
        }
        move += speed;
    }

    public void drawDemo() {
 
    	
    	
    		numOfUpdates++;
    		numOfSecs = numOfUpdates/numOfUpdatesPerSec;
    		numOfSegments = numOfSecs/numOfSecsPerSegment;
    		
            drawBackGroundWithTileImage();
            GameObj.updateGameObj(w,h);

            
            if(numOfSegments==0){
	            while(GameObj.enemies.size()<3){
	            	GameObj.enemies.add(new EnemyPlane1(enemyImg1, generator));
	            }
            }else if(numOfSegments==1){
            	while(GameObj.enemies.size()<3){
	            	GameObj.enemies.add(new EnemyPlane2(enemyImg2, generator));
	            }
            }else if(numOfSegments==2 && boss){
	            GameObj.enemies.add(new EnemyPlane3(enemyImg3, generator));
	            boss = false;
            	
            }
          
            gameObjArr(GameObj.scenery);
            gameObjArr(GameObj.players);
            gameObjArr(GameObj.enemies);
            gameObjArr(GameObj.animations);
            gameObjArr(GameObj.plyBullets);
            gameObjArr(GameObj.enmBullets);
            
            Scenery temp;
            if(GameObj.players.isEmpty()){
        		temp = new Scenery(gameOver, w/4, h/2, 0, generator);
        		temp.draw(this,g2);
        	}else if(GameObj.enemies.isEmpty()){
        		temp = new Scenery(youWin, w/4, h/4, 0, generator);
        		temp.draw(this,g2);
        	}
            
    }
    
    private void gameObjArr(Vector<GameObj> V){
    	GameObj temp;
        for (int i = 0; i < V.size(); i++){
        	temp = V.get(i);
        	if(temp.isNull())
        		V.remove(i);
        	else{
        		temp.update();
        		temp.draw(this,g2);
        	}
        }
    	
    }

    public void paint(Graphics g) {
        if(bimg == null) {
            Dimension windowSize = getSize();
            bimg = (BufferedImage) createImage(windowSize.width, 
                    windowSize.height);
            g2 = bimg.createGraphics();
        }
        drawDemo();
        g.drawImage(bimg, 0, 0, this);
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
        sequencer.start();
        sequencer.start();
    }

    public void run() {
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();  
          try {
                thread.sleep(threadSleep);
            } catch (InterruptedException e) {
                break;
            }
            
        }
    }
    
    public class KeyControl extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            gameEvents.setValue(e);
        }
    }
 

    public static void main(String argv[]) {
    	
        final myPlaneGame demo = new myPlaneGame();
        demo.init();
        JFrame f = new JFrame("Scrolling Shooter");
        f.addWindowListener(new WindowAdapter() {});
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(640, 480));
        f.setVisible(true);
        f.setResizable(false);
        demo.start();
        
        

        
        
    }

}
