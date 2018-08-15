package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * Класс Calc_view отвечает за визуализацию калькулятора.
 * @author Ehwaz Raido
 *
 */
public class Calc_view extends JFrame {

	private int dCount;
	private String s;
	private JLabel scr0, scr1, scr2;
	private JPanel screen, numpad, ctrlpad;
	private JButton[] nbset = new JButton[11];
	private JButton[] ctrlset = new JButton[8];
	private Calculator calc;
	
	protected Calc_view(Calculator c) {
		super("Calculator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		calc = c;
		
		this.createScreen();
		this.createNumPad();
		this.createCtrlPad();
		
		BorderLayout border = new BorderLayout(10, 10);
		this.setLayout(border);
		
		add("North", screen);
		add("West", numpad);
		add("Center", ctrlpad);
		
		addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e) {
				char kc;
				
				switch(e.getKeyCode()) {
					case 8: kc = 'B'; break;
					case 10: kc = '='; break;
					case 127: kc = 'C'; break;
					default:
						kc = e.getKeyChar();
				}
	
				switch(kc) {
					case '0': case '1': case '2': case '3': case '4': case '5':
					case '6': case '7': case '8': case '9': case '.':
						if(calc.getOperation().equals(new String("="))) {
							reset();
						}
						
						enterKey(String.valueOf(e.getKeyChar()));
					break;
					case 'c': case 'C': 
						selectCommand("C");
					break;
					case '-': case '+': case '/':
						selectCommand(String.valueOf(e.getKeyChar()));
					break;
					case 'X': case 'x': case '*':
						selectCommand("x");
					break;
					case 'S': case 's':
						selectCommand("SQR");
					break;
					case 'B':
						selectCommand("<<");
					break;
					case '=':
						selectCommand("=");
				}
				
			}
			
		});
		
		setSize(480, 360);
		setResizable(false);
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Метод createScreen создаёт и отрисовывает дисплей калькулятора.
	 */
	private void createScreen() {
		JPanel st = new JPanel();
		st.setLayout(new BorderLayout());
		st.setBackground(new Color(0xbb, 0xbb, 0x99));
		screen = new JPanel();
		screen.setBackground(new Color(0xbb, 0xbb, 0x99));
		Border border = BorderFactory.createLineBorder(new Color(0x88, 0x88, 0x66), 2);
		screen.setBorder(border);
		screen.setLayout(new BorderLayout());
		scr2 = new JLabel("0");
		scr2.setHorizontalAlignment(SwingConstants.LEFT);
		scr2.setFont(new Font("Courier", Font.PLAIN, 12));
		scr2.setPreferredSize(new Dimension(20, 12));
		scr1 = new JLabel(" ");
		scr1.setHorizontalAlignment(SwingConstants.RIGHT);
		scr1.setFont(new Font("Courier", Font.PLAIN, 12));
		scr0 = new JLabel(" ");
		scr0.setHorizontalAlignment(SwingConstants.RIGHT);
		scr0.setFont(new Font("Courier", Font.PLAIN, 30));
		st.add("West", scr2);
		st.add("Center", scr1);
		screen.add("North", st);
		screen.add("South", scr0);
		reset();
		screen.setVisible(true);
	}
	
	/**
	 * Метод reset выполняет "сброс" параметров калькулятора в исходное состояние.
	 */
	public void reset() {
		dCount = 0;
		calc.creset();
		scr2.setText(" 0");
		scr1.setText(" ");
		scr0.setText("0. ");
	}
	
	/**
	 * Метод createNumPad создаёт и отрисовывает клавиатуру для ввода данных.
	 */
	private void createNumPad() {
		int m;
		numpad = new JPanel();
		numpad.setPreferredSize(new Dimension(240, 200));
		numpad.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 40));
		JPanel pad = new JPanel();
		pad.setLayout(new GridLayout(4, 3, 5, 5));
		for(int i = 0; i < nbset.length; i++) {
			switch(i) {
				case 0: m = 7; s = String.valueOf(m); break;
				case 1: m = 8; s = String.valueOf(m); break;
				case 2: m = 9; s = String.valueOf(m); break;
				case 3: m = 4; s = String.valueOf(m); break;
				case 4: m = 5; s = String.valueOf(m); break;
				case 5: m = 6; s = String.valueOf(m); break;
				case 6: m = 1; s = String.valueOf(m); break;
				case 7: m = 2; s = String.valueOf(m); break;
				case 8: m = 3; s = String.valueOf(m); break;
				case 9: m = 0; s = String.valueOf(m); break;
				default: m = 10; s = "."; break;
			}
			nbset[m] = new JButton(s);
			nbset[m].setPreferredSize(new Dimension(50, 45));
			nbset[m].setForeground(Color.WHITE);
			nbset[m].setBackground(new Color(0x33, 0x33, 0x33));
			nbset[m].setFont(new Font("Courier", Font.BOLD, 22));
			nbset[m].setFocusable(false);
			nbset[m].addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					JButton b = (JButton) e.getSource();
					if(calc.getOperation().equals(new String("="))) {
						reset();
					}
					enterKey(b.getText());
				}
			
			});
					
			pad.add(nbset[m]);
		}
		numpad.add(pad);
	}
	
	/**
	 * Метод createCtrlPad создаёт и отрисовывает клавиатуру для управления калькулятором.
	 */
	private void createCtrlPad() {
		Color c;
		ctrlpad = new JPanel();
		ctrlpad.setPreferredSize(new Dimension(240, 200));
		ctrlpad.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 40));
		JPanel pad = new JPanel();
		pad.setLayout(new GridLayout(4, 2, 5, 5));
		for(int i = 0; i < ctrlset.length; i++) {
			c = new Color(0x11, 0x22, 0x55);
			switch(i) {
				case 0: s = "C"; c = new Color(0xcc, 0x0, 0x0); break;
				case 1: s = "<<"; c = new Color(0xcc, 0x0, 0x0); break;
				case 2: s = "+"; break;
				case 3: s = "-"; break;
				case 4: s = "x"; break;
				case 5: s = "/"; break;
				case 6: s = "SQR"; break;
				default: s = "="; c = new Color(0xcc, 0x0, 0x0);; break;
			}
			ctrlset[i] = new JButton(s);
			ctrlset[i].setPreferredSize(new Dimension(80, 45));
			ctrlset[i].setForeground(Color.WHITE);
			ctrlset[i].setBackground(c);
			ctrlset[i].setFont(new Font("Courier", Font.PLAIN, 19));
			ctrlset[i].setFocusable(false);
			ctrlset[i].addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {
					JButton b = (JButton) e.getSource();
					selectCommand(b.getText());
				}
				
			});
			
			pad.add(ctrlset[i]);
		}
		ctrlpad.add(pad);
	}
	
	/**
	 * Метод обрабатыват события от кнопок ввода данных.
	 * @param s String (символ клавиши)
	 */
	private void enterKey(String s) {
		String s2;
		
		if(dCount >= 16) {
			scr2.setText(" 16!");
			return;
		}
		
		switch(s) {
			case ".": s2 = " " + String.valueOf(dCount) + "."; scr2.setText(s2); break;
			case "-": s2 = " " + String.valueOf(dCount) + "-"; scr2.setText(s2); break;
			default: s2 = " " + String.valueOf(dCount);
		}
		
		if(calc.appendSign(s, dCount)) {
			dCount++;
			calc.setDCount(dCount);
			scr2.setText(" " + String.valueOf(dCount));
		}
		else
			dCount = calc.getDCount();
		
		redraw();
	}
	
	private void selectCommand(String cmd) {
		switch(cmd) {
			case "C": reset(); break;
			case "-": if(dCount == 0 && !calc.getOperation().equals(new String("=")))
						enterKey("-");
					else {
						setCommand("-");
					}
			break;
			case "+": setCommand("+"); break;
			case "x": setCommand("*"); break;
			case "/": setCommand("/"); break;
			case "<<": setCommand("<<"); break;
			case "SQR": setCommand("SQR");
				scr1.setText("SQRT ");
				scr0.setText(((calc.getResult().indexOf(".") < 0) ? calc.getResult() + "." : calc.getResult()) + calc.getSign());
				break;
			case "=": setCommand("=");
				scr2.setText(" 0");
				scr1.setText("= ");
				scr0.setText(((calc.getResult().indexOf(".") < 0) ? calc.getResult() + "." : calc.getResult()) + calc.getSign());
			break;
		}
	}
	
	/**
	 *  Метод обрабатыват события от кнопок управления.
	 * @param cmd String (символ клавиши)
	 */
	private void setCommand(String cmd) {
		switch(cmd) {
			case "SQR": calc.getRoot(); break;
			case "<<": if(calc.backSpace()) {
							dCount = calc.getDCount(); scr2.setText(" " + String.valueOf(dCount)); redraw();
					}
			 		return;
			default: calc.setCommand(cmd);
		}
		scr1.setText(calc.getOp2() + calc.getOperationSign(cmd));
		dCount = 0;
		calc.setDCount(dCount);
		scr2.setText(" 0");
		if(calc.isError())
			scr0.setText(((calc.getResult().indexOf(".") < 0) ? calc.getResult() + "." : calc.getResult()) + calc.getSign());
		else
			redraw();
	}
	
	/**
	 * Метод "перерисовки" основного поля дисплея.
	 */
	private void redraw() {
		String s;
		
		s = calc.getOp1();
		if(s.indexOf('.') == -1)
			s += ".";
			
		scr0.setText(s + calc.getSign());
	}
	
}

