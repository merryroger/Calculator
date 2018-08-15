package winevents;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Winevents extends JFrame {

	protected Winevents() {
		super("Test window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			
			public void windowOpened(WindowEvent e) {
				System.out.println("Opened");
			}
			
			public void windowClosing(WindowEvent e) {
				System.out.println("Closing");
			}
	
			public void windowClosed(WindowEvent e) {
				System.out.println("Closed");
			}
			
			public void windowIconified(WindowEvent e) {
				System.out.println("Iconified");
			}
			
			public void windowDeiconified(WindowEvent e) {
				System.out.println("Deiconified");
			}
			
			public void windowActivated(WindowEvent e) {
				System.out.println("Activated");
			}
			
			public void windowDeactivated(WindowEvent e) {
				System.out.println("Deactivated");
			}
			
		});
		
		addWindowStateListener(new WindowAdapter() {
			
			public void windowStateChanged(WindowEvent e) {
				System.out.println("State changed");
			}
			
		});
		
		addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
				System.out.println("Gained focus");
			}
			
			public void windowLostFocus(WindowEvent e) {
				System.out.println("Lost focus");
			}
			
		});
		
		addComponentListener(new ComponentAdapter() {
		    
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
				System.out.println("Moved");
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				System.out.println("Resized");
				
			}

		});
		
		setSize(400, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Winevents();
	}

}
