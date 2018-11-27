package Kalkulationen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import View.MainView;
import extraViews.View_SuperClass;

public class Absatzkalkulation_View extends JFrame{
	
	private Controller_Kalkulationen myController;

	public JTextField txtSelbstkosten_Setup;
	public JTextField txtGewinn_Setup;
	public JTextField txtNomSK_Setup;
	public JTextField txtVerkProvision_Setup;
	public JTextField txtVerkaufsprovision_Setup;
	public JTextField txtSkonto_Setup;
	public JTextField txtMengenRabatt_Setup;
	public JTextField txtSonderRabatt_Setup;
	public JTextField txtGroßhandelsRabatt_Setup;
	public JTextField txtEinzelhandelsRabatt_Setup;
	
	public JCheckBox chckbxProgressiv;
	public JCheckBox chckbxRetrograd;
	
	
	public static JPanel mainPane;
	public JTextField txtSelbstkosten;
	public JTextField txtGewinn;
	public JTextField txtNettoverkaufspreis;
	public JTextField txtNomSK;
	public JTextField txtZwischensumme;
	public JTextField txtVerkaufsprovision;
	public JTextField txtKassapreis;
	public JTextField txtSkonto;
	public JTextField txtZielpreis;
	public JTextField txtMengenRabatt;
	public JTextField txtSonderRabatt;
	public JTextField txtGroßhandelsRabatt;
	public JTextField txtEinzelhandelsRabatt;
	public JTextField txtBruttoVPexkl;
	public JTextField txtUSt;
	public JTextField txtBruttoVP;
	
	public int yRight = 125;
	public int xRight = 442;
	
	private static int xLeft = 100;
	private static int yLeft = 125;

