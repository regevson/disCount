package Anlagenbewertung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JTextField;

import View.MainModel;
import extraViews.View_SuperClass;

public class Erweiterung_Gebäude_View extends View_SuperClass{

	private JTextField txtAnlage;
	private JTextField txtAW;
	private JTextField txtRND;
	private JTextField IBN_Monat;
	private JTextField buchWert;
	
	public static LinkedList<String> llAufgabe;
	

	private String[] replaceObj = {".", ";", ",", "€", "-", "%"};
	private String[] replaceOb_Komma = {".", ";", "€", "-", "%"};

	
	
	
	private JTextField txtNB;
	
	private int y = 50;
	private double AW;
	private int nbMonth;
	private double BW;
	private String RND;
	private ArrayList<Double> solutions;
	private ArrayList<JTextField> alJTFs;


	
	public void readAufgabe(String aufgabe) {
		String[] aufgabe_arr = aufgabe.split(" ");
		llAufgabe = new LinkedList<String>(Arrays.asList(aufgabe_arr));
			
		MainModel.setllAufgabe(llAufgabe);
		
		makeAWGUI();
		makeNB();
		makeRND();
		createBuchWert();
		getPrices();
		
	}
	
	private void makeAWGUI() {
		AW = MainModel.getnext€("AW", replaceObj, false);
		
		txtAW = new JTextField("Anschaffungswert:   " + Double.toString(AW));
		View_SuperClass.txtFieldDesign(txtAW);
		txtAW.setBounds(20, y, 300, 40);
		txtAW.setEditable(false);
		contentPane.add(txtAW);
		txtAW.setColumns(10);
		
		y = y + 50;
	}
	
	private void makeNB() {
		int indexNB = llAufgabe.indexOf("Nutzungsbeginn");
		if(indexNB == -1)
			indexNB = llAufgabe.indexOf("Betrieb") - 5;
		
		nbMonth = MainModel.getNearMonth(indexNB);
		
		txtNB = new JTextField("Nutzungsbeginn:   " + Integer.toString(nbMonth));
		View_SuperClass.txtFieldDesign(txtNB);
		txtNB.setBounds(20, y, 300, 40);
		txtNB.setEditable(false);
		contentPane.add(txtNB);
		txtNB.setColumns(10);
		
		y = y + 50;
	}
	

	
	private void makeRND() {
		RND = MainModel.getString_nearOtherString("Restnutzungsdauer", "Jahre", replaceOb_Komma, 0);
		
		txtRND = new JTextField("Restnutzungsdauer:   " + RND);
		View_SuperClass.txtFieldDesign(txtRND);
		txtRND.setBounds(20, y, 300, 40);
		txtRND.setEditable(false);
		contentPane.add(txtRND);
		txtRND.setColumns(10);
		
		y = y + 50;
	}
	

	
	
	public void createBuchWert() {
		BW = MainModel.getnext€("BW", replaceObj, false);
		
		buchWert = new JTextField("Buchwert:   " + Double.toString(BW));
		View_SuperClass.txtFieldDesign(buchWert);
		buchWert.setBounds(20, y + 23, 300, 42);
		contentPane.add(buchWert);
		buchWert.setColumns(10);
		
		y = y + 50;
	}
	
	private void getPrices() {
		
		solutions = new ArrayList<Double>();
		
		solutions = MainModel.erweiterungGeb_abrechnung_ausgleich(replaceObj);
		System.out.println(solutions);
		
		alJTFs = new ArrayList<JTextField>();
		
		for(int x = 0; x < solutions.size(); x++) {
			JTextField field = new JTextField();
			View_SuperClass.txtFieldDesign(field);
			field.setBounds(20, y, 300, 42);
			field.setEditable(false);
			contentPane.add(field);
			field.setColumns(10);
			
			y = y + 50;
			
			alJTFs.add(field);
		}
		
		int y = 0;
		
		for(int x = 0; x < solutions.size(); x++) {
			alJTFs.get(y++).setText(Double.toString(solutions.get(x)));
		}
		
		System.out.println("prices");
	}
	
	
	public void printStuff() {
		int y = 0;
		
		int solutionsCounter = 0;
		System.out.println(MainModel.solutions_AUSG);
		System.out.println(solutions);
		
		for(int x = 0; x < MainModel.solutions_AUSG.size(); x++) {
			
			if(MainModel.solutions_AUSG.get(x) != 0.00) {
				
				myController.initPaint2Konten("3300", "2800");
				
				if(MainModel.solutions_AUSG.get(x) == -1.00)
					myController.initPaint1Price(MainModel.bruttoValuesFromAbrechnung.poll());
				
				else
					myController.initPaint1Price(MainModel.solutions_AUSG.get(x));
				
			}
			
			else {
				
				if(solutionsCounter < solutions.size()) {
					myController.initPaint3Konten(MainModel.abrAW_Konto.poll(), "3300", "2500");
					
					myController.initPaint3Prices(Double.parseDouble(alJTFs.get(y++).getText()), Double.parseDouble(alJTFs.get(y++).getText()), Double.parseDouble(alJTFs.get(y++).getText()));
					solutionsCounter++;
				}
				
			}
		}
		
			
		
		((Controller_Anlagenbewertung) myController).initCalcAbschreibung("0300", "2", "2017", "40", AW, "erstes Jahr");
		((Controller_Anlagenbewertung) myController).initCalcAbschreibung("0300", Integer.toString(nbMonth), "2017", RND, MainModel.biggestNetto, "erste zwei Jahre");
	}


	
}
