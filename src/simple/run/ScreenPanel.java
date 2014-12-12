package simple.run;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;

import javax.swing.*;

import simple.gui.DrawModule;
import simple.gui.DrawObject;

// Main programs must inherit this class. This performs a lot of background action for the main program.
@SuppressWarnings("serial")
public abstract class ScreenPanel extends JPanel implements Runnable, KeyListener {	
	public static int FPS = 30;
	public static int DELAY_TIME = 1000/FPS;
	
	public static Color BACKGROUND_COLOR = new Color(180, 180, 180);
	
	public static final int MAXWIDTH = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int MAXHEIGHT = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static int WIDTH;
	public static int HEIGHT;
		
	public static DrawObject draw;

	private Thread thread;
	protected boolean running;
	
	private BufferedImage image;
	private JFrame frame;
			
	public void quit() { running = false; }
	public void minimize() { frame.setState(Frame.ICONIFIED); }
	
	public JFrame getFrame() { return frame; }
	public void setFrame(JFrame frame_) { frame = frame_; }
	public void setSize(int w, int h) {
		frame.setSize(w, h);
		WIDTH = w;
		HEIGHT = h;
		DrawModule.setGraphics();
	}
	public void setLocation(int x, int y) {
		frame.setLocation(x, y);
	}
	
	public static void start(ScreenPanel mainProgram, String name, boolean isUndecorated) {
		mainProgram.setFrame(new GUIRunWindow(mainProgram, name, isUndecorated)); 
	}
	public static void start(ScreenPanel mainProgram, String name) {
		mainProgram.setFrame(new GUIRunWindow(mainProgram, name, false)); 
	}
	
	public ScreenPanel(int width_, int height_, int FPS_) {
		super();
		Keyboard.addTypableObject(this);
		WIDTH = width_;
		HEIGHT = height_;
		FPS = FPS_;
		DELAY_TIME = 1000/FPS;
		frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			Mouse m = new Mouse();
			Keyboard k = new Keyboard();
			addMouseListener(m);
			addMouseMotionListener(m);
			addMouseWheelListener(m);
			addKeyListener(k);
			thread.start();
		}
	}
	
	private void initializeGraphicWindow() {
		DrawModule.initialize();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		draw = new DrawObject();
		running = true;
	}
	
	public void run() {
		initializeGraphicWindow();
		setup();
		while(running) {
			loop();
		}
		System.exit(0);
	}
	
	// Method to initialize main program
	public abstract void setup();
	// Program loop is setup here
	public abstract void loop();
	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	// Physically updates the screen and draws whatever is on the buffer
	protected void DrawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(DrawModule.getImage(), 0, 0, null);
		g2.dispose();
	}
	// Made to wipe the screen after drawing is complete
	protected void cls() {
		draw.setDrawColors(BACKGROUND_COLOR, null, null);
		draw.rect(0, 0, WIDTH, HEIGHT);
	}
	
	protected void updateView() {
		DrawToScreen();
		Timer.correctedDelay(DELAY_TIME);
		cls();
	}
}
