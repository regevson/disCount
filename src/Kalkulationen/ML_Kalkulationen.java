package Kalkulationen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import View.MainController;
import View.MainView;

public class ML_Kalkulationen implements MouseListener{
	
	Controller_Kalkulationen myController;

	public ML_Kalkulationen(MainController mainController) {
		myController = new Controller_Kalkulationen(mainController);
		mainController.getControllerList().addLast(myController);	
	}





	@Override
	public void mousePressed(MouseEvent e) {
	
		String cmd = ((JLabel) e.getSource()).getText();
		
		int index = cmd.indexOf(':');
		if(index != -1)
			cmd = cmd.substring(0, index-3);
		
		
		if(cmd.equals("~  Bezugskalkulation")) {
			myController.exec_Bezugskalkulation_View();
		}
		
		else if(cmd.equals("~  Personalverrechnung")) {
			myController.exec_Personalverrechnung_View();
		}
		
		else if(cmd.equals("~  Absatzkalkulation")) {
			myController.exec_Absatzkalkulation_View();
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
