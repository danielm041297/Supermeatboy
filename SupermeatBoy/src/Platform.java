import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Platform {
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private Color color;
	private Rectangle platformHitbox;
	public Platform(int x, int y, int w, int h){
		color = Color.BLACK;
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
		platformHitbox = new Rectangle(xCoord,yCoord,width,height);
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(xCoord,yCoord,width,height);
	}
	public Rectangle getHitbox(){
		return platformHitbox;
	}
	//coordinates backwards
	public int getTop(){
		return yCoord;
	}
	public int getLeft(){
		return xCoord;
	}
	public int getRight(){
		return xCoord+width;
	}
	public int getBottom(){
		return yCoord+height;
	}
	public void setColor(Color c){ //for demo purposes
		color = c;
	}
	public Color getColor(){
		return color;
	}
}