package Personalverrechnung;

import View.MainModel;
import extraViews._2Stufig_VariableKonto;

public class Fahrtkosten_View extends _2Stufig_VariableKonto{

	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {

		finalZahlungskonto = lblKonto2Variable.getSelectedItem().toString();
			
		
		if(txtKontonummer.getText().equals("7341"))
			percent = "13";
		
		double nettoPrice = MainModel.parseDouble(txtPreis.getText());
		double bruttoPrice = MainModel.parseDouble(txtPreis.getText());
		
		if(netto.isSelected())
			bruttoPrice = myController.initNettoToBrutto(txtPreis.getText(), percent);
		else
			nettoPrice = myController.initBruttoToNetto(txtPreis.getText(), percent);
	
		String kontos[] = {txtKontonummer.getText(), finalZahlungskonto, Konto3};
		Double prices[] = {nettoPrice, bruttoPrice, bruttoPrice - nettoPrice};
		
		myController.initpaintUpTo7(kontos, prices, leftMore);
	
	}
	
}
