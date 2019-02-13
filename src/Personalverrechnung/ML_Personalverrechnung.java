package Personalverrechnung;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import View.MainController;
import View.MainView;

public class ML_Personalverrechnung implements MouseListener{

	

	private Controller_Personalverrechnung myController;
	
	
	public ML_Personalverrechnung(MainController mainController) {
		myController = new Controller_Personalverrechnung(mainController);
		mainController.getControllerList().addLast(myController);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	String[] FULLAW = {"2800", "2700", "3300", "3190 (Bankomatkarte)", "3180 (Kreditkarte)"};
	String[] FIXEDAW = {"6200"};
		
		
	String cmd = ((JLabel) e.getSource()).getText();
	
	int index = cmd.indexOf(':');
	if(index != -1)
		cmd = cmd.substring(0, index-3);
	
	
	if(cmd.equals("~  Gehaltsbuchungen")) {
		myController.exec__4_Stufig_Fixed_View(cmd, "3600", FIXEDAW, "3540", "3850", null, null, true, null, false);
	}
	
	else if(cmd.equals("~  Geschäftsessen")) {
		myController.exec_Geschäftsessen_View(cmd, null, FULLAW, "5080", "20", true, true);
	}
	
	else if(cmd.equals("~  Fahrtkosten")) {
		myController.exec_Fahrtkosten_View(cmd, null, FULLAW, "2500", "10", false, true);
	}
	
	else if(cmd.equals("~  Kilometergeld Unternehmer")) {
		myController.exec_Kilometergeld_Unternehmer_View(cmd, "7345", FULLAW, null, null, false, true);
	}
	
	else if(cmd.equals("~  Tagesgeld")) {
		myController.exec_Tagesgeld_View(cmd, "7360", FULLAW, "2500", "10", false, true);
	}
	
	else if(cmd.equals("~  Nächtigungsgeld")) {
		myController.exec_2Stufig_Fixed_View(cmd, "7364", FULLAW, "2500", "13", false, true);
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
