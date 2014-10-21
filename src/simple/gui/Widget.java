package simple.gui;

import java.awt.*;

import simple.run.Mouse;
import simple.run.ScreenPanel;

/**
 * Generic Widget class. All widgets inherit from this class.
 * Note that if you decide to make your own Widget object, they have access to a DrawObject named draw. Drawing should be done with this object.
 * @author Austin
 *
 */
public abstract class Widget {
	protected static DrawObject draw = new DrawObject(ScreenPanel.getGraphicsObject());
	
	protected static int WINDOW_WIDTH = ScreenPanel.WIDTH;
	protected static int WINDOW_HEIGHT = ScreenPanel.HEIGHT;
	
	private static Color DEFAULT_FILLCOLOR = Color.WHITE;
	private static Color DEFAULT_BORDERCOLOR = Color.BLACK;
	private static Color DEFAULT_TEXTAREACOLOR = Color.WHITE;
	private static Color DEFAULT_TEXTCOLOR = Color.BLACK;
	private static Font DEFAULT_TEXTFONT = new Font("Consolas", Font.PLAIN, 12);
	
	public static Color getDefaultFillColor() { return DEFAULT_FILLCOLOR; }
	public static Color getDefaultCorderColor() { return DEFAULT_BORDERCOLOR; }
	public static Color getDefaultTextAreaColor() { return DEFAULT_TEXTAREACOLOR; }
	public static Color getDefaultTextColor() { return DEFAULT_TEXTCOLOR; }
	public static Font getDefaultTextFont() { return DEFAULT_TEXTFONT; }
	
	public static void setDefaultFillColor(Color c_) { DEFAULT_FILLCOLOR = c_; }
	public static void setDefaultCorderColor(Color c_) { DEFAULT_BORDERCOLOR = c_; }
	public static void setDefaultTextAreaColor(Color c_) { DEFAULT_TEXTAREACOLOR = c_; }
	public static void setDefaultTextColor(Color c_) { DEFAULT_TEXTCOLOR = c_; }
	public static void setDefaultWidgetColors(Color newFillColor, Color newBorderColor, Color newTextAreaColor, Color newTextColor) { 
		DEFAULT_FILLCOLOR = newFillColor; 
		DEFAULT_BORDERCOLOR = newBorderColor; 
		DEFAULT_TEXTAREACOLOR = newTextAreaColor;
		DEFAULT_TEXTCOLOR = newTextColor; 
	}
	public static void setDefaultTextFont(Font f_) { DEFAULT_TEXTFONT = f_; }
	
	public static boolean intersectsWith(Widget a, Widget b) {
		return a.intersectsWith(b);
	}
	
	protected int x, y, w, h;
	protected boolean hovering, clicking, clicked, hasEntered, isBlocked;
	protected boolean enabled, visible;
	
	protected Color fillColor;
	protected Color borderColor;
	protected Color textAreaColor;
	protected Color textColor;
	protected Font textFont;
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return w; }
	public int getHeight() { return h; }
	public boolean isEnabled() { return enabled; }
	public boolean isVisible() { return visible; }
	
	public Color getFillColor() { return fillColor; }
	public Color getBorderColor() { return borderColor; }
	public Color getTextAreaColor() { return textAreaColor; }
	public Color getTextColor() { return textColor; }
	public Font getTextFont() { return textFont; }

	public void setX(int x_) { x=x_; }
	public void setY(int y_) { y=y_; }
	public void addX(int x_) { setX(x+x_); }
	public void addY(int y_) { setY(y+y_); }
	public void setLocation(int x_, int y_) { x=x_; y=y_; }
	public void setWidth(int w_) { w=w_; }
	public void setHeight(int h_) { h=h_; }
	public void setSize(int w_, int h_) { w=w_; h=h_; }
	public void setEnabled(boolean enabled_) { 
		enabled = enabled_; 
		hovering = false;
		clicking = false;
		clicked = false;
	}
	public void setVisible(boolean visible_) { 
		visible = visible_;
		hovering = false;
		clicking = false;
		clicked = false;
	}
	
	public void setFillColor(Color c_) { fillColor = c_;}
	public void setBorderColor(Color c_) { borderColor = c_; }
	public void setTextAreaColor(Color c_) { textAreaColor = c_; }
	public void setTextColor(Color c_) { textColor = c_; }
	public void setWidgetColors(Color newFillColor, Color newBorderColor, Color newTextAreaColor, Color newTextColor) {
		setFillColor(newFillColor);
		setBorderColor(newBorderColor);
		setTextAreaColor(newTextAreaColor);
		setTextColor(newTextColor);
	}
	public void setTextFont(Font f_) { textFont = f_; }

	public Widget() { 
		this(0, 0, 10, 10);
	}
	public Widget(int x_, int y_, int w_, int h_) { 
		if (w < 0 || h < 0) { throw new IndexOutOfBoundsException("Size parameters must be non-negative"); }
		x=x_; 
		y=y_; 
		w=w_; 
		h=h_; 
		
		fillColor = DEFAULT_FILLCOLOR;
		borderColor = DEFAULT_BORDERCOLOR;
		textAreaColor = DEFAULT_TEXTAREACOLOR;
		textColor = DEFAULT_TEXTCOLOR;
		textFont = DEFAULT_TEXTFONT;
		
		enabled = true;
		visible = true;
		hovering = false;
		clicking = false;
		clicked = false;
		hasEntered = false;
		isBlocked = false;
	}
	
	public boolean intersectsWith(Widget other) {
		return intersectsWith(other.getX(), other.getY(), other.getWidth(), other.getHeight());
	}
	public boolean intersectsWith(int x2, int y2, int w2, int h2) {
		return false;
	}
	
	public boolean containsMouse() {
		if (Mouse.getX() < x+w && Mouse.getX() > x && Mouse.getY() < y+h && Mouse.getY() > y) {
			return true && visible;
		} else {
			return false;
		}
	}
	public boolean isHovering() {
		return hovering;
	}
	public boolean isClicking() {
		return clicking;
	}
	public boolean isClicked() {
		return clicked;
	}
	
	public void blockWidget() {
		isBlocked = true;
	}
	
	// Must be called in a widgets Update() function for mouse activity to be registered properly
	protected void updateClickingState() {
		if (clicked) 
			clicked = false;
		if (isBlocked) {
			isBlocked = false;
			hovering = false;
			clicking = false;
			clicked = false;
		} else {
			if (containsMouse() && Mouse.isPressed() && hovering) {
				clicking = true && enabled && visible;
			} else if (containsMouse() && clicking && !Mouse.isPressed()) {
				clicked = true && enabled && visible;
				clicking = false;
			} else {
				clicking = false;
				clicked = false;
				if (containsMouse() && !Mouse.isPressed()) {
					hovering = true && visible;
				} else {
					hovering = false;
				}
			}
		}
	}
	
	public abstract void Update();
	public abstract void Draw();  
	
	public String toString() {
		return this.getClass().getName() + " at position (" + x + ", " + y + ") of size " + w + ", " + h + ").";
	}
}
