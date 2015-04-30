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

public class Platform implements Runnable {
	private MeatBoy meatBoy;
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private Color color;
	
	public Platform(MeatBoy m, Component c, int w, int h, int x, int y){
		color = Color.GREEN;
		meatBoy = m;
		meatBoy.setMeatBoyStatus("none");
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
		Rectangle platformHitbox = new Rectangle(xCoord,yCoord,width,height);
	}
	public void draw(Graphics g) throws IOException{
		g.setColor(color);
		g.fillRect(xCoord,yCoord,width,height);
	}
		
	public void run() {
		//Checks if meatboy is within its parameters and stops velocity if so
		color = Color.RED;
//		if ((meatBoy.getXPos()>=xCoord || meatBoy.getXPos()<=xCoord+width) && meatBoy.getYPos()>yCoord && meatBoy.getYPos()<yCoord+width)
//		{
//			meatBoy.setXVel(0);
//			if (meatBoy.getXPos()==xCoord)
//				MeatBoyStatus = "LeftWall";
//			else
//				MeatBoyStatus = "RightWall";
//		}
//		else if (meatBoy.getXPos()>=xCoord && meatBoy.getXPos()<=xCoord+width && (meatBoy.getYPos()>=yCoord || meatBoy.getYPos()<=yCoord+height))
//		{
//			if (meatBoy.getYPos()==yCoord)
//				MeatBoyStatus = "Grounded";
//			meatBoy.setYVel(0);
//		}
		//left wall case
		if ((meatBoy.getXPos()+20>=xCoord-21 && meatBoy.getXPos()+20<=xCoord+21) && meatBoy.getYPos()>yCoord && meatBoy.getYPos()<yCoord+height)
		{
			meatBoy.setMeatBoyStatus("LeftWall");
			
		}
		//right wall case;
		else if ((meatBoy.getXPos()<=xCoord+width+21 && meatBoy.getXPos()>=xCoord+width-21) && meatBoy.getYPos()>yCoord && meatBoy.getYPos()<yCoord+height)
		{
			meatBoy.setMeatBoyStatus("RightWall");
		}
		//top case
		else if (meatBoy.getXPos()>=xCoord && meatBoy.getXPos()<=xCoord+width && (meatBoy.getYPos()+20>=yCoord-21 && meatBoy.getYPos()+20<=yCoord+21))
		{
			meatBoy.setMeatBoyStatus("Top");
		}
		//bottom case
		else if (meatBoy.getXPos()>=xCoord && meatBoy.getXPos()<=xCoord+width && (meatBoy.getYPos()<=yCoord+height+21 && meatBoy.getYPos()>=yCoord+height-21))
		{
			meatBoy.setMeatBoyStatus("Bottom");
		}
		
		else
		{
			meatBoy.setMeatBoyStatus("none");
			color = Color.green;
		}
		}


}