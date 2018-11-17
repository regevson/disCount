package Ausland;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import View.MainModel;
import View.MainView;
import extraViews.View_SuperClass;
import extraViews.View_SuperClass_2Outputs;

public class IG_Erwerb_Kauf_View extends View_SuperClass_2Outputs{

	
	private String konto1;
	private String[] konten2;
	private String konto3;
	
	private static JTextField fwSatz;
	public JCheckBox fwCB;
	
	private JPanel panel;
	

	
	@Override
	public void build(String konto1, String[] konten2, String konto3, boolean twoOutputs) {
		this.konto1 = konto1;
		this.konten2 = konten2;
		this.konto3 = konto3;
		
		changeYZKWP();
		makeKonto1(konto1);

		makeKonto2Fixed(konten2[0]);
		
		if(twoOutputs)
			makeNotice("Erwerbssteuer wir automatisch hinzugef�gt.");
		
		makePrice();
		makeFremdw�hrungssatz();
		makeFremdw�hrungCheckbox();
	}
	
	private void makeFremdw�hrungssatz() {
		fwSatz = new JTextField();
		View_SuperClass.txtFieldDesign(fwSatz);
		fwSatz.setText("Kurs");
		fwSatz.setBounds(621, 142, 156, 42);
		contentPane.add(fwSatz);
		fwSatz.setColumns(10);
		fwSatz.setVisible(false);
	}
	
	private void makeFremdw�hrungCheckbox() {
		fwCB = new JCheckBox("Fremdw�hrung");
		View_SuperClass.checkBoxDesign(fwCB);
		fwCB.setBounds(611, 267, 200, 25);
		fwCB.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				fwSatz.setVisible(true);
				fwCB.setForeground(new Color(0, 117, 211));
			}

				});
		contentPane.add(fwCB);
	}
	

	
	public void setUpRoutineWithFW() {
		fwSatz.setVisible(true);
	}

	@Override
	public void setUpRoutine(String konto4, String konto5, String percent, boolean twoOutputs, String hint) {

		finalZahlungskonto = konten2[0];
		
		

		if(fwCB.isSelected()) //zusammenfassen mit unterem eventuell
			txtPreis.setText(MainModel.roundDouble_giveBack_String(Double.parseDouble(((Controller_Ausland) myController).initExecuteFWRoutine(txtPreis.getText(), fwSatz.getText()))));
			
		
		panel = myController.initPaint2Konten_mitzyk(konto1, finalZahlungskonto);
		
		if(fwCB.isSelected())
			MainView.addNoteToPanel("Verwendeter Kurs:     "+ fwSatz.getText(), panel, 375);
		
		myController.initPaint1Price(Float.parseFloat(txtPreis.getText()));

		if(twoOutputs)
			makeThe2ndBS(konto3, konto4, txtPreis.getText());
			
		View_SuperClass.resetSwap();

	}
	
	public void makeThe2ndBS(String konto3, String konto4, String price) {

		myController.initPaint2Konten_mitzyk(konto3, konto4);
		
		((Controller_Ausland) myController).initCalculateErwerbsteuerbetrag_andPaintIt(price);
		
		resetyZK();
	

	}
	
	private void changeYZKWP() {
		yZKWP = MainView.margin+10;
	}
	
	private void makeNotice(String msg) {
		JLabel jl = new JLabel(msg);
		jl.setFont(MainView.font_17);
		jl.setBounds(xLeft, ySecond+100, 500, 20);
		jl.setForeground(Color.WHITE);
		contentPane.add(jl);
	}
	
}
