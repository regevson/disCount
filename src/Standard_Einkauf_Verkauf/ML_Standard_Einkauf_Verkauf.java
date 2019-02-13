package Standard_Einkauf_Verkauf;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import View.MainController;
import View.MainView;

public class ML_Standard_Einkauf_Verkauf implements MouseListener{
	
	private Controller_Standard_Einkauf_Verkauf myController;
	

	public ML_Standard_Einkauf_Verkauf(MainController mainController) {
		myController = new Controller_Standard_Einkauf_Verkauf(mainController);
		mainController.getControllerList().addLast(myController);
	}

	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		String[] FULLAW = {"2800", "2700", "3300", "3190 (BK)", "3180 (KK)"};
		
		String[] FULLET = {"2800", "2700", "2000", "2794 (BK)", "2790 (KK)"};
		
		String[] VAG = {"2800"};
		
		String[] RAB = {"3300"};
		
		String[] _2000 = {"2000"};
		
		
		
		
		String cmd = ((JLabel) e.getSource()).getText();
		
		int index = cmd.indexOf(':');
		if(index != -1)
			cmd = cmd.substring(0, index-3);

		
		
		
		if(cmd.equals("~  Handelswareneinkauf")) {
			myController.exec_2Stufig_Fixed_View(cmd, "5010", FULLAW, "2500", "20", false, true);
		}
		
		else if(cmd.equals("~  Handelswarenverkauf")) {
			myController.exec_2Stufig_Fixed_View(cmd, "4000", FULLET, "3500", "20", false, false);
		}
		
		
		else if(cmd.equals("~  sonstiger Aufwand (20%)")) {
			myController.exec_2Stufig_VariableKonto(cmd, null, FULLAW, "2500", "20", false, true);		
		}
		
		
		else if(cmd.equals("~  Wir gewähren nachtr. Rabatt")) {
			myController.exec_2Stufig_Fixed_NTRabatt_View(cmd, "4400", _2000, "3500", "20", true, true);
		}
		
		
		else if(cmd.equals("~  Lieferant gewährt nachtr. Rabatt")) {
			myController.exec_2Stufig_Fixed_NTRabatt_View(cmd, "5010", RAB, "2500", "20", true, false);
		}
		
		
		else if(cmd.equals("~  Wir senden Waren zurück")) {
			myController.exec_2Stufig_Fixed_View(cmd, "5010", FULLAW, "2500", "20", false, false);
		}
		
		
		else if(cmd.equals("~  Kunde sendet Waren zurück")) {
			myController.exec_2Stufig_Fixed_View(cmd, "4000", FULLET, "3500", "20", false, true);
		}
		
		
		else if(cmd.equals("~  Wir lassen uns die Waren liefern")) {
			myController.exec_2Stufig_VariableKonto(cmd, null, FULLAW, "2500", "20", false, true);	
		}
		
		
		else if(cmd.equals("~  Wir senden dem Kunden die Waren")) {
			myController.exec_2Stufig_Fixed_View(cmd, "7300", FULLAW, "2500", "20", false, true);
		}
		
		
	}
	
	
	
	
	

	


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		MainController.tellViewToSetCusor(MainView.handCursor());

	}
		
	@Override
	public void mouseExited(MouseEvent e) {
		MainController.tellViewToSetCusor(MainView.normalCursor());
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
