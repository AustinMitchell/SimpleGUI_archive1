package simple.run;

@SuppressWarnings("serial")
public class MainProgramTemplate extends ScreenPanel {
	public static void main(String[] args) {
		GUIRunWindow window = new GUIRunWindow(new MainProgramTemplate(), "Program Name", false);
	}
	
	public MainProgramTemplate() {
		// Set the size of the screen and frames per second
		super(500, 500, 30);
	}
	
	// MUST HAVE
	// Program initialization should be done here
	public void initializeProgram() {}
	
	// MUST HAVE
	// All widget updating and any other program updates should be done here
	// Note all widgets have a designated "Update()" function
	public void Update() {}

	// MUST HAVE
	// All widget drawing and any other drawings should be done here
	// Note all widgets have a designated "Draw()" function
	public void Draw() {
		
		// Necessary call to draw everything to the screen
		DrawToScreen();
		// Call this to wipe screen to default background color
		cls();
		// Call this to delay the program for the appropriate amount of time
		// This will take into account time passed since last call. Use Time.delay(long delayMillis) for a full delay
		Timer.correctedDelay(DELAY_TIME);
	}
	
}