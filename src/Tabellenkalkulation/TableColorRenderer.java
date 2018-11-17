package Tabellenkalkulation;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import View.MainModel;
import View.MainView;

public class TableColorRenderer extends DefaultTableCellRenderer {
	
	public static LinkedList<String> ll = new LinkedList<String>();
	public static ArrayList<Color> relationColors = new ArrayList<Color>();
	public static int colorIndex = 0;
	public static Object selectedValue = null;
	public static boolean combinedOnce = false;
	public static boolean gotChanged = false;
	private static int count = 0;
	public static boolean isSelected = false;
	private static double istEB = -1;
	


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

    	
    	 Component cr = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
     	
    	 String rowcols = Integer.toString(row) + Integer.toString(column);
    	 
    	 Object obj = table.getValueAt(row, column);
    	 
    	 if(column == 1)
    		 setFont(MainView.font_17_bold);
    	 
    	 else
    		 setFont(MainView.font_16);
    	 
    	 
    	 if(value instanceof String && value.equals("Schwund") && Table_Model.schwund != -1) {
    		 table.setValueAt(Table_Model.schwund, row, column + 3);
    		 Table_Model.schwund = -1;
    	 }
    		 
    	 
    	 
			
    	 if(selectedValue != null) {
	    	
	    	
				
			if(obj instanceof String) {
				String input = (String) obj;
				if(input.contains("=")) {
					if(!combinedOnce) {
						table.setValueAt(input + selectedValue, row, column);
						combinedOnce = true;
					}
					else {
						if(input.contains("=") && count++ == 1) {
							input = input.substring(1, input.length());
							table.setValueAt(input + selectedValue, row, column);
							count = 0;
							combinedOnce = false;
						}
					}
					selectedValue = null;
					
				}
				
				
			}

    	 }
    	 
    	 try {
    		 
    	 calcBilanzansatz(obj, row, column, table);
    	 
    	 }catch(Exception e) {
 			JOptionPane.showMessageDialog(null, "Der Bilanzansatz konnte nicht berechnet werden!\n Haben Sie alle notwendigen Schritte richtig ausgeführt?\n Sie müssen leider das Programm neu starten!", "Warning", JOptionPane.WARNING_MESSAGE);
    	 }

        
     
    	int sizebefore = ll.size();


     for(int x = 0; x < ll.size(); x++) {
		if(ll.get(x).equals(rowcols) && ll.size() != 0) {
			Border empty = new EmptyBorder(3,3,3,3);
			Border line = new LineBorder(relationColors.get(colorIndex), 2);
			setBorder(new CompoundBorder(line, empty));
			ll.remove(x);
			
			if(colorIndex == relationColors.size() - 1)
				colorIndex = 0;
		}
			
	}
	
	if(ll.size() == sizebefore) {
		setBorder(null);
		setBorder(new EmptyBorder(5,5,5,5));
	}



    	
    
    	
    	
    	return cr;
    
    
    		
    }
    
    private void calcBilanzansatz(Object obj, int row, int column, JTable table) {
    	
    	double bilanzAnsatz = -1;
    	
		if(obj != null && obj instanceof String) {
			String input = (String) obj;
			
			
			if(input.equals("Ist EB")) {
				Object ranValue = table.getValueAt(row, column + 3);
				if(ranValue != null && ranValue instanceof Double)
					istEB = (double) table.getValueAt(row, column + 3);
				
				else if((String) ranValue != null && !(((String) ranValue).isEmpty()) && ranValue instanceof String && !(((String) ranValue).contains("=")))
					istEB = Double.parseDouble((String) table.getValueAt(row, column + 3));
				
			}
				
		 
			 if(Table_Model.pricesFromCommand != null && Table_Model.pricesFromCommand.size() != 0) {
				 if(input.equals("Bilanzansatz") && Table_Model.pricesFromCommand.get(Table_Model.pricesFromCommand.size()-1) == -1) {
					 bilanzAnsatz = Table_Model.calcuateBilanzansatz(row, column, table);
		    		 Table_Model.pricesFromCommand.removeAll(Table_Model.pricesFromCommand);
		    		 
		    		 if(bilanzAnsatz != -1 && istEB != -1) {
		    			 table.setValueAt(istEB - bilanzAnsatz, row - 1, column + 3);
		    			 istEB = -1;
		    			 bilanzAnsatz = -1;
		    			 
		    		 }
				 }
					
			}
			 
			 
			 
	
			
		}
	    	
    }
	
   

       
        
       
        
}

