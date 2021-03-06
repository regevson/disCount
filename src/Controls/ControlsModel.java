package Controls;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.Buchungssatz;
import View.MainView;

public class ControlsModel {

	
	
	private JLabel plus_pic;
	private JLabel minus_pic;
	private JLabel switch_right_pic;
	private JLabel switch_left_pic;

	private BS_1stufig_Editor einstufig;
	private BS_MehrStufig_Editor mehrstufig;

	private JPanel newBSPanel;

	private JPanel editorPanel;
	
	private int stufen = 1;
	
	BS_Editor lastStufen;
	private Buchungssatz bs;
	

	
	
	
	
	
	public void addStufe(ML_Controls ml_Controls) {
		
		if(stufen > 6)
			return;
		
		stufen++;
		editorPanel.add(minus_pic, this);
		upStufen();
		editorPanel.repaint();
	}

	public void removeStufe() {
		stufen--;
		downStufen();
	}

	public void switchLeftToRight() {
		editorPanel.remove(switch_right_pic);
		editorPanel.add(switch_left_pic);
		doASwitch();
		editorPanel.repaint();
	}

	public void switchRightToLeft() {
		editorPanel.remove(switch_left_pic);
		editorPanel.add(switch_right_pic);
		doASwitch();
		editorPanel.repaint();
	}

	public void turnOnCloud(JLabel jltemp) {
		MainView.menuPanel.remove(jltemp);
		JLabel label = MainView.makeMenuLabels("src/cloudON.gif", "Lösungsvorschläge deaktivieren", 310, 1, 8, 51, 51);
		MainView.menuPanel.add(label);
		MainView.menuPanel.repaint();
		MainView.suggestionsEnabled = true;		
	}

	public void turnOffCloud(JLabel jltemp) {
		MainView.menuPanel.remove(jltemp);
		JLabel label = MainView.makeMenuLabels("src/cloudOFF.png", "Lösungsvorschläge aktivieren", 310, 1, 8, 52, 51);
		MainView.menuPanel.add(label);
		MainView.menuPanel.repaint();
		MainView.suggestionsEnabled = false;
	}

	public void addBS(JLabel jltemp, ML_Controls ML, JPanel workPanel) {
		stufen = 1;
		prepareAdd(jltemp, ML, workPanel);
	}

	public int getStufen() {
		return stufen;
	}
	
	public BS_Editor getLastStufen() {
		return lastStufen;
	}
	
	
	
	
	

	
	
	
	private void prepareAdd(JLabel jltemp, ML_Controls ML, JPanel workPanel) {
		
		bs = new Buchungssatz();
		newBSPanel = bs.createBSContainer(workPanel);
		
		bs.setRadioButtonSelected(true);
		
		if(jltemp.isEnabled()) {
			einstufig  = new BS_1stufig_Editor();
			einstufig.setRemovePriceCount(2);
			einstufig.createEditorPanel(newBSPanel, 1);
			editorPanel = einstufig.getEditorPanel();
			
			lastStufen = einstufig;
			mehrstufig = null;
			
			initIcons(ML);
			printIconsOf1(true);
			
			MainView.addPic.setEnabled(false);
		}

	}
	
	private void initIcons(ML_Controls ML) {
		plus_pic = makeplus_minus_pic("src/addBS.png", ML);
		plus_pic.setBounds(2, 125, 20, 20);
		
		minus_pic = makeplus_minus_pic("src/removeBS.png", ML);
		minus_pic.setBounds(2, 105, 20, 20);
		
		switch_right_pic = makeplus_minus_pic("src/switch_right_pic.png", ML);
		switch_right_pic.setBounds(198, 2, 36, 20);

		switch_left_pic = makeplus_minus_pic("src/switch_left_pic.png", ML);
		switch_left_pic.setBounds(198, 2, 36, 20);
	}
	
	
	

	
	private void upStufen() {
		
		if(!lastStufen.getSize() && stufen > 4)
			bs.modifyJPanel();
			
		newBSPanel.remove(editorPanel);
		newBSPanel.repaint();
		
		mehrstufig  = new BS_MehrStufig_Editor();
		mehrstufig.createEditorPanel(newBSPanel, stufen);
		
		ArrayList<LinkedList<JTextField>> content = lastStufen.getContent();
		
		mehrstufig.setFields(content.get(0), content.get(1), lastStufen.getSide(), true);
		
		editorPanel = mehrstufig.exchangeEditorPanel(newBSPanel, editorPanel);
		printIconsOfMehrStufig(lastStufen.getSide());
		
		lastStufen = mehrstufig;
		
	}
	
	private void downStufen() {
		
		if(lastStufen.getSize() && stufen < 5)
			bs.reModifyJPanel();
		
		newBSPanel.remove(editorPanel);
		newBSPanel.repaint();
		
		boolean oldSide = lastStufen.getSide();
		
		BS_Editor newStufen;
		
		if(stufen == 1)
			newStufen = einstufig;
		else
			newStufen = mehrstufig;
		
		newStufen.createEditorPanel(newBSPanel, stufen);
		
		ArrayList<LinkedList<JTextField>> content = lastStufen.getContent();
		
		newStufen.setFields(content.get(0), content.get(1), oldSide, false);
		
		editorPanel = newStufen.exchangeEditorPanel(newBSPanel, editorPanel);
		printIconsOfMehrStufig(lastStufen.getSide());
		
		lastStufen = newStufen;
		
	}
	

	
	private void printIconsOf1(boolean leftMore) {
		editorPanel.add(plus_pic);
		if(leftMore)
			editorPanel.add(switch_right_pic);
		else
			editorPanel.add(switch_left_pic);
	}
	
	private void printIconsOfMehrStufig(boolean leftMore) {
		editorPanel.add(plus_pic);
		editorPanel.add(minus_pic);
		if(leftMore)
			editorPanel.add(switch_right_pic);
		else
			editorPanel.add(switch_left_pic);
	}
	
	
	
	private void doASwitch() {
		
		if(stufen == 1) {
			einstufig.switchDownPart(true);
			editorPanel = einstufig.exchangeEditorPanel(newBSPanel, editorPanel);
		}
		
		else if(stufen > 1) {
			mehrstufig.switchDownPart(true);
			mehrstufig.switchUpperPart();
			editorPanel = mehrstufig.exchangeEditorPanel(newBSPanel, editorPanel);
		}
		
	}
	
	
	
	
	private JLabel makeplus_minus_pic(String source, ML_Controls ML) {
		ImageIcon icon = new ImageIcon(source);
		JLabel picLabel = new JLabel(icon);
		picLabel.addMouseListener(ML);
		return picLabel;
	}
	
	

	
	
}
