package simple.run;

import java.util.ArrayList;
import java.awt.event.*;

public class Keyboard implements KeyListener {
	private static ArrayList<KeyListener> listeningList = new ArrayList<KeyListener>();
	private static int lastKeyTypedCode = 0;
	private static int lastKeyPressedCode = 0;
	private static int lastKeyReleasedCode = 0;
	
	public static int getLastKeyTypedCode() { return lastKeyTypedCode; }
	public static int getLastKeyPressedCode() { return lastKeyPressedCode; }
	public static int getLastKeyReleasedCode() { return lastKeyReleasedCode; }
	
	public static void addTypableObject(KeyListener newKeyListener) {
		listeningList.add(newKeyListener);
	}

	public void keyPressed(KeyEvent e) {
		lastKeyPressedCode = e.getKeyCode();
		for (KeyListener t: listeningList)
			t.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		lastKeyReleasedCode = e.getKeyCode();
		for (KeyListener t: listeningList)
			t.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
		lastKeyTypedCode = e.getKeyCode();
		for (KeyListener t: listeningList) {
			t.keyTyped(e);
		}
	}
	
	

}
