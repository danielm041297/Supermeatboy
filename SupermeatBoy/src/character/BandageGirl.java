package character;

import java.awt.Rectangle;

public class BandageGirl {
	private int x;
	private int y;
	private int size;
	private Rectangle hitbox;
	public BandageGirl(int x,int y, int size){
		this.x=x;
		this.y=y;
		this.size=size;
		hitbox=new Rectangle(x,y,size,size);
	}
	public Rectangle getHitbox(){
		return hitbox;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getSize(){
		return size;
	}
}
