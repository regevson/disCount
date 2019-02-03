package extraViews;

import java.awt.event.ActionListener;

import dbActivity.ML_db;

public class SearchForSessions extends TextBox{
	
	public SearchForSessions(ML_db ML, String heading, String placeholder, String button) {
		
		super(ML, heading, placeholder, button);
		
		btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				if(ML.initSearchForSessions(textField.getText()) == -1) {
					
					printWarning("Diese Session ist noch nicht vorhanden!");
					return;
					
				}
				
				dispose();
				
				ML.initBuildSessionEnvironment();
				ML.initScanForSessionComments();
				ML.initScanForSessionCodeChanges();
				
			}

		});
		
	}

}
