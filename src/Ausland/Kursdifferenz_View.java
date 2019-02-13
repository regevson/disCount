package Ausland;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import View.MainModel;
import View.MainView;
import extraViews.View_SuperClass;

public class Kursdifferenz_View extends View_SuperClass{

	private JCheckBox IGE_IMP_CB;
	private JCheckBox IGL_EXP_CB;
	
	private JTextField txtOldPrice;
	private JTextField txtOldKurs;
	private JTextField txtNewKurs;
	
	private String konto_Vab, konto2_Gewinn = "4840", konto1_Verlust = "7815";

	@Override
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		
		makeCB1();
		makeCB2();
		makeFields();
	}
	
	

	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {
		
		if(IGE_IMP_CB.isSelected())
			makeKursdifferenz(true);
		else
			makeKursdifferenz(false);
		
	}
	
	private void makeCB1() {
		IGE_IMP_CB = new JCheckBox("IG Erwerb oder Import");
		IGE_IMP_CB.setBackground(null);
		IGE_IMP_CB.setForeground(Color.WHITE);
		IGE_IMP_CB.setFont(MainView.font_16);
		IGE_IMP_CB.setBounds(10, 180, 200, 25);
		contentPane.add(IGE_IMP_CB);
	}
	
	private void makeCB2() {
		IGL_EXP_CB = new JCheckBox("IG Lieferung oder Export");
		IGL_EXP_CB.setBackground(null);
		IGL_EXP_CB.setForeground(Color.WHITE);
		IGL_EXP_CB.setFont(MainView.font_16);
		IGL_EXP_CB.setBounds(10, 210, 220, 25);
		contentPane.add(IGL_EXP_CB);
	}
	
	private void makeFields() {
		txtOldPrice = new JTextField();
		txtOldPrice.setFont(MainView.font_18);
		txtOldPrice.setText("Betrag");
		txtOldPrice.setBounds(250, 189, 116, 42);
		contentPane.add(txtOldPrice);
		txtOldPrice.setColumns(10);
		
		txtOldKurs = new JTextField();
		txtOldKurs.setFont(MainView.font_18);
		txtOldKurs.setText("alter Kurs");
		txtOldKurs.setBounds(450, 189, 116, 42);
		contentPane.add(txtOldKurs);
		txtOldKurs.setColumns(10);
		
		txtNewKurs = new JTextField();
		txtNewKurs.setFont(MainView.font_18);
		txtNewKurs.setText("neuer Kurs");
		txtNewKurs.setBounds(650, 189, 116, 42);
		contentPane.add(txtNewKurs);
		txtNewKurs.setColumns(10);
	}
	
	private void makeKursdifferenz(boolean IGErw_Impo) {
		Double erg1 = (MainModel.parseDouble(txtOldPrice.getText())) / (MainModel.parseDouble(txtOldKurs.getText()));
		System.out.println("erg1  " + erg1);
		System.out.println(txtOldPrice.getText() + "  oldprice");
		System.out.println(txtNewKurs.getText() + "  kurs");
		Double erg2 = MainModel.parseDouble(txtOldPrice.getText()) / MainModel.parseDouble(txtNewKurs.getText());
		System.out.println("erg2  " + erg2);
		
		Double res = erg1 - erg2;
		System.out.println("res  " + res);
		Double testres = res + Math.abs(res);
		
		if(IGErw_Impo) {
			konto_Vab = "3300";
			if(testres == 0) // res ist negativ
				output_Result_IGE_IMP(false, res);
			else
				output_Result_IGE_IMP(true, res);
		}
		
		else {
			konto_Vab = "2000";
			if(testres == 0)
				output_Result_IGL_EXP(true, res);
			else
				output_Result_IGL_EXP(false, res);
		}
		
		
	}
	
	private void output_Result_IGE_IMP(boolean gewinn, Double result) {
		
		
		if(gewinn == true) {
			String kontos[] = {konto_Vab, konto2_Gewinn};
			Double prices[] = {Math.abs(MainModel.parseDouble(MainModel.roundDouble_giveBack_String(result)))};
			myController.initpaintUpTo7(kontos, prices, leftMore);
		}
		
		else {
			String kontos[] = {konto1_Verlust, konto_Vab};
			Double prices[] = {Math.abs(MainModel.parseDouble(MainModel.roundDouble_giveBack_String(result)))};
			myController.initpaintUpTo7(kontos, prices, leftMore);
		}
	}
	
	private void output_Result_IGL_EXP(boolean gewinn, Double result) {
		
		if(gewinn == true) {
			String kontos[] = {konto_Vab, konto2_Gewinn};
			Double prices[] = {Math.abs(MainModel.parseDouble(MainModel.roundDouble_giveBack_String(result)))};
			myController.initpaintUpTo7(kontos, prices, leftMore);
		}
		
		else {
			String kontos[] = {konto1_Verlust, konto_Vab};
			Double prices[] = {Math.abs(MainModel.parseDouble(MainModel.roundDouble_giveBack_String(result)))};
			myController.initpaintUpTo7(kontos, prices, leftMore);
		}
	}
	
}
