package Ausland;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.Buchungssatz;
import View.MainModel;
import extraViews.View_SuperClass;
import extraViews.View_SuperClass_2Outputs;

public class Ausland_Ausgleich extends View_SuperClass_2Outputs{

	private String konto1;
	private String[] konten2;
	private String konto3;
	
	private static JTextField fwSatz;
	private static JTextField spField;
	private static JTextField skField;
	
	private JCheckBox fwCB;
	private JCheckBox spCB;
	private JCheckBox skCB;
	
	private View_SuperClass VSC;
	private View_SuperClass_2Outputs VSC2OP;
	
	private boolean threeOutputs = true;
	
	private double newPrice;
	private double skonto_Brutto;
	private double skonto_20Percent;
	
	private JPanel panel;

	
	@Override
	public void build(String konto1, String[] konten2, String konto3, boolean spesen) {
		this.konto1 = konto1;
		this.konten2 = konten2;
		this.konto3 = konto3;
			
		makeKonto1(konto1);
		makeKonto2Fixed(konten2[0]);
		makeKonto3(konto3);
		lblKonto3.setVisible(false);
		makePrice();
		
		makeSpesenField();
		makeFremdwährungssatzField();
		makeSkontoField();
		makeFremdwährungCheckbox();
		makeSkontoCheckbox();
		makeSpesenCheckbox();
		if(!spesen)
			spCB.setVisible(false);
	}
	
	private void makeFremdwährungssatzField() {
		fwSatz = new JTextField();
		View_SuperClass.txtFieldDesign(fwSatz);
		fwSatz.setText("Kurs");
		fwSatz.setBounds(621, 142, 116, 42);
		contentPane.add(fwSatz);
		fwSatz.setColumns(10);
		fwSatz.setVisible(false);
	}
	
	private void makeSkontoField() {
		skField = new JTextField();
		View_SuperClass.txtFieldDesign(skField);
		skField.setText("Skonto in %");
		skField.setBounds(621, 40, 116, 42);
		contentPane.add(skField);
		skField.setColumns(10);
		skField.setVisible(false);
	}
	
	private void makeSpesenField() {
		spField = new JTextField();
		View_SuperClass.txtFieldDesign(spField);
		spField.setText("Spesen in €");
		spField.setBounds(621, 95, 116, 42);
		contentPane.add(spField);
		spField.setColumns(10);
		spField.setVisible(false);
	}
	
	
	private void makeFremdwährungCheckbox() {
		fwCB = new JCheckBox("Fremdwährung");
		View_SuperClass.checkBoxDesign(fwCB);
		fwCB.setBounds(618, 277, 200, 25);
		fwCB.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				fwSatz.setVisible(true);
				fwCB.setForeground(new Color(0, 117, 211));
			}

				});
		contentPane.add(fwCB);
	}
	
	private void makeSkontoCheckbox() {
		skCB = new JCheckBox("Skonto");
		View_SuperClass.checkBoxDesign(skCB);
		skCB.setBounds(618, 247, 200, 25);
		skCB.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				skField.setVisible(true);
				skCB.setForeground(new Color(0, 117, 211));
			}

				});
		contentPane.add(skCB);
	}
	
	private void makeSpesenCheckbox() {
		spCB = new JCheckBox("Spesen");
		View_SuperClass.checkBoxDesign(spCB);
		spCB.setBounds(618, 307, 100, 25);
		spCB.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				spField.setVisible(true);
				lblKonto3.setVisible(true);
				spCB.setForeground(new Color(0, 117, 211));
			}

				});
		contentPane.add(spCB);
	}


	@Override
	public void setUpRoutine(String konto4, String konto5, String percent, boolean spesen, String erwSt) {
		
		Buchungssatz bs = null;
		
		finalZahlungskonto = konten2[0];
		
		this.newPrice = MainModel.parseDouble(txtPreis.getText());
		
		

		if(fwCB.isSelected())
			this.newPrice = MainModel.parseDouble(((Controller_Ausland) myController).initExecuteFWRoutine(txtPreis.getText(), fwSatz.getText()));
		
		
		
		if(!(skCB.isSelected()) && !(spCB.isSelected()))
			make1stBS();
		
		
		
		else if(skCB.isSelected()) {
			skontoException(newPrice, percent);
			
			if(spCB.isSelected()) {// Wenn Skonto und Spesen selected sind
				spesenException(newPrice, konto1);
				bs = makeThe2ndBSWith_Skonto(konto4, konto5, spesen, erwSt);
				threeOutputs = false;
			}//if
			
			else { // Wenn nur Skonto selected ist
				make1stBS();
				bs = makeThe2ndBSWith_Skonto(konto4, konto5, spesen, erwSt);
			}//else
		}//else if
		
		
		
		else { // Wenn nur Spesen selected ist
			spesenException(newPrice, konto1);
			threeOutputs = false;
		}
		
		if(fwCB.isSelected())
			bs.addNoteToPanel("Verwendeter Kurs:     "+ fwSatz.getText(), 375);
			
	}
	
	public void make1stBS() {
		String kontos[] = {konto1, finalZahlungskonto};
		Double prices[] = {newPrice};
		myController.initpaintUpTo7(kontos, prices, leftMore);
	}
	

	
	public Buchungssatz makeThe2ndBSWith_Skonto(String konto4, String konto5, boolean spesen, String erwSt) {
		Buchungssatz bs;
		
		String kontos[] = {konto4, konto5};
		Double prices[] = {skonto_Brutto};
		
		bs = myController.initpaintUpTo7(kontos, prices, leftMore);
		
		if(erwSt == null) {
			String kontos2[] = {"3510", "2510"};
			Double prices2[] = {((Controller_Ausland) myController).initCalculateErwerbsteuerbetrag(Double.toString(skonto_Brutto))};
			
			bs = myController.initpaintUpTo7(kontos2, prices2, leftMore);
		}
		
		return bs;
		
	}
	
	
	private void spesenException(double price, String konto1) {
		double spesenPreis = MainModel.parseDouble(spField.getText());
		double newPrice = 0;
		if(konto1.equals("2800")) {
			newPrice = price - spesenPreis;
			String kontos[] = {konto1, finalZahlungskonto, konto3};
			Double prices[] = {newPrice, price, spesenPreis};
			
			myController.initpaintUpTo7(kontos, prices, leftMore);
		}
			
			else {
				newPrice = price + spesenPreis;
				String kontos[] = {konto1, finalZahlungskonto, konto3};
				Double prices[] = {price, newPrice, spesenPreis};
				
				myController.initpaintUpTo7(kontos, prices, leftMore);
		}
	}
	
	private void skontoException(double oldPrice, String percent) {
		double skPercent = MainModel.parseDouble(skField.getText());
		
		skonto_Brutto = MainModel.roundDouble_giveBack_Double((oldPrice / 100) * skPercent);
		this.newPrice = MainModel.roundDouble_giveBack_Double((oldPrice / 100) * (100 - skPercent));
		double percent_double = MainModel.parseDouble(percent);
		skonto_20Percent = MainModel.roundDouble_giveBack_Double((skonto_Brutto / 100) * percent_double); //Erwerbssteuer Skonto
	}
	
}
