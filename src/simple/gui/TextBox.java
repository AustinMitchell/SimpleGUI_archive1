package simple.gui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import simple.run.Keyboard;
import simple.run.Mouse;
import simple.run.ScreenPanel;

// creates a floating text box. Only works if clicked(active)
public class TextBox extends Widget implements KeyListener {
	public static final int KEY_NOT_PRESSED = -1;
	
	protected String text;
	protected int newKeyCode;
	protected int numLinesToDisplay;
	protected boolean active, editable;
	
	public boolean isEditable() { return editable; } 
	public boolean isActive() { return active; }
	public int getNumLines() { return numLinesToDisplay; }	
	public String getText() { return text; }
	
	public void setEditable(boolean newEditable) { editable = newEditable; }
	public void setText(String newText) { text = newText; }
	
	@Override
	public void setSize(int w_, int h_) {
		super.setSize(w_, h_);
		FontMetrics fm = draw.getFontMetrics(textFont);
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
	
	public TextBox() {
		this(0, 0, 10, 10);
	}
	public TextBox(int x_, int y_, int w_, int h_) {
		super(x_, y_, w_, h_);
		
		Keyboard.addTypableObject(this);
		
		editable = true;
		active = false;
		text = "";
		FontMetrics fm = draw.getFontMetrics(textFont);
		// We want space of at least 2 pixels above the text and below the text to the border, and 2 pixels between lines
		numLinesToDisplay = (h-4)/(fm.getMaxAscent()+2);
	}
	
	@Override
	public void keyTyped(KeyEvent e) { newKeyCode = e.getKeyChar(); }
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	
	public void addChar(char c) {
		text += c;
	}
	public void clear() {
		text = "";
	}
	
	
	@Override
	public void Update() {
		if (!enabled || !visible) 
			return;
		
		updateClickingState();
		if (isClicked()) {
			active = true;
		} else if (!containsMouse() && Mouse.isPressed()) {
			active = false;
		}
		if (newKeyCode != KEY_NOT_PRESSED && editable) {
			if (active) {
				if (newKeyCode == KeyEvent.VK_BACK_SPACE) {
					if (text.length() > 0) {
						text = text.substring(0, text.length()-1);
					}
				} else if (newKeyCode >= 32 && newKeyCode <= 126) {
					text += (char)newKeyCode;
				}
			}
			newKeyCode = KEY_NOT_PRESSED;
		}
	}
	
	@Override
	public void Draw() {
		if (!visible)
			return;
		
		FontMetrics fm = draw.getFontMetrics(textFont);
		int lineHeight = fm.getMaxAscent()+2;
		
		draw.setDrawColors(textAreaColor, borderColor, textColor);
		draw.rect(x, y, w, h);
		
		draw.setFont(textFont);
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
				if (active && (System.nanoTime() / 500000000) % 2 == 0) {
					draw.line(x+2 + fm.stringWidth(currentText)+1, y+5 + lineHeight*(currentLine-1), x+2 + fm.stringWidth(currentText)+1, y+2 + lineHeight*currentLine);
				}
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
