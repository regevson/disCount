package stats;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import View.MainController;

public class ML_Stats implements MouseListener {
	
	private Controller_Stats myController;
	private stats_View myView;
	
	public ML_Stats(MainController mainController) {
		myController = new Controller_Stats(mainController);
		mainController.getControllerList().addLast(myController);
		myView = new stats_View();
	}
	
	public void paintGraphs(JPanel panelMiddle, LinkedList<JLabel> labels) {
		myView.setMainPanel(panelMiddle);
		myView.paintLabels(labels);
		myView.paintGraphs(myController.initGetGraphInfo());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
