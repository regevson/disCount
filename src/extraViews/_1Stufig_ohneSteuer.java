package extraViews;

import View.MainModel;

public class _1Stufig_ohneSteuer extends _1Stufig_Fixed_View{

	@Override
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		System.out.println("INHERE");
		makeKonto1(Konto1);
		
		if(fixed == true)
			makeKonto2Fixed(Konten2[0]);
		else
			makeKonto2Variable(Konten2);
		makePrice();
		netto = null;
	}
	
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {
		String kontos[] = {Konten2[0], lblKonto1.getText()};
		Double prices[] = {MainModel.parseDouble(txtPreis.getText())};
		
		myController.initpaintUpTo7(kontos, prices, leftMore);
	}
	
}
