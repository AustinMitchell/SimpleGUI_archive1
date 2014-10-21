package simple.run;

@SuppressWarnings("serial")
public class MainProgramEmptyTemplate extends ScreenPanel {
	public static void main(String[] args) {
		GUIRunWindow window = new GUIRunWindow(new MainProgramEmptyTemplate(), "Program Name", false);
	}
	
	public MainProgramEmptyTemplate() {
		super(500, 500, 30);
	}
	
	public void initializeProgram() {
		
	}
	
	public void Update() {
		
	}

	public void Draw() {		
		DrawToScreen();
		cls();
		Timer.correctedDelay(DELAY_TIME);
	}
}