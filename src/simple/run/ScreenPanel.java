package simple.run;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;

import javax.swing.*;

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
	
	protected static Graphics2D g;
	public static Graphics2D getGraphicsObject() { return g; }
	
	public static DrawObject draw;
	
	public static int currentKeyCode;
	public static boolean hasch() {
		return currentKeyCode != 0;
	}
	public static char getch() {
		return (char)currentKeyCode;
	}

	private Thread thread;
	protected boolean running;
	
	private BufferedImage image;
	private JFrame frame;
			
	public void quitProgram() { running = false; }
	
	public JFrame getFrame() { return frame; }
	public void setFrame(JFrame frame_) { frame = frame_; }
	
	public ScreenPanel(int width_, int height_, int FPS_, JFrame parentFrame) {
		this(width_, height_, FPS_);
		frame = parentFrame;
	}
	public ScreenPanel(int width_, int height_, int FPS_) {
		super();
		currentKeyCode = 0;
		Keyboard.addTypableObject(this);
		WIDTH = width_;
		HEIGHT = height_;
		FPS = FPS_;
		DELAY_TIME = 1000/FPS;
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
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) image.getGraphics();
		g.setBackground(Color.WHITE);
		draw = new DrawObject(g);
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
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	// Made to wipe the screen after drawing is complete
	protected void cls() {
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}
}
