package Rechnungsausgleich;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import View.MainController;
import View.MainView;

public class ML_Rechnungsausgleich implements MouseListener{
	

	Controller_Rechnungsausgleich myController;




	public ML_Rechnungsausgleich(MainController mainController) {
		myController = new Controller_Rechnungsausgleich(mainController);
		mainController.getControllerList().addLast(myController);
	}




	@Override
	public void mousePressed(MouseEvent e) {
		
		String[] VAG = {"2800"};
		
		String[] _2000 = {"2000"};
		
		String[] RAB = {"3300"};
		
		String[] _2790 = {"2790"};
		
		
		
		int[] leftmore2Stufig = {150, 250, 170, 210};
		
		int[] leftmore2Stufig_X_SK = {250, 150, 170, 170};
		
		int[] leftmore1Stufig_X = {250, 150, 170, 210};
		
		String cmd = ((JLabel) e.getSource()).getText();
		
		int index = cmd.indexOf(':');
		if(index != -1)
			cmd = cmd.substring(0, index-3);
		
		
		
		if(cmd.equals("~  allgemeiner Verbindlichkeitenausgleich")) {
			myController.exec_1Stufig_ohneSteuer(cmd, "3300", VAG, null, null, leftmore2Stufig, true, false);
		}
		
		
		else if(cmd.equals("~  allgemeiner Forderungskeitenausgleich")) {
			myController.exec_1Stufig_ohneSteuer(cmd, "2000", VAG, null, null, leftmore1Stufig_X, true, true);
		}
		
		else if(cmd.equals("~  Wir zahlen Mahnspesen")) {
			myController.exec_1Stufig_ohneSteuer(cmd, "8301", RAB, null, null, leftmore2Stufig, true, false);
		}
		
		else if(cmd.equals("~  Wir verlangen Mahnspesen")) {
			myController.exec_1Stufig_ohneSteuer(cmd, "4890", _2000, null, null, leftmore1Stufig_X, true, true);
		}
		
		
		else if(cmd.equals("~  Wir zahlen Verzugszinsen")) {
			myController.exec_1Stufig_ohneSteuer(cmd, "8300", RAB, null, null, leftmore2Stufig, true, false);
		}
		
		
		else if(cmd.equals("~  Wir verlangen Verzugszinsen")) {
			myController.exec_1Stufig_ohneSteuer(cmd, "8130", _2000, null, null, leftmore1Stufig_X, true, true);
		}
		
		else if(cmd.equals("~  Kundenskonto")) {
			((Controller_Rechnungsausgleich) myController).exec_Skonto_1_2_Stufig(cmd, "2800", _2000, "4410", "2000", "3500", "20", leftmore2Stufig, true, null, false);
		}
		
		else if(cmd.equals("~  Lieferantenskonto")) {
			((Controller_Rechnungsausgleich) myController).exec_Skonto_1_2_Stufig(cmd, "2800", RAB, "5880", "3300", "2500", "20", leftmore2Stufig_X_SK, true, null, true);
		}
		
		
		else if(cmd.equals("~  Überweisung der Kreditkartengesellschaft")) {
			((Controller_Rechnungsausgleich) myController).exec_Ausgleich_Kreditkarten_View(cmd, "2800", _2790, "7792", "2500", leftmore2Stufig, true);
		}
		
		else if(cmd.equals("~  Abbuchung der Bankomatkartengesellschaft")) {
			myController.exec_2Stufig_Fixed_View(cmd, "7792", VAG, "2500", "20", leftmore2Stufig, true, false);
		}
		
		
		else if(cmd.equals("~  Buchungen mit Bankkonto")) {
			((Controller_Rechnungsausgleich) myController).exec_BuchBankKonto_View(cmd, "2000", VAG, null, "20", leftmore1Stufig_X, true);
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
