package Anlagenbewertung;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import View.MainModel;
import View.MainView;
import extraViews.View_SuperClass;

public class Abschreibung_View extends View_SuperClass{

	private JTextField txtAnlage;
	private JTextField txtND;
	private JTextField txtIBN_Monat;
	private JTextField txtIBN_Year;
	
	private String finalAnlKonto;
	private JCheckBox firstTwo;
	private JCheckBox lastTwo;
	private JCheckBox allYears;
	
	private double AW = 0;
	
	public boolean warningActivated = false;
	
	
	
	private LinkedList<JCheckBox> llCBses = new LinkedList<JCheckBox>();
	
	@Override
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		
		if(getSelectedCB() == 0) {
			
			warningActivated = true;
			return;
			
		}

		makeKonto1(Konto1);
		makeAnlKonto();
		makeND();
		make_Monat_IBN();
		make_Year_IBN();
		makeCheckBoxes();
		
	}
	
	private void makeAnlKonto() {
		txtAnlage = new JTextField("Anlagenkonto");
		txtAnlage.setFont(MainView.font_18);
		txtAnlage.setBounds(300, 195, 200, 26);
		contentPane.add(txtAnlage);
		txtAnlage.setColumns(10);
	}
	
	private void makeND() {
		txtND = new JTextField("Nutzungsdauer");
		View_SuperClass.txtFieldDesign(txtND);
		txtND.setBounds(850, 195, 210, 40);
		contentPane.add(txtND);
		txtND.setColumns(10);
	}
	
	private void make_Monat_IBN() {
		txtIBN_Monat = new JTextField("Monat Inbetriebnahme");
		View_SuperClass.txtFieldDesign(txtIBN_Monat);
		txtIBN_Monat.setBounds(850, 235+5, 210, 40);
		contentPane.add(txtIBN_Monat);
		txtIBN_Monat.setColumns(10);
	}
	
	private void make_Year_IBN() {
		txtIBN_Year = new JTextField("Jahr Inbetriebnahme");
		View_SuperClass.txtFieldDesign(txtIBN_Year);
		txtIBN_Year.setBounds(1070, 235+5, 200, 40);
		contentPane.add(txtIBN_Year);
		txtIBN_Year.setColumns(10);
	}
	
	private void makeCheckBoxes() {
		firstTwo = new JCheckBox("erste zwei Jahre");
		View_SuperClass.checkBoxDesign(firstTwo);
		firstTwo.setBounds(611, 195, 200, 25);
		firstTwo.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				firstTwo.setForeground(new Color(0, 117, 211));
			}

				});
		contentPane.add(firstTwo);
		
		lastTwo = new JCheckBox("letzte zwei Jahre");
		View_SuperClass.checkBoxDesign(lastTwo);
		lastTwo.setBounds(611, 235, 200, 25);
		lastTwo.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				lastTwo.setForeground(new Color(0, 117, 211));
			}

				});
		contentPane.add(lastTwo);
		
		allYears = new JCheckBox("alle Jahre");
		View_SuperClass.checkBoxDesign(allYears);
		allYears.setBounds(611, 235 + (235-195), 200, 25);
		allYears.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				allYears.setForeground(new Color(0, 117, 211));
			}

				});
		contentPane.add(allYears);
		
		llCBses.add(firstTwo);
		llCBses.add(lastTwo);
		llCBses.add(allYears);
	}
	
	

	@Override
	public void setUpRoutine(String Konto1, String[] Konten2, String Konto3, boolean fixed, String percent) {

		finalAnlKonto = txtAnlage.getText();
		
		for(int x = 0; x < llCBses.size(); x++) {
			if(llCBses.get(x).isSelected()) {
				((Controller_Anlagenbewertung) myController).initCalcAbschreibung(finalAnlKonto, txtIBN_Monat.getText(), txtIBN_Year.getText(), txtND.getText(), AW, llCBses.get(x).getText(), leftMore);
				break;
			}
		}
		
			
		AW = 0;

	}
	
	private int getSelectedCB() {
		
		MainModel.refreshAllBSLists();
		
		for(int x = 0; x < MainView.bsList.size(); x++) {
			
			if(MainView.bsList.get(x).getRadioButtonStatus())
				AW += MainModel.parseDouble(MainView.bsList.get(x).getNettoPrice());
			
		}
		
		if(AW == 0) {
			
			MainView.warning_CheckCBoxes();
			return 0;
			
		}
		
		return 1;
	}
	
}
