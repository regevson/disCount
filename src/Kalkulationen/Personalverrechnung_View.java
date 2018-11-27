package Kalkulationen;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import View.MainModel;
import View.MainView;
import extraViews.View_SuperClass;

public class Personalverrechnung_View extends JFrame {
	
	Controller_Kalkulationen myController;
	
	
	public static Double txtCurrentSV1;
	public static Double txtCurrentSV2;
	public static Double txtCurrentSV3;
	public static Double txtCurrentSV4;
	
	public static Double txtmind20KPP;
	public static Double txtmehrals40KPP;
	public static Double textField_mehrals60KPP;
	public static Double txtmind20GPP;
	public static Double txtmehrals40GPP;
	public static Double textField_mehrals60GPP;
	
	public static Double txtGeringfügigkeitsgrenze;
	public static Double txtHöchstbeitragsgrundlage;
	
	
	
	public JTextField txtGehalt_Lohn;
	public JTextField txtFahrtstrecke;
	public JTextField txtFreibetrag;
	public JTextField txtECardGebühr;
	public JTextField txtGewerkschaftsbeitrag;
	
	
	public static JPanel mainPane;
	
	public int yRight = 152;
	public int xRight = 442;
	
	public int xLeft = 100;
	public int yLeft = 157;
	private JTextField txtAkonto;
	private JTextField txtLohn_Gehalt;
	private JTextField txtSZV;
	private JTextField txtBG;
	private JTextField txtLS;
	private JTextField txtNewGehalt_Lohn;
	
	private static JTextField txtGrenzss;
	private static JTextField txtAvabaeab;
	private static JTextField txtBemessungsgrundlage;
	
	private static String Lohn_Gehalt;
	private static Double LS;
	private static Double SV;
	private static Double BG;
	private static Double newGehalt;
	
	

public Personalverrechnung_View(Controller_Kalkulationen myController) {
	this.myController = myController;
	Bezugskalkulation_View.resetCoordinates();	
	
	if(mainPane != null) {
		mainPane.setVisible(true);
		paintSolutions(Lohn_Gehalt, LS, SV, BG, newGehalt);
	}
	
	else {
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 531, 748);
	JPanel contentPane = new JPanel();
	contentPane.setBackground(new Color(51, 51, 51));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel lblHeading = new JLabel("Lohnverrechnung");
	lblHeading.setFont(MainView.font_20);
	lblHeading.setForeground(Color.WHITE);
	lblHeading.setBounds(174, 35, 165, 42);
	contentPane.add(lblHeading);
	
	int x = 71;
	int y = 130;
	
	txtGehalt_Lohn = new JTextField();
	View_SuperClass.txtFieldDesign_THIN(txtGehalt_Lohn);
	txtGehalt_Lohn.setText("Gehalt/Lohn");
	txtGehalt_Lohn.setBounds(x, y, 377, 40);
	contentPane.add(txtGehalt_Lohn);
	txtGehalt_Lohn.setColumns(10);
	y = y + 75;
	
	txtFahrtstrecke = new JTextField();
	View_SuperClass.txtFieldDesign_THIN(txtFahrtstrecke);
	txtFahrtstrecke.setText("km einfache Fahrtstrecke");
	txtFahrtstrecke.setColumns(10);
	txtFahrtstrecke.setBounds(x, y, 377, 40);
	contentPane.add(txtFahrtstrecke);
	y = y + 75;
	
	txtFreibetrag = new JTextField();
	View_SuperClass.txtFieldDesign_THIN(txtFreibetrag);
	txtFreibetrag.setText("Freibetrag");
	txtFreibetrag.setColumns(10);
	txtFreibetrag.setBounds(71, 320, 377, 40);
	contentPane.add(txtFreibetrag);
	y = y + 75;
	
	txtECardGebühr = new JTextField();
	View_SuperClass.txtFieldDesign_THIN(txtECardGebühr);
	txtECardGebühr.setText("E-Card-Geb\u00FChr");
	txtECardGebühr.setColumns(10);
	txtECardGebühr.setBounds(71, 395, 377, 40);
	contentPane.add(txtECardGebühr);
	y = y + 75;
	
	txtGewerkschaftsbeitrag = new JTextField();
	View_SuperClass.txtFieldDesign_THIN(txtGewerkschaftsbeitrag);
	txtGewerkschaftsbeitrag.setText("Gewerkschaftsbeitrag");
	txtGewerkschaftsbeitrag.setColumns(10);
	txtGewerkschaftsbeitrag.setBounds(71, 470, 377, 40);
	contentPane.add(txtGewerkschaftsbeitrag);
	

	
	JCheckBox cb_zumutbar = new JCheckBox("zumutbar");
	View_SuperClass.checkBoxDesign(cb_zumutbar);
	cb_zumutbar.setBounds(71, 265, 113, 25);
	contentPane.add(cb_zumutbar);
	
	JCheckBox cb_unzumutbar = new JCheckBox("unzumutbar");
	View_SuperClass.checkBoxDesign(cb_unzumutbar);
	cb_unzumutbar.setBounds(205, 265, 133, 25);
	contentPane.add(cb_unzumutbar);
	
	txtAkonto = new JTextField();
	View_SuperClass.txtFieldDesign_THIN(txtAkonto);
	txtAkonto.setText("Akonto");
	txtAkonto.setColumns(10);
	txtAkonto.setBounds(71, 540, 377, 40);
	contentPane.add(txtAkonto);
	
	
	JButton btnLöse = new JButton("Fertig");
	btnLöse.setBackground(new Color(37, 37, 38));
	btnLöse.setForeground(Color.WHITE);
	btnLöse.setContentAreaFilled(false);
	btnLöse.setOpaque(true);
	btnLöse.setFont(MainView.font_20);
	btnLöse.setBorder(new LineBorder(new Color(0, 117, 211), 2));
	btnLöse.setBounds(346, 630, 142, 48);
	btnLöse.addActionListener(new ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					setUpStuff(cb_zumutbar.isSelected(), cb_unzumutbar.isSelected());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			dispose();
			++(MainView.kalkulationsOpen);
			MainView.llSearchNames.get(MainView.llSearchNames.size() - 2).setText("Kalkulationen" + "   " + MainView.kalkulationsOpen);
		}

	});
	contentPane.add(btnLöse);
	
	
	}
}

