package simple.gui;

import java.awt.*;

public class Button extends Widget{
	
	protected float clrRatio;
	protected String text;
	protected ImageBox imageBox;

	public String getText() { return text; }
	public Image getImage() { return imageBox.getImage(); }
	
	public void setText(String text_) { text = text_; } 
	public void setImage(Image image_) { imageBox.setImage(image_); }
	
	// This constructor is made to be used for panels with specific layouts, as initial position is irrelevant
	public Button(String text_, Image image_) {
		this(0, 0, 10, 10, text_, image_);
	}
	public Button(int x_, int y_, int w_, int h_, String text_, Image image_) {
		super(x_, y_, w_, h_);
		text = text_;
		imageBox = new ImageBox(x, y, w, h, image_);
		
		clicking = false;
		enabled = true;

		clrRatio = 0.84f;
	}

	@Override
	public void Update() {
		if (!enabled || !visible) 
			return;
		updateClickingState();
	}
	
	@Override
	public void Draw () {
		if (!visible) 
			return;
		
		if (!enabled) {
			clrRatio = 0.75f;
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

		FontMetrics fm = draw.getFontMetrics(textFont);
		draw.getGraphics().setFont(textFont);
		draw.text(text, x + w/2 - fm.stringWidth(text)/2, y + h/2 + fm.getMaxAscent()/2);
	}
}
