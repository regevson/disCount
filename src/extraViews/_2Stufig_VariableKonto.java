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
			
		myController.initPaint3Konten(txtKontonummer.getText(), finalZahlungskonto, Konto3);
		
			if(netto.isSelected())
				myController.initNetto_to_paintAll3(((String) variableTax.getSelectedItem()).substring(0, 2), txtPreis.getText());
			else
				myController.initBrutto_to_paintAll3(((String) variableTax.getSelectedItem()).substring(0, 2), txtPreis.getText());
	
			resetSwap();
	}
	
}
