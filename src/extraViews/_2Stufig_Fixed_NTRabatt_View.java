package extraViews;

import javax.swing.JTextField;

import View.MainModel;
import View.MainView;

public class _2Stufig_Fixed_NTRabatt_View extends View_SuperClass{

	
	public static JTextField txtPreis2;
	
	@Override
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		makeKonto1(Konto1);
		
		if(fixed == true)
			makeKonto2Fixed(Konten2[0]);
		else
			makeKonto2Variable(Konten2);
		makeKonto3(Konto3);
		makePrice();
		makePrice2();
		makeButtons();
	}
	
	
	private void makePrice2() {
		rabattPercent = new JTextField();
		rabattPercent.setFont(MainView.font_18);
		rabattPercent.setText("Rabatt");
		rabattPercent.setBounds(621, 139, 116, 42);
		contentPane.add(rabattPercent);
		rabattPercent.setColumns(10);
	}
	

	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {
		if(fixed == true)
			finalZahlungskonto = Konten2[0];
		else
			finalZahlungskonto = lblKonto2Variable.getSelectedItem().toString();
			
		
		String kontos[] = {Konto1, finalZahlungskonto, Konto3};
		Double prices[];
		
			if(netto.isSelected()) {
				prices = myController.initCalculateRabattPricesFromNetto(percent, txtPreis.getText(), rabattPercent.getText());
			}
			else {
				prices = myController.initCalculateRabattPricesFromNetto(percent, Double.toString(myController.initBruttoToNetto(txtPreis.getText(), "20")), rabattPercent.getText());
			}
	
			myController.initpaintUpTo7(kontos, prices, leftMore);
			
	}
	
	
	
			
}
