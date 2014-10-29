package simple.gui;

import java.awt.*;

/** Class with methods for drawing to a Graphics2D object and manipulating colors. 
 * <P>Note that if any of this classes color fields are null, when that color is used it will simply skip the operation rather than casting
 * an error. **/
public class DrawModule {	
	private static Color fillColor = Color.BLACK;
	private static Color borderColor = Color.BLACK;
	private static Color fontColor = Color.BLACK;
	
	/** Sets the class's local fillColor field. **/
	public static void setFillColor(Color newFillColor) { fillColor = newFillColor; }
	/** Sets the class's local borderColor field. **/
	public static void setBorderColor(Color newBorderColor) { borderColor = newBorderColor; }
	/** Sets the class's local fontColor field. **/
	public static void setFontColor(Color newFontColor) { fontColor = newFontColor; }
	/** Sets the class's local color fields. **/
	public static void setDrawColors(Color newFillColor, Color newBorderColor, Color newFontColor) {
		fillColor = newFillColor;
		borderColor = newBorderColor;
		fontColor = newFontColor;
	}
	
	/** Draws a rectangle. The outline is specified by borderColor, the fill by fillColor. 
	 * @param g			Graphics object to draw to.
	 * @param x			x coordinate of the bottom left (visually top left) corner. 
	 * @param y			y coordinate of the bottom left (visually top left) corner. 
	 * @param w			width of the retangle. 
	 * @param h			height of the rectangle. **/
	public static void rect(Graphics2D g, int x, int y, int w, int h) {
		if (fillColor != null) {
			g.setColor(fillColor);
			g.fillRect(x, y, w, h);
		}
		if (borderColor != null) {
			g.setColor(borderColor);
			g.drawRect(x, y, w, h);
		}
	}
	/** Draws an oval. The outline is specified by borderColor, the fill by fillColor. 
	 * @param g			Graphics object to draw to.
	 * @param x			x coordinate of the center. 
	 * @param y			y coordinate of the center. 
	 * @param w			x radius of the oval. 
	 * @param h			y radius of the oval. **/
	public static void oval(Graphics2D g, int x, int y, int w, int h) {
		if (fillColor != null) {
			g.setColor(fillColor);
			g.fillOval(x, y, w, h);
		}
		if (borderColor != null) {
			g.setColor(borderColor);
			g.drawOval(x, y, w, h);
		}
	}
	/** Draws a line between two points. The color is specified by borderColor. 
	 * @param g			Graphics object to draw to.
	 * @param x1		x coordinate of one end. 
	 * @param y1		y coordinate of one end. 
	 * @param x2		x coordinate of another end. 
	 * @param y2		y coordinate of another end. **/
	public static void line(Graphics2D g, int x1, int y1, int x2, int y2) {
		if (borderColor != null) {
			g.setColor(borderColor);
			g.drawLine(x1, y1, x2, y2);
		}
	}
	
	/** Draws text on the screen with a given font, with the text starting at the point x, y. This changes the currently set font
	 * for the given graphics object. 
	 * @param g				Graphics object to draw to.
	 * @param textToDraw	Text to draw to the screen. 
	 * @param font			Set's the currently used font for the graphics object. 
	 * @param x				x coordinate to start the drawing of the text.
	 * @param y				y coordinate to start the drawing of the text.**/
	public static void text(Graphics2D g, String textToDraw, Font font, int x, int y) {
		g.setFont(font);
		text(g, textToDraw, x, y);
	}
	/** Draws text on the screen with whatever font the graphics object is using, with the text starting at the point x, y.
	 * for the given graphics object. 
	 * @param g				Graphics object to draw to.
	 * @param textToDraw	Text to draw to the screen. 
	 * @param x				x coordinate to start the drawing of the text.
	 * @param y				y coordinate to start the drawing of the text.**/
	public static void text(Graphics2D g, String textToDraw, int x, int y) {
		if (fontColor != null) {
			g.setColor(fontColor);
			g.drawString(textToDraw, x, y);
		}
	}
	
	/** Multiplies each value in a Color object by a constant.
	 * @param c				Base color
	 * @param scale			Constant scalar value **/
	public static Color scaleColor(Color c, float scale) {
		return new Color((int)(c.getRed()*scale), (int)(c.getGreen()*scale), (int)(c.getBlue()*scale));
	}
	/** Multiplies each value in a Color object by a constant. Each color is multiplied by a different constant.
	 * @param c				Base color
	 * @param rscale		Constant scalar value for red
	 * @param gscale		Constant scalar value for green
	 * @param bscale		Constant scalar value for blue**/
	public static Color scaleColor(Color c, float rscale, float gscale, float bscale) {
		return new Color(c.getRed()*rscale, c.getGreen()*gscale, c.getBlue()*bscale);
	}
}
