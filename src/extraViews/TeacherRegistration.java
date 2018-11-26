package extraViews;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import View.MainView;
import dbActivity.ML_db;

public class TeacherRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodeEingeben;

	
	public TeacherRegistration(ML_db ML) {
		
		setTitle("Registrierung");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 746, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(MainView.disCountBrown);
		
		JLabel lblCodeEingeben = new JLabel("Code eingeben:");
		lblCodeEingeben.setBounds(298, 13, 131, 43);
		lblCodeEingeben.setForeground(Color.WHITE);
		lblCodeEingeben.setFont(MainView.font_18);
		contentPane.add(lblCodeEingeben);
		
		txtCodeEingeben = new JTextField();
		txtCodeEingeben.setText("Code eingeben");
		txtCodeEingeben.setBounds(387, 139, 116, 43);
		contentPane.add(txtCodeEingeben);
		txtCodeEingeben.setColumns(10);
		
		JLabel lblCode = new JLabel("Code:");
		lblCode.setBounds(210, 139, 109, 43);
		lblCode.setForeground(Color.WHITE);
		lblCode.setFont(MainView.font_17_bold);
		contentPane.add(lblCode);
		
		JLabel lblValid = new JLabel("Sie sind nun als Lehrer angemeldet!");
		lblValid.setForeground(Color.BLUE);
		lblValid.setFont(new Font("Roboto", Font.BOLD, 17));
		lblValid.setBounds(220, 216, 287, 43);
		lblValid.setVisible(false);
		contentPane.add(lblValid);
		
		JLabel lblInvalid = new JLabel("Dieser Code ist ung\u00FCltig!");
		lblInvalid.setForeground(Color.BLUE);
		lblInvalid.setFont(new Font("Roboto", Font.BOLD, 17));
		lblInvalid.setBounds(216, 216, 287, 43);
		lblInvalid.setVisible(false);
		contentPane.add(lblInvalid);
		
		JButton btnAbschicken = new JButton("Abschicken");
		btnAbschicken.setBounds(571, 294, 124, 43);
		btnAbschicken.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				boolean isValid = ML.initCheckTeacherCode(txtCodeEingeben.getText());
				
				if(isValid)
					lblValid.setVisible(true);
				else
					lblInvalid.setVisible(true);
				
				btnAbschicken.setEnabled(false);
			}

		});
		
		contentPane.add(btnAbschicken);
		
		JLabel lblSieHabenKeinen = new JLabel("Sie haben keinen Code?");
		lblSieHabenKeinen.setBounds(12, 305, 165, 21);
		contentPane.add(lblSieHabenKeinen);
		
		JLabel lblSchreibenSieEin = new JLabel("Schreiben Sie ein Mail an: juwal.regev@hotmail.com");
		lblSchreibenSieEin.setBounds(12, 323, 359, 21);
		contentPane.add(lblSchreibenSieEin);
		
		
		
	}
	
	
}
