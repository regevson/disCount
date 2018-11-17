package Anlagenbewertung;

import javax.swing.JTextField;

import View.MainModel;
import View.MainView;
import extraViews.View_SuperClass;
import extraViews.View_SuperClass_2Outputs;

public class Anlagenausscheidung_durchVerkauf extends View_SuperClass_2Outputs{

	private String konto1;
	private String[] konto2;
	private String konto3;
	private JTextField buchWert;
	private JTextField IBN_Monat;
	private JTextField txtAnlage;
	private JTextField txtND;
	private String finalAnlKonto;
	private JTextField txtAW;

	@Override
	public void build(String konto1, String konto2[], String konto3, boolean fixed) {
		this.konto1 = konto1;
		this.konto2 = konto2;
		this.konto3 = konto3;
		
		changeYZKWP();
		makeKonto1(konto1);
		
		makeKonto2Variable(konto2);
		makeKonto3(konto3);
		makeAnlKonto();
		
		makePrice();
		makeButtons();
		createBuchWert();
		createIBN();
		makeND();
		makeAW();
	}
	
	private void makeAnlKonto() {
		txtAnlage = new JTextField("Anlagenkonto");
		View_SuperClass.txtFieldDesign(txtAnlage);
		txtAnlage.setBounds(850, yFirst-100, 220, 40);
		contentPane.add(txtAnlage);
		txtAnlage.setColumns(10);
	}
	
	private void makeAW() {
		txtAW = new JTextField("Anschaffungswert");
		View_SuperClass.txtFieldDesign(txtAW);
		txtAW.setBounds(850, yFirst-50, 220, 40);
		contentPane.add(txtAW);
		txtAW.setColumns(10);
	}
	
	private void makeND() {
		txtND = new JTextField("Nutzungsdauer");
		View_SuperClass.txtFieldDesign(txtND);
		txtND.setBounds(850, yFirst, 220, 40);
		contentPane.add(txtND);
		txtND.setColumns(10);
	}
	
	
	public void createIBN() {
		IBN_Monat = new JTextField();
		View_SuperClass.txtFieldDesign(IBN_Monat);
		IBN_Monat.setText("Monat Verkauf");
		IBN_Monat.setBounds(850, ySecond + 10, 220, 42);
		contentPane.add(IBN_Monat);
		IBN_Monat.setColumns(10);
	}
	
	
	public void createBuchWert() {
		buchWert = new JTextField();
		View_SuperClass.txtFieldDesign(buchWert);
		buchWert.setText("Buchwert");
		buchWert.setBounds(850, ySecond + (ySecond - yFirst) + 23, 220, 42);
		contentPane.add(buchWert);
		buchWert.setColumns(10);
	}

	
	public String getbuchWertText() {
		return buchWert.getText();
	}
	
	

	
	public void setUpRoutine(String konto4, String konto5, String percent, boolean fixed, String extra) {
		System.out.println("setuproutine");
		
		finalZahlungskonto = lblKonto2Variable.getSelectedItem().toString();
		
		myController.initPaint3Konten(konto1, finalZahlungskonto, konto3);
		
		if(netto.isSelected())
			myController.initNetto_to_paintAll3(percent, txtPreis.getText());
		else
			txtPreis.setText(myController.initBrutto_to_paintAll3(percent, txtPreis.getText()));

		
		View_SuperClass.resetyZK();
		View_SuperClass.resetSwap();
			
		makeAbschreibung();
			


	}
	
	public void makeAbschreibung() {

		finalAnlKonto = txtAnlage.getText();
		
		if(Integer.parseInt(IBN_Monat.getText()) > 6) // dann halbjahres AFA
			IBN_Monat.setText("1");
		
		else
			IBN_Monat.setText("12");
		
		Double abschreibungsWert = ((Controller_Anlagenbewertung) myController).initCalcAbschreibung(finalAnlKonto, IBN_Monat.getText(), "2017", txtND.getText(), Double.parseDouble(txtAW.getText()), "erstes Jahr");
	
		make3rdBS(abschreibungsWert);

	}
	
	private void changeYZKWP() {
		yZKWP = MainView.margin+10;
	}
	
	private void make3rdBS(Double abschreibungsBetrag) {
		myController.initPaint2Konten("7820", finalAnlKonto);
		
		Double ausbuchWert = Double.parseDouble(buchWert.getText()) - abschreibungsBetrag;
		myController.initPaint1Price(ausbuchWert);
		
		Double richtWert = Double.parseDouble(txtPreis.getText()) - ausbuchWert;
		Double testRes = richtWert + Math.abs(richtWert);
		
		if(testRes == 0)// dann richtWert ist negativ
			openSaldBuchungen("4600", "7830", "7830", "7820", Double.parseDouble(txtPreis.getText()), MainModel.roundDouble_giveBack_Double(ausbuchWert));
		else
			openSaldBuchungen("4600", "4630", "4630", "7820", Double.parseDouble(txtPreis.getText()), MainModel.roundDouble_giveBack_Double(ausbuchWert));
	}
	
	private void openSaldBuchungen(String konto1, String konto2, String konto3, String konto4, Double nettoVP, Double ausbuchWert) {
		myController.initPaint2Konten_mitzyk(konto1, konto2);
		myController.initPaint1Price(nettoVP);
		
		myController.initPaint2Konten_mitzyk(konto3, konto4);
		myController.initPaint1Price(ausbuchWert);
	}
	
}
