package Personalverrechnung;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

import View.MainModel;
import View.MainView;
import extraViews.View_SuperClass;
import extraViews.View_SuperClass_2Outputs;

public class _4_Stufig_Fixed_View extends View_SuperClass_2Outputs{

	private String konto1;
	private String[] konten2;
	private String konto3;
	
	private String price1, price2, price3, price4;
	
	private JTextField txtPrice1, txtPrice2, txtPrice3, txtPrice4;
	private JTextField SV_DGA, DB, DZ, KommSt;
	
	
	@Override
	public void build(String konto1, String[] konten2, String konto3, boolean twoOutputs) {
		this.konto1 = konto1;
		this.konten2 = konten2;
		this.konto3 = konto3;
			
		makeKonto1(konto1);
		makeKonto2Fixed(konten2[0]);
		makeRightKonto(konto3, yFirst + 20);
		makeRightKonto(konto4, yFirst + 40);
		makeRightKonto(percent, yFirst + 60);
		
		makePrices();
		makeInfoFields();
	}
	
	public void makeRightKonto(String konto, int y) {
		JLabel lblKonto = new JLabel(konto);
		lblKonto.setForeground(Color.WHITE);
		lblKonto.setFont(MainView.font_20);
		lblKonto.setBounds(xLeft, y, 71, 26);
		contentPane.add(lblKonto);
	}
	
	
	private void makePrices() {
		txtPrice1 = new JTextField("Gehälter");
		View_SuperClass.txtFieldDesign(txtPrice1);
		txtPrice1.setBounds(xLeft+200, yFirst, 130, 42);
		contentPane.add(txtPrice1);
		txtPrice1.setColumns(10);
		
		txtPrice2 = new JTextField("Krankenkasse");
		View_SuperClass.txtFieldDesign(txtPrice2);
		txtPrice2.setBounds(xLeft+400, yFirst, 140, 42);
		contentPane.add(txtPrice2);
		txtPrice2.setColumns(10);
		
		txtPrice3 = new JTextField("Finanzamt");
		View_SuperClass.txtFieldDesign(txtPrice3);
		txtPrice3.setBounds(xLeft+400, ySecond+10, 140, 42);
		contentPane.add(txtPrice3);
		txtPrice3.setColumns(10);
		
		txtPrice4 = new JTextField("Mitarbeiter");
		View_SuperClass.txtFieldDesign(txtPrice4);
		txtPrice4.setBounds(xLeft+400, ySecond + (ySecond-yFirst)+20, 140, 42);
		contentPane.add(txtPrice4);
		txtPrice4.setColumns(10);
	}
	
	
	private void makeInfoFields() {
		SV_DGA = new JTextField("SV-DGA in €");
		View_SuperClass.txtFieldDesign(SV_DGA);
		SV_DGA.setBounds(xLeft+800, yFirst, 130, 42);
		contentPane.add(SV_DGA);
		SV_DGA.setColumns(10);
		
		DB = new JTextField("DB in %");
		View_SuperClass.txtFieldDesign(DB);
		DB.setBounds(xLeft+800, ySecond+10, 130, 42);
		contentPane.add(DB);
		DB.setColumns(10);
		
		DZ = new JTextField("DZ in %");
		View_SuperClass.txtFieldDesign(DZ);
		DZ.setBounds(xLeft+800, ySecond + (ySecond-yFirst)+20, 130, 42);
		contentPane.add(DZ);
		DZ.setColumns(10);
		
		KommSt = new JTextField("KommSt in %");
		View_SuperClass.txtFieldDesign(KommSt);
		KommSt.setBounds(xLeft+800, ySecond + 2*((ySecond-yFirst)+15), 130, 42);
		contentPane.add(KommSt);
		KommSt.setColumns(10);
	}


	


	@Override
	public void setUpRoutine(String konto4, String konto5, String percent, boolean twoOutputs, String extra) {
		
		makeAdditionalBS();
		
	}
	
	private void makeAdditionalBS() {
		
		String kontos[] = {"3600", "6200", "3540", "3850"};
		Double prices[] = {MainModel.parseDouble(txtPrice2.getText()), MainModel.parseDouble(txtPrice1.getText()), MainModel.parseDouble(txtPrice3.getText()), MainModel.parseDouble(txtPrice4.getText())};
		
		myController.initpaintUpTo7(kontos, prices, leftMore);
		
		
		double SV_DGA_Price = MainModel.parseDouble(SV_DGA.getText());
		double DB_Price = calcPrices(DB);
		double DZ_Price = calcPrices(DZ);
		double KommSt_Price = calcPrices(KommSt);
		
		
		double SV_Price = MainModel.parseDouble(txtPrice2.getText());
		double FA_Price = MainModel.parseDouble(txtPrice3.getText());
		double Mitarbeiter_Price = MainModel.parseDouble(txtPrice4.getText());
		
		
		
		String kontos2[] = {"3600", "6560"};
		Double prices2[] = {MainModel.parseDouble(SV_DGA.getText())};
		myController.initpaintUpTo7(kontos2, prices2, leftMore);
		
		String kontos3[] = {"3540", "6660"};
		Double prices3[] = {DB_Price};
		myController.initpaintUpTo7(kontos3, prices3, leftMore);
		
		String kontos4[] = {"3540", "6670"};
		Double prices4[] = {DZ_Price};
		myController.initpaintUpTo7(kontos4, prices4, leftMore);
		
		String kontos5[] = {"3610", "6680"};
		Double prices5[] = {KommSt_Price};
		myController.initpaintUpTo7(kontos5, prices5, leftMore);
		
		
		
		
		String kontos6[] = {"2800", "3850"};
		Double prices6[] = {Mitarbeiter_Price};
		myController.initpaintUpTo7(kontos6, prices6, leftMore);
		
		String kontos7[] = {"2800", "3600"};
		Double prices7[] = {SV_Price + SV_DGA_Price};
		myController.initpaintUpTo7(kontos7, prices7, leftMore);
		
		String kontos8[] = {"2800", "3540"};
		Double prices8[] = {FA_Price + DB_Price + DZ_Price};
		myController.initpaintUpTo7(kontos8, prices8, leftMore);
		
		String kontos9[] = {"2800", "3610"};
		Double prices9[] = {KommSt_Price};
		myController.initpaintUpTo7(kontos9, prices9, leftMore);
		
	}
	
	
	private double calcPrices(JTextField tf) {
		return myController.initCalcGehaltsPercent(txtPrice1.getText(), tf.getText());
	}
	
}
