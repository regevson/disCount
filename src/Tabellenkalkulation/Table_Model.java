package Tabellenkalkulation;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import View.MainModel;
import View.MainView;

public class Table_Model {
	
	private JTable table;
	private DefaultTableModel tableModel;
	public static double schwund = -1;
	public static ArrayList<Double> pricesFromCommand = new ArrayList<Double>();
	public static LinkedList<ArrayList<String>> tableCells = new LinkedList<ArrayList<String>>();
	public static LinkedList<String> usedCells = new LinkedList<String>();
	
	
	public void triggerEnterRoutine(int[] triggeredCols, int[] triggeredRows) {
		System.out.println(triggeredCols.length + "LENGTH");
		ML_Tabellenkalkulation.tableFiredUp = true;
		double solution = 0;
		ArrayList<String> arrList = new ArrayList<String>();
		arrList.add("-1");
		
		outer:
		for(int x = 0; x < triggeredRows.length; x++) {
			for(int y = 0; y < triggeredCols.length; y++) {
				if(triggeredCols.length > 1) {
					arrList.remove(0);
					String rowcols = Integer.toString(triggeredRows[x]) + Integer.toString(triggeredCols[y]);
					usedCells.add(rowcols);
					String rowcols2 = Integer.toString(triggeredRows[x]) + Integer.toString(triggeredCols[y+1]);
					usedCells.add(rowcols2);

					solution = checkandCorrectFormat(table.getValueAt(triggeredRows[x], triggeredCols[y])) * checkandCorrectFormat(table.getValueAt(triggeredRows[x], triggeredCols[y + 1]));
					
					if(solution != -1) {
						arrList.add(rowcols); arrList.add(rowcols2);
						break outer;
					}
					else
						return;
					
					
				}
				else {
					String rowcols = Integer.toString(triggeredRows[x]) + Integer.toString(triggeredCols[y]);
					usedCells.add(rowcols);
					solution += checkandCorrectFormat(table.getValueAt(triggeredRows[x], triggeredCols[y]));
					arrList.add(rowcols);
				}
				
			}
			
		}
		arrList.add(Double.toString(solution));
		tableCells.add(arrList);
		

			if(triggeredCols.length == 1)
				table.setValueAt(solution, triggeredRows[0] + triggeredRows.length, triggeredCols[0]);
			else
				table.setValueAt(solution, triggeredRows[0], triggeredCols[0] + triggeredCols.length);
	}
	
	public void addRow(JPanel panel) {
		int index = TableView.addRowList.indexOf(panel);
		System.out.println(index);
		tableModel.insertRow(index + 1, new Object[]{"neue Zeile", "", "", "", "", ""});
		table.repaint();
	}
	
	public void setTable(JTable table) {
		this.table = table;
		
	}
	
	
	public void setDefaultModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
	
	private double checkandCorrectFormat(Object object) {
		try {
			return Double.parseDouble((String) object);
		}catch(Exception ex) {
			try {
			return (double) object;
				}catch(Exception exc) {
					return -1;
				}
		}
	}
	
	public void checkForArithmetic(int row, int col, String command) {

			Object solution = chooseArithmetic(command);
			table.setValueAt(solution, row, col);
			
	}
	
	private void changeRelations(String rowcols) {
		ArrayList<Double> otherOperands = new ArrayList<Double>();
		boolean isHorizontal = true;

		
		int selList = 0;

		for(int x = 0; x < tableCells.size(); x++) {
			for(int y = 0; y < tableCells.get(x).size(); y++) {
				if(tableCells.get(x).get(y).equals(rowcols)) {
					
					
					if(tableCells.get(x).get(0).equals("-1")) {
						isHorizontal = false;
						tableCells.get(x).remove(0);
					}
					
					
					selList = x;

					otherOperands = fillUpOperands(x, tableCells.get(x).size(), otherOperands, isHorizontal);

					int[] rowcolArray = convertRowCols_toInt(tableCells.get(x).get(tableCells.get(x).size()-2), isHorizontal);
					
					if(isHorizontal)
						finishUpNewValue(otherOperands, selList, rowcolArray, 0, rowcolArray[1] + 1, isHorizontal);
					else
						finishUpNewValue(otherOperands, selList, rowcolArray, 1, rowcolArray[0] + 1, isHorizontal);
					
					if(!isHorizontal)
						tableCells.get(x).add(0, "-1");
					
					break;
					
				}
			}
			
			isHorizontal = true;
			otherOperands.removeAll(otherOperands);
			
		}
	}
	
