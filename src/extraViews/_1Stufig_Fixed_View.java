package extraViews;

import View.MainModel;

public class _1Stufig_Fixed_View extends View_SuperClass{
	
	@Override
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		makeKonto1(Konto1);
		
		if(fixed == true)
			makeKonto2Fixed(Konten2[0]);
		else
			makeKonto2Variable(Konten2);
		makePrice();
		makeButtons();
	}
	
	

	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {
		
		if(fixed == true)
			finalZahlungskonto = Konten2[0];
		
		else
			finalZahlungskonto = lblKonto2Variable.getSelectedItem().toString();
		
		
		double bruttoPrice = MainModel.parseDouble(txtPreis.getText());
		
		if(netto != null && netto.isSelected())
			bruttoPrice = myController.initNettoToBrutto(percent, txtPreis.getText());
		
		String kontos[] = {lblKonto1.getText(), finalZahlungskonto};
		Double prices[] = {bruttoPrice};
			
	}

	
}
