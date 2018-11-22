package Controls;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import View.MainView;
import extraViews.View_SuperClass;

public abstract class BS_Editor {
	protected JPanel editorPanel;
	protected boolean leftMore;
	protected int margin = 37;
	
	protected LinkedList<JTextField> kontoList;
	protected LinkedList<JTextField> priceList;
	private int removePriceCount = 1;
	private boolean biggerThan4;

	public void createEditorPanel(JPanel newBSPanel, int stufig) {
		kontoList = new LinkedList<JTextField>();
		priceList = new LinkedList<JTextField>();
		setSide(true);
		biggerThan4 = false;
		
		editorPanel = new JPanel();
		if(stufig < 5)
			editorPanel.setBounds(90, 2, 450, 146);
		else {
			editorPanel.setBounds(90, 2, 450, 292);
			biggerThan4 = true;
		}
		editorPanel.setBackground(MainView.darkDisCountBlue);
		editorPanel.setLayout(null);
		newBSPanel.add(editorPanel);
		createFields(stufig, biggerThan4);
	}
	

	public JPanel getEditorPanel() {
		return editorPanel;
	}
	
	public void setSide(boolean leftMore) {
		this.leftMore = leftMore;
	}
	
	public LinkedList<JTextField> getKontoList() {
		if(!leftMore) {
			String temp = kontoList.get(0).getText();
			kontoList.get(0).setText(kontoList.get(1).getText());
			kontoList.get(1).setText(temp);
		}
		return kontoList;
	}
	
	public LinkedList<JTextField> getPriceList() {
		if(priceList.size() > 1 && !leftMore) {
			String temp = priceList.get(0).getText();
			priceList.get(0).setText(priceList.get(1).getText());
			priceList.get(1).setText(temp);
		}
		return priceList;
	}
	
	public boolean getSide() {
		return leftMore;
	}
	
	public boolean getSize() {
		return biggerThan4;
	}
	
	public ArrayList<LinkedList<JTextField>> getContent() {
		ArrayList<LinkedList<JTextField>> list = new ArrayList<LinkedList<JTextField>>();
		list.add(kontoList);
		list.add(priceList);
		
		return list;
	}
	
	protected JTextField createTextField(int x, int y, String msg, boolean large) {
		JTextField txtField = new JTextField();
		if(large)
			txtField.setBounds(x, y, 95, 30);
		else
			txtField.setBounds(x, y, 80, 30);
		txtField.setText(msg);
		txtField.setFont(MainView.font_18);
		txtField.setBackground(MainView.lightBlack);
		txtField.setBorder(new LineBorder(MainView.disCountBlue, 2));
		txtField.setBorder(new LineBorder(MainView.disCountBlue, 1));
		txtField.setCaretColor(Color.WHITE);
		txtField.setBorder(BorderFactory.createCompoundBorder(txtField.getBorder(), BorderFactory.createEmptyBorder(3, 3, 3, 3)));
		txtField.setForeground(new Color(218, 218, 218));
		View_SuperClass.highlightContent(txtField);
		
		editorPanel.add(txtField);
		
		return txtField;
	}
	
	public JPanel exchangeEditorPanel(JPanel BSPanel, JPanel oldEditorPanel) {
		BSPanel.remove(oldEditorPanel);
		BSPanel.add(this.editorPanel);
		
		return editorPanel;
	}
	
	public void setFields(LinkedList<JTextField> oldKontoList, LinkedList<JTextField> oldPriceList, boolean leftMore, boolean add) {
		
		
		int maxKonto = oldKontoList.size();
		int maxPrice = oldPriceList.size();
		
		if(!add) {               //wenn upStufen
			maxKonto = kontoList.size();
			maxPrice = priceList.size();
		}
			
		
		for(int i = 0; i < maxKonto; i++) {
			kontoList.get(i).setText(oldKontoList.get(i).getText());
		}
		
		for(int i = 0; i < maxPrice; i++) {
			priceList.get(i).setText(oldPriceList.get(i).getText());
		}
		
		if(kontoList.size() > 2) { // wenn mehrstufig	
			if(this.leftMore != leftMore)
				switchDownPart(add);	
		}

		this.leftMore = leftMore;
	}

	public void setRemovePriceCount(int removePriceCount) {
		this.removePriceCount = removePriceCount;
	}
	
	private int getRemovePriceCount() {
		return removePriceCount;
	}
	


	protected abstract void createFields(int stufig, boolean biggerThan4);
	protected abstract void switchDownPart(boolean add);
	
}