	private void finishUpNewValue(ArrayList<Double> otherOperands, int selList, int[] rowcolArray, int rowcolArrayIndex, int rowcolIndexOfSolution, boolean isHorizontal) {
		double otherOps = 1;

		if(isHorizontal) {
			for(int x = 0; x < otherOperands.size(); x++) {
				otherOps *= otherOperands.get(x);
				tableCells.get(selList).set(tableCells.get(selList).size()-1, Double.toString(otherOps));
				table.setValueAt(otherOps, rowcolArray[rowcolArrayIndex], rowcolIndexOfSolution);
			}
		}else {
			otherOps--;
			for(int x = 0; x < otherOperands.size(); x++) {
				otherOps += otherOperands.get(x);
				tableCells.get(selList).set(tableCells.get(selList).size()-1, Double.toString(otherOps));
				table.setValueAt(otherOps, rowcolIndexOfSolution, rowcolArray[rowcolArrayIndex]);
			}
		}
			
	}
	
	
	private ArrayList<Double> fillUpOperands(int x, int size, ArrayList<Double> otherOperands, boolean isHorizontal) {
		for(int z = 0; z < size-1; z++) {
			int[] rowcolArrayOperands = convertRowCols_toInt(tableCells.get(x).get(z), isHorizontal);
			
			try {
				
				Object obj = table.getValueAt(rowcolArrayOperands[0], rowcolArrayOperands[1]);
				
				if(obj instanceof Double)
					otherOperands.add((double) obj);
				
				else if(obj instanceof String) {
					if(!((String) obj).equals("?"))
						otherOperands.add(Double.parseDouble((String) obj));
				}
				
				

			}
			
			catch(Exception ex) {
				System.out.println("ERROR: " + table.getValueAt(rowcolArrayOperands[0], rowcolArrayOperands[1]));
			}
			
		
		}
		return otherOperands;
	}
	
	private void possibleCatches(ArrayList<Double> otherOperands, int[] rowcolArrayOperands) {
		try {
			otherOperands.add((double) table.getValueAt(rowcolArrayOperands[0], rowcolArrayOperands[1]));}
			catch(Exception e) {
				try {
				String temp = (String) table.getValueAt(rowcolArrayOperands[0], rowcolArrayOperands[1]);
				otherOperands.add(Double.parseDouble(temp));
				}catch(Exception eddd) {
					otherOperands.add((double) table.getValueAt(rowcolArrayOperands[0], rowcolArrayOperands[1]));
				}
			}
	}
	
	private Object chooseArithmetic(String cmd) {
		if(cmd.contains("-")) {
			int opIndex = cmd.indexOf("-");
			double num1 = MainModel.roundDouble_giveBack_Double(Double.parseDouble(cmd.substring(0, opIndex)));
			double num2 = MainModel.roundDouble_giveBack_Double(Double.parseDouble(cmd.substring(opIndex+1, cmd.length())));
			return MainModel.roundDouble_giveBack_Double(num1 - num2);
		}
		
		else if(cmd.contains("+")) {
			int opIndex = cmd.indexOf("+");
			double num1 = MainModel.roundDouble_giveBack_Double(Double.parseDouble(cmd.substring(0, opIndex)));
			double num2 = MainModel.roundDouble_giveBack_Double(Double.parseDouble(cmd.substring(opIndex+1, cmd.length())));
			return MainModel.roundDouble_giveBack_Double(num1 + num2);
		}
		
		else if(cmd.contains("*")) {
			int opIndex = cmd.indexOf("*");
			double num1 = MainModel.roundDouble_giveBack_Double(Double.parseDouble(cmd.substring(0, opIndex)));
			double num2 = MainModel.roundDouble_giveBack_Double(Double.parseDouble(cmd.substring(opIndex+1, cmd.length())));
			return MainModel.roundDouble_giveBack_Double(num1 * num2);
		}
		
		else if(cmd.contains("/")) {
			int opIndex = cmd.indexOf("/");
			double num1 = Double.parseDouble(cmd.substring(0, opIndex));
			double num2 = Double.parseDouble(cmd.substring(opIndex+1, cmd.length()));
			
			return MainModel.roundDouble_giveBack_Double(num1 / num2);
			
		}
		
		
		return TableColorRenderer.selectedValue;
	}
	
	public void updateSolution(String rowcols) {
		changeRelations(rowcols);
	}
	