	public Absatzkalkulation_View(Controller_Kalkulationen myController) {
		this.myController = myController;
		resetCoordinates();
		
		if(mainPane != null) {
			mainPane.setVisible(true);
			setUpStuff();
		}
		
		else {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 531, 985);
			JPanel contentPane = new JPanel();
			contentPane.setBackground(new Color(51, 51, 51));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblHeading = new JLabel("Absatzkalkulation");
			lblHeading.setFont(MainView.font_30);
			lblHeading.setForeground(Color.WHITE);
			lblHeading.setBounds(136, 35, 280, 42);
			contentPane.add(lblHeading);
			
			int x = 71;
			int y = 130;
			
			txtSelbstkosten_Setup = new JTextField();
			View_SuperClass.txtFieldDesign_THIN(txtSelbstkosten_Setup);
			txtSelbstkosten_Setup.setText("Selbstkosten/Bruttoverkaufspreis inkl. USt");
			txtSelbstkosten_Setup.setBounds(x, y, 377, 40);
			contentPane.add(txtSelbstkosten_Setup);
			txtSelbstkosten_Setup.setColumns(10);
			y = y + 75;
			
			txtGewinn_Setup = new JTextField();
			View_SuperClass.txtFieldDesign_THIN(txtGewinn_Setup);
			txtGewinn_Setup.setText("Gewinn in %");
			txtGewinn_Setup.setColumns(10);
			txtGewinn_Setup.setBounds(x, y, 377, 40);
			contentPane.add(txtGewinn_Setup);
			y = y + 75;
			
			txtNomSK_Setup = new JTextField();
			View_SuperClass.txtFieldDesign_THIN(txtNomSK_Setup);
			txtNomSK_Setup.setText("nominelle Sonderkosten");
			txtNomSK_Setup.setColumns(10);
			txtNomSK_Setup.setBounds(x, y, 377, 40);
			contentPane.add(txtNomSK_Setup);
			y = y + 75;
			
			txtVerkProvision_Setup = new JTextField();
			View_SuperClass.txtFieldDesign_THIN(txtVerkProvision_Setup);
			txtVerkProvision_Setup.setText("Verkaufsprovision");
			txtVerkProvision_Setup.setColumns(10);
			txtVerkProvision_Setup.setBounds(x, y, 377, 40);
			contentPane.add(txtVerkProvision_Setup);
			y = y + 75;
			
			
			txtSkonto_Setup = new JTextField();
			View_SuperClass.txtFieldDesign_THIN(txtSkonto_Setup);
			txtSkonto_Setup.setText("Skonto in %");
			txtSkonto_Setup.setColumns(10);
			txtSkonto_Setup.setBounds(x, y, 377, 40);
			contentPane.add(txtSkonto_Setup);
			y = y + 75;
			
			
			txtMengenRabatt_Setup = new JTextField();
			View_SuperClass.txtFieldDesign_THIN(txtMengenRabatt_Setup);
			txtMengenRabatt_Setup.setText("Mengenrabatt in %");
			txtMengenRabatt_Setup.setColumns(10);
			txtMengenRabatt_Setup.setBounds(x, y, 377, 40);
			contentPane.add(txtMengenRabatt_Setup);
			y = y + 75;
			
			
			
			txtSonderRabatt_Setup = new JTextField();
			View_SuperClass.txtFieldDesign_THIN(txtSonderRabatt_Setup);
			txtSonderRabatt_Setup.setText("Sonderrabatt in %");
			txtSonderRabatt_Setup.setColumns(10);
			txtSonderRabatt_Setup.setBounds(x, y, 377, 40);
			contentPane.add(txtSonderRabatt_Setup);
			y = y + 75;
			
			
			
			txtGroßhandelsRabatt_Setup = new JTextField();
			View_SuperClass.txtFieldDesign_THIN(txtGroßhandelsRabatt_Setup);
			txtGroßhandelsRabatt_Setup.setText("Großhandelsrabatt in %");
			txtGroßhandelsRabatt_Setup.setColumns(10);
			txtGroßhandelsRabatt_Setup.setBounds(x, y, 377, 40);
			contentPane.add(txtGroßhandelsRabatt_Setup);
			y = y + 75;
			
			txtEinzelhandelsRabatt_Setup = new JTextField();
			View_SuperClass.txtFieldDesign_THIN(txtEinzelhandelsRabatt_Setup);
			txtEinzelhandelsRabatt_Setup.setText("Einzelhandelsrabatt in %");
			txtEinzelhandelsRabatt_Setup.setColumns(10);
			txtEinzelhandelsRabatt_Setup.setBounds(x, y, 377, 40);
			contentPane.add(txtEinzelhandelsRabatt_Setup);
			y = y + 75;
			
			
			chckbxProgressiv = new JCheckBox("progressiv");
			View_SuperClass.checkBoxDesign(chckbxProgressiv);
			chckbxProgressiv.setBounds(x, y, 113, 25);
			contentPane.add(chckbxProgressiv);
			
			chckbxRetrograd = new JCheckBox("retrograd");
			View_SuperClass.checkBoxDesign(chckbxRetrograd);
			chckbxRetrograd.setBounds(205, y, 113, 25);
			contentPane.add(chckbxRetrograd);
			
			JButton btnLöse = new JButton("Fertig");
			btnLöse.setBackground(new Color(37, 37, 38));
			btnLöse.setForeground(Color.WHITE);
			btnLöse.setContentAreaFilled(false);
			btnLöse.setOpaque(true);
			btnLöse.setFont(MainView.font_20);
			btnLöse.setBorder(new LineBorder(new Color(0, 117, 211), 2));
			btnLöse.setBounds(346, y + 50, 142, 48);
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
		
		
		//setUpStuff();

	
	public void setUpStuff() {
		JPanel mainContentPane = MainView.getConPane();
		JPanel tempPanel = MainView.getPanel();
		tempPanel.setVisible(false);
		
		if(mainPane == null) {
			mainPane = new JPanel();
			mainPane.setBackground(new Color(51, 51, 51));
			mainPane.setFont(MainView.font_30);
			mainPane.setBounds(300, 0, 1000, 1000);
			mainContentPane.add(mainPane);
			mainPane.setLayout(null);
			mainPane.setVisible(true);
		}
		
		JLabel lblBezugsspesen = new JLabel("Absatzkalkulation");
		lblBezugsspesen.setFont(MainView.font_30);
		lblBezugsspesen.setForeground(Color.WHITE);
		lblBezugsspesen.setBounds(337, 30, 345, 42);
		mainPane.add(lblBezugsspesen);
		
		txtSelbstkosten = new JTextField();
		if(chckbxRetrograd.isSelected()) {
			View_SuperClass.txtFieldDesign(txtSelbstkosten);
			txtSelbstkosten.setBounds(xRight, yRight-10, MainView.kalktxtWidth, 40);
		}
		else {
			View_SuperClass.txtFieldDesign_THIN_SMALL(txtSelbstkosten);
			txtSelbstkosten.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		}
		
		mainPane.add(txtSelbstkosten);
		txtSelbstkosten.setColumns(10);
		yRight = yRight + 50;
		
		txtGewinn = new JTextField();
		View_SuperClass.txtFieldDesign_THIN_SMALL(txtGewinn);
		txtGewinn.setColumns(10);
		txtGewinn.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		mainPane.add(txtGewinn);
		yRight = yRight + 50;
		
		txtNettoverkaufspreis = new JTextField();
		View_SuperClass.txtFieldDesign_THIN_SMALL(txtNettoverkaufspreis);
		txtNettoverkaufspreis.setColumns(10);
		txtNettoverkaufspreis.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		mainPane.add(txtNettoverkaufspreis);
		yRight = yRight + 50;
		
		JLabel lblSK = new JLabel("Selbstkosten");
		labelDesign(lblSK);
		mainPane.add(lblSK);
		
		JLabel lblGewinn = new JLabel("+ Gewinn");
		labelDesign(lblGewinn);
		mainPane.add(lblGewinn);
		
		JLabel lblNVP = new JLabel("Nettoverkaufspreis");
		labelDesign(lblNVP);
		mainPane.add(lblNVP);
		
		JLabel lblNSK = new JLabel("+ nominelle Sonderkosten");
		labelDesign(lblNSK);
		mainPane.add(lblNSK);
		
		JLabel lblZWS = new JLabel("Zwischensumme");
		labelDesign(lblZWS);
		mainPane.add(lblZWS);
		
		JLabel lblVP = new JLabel("+ Verkaufsprovision");
		labelDesign(lblVP);
		mainPane.add(lblVP);
		
		JLabel lblKassapreis = new JLabel("Kassapreis");
		labelDesign(lblKassapreis);
		mainPane.add(lblKassapreis);
		
		JLabel lblSkonto = new JLabel("+ Skonto");
		labelDesign(lblSkonto);
		mainPane.add(lblSkonto);
		
		JLabel lblZielpreis = new JLabel("Zielpreis");
		labelDesign(lblZielpreis);
		mainPane.add(lblZielpreis);
		
		JLabel lblMengenRabatt = new JLabel("+ Mengenrabatt");
		labelDesign(lblMengenRabatt);
		mainPane.add(lblMengenRabatt);
		
		JLabel lblSonderRabatt = new JLabel("+ Sonderrabatt");
		labelDesign(lblSonderRabatt);
		mainPane.add(lblSonderRabatt);
		
		JLabel lblGroßhandelsRabatt = new JLabel("+ Großhandelsrabatt");
		labelDesign(lblGroßhandelsRabatt);
		mainPane.add(lblGroßhandelsRabatt);
		
		JLabel lblEinzelhandelsRabatt = new JLabel("+ Einzelhandelsrabatt");
		labelDesign(lblEinzelhandelsRabatt);
		mainPane.add(lblEinzelhandelsRabatt);
		
		JLabel lblBVPexkl = new JLabel("Bruttoverkaufspreis exkl. USt");
		labelDesign(lblBVPexkl);
		lblBVPexkl.setBounds(xLeft, yLeft-50, 220, 22);
		mainPane.add(lblBVPexkl);
		
		JLabel lblUSt = new JLabel("USt");
		labelDesign(lblUSt);
		mainPane.add(lblUSt);
		
		
		JLabel lblBVPinkl = new JLabel("Bruttoverkaufspreis inkl. USt");
		labelDesign(lblBVPinkl);
		lblBVPinkl.setBounds(xLeft, yLeft-40, 220, 22);
		mainPane.add(lblBVPinkl);
		
		txtNomSK = new JTextField();
		View_SuperClass.txtFieldDesign_THIN_SMALL(txtNomSK);
		txtNomSK.setColumns(10);
		txtNomSK.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		mainPane.add(txtNomSK);
		yRight = yRight + 50;
		
		txtZwischensumme = new JTextField();
		View_SuperClass.txtFieldDesign_THIN_SMALL(txtZwischensumme);
		txtZwischensumme.setColumns(10);
		txtZwischensumme.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		mainPane.add(txtZwischensumme);
		yRight = yRight + 50;
		
		txtVerkaufsprovision = new JTextField();
		View_SuperClass.txtFieldDesign_THIN_SMALL(txtVerkaufsprovision);
		txtVerkaufsprovision.setColumns(10);
		txtVerkaufsprovision.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		mainPane.add(txtVerkaufsprovision);
		yRight = yRight + 50;
		
		txtKassapreis = new JTextField();
		View_SuperClass.txtFieldDesign_THIN_SMALL(txtKassapreis);
		txtKassapreis.setColumns(10);
		txtKassapreis.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		mainPane.add(txtKassapreis);
		yRight = yRight + 50;
		
		txtSkonto = new JTextField();
		View_SuperClass.txtFieldDesign_THIN_SMALL(txtSkonto);
		txtSkonto.setColumns(10);
		txtSkonto.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		mainPane.add(txtSkonto);
		yRight = yRight + 50;
		
		
		txtZielpreis = new JTextField();
		View_SuperClass.txtFieldDesign_THIN_SMALL(txtZielpreis);
		txtZielpreis.setColumns(10);
		txtZielpreis.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		mainPane.add(txtZielpreis);
		yRight = yRight + 50;
		
		
		
		txtMengenRabatt = new JTextField();
		View_SuperClass.txtFieldDesign_THIN_SMALL(txtMengenRabatt);
		txtMengenRabatt.setColumns(10);
		txtMengenRabatt.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		mainPane.add(txtMengenRabatt);
		yRight = yRight + 50;
		
		
		txtSonderRabatt = new JTextField();
		View_SuperClass.txtFieldDesign_THIN_SMALL(txtSonderRabatt);
		txtSonderRabatt.setColumns(10);
		txtSonderRabatt.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		mainPane.add(txtSonderRabatt);
		yRight = yRight + 50;
		
		
		txtGroßhandelsRabatt = new JTextField();
		View_SuperClass.txtFieldDesign_THIN_SMALL(txtGroßhandelsRabatt);
		txtGroßhandelsRabatt.setColumns(10);
		txtGroßhandelsRabatt.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		mainPane.add(txtGroßhandelsRabatt);
		yRight = yRight + 50;
		
		txtEinzelhandelsRabatt = new JTextField();
		View_SuperClass.txtFieldDesign_THIN_SMALL(txtEinzelhandelsRabatt);
		txtEinzelhandelsRabatt.setColumns(10);
		txtEinzelhandelsRabatt.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		mainPane.add(txtEinzelhandelsRabatt);
		yRight = yRight + 50;
		
		
		txtBruttoVPexkl = new JTextField();
		View_SuperClass.txtFieldDesign_THIN_SMALL(txtBruttoVPexkl);
		txtBruttoVPexkl.setColumns(10);
		txtBruttoVPexkl.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		mainPane.add(txtBruttoVPexkl);
		yRight = yRight + 50;
		
		
		
		txtUSt = new JTextField();
		View_SuperClass.txtFieldDesign_THIN_SMALL(txtUSt);
		txtUSt.setColumns(10);
		txtUSt.setBounds(xRight, yRight, MainView.kalktxtWidth, 30);
		mainPane.add(txtUSt);
		yRight = yRight + 50;
		
		
		
		txtBruttoVP = new JTextField();
		if(chckbxProgressiv.isSelected()) {
			View_SuperClass.txtFieldDesign(txtBruttoVP);
			txtBruttoVP.setBounds(xRight, yRight+10, MainView.kalktxtWidth, 40);
		}
		else {
			View_SuperClass.txtFieldDesign_THIN_SMALL(txtBruttoVP);
			txtBruttoVP.setBounds(xRight, yRight+10, MainView.kalktxtWidth, 30);
		}
		
		txtBruttoVP.setColumns(10);
		mainPane.add(txtBruttoVP);
		
		
		
		JButton btnLösche = new JButton("Lösche");
		btnLösche.setBackground(new Color(37, 37, 38));
		btnLösche.setForeground(Color.WHITE);
		btnLösche.setContentAreaFilled(false);
		btnLösche.setOpaque(true);
		btnLösche.setFont(MainView.font_18);
		btnLösche.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		btnLösche.setBounds(xRight, 940, 142, 38);
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
			myController.execCalcAbsatzkalkulation_Pro(this, txtSelbstkosten_Setup.getText(), txtGewinn_Setup.getText(), txtNomSK_Setup.getText(), txtVerkProvision_Setup.getText(), txtSkonto_Setup.getText(), txtMengenRabatt_Setup.getText(), txtSonderRabatt_Setup.getText(), txtGroßhandelsRabatt_Setup.getText(), txtEinzelhandelsRabatt_Setup.getText());
		else
			myController.execCalcAbsatzkalkulation_Ret(this, txtSelbstkosten_Setup.getText(), txtGewinn_Setup.getText(), txtNomSK_Setup.getText(), txtVerkProvision_Setup.getText(), txtSkonto_Setup.getText(), txtMengenRabatt_Setup.getText(), txtSonderRabatt_Setup.getText(), txtGroßhandelsRabatt_Setup.getText(), txtEinzelhandelsRabatt_Setup.getText());
	}
	
	
	
	public static void labelDesign(JLabel jl) {
		jl.setForeground(Color.WHITE);
		jl.setBounds(xLeft, yLeft, 190, 22);
		jl.setFont(MainView.font_16);
		jl.setForeground(new Color(113, 186, 253));
		
		yLeft = yLeft + 50;
	}
	
	public static void resetCoordinates() {
		xLeft = 100;
		yLeft = 125;
	}
	
	
	
}
