package Tabellenkalkulation;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import View.MainController;
import View.MainView;

public class ML_Tabellenkalkulation implements MouseListener, TableModelListener, ActionListener{
	
	private MainController MC;
	private MainView MV;
	DefaultTableModel tableModel;
	JTable table;
	private TableController tableController;
	private TableView tableView;
	private Table_Model table_Model;
	private boolean isOpen = false;
	public static boolean tableFiredUp = false;
	

	public void getControllerandView(MainController MC, MainView MV) {
		this.MC = MC;
		this.MV = MV;
	}
	
	
	public void triggerModelEnterRoutine(int[] triggeredCols, int[] triggeredRows) {
		tableController.tellModelToTriggerEnterRoutine(triggeredCols, triggeredRows);
	}
	
	
	
	
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		try{
			MV.setYourCursor(MainView.handCursor());
			JPanel panel = ((JPanel) e.getSource());
			tableController.tellModelToAddRow(panel);

		}catch(Exception ex) {
			JLabel label = (JLabel) e.getSource();
			
			if(label.getIcon().toString().equals("src/exitTable.png")) {
				MC.tellViewToBoot();
				tableView.doADispose();
			}
		}

		
	}
	
	public void startVerfahren(String topic) {
		MV.setYourCursor(MainView.normalCursor());
		MC.tellViewToDispose();
		table_Model = new Table_Model();
		tableView = new TableView(this, MV.getScreenWidth(), MV.getScreenHeight(), topic);
		this.tableController = new TableController(tableView, table_Model);
		
		this.table = tableController.getTableFromView();
		this.tableModel = tableController.getDefaultModelFromView();
		
		executeRelationsRoutine();
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(isOpen != true) {
			MV.offerSelection();
			isOpen  = true;
		}
		
		else {
			MV.disableSelection();
			MV.setYourCursor(MainView.normalCursor());
			
			if(MainView.workPanel.getComponentCount() >= 1) {
				for(int x = 0; x < MainView.workPanel.getComponentCount(); x++) {
					Component comp = MainView.workPanel.getComponent(x);
					comp.setVisible(true);
				}
			}
			
			isOpen = false;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		MV.setYourCursor(MainView.handCursor());
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		MV.setYourCursor(MainView.normalCursor());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void tableChanged(TableModelEvent e) {
		TableColorRenderer.gotChanged = true;
		int row = e.getFirstRow();
		int col = e.getColumn();
		try {
			String str = (String) table.getValueAt(row, col);
	
		if(str.contains("+") || str.contains("-") || str.contains("*") || str.contains("/")) {
			tableController.tellModelToCheckForArithmetic(row, col, str);
		}
		}catch(Exception ex) {
			String rowcols = Integer.toString(row) + Integer.toString(col);
			
			for(int x = 0; x < Table_Model.usedCells.size(); x++) {
				if(Table_Model.usedCells.get(x).equals(rowcols))
					tableController.tellModeltoUpdateSolution(rowcols);
			}
		}//catch
		
		String rowcols = Integer.toString(row) + Integer.toString(col);
		
		for(int x = 0; x < Table_Model.usedCells.size(); x++) {
			if(Table_Model.usedCells.get(x).equals(rowcols))
				tableController.tellModeltoUpdateSolution(rowcols);
		}
		
			
			
	}
	
	
	private void executeRelationsRoutine() {
		
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
					
			}
			
			@Override public void mouseExited(java.awt.event.MouseEvent e) {}@Override public void mousePressed(java.awt.event.MouseEvent e) {
				
				 int selectedRow = table.getSelectedRow();
		    	 int selectedColumn = table.getSelectedColumn();
		    	 
		    	 System.out.println(selectedRow + "/" + selectedColumn);
		    	 
		    	 Object obj = table.getValueAt(selectedRow, selectedColumn);
		    	 
		    	 TableColorRenderer.selectedValue = obj;
		    	 
		    	 TableColorRenderer.isSelected = true;
		    	 
		    	 
				
				String rowcols = Integer.toString(selectedRow) + Integer.toString(selectedColumn);
				
				  for(int x = 0; x < Table_Model.usedCells.size(); x++) {
						if(Table_Model.usedCells.get(x).equals(rowcols)) {
							for(int y = 0; y < Table_Model.tableCells.size(); y++) {
								for(int z = 0; z < Table_Model.tableCells.get(y).size()-1; z++) {
									if(Table_Model.tableCells.get(y).get(z).equals(rowcols)) {
										for(int a = 0; a < Table_Model.tableCells.get(y).size()-1; a++) {
											TableColorRenderer.ll.add(Table_Model.tableCells.get(y).get(a));
										}
										TableColorRenderer.colorIndex++;
										if(TableColorRenderer.colorIndex == TableColorRenderer.relationColors.size())
											TableColorRenderer.colorIndex = 0;
								
										break;
									}
								}
							}
						}
							
					}
				
				
				table.repaint();
				
				
			}@Override public void mouseReleased(java.awt.event.MouseEvent e) {	}});
		
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		try {
		
			String command = ((JTextField) event.getSource()).getText();
			
			if(command.contains("<"))
				tableController.tellModelToCheckForCommands(command);
				
			
			if(command.contains("Bilanzansatz") && !(command.contains("Endbestände")) && !(command.contains("<")))
				tableView.addToEingabeField("Bilanzansatz(Liste <Endbestandände lt. Inventur>; <Preis Abschlusstichtag>)");
			
			
			else if(command.contains("Abwertung") && !(command.contains("<")))
				tableView.addToEingabeField("Abwertung(<Bilanzansatz>; <Ist Endbestand>)");
			
			else if(command.contains("Schwund") && !(command.contains("<")))
				tableView.addToEingabeField("Schwund(Liste <Zukäufe>; Liste <Verkäufe>; Liste <Endebstände>; Liste <Beträge>)");
		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Fehler!\n Der Befehl wurde nicht erkannt.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		
		
		
		
		
		//MainView.addToLeftPanel(command);

		
	}
	
	public void tellViewToSetCursor(Cursor cursor) {
		MV.setYourCursor(cursor);
	}

	


	
}
