package Personalverrechnung;

import View.MainModel;
import extraViews._2Stufig_VariableKonto;

public class Fahrtkosten_View extends _2Stufig_VariableKonto{

	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {

		finalZahlungskonto = lblKonto2Variable.getSelectedItem().toString();
			
		myController.initPaint3Konten(txtKontonummer.getText(), finalZahlungskonto, Konto3);
		
		if(txtKontonummer.getText().equals("7341"))
			percent = "13";
		
			if(netto.isSelected())
				myController.initNetto_to_paintAll3(percent, txtPreis.getText());
			else
				myController.initBrutto_to_paintAll3(percent, txtPreis.getText());
	
			resetSwap();
	}
	
}
