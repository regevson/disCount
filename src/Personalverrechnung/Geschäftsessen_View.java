package Personalverrechnung;

import javax.swing.JTextField;

import View.MainModel;
import extraViews.View_SuperClass;

public class Geschäftsessen_View extends View_SuperClass{
	
	private JTextField txtPrice1, txtPrice2, txtPrice3, txtPrice4;

	@Override
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		makePrices();
	}
	
	private void makePrices() {
		txtPrice1 = new JTextField("Gesamte Kosten");
		View_SuperClass.txtFieldDesign(txtPrice1);
		txtPrice1.setBounds(xLeft, yFirst, 170, 42);
		contentPane.add(txtPrice1);
		txtPrice1.setColumns(10);
		
		txtPrice2 = new JTextField("Betrag zu 20%");
		View_SuperClass.txtFieldDesign(txtPrice2);
		txtPrice2.setBounds(xLeft, ySecond+10, 170, 42);
		contentPane.add(txtPrice2);
		txtPrice2.setColumns(10);
		
		txtPrice3 = new JTextField("Betrag zu 10%");
		View_SuperClass.txtFieldDesign(txtPrice3);
		txtPrice3.setBounds(xLeft, ySecond+(ySecond-yFirst)+20, 170, 42);
		contentPane.add(txtPrice3);
		txtPrice3.setColumns(10);
	}
	
	

	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {

			finalZahlungskonto = Konten2[0];
			
			double half20 = Double.parseDouble(txtPrice2.getText())/2;
			double half10 = Double.parseDouble(txtPrice3.getText())/2;
		
			
			double res20Percent = calcRes(txtPrice2.getText(), 120);
			double tax20 = (half20/120)*20;
			double res10Percent = calcRes(txtPrice3.getText(), 110);
			double tax10 = (half10/110)*10;
			double allTax = tax20 + tax10;
			double rightPrice = res20Percent + res10Percent + allTax;
			
			myController.initPaint4Konten("7660", "2800", "7661", "2500");
			myController.initPaint4Prices(res20Percent, rightPrice, res10Percent, allTax);
			
			myController.initPaint4Konten("7663", "2800", "7664", "2500");
			myController.initPaint4Prices(res20Percent, rightPrice, res10Percent, allTax);
			
			
			

			resetSwap();
	}
	
	private double calcRes(String price, int percent) {
		double price_double = Double.parseDouble(price);
		return ((price_double/2)/percent)*100;
	}
	
	
}
