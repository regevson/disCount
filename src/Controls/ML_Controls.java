package Controls;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.MainController;
import View.MainModel;
import View.MainView;
import dbActivity.Controller_dbActivity;
import dbActivity.db_Model;
import extraViews.MessageBox;
import extraViews.View_SuperClass;

public class ML_Controls implements MouseListener{
	
	private MainController MC;
	private ControlsModel myModel;
	
	private boolean windowListererIsActive = false;

	

	public ML_Controls(MainController mainController) {
		
		MC = mainController;
		myModel = new ControlsModel();
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		
		JLabel jltemp = (JLabel) e.getSource();
		
		if(jltemp.getIcon().toString().equals("src/addBS.png") && jltemp.isEnabled())
			myModel.addStufe(this);
		
		
		
		else if(jltemp.getIcon().toString().equals("src/removeBS.png") && jltemp.isEnabled())
			myModel.removeStufe();
		
		else if(jltemp.getIcon().toString().equals("src/switch_right_pic.png") && jltemp.isEnabled())
			myModel.switchLeftToRight();
		
		else if(jltemp.getIcon().toString().equals("src/switch_left_pic.png") && jltemp.isEnabled())
			myModel.switchRightToLeft();
		
		else if(jltemp.getIcon().toString().equals("src/cloudON.gif")) {
			
			myModel.turnOffCloud(jltemp);
			MC.tellViewToRemoveExerciseSelectionPanel();
			
		}
		
		else if(jltemp.getIcon().toString().equals("src/cloudOFF.png") && db_Model.allowSolutions && !Controller_dbActivity.inExam && MainView.databaseIsActive) {
			
			myModel.turnOnCloud(jltemp);
			MC.tellViewToAddExerciseSelectionPanel(false);
			
		}
		
		
		else if(jltemp.getIcon().toString().equals("src/bsCheck.png") && db_Model.allowSolutions == true && !Controller_dbActivity.inExam && MainView.databaseIsActive) {
			
			if(jltemp.isEnabled()) {
				
				jltemp.setEnabled(false);
				MC.tellViewToRemoveExerciseSelectionPanel();
				
			}
			
			else {
				
				MC.tellViewToAddExerciseSelectionPanel(true);
				jltemp.setEnabled(true);
				
			}
			
		}
		
		
		
		else if((jltemp.getIcon().toString().equals("src/cloudOFF.png") ||  jltemp.getIcon().toString().equals("src/bsCheck.png")) && (!db_Model.allowSolutions || !Controller_dbActivity.inExam || !MainView.databaseIsActive)) {
			
			if(!MainView.databaseIsActive) {
				
				MessageBox mb = new MessageBox("Hinweis", "disCount wurde ohne Datenbankverbindung gestartet!\n", "Ohne funktionierende Verbindung zur Datenbank"
						+ "ist diese Funktion nicht funktionsfähig.", "smallMessage");
				mb.setVisible(true);
				
			}
			
			else if(!db_Model.allowSolutions) {
				
				MessageBox mb = new MessageBox("Hinweis", "Diese Funktion muss erst freigeschaltet werden!\n", "Damit die Lösungsvorschläge-Funktion und "
			    			+ "die Feherkorrektur gut funktionieren, müssen "
							+ "so viele Buchungssätze wie möglich vom Server abrufbar sein. Hilf deinen MitschülerInnen und veröffentliche deine Lösung!\n\n"
							+ "Erst wenn du " + db_Model.MINIMUMADDED + " Lösungen hochgeladen hast, werden die Funktionen aktiviert.\n\n"
									+ "Für weitere Informationen besuchen Sie bitte:\n www.discount-solutions.tk/hochladen", "smallMessage");
				
				mb.setVisible(true);
				
			}
			
			
		}
		
		
		else if(jltemp.getIcon().toString().equals("src/addPic.png") && jltemp.isEnabled()) {
			
			if(Controller_dbActivity.inExam == true && !windowListererIsActive) {
				
				MC.execSetWindowListener();
				windowListererIsActive = true;
				
			}
			
			myModel.addBS(jltemp, this, MC.execGetWorkPanel());
			
		}
		
		else if(jltemp.getIcon().toString().equals("src/checkedPic.png")) {
			
			putIntoFile(myModel.getStufen(), myModel.getLastStufen());
			MainView.addPic.setEnabled(true);
			
		}
		
		else if(jltemp.getIcon().toString().equals("src/checkedPic.png")) {
			
			putIntoFile(myModel.getStufen(), myModel.getLastStufen());
			MainView.addPic.setEnabled(true);
			
		}
		
		
		else if(jltemp.getIcon().toString().equals("src/escapeSmall.png"))
			MC.execRemoveSuchenPanel();
		
}
	
	
	
	
	
