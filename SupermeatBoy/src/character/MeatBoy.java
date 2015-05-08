package character;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import platform.Platform;
import level.MeatBoyLevel;
import input.MeatBoyInput;


public class MeatBoy {
	
	//Communicating with:
	private MeatBoyLevel level;
	private MeatBoyInput input;
	private ArrayList<Platform> platforms;
	//MeatBoy properties:
	private final int MEATBOY_WIDTH =25;
	private final int MEATBOY_HEIGHT =25;
	private Rectangle hitbox;
	private boolean alive;
	private Image meatboy;
	private boolean inAir;
	private int xPos;
	private int yPos;
	private double xVel;
	private double yVel;
	private int xscroll; 
	private int yscroll; 
	private int standingLeft;
	private int standingRight;
	//touching wall restrictions
	private boolean cannotLeft;
	private boolean cannotRight;
	private boolean holdingLeft;
	private boolean holdingRight;
	
	
	private Graphics offgc;
    private BufferedImage offscreen;
    private double gravity;
    
	public MeatBoy(Component c,MeatBoyLevel lev) {
		cannotLeft = false;
		cannotRight = false;
		holdingLeft = false;
		holdingRight = false;
		yscroll=0;
		xscroll=0;
		gravity =1.1;
		offscreen = new BufferedImage(MEATBOY_HEIGHT,MEATBOY_WIDTH,BufferedImage.TYPE_INT_RGB);
		offgc = offscreen.getGraphics();
		level=lev;
		platforms = level.getPlatforms();
		offscreen=null;
		xPos=100;
		yPos=100;
		alive=true;
		inAir=true;
		hitbox = new Rectangle(xPos,yPos,MEATBOY_WIDTH,MEATBOY_HEIGHT);
		meatboy =Toolkit.getDefaultToolkit().createImage("resources/meatboy.jpg");
		input= new MeatBoyInput(c);
	}
	public void move(){
		if(input.isKeyPressed(KeyEvent.VK_R)){
			xPos = 100;
			yPos = 100;
			inAir = true;
		}
		if(xPos+xVel<0){
			xPos=0;
			xVel=0;
		}
		if(xPos+xVel>level.getWidth()-MEATBOY_WIDTH){
			xPos=level.getWidth()-MEATBOY_WIDTH;
			xVel=0;
		}
		if(yPos+yVel<0){
			yPos=0; 
			yVel=0;
		}
		if(yPos+yVel>level.getHeight()){
			yPos=level.getHeight();
			yVel=0;
		}
		if(!inAir){
			
			if(input.isKeyPressed(KeyEvent.VK_UP)){
				yVel=-14;
				inAir=true;
			}
			if(input.isKeyPressed(KeyEvent.VK_UP)&&input.isKeyPressed(KeyEvent.VK_F))
					yVel=-20;
			if(input.isKeyPressed(KeyEvent.VK_RIGHT)){
				xVel=10;
				holdingRight = true;
				if (cannotRight)
					xVel=0;
				if(input.isKeyPressed(KeyEvent.VK_F)){
					xVel*=1.75;
				}
			}
			else if(input.isKeyPressed(KeyEvent.VK_LEFT)){
				xVel=-10;
				holdingLeft = true;
				if(cannotLeft)
				{
					xVel = 0;
				}
				if(input.isKeyPressed(KeyEvent.VK_F)){
					xVel*=1.75;
				}
			}
			else
				xVel=0;	
		}
		else{
			
			if(input.isKeyPressed(KeyEvent.VK_RIGHT)){
				xVel=10;
				holdingRight = true;
				if (cannotRight)
					xVel=0;
				if(input.isKeyPressed(KeyEvent.VK_F)){
					xVel*=1.75;
				}
			}
			else if(input.isKeyPressed(KeyEvent.VK_LEFT)){
				xVel=-10;
				holdingLeft = true;
				if(cannotLeft)
				{
					xVel = 0;
				}
				
				if(input.isKeyPressed(KeyEvent.VK_F)){
					xVel*=1.75;
				}
			}
			else
				xVel=0;
			
			yVel+=gravity;
		}
		xPos+=xVel;
		yPos+=yVel;
		cannotRight = false;
		cannotLeft = false;
		hitbox = new Rectangle(xPos,yPos,MEATBOY_HEIGHT, MEATBOY_WIDTH);
		checkCollisions();
		holdingLeft = false;
		holdingRight = false;
		xscroll=xPos;
		yscroll=yPos;
		hitbox = new Rectangle(xPos,yPos,MEATBOY_HEIGHT, MEATBOY_WIDTH);
		
	}

	public void draw(Graphics g) {
		if(offscreen==null){
			offscreen = new BufferedImage(MEATBOY_HEIGHT,MEATBOY_WIDTH,BufferedImage.TYPE_INT_RGB);
			offgc = offscreen.getGraphics();
		}
		offgc.drawImage(meatboy,0,0,MEATBOY_HEIGHT,MEATBOY_WIDTH, null);
		g.drawImage(offscreen,xPos-xscroll,yPos-yscroll,MEATBOY_HEIGHT,MEATBOY_WIDTH, null);
	}
	public void checkCollisions(){
		platforms=level.getPlatforms();
		for(int i=0;i<platforms.size();i++){
			Platform temp = platforms.get(i);
			if(hitbox.intersects(temp.getHitbox())){
				//left wall
				if (Math.abs(xPos+MEATBOY_WIDTH-temp.getLeft())<=xVel+18 && yPos>temp.getTop()-MEATBOY_HEIGHT && yPos<temp.getBottom() && holdingRight)
				{
					xPos = temp.getLeft()-MEATBOY_WIDTH;
					cannotRight = true;
				//	System.out.println("left");
					
				}
				//right wall
				else if (Math.abs(xPos-temp.getRight())<=Math.abs(xVel-18) && yPos>temp.getTop()-MEATBOY_HEIGHT && yPos<temp.getBottom())
				{
					xPos = temp.getRight();
					cannotLeft = true;
				//	System.out.println("right");
					
				}
				//top wall
				else if (yVel>=0)//(xPos>temp.getLeft()-MEATBOY_WIDTH && xPos<temp.getRight() && yPos+MEATBOY_HEIGHT-temp.getTop()<=yVel)
				{	
					yVel = 0;
					standingLeft = temp.getLeft();
					standingRight = temp.getRight();
					yPos = temp.getTop()-MEATBOY_HEIGHT;
					inAir=false;	
					System.out.println(inAir + "");
				}
				//bottom wall
				else //(xPos>=temp.getLeft()-MEATBOY_WIDTH && xPos<=temp.getRight() && (yPos+MEATBOY_HEIGHT>temp.getBottom()-1 && yPos<temp.getBottom()+1))
				{
					yPos = temp.getBottom();
					yVel = 0;
				}		
			}	
			else{
				if(!inAir && (xPos+MEATBOY_WIDTH<standingLeft || xPos>standingRight))
				{
					inAir = true;
				}
			}
		}
	}
	public boolean isAlive(){
		return alive;
	}
	public int getX(){
		return xPos;
	}
	public int getY(){
		return yPos;
	}
	public boolean isInAir(){
		return inAir;
	}
	public int getXScroll(){
		return xscroll;
	}
	public int getYScroll(){
		return yscroll;
	}
	public void setXScroll(int x ){
		xscroll=x;
	}
	public void setYScroll(int y){
		yscroll=y;
	}
}
