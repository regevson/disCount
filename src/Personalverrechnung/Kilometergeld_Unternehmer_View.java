package Personalverrechnung;

import javax.swing.JTextField;

import View.MainModel;
import View.MainView;
import extraViews.View_SuperClass;

public class Kilometergeld_Unternehmer_View extends View_SuperClass{

	private JTextField txtKM;
	private JTextField txtKMPrice;
	
	@Override
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		makeKonto1(Konto1);
		
		if(fixed == true)
			makeKonto2Fixed(Konten2[0]);
		else
			makeKonto2Variable(Konten2);
		makeKMPrice();
		makeKM();
	}
	
	
	private void makeKMPrice() {
		txtKMPrice = new JTextField();
		View_SuperClass.txtFieldDesign(txtKMPrice);
		txtKMPrice.setText("Geld/Kilometer");
		txtKMPrice.setBounds(621, 129, 150, 42);
		contentPane.add(txtKMPrice);
		txtKMPrice.setColumns(10);
	}
	
	private void makeKM() {
		txtKM = new JTextField();
		txtKM.setFont(MainView.font_18);
		txtKM.setText("Kilometer");
		txtKM.setBounds(621, 189, 116, 42);
		contentPane.add(txtKM);
		txtKM.setColumns(10);
	}
	

	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {

		finalZahlungskonto = lblKonto2Variable.getSelectedItem().toString();
		
		String kontos[] = {lblKonto1.getText(), finalZahlungskonto};
		Double prices[] = {MainModel.parseDouble(txtKM.getText()) * Float.parseFloat(txtKMPrice.getText())};
		
		myController.initpaintUpTo7(kontos, prices, leftMore);
		
	}
	
}
