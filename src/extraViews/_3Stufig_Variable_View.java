package extraViews;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

import View.MainModel;
import View.MainView;

public class _3Stufig_Variable_View extends View_SuperClass{

	public static JTextField txtPreis2;
	
	@Override
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		makeVariableTextField();
		if(fixed == true)
			makeKonto2Fixed(Konten2[0]);
		else
			makeKonto2Variable(Konten2);
		makeKonto3(Konto3);
		makePrice();
		makePrice2();
		makeButtons();
		makeKonto4();
	}
	

	private void makeVariableTextField() {
		txtKontonummer = new JTextField("Kontonummer");
		txtKontonummer.setFont(MainView.font_18);
		txtKontonummer.setBounds(50, yFirst, 130, 26);
		contentPane.add(txtKontonummer);
		txtKontonummer.setColumns(10);
		xLeft = 50;
		
		makeKonto1(null);
		lblKonto1.setVisible(false);
	}
	
	
	public void makeKonto4() {
		lblKonto4 = new JLabel("2500");
		lblKonto4.setForeground(Color.WHITE);
		lblKonto4.setFont(MainView.font_20);
		lblKonto4.setBounds(xLeft, ySecond+(ySecond-yFirst)-5, 56, 16);
		contentPane.add(lblKonto4);
	}
	
	private void makePrice2() {
		txtPreis2 = new JTextField();
		View_SuperClass.txtFieldDesign(txtPreis2);
		txtPreis2.setText("Emballagen");
		txtPreis2.setBounds(621, 139, 116, 42);
		contentPane.add(txtPreis2);
		txtPreis2.setColumns(10);
	}
	
	

	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {

		finalZahlungskonto = lblKonto2Variable.getSelectedItem().toString();
			
		String kontos[] = {txtKontonummer.getText(), finalZahlungskonto, Konto3, lblKonto4.getText()};
		Double prices[];
		
			if(netto.isSelected())
				prices = myController.initNettoToBrutto(txtPreis.getText(), txtPreis2.getText(), percent);
			else
				prices = myController.initBruttoToNetto(txtPreis.getText(), txtPreis2.getText(), percent);

			myController.initpaintUpTo7(kontos, prices, leftMore);
			
	}
	
}
