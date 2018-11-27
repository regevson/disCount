package Kalkulationen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.MathContext;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import View.MainView;
import extraViews.View_SuperClass;

import javax.swing.JCheckBox;
import javax.swing.JButton;

public class Bezugskalkulation_View extends JFrame {

	private Controller_Kalkulationen myController;
	
	public JTextField txtRechnEinstPreis_Setup;
	public JTextField txtRabattpercent;
	public JTextField txtFakturenspesen_Setup;
	public JTextField txtSkonto_Setup;
	public JTextField txtEigeneBezugsspesen_Setup;
	
	public JCheckBox chckbxProgressiv;
	public JCheckBox chckbxRetrograd;
	
	
	public static JPanel mainPane;
	public JTextField txtRechnungspreis;
	public JTextField txtRabatt;
	public JTextField txtRabattierterPreis;
	public JTextField txtFakturenspesen;
	public JTextField txtZielpreis;
	public JTextField txtSkonto;
	public JTextField txtKassapreis;
	public JTextField txtEigeneBezugsspesen;
	public JTextField txtEinstandspreis;
	
	public int yRight = 152;
	public int xRight = 442;
	
	public static int xLeft = 100;
	public static int yLeft = 157;

	public Bezugskalkulation_View(Controller_Kalkulationen myController) {
		this.myController = myController;
		resetCoordinates();
		
		if(mainPane != null) {
			mainPane.setVisible(true);
			setUpStuff();
		}
		
		else {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 531, 664);
			JPanel contentPane = new JPanel();
			contentPane.setBackground(new Color(51, 51, 51));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblHeading = new JLabel("Bezugsspesenkalkulation");
			lblHeading.setFont(MainView.font_20);
			lblHeading.setForeground(Color.WHITE);
			lblHeading.setBounds(136, 35, 240, 42);
			contentPane.add(lblHeading);
			
			int x = 71;
			int y = 130;
			
			txtRechnEinstPreis_Setup = new JTextField();
			View_SuperClass.txtFieldDesign_THIN(txtRechnEinstPreis_Setup);
			txtRechnEinstPreis_Setup.setText("Rechnungspreis/Einstandspreis");
			txtRechnEinstPreis_Setup.setBounds(x, y, 377, 40);
			contentPane.add(txtRechnEinstPreis_Setup);
			txtRechnEinstPreis_Setup.setColumns(10);
			y = y + 75;
			
			txtRabattpercent = new JTextField();
			View_SuperClass.txtFieldDesign_THIN(txtRabattpercent);
			txtRabattpercent.setText("Rabatt in %");
			txtRabattpercent.setColumns(10);
			txtRabattpercent.setBounds(x, y, 377, 40);
			contentPane.add(txtRabattpercent);
			y = y + 75;
			
			txtFakturenspesen_Setup = new JTextField();
			View_SuperClass.txtFieldDesign_THIN(txtFakturenspesen_Setup);
			txtFakturenspesen_Setup.setText("Fakturenspesen");
			txtFakturenspesen_Setup.setColumns(10);
			txtFakturenspesen_Setup.setBounds(x, y, 377, 40);
			contentPane.add(txtFakturenspesen_Setup);
			y = y + 75;
			
			txtSkonto_Setup = new JTextField();
			View_SuperClass.txtFieldDesign_THIN(txtSkonto_Setup);
			txtSkonto_Setup.setText("Skonto");
			txtSkonto_Setup.setColumns(10);
			txtSkonto_Setup.setBounds(x, y, 377, 40);
			contentPane.add(txtSkonto_Setup);
			y = y + 75;
			
			txtEigeneBezugsspesen_Setup = new JTextField();
			View_SuperClass.txtFieldDesign_THIN(txtEigeneBezugsspesen_Setup);
			txtEigeneBezugsspesen_Setup.setText("eigene Bezugsspesen");
			txtEigeneBezugsspesen_Setup.setColumns(10);
			txtEigeneBezugsspesen_Setup.setBounds(x, y, 377, 40);
			contentPane.add(txtEigeneBezugsspesen_Setup);
			
			chckbxProgressiv = new JCheckBox("progressiv");
			View_SuperClass.checkBoxDesign(chckbxProgressiv);
			chckbxProgressiv.setBounds(x, 490, 113, 25);
			contentPane.add(chckbxProgressiv);
			
			chckbxRetrograd = new JCheckBox("retrograd");
			View_SuperClass.checkBoxDesign(chckbxRetrograd);
			chckbxRetrograd.setBounds(205, 490, 113, 25);
			contentPane.add(chckbxRetrograd);
			
			JButton btnLöse = new JButton("Fertig");
			btnLöse.setBackground(new Color(37, 37, 38));
			btnLöse.setForeground(Color.WHITE);
			btnLöse.setContentAreaFilled(false);
			btnLöse.setOpaque(true);
			btnLöse.setFont(MainView.font_20);
			btnLöse.setBorder(new LineBorder(new Color(0, 117, 211), 2));
			btnLöse.setBounds(346, 544, 142, 48);
			btnLöse.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setUpStuff();
					dispose();
					++(MainView.kalkulationsOpen);
					MainView.llSearchNames.get(MainView.llSearchNames.size() - 2).setText("Kalkulationen" + "   " + MainView.kalkulationsOpen);
				}
	
			});
			contentPane.add(btnLöse);
		}
	}

	
	public void setUpStuff() {
		JPanel mainContentPane = MainView.getConPane();
		JPanel tempPanel = MainView.getPanel();
		tempPanel.setVisible(false);
		
		if(mainPane == null) {
			mainPane = new JPanel();
			mainPane.setBackground(new Color(51, 51, 51));
			mainPane.setFont(MainView.font_20);
			mainPane.setBounds(300, 0, 1000, 1000);
			mainContentPane.add(mainPane);
			mainPane.setLayout(null);
			mainPane.setVisible(true);
		}
		
		JLabel lblBezugsspesen = new JLabel("Bezugsspesenkalkulation");
		lblBezugsspesen.setFont(MainView.font_20);
		lblBezugsspesen.setForeground(Color.WHITE);
		lblBezugsspesen.setBounds(337, 40, 405, 42);
		mainPane.add(lblBezugsspesen);
		
		txtRechnungspreis = new JTextField();
		if(chckbxProgressiv.isSelected())
			View_SuperClass.txtFieldDesign_THIN(txtRechnungspreis);
		else
			View_SuperClass.txtFieldDesign(txtRechnungspreis);
		txtRechnungspreis.setText("Rechnungspreis");
		txtRechnungspreis.setBounds(xRight, yRight, MainView.kalktxtWidth, 40);
		mainPane.add(txtRechnungspreis);
		txtRechnungspreis.setColumns(10);
		yRight = yRight + 70;
		
		txtRabatt = new JTextField();
		View_SuperClass.txtFieldDesign_THIN(txtRabatt);
		txtRabatt.setText("- Rabatt");
		txtRabatt.setColumns(10);
		txtRabatt.setBounds(xRight, yRight, MainView.kalktxtWidth, 40);
		mainPane.add(txtRabatt);
		yRight = yRight + 70;
		
		txtRabattierterPreis = new JTextField();
		View_SuperClass.txtFieldDesign_THIN(txtRabattierterPreis);
		txtRabattierterPreis.setText("rabattierter Preis");
		txtRabattierterPreis.setColumns(10);
		txtRabattierterPreis.setBounds(xRight, yRight, MainView.kalktxtWidth, 40);
		mainPane.add(txtRabattierterPreis);
		yRight = yRight + 70;
		
		JLabel lblRechnungspreis = new JLabel("Rechnungspreis");
		labelDesign(lblRechnungspreis);
		lblRechnungspreis.setText("Rechnungspreis");
		mainPane.add(lblRechnungspreis);
		
		JLabel lblRabatt = new JLabel("- Rabatt");
		labelDesign(lblRabatt);
		mainPane.add(lblRabatt);
		
		JLabel lblRabattierterPreis = new JLabel("rabattierter Preis");
		labelDesign(lblRabattierterPreis);
		mainPane.add(lblRabattierterPreis);
		
		JLabel lblFakturenspesen = new JLabel("+ Fakturenspesen");
		labelDesign(lblFakturenspesen);
		mainPane.add(lblFakturenspesen);
		
		JLabel lblZielpreis = new JLabel("Zielpreis");
		labelDesign(lblZielpreis);
		mainPane.add(lblZielpreis);
		
		JLabel lblSkonto = new JLabel("- Skonto");
		labelDesign(lblSkonto);
		mainPane.add(lblSkonto);
		
		JLabel lblKassapreis = new JLabel("Kassapreis");
		labelDesign(lblKassapreis);
		mainPane.add(lblKassapreis);
		
		JLabel lblEigeneBezugsspesen = new JLabel("+ eigene Bezugsspesen");
		labelDesign(lblEigeneBezugsspesen);
		mainPane.add(lblEigeneBezugsspesen);
		
		JLabel lblEinstandspreis = new JLabel("Einstandspreis");
		labelDesign(lblEinstandspreis);
		lblEinstandspreis.setBounds(xLeft, yLeft+30, 190, 22);
		mainPane.add(lblEinstandspreis);
		
		txtFakturenspesen = new JTextField();
		View_SuperClass.txtFieldDesign_THIN(txtFakturenspesen);
		txtFakturenspesen.setText("Fakturenspesen");
		txtFakturenspesen.setColumns(10);
		txtFakturenspesen.setBounds(xRight, yRight, MainView.kalktxtWidth, 40);
		mainPane.add(txtFakturenspesen);
		yRight = yRight + 70;
		
		txtZielpreis = new JTextField();
		View_SuperClass.txtFieldDesign_THIN(txtZielpreis);
		txtZielpreis.setText("Zielpreis");
		txtZielpreis.setColumns(10);
		txtZielpreis.setBounds(xRight, yRight, MainView.kalktxtWidth, 40);
		mainPane.add(txtZielpreis);
		yRight = yRight + 70;
		
		txtSkonto = new JTextField();
		View_SuperClass.txtFieldDesign_THIN(txtSkonto);
		txtSkonto.setText("Skonto");
		txtSkonto.setColumns(10);
		txtSkonto.setBounds(xRight, yRight, MainView.kalktxtWidth, 40);
		mainPane.add(txtSkonto);
		yRight = yRight + 70;
		
		txtKassapreis = new JTextField();
		View_SuperClass.txtFieldDesign_THIN(txtKassapreis);
		txtKassapreis.setText("Kassapreis");
		txtKassapreis.setColumns(10);
		txtKassapreis.setBounds(xRight, yRight, MainView.kalktxtWidth, 40);
		mainPane.add(txtKassapreis);
		yRight = yRight + 70;
		
		txtEigeneBezugsspesen = new JTextField();
		View_SuperClass.txtFieldDesign_THIN(txtEigeneBezugsspesen);
		txtEigeneBezugsspesen.setText("eigene Bezugsspesen");
		txtEigeneBezugsspesen.setColumns(10);
		txtEigeneBezugsspesen.setBounds(xRight, yRight, MainView.kalktxtWidth, 40);
		mainPane.add(txtEigeneBezugsspesen);
		yRight = yRight + 70;
		
		txtEinstandspreis = new JTextField();
		if(chckbxProgressiv.isSelected())
			View_SuperClass.txtFieldDesign(txtEinstandspreis);
		else
			View_SuperClass.txtFieldDesign_THIN(txtEinstandspreis);
		txtEinstandspreis.setText("Einstandspreis");
		txtEinstandspreis.setColumns(10);
		txtEinstandspreis.setBounds(xRight, yRight+100, MainView.kalktxtWidth, 40);
		mainPane.add(txtEinstandspreis);
		
		
		
		JButton btnLösche = new JButton("Lösche");
		btnLösche.setBackground(new Color(37, 37, 38));
		btnLösche.setForeground(Color.WHITE);
		btnLösche.setContentAreaFilled(false);
		btnLösche.setOpaque(true);
		btnLösche.setFont(MainView.font_20);
		btnLösche.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		btnLösche.setBounds(xRight, 900, 142, 48);
		btnLösche.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				mainContentPane.remove(mainPane);
				tempPanel.setVisible(true);
				mainPane.setVisible(false);
				mainPane = null;
				MainView.llSearchNames.get(MainView.llSearchNames.size() - 2).setText("Kalkulationen" + "   " + --MainView.kalkulationsOpen);
			}

		});
		mainPane.add(btnLösche);
		
		
		
		
		
		if(chckbxProgressiv.isSelected())
			myController.execCalcBezugsspesenKalkulation_Pro(this, txtRechnEinstPreis_Setup.getText(), txtRabattpercent.getText(), txtFakturenspesen_Setup.getText(), txtSkonto_Setup.getText(), txtEigeneBezugsspesen_Setup.getText());
		else
			myController.execCalcBezugsspesenKalkulation_Ret(this, txtRechnEinstPreis_Setup.getText(), txtRabattpercent.getText(), txtFakturenspesen_Setup.getText(), txtSkonto_Setup.getText(), txtEigeneBezugsspesen_Setup.getText());
	}
	
	
	
	public static void labelDesign(JLabel jl) {
		jl.setForeground(Color.WHITE);
		jl.setBounds(xLeft, yLeft, 190, 22);
		jl.setFont(MainView.font_18);
		jl.setForeground(new Color(113, 186, 253));
		
		yLeft = yLeft + 70;
	}
	
	public static void resetCoordinates() {
		xLeft = 100;
		yLeft = 157;
	}
}
