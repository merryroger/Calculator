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
		super("���������� �������� ���������� �����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cars.add(new Carset("���", new String[] {"��� 965", "��� 968"}));
		cars.add(new Carset("���", new String[] {"��� 2101", "��� 2102", "��� 2103", "��� 2105", "��� 2106", "��� 2109", "��� 2121"}));
		cars.add(new Carset("�������", new String[] {"������� 403", "������� 407", "������� 408", "������� 412"}));

		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel pan = new JPanel();
		pan.setPreferredSize(new Dimension(100, 24));
		pan.setBackground(new Color(0xcc, 0xcc, 0xcc));
		pan.setLayout(new BorderLayout());
		pan.add("West", new JLabel("��� �����: "));
		
		choice = new JLabel("���� ������");
		pan.add("Center", choice);
		
		buildMenu();
		
		setJMenuBar(menuBar);
		
		c.add("South", pan);
		setSize(400, 300);
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void buildMenu() {
		JMenu mm = new JMenu("���������");
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
		
		mi = new JMenuItem("�������");
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
