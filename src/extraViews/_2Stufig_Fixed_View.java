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
		
		
		double nettoPrice = MainModel.parseDouble(txtPreis.getText());
		double bruttoPrice = MainModel.parseDouble(txtPreis.getText());
		
		if(netto.isSelected())
			bruttoPrice = myController.initNettoToBrutto(txtPreis.getText(), ((String) variableTax.getSelectedItem()).substring(0, 2));
		else
			nettoPrice = myController.initBruttoToNetto(txtPreis.getText(), ((String) variableTax.getSelectedItem()).substring(0, 2));
		
		String kontos[] = {Konto1, finalZahlungskonto, Konto3};
		Double prices[] = {nettoPrice, bruttoPrice, bruttoPrice - nettoPrice};
		
		myController.initpaintUpTo7(kontos, prices, leftMore);

		MainView.waitOK = true;
		
		
	}


}
