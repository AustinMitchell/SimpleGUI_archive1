package simple.gui.panel;

import java.util.ArrayList;

import simple.gui.Widget;

// Mixture of grid panels and regular panels. Essentially resizes the pixels within the panel. 
// To add a new widget you must define the starting point and the grid space of the widget.
public class ScaledGridLayoutPanel extends Panel{
	class Rect {
		int x, y, w, h;
		Rect(int x_, int y_, int w_, int h_) {
			x = x_;
			y = y_;
			w = w_;
			h = h_;
		}
	}
	private ArrayList<Rect> coordinateList;
	
	private int rows, cols;
	private float boxWidth, boxHeight;
	
	private int startCol, startRow, colSpace, rowSpace;
	
	public int getRows() { return rows; }
	public int getCols() { return cols; }
	public int getTotalWidgetSpace() { return rows*cols; }
	public float getBoxWidth() { return boxWidth; } 
	public float getBoxHeight() { return boxHeight; }
	
	@Override
	public void setSize(int w_, int h_) {
		w = w_;
		h = h_;
		boxWidth = ((float)w)/cols;
		boxHeight = ((float)h)/rows;
		
		for (int i=0; i<widgetList.size(); i++) {
			widgetList.get(i).setLocation(x+(int)Math.floor((coordinateList.get(i).x*boxWidth)), y+(int)Math.floor((coordinateList.get(i).y*boxHeight)));
			widgetList.get(i).setSize((int)(Math.ceil(boxWidth*coordinateList.get(i).w)), (int)(Math.ceil(boxHeight*coordinateList.get(i).h)));
		}
	}
	@Override
	public void setWidth(int w_) {
		setSize(w_, h);
	}
	@Override
	public void setHeight(int h_) {
		setSize(w, h_);
	}
	
	public void setNextStartingPoint(int startCol_, int startRow_) {
		startCol = startCol_;
		startRow = startRow_;
	}
	public void setNextWidgetSpace(int colSpace_, int rowSpace_) {
		colSpace = colSpace_;
		rowSpace = rowSpace_;
	}
	
	public ScaledGridLayoutPanel(int rows_, int cols_) {
		this(0, 0, 10, 10, rows_, cols_);
	}
	public ScaledGridLayoutPanel(int x_, int y_, int w_, int h_, int rows_, int cols_) {
		super(x_, y_, w_, h_);
		rows = Math.max(1, rows_);
		cols = Math.max(1, cols_);
		
		coordinateList = new ArrayList<Rect>();
		boxWidth = ((float)w)/cols;
		boxHeight = ((float)h)/rows;
		startCol = 0;
		startRow = 0;
		colSpace = 1;
		rowSpace = 1;
	}

	public void addWidget(Widget newWidget, int startCol_, int startRow_, int colSpace_, int rowSpace_) {
		setNextStartingPoint(startCol_, startRow_);
		setNextWidgetSpace(colSpace_, rowSpace_);
		addWidget(newWidget);
	}
	@Override
	public void addWidget(Widget newWidget) {
		widgetList.add(newWidget);
		coordinateList.add(new Rect(startCol, startRow, colSpace, rowSpace));
		newWidget.setLocation(x+(int)Math.floor(startCol*boxWidth), y+(int)Math.floor(startRow*boxHeight));
		newWidget.setSize((int)(Math.ceil(boxWidth*colSpace)), (int)(Math.ceil(boxHeight*rowSpace)));
	}
}
