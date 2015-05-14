package level;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import platform.Platform;
import tile.TileMap;

import javax.swing.*;
import character.MeatBoy;
import character.BandageGirl;


public class MeatBoyLevel extends JPanel implements ActionListener{
	private Timer time;
	private MeatBoy player;
	private int mbxstart;
	private int mbystart;
	private BandageGirl destination;
	private int width;
	private int height;
	private int frame_width;
	private int frame_height;
	private ArrayList<Platform> platformList;
	private int xscroll;
	private int yscroll;
	private BufferedImage entirebackground;
	private BufferedImage subbackground;
	private TileMap tmap;
	private boolean finished;

	public MeatBoyLevel(Component c)   {
		this.setSize(400,400);
		xscroll=0;
		yscroll=0;
		frame_height=c.getHeight()-40;
		frame_width=c.getWidth();
		String src = "resources/forest2.tmx";
		tmap = new TileMap(new File(src));
		entirebackground = tmap.drawMap();
		destination = tmap.getBandageGirl();
		mbxstart = tmap.getXStart();
		mbystart = tmap.getYStart();
		subbackground=null;
		width=tmap.getNumCols()*tmap.TILE_SIZE;
		height=tmap.getNumRows()*tmap.TILE_SIZE;
		platformList=tmap.getPlatforms();
		player = new MeatBoy(c,this, mbxstart,mbystart);	
		destination = tmap.getBandageGirl();
		this.setOpaque(true);
		this.setBackground(Color.white);
		time=new Timer(40,this);
		time.start();
		
	}
	public void update(){
		if(!finished){
			if(player.getHitbox().intersects(destination.getHitbox())){
				finished=true;
			}
			player.move();
			xscroll=player.getXScroll()-frame_width/2;
			yscroll=player.getYScroll()-frame_height/2;
			if(xscroll<0)
				xscroll=0;
			if(yscroll<0)
				yscroll=0;
			if(xscroll>width-frame_width)
				xscroll=width-frame_width;
			if(yscroll>height-frame_height)
				yscroll=height-frame_height;
			player.setXScroll(xscroll);
			player.setYScroll(yscroll);
			for(int i=0;i<platformList.size();i++){
				platformList.get(i).setScroll(xscroll,yscroll);
			}
		}
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		subbackground = entirebackground.getSubimage(xscroll,yscroll, width<frame_width?width:frame_width, height<frame_height?height:frame_height)	;
		g.drawImage(subbackground, 0, 0,null);
		player.draw(g);	
	}
	public ArrayList<Platform> getPlatforms(){
		return platformList;
	}
	public void actionPerformed(ActionEvent e){
		update();
		repaint();
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public int getXScroll(){
		return xscroll;
	}
	public int getYScroll(){
		return yscroll;
	}
}