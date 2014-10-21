package simple.gui;

public class ImageBox extends Widget {
	Image image;
	boolean drawCentered;
	
	public Image getImage() { return image; }
	
	public void setImage(Image image_) { image = image_.resize(w, h); }
	public void setDrawCentered(boolean centered) { drawCentered = centered; }
	
	@Override
	public void setSize(int w_, int h_) {
		super.setSize(w_, h_);
		if (image != null)
			image = image.resize(w, h);
	}
	@Override
	public void setWidth(int w_) { setSize(w_, h); }
	@Override
	public void setHeight(int h_) { setSize(w, h_); }

	
	public ImageBox(Image image_) {
		this(0, 0, image_);
	}
	public ImageBox(int x_, int y_, Image image_) {
		setLocation(x_, y_);
		drawCentered = false;
		if (image_ != null) { 
			image = image_;
			setSize(image.getWidth(), image.getHeight());
		} else {
			setSize(1, 1);
		}
	}
	public ImageBox(int x_, int y_, int w_, int h_, Image image_) {
		super(x_, y_, w_, h_);
		drawCentered = false;
		if (image_ != null) 
			image = image_.resize(w, h);
	}
	
	@Override
	public void Update() {
		if (!enabled || !visible) 
			return;
		updateClickingState();
	}
	
	@Override
	public void Draw() {
		if (!visible) {
			return;
		}
		if (image != null) {
			if (drawCentered) {
				draw.imageCentered(this.image, x, y);
			} else {
				draw.image(this.image, x, y);
			}
		}
	}
	public void DrawRotated(double angle) {
		if (!visible) {
			return;
		}
		if (image != null) {
			draw.imageRotated(this.image, x, y, angle);
		}
	}
}
