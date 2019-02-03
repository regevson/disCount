package extraViews;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import dbActivity.ML_db;

public class InsertExamIDView extends TextBox {

	private JPanel contentPane;


	
	
	public InsertExamIDView(ML_db ML, String heading, String placeholder, String button) {
		super(ML, heading, placeholder, button);
		
		btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				ML.initSignInStudent(textField.getText());
				ML.execScanForStartSignal();
				dispose();
				
			}

		});
		
	}

}
