package simple.gui;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public final class Image {
    private BufferedImage image;            
    private String filename;                   
    private int w, h;          

    public int getWidth() { return w; }
    public int getHeight() { return h; }
    public BufferedImage getBufferedImage() { return image; }
    public String getFileName() { return filename; }
    
    public Image(int w_, int h_) {
        if (w < 0) throw new IllegalArgumentException("width must be non-negative");
        if (h < 0) throw new IllegalArgumentException("height must be non-negative");
        w = w_;
        h = h_;
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        // set to TYPE_INT_ARGB to support transparency
        filename = w + "-by-" + h;
    }

    public Image(Image imageToCopy) {
        w = imageToCopy.getWidth();
        h = imageToCopy.getHeight();
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        filename = imageToCopy.getFileName();
        for (int col = 0; col < w; col++)
            for (int row = 0; row < h; row++)
                image.setRGB(col, row, imageToCopy.get(col, row).getRGB());
    }
    
    public Image(BufferedImage imageToCopy) {
        w = imageToCopy.getWidth();
        h = imageToCopy.getHeight();
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        filename = w + "-by-" + h;
        for (int col = 0; col < w; col++)
            for (int row = 0; row < h; row++)
                image.setRGB(col, row, imageToCopy.getRGB(col, row));
    }

    public Image(String filename_) {
        filename = filename_;
        try {
            // try to read from file in working directory
            File file = new File(filename);
            if (file.isFile()) {
                image = ImageIO.read(file);
            }

            // now try to read from file in same directory as this .class file
            else {
                URL url = getClass().getResource(filename);
                if (url == null) { url = new URL(filename); }
                image = ImageIO.read(url);
            }
            w  = image.getWidth(null);
            h = image.getHeight(null);
        }
        catch (IOException e) {
            // e.printStackTrace();
            throw new RuntimeException("Could not open file: " + filename);
        }
    }

    public Image(File file) {
        try { image = ImageIO.read(file); }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not open file: " + file);
        }
        if (image == null) {
            throw new RuntimeException("Invalid image file: " + file);
        }
        w  = image.getWidth(null);
        h = image.getHeight(null);
        filename = file.getName();
    }
    
    public static Image resize(Image image, int newWidth, int newHeight) {
    	return image.resize(newWidth, newHeight);
    }
    public Image resize(int newWidth, int newHeight) {
    	if (newWidth == w && newHeight == h) {
    		return new Image(this);
    	}
    	Image newImage = new Image(newWidth, newHeight);
    	float newToOldScaleX = (float)w/newWidth;
    	float newToOldScaleY = (float)h/newHeight;
		for (int x=0; x<newWidth; x++) {
			for (int y=0; y<newHeight; y++) {
    			newImage.set(x, y, this.get(Math.min(w-1, Math.round(x*newToOldScaleX)), Math.min(h-1, Math.round(y*newToOldScaleY))));
    		}
		}
		return newImage;
    }
    
    public Color get(int x, int y) {
        if (x < 0 || x >= w)  throw new IndexOutOfBoundsException("x must be between 0 and " + (w-1) + ", recieved " + x);
        if (y < 0 || y >= h) throw new IndexOutOfBoundsException("y must be between 0 and " + (h-1) + ", recieved " + y);
        return new Color(image.getRGB(x, y));
    }
    public Color[] getPixels() {
    	Color[] pixelData = new Color[w*h];
    	for (int x=0; x<w; x++)
    		for (int y=0; y<h; y++)
    			pixelData[x*w + y] = this.get(x, y);
    	
    	return pixelData;
    }

    public void set(int x, int y, Color color) {
        if (x < 0 || x >= w)  throw new IndexOutOfBoundsException("x must be between 0 and " + (w-1) + ", recieved " + x);
        if (y < 0 || y >= h) throw new IndexOutOfBoundsException("y must be between 0 and " + (h-1) + ", recieved " + y);
        if (color == null) throw new NullPointerException("can't set Color to null");
        image.setRGB(x, y, color.getRGB());
    }
    public void setPixels(Color[] pixelData) {
    	for (int x=0; x<w; x++)
    		for (int y=0; y<h; y++)
    			this.set(x, y, pixelData[x*w + y]);	
    }

    public void Draw(Graphics2D g, int x, int y) {
    	g.drawImage(image, x, y, null);
    }
    public void DrawCentered(Graphics2D g, int x, int y) {
    	g.drawImage(image, x-(int)image.getWidth()/2, y-(int)image.getHeight()/2, null);
    }
    public void DrawRotated(Graphics2D g, int x, int y, double angle) {
    	g.rotate(angle, x, y);
    	g.drawImage(image, x-(int)image.getWidth()/2, y-(int)image.getHeight()/2, null);
    	g.rotate(-angle, x, y);
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Image that = (Image) obj;
        if (this.getWidth()  != that.getWidth())  return false;
        if (this.getHeight() != that.getHeight()) return false;
        for (int x = 0; x < w; x++)
            for (int y = 0; y < h; y++)
                if (!this.get(x, y).equals(that.get(x, y))) return false;
        return true;
    }
}