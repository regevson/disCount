package Personalverrechnung;

import javax.swing.JTextField;

import View.MainModel;
import extraViews.View_SuperClass;

public class Tagesgeld_View extends View_SuperClass{
	
	public static final float tagesGeld = (float) 26.4;
	public static final float essensGeld = (float) 13.20;
	
	private JTextField txtTage;
	private JTextField txtStartZeit;
	private JTextField txtEndZeit;
	private JTextField txtEssen;

	@Override
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		makeKonto1(Konto1);
		
		if(fixed == true)
			makeKonto2Fixed(Konten2[0]);
		else
			makeKonto2Variable(Konten2);
		makeKonto3(Konto3);
		makePrices();
	}
	
	private void makePrices() {
		txtTage = new JTextField("Tage");
		View_SuperClass.txtFieldDesign(txtTage);
		txtTage.setBounds(xLeft+400, yFirst, 130, 42);
		contentPane.add(txtTage);
		txtTage.setColumns(10);
		
		txtStartZeit = new JTextField("Startzeit");
		View_SuperClass.txtFieldDesign(txtStartZeit);
		txtStartZeit.setBounds(xLeft+400, ySecond, 130, 42);
		contentPane.add(txtStartZeit);
		txtStartZeit.setColumns(10);
		
		txtEndZeit = new JTextField("Endzeit");
		View_SuperClass.txtFieldDesign(txtEndZeit);
		txtEndZeit.setBounds(xLeft+400, ySecond+(ySecond-yFirst), 130, 42);
		contentPane.add(txtEndZeit);
		txtEndZeit.setColumns(10);
		
		txtEssen = new JTextField("Anzahl Essen");
		View_SuperClass.txtFieldDesign(txtEssen);
		txtEssen.setBounds(xLeft+550, yFirst, 130, 42);
		contentPane.add(txtEssen);
		txtEssen.setColumns(10);
	}
	
	

	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {
		if(fixed == true)
			finalZahlungskonto = Konten2[0];
		else
			finalZahlungskonto = lblKonto2Variable.getSelectedItem().toString();
		
		double resTG = myController.initCalcTagesgeld(txtTage.getText(), txtStartZeit.getText(), txtEndZeit.getText(), txtEssen.getText());
		double nettoPrice = myController.initBruttoToNetto(Double.toString(resTG), percent);
			
		String kontos[] = {Konto1, finalZahlungskonto, Konto3};
		Double prices[] = {nettoPrice, resTG, resTG - nettoPrice};
		
		myController.initpaintUpTo7(kontos, prices, leftMore);
		

	}
	
}
