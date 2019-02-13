package Rechnungsausgleich;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

import View.MainModel;
import View.MainView;
import extraViews.View_SuperClass;

public class Ausgleich_Kreditkarten_View extends View_SuperClass{

	private JTextField txtPreis2;



	@Override
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		
		makeKonto1(Konto1);
		makeKonto2Fixed(Konten2[0]);
		makeKonto3(Konto3);
		makePrice();
		makePrice2();
		makeKonto4();
		
	}
	


	
	
	public void makeKonto4() {
		lblKonto4 = new JLabel("2500");
		lblKonto4.setFont(MainView.font_20);
		lblKonto4.setForeground(Color.WHITE);
		lblKonto4.setBounds(marginLeft1, 235 + (235 - 195), 56, 16);
		contentPane.add(lblKonto4);
	}
	
	private void makePrice2() {
		txtPreis2 = new JTextField();
		View_SuperClass.txtFieldDesign(txtPreis2);
		txtPreis2.setText("Spesen");
		txtPreis2.setBounds(621, 139, 116, 42);
		contentPane.add(txtPreis2);
		txtPreis2.setColumns(10);
	}
	
	

	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {

		finalZahlungskonto = Konten2[0];
		
		String kontos[] = {Konto1, finalZahlungskonto, Konto3, lblKonto4.getText()};
		Double prices[] = ((Controller_Rechnungsausgleich) myController).initCalcNettoKreditkarten(txtPreis.getText(), txtPreis2.getText());
		
		myController.initpaintUpTo7(kontos, prices, leftMore);

	}
	
}
