package simple.run;

@SuppressWarnings("serial")
public class MainProgramTemplate extends ScreenPanel {
	// MUST HAVE
	// This creates a JFrame that contains the Panel (ScreenPanel) running your program
	public static void main(String[] args) {
		GUIRunWindow window = new GUIRunWindow(new MainProgramTemplate(), "Program Name", false);
	}
	
	// MUST HAVE
	// Constructor should CONTAIN NO INITIALIZATION for your program, EXCEPT THE SUPER() CONSTRUCTOR.
	public MainProgramTemplate() {
		// Set the size of the screen and frames per second
		super(500, 500, 30);
	}
	
	// MUST HAVE
	// Program initialization should be done here
	public void setup() {}
	
	// MUST HAVE
	// Main loop for the program. Loops everything here.
	public void loop() {}
	
	// RECOMMENDED
	// All widget updating and any other program updates should be done here
	// Note all widgets have a designated "Update()" function
	public void Update() {}

	// RECOMMENDED
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