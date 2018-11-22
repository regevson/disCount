package Personalverrechnung;

import extraViews._2Stufig_VariableKonto;

public class Fahrtkosten_View extends _2Stufig_VariableKonto{

	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {

		finalZahlungskonto = lblKonto2Variable.getSelectedItem().toString();
			
		
		if(txtKontonummer.getText().equals("7341"))
			percent = "13";
		
		double nettoPrice = Double.parseDouble(txtPreis.getText());
		double bruttoPrice = Double.parseDouble(txtPreis.getText());
		
		if(netto.isSelected())
			nettoPrice = myController.initNettoToBrutto(percent, txtPreis.getText());
		else
			bruttoPrice = myController.initBruttoToNetto(percent, txtPreis.getText());
	
		String kontos[] = {txtKontonummer.getText(), finalZahlungskonto};
		Double prices[] = {nettoPrice, bruttoPrice, bruttoPrice - nettoPrice};
		
		myController.initpaintUpTo7(kontos, prices, leftMore);
	
	}
	
}
