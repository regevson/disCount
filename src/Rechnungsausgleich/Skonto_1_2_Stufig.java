package Rechnungsausgleich;

import javax.swing.JTextField;

import View.MainView;
import extraViews.View_SuperClass;
import extraViews.View_SuperClass_2Outputs;

public class Skonto_1_2_Stufig extends View_SuperClass_2Outputs {
	public Skonto_1_2_Stufig() {
	}

	
	public static JTextField skontoPercent;
	private static String skontoBruttoPrice;
	
	private String konto1;
	private String[] konto2;
	private String konto3;

	
	@Override
	public void build(String konto1, String konto2[], String konto3, boolean fixed) {
		this.konto1 = konto1;
		this.konto2 = konto2;
		this.konto3 = konto3;
		
		changeYZKWP();
		makeKonto1(konto1);
		
		if(fixed == true)
			makeKonto2Fixed(konto2[0]);
		else
			makeKonto2Variable(konto2);
		
		makePrice();
		makeButtons();
		createSkontoPercent();
	}
	
	
	public void createSkontoPercent() {
		skontoPercent = new JTextField();
		View_SuperClass.txtFieldDesign(skontoPercent);
		skontoPercent.setText("Skonto in %");
		skontoPercent.setBounds(621, 142, 116, 42);
		contentPane.add(skontoPercent);
		skontoPercent.setColumns(10);
	}
	
	public String getSkontoPercentText() {
		return skontoPercent.getText();
	}
	
	

	@Override
	public void setUpRoutine(String konto4, String konto5, String percent, boolean fixed, String extra) {

		if(fixed == true)
			finalZahlungskonto = konto2[0];
		else
			finalZahlungskonto = lblKonto2Variable.getSelectedItem().toString();
		
		
		((Controller_Rechnungsausgleich) myController).initCreateKontos1stufig_Skonto(konto1, konto2[0]);

		
			if(netto.isSelected())
				skontoBruttoPrice = ((Controller_Rechnungsausgleich) myController).initNetto_to_newBrutto_Skonto(txtPreis.getText(), skontoPercent.getText());
			else
				skontoBruttoPrice = ((Controller_Rechnungsausgleich) myController).initBrutto_to_newBrutto_Skonto(txtPreis.getText(), skontoPercent.getText());
			
			View_SuperClass.resetyZK();
			
		makeThe2ndBS(konto4, konto5);
			


	}
	
	public void makeThe2ndBS(String konto4, String konto5) {

		myController.initPaint3Konten(konto3, konto4, konto5);
		
		String nettoPrice =myController.initBrutto_to_paintAll3("20", skontoBruttoPrice);
		MainView.llNettoPrices.set(MainView.llNettoPrices.size()-1, Double.parseDouble("-" + nettoPrice));
	
		View_SuperClass.resetSwap();

	}
	
	private void changeYZKWP() {
		yZKWP = MainView.margin+10;
	}
	


}
