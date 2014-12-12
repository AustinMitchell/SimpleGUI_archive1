package simple.gui;

import java.awt.*;

/** Widget object. Creates a clickable button, intended to be tied to an event. Changes colour according to current widget status.
 * 
 * <P> TODO: create functionality for method attachment. **/
public class Button extends Widget {
	/** Colour fullness displayed. A value of 1 means the rgb values will be displayed at 100%. A value of 0.5 means rgb values 
	 * of 50%. **/
	protected float clrRatio;
	/** Text displayed at the center of the button. **/
	protected String text;
	/** Image displayed on the button's background. **/
	protected ImageBox imageBox;

	/** Returns the button's text variable. **/
	public String getText() { return text; }
	/** Returns the button's Image object of the imageBox variable. **/
	public Image getImage() { return imageBox.getImage(); }
	
	/** Sets the button's text variable. **/
	public void setText(String newText) { text = newText; } 
	/** Sets the button's Image object of the imageBox variable**/
	public void setImage(Image newImage) { imageBox.setImage(newImage); }
	
	/** Sets the button's x and y coordinates, as well as shifts the imageBox. **/
	public void setLocation(int newX, int newY) {
		super.setLocation(newX, newY);
		imageBox.setLocation(newX, newY);
	}
	/** Sets the button's x coordinate, as well as shifts the imageBox. **/
	public void setX(int newX) { setLocation(newX, y); }
	/** Sets the button's y coordinate, as well as shifts the imageBox. **/
	public void setY(int newY) { setLocation(x, newY); }
	
	/** Sets the button's width and height, as well as resizes the imageBox. **/
	public void setSize(int newWidth, int newHeight) {
		super.setSize(newWidth, newHeight);
		imageBox.setSize(newWidth, newHeight);
	}
	/** Sets the button's width, as well as resizes the imageBox. **/
	public void setWidth(int newWidth) { setSize(newWidth, h); }
	/** Sets the button's height, as well as resizes the imageBox. **/
	public void setHeight(int newHeight) { setSize(w, newHeight); }
	
	/** Creates a button with unspecified position and size, for the case where it's irrelevant. **/
	public Button(String text_, Image image_) {
		this(0, 0, 10, 10, text_, image_);
	}
	/** Create's a Button object. **/
	public Button(int x_, int y_, int w_, int h_, String text_, Image image_) {
		super(x_, y_, w_, h_);
		text = text_;
		imageBox = new ImageBox(x, y, w, h, image_);
		
		clicking = false;
		enabled = true;

		clrRatio = 0.84f;
	}
	
	/** Updates the widget's status. **/
	public void Update() {
		if (!enabled || !visible) 
			return;
		updateClickingState();
	}
	
	/** Draws the button to the ScreenPanel graphics buffer. **/
	public void Draw () {
		if (!visible) 
			return;
		
		if (!enabled) {
			clrRatio = 0.75f;
		} else if (blocked) {
			clrRatio = 0.88f;
		} else if (isClicking()) {
			clrRatio = 1;
		} else if (isHovering()) {
			clrRatio = 0.94f;
		} else { 
			clrRatio = 0.88f;
		}

		draw.setDrawColors(DrawModule.scaleColor(fillColor, clrRatio), DrawModule.scaleColor(borderColor, clrRatio), DrawModule.scaleColor(textColor, clrRatio));
		draw.rect(x, y, w, h);
		
		imageBox.Draw();

		if (customDrawObject != null) {
			customDrawObject.customDraw(this);
		}
		
		if (text != "") {
			FontMetrics fm = draw.getFontMetrics(textFont);
			draw.getGraphics().setFont(textFont);
			draw.text(text, x + w/2 - fm.stringWidth(text)/2, y + h/2 + fm.getMaxAscent()/2);
		}
	}
}