	public static int[] convertRowCols_toInt(String rowcols, boolean isHorizontal) {
		if(rowcols.length() % 2 == 0) {
				int[] tempArr = {Integer.parseInt(rowcols.substring(0, rowcols.length() / 2)), Integer.parseInt(rowcols.substring(rowcols.length() / 2, rowcols.length()))};
				//System.out.println(tempArr[0] + "/" + tempArr[1] + "   even");
				return tempArr;
		}
			
		else {
				int[] tempArr = {Integer.parseInt(rowcols.substring(0, rowcols.length()-1)), Integer.parseInt(rowcols.substring(rowcols.length()-1, rowcols.length()))};
				//System.out.println(tempArr[0] + "/" + tempArr[1] + "   uneven");
				return tempArr;
			
		}
			
	}
	
	
	public void checkForCommands(String command) {
		if(command.contains("Bilanzansatz")) {
			int startIndex = getValuesWithKommaReference(command);
			command = command.substring(startIndex, command.length());
			getValuesWithKommaReference(command);
			
			pricesFromCommand.add((double) -1);
			System.out.println(pricesFromCommand);
			table.repaint();
			//MainView.addToEingabeField("");
		}
		
		else if(command.contains("Abwertung")) {
			int startIndex = getValuesWithKommaReference(command);
			command = command.substring(startIndex, command.length());
			getValuesWithKommaReference(command);
			double BA = pricesFromCommand.get(0);
			double istEB = pricesFromCommand.get(1);
			TableView.addToLeftPanel("Abwertung = " + (istEB - BA));
			Table_Model.pricesFromCommand.removeAll(Table_Model.pricesFromCommand);
		}
		
		
		else if(command.contains("Schwund")) {
			int startIndex = getValuesWithKommaReference(command);
			command = command.substring(startIndex, command.length());
			startIndex = getValuesWithKommaReference(command);
			command = command.substring(startIndex, command.length());
			startIndex = getValuesWithKommaReference(command);
			command = command.substring(startIndex, command.length());
			getValuesWithKommaReference(command);
			calcSchwund();
			TableView.addToLeftPanel("Schwund = " + this.schwund);
			Table_Model.pricesFromCommand.removeAll(Table_Model.pricesFromCommand);
		}
			
	}
	
	
	private int getValuesWithKommaReference(String command) {
		String tempString = command;
		boolean stop = false;
		int max = tempString.length() - tempString.replace(",", "").length();
		System.out.println(max);
		int indexKleinerZeichen = command.indexOf("<") + 1;
		int indexGroesserZeichen = command.indexOf(">");
		
		for(int x = 0; x <= indexGroesserZeichen; x++) {
			System.out.println("OUTPUT");
			int indexBeistrich = command.indexOf(",", indexKleinerZeichen);
			if(indexBeistrich > indexGroesserZeichen || indexBeistrich == -1) {
				stop = true;
				indexBeistrich = command.indexOf(">");
			}
			String value = command.substring(indexKleinerZeichen, indexBeistrich);
			indexKleinerZeichen = indexBeistrich + 1;
			
			if(value.contains(" "))
				value = value.replace(" ", "");
			
			pricesFromCommand.add(Double.parseDouble(value));
			System.out.println(value + "   command");
			
			if(stop)
				return indexKleinerZeichen;
		}
		
		return indexKleinerZeichen;
		
	
	}
	
	
	//Bilanzansatz(Liste <900,1000,1900>; <1.1>)
	public static double calcuateBilanzansatz(int row, int column, JTable table) {
		
		 double price = 0;
		 double bilanzAnsatzPrice = -1;
		 double maxPrice = Table_Model.pricesFromCommand.get(Table_Model.pricesFromCommand.size()-2);
		 for(int x = 0; x < Table_Model.pricesFromCommand.size()-2; x++) {
			Object object = table.getValueAt(x + 1, 3);
			 if(object instanceof Double)
				 price = (double) object;
			 else if(object instanceof String){
				 price = Double.parseDouble((String) object);
			 }
			
			 if(price > maxPrice) {
				 price = maxPrice;
				 table.setValueAt("Niederstwertprinzip!", row, 0);
			 }
			
			 table.setValueAt(price, row, column + 2);
			 
			 table.setValueAt(Table_Model.pricesFromCommand.get(x), row, column + 1);
			 
			 
			 table.setValueAt(Table_Model.pricesFromCommand.get(x) * price, row++, column + 3);
			 bilanzAnsatzPrice += Table_Model.pricesFromCommand.get(x) * price;
			 
		 }
		 TableView.addToLeftPanel("Bilanzansatz = " + MainModel.roundDouble_giveBack_String(bilanzAnsatzPrice));
		 Table_Model.pricesFromCommand.removeAll(Table_Model.pricesFromCommand);
		 
		 return bilanzAnsatzPrice;
		 
	 }
	
	//Schwund(Liste <6000,25000,40000>; Liste <5000,24000,38000>; Liste <900,1000,1900>; Liste <1,1.2,1.3>)
	
	public void calcSchwund() {
		ArrayList<Double> zukäufe = new ArrayList<Double>(Table_Model.pricesFromCommand.subList(0, pricesFromCommand.size() / 4));
		ArrayList<Double> verkäufe = new ArrayList<Double>(Table_Model.pricesFromCommand.subList(pricesFromCommand.size() / 4, (pricesFromCommand.size() / 4) * 2));
		ArrayList<Double> endbestände = new ArrayList<Double>(Table_Model.pricesFromCommand.subList((pricesFromCommand.size() / 4) * 2, (pricesFromCommand.size() / 4) * 3));
		ArrayList<Double> beträge = new ArrayList<Double>(Table_Model.pricesFromCommand.subList((pricesFromCommand.size() / 4) * 3, (pricesFromCommand.size() / 4) * 4));
		double solution = 0;
		
		for(int x = 0; x < pricesFromCommand.size() / 4; x++) {
			solution += (zukäufe.get(x) - verkäufe.get(x) - endbestände.get(x)) * beträge.get(x);
		}
		
		this.schwund  = solution;
		table.repaint(); 
	}
		

	
	
	
	
	
}
