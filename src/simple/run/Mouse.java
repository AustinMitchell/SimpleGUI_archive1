package simple.run;

import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
	private static int x, y, prevx, prevy = 0;
	private static boolean pressed = false;
	private static int framesPassed = 0;
	
	public static int getX() { return x; }
	public static int getY() { return y; }
	public static int getPrevX() { return prevx; }
	public static int getPrevY() { return prevy; }
	public static boolean isPressed() { return pressed; }

	public static void updatePosition(int newX, int newY) {
		if (framesPassed < Timer.getFramesPassed()) {
			prevx = x;
			prevy = y;
			framesPassed = Timer.getFramesPassed();
		}
		x = newX;
		y = newY;
	}
	
	public void mouseMoved(MouseEvent e) { updatePosition(e.getX(), e.getY()); }
	public void mousePressed(MouseEvent e) { pressed = true; updatePosition(e.getX(), e.getY());}
	public void mouseReleased(MouseEvent e) { pressed = false; updatePosition(e.getX(), e.getY()); }
	public void mouseDragged(MouseEvent e) { updatePosition(e.getX(), e.getY()); }
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseWheelMoved(MouseWheelEvent arg0) {}


}