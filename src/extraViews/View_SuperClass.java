package extraViews;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import View.Controller_AbstractClass;
import View.MainView;

public class View_SuperClass extends JFrame{
	
	protected Controller_AbstractClass myController;
	
	public static JPanel contentPane;
	public static JComboBox comboBox;
	public JComboBox variableTax;
	public ActionListener AL;
	
	
	private final int kontenLeft = 150;
	private final int kontenRight = 220;
	
	protected int marginLeft1 = 0;
	protected int marginLeft2 = 0;
	
	private int boundsWidth = 870, boundsHeight = 470;

	
	
	protected static JTextField txtPreis;
	protected static JTextField txtKontonummer;
	protected static JCheckBox netto;
	protected static JCheckBox brutto;
	protected static JTextField emballagenPrice;
	protected static JLabel AWET;
	protected static JLabel kontoEKAUSG;
	protected static JLabel kontoVKAUSG;
	protected static JTextField rabattPercent = null;
	
	protected static JLabel lblKonto1;
	protected static JLabel lblKonto2Fixed;
	protected static JLabel lblKonto3;
	protected static JLabel lblKonto4;
	protected static JComboBox lblKonto2Variable;
	private JFrame frame;
	
	protected static String finalZahlungskonto;
	
	protected boolean leftMore;
	

