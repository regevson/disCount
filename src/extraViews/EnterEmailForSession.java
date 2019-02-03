package extraViews;

import java.awt.event.ActionListener;

import dbActivity.ML_db;

public class EnterEmailForSession extends TextBox{

	public EnterEmailForSession(ML_db ML, String heading, String placeholder, String button) {
		
		super(ML, heading, placeholder, button);
		
		btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				if(ML.initContactPartner(textField.getText()) == -1) {
					
					printWarning("Diesen Account gibt es nicht!");
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
