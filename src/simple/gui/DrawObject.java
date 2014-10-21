package simple.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class DrawObject {
	Graphics2D g;
	
	public Graphics2D getGraphics() { return g; }
	public FontMetrics getFontMetrics() { return g.getFontMetrics(); }
	public FontMetrics getFontMetrics(Font font) { return g.getFontMetrics(font); }
	
	public DrawObject(Graphics2D g_) {
		g = g_;
	}
	
	public void setFillColor(Color c_) { DrawModule.setFillColor(c_); }
	public void setBorderColor(Color c_) { DrawModule.setBorderColor(c_); }
	public void setFontColor(Color c_) { DrawModule.setFontColor(c_); }
	public void setDrawColors(Color newFillColor, Color newBorderColor, Color newFontColor) {
		DrawModule.setDrawColors(newFillColor, newBorderColor, newFontColor);
	}
	
	public void rect(int x, int y, int w, int h) {
		DrawModule.rect(g, x, y, w, h);
	}
	public void oval(int x, int y, int w, int h) {
		DrawModule.oval(g, x, y, w, h);
	}
	public void line(int x1, int y1, int x2, int y2) {
		DrawModule.line(g, x1, y1, x2, y2);
	}
	
	public void setFont(Font font) { g.setFont(font); }
	public void text(String textToDraw, Font font, int x, int y) {
		DrawModule.text(g, textToDraw, font, x, y);
	}
	public void text(String textToDraw, int x, int y) {
		DrawModule.text(g, textToDraw, x, y);
	}
	
	public void image(Image imageToDraw, int x, int y) {
		imageToDraw.Draw(g, x, y);
	}
	public void imageCentered(Image imageToDraw, int x, int y) {
		imageToDraw.DrawCentered(g, x, y);
	}
	public void imageRotated(Image imageToDraw, int x, int y, double angle) {
		imageToDraw.DrawRotated(g, x, y, angle);
	}
}
