package simple.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/** Object which utilizes the DrawModule and stores its own graphics object. Also includes methods to draw an image to that graphics object. **/
public class DrawObject {
	/** Stored Graphics2D object to which everything in this object is rendered to. **/
	protected Graphics2D g;
	
	/** Returns the DrawObject's stored Graphics2D object. **/
	public Graphics2D getGraphics() { return g; }
	/** Returns a FontMetrics object from the stored Graphics2D object's current font. **/
	public FontMetrics getFontMetrics() { return g.getFontMetrics(); }
	/** Returns a FontMetrics object from the given font throught the stored Graphics2D object. **/
	public FontMetrics getFontMetrics(Font font) { return g.getFontMetrics(font); }
	
	/** Creates a new DrawObject that draws to the given Graphics2D object. **/
	public DrawObject(Graphics2D g_) {
		g = g_;
	}
	
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
	
	/** Calls the DrawModule's rect function with the stored Graphics2D object. Refer to DrawModule. **/
	public void rect(int x, int y, int w, int h) {
		DrawModule.rect(g, x, y, w, h);
	}
	/** Calls the DrawModule's oval function with the stored Graphics2D object. Refer to DrawModule. **/
	public void oval(int x, int y, int w, int h) {
		DrawModule.oval(g, x, y, w, h);
	}
	/** Calls the DrawModule's line function with the stored Graphics2D object. Refer to DrawModule. **/
	public void line(int x1, int y1, int x2, int y2) {
		DrawModule.line(g, x1, y1, x2, y2);
	}
	
	/** Sets the stored Graphics2D object's stored font. **/
	public void setFont(Font font) { g.setFont(font); }
	/** Calls the DrawModule's setFont function with the stored Graphics2D object. Refer to DrawModule. Changes this Graphics2D object's
	 * stored font. **/
	public void text(String textToDraw, Font font, int x, int y) { DrawModule.text(g, textToDraw, font, x, y); }
	/** Calls the DrawModule's setFont function with the stored Graphics2D object. Refer to DrawModule. **/
	public void text(String textToDraw, int x, int y) { DrawModule.text(g, textToDraw, x, y); }
	
	/** Calls an Image object's Draw function using the stored Graphics2D object. Refer to Image. **/
	public void image(Image imageToDraw, int x, int y) { imageToDraw.Draw(g, x, y); }
	/** Calls an Image object's DrawCentered function using the stored Graphics2D object. Refer to Image. **/
	public void imageCentered(Image imageToDraw, int x, int y) { imageToDraw.DrawCentered(g, x, y); }
	/** Calls an Image object's DrawRotated function using the stored Graphics2D object. Refer to Image. **/
	public void imageRotated(Image imageToDraw, int x, int y, double angle) { imageToDraw.DrawRotated(g, x, y, angle); }
}
