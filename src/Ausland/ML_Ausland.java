package Ausland;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import View.MainController;
import View.MainView;

public class ML_Ausland implements MouseListener{
	
	Controller_Ausland myController;
	
	
	public ML_Ausland(MainController mainController) {
		myController = new Controller_Ausland(mainController);
		mainController.getControllerList().addLast(myController);	
	}







	@Override
	public void mousePressed(MouseEvent e) {
		
		String[] FULLAW = {"2800", "2700", "3300", "3190 (Bankomatkarte)", "3180 (Kreditkarte)"};
		String[] FIXEDAW = {"3300"};
		String[] FIXEDET = {"2000"};
		
		
		String[] VAG = {"2800"};
		
		
		int[] leftmore2Stufig = {150, 250, 170, 210};
		
		int[] IGLAGCoo = {150, 250, 170, 200};
		
		int[] KSKCoo = {150, 250, 170, 170};
		int[] LSKCoo = {250, 150, 170, 170};
	
		String cmd = ((JLabel) e.getSource()).getText();
		
		int index = cmd.indexOf(':');
		if(index != -1)
			cmd = cmd.substring(0, index-3);
		
	
		
		if(cmd.equals("~  IG Erwerb")) {
			myController.exec_IG_Erwerb_Kauf_View(cmd, "5010", FIXEDAW, "2510", "3510", null, "20", KSKCoo, true, null, false);
		}
		
		if(cmd.equals("~  IG Erwerb Bezugskosten")) {
			myController.exec_2Stufig_Fixed_View(cmd, "5010", FULLAW, "2500", "20", leftmore2Stufig, false, false);
		}
		
		if(cmd.equals("~  IG Erwerb Ausgleich")) {
			myController.exec_Ausland_Ausgleich(cmd, "3300", VAG, "7790", "3300", "5885", "20", IGLAGCoo, true, null);
		}
		
		
		if(cmd.equals("~  IG Lieferung")) {
			myController.exec_IG_Erwerb_Kauf_View(cmd, "4010", FIXEDET, null, null, null, "20", LSKCoo, false, null, true);
		}
		
		if(cmd.equals("~  IG Lieferung Versandkosten")) {
			myController.exec_2Stufig_Fixed_View(cmd, "7300", FULLAW, "2500", "20", leftmore2Stufig, false, false);
		}
		
		if(cmd.equals("~  IG Lieferung Ausgleich")) {
			myController.exec_Ausland_Ausgleich(cmd, "2800", FIXEDET, "7790", "4415", "2000", "20", IGLAGCoo, true, "no");
		}
		
		else if(cmd.equals("~  Import")) {
			myController.exec_IG_Erwerb_Kauf_View(cmd, "5010", FIXEDAW, "2510", "3510", null, "20", KSKCoo, false, null, false);
		}
		
		else if(cmd.equals("~  Import Bezugskosten")) {
			IG_Erwerb_Kauf_View view = myController.exec_IG_Erwerb_Kauf_View(cmd, "5010", FIXEDAW, null, null, null, "20", KSKCoo, false, null, false);
			view.fwCB.setVisible(false);
		}
		
		else if(cmd.equals("~  Import Ausgleich")) {
			myController.exec_Ausland_Ausgleich(cmd, "3300", VAG, "7790", "3300", "5886", "20", IGLAGCoo, true, "no");
		}
		
		else if(cmd.equals("~  Export")) {
			myController.exec_IG_Erwerb_Kauf_View(cmd, "4015", FIXEDET, null, null, null, "20", LSKCoo, false, null, true);
		}
		
		
		else if(cmd.equals("~  Export Bezugskosten")) {
			myController.exec_1Stufig_ohneSteuer(cmd, "7301", VAG, null, null, leftmore2Stufig, true, false);
		}
		
		
		else if(cmd.equals("~  Export Ausgleich")) {
			myController.exec_Ausland_Ausgleich(cmd, "2800", FIXEDET, "7790", "4416", "2000", "20", IGLAGCoo, true, "no");
		}
		
		
		else if(cmd.equals("~  Kursdifferenz")) {
			myController.exec_Kursdifferenz_View(cmd, null, null, null, null, IGLAGCoo, false);
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
