package Tabellenkalkulation;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import View.MainView;

public class TableController {
	
	TableView tableView;
	Table_Model table_Model;
	
	public TableController(TableView tableView, Table_Model table_Model) {
		this.tableView = tableView;
		this.table_Model = table_Model;
	}


	
	public void tellModelToTriggerEnterRoutine(int[] triggeredCols, int[] triggeredRows) {
		table_Model.triggerEnterRoutine(triggeredCols, triggeredRows);
	}
	
	public void tellModelToAddRow(JPanel panel) {
		table_Model.addRow(panel);
	}
	
	public JTable getTableFromView() {
		JTable table = tableView.getTable();
		table_Model.setTable(table);
		return table;
	}
	
	public DefaultTableModel getDefaultModelFromView() {
		DefaultTableModel model = tableView.getDefaultTableModel();
		table_Model.setDefaultModel(model);
		return model;
	}
	
	public void tellModelToCheckForArithmetic(int row, int col, String cmd) {
		table_Model.checkForArithmetic(row, col, cmd);
	}
	
	public void tellModeltoUpdateSolution(String rowcols) {
		table_Model.updateSolution(rowcols);
	}
	
	public void tellModelToCheckForCommands(String cmd) {
		table_Model.checkForCommands(cmd);
	}
	
	public String tellViewToGetCommand() {
		return tableView.getCommand();
	}
	
	
}
