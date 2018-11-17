package Tourismus;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import View.MainController;
import View.MainView;

public class ML_Tourismus implements MouseListener{

	private Controller_Tourismus myController;
	
	
	public ML_Tourismus(MainController mainController) {
		myController = new Controller_Tourismus(mainController);
		mainController.getControllerList().addLast(myController);
	}


	@Override
	public void mousePressed(MouseEvent e) {
		String[] FULLAW = {"2800", "2700", "3300", "3190 (Bankomatkarte)", "3180 (Kreditkarte)"};
		
		int[] leftmore2Stufig = {150, 250, 170, 210};
		
		
		
		
		String cmd = ((JLabel) e.getSource()).getText();
		
		int index = cmd.indexOf(':');
		if(index != -1)
			cmd = cmd.substring(0, index-3);
	
	
		if(cmd.equals("~  Lebensmitteleinkauf")) {
			myController.exec_2Stufig_Fixed_View(cmd, "5100", FULLAW, "2500", "10", leftmore2Stufig, true, false);
		}
		
		else if(cmd.equals("~  Wareneinkauf mit Emballagen")) {
			myController.exec_3Stufig_Variable_View(cmd, null, FULLAW, "5080", "20", leftmore2Stufig, false);
		}
		
		else if(cmd.equals("~  Getränkeeinkauf")) {
			myController.exec_2Stufig_VariableKonto(cmd, null, FULLAW, "2500", "20", leftmore2Stufig, false);
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
