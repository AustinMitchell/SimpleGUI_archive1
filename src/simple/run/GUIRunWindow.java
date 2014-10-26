package simple.run;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GUIRunWindow extends JFrame{
	public GUIRunWindow(ScreenPanel programToRun, String title, boolean isUndecorated) {
		super(title);
		setContentPane(programToRun);
		setUndecorated(isUndecorated);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setVisible(true);
	}
	public GUIRunWindow(ScreenPanel programToRun, String title) {
		super(title);
		setContentPane(programToRun);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setVisible(true);
	}
	/*public GUIRunWindow(ScreenPanel programToRun, String title, String iconFilePath) {
		super(title);
		setContentPane(programToRun);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		ImageIcon icon = new ImageIcon(iconFilePath);
		this.setIconImage(icon.getImage());
		
		pack();
		setVisible(true);
	}*/
}
