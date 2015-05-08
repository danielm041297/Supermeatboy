package input;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;


public class MeatBoyInput implements KeyListener {
	private boolean[] keys = new boolean[256];	//contains the status of all keyboard keys
	
	public MeatBoyInput(Component c){
		c.addKeyListener(this);
		Arrays.fill(keys,false);
	}
	
	public boolean isKeyPressed( int key )
	{
		if( key >= 0 && key < keys.length )
			return keys[key];
		else
			return false;
	}
	//@Override
	public void keyTyped(KeyEvent e) {}
	//@Override
	public void keyPressed(KeyEvent e) {
		if( e.getKeyCode() >= 0 && e.getKeyCode() < keys.length )
			keys[e.getKeyCode()] = true;
	}
	//@Override
	public void keyReleased(KeyEvent e) {
		if( e.getKeyCode() >= 0 && e.getKeyCode() < keys.length )
			keys[e.getKeyCode()] = false;
	}
	public String toString(){
		String s="";
		for(int i=0;i<keys.length;i++){
			if(keys[i])
				s+="Key with value"+i+"is being pressed";
		}
		return s;
	}
}
