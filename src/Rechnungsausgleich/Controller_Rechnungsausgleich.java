package Rechnungsausgleich;

import View.Controller_AbstractClass;
import View.MainController;
import extraViews.View_SuperClass;
import extraViews._2Stufig_VariableKonto;

public class Controller_Rechnungsausgleich extends Controller_AbstractClass{

	public Controller_Rechnungsausgleich(MainController MC) {
		super(MC);
	}
	
	public void exec_Skonto_1_2_Stufig(String cmd, String konto1, String[] kontoSelection, String konto3, String konto4, String konto5, String percent, int[] coordinates, boolean fixed, String extra, boolean leftMore) {		
		Skonto_1_2_Stufig view = new Skonto_1_2_Stufig();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, konto4, konto5, percent, coordinates, fixed, extra, this, leftMore);
		view.setVisible(true);	
	}
	
	public void exec_Ausgleich_Kreditkarten_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed) {		
		Ausgleich_Kreditkarten_View view = new Ausgleich_Kreditkarten_View();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this, true);
		view.setVisible(true);	
	}
	
	public void exec_BuchBankKonto_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed) {		
		BuchBankKonto_View view = new BuchBankKonto_View();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this, true);
		view.setVisible(true);	
	}
	
	public Double[] initCalcNettoKreditkarten(String bruttoPrice_str, String spesen_str) {
		return MC.execCalcNettoKreditkarten(bruttoPrice_str, spesen_str);
	}
	
	public Double initNettoToSkontoBrutto(String price, String skPercent) {
		return MC.execNettoToSkontoBrutto(price, skPercent);
	}
	
	public Double initBruttoToSkontoBrutto(String price, String skPercent) {
		return MC.execBruttoToSkontoBrutto(price, skPercent);
	}

}
