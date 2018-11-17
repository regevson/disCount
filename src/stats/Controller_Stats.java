package stats;

import View.Controller_AbstractClass;
import View.MainController;

public class Controller_Stats extends Controller_AbstractClass{

	public Controller_Stats(MainController MC) {
		super(MC);
	}

	public double[][] initGetGraphInfo() {
		return MC.execGetGraphInfo();
	}
}