public void setUpStuff(boolean zumutbar, boolean unzumutbar) throws NumberFormatException, IOException {
	
	BufferedReader br = new BufferedReader(new FileReader("src/settings.txt"));
	
	
	 txtCurrentSV1 = Double.parseDouble(br.readLine());
	 txtCurrentSV2 = Double.parseDouble(br.readLine());
	 txtCurrentSV3 = Double.parseDouble(br.readLine());
	 txtCurrentSV4 = Double.parseDouble(br.readLine());
	
	 txtmind20KPP = Double.parseDouble(br.readLine());
	 txtmehrals40KPP = Double.parseDouble(br.readLine());
	 textField_mehrals60KPP = Double.parseDouble(br.readLine());
	 txtmind20GPP = Double.parseDouble(br.readLine());
	 txtmehrals40GPP = Double.parseDouble(br.readLine());
	 textField_mehrals60GPP = Double.parseDouble(br.readLine());
	 
	 txtGeringfügigkeitsgrenze = Double.parseDouble(br.readLine());
	 txtHöchstbeitragsgrundlage = Double.parseDouble(br.readLine());
	 
	 br.close();
	 
	
	Double gehalt_lohn = Double.parseDouble(txtGehalt_Lohn.getText());
	Double fahrtstrecke = Double.parseDouble(txtFahrtstrecke.getText());
	Double freibetrag = Double.parseDouble(txtFreibetrag.getText());
	Double eCardGebühr = Double.parseDouble(txtECardGebühr.getText());
	Double gewerkschaftsbeitrag = Double.parseDouble(txtGewerkschaftsbeitrag.getText());
	Double akonto = Double.parseDouble(txtAkonto.getText());
	
	Double BG = myController.execCalcSV_LS(gehalt_lohn, fahrtstrecke, freibetrag, eCardGebühr, gewerkschaftsbeitrag, akonto, zumutbar);
	openBemessungsgrundlage_Input(BG, txtGehalt_Lohn.getText());
	
	
	
}


	private void openBemessungsgrundlage_Input(Double BG, String gehalt_lohn) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setBounds(400, 600, 661, 382);
		JPanel contentPane2 = new JPanel();
		contentPane2.setBackground(new Color(51, 51, 51));
		jf.setContentPane(contentPane2);
		contentPane2.setLayout(null);
		jf.setVisible(true);
		
		JLabel lblBG = new JLabel("Grenzsteuersatz und AVAB/AEAB");
		lblBG.setForeground(Color.WHITE);
		lblBG.setFont(MainView.font_16);
		lblBG.setBounds(271, 26, 300, 16);
		contentPane2.add(lblBG);
		
		
		JLabel lblGrenzsteuersatzUndAvabaeab = new JLabel("Bemessungsgrundlage");
		lblGrenzsteuersatzUndAvabaeab.setForeground(Color.WHITE);
		lblGrenzsteuersatzUndAvabaeab.setFont(MainView.font_16);
		lblGrenzsteuersatzUndAvabaeab.setBounds(72, 90, 300, 16);
		contentPane2.add(lblGrenzsteuersatzUndAvabaeab);
		
		JLabel lblGrenzsteuersatz = new JLabel("Grenzsteuersatz");
		lblGrenzsteuersatz.setForeground(Color.WHITE);
		lblGrenzsteuersatz.setFont(MainView.font_16);
		lblGrenzsteuersatz.setBounds(72, 166, 300, 16);
		contentPane2.add(lblGrenzsteuersatz);
		
		JLabel lblAvabaeab = new JLabel("AVAB/AEAB");
		lblAvabaeab.setForeground(Color.WHITE);
		lblAvabaeab.setFont(MainView.font_16);
		lblAvabaeab.setBounds(72, 216, 300, 16);
		contentPane2.add(lblAvabaeab);
		
		txtGrenzss = new JTextField();
		View_SuperClass.txtFieldDesign_THIN(txtGrenzss);
		txtGrenzss.setText("Grenzsteuersatz");
		txtGrenzss.setBounds(330, 163, 160, 40);
		contentPane2.add(txtGrenzss);
		txtGrenzss.setColumns(10);
		
		txtAvabaeab = new JTextField();
		View_SuperClass.txtFieldDesign_THIN(txtAvabaeab);
		txtAvabaeab.setText("AVAB/AEAB");
		txtAvabaeab.setColumns(10);
		txtAvabaeab.setBounds(330, 213, 160, 40);
		contentPane2.add(txtAvabaeab);
		
		txtBemessungsgrundlage = new JTextField();
		txtBemessungsgrundlage.setText(MainModel.round(BG));
		View_SuperClass.txtFieldDesign(txtBemessungsgrundlage);
		txtBemessungsgrundlage.setColumns(10);
		txtBemessungsgrundlage.setBounds(330, 87, 160, 40);
		contentPane2.add(txtBemessungsgrundlage);
		
		JButton btnFertig = new JButton("Fertig");
		btnFertig.setBackground(new Color(37, 37, 38));
		btnFertig.setForeground(Color.WHITE);
		btnFertig.setContentAreaFilled(false);
		btnFertig.setOpaque(true);
		btnFertig.setFont(MainView.font_20);
		btnFertig.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		btnFertig.setBounds(xRight, 270, 142, 48);
		btnFertig.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				jf.setVisible(false);
				Double LS = myController.execCalcLS(BG, txtGrenzss.getText(), txtAvabaeab.getText());
				Double newGehalt =  myController.execCalcNewGehalt(LS, gehalt_lohn);
				paintSolutions(gehalt_lohn, LS, myController.execGetSV(), BG, newGehalt);
			}

		});
		contentPane2.add(btnFertig);
	}
	
	private void paintSolutions(String Lohn_Gehalt_p, Double LS_p, Double SV_p, Double BG_p, Double newGehalt_p) {
		this.Lohn_Gehalt = Lohn_Gehalt_p;
		this.LS = LS_p;
		this.SV = SV_p;
		this.BG = BG_p;
		this.newGehalt = newGehalt_p;
		
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
		
		JLabel lblBezugsspesen = new JLabel("Personalverrechnung");
		lblBezugsspesen.setFont(MainView.font_20);
		lblBezugsspesen.setForeground(Color.WHITE);
		lblBezugsspesen.setBounds(337, 40, 405, 42);
		mainPane.add(lblBezugsspesen);
		
		txtLohn_Gehalt = new JTextField();
		txtLohn_Gehalt.setText(Lohn_Gehalt);
		View_SuperClass.txtFieldDesign_THIN(txtLohn_Gehalt);
		txtLohn_Gehalt.setBounds(xRight, yRight, MainView.kalktxtWidth, 40);
		mainPane.add(txtLohn_Gehalt);
		txtLohn_Gehalt.setColumns(10);
		yRight = yRight + 70;
		
		txtSZV = new JTextField();
		txtSZV.setText(MainModel.roundDouble_giveBack_String(SV));
		View_SuperClass.txtFieldDesign_THIN(txtSZV);
		txtSZV.setColumns(10);
		txtSZV.setBounds(xRight, yRight, MainView.kalktxtWidth, 40);
		mainPane.add(txtSZV);
		yRight = yRight + 70;
		
		txtBG = new JTextField();
		txtBG.setText(Double.toString(BG));
		View_SuperClass.txtFieldDesign_THIN(txtBG);
		txtBG.setColumns(10);
		txtBG.setBounds(xRight, yRight, MainView.kalktxtWidth, 40);
		mainPane.add(txtBG);
		yRight = yRight + 70;
		
		JLabel lblGehalt_Lohn = new JLabel("Gehalt/Lohn");
		Bezugskalkulation_View.labelDesign(lblGehalt_Lohn);
		mainPane.add(lblGehalt_Lohn);
		
		JLabel lblRabatt = new JLabel("Sozialversicherung");
		Bezugskalkulation_View.labelDesign(lblRabatt);
		mainPane.add(lblRabatt);
		
		JLabel lblSV = new JLabel("Bemessungsgrundlage");
		Bezugskalkulation_View.labelDesign(lblSV);
		mainPane.add(lblSV);
		
		JLabel lblLS = new JLabel("Lohnsteuer");
		Bezugskalkulation_View.labelDesign(lblLS);
		mainPane.add(lblLS);
		
		JLabel lblnewGehalt_Lohn = new JLabel("neuer Gehalt/Lohn");
		Bezugskalkulation_View.labelDesign(lblnewGehalt_Lohn);
		lblnewGehalt_Lohn.setBounds(xLeft, yLeft+380, 190, 22);
		mainPane.add(lblnewGehalt_Lohn);
		
		txtLS = new JTextField();
		txtLS.setText(MainModel.roundDouble_giveBack_String(LS));
		View_SuperClass.txtFieldDesign_THIN(txtLS);
		txtLS.setColumns(10);
		txtLS.setBounds(xRight, yRight, MainView.kalktxtWidth, 40);
		mainPane.add(txtLS);
		yRight = yRight + 70;
		
	
		
		txtNewGehalt_Lohn = new JTextField();
		txtNewGehalt_Lohn.setText(MainModel.roundDouble_giveBack_String(newGehalt));
		View_SuperClass.txtFieldDesign(txtNewGehalt_Lohn);
		txtNewGehalt_Lohn.setColumns(10);
		txtNewGehalt_Lohn.setBounds(xRight, yRight+100, MainView.kalktxtWidth, 40);
		mainPane.add(txtNewGehalt_Lohn);
		
		
		
		JButton btnLösche = new JButton("Lösche");
		btnLösche.setBackground(new Color(37, 37, 38));
		btnLösche.setForeground(Color.WHITE);
		btnLösche.setContentAreaFilled(false);
		btnLösche.setOpaque(true);
		btnLösche.setFont(MainView.font_20);
		btnLösche.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		btnLösche.setBounds(800, 900, 142, 48);
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
		
		
	}
}
