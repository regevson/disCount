package extraViews;

import View.MainModel;
import View.MainView;

public class _2Stufig_Fixed_View extends View_SuperClass {
	
	
	@Override
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		MainView.waitOK = false;
		makeKonto1(Konto1);
		
		if(fixed == true)
			makeKonto2Fixed(Konten2[0]);
		else
			makeKonto2Variable(Konten2);
		makeKonto3(Konto3);
		makePrice();
		makeVariableTax();
		makeButtons();
	}
	
	

	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {
		if(fixed == true)
			finalZahlungskonto = Konten2[0];
		else
			finalZahlungskonto = lblKonto2Variable.getSelectedItem().toString();
			
		myController.initPaint3Konten(Konto1, finalZahlungskonto, Konto3);
		
			if(netto.isSelected())
				myController.initNetto_to_paintAll3(((String) variableTax.getSelectedItem()).substring(0, 2), txtPreis.getText());
			else
				myController.initBrutto_to_paintAll3(((String) variableTax.getSelectedItem()).substring(0, 2), txtPreis.getText());

			resetSwap();
			MainView.waitOK = true;
	}


}
