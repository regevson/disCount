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
		
		finalZahlungskonto = lblKonto2Variable.getSelectedItem().toString();
		
		double nettoPrice = MainModel.parseDouble(txtPreis.getText());
		double bruttoPrice = MainModel.parseDouble(txtPreis.getText());
		
		if(netto.isSelected())
			bruttoPrice = myController.initNettoToBrutto(txtPreis.getText(), percent);
		else {
			nettoPrice = myController.initBruttoToNetto(txtPreis.getText(), percent);
			txtPreis.setText(Double.toString(nettoPrice));
		}
		
		String kontos[] = {konto1, finalZahlungskonto, konto3};
		Double prices[] = {nettoPrice, bruttoPrice, bruttoPrice - nettoPrice};
		
		myController.initpaintUpTo7(kontos, prices, !leftMore);
			
		makeAbschreibung();
			
	}
	
	public void makeAbschreibung() {

		finalAnlKonto = txtAnlage.getText();
		
		if(Integer.parseInt(IBN_Monat.getText()) > 6) // dann halbjahres AFA
			IBN_Monat.setText("1");
		
		else
			IBN_Monat.setText("12");
		
		Double abschreibungsWert = ((Controller_Anlagenbewertung) myController).initCalcAbschreibung(finalAnlKonto, IBN_Monat.getText(), "2018", txtND.getText(), MainModel.parseDouble(txtAW.getText()), "erstes Jahr", leftMore);
	
		make3rdBS(abschreibungsWert);

	}
	
	private void make3rdBS(Double abschreibungsBetrag) {
		
		String kontos[] = {"7820", finalAnlKonto};
		
		Double ausbuchWert = MainModel.parseDouble(buchWert.getText()) - abschreibungsBetrag;
		Double prices[] = {ausbuchWert};
		
		myController.initpaintUpTo7(kontos, prices, leftMore);
		
		Double richtWert = MainModel.parseDouble(txtPreis.getText()) - ausbuchWert;
		Double testRes = richtWert + Math.abs(richtWert);
		
		if(testRes == 0)// dann richtWert ist negativ
			openSaldBuchungen("4600", "7830", "7830", "7820", MainModel.parseDouble(txtPreis.getText()), MainModel.roundDouble_giveBack_Double(ausbuchWert));
		else
			openSaldBuchungen("4600", "4630", "4630", "7820", MainModel.parseDouble(txtPreis.getText()), MainModel.roundDouble_giveBack_Double(ausbuchWert));
		
	}
	
	private void openSaldBuchungen(String konto1, String konto2, String konto3, String konto4, Double nettoVP, Double ausbuchWert) {
		
		String kontos[] = {konto1, konto2};
		Double prices[] = {nettoVP};
		
		myController.initpaintUpTo7(kontos, prices, leftMore);
		
		
		String kontos2[] = {konto3, konto4};
		Double prices2[] = {ausbuchWert};
		
		myController.initpaintUpTo7(kontos2, prices2, leftMore);

	}
	
}
