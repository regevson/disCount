package extraViews;

import javax.swing.JTextField;

import View.MainModel;
import View.MainView;

public class _2Stufig_VariableKonto extends View_SuperClass{
	
	@Override
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		makeVariableTextField();
		if(fixed == true)
			makeKonto2Fixed(Konten2[0]);
		else
			makeKonto2Variable(Konten2);
		makeKonto3(Konto3);
		makePrice();
		makeVariableTax();
		makeButtons();
	}

	private void makeVariableTextField() {
		txtKontonummer = new JTextField("Kontonummer");
		txtKontonummer.setFont(MainView.font_18);
		txtKontonummer.setBounds(50, yFirst, 130, 26);
		contentPane.add(txtKontonummer);
		txtKontonummer.setColumns(10);
		xLeft = 50;
	}
	
	
	
	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {

		finalZahlungskonto = lblKonto2Variable.getSelectedItem().toString();
			
		double nettoPrice = MainModel.parseDouble(txtPreis.getText());
		double bruttoPrice = MainModel.parseDouble(txtPreis.getText());
		
		if(netto.isSelected())
			bruttoPrice = myController.initNettoToBrutto(txtPreis.getText(), ((String) variableTax.getSelectedItem()).substring(0, 2));
		else
			nettoPrice = myController.initBruttoToNetto(txtPreis.getText(), ((String) variableTax.getSelectedItem()).substring(0, 2));

		String kontos[] = {txtKontonummer.getText(), finalZahlungskonto, Konto3};
		Double prices[] = {nettoPrice, bruttoPrice, bruttoPrice - nettoPrice};
		
		myController.initpaintUpTo7(kontos, prices, leftMore);

	}
	
}
