package extraViews;

import java.awt.event.ActionListener;

import dbActivity.ML_db;

public class NewGroupView extends TextBox {
	
	
	public NewGroupView(ML_db ML, String heading, String placeholder, String button) {
		
		super(ML, heading, placeholder, button);
		
		btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				ML.initCreateNewGroup(textField.getText());
				dispose();
				
			}

		});
		
	}

}
