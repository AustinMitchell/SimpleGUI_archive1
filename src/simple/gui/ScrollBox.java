package simple.gui;

public abstract class ScrollBox extends Widget {
	protected static final int BAR_WIDTH = 20;
	
	protected Slider scrollBar;
	protected Button scrollUp, scrollDown;
	
	public Button getScrollUpButton() { return scrollUp; }
	public Button getScrollDownButton() { return scrollDown; }
	public Slider getScrollBar() { return scrollBar; }
	
	@Override
	public void setSize(int w_, int h_) {
		super.setSize(w_, h_);
		setScrollWidgetPosition();
	}
	@Override
	public void setWidth(int w_) { setSize(w_, h); }
	@Override
	public void setHeight(int h_) { setSize(w, h_); }
	
	@Override
	public void setLocation(int x_, int y_) {
		super.setLocation(x_, y_);
		setScrollWidgetPosition();
	}
	@Override
	public void setX(int x_) { setLocation(x_, y); }
	@Override
	public void setY(int y_) { setLocation(x, y_); 
	}
	
	public ScrollBox() {
		this(0, 0, 10, 10);
	}
	public ScrollBox(int x_, int y_, int w_, int h_) {
		super(x_, y_, w_, h_);
		scrollUp = new Button(x+w-BAR_WIDTH, y, BAR_WIDTH, BAR_WIDTH, "^", null);
		scrollDown = new Button(x+w-BAR_WIDTH, y+h-BAR_WIDTH, BAR_WIDTH, BAR_WIDTH, "v", null);
		scrollBar = new Slider(x+w-BAR_WIDTH, y+BAR_WIDTH, BAR_WIDTH, h-BAR_WIDTH*2, 0, 0, false, false);
	}
	
	protected void setScrollWidgetPosition() {
		scrollUp.setLocation(x+w-BAR_WIDTH, y);
		scrollUp.setSize(BAR_WIDTH, BAR_WIDTH);
		scrollDown.setLocation(x+w-BAR_WIDTH, y+h-BAR_WIDTH);
		scrollDown.setSize(BAR_WIDTH, BAR_WIDTH);
		scrollBar.setLocation(x+w-BAR_WIDTH, y+BAR_WIDTH);
		scrollBar.setSize(BAR_WIDTH, h-BAR_WIDTH*2);
	}
	
	protected void updateScrollWidgets() {
		scrollUp.Update();
		scrollDown.Update();
		scrollBar.Update();
	}
	
	protected void drawScrollWidgets() {
		scrollUp.Draw();
		scrollDown.Draw();
		scrollBar.Draw();
	}
	
	public abstract void Update();
	public abstract void Draw();
}
