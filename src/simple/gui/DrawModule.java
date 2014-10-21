package simple.gui;

import java.awt.*;

public class DrawModule {	
	private static Color fillColor = Color.BLACK;
	private static Color borderColor = Color.BLACK;
	private static Color fontColor = Color.BLACK;
	
	public static void setFillColor(Color c_) { fillColor = c_; }
	public static void setBorderColor(Color c_) { borderColor = c_; }
	public static void setFontColor(Color c_) { fontColor = c_; }
	public static void setDrawColors(Color newFillColor, Color newBorderColor, Color newFontColor) {
		fillColor = newFillColor;
		borderColor = newBorderColor;
		fontColor = newFontColor;
	}
	
	// Draws shapes using its stored color data. Null color means nothing is drawn
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
	public static void line(Graphics2D g, int x1, int y1, int x2, int y2) {
		if (borderColor != null) {
			g.setColor(borderColor);
			g.drawLine(x1, y1, x2, y2);
		}
	}
	
	public static void text(Graphics2D g, String textToDraw, Font font, int x, int y) {
		g.setFont(font);
		text(g, textToDraw, x, y);
	}
	public static void text(Graphics2D g, String textToDraw, int x, int y) {
		if (fontColor != null) {
			g.setColor(fontColor);
			g.drawString(textToDraw, x, y);
		}
	}
	
	// returns a Color object with its components scaled to a value between 0 and 1
	public static Color scaleColor(Color c, float scale) {
		return new Color((int)(c.getRed()*scale), (int)(c.getGreen()*scale), (int)(c.getBlue()*scale));
	}
	public static Color scaleColor(Color c, float rscale, float gscale, float bscale) {
		return new Color(c.getRed()*rscale, c.getGreen()*gscale, c.getBlue()*bscale);
	}
}
