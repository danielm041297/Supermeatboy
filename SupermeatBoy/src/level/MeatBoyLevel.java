package level;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import character.MeatBoy;
import platform.Platform;
import javax.swing.*;


public class MeatBoyLevel extends JPanel implements ActionListener{
	private Timer time;
	private MeatBoy player;
	private int width= 2000;
	private int height= 2000;
	private int frame_width;
	private int frame_height;
	private ArrayList<Platform> platformList;
	private int xscroll;
	private int yscroll;
	public MeatBoyLevel(Component c)   {
		platformList = new ArrayList<Platform>();
		xscroll=0;
		yscroll=0;
		frame_height=c.getHeight();
		frame_width=c.getWidth();
		width=2000;
		height=2000;
		this.setOpaque(true);
		this.setBackground(Color.white);
		for(int i=0;i<4;i++){
			Platform test = new Platform(130,350-i*50,50,50);
			test.setColor(Color.GREEN);
			platformList.add(test);
		}
		int gap=50;
		for (int i2 = 0; i2<10; i2++){
			Platform plat= new Platform(350+i2*30+gap*i2,i2*25+280,50,50);
			plat.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			platformList.add(plat);
		}
		
		for(int i=0;i<4;i++){
			Platform test = new Platform(1100+200*i,330-i*100,600,20+100*i);
			test.setColor(Color.GREEN);
			platformList.add(test);
		}
		for(int i=0;i<2;i++){
			Platform test = new Platform(1150+200*i,500,20,470);
			test.setColor(Color.BLUE);
			platformList.add(test);
		}
		
		Platform test = new Platform(130,450,50,50);
		test.setColor(Color.red);
		platformList.add(test);
		
		Platform ground = new Platform(50,500,1100,50);
		ground.setColor(Color.red);
		Platform ground2 = new Platform(500,1000,1100,250);
		ground2.setColor(Color.RED);
		
		platformList.add(ground);
		platformList.add(ground2);
		player = new MeatBoy(c,this);	
		
		time=new Timer(40,this);
		time.start();
		
	}
	public void update(){
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
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		player.draw(g);	
		for(Platform p:platformList){
			p.draw(g);
		}
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
