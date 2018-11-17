package Anlagenbewertung;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import View.MainController;
import View.MainView;

public class ML_Anlagenbewertung implements MouseListener{
	
	Controller_Anlagenbewertung myController;

	String[] FULLAW = {"2800", "2700", "3300", "3190 (Bankomatkarte)", "3180 (Kreditkarte)"};
	String[] FIXEDAW = {"6200"};
	String[] FIXEDET = {"2000"};
	
	String[] _1STOS = {"4015"};
	
	String[] VAG = {"2800"};
	
	String[] ANLIB = {"0300", "0400"};
	
	int[] leftmore2Stufig = {150, 250, 170, 210};
	int[] leftmore2Stufig_X = {400, 100, 170, 210};
	
	int[] IGLAGCoo = {150, 250, 170, 200};
	

	int[] bigGB = {250, 150, 170, 210};
	
	int[] leftmore1Stufig_X = {380, 100, 170, 210};
	
	
	
	
	
	public ML_Anlagenbewertung(MainController mainController) {
		myController = new Controller_Anlagenbewertung(mainController);
		mainController.getControllerList().addLast(myController);	
	}
	
	

	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	String cmd = ((JLabel) e.getSource()).getText();
	
	
	
	if(cmd.contains("~  Teilzahlung")) {
		myController.exec_2Stufig_Fixed_View(cmd, "0710", FULLAW, "2500", "20", leftmore2Stufig, false, false);
	}
	

	else if(cmd.contains("~  Anlagen in Bau Umbuchung")) {
		myController.exec_1Stufig_ohneSteuer(cmd, "0710", ANLIB, null, "20", leftmore1Stufig_X, false, true);
	}
	
	
	else if(cmd.contains("~  Instandhaltung")) {
		myController.exec_2Stufig_Fixed_View(cmd, "7200", FULLAW, "2500", "20", leftmore2Stufig, false, false);
	}
	
	
	else if(cmd.contains("~  Anlagenaktivierung")) {
		myController.exec_2Stufig_VariableKonto(cmd, null, FULLAW, "2500", "20", leftmore2Stufig, false);
	}
	
	
	else if(cmd.contains("~  Anlagenausscheidung durch Verkauf")) {
		myController.exec_Anlagenausscheidung_durchVerkauf(cmd, "4600", FULLAW, "3500", null, null, "20", leftmore2Stufig_X, false, null);
	}
	
}
	

	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		MainController.tellViewToSetCusor(MainView.handCursor());
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		MainController.tellViewToSetCusor(MainView.normalCursor());
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	public void makeAbschreibung() {
		System.out.println("Abschreibung");
		myController.exec_Abschreibung("Abschreibung", "7010", FIXEDAW, null, null, leftmore2Stufig, true);
	}
	
	
	public void aufgabeAuswerten(String aufgabe) {
		myController.exec_Erweiterung_Gebäude_View(aufgabe, 400, 800);
	}
	
}
