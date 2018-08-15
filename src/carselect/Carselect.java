package carselect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.MenuBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Carselect extends JFrame {

	private JLabel choice;
	private JMenuBar menuBar;
	private ArrayList<Carset> cars = new ArrayList<Carset>();
	
	private Carselect() {
		super("Продолжаем выбирать автомобиль мечты");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cars.add(new Carset("ЗАЗ", new String[] {"ЗАЗ 965", "ЗАЗ 968"}));
		cars.add(new Carset("ВАЗ", new String[] {"ВАЗ 2101", "ВАЗ 2102", "ВАЗ 2103", "ВАЗ 2105", "ВАЗ 2106", "ВАЗ 2109", "ВАЗ 2121"}));
		cars.add(new Carset("Москвич", new String[] {"Москвич 403", "Москвич 407", "Москвич 408", "Москвич 412"}));

		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel pan = new JPanel();
		pan.setPreferredSize(new Dimension(100, 24));
		pan.setBackground(new Color(0xcc, 0xcc, 0xcc));
		pan.setLayout(new BorderLayout());
		pan.add("West", new JLabel("Ваш выбор: "));
		
		choice = new JLabel("пока ничего");
		pan.add("Center", choice);
		
		buildMenu();
		
		setJMenuBar(menuBar);
		
		c.add("South", pan);
		setSize(400, 300);
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void buildMenu() {
		JMenu mm = new JMenu("Автомарки");
		JMenu sm;
		JMenuItem mi;
		
		for(Carset c : cars) {
			sm = new JMenu(c.getBrend());
			
			for(String mod : c.getModelList()) {
				mi = new JMenuItem(mod);
				mi.addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent e) {
						getSelection((JMenuItem) e.getSource());
					}
				});
				sm.add(mi);
			}
			
			mm.add(sm);
		}
		
		mm.addSeparator();
		
		mi = new JMenuItem("Закрыть");
		mi.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		mm.add(mi);
				
		
		menuBar = new JMenuBar();
		menuBar.add(mm);
	}
	
	private void getSelection(JMenuItem mi) {
		choice.setText(mi.getText());
	}
	
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new Carselect();
	}

}
