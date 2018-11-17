package extraViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	public int xLeft;
	public int xRight;
	public int yFirst;
	public int ySecond;
	
	public static int kontenLeftWP = 100;
	public static int kontenRightWP = 180;
	
	public static int pricesLeftWP = 350;
	public static int pricesRightWP = 450;
	
	public int boundsWidth = 870, boundsHeight = 470;

	

	
	public static int yZKWP = MainView.margin;
	
	public static JTextField txtPreis;
	public static JTextField txtKontonummer;
	public static JCheckBox netto;
	public static JCheckBox brutto;
	public static JTextField emballagenPrice;
	public static JLabel AWET;
	public static JLabel kontoEKAUSG;
	public static JLabel kontoVKAUSG;
	public static JTextField rabattPercent = null;
	
	public static JLabel lblKonto1;
	public static JLabel lblKonto2Fixed;
	public static JLabel lblKonto3;
	public static JLabel lblKonto4;
	public static JComboBox lblKonto2Variable;
	
	public static String finalZahlungskonto;

	public void setUpBasicStuff(String cmd, String Konto1, String[] Konten2, String Konto3, String percent, int[] coordinates, boolean fixed, Controller_AbstractClass myController) {
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
		addPic();
		
		JLabel lblTitle = new JLabel(cmd);
		lblTitle.setFont(new Font("Roboto", Font.BOLD, 22));
		lblTitle.setForeground(new Color(0, 117, 211));
		lblTitle.setBounds(290, 28, 423, 31);
		contentPane.add(lblTitle);
		
		JButton btnFertig = new JButton("Fertig");
		btnFertig.setBackground(MainView.darkBlack);
		btnFertig.setForeground(Color.WHITE);
		btnFertig.setContentAreaFilled(false);
		btnFertig.setOpaque(true);
		btnFertig.setFont(new Font("Roboto", Font.BOLD, 20));
		btnFertig.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		btnFertig.setBounds(667, 356, 142, 48);
		btnFertig.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				setUpRoutine(Konto1, Konten2, Konto3, fixed, percent);
				dispose();
				if(MainView.suggestionsEnabled)
					MainView.checkIfShowSolutions();
			}

				});
		contentPane.add(btnFertig);
		
		setXYWidthHeight(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
		build(Konto1, Konten2, Konto3, fixed);
		
	}
	
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		
	}
	
	
	public void getAL(ActionListener AL){
		this.AL = AL;
	}
	
	
	public void doADispose() {
		dispose();
	}
	
	
	public void makeKonto1(String konto1) {
		lblKonto1 = new JLabel(konto1);
		lblKonto1.setFont(new Font("Roboto", Font.BOLD, 20));
		lblKonto1.setForeground(Color.WHITE);
		lblKonto1.setBounds(xLeft, yFirst, 71, 26);
		contentPane.add(lblKonto1);
	}
	
	public void makeKonto2Fixed(String konto2) {
		lblKonto2Fixed = new JLabel(konto2);
		lblKonto2Fixed.setForeground(Color.WHITE);
		lblKonto2Fixed.setFont(new Font("Roboto", Font.BOLD, 20));
		lblKonto2Fixed.setBounds(xRight, yFirst, 248, 31);
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
		lblKonto2Variable.setFont(new Font("Roboto", Font.BOLD, 20));
		lblKonto2Variable.setBounds(xRight, yFirst, 248, 31);
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
		variableTax.setFont(new Font("Roboto", Font.BOLD, 20));
		variableTax.setBounds(757, 189, 90, 42);
		contentPane.add(variableTax);
	}
	
	
	
	public void makeKonto3(String konto3) {
		lblKonto3 = new JLabel(konto3);
		lblKonto3.setFont(new Font("Roboto", Font.BOLD, 20));
		lblKonto3.setForeground(Color.WHITE);
		lblKonto3.setBounds(xLeft, ySecond, 56, 16);
		contentPane.add(lblKonto3);
	}
	
	public void setXYWidthHeight(int xLeft, int xRight, int yFirst, int ySecond) {
		this.xLeft = xLeft;
		this.xRight = xRight;
		this.yFirst = yFirst;
		this.ySecond = ySecond;
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
		netto.setBounds(611, 267, 71, 25);
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
		brutto.setBounds(696, 268, 113, 25);
		contentPane.add(brutto);
	}
	
	
	
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {
		
	}
	
	public static int getyZK() {
		return yZKWP;
	}
	
	public static void resetyZK() {
		yZKWP = MainView.margin;
	}
	
	public static void swapLeft_RightWP() {
		kontenLeftWP = 180;
		kontenRightWP = 100;
		
		pricesLeftWP = 450;
		pricesRightWP = 350;
	}
	
	public static void resetSwap() {
		kontenLeftWP = 100;
		kontenRightWP = 180;
		
		pricesLeftWP = 350;
		pricesRightWP = 450;
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
			btnFertig.setFont(new Font("Roboto", Font.BOLD, 20));
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
	
}
