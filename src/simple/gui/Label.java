package simple.gui;

import java.awt.Font;
import java.awt.FontMetrics;

import simple.run.ScreenPanel;

// creates a floating text box. Only works if clicked(active)
public class Label extends Widget {
	public static int KEY_NOT_PRESSED = -1;
	
	protected String text;
	protected int numLinesToDisplay;
	protected boolean boxIsDrawable;
	
	public int getNumLines() { return numLinesToDisplay; }	
	public String getText() { return text; }
		
	public void setText(String newText) { text = newText; }
	public void setBoxIsDrawable(boolean drawable) { boxIsDrawable = drawable; }
	
	@Override
	public void setSize(int w_, int h_) {
		super.setSize(w_, h_);
		FontMetrics fm = ScreenPanel.getGraphicsObject().getFontMetrics(textFont);
		numLinesToDisplay = (h-4)/(fm.getMaxAscent()+2);
	}
	@Override
	public void setWidth(int w_) {
		setSize(w_, h);
	}
	@Override
	public void setHeight(int h_) {
		setSize(w, h_);
	}
	
	@Override
	public void setTextFont(Font f_) {
		super.setTextFont(f_);
		FontMetrics fm = draw.getFontMetrics(textFont);
		numLinesToDisplay = (h-4)/(fm.getMaxAscent()+2);
	}
	
	public Label() {
		this(0, 0, 10, 10, "");
	}
	public Label(String initText) {
		this(0, 0, 10, 10, initText);
	}
	public Label(int x_, int y_, int w_, int h_) {
		this(x_, y_, w_, h_, "");
	}
	public Label(int x_, int y_, int w_, int h_, String initText) {
		super(x_, y_, w_, h_);
				
		boxIsDrawable = false;
		text = initText;
		FontMetrics fm = draw.getFontMetrics(textFont);
		// We want space of at least 2 pixels above the text and below the text to the border, and 2 pixels between lines
		numLinesToDisplay = (h-4)/(fm.getMaxAscent()+2);
	}
	
	public void addChar(char c) {
		text += c;
	}

	@Override
	public void Update() {
		if (!enabled || !visible) 
			return;
		
		updateClickingState();
	}
	
	@Override
	public void Draw() {
		if (!visible)
			return;
		
		if (boxIsDrawable) {
			draw.setDrawColors(textAreaColor, borderColor, null);
			draw.rect(x, y, w, h);
		}
		
		FontMetrics fm = draw.getFontMetrics(textFont);
		int lineHeight = fm.getMaxAscent()+2;
		
		draw.setFont(textFont);
		draw.setFontColor(textColor);
		int currentLine = 1;
		String currentText = text;
		
		first: while(true) {
			// Cuts off any characters that would take it out of the box's bounds
			if (currentLine > numLinesToDisplay) {
				text = text.substring(0, text.length()-currentText.length());
				break first;
			// checks if the partitioned string will fit in the line
			} else if (fm.stringWidth(currentText) <= w-4) {
				draw.text(currentText, x+2, y+2 + lineHeight*currentLine);
				break first;
			// Searches for a point in the partitioned string that will fit in the box's bounds. Takes formation of words into account
			} else {
				for (int i=currentText.length()-1; i>=0; i--) {
					if (fm.stringWidth(currentText.substring(0, i+1)) <= w-4) {
						int lastIndex = i;
						if (currentLine < numLinesToDisplay) {
							for (int j=lastIndex; j>=0; j--) {
								if (currentText.charAt(j) == ' ') {
									lastIndex = j;
									break;
								}
							}
						}
						draw.text(currentText.substring(0, lastIndex+1), x+2, y+2 + lineHeight*currentLine);
						currentText = currentText.substring(lastIndex+1);
						currentLine += 1;
						break;
					}
				}
			}
		}
	}	
}
