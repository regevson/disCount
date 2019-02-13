package Steuer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import View.MainController;
import View.MainView;

public class ML_Steuer implements MouseListener{




	private Controller_Steuer myController;
	
	
	public ML_Steuer(MainController mainController) {
		myController = new Controller_Steuer(mainController);
		mainController.getControllerList().addLast(myController);
	}




	@Override
	public void mousePressed(MouseEvent e) {
		
		String[] dirZahl = {"2800", "2700", "2810"};
		
		String[] VAG = {"2800"};
		
		
		String cmd = ((JLabel) e.getSource()).getText();
		
		int index = cmd.indexOf(':');
		if(index != -1)
			cmd = cmd.substring(0, index-3);
		
		
		
		if(cmd.equals("~  Einkommensteuer/KESt/Grundsteuer Privatgrundstücke")) {
			myController.exec_1Stufig_ohneSteuer(cmd, "9600", dirZahl, null, null, false, true);
		}
		
		
		else if(cmd.equals("~  USt-Zahllast")) {
			myController.exec_1Stufig_ohneSteuer(cmd, "3520", VAG, null, null, false, true);
		}
		
		else if(cmd.equals("~  Lohnsteuer")) {
			myController.exec_1Stufig_ohneSteuer(cmd, "3540", VAG, null, null, false, true);
		}
		
		else if(cmd.equals("~  Grunderwerbssteuer (unbebaut)")) {
			myController.exec_1Stufig_ohneSteuer(cmd, "0200", VAG, null, null, false, true);
		}
		
		else if(cmd.equals("~  Kammerumlage")) {
			myController.exec_1Stufig_ohneSteuer(cmd, "7780", VAG, null, null, false, true);
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
