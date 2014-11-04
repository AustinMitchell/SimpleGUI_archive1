package simple.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/** Object which utilizes the DrawModule and stores its own graphics object. Also includes methods to draw an image to that graphics object. **/
public class DrawObject {
	
	/** Returns the DrawObject's stored Graphics2D object. **/
	public Graphics2D getGraphics() { return DrawModule.getGraphics(); }
	/** Returns a FontMetrics object from the stored Graphics2D object's current font. **/
	public FontMetrics getFontMetrics() { return DrawModule.getFontMetrics(); }
	/** Returns a FontMetrics object from the given font throught the stored Graphics2D object. **/
	public FontMetrics getFontMetrics(Font font) { return DrawModule.getFontMetrics(font); }
	
	/** Sets the DrawModules's fillColor. **/
	public void setFillColor(Color newFillColor) { DrawModule.setFillColor(newFillColor); }
	/** Sets the DrawModules's borderColor. **/
	public void setBorderColor(Color newBorderColor) { DrawModule.setBorderColor(newBorderColor); }
	/** Sets the DrawModules's fontColor. **/
	public void setFontColor(Color newFontColor) { DrawModule.setFontColor(newFontColor); }
	/** Sets the DrawModules's color fields. **/
	public void setDrawColors(Color newFillColor, Color newBorderColor, Color newFontColor) {
		DrawModule.setDrawColors(newFillColor, newBorderColor, newFontColor);
	}
	
	/** Calls the DrawModule's polygon function. Refer to DrawModule. **/
	public void polygon(int[] x, int[] y, int numPoints) {
		DrawModule.polygon(x, y, numPoints);
	}
	/** Calls the DrawModule's rect function. Refer to DrawModule. **/
	public void rect(int x, int y, int w, int h) {
		DrawModule.rect(x, y, w, h);
	}
	/** Calls the DrawModule's oval function. Refer to DrawModule. **/
	public void oval(int x, int y, int w, int h) {
		DrawModule.oval(x, y, w, h);
	}
	/** Calls the DrawModule's line function. Refer to DrawModule. **/
	public void line(int x1, int y1, int x2, int y2) {
		DrawModule.line(x1, y1, x2, y2);
	}
	
	/** Sets DrawModule's stored font. **/
	public void setFont(Font font) { DrawModule.setFont(font); }
	/** Calls the DrawModule's text function. Refer to DrawModule. Changes the current font. **/
	public void text(String textToDraw, Font font, int x, int y) { DrawModule.text(textToDraw, font, x, y); }
	/** Calls the DrawModule's text function. Refer to DrawModule. **/
	public void text(String textToDraw, int x, int y) { DrawModule.text(textToDraw, x, y); }
	public void textCentered(String textToDraw, Font font, int x, int y) { DrawModule.textCentered(textToDraw, font, x, y);}
	public void textCentered(String textToDraw, int x, int y) { DrawModule.textCentered(textToDraw, x, y);}
	
	/** Calls an Image object's Draw function using the stored Graphics2D object. Refer to Image. **/
	public void image(Image imageToDraw, int x, int y) { DrawModule.image(imageToDraw, x, y); }
	/** Calls an Image object's DrawCentered function using the stored Graphics2D object. Refer to Image. **/
	public void imageCentered(Image imageToDraw, int x, int y) { DrawModule.imageCentered(imageToDraw, x, y); }
	/** Calls an Image object's DrawRotated function using the stored Graphics2D object. Refer to Image. **/
	public void imageRotated(Image imageToDraw, int x, int y, double angle) { DrawModule.imageRotated(imageToDraw, x, y, angle); }
}