	private void putIntoFile(int stufen, BS_Editor lastStufen) {
		
		LinkedList<String> kontoList = MC.execConvertTFListToStringList(lastStufen.getKontoList());
		
		LinkedList<String> priceList = MC.execConvertTFListToStringList(lastStufen.getPriceList());
		
		String[] kontos = MainModel.convertLLStringToStringArray(kontoList);
		String[] prices = MainModel.convertLLStringToStringArray(priceList);
		
		MC.execpaintUpTo7(kontos, prices, lastStufen.getSide());
		
		MainModel.deleteChecked(MC.execGetWorkPanel());
		
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















class BS_1stufig_Editor extends BS_Editor{


	@Override
	protected void createFields(int stufig, boolean biggerThan4) {
		kontoList.add(createTextField(30, 60, "1111", false)); // kontoLeft1
		kontoList.add(createTextField(140, 60, "2222", false)); // kontoRight1
		
		priceList.add(createTextField(315, 60, "3333", true)); // kontoLeft2
	}
	
	public void setFields(LinkedList<JTextField> oldKontoList, LinkedList<JTextField> oldPriceList, boolean leftMore) {
		
		kontoList.get(0).setText(oldKontoList.get(0).getText());
		kontoList.get(1).setText(oldKontoList.get(1).getText());
		
		priceList.get(0).setText(oldPriceList.get(0).getText());
		
		this.leftMore = leftMore;
		
	}
	



	@Override
	protected void switchDownPart(boolean add) {
		String temp = kontoList.get(0).getText();
		kontoList.get(0).setText(kontoList.get(1).getText());
		kontoList.get(1).setText(temp);
		
		if(leftMore)
			leftMore = false;
		else
			leftMore = true;
	}


	
}














///////////////////////mehrStufig/////////////////////////////////////////////////////////////////


class BS_MehrStufig_Editor extends BS_Editor{

	private int adaptMargin;

	@Override
	protected void createFields(int stufig, boolean biggerThan4) {
		adaptMargin = 20 * (stufig - 2);
		if(biggerThan4)
			adaptMargin = 2 * (stufig + 5);
			
		
		kontoList.add(createTextField(30, 45 - adaptMargin, "1111", false)); // kontoLeft1
		kontoList.add(createTextField(115, 45 - adaptMargin, "2222", false)); // kontoRight1
		createKonto_Prices_Fields(stufig, 30, kontoList, false, 3333, 45 - adaptMargin); // other kontos
		
		priceList.add(createTextField(237, 45 - adaptMargin, "4444", true)); // priceLeft1
		priceList.add(createTextField(337, 45 - adaptMargin, "5555", true)); // priceRight1
		createKonto_Prices_Fields(stufig, 237, priceList, true, 6666, 45 - adaptMargin); // other prices
	
	}
	
	
	private void createKonto_Prices_Fields(int stufig, int x, LinkedList<JTextField> list, boolean wide, int text, int adaptMargin) {
		int tempMargin = margin;
		
		for(int i = 0; i < stufig - 1; i++) {
			list.add(createTextField(x, adaptMargin + tempMargin, Integer.toString(text), wide));
			text += 1111;
			tempMargin += margin;
		}
	}
	
	
	
	


	@Override
	protected void switchDownPart(boolean add) {
		
		if(leftMore) {
			switchBounds(kontoList, 115, 80);
			switchBounds(priceList, 337, 95);
			
			leftMore = false;
		}
		
		else {
			switchBounds(kontoList, 30, 80);
			switchBounds(priceList, 237, 95);
			
			leftMore = true;
		}
		
		if(!add)
			switchUpperPart();
		
	}
	
	private void switchBounds(LinkedList<JTextField> list, int x, int width) {
		int tempMargin = margin;
		
		for(int i = 2; i < list.size(); i++) {
			list.get(i).setBounds(x, tempMargin + 45 - adaptMargin, width, 30);
			tempMargin += margin;
		}
	}
	
	public void switchUpperPart() {
		String temp = kontoList.get(0).getText();
		kontoList.get(0).setText(kontoList.get(1).getText());
		kontoList.get(1).setText(temp);
		
		temp = priceList.get(0).getText();
		priceList.get(0).setText(priceList.get(1).getText());
		priceList.get(1).setText(temp);
	}
}

	


	
	







