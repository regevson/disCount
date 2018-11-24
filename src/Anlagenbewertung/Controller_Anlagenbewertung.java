package Anlagenbewertung;

import View.Controller_AbstractClass;
import View.MainController;
import extraViews.View_SuperClass;

public class Controller_Anlagenbewertung extends Controller_AbstractClass{
	
	public Controller_Anlagenbewertung(MainController MC) {
		super(MC);
	}
	
	public void exec_Anlagenausscheidung_durchVerkauf(String cmd, String konto1, String[] kontoSelection, String konto3, String konto4, String konto5, String percent, int[] coordinates, boolean fixed, String extra, boolean leftMore) {
		Anlagenausscheidung_durchVerkauf view = new Anlagenausscheidung_durchVerkauf();
		view.changeBounds(1300, 470);
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, konto4, konto5, percent, coordinates, fixed, extra, this, leftMore);
		view.setVisible(true);
	}

	public void exec_Abschreibung(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed, boolean leftMore) {
		Abschreibung_View view = new Abschreibung_View();
		view.changeBounds(1300, 470);
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this, leftMore);
		view.setVisible(true);
		if(view.warningActivated)
			view.doADispose();	
	}

	public void exec_Erweiterung_Geb�ude_View(String aufgabe, int width, int height) {
		Erweiterung_Geb�ude_View view = new Erweiterung_Geb�ude_View();
		view.setUpBasicStuff(aufgabe, width, height);
	}
	
	public Double initCalcAbschreibung(String anlKonto, String IBN_Monat_str, String IBN_Year, String ND_str, Double AW, String command, boolean leftMore) {
		return MC.execCalcAbschreibung(anlKonto, IBN_Monat_str, IBN_Year, ND_str, AW, command, leftMore);
	}
	

}
