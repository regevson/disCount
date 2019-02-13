package Tourismus;

import View.Controller_AbstractClass;
import View.MainController;
import extraViews._3Stufig_Variable_View;

public class Controller_Tourismus extends Controller_AbstractClass{

	public Controller_Tourismus(MainController MC) {
		super(MC);
	}
	
	

	public void exec_3Stufig_Variable_View(String cmd, String konto1, String[] kontoSelection, String konto3, String steuer, boolean fixed, boolean leftMore) {
		_3Stufig_Variable_View view = new _3Stufig_Variable_View();
		view.setUpBasicStuff(cmd, konto1, kontoSelection, konto3, steuer, fixed, this, leftMore);
	}

}
