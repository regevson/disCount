package Personalverrechnung;

import View.Controller_AbstractClass;
import View.MainController;
import extraViews.View_SuperClass;

public class Controller_Personalverrechnung extends Controller_AbstractClass{

	public Controller_Personalverrechnung(MainController MC) {
		super(MC);
	}
	
	
	public void exec__4_Stufig_Fixed_View(String cmd, String konto1, String[] kontoSelection, String konto3, String konto4, String konto5, String percent, int[] coordinates, boolean fixed, String extra, boolean swap) {
		_4_Stufig_Fixed_View view = new _4_Stufig_Fixed_View();
		view.changeBounds(1300, 470);
		if(swap)
			View_SuperClass.swapLeft_RightWP();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, konto4, konto5, percent, coordinates, fixed, extra, this);
		view.setVisible(true);
	}
	
	public void exec_Geschäftsessen_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed) {
		Geschäftsessen_View view = new Geschäftsessen_View();
		view.changeBounds(1000, 470);
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this);
		view.setVisible(true);
	}
	
	public void exec_Fahrtkosten_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed) {
		Fahrtkosten_View view = new Fahrtkosten_View();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this);
		view.setVisible(true);
	}
	
	public void exec_Kilometergeld_Unternehmer_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed) {
		Kilometergeld_Unternehmer_View view = new Kilometergeld_Unternehmer_View();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this);
		view.setVisible(true);
	}
	
	public void exec_Tagesgeld_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed) {
		Tagesgeld_View view = new Tagesgeld_View();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this);
		view.setVisible(true);
	}



}
