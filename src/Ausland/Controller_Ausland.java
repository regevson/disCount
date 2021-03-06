package Ausland;

import View.Controller_AbstractClass;
import View.MainController;

public class Controller_Ausland extends Controller_AbstractClass{

	public Controller_Ausland(MainController MC) {
		super(MC);
	}
	
	public IG_Erwerb_Kauf_View exec_IG_Erwerb_Kauf_View(String cmd, String konto1, String[] kontoSelection, String konto3, String konto4, String konto5, String percent, boolean fixed, String extra, boolean leftMore) {
		IG_Erwerb_Kauf_View view = new IG_Erwerb_Kauf_View();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, konto4, konto5, percent, fixed, extra, this, leftMore);
		return view;
	}

	public void exec_Ausland_Ausgleich(String cmd, String konto1, String[] kontoSelection, String konto3, String konto4, String konto5, String percent, boolean fixed, String extra, boolean leftMore) {
		Ausland_Ausgleich view = new Ausland_Ausgleich();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, konto4, konto5, percent, fixed, extra, this, leftMore);
	}
	
	public void exec_Kursdifferenz_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, boolean fixed, boolean leftMore) {
		Kursdifferenz_View view = new Kursdifferenz_View();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, fixed, this, leftMore);
	}
	
	
	

	public String initExecuteFWRoutine(String price, String satz) {
		return MC.execExecuteFWRoutine(price, satz);
	}

	public Double initCalculateErwerbsteuerbetrag(String price) {
		return MC.execCalculateErwerbsteuerbetrag(price);
	}
	
	
}
