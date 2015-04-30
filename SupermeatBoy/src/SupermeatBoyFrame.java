import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;


public class SupermeatBoyFrame extends JFrame implements ActionListener{
	private Timer time;
	private MeatBoy player;
	private Platform platform1;
	public static void main(String[] args) throws IOException{
		SupermeatBoyFrame mb = new SupermeatBoyFrame();
	}
	public SupermeatBoyFrame() throws IOException {
		setSize(600,600);
		player = new MeatBoy(this);
		
		platform1 = new Platform(player, this, 100, 100, 200, 200);
		Thread platform1Detection = new Thread(platform1);
		platform1Detection.start();
		
		setTitle("Super Meat Boy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		time=new Timer(5,this);
		time.start();
		setVisible(true);
	}
	public void update(){
		player.move();
		platform1.run();
	}
	public void paint(Graphics g){
		super.paint(g);
		try {
			player.draw(g);
			platform1.draw(g);
		} catch (IOException e) {e.printStackTrace();}
	}
	public void actionPerformed(ActionEvent e){
		update();
		repaint();
	}
	
}
