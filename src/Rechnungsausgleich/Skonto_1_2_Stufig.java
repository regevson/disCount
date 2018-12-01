package Rechnungsausgleich;

import javax.swing.JTextField;

import View.MainModel;
import extraViews.View_SuperClass;
import extraViews.View_SuperClass_2Outputs;

public class Skonto_1_2_Stufig extends View_SuperClass_2Outputs {
	
	public static JTextField skontoPercent;
	private static Double skontoBruttoPrice;
	
	private String konto1;
	private String[] konto2;
	private String konto3;

	
	@Override
	public void build(String konto1, String konto2[], String konto3, boolean fixed) {
		this.konto1 = konto1;
		this.konto2 = konto2;
		this.konto3 = konto3;

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
		
		
		
		double nettoPrice = MainModel.parseDouble(txtPreis.getText());
		double bruttoPrice = MainModel.parseDouble(txtPreis.getText());
		

		
		if(netto.isSelected()) {
			bruttoPrice = myController.initNettoToBrutto(txtPreis.getText(), percent);
			skontoBruttoPrice = ((Controller_Rechnungsausgleich) myController).initNettoToSkontoBrutto(txtPreis.getText(), skontoPercent.getText());
		}
		else {
			nettoPrice = myController.initBruttoToNetto(txtPreis.getText(), percent);
			skontoBruttoPrice = ((Controller_Rechnungsausgleich) myController).initBruttoToSkontoBrutto(txtPreis.getText(), skontoPercent.getText());
		}
		
		
		
		String kontos[] = {konto1, finalZahlungskonto};
		Double prices[] = {bruttoPrice - skontoBruttoPrice};
		
		myController.initpaintUpTo7(kontos, prices, leftMore);
			
		makeThe2ndBS(konto4, konto5);
			


	}
	
	public void makeThe2ndBS(String konto4, String konto5) {
		
		Double skontoNettoPrice = myController.initBruttoToNetto(Double.toString(skontoBruttoPrice), "20");
		Double skontoTaxPrice = MainModel.roundDouble_giveBack_Double(skontoBruttoPrice - skontoNettoPrice);
		
		
		String kontos[] = {konto3, konto4, konto5};
		Double prices[] = {skontoNettoPrice, skontoBruttoPrice, skontoTaxPrice};
		myController.initpaintUpTo7(kontos, prices, leftMore);

	}
	


}
