package Tabellenkalkulation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import View.MainView;

public class ML_TableSelectionTopics implements MouseListener{
	
	ML_Tabellenkalkulation ML_TABKALK;

	public ML_TableSelectionTopics(ML_Tabellenkalkulation ML_TABKALK) {
		this.ML_TABKALK = ML_TABKALK;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		ML_TABKALK.tellViewToSetCursor(MainView.handCursor());
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		ML_TABKALK.tellViewToSetCursor(MainView.normalCursor());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clearPreviousLists();
		JLabel label = ((JLabel) e.getSource());
		if(label.getText().equals("Identitätspreisverfahren"))
			ML_TABKALK.startVerfahren("Identitätspreisverfahren");
		
		else if(label.getText().equals("Durchschnittspreisverfahren"))
			ML_TABKALK.startVerfahren("Durchschnittspreisverfahren");
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void clearPreviousLists() {
		Table_Model.pricesFromCommand.removeAll(Table_Model.pricesFromCommand);
		Table_Model.tableCells.removeAll(Table_Model.tableCells);
		Table_Model.usedCells.removeAll(Table_Model.usedCells);
		
		TableColorRenderer.ll.removeAll(TableColorRenderer.ll);
	}

}
