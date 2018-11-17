package Ausland;

import View.Controller_AbstractClass;
import View.MainController;
import extraViews.View_SuperClass;

public class Controller_Ausland extends Controller_AbstractClass{

	public Controller_Ausland(MainController MC) {
		super(MC);
	}
	
	public IG_Erwerb_Kauf_View exec_IG_Erwerb_Kauf_View(String cmd, String konto1, String[] kontoSelection, String konto3, String konto4, String konto5, String percent, int[] coordinates, boolean fixed, String extra, boolean swap) {
		IG_Erwerb_Kauf_View view = new IG_Erwerb_Kauf_View();
		if(swap)
			View_SuperClass.swapLeft_RightWP();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, konto4, konto5, percent, coordinates, fixed, extra, this);
		view.setVisible(true);
		return view;
	}

	public void exec_Ausland_Ausgleich(String cmd, String konto1, String[] kontoSelection, String konto3, String konto4, String konto5, String percent, int[] coordinates, boolean fixed, String extra) {
		Ausland_Ausgleich view = new Ausland_Ausgleich();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, konto4, konto5, percent, coordinates, fixed, extra, this);
		view.setVisible(true);
	}
	
	public void exec_Kursdifferenz_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed) {
		Kursdifferenz_View view = new Kursdifferenz_View();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this);
		view.setVisible(true);	
	}
	
	
	

	public String initExecuteFWRoutine(String price, String satz) {
		return MC.execExecuteFWRoutine(price, satz);
	}

	public void initCalculateErwerbsteuerbetrag_andPaintIt(String price) {
		MC.execCalculateErwerbsteuerbetrag_andPaintIt(price);
	}
	
	
}
