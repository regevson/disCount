package Rechnungsausgleich;

import View.Controller_AbstractClass;
import View.MainController;
import extraViews.View_SuperClass;
import extraViews._2Stufig_VariableKonto;

public class Controller_Rechnungsausgleich extends Controller_AbstractClass{

	public Controller_Rechnungsausgleich(MainController MC) {
		super(MC);
	}
	
	public void exec_Skonto_1_2_Stufig(String cmd, String konto1, String[] kontoSelection, String konto3, String konto4, String konto5, String percent, int[] coordinates, boolean fixed, String extra, boolean swap) {		
		Skonto_1_2_Stufig view = new Skonto_1_2_Stufig();
		if(swap)
			View_SuperClass.swapLeft_RightWP();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, konto4, konto5, percent, coordinates, fixed, extra, this);
		view.setVisible(true);	
	}
	
	public void exec_Ausgleich_Kreditkarten_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed) {		
		Ausgleich_Kreditkarten_View view = new Ausgleich_Kreditkarten_View();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this);
		view.setVisible(true);	
	}
	
	public void exec_BuchBankKonto_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed) {		
		BuchBankKonto_View view = new BuchBankKonto_View();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this);
		view.setVisible(true);	
	}
	
	public void initCalcNettoKreditkarten(String bruttoPrice_str, String spesen_str) {
		MC.execCalcNettoKreditkarten(bruttoPrice_str, spesen_str);
	}
	
	public void initCreateKontos1stufig_Skonto(String kontoLinks, String kontoRechts) {
		MC.execCreateKontos1stufig_Skonto(kontoLinks, kontoRechts);
	}
	
	public String initNetto_to_newBrutto_Skonto(String price, String skPercent) {
		return MC.execNetto_to_newBrutto_Skonto(price, skPercent);
	}
	
	public String initBrutto_to_newBrutto_Skonto(String price, String skPercent) {
		return MC.execBrutto_to_newBrutto_Skonto(price, skPercent);
	}
	
	public String initBrutto_to_skBrutto_Skonto(String price, String skPercent) {
		return MC.execBrutto_to_skBrutto_Skonto(price, skPercent);
	}

}