	public void setUpBasicStuff(String cmd, String Konto1, String[] Konten2, String Konto3, String percent, boolean fixed, Controller_AbstractClass myController, boolean leftMore) {
		
		frame = this;
		
		if(MainView.openWindow == null) {
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setUndecorated(true);
			setBounds(100, 100, boundsWidth, boundsHeight);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(51, 51, 51));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, MainView.disCountBlue));

			this.myController = myController;
			this.leftMore = leftMore;
			addPic();
			
			JLabel lblTitle = new JLabel(cmd);
			lblTitle.setFont(new Font("Roboto", Font.BOLD, 22));
			lblTitle.setForeground(new Color(0, 117, 211));
			lblTitle.setBounds(0, 28, boundsWidth, 31);
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(lblTitle);
			
			JButton btnFertig = new JButton("Fertig");
			btnFertig.setBackground(MainView.darkBlack);
			btnFertig.setForeground(Color.WHITE);
			btnFertig.setContentAreaFilled(false);
			btnFertig.setOpaque(true);
			btnFertig.setFont(MainView.font_20);
			btnFertig.setBorder(new LineBorder(new Color(0, 117, 211), 2, true));
			btnFertig.setBounds(675, 376, 142, 48);
			btnFertig.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					setUpRoutine(Konto1, Konten2, Konto3, fixed, percent);
					dispose();
					MainView.openWindow = null;
					
				}
	
					});
			contentPane.add(btnFertig);
			
			JLabel escapeLabel = MainView.makeMenuLabels("src/escape.png", "Schlieﬂen", boundsWidth - 43, 11, -1, 23, 23);
			escapeLabel.addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent e) { 
		        	  
		              dispose();
		              MainView.openWindow = null;
		              
		            } 
		          });
			contentPane.add(escapeLabel);
			
			
			setMargins(fixed, Konto3);
			build(Konto1, Konten2, Konto3, fixed);
			
			setVisible(true);
			MainView.openWindow = frame;
			
		}
		
		else
			manageOpenWindow();
		
	}
	
	private void manageOpenWindow() {
		
		MainView.openWindow.toFront();
		MainView.openWindow.repaint();
		vibrate(MainView.openWindow);
		
	}
	
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		
	}
	
	
	public void getAL(ActionListener AL){
		this.AL = AL;
	}
	
	
	public void doADispose() {
		dispose();
	}
	
	
	private void setMargins(boolean fixed, String konto3) {
		
		if(leftMore) {
			
			marginLeft1 = kontenLeft;
			marginLeft2 = kontenRight;
			
			if(!fixed) {
				
				marginLeft1 -= 40;
				marginLeft2 += 40;
				
			}
			
		}
		
		else {
			
			marginLeft1 = kontenRight;
			marginLeft2 = kontenLeft;
			
			if(!fixed) {
				
				marginLeft1 += 80;
				marginLeft2 -= 50;
				
			}
			
		}
		
	}
	
	
	public void makeKonto1(String konto1) {
		lblKonto1 = new JLabel(konto1);
		lblKonto1.setFont(MainView.font_20);
		lblKonto1.setForeground(Color.WHITE);
		lblKonto1.setBounds(marginLeft1, 195, 71, 26);
		contentPane.add(lblKonto1);
	}
	
	public void makeKonto2Fixed(String konto2) {
		lblKonto2Fixed = new JLabel(konto2);
		lblKonto2Fixed.setForeground(Color.WHITE);
		lblKonto2Fixed.setFont(MainView.font_20);
		lblKonto2Fixed.setBounds(marginLeft2, 195, 248, 26);
		contentPane.add(lblKonto2Fixed);
	}
	
	public void makeKonto2Variable(String[] konto2) {
		UIManager.put("ComboBox.background", new Color(37, 37, 38));
		UIManager.put("ComboBox.foreground", Color.WHITE);
		UIManager.put("ComboBox.selectionBackground", new Color(0, 117, 211));
		UIManager.put("ComboBox.selectionForeground", Color.WHITE);
		
		lblKonto2Variable = new JComboBox(konto2);
		lblKonto2Variable.setBorder(new LineBorder(new Color(37, 37, 38), 2));
		lblKonto2Variable.setEditable(true);
		lblKonto2Variable.setFont(MainView.font_20);
		lblKonto2Variable.setBounds(marginLeft2, 195, 148, 31);
		contentPane.add(lblKonto2Variable);
	}
	
	
	
	public void makeVariableTax() {
		UIManager.put("ComboBox.background", new Color(37, 37, 38));
		UIManager.put("ComboBox.foreground", Color.WHITE);
		UIManager.put("ComboBox.selectionBackground", new Color(0, 117, 211));
		UIManager.put("ComboBox.selectionForeground", Color.WHITE);
		String tax[] = {"20%", "10%", "13%"};
		
		variableTax = new JComboBox(tax);
		variableTax.setBorder(new LineBorder(new Color(37, 37, 38), 2));
		variableTax.setEditable(true);
		variableTax.setFont(MainView.font_20);
		variableTax.setBounds(757, 189, 90, 42);
		contentPane.add(variableTax);
	}
	
	
	
	public void makeKonto3(String konto3) {
		lblKonto3 = new JLabel(konto3);
		lblKonto3.setFont(MainView.font_20);
		lblKonto3.setForeground(Color.WHITE);
		lblKonto3.setBounds(marginLeft1, 235, 56, 16);
		contentPane.add(lblKonto3);
	}
	
	
	public void makePrice() {
		txtPreis = new JTextField();
		txtFieldDesign(txtPreis);
		txtPreis.setText("Preis");
		txtPreis.setBounds(621, 189, 116, 42);
		contentPane.add(txtPreis);
		txtPreis.setColumns(10);
		highlightContent(txtPreis);	
	}
	
	public void makeButtons() {
		netto = new JCheckBox("netto");
		checkBoxDesign(netto);
		netto.setBounds(617, 257, 71, 25);
		netto.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	netto.setForeground(new Color(0, 117, 211));
		    }
		});
		contentPane.add(netto);
		
		brutto = new JCheckBox("brutto");
		checkBoxDesign(brutto);
		brutto.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	brutto.setForeground(new Color(0, 117, 211));
		    }
		});
		brutto.setBounds(617, 288, 113, 25);
		contentPane.add(brutto);
	}
	
	
	
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {
		
	}
	
	
	public void changeBounds(int x, int y) {
		boundsWidth = x;
		boundsHeight = y;
	}
	
	
	public static void txtFieldDesign(JTextField tf) {
		tf.setBackground(MainView.darkBlack);
		tf.setCaretColor(Color.WHITE);
		tf.setForeground(Color.WHITE);
		tf.setBorder(new LineBorder(MainView.disCountBlue, 2));
		tf.setBorder(BorderFactory.createCompoundBorder(tf.getBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		tf.setFont(MainView.font_18);
		highlightContent(tf);	
	}
	
	public static void txtFieldDesign_THIN(JTextField tf) {
		tf.setBackground(MainView.darkBlack);
		tf.setCaretColor(Color.WHITE);
		tf.setForeground(Color.WHITE);
		tf.setBorder(new LineBorder(MainView.disCountBlue, 1));
		tf.setBorder(BorderFactory.createCompoundBorder(tf.getBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		tf.setFont(MainView.font_18);
		highlightContent(tf);	
	}
	
	public static void txtFieldDesign_THIN_SMALL(JTextField tf) {
		tf.setBackground(MainView.darkBlack);
		tf.setForeground(Color.WHITE);
		tf.setCaretColor(Color.WHITE);
		tf.setBorder(new LineBorder(MainView.disCountBlue, 1));
		tf.setBorder(BorderFactory.createCompoundBorder(tf.getBorder(), BorderFactory.createEmptyBorder(3, 3, 3, 3)));
		tf.setFont(MainView.font_15);
		highlightContent(tf);	
	}
	
	public static void checkBoxDesign(JCheckBox cb) {
		cb.setFont(MainView.font_16);
		cb.setForeground(Color.WHITE);
		cb.setBackground(new Color(51, 51, 51));
	}
	
	
	
	
	
	
	
	
	
	
	public void setUpBasicStuff(String aufgabe, int width, int height) {
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, width, height);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(51, 51, 51));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			
			JLabel lblTitle = new JLabel("sdsdsd");
			lblTitle.setFont(new Font("Roboto", Font.BOLD, 22));
			lblTitle.setForeground(new Color(0, 117, 211));
			lblTitle.setBounds(290, 28, 423, 31);
			contentPane.add(lblTitle);
			
			JButton btnFertig = new JButton("Fertig");
			btnFertig.setBackground(MainView.darkBlack);
			btnFertig.setForeground(Color.WHITE);
			btnFertig.setContentAreaFilled(false);
			btnFertig.setOpaque(true);
			btnFertig.setFont(MainView.font_20);
			btnFertig.setBorder(new LineBorder(new Color(0, 117, 211), 2));
			btnFertig.setBounds(width/2-(142/2), height-200, 142, 48);
			btnFertig.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					printStuff();
				}
	
					});
			contentPane.add(btnFertig);
			
			readAufgabe(aufgabe);
			
			setVisible(true);
			
			
		}
	
	
	public void readAufgabe(String aufgabe) {
		
	}
	
	public void printStuff() {
		
	}
	
	public void addPic() {
		ImageIcon dcIcon = new ImageIcon("src/dc_small.png");
		JLabel dcLabel = new JLabel(dcIcon);
		dcLabel.setBounds(8, 430, 31, 28);
		contentPane.add(dcLabel);
	}
	
	
	
	public static void highlightContent(JTextField txtField) {
		txtField.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				txtField.requestFocusInWindow();
				txtField.selectAll();
			}
			
			@Override public void mouseEntered(java.awt.event.MouseEvent e) {} @Override public void mouseExited(java.awt.event.MouseEvent e) 
			{}@Override public void mousePressed(java.awt.event.MouseEvent e) {}@Override public void mouseReleased(java.awt.event.MouseEvent e) {}

		});
	}
	
	
	
	
	
	public static void vibrate(Frame frame) {
		
		try {
			
			frame.setVisible(true);
			
			final int originalX = frame.getLocationOnScreen().x;
			final int originalY = frame.getLocationOnScreen().y;
			
			for (int i = 0; i < 10; i++) {
				
				Thread.sleep(10);
				frame.setLocation(originalX, originalY + 5);
				Thread.sleep(10);
				frame.setLocation(originalX, originalY - 5);
				Thread.sleep(10);
				frame.setLocation(originalX + 5, originalY);
				Thread.sleep(10);
				frame.setLocation(originalX, originalY);
				
			}
			
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
}
