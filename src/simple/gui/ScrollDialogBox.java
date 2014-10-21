package simple.gui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;

public class ScrollDialogBox extends ScrollBox {
	ArrayList<String> lines, lineDisplay;	
	
	int firstIndex, numLinesToDisplay;
	
	public ArrayList<String> getLineData() { return lines; }
	public ArrayList<String> getLineDisplay() { return lineDisplay; }

	@Override
	public void setTextFont(Font f_) {
		super.setTextFont(f_);
		reformatDisplay();
	}
	
	@Override
	public void setSize(int w_, int h_) {
		super.setSize(w_, h_);
		reformatDisplay();
	}
	@Override
	public void setWidth(int w_) { setSize(w_, h); }
	@Override
	public void setHeight(int h_) { setSize(w, h_); }
	
	public ScrollDialogBox() {
		this(0, 0, 10, 10);
	}
	public ScrollDialogBox(int x_, int y_, int w_, int h_) {
		super(x_, y_, w_, h_);
		
		lines = new ArrayList<String>();
		lineDisplay = new ArrayList<String>();
		firstIndex = -1;
		FontMetrics fm = draw.getFontMetrics(textFont);
		numLinesToDisplay = (h-4)/(fm.getMaxAscent()+2);
	}
	
	private void reformatDisplay() {
		FontMetrics fm = draw.getFontMetrics(textFont);
		numLinesToDisplay = (h-4)/(fm.getMaxAscent()+2);
		
		clearDisplay();
		for(String s: lines) 
			addLine(s);
	}
	
	public void addLine(String newLine) {
		lines.add(newLine);
		
		FontMetrics fm = draw.getFontMetrics(textFont);		
		String currentText = newLine;
		int lineWidth = w-4-BAR_WIDTH;
		
		first: while(true) {
			// checks if the partitioned string will fit in the line
			if (fm.stringWidth(currentText) <= lineWidth) {
				lineDisplay.add(currentText);
				break first;
			// Searches for a point in the partitioned string that will fit in the box's bounds. Takes formation of words into account
			} else {
				for (int i=currentText.length()-1; i>=0; i--) {
					if (fm.stringWidth(currentText.substring(0, i+1)) <= lineWidth) {
						int lastIndex = i;
						for (int j=lastIndex; j>0; j--) {
							if (currentText.charAt(j) == ' ') {
								if (!(j == 1 && currentText.charAt(0) == ' ')) {
									lastIndex = j;
								}
								break;
							}
						}
						lineDisplay.add(currentText.substring(0, lastIndex+1));
						currentText = "  " + currentText.substring(lastIndex+1);
						break;
					}
				}
			}
		}
		if (lineDisplay.size() == 1 && firstIndex == -1) {
			firstIndex = 0;
		} else if (lineDisplay.size() > 1) {
			firstIndex = Math.max(lineDisplay.size()-numLinesToDisplay, 0);
			scrollBar.setValue(firstIndex);
		}
		scrollBar.setRange(0, lineDisplay.size()-1);
	}
	
	public void clear() {
		lines = new ArrayList<String>();
		lineDisplay = new ArrayList<String>();
	}
	private void clearDisplay() {
		lineDisplay = new ArrayList<String>();
	}
	
	@Override
	public void Update() {
		if (!enabled || !visible) 
			return;
		
		updateScrollWidgets(); 
		
		if (scrollUp.isClicked() && firstIndex > 0) {
			firstIndex -= 1;
			scrollBar.setValue(firstIndex);
		} else if (scrollDown.isClicked() && firstIndex < lineDisplay.size()-1) {
			firstIndex += 1;
			scrollBar.setValue(firstIndex);
		}
		
		if (scrollBar.getValue() != firstIndex) {
			firstIndex = scrollBar.getValue();
		}
	}
	
	@Override
	public void Draw() {
		if (!visible) 
			return;
		
		drawScrollWidgets();
		
		draw.setDrawColors(fillColor, borderColor, null);
		draw.rect(x, y, w-BAR_WIDTH, h);
		
		FontMetrics fm = draw.getFontMetrics(textFont);
		int lineHeight = fm.getMaxAscent()+2;
		
		draw.setFont(textFont);
		draw.setFontColor(textColor);
		
		if (firstIndex != -1) {
			for (int i=firstIndex; i<firstIndex+numLinesToDisplay && i<lineDisplay.size(); i++) {
				draw.text(lineDisplay.get(i), x+2, y+2+lineHeight*(i+1-firstIndex));
			}
		}
	}
}
