package View;

import extraViews._1Stufig_ohneSteuer;
import extraViews._2Stufig_Fixed_NTRabatt_View;
import extraViews._2Stufig_Fixed_View;
import extraViews._2Stufig_VariableKonto;

public abstract class Controller_AbstractClass {
	
	protected MainController MC;
	private boolean leftMore;
	
	public Controller_AbstractClass(MainController MC) {
		this.MC = MC;
	}
	
	
	public void exec_2Stufig_Fixed_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed, boolean leftMore) {
		this.leftMore = leftMore;
		_2Stufig_Fixed_View view = new _2Stufig_Fixed_View();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this, leftMore);
		view.setVisible(true);	
	}
	

	public void exec_2Stufig_VariableKonto(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed, boolean leftMore) {		
		_2Stufig_VariableKonto view = new _2Stufig_VariableKonto();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this, leftMore);
		view.setVisible(true);	
	}
	
	
	public void exec_2Stufig_Fixed_NTRabatt_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed, boolean leftMore) {
		_2Stufig_Fixed_NTRabatt_View view = new _2Stufig_Fixed_NTRabatt_View();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this, leftMore);
		view.setVisible(true);	
	}
	
	public void exec_1Stufig_ohneSteuer(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed, boolean leftMore) {
		_1Stufig_ohneSteuer view = new _1Stufig_ohneSteuer();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this, leftMore);
		view.setVisible(true);	
	}

	

	public Double[] initCalculateRabattPricesFromNetto(String percent, String price, String rabattPercentarge) {
		return MC.execCalculateRabattPricesFromNetto(percent, price, rabattPercentarge);
	}

	public double initCalcGehaltsPercent(String ausgangsPreis_str, String percent_str) {
		return MC.execCalcGehaltsPercent(ausgangsPreis_str, percent_str);
	}

	public Double initNettoToBrutto(String price, String percent) {
		return MC.execNettoToBrutto(price, percent);
	}
	
	public Double[] initNettoToBrutto(String nettoPrice, String otherPrice, String percent) {
		return MC.execNettoToBrutto(nettoPrice, otherPrice, percent);
	}
	
	public Double[] initBruttoToNetto(String bruttoPrice, String otherPrice, String percent) {
		return MC.execBruttoToNetto(bruttoPrice, otherPrice, percent);
	}

	
	public Double initBruttoToNetto(String price, String percent) {
		return MC.execBruttoToNetto(price, percent);
	}


	public Double initCalcTagesgeld(String days, String start, String end, String food) {
		return MC.execCalcTagesgeld(days, start, end, food);
	}

	public Buchungssatz initpaintUpTo7(String kontos[], Double prices[], boolean leftMore) {
		return MC.execpaintUpTo7(kontos, prices, leftMore);
	}
	

}
