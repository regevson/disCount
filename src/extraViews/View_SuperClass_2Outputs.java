package extraViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Arrays;

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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import View.Controller_AbstractClass;
import View.MainView;


public class View_SuperClass_2Outputs extends JFrame{
	
	protected Controller_AbstractClass myController;
	
	public JPanel contentPane;
	public ActionListener AL;
	
	public int xLeft;
	public int xRight;
	public int yFirst;
	public int ySecond;
	
	public int boundsWidth = 870, boundsHeight = 470;

	
	public static int yZKWP = MainView.margin;
	
	public static JTextField txtPreis;
	public static JTextField txtKontonummer;
	public static JCheckBox netto;
	public static JCheckBox brutto;
	public static JTextField emballagenPrice;
	
	public static JLabel lblKonto1;
	public static JLabel lblKonto2Fixed;
	public static JLabel lblKonto3;
	public static JLabel lblKonto4;
	public static JComboBox lblKonto2Variable;
	
	public static JLabel kontoEKAUSG;
	public static JLabel kontoVKAUSG;
	public static JTextField rabattPercent = null;
	
	public String konto4;
	public String konto5;
	public String percent;
	
	
	public static String finalZahlungskonto;
	

	public void setUpBasicStuff(String cmd, String Konto1, String[] Konto2, String Konto3, String Konto4, String Konto5, String percent, int[] coordinates, boolean fixed, String extra, Controller_AbstractClass myController) {
		
		this.konto4 = Konto4;
		this.konto5 = Konto5;
		this.percent = percent;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, boundsWidth, boundsHeight);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		btnFertig.setFont(MainView.font_20);
		btnFertig.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		btnFertig.setBounds(667, 356, 142, 48);
		btnFertig.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try{
				setUpRoutine(Konto4, Konto5, percent, fixed, extra);
				}catch(Exception ex) {
					dispose();
				}
				dispose();
			}

				});
		contentPane.add(btnFertig);
		
		setXYWidthHeight(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
		build(Konto1, Konto2, Konto3, fixed);
		
	}
	
	public void build(String Konto1, String[] Konto2, String Konto3, boolean fixed) {
		
	}
	
	
	public void getAL(ActionListener AL){
		this.AL = AL;
	}
	
	
	public void doADispose() {
		dispose();
	}
	
	
	public void makeKonto1(String konto1) {
		lblKonto1 = new JLabel(konto1);
		lblKonto1.setFont(MainView.font_20);
		lblKonto1.setForeground(Color.WHITE);
		lblKonto1.setBounds(xLeft, yFirst, 71, 26);
		contentPane.add(lblKonto1);
	}
	
	public void makeKonto2Fixed(String konto2) {
		lblKonto2Fixed = new JLabel(konto2);
		lblKonto2Fixed.setForeground(Color.WHITE);
		lblKonto2Fixed.setFont(MainView.font_20);
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
		lblKonto2Variable.setFont(MainView.font_20);
		lblKonto2Variable.setBounds(xRight, yFirst, 248, 31);
		contentPane.add(lblKonto2Variable);
	}
	
	
	public void makeKonto3(String konto3) {
		lblKonto3 = new JLabel(konto3);
		lblKonto3.setFont(MainView.font_20);
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
		txtPreis.setBackground(new Color(37, 37, 38));
		txtPreis.setForeground(Color.WHITE);
		txtPreis.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		txtPreis.setBorder(BorderFactory.createCompoundBorder(txtPreis.getBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txtPreis.setFont(MainView.font_18);
		txtPreis.setText("Preis");
		txtPreis.setBounds(621, 189, 116, 42);
		contentPane.add(txtPreis);
		txtPreis.setColumns(10);
	}
	
	public void makeButtons() {
		netto = new JCheckBox("netto");
		netto.setFont(MainView.font_16);
		netto.setForeground(Color.WHITE);
		netto.setBackground(new Color(51, 51, 51));
		netto.setBounds(611, 267, 71, 25);
		netto.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	netto.setForeground(new Color(0, 117, 211));
		    }
		});
		contentPane.add(netto);
		
		brutto = new JCheckBox("brutto");
		brutto.setFont(MainView.font_16);
		brutto.setForeground(Color.WHITE);
		brutto.setBackground(new Color(51, 51, 51));
		brutto.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	brutto.setForeground(new Color(0, 117, 211));
		    }
		});
		brutto.setBounds(696, 268, 113, 25);
		contentPane.add(brutto);
	}
	
	
	public void setUpRoutine(String Konto4, String Konto5, String percent, boolean fixed, String extra) {
		
	}
	
	public static int getyZK() {
		return yZKWP;
	}
	
	public static void resetyZK() {
		yZKWP = MainView.margin;
	}
	
	public void changeBounds(int x, int y) {
		boundsWidth = x;
		boundsHeight = y;
	}
	
	public void addPic() {
		ImageIcon dcIcon = new ImageIcon("src/dc_small.png");
		JLabel dcLabel = new JLabel(dcIcon);
		dcLabel.setBounds(8, 430, 31, 28);
		contentPane.add(dcLabel);
	}
	
}
