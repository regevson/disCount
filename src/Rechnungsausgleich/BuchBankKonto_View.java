package Rechnungsausgleich;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import View.MainModel;
import extraViews.View_SuperClass;

public class BuchBankKonto_View extends View_SuperClass{

	private JCheckBox cbBankbeleg;
	private JCheckBox cbAbhebungVomBankkonto;
	private JCheckBox cbEinzahlungInsBankkonto;
	private JCheckBox cbAbhebungVonKassa;
	private JCheckBox cbEinzahlungInKassa;



	@Override
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		
		makeCB();
		makePrice();
	}
	
	private void makeCB() {
		
		cbAbhebungVomBankkonto = new JCheckBox("Abhebung vom Bankkonto");
		View_SuperClass.checkBoxDesign(cbAbhebungVomBankkonto);
		cbAbhebungVomBankkonto.setBounds(285, 138, 228, 25);
		contentPane.add(cbAbhebungVomBankkonto);
		cbAbhebungVomBankkonto.setVisible(false);
		
		cbEinzahlungInsBankkonto = new JCheckBox("Einzahlung ins Bankkonto");
		View_SuperClass.checkBoxDesign(cbEinzahlungInsBankkonto);
		cbEinzahlungInsBankkonto.setBounds(285, 189, 228, 25);
		contentPane.add(cbEinzahlungInsBankkonto);
		cbEinzahlungInsBankkonto.setVisible(false);
		
		cbAbhebungVonKassa = new JCheckBox("Abhebung von Kassa");
		View_SuperClass.checkBoxDesign(cbAbhebungVonKassa);
		cbAbhebungVonKassa.setBounds(285, 138, 228, 25);
		contentPane.add(cbAbhebungVonKassa);
		cbAbhebungVonKassa.setVisible(false);
		
		cbEinzahlungInKassa = new JCheckBox("Einzahlung in Kassa");
		View_SuperClass.checkBoxDesign(cbEinzahlungInKassa);
		cbEinzahlungInKassa.setBounds(285, 189, 228, 25);
		contentPane.add(cbEinzahlungInKassa);
		cbEinzahlungInKassa.setVisible(false);
		
		
		JCheckBox cbKassabeleg = new JCheckBox("Kassabeleg");
		View_SuperClass.checkBoxDesign(cbKassabeleg);
		cbKassabeleg.setBounds(64, 138, 113, 25);
		cbKassabeleg.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	cbKassabeleg.setForeground(new Color(0, 117, 211));
		    	cbAbhebungVomBankkonto.setVisible(true);
		    	cbEinzahlungInsBankkonto.setVisible(true);
		    }
		});
		contentPane.add(cbKassabeleg);
		
		
		JCheckBox cbBankbeleg = new JCheckBox("Bankbeleg");
		View_SuperClass.checkBoxDesign(cbBankbeleg);
		cbBankbeleg.setBounds(64, 189, 113, 25);
		cbBankbeleg.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	cbBankbeleg.setForeground(new Color(0, 117, 211));
		    	cbAbhebungVonKassa.setVisible(true);
		    	cbEinzahlungInKassa.setVisible(true);
		    }
		});
		contentPane.add(cbBankbeleg);
	}
	
	

	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {
			
		String kontos[] = null;
		
		if(cbAbhebungVomBankkonto.isSelected())
			kontos = new String[] {"2700", "2870"};
		
		else if(cbEinzahlungInsBankkonto.isSelected())
			kontos = new String[] {"2870", "2700"};
		
		else if(cbAbhebungVonKassa.isSelected())
			kontos = new String[] {"2800", "2870"};
		
		else if(cbEinzahlungInKassa.isSelected())
			kontos = new String[] {"2870", "2800"};
		
		Double prices[] = {MainModel.parseDouble(txtPreis.getText())};
		
		myController.initpaintUpTo7(kontos, prices, leftMore);

	}
	
}
