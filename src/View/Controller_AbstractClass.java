package View;

import javax.swing.JPanel;

import extraViews._1Stufig_ohneSteuer;
import extraViews._2Stufig_Fixed_NTRabatt_View;
import extraViews._2Stufig_Fixed_View;
import extraViews._2Stufig_VariableKonto;

public abstract class Controller_AbstractClass {
	
	protected MainController MC;
	
	public Controller_AbstractClass(MainController MC) {
		this.MC = MC;
	}
	
	
	public void exec_2Stufig_Fixed_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed, boolean swap) {
		_2Stufig_Fixed_View view = new _2Stufig_Fixed_View();
		if(swap)
			view.swapLeft_RightWP();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this);
		view.setVisible(true);	
	}
	

	public void exec_2Stufig_VariableKonto(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed) {		
		_2Stufig_VariableKonto view = new _2Stufig_VariableKonto();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this);
		view.setVisible(true);	
	}
	
	
	public void exec_2Stufig_Fixed_NTRabatt_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed, boolean swap) {
		_2Stufig_Fixed_NTRabatt_View view = new _2Stufig_Fixed_NTRabatt_View();
		if(swap)
			view.swapLeft_RightWP();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this);
		view.setVisible(true);	
	}
	
	public void exec_1Stufig_ohneSteuer(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, int[] coordinates, boolean fixed, boolean swap) {
		_1Stufig_ohneSteuer view = new _1Stufig_ohneSteuer();
		if(swap)
			view.swapLeft_RightWP();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, coordinates, fixed, this);
		view.setVisible(true);	
	}


	public void initPaint2Konten(String konto1, String konto2) {
		MC.execPaint2Konten(konto1, konto2);
	}
	
	public void initNetto_to_paintBrutto(String percent, String price) {
		MC.execNetto_to_paintBrutto(percent, price);
	}


	public void initBrutto_to_paintBrutto(String price) {
		MC.execBrutto_to_paintBrutto(price);
	}


	public JPanel initPaint3Konten(String konto1, String konto2, String konto3) {
		return MC.execPaint3Konten(konto1, konto2, konto3);
	}
	
	public void initPaint3Prices(Double price1, Double price2, Double price3) {
		MC.execPaint3Prices(price1, price2, price3);
	}
	
	public void initPaint4Konten(String konto1, String konto2, String konto3, String konto4) {
		MC.execPaint4Konten(konto1, konto2, konto3, konto4);
	}
	
	public void initPaint4Prices(Double price1, Double price2, Double price3, Double price4) {
		MC.execPaint4Prices(price1, price2, price3, price4);
	}

	public void initNetto_to_paintAllRabatt(String percent, String price, String rabattPercentarge) {
		MC.execNetto_to_paintAllRabatt(percent, price, rabattPercentarge);
	}


	public void initBrutto_to_paintAllRabatt(String percent, String price, String rabattPercentarge) {
		MC.execBrutto_to_paintAllRabatt(percent, price, rabattPercentarge);
	}


	public void initNetto_to_paintAll3(String percent, String price) {
		MC.execNetto_to_paintAll3(percent, price);
	}


	public String initBrutto_to_paintAll3(String percent, String price) {
		return MC.execBrutto_to_paintAll3(percent, price);
	}
	
	public void initNetto_to_paintAll4(String percent, String price1, String price2) {
		MC.execNetto_to_paintAll4(percent, price1, price2);
	}


	public void initBrutto_to_paintAll4(String percent, String price1, String price2) {
		MC.execBrutto_to_paintAll4(percent, price1, price2);
	}
	
	public void initPaint1Price(double price) {
		MC.execPaint1Price(price);
	}

	public JPanel initPaint2Konten_mitzyk(String konto4, String konto5) {
		return MC.execPaint2Konten_mitzyk(konto4, konto5);
	}

	public double initCalcGehaltsPercent_andPrintIt(String ausgangsPreis_str, String percent_str) {
		return MC.execCalcGehaltsPercent_andPrintIt(ausgangsPreis_str, percent_str);
	}



	

}
