package extraViews;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import View.MainView;
import dbActivity.ML_db;

public class TeacherRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodeEingeben;
	
	private Color purple = new Color(128, 0, 128);

	
	public TeacherRegistration(ML_db ML) {
		
		setTitle("Registrierung");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 746, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		
		JLabel lblCodeEingeben = new JLabel("Code eingeben:");
		lblCodeEingeben.setBounds(298, 35, 131, 43);
		lblCodeEingeben.setForeground(Color.DARK_GRAY);
		lblCodeEingeben.setFont(MainView.font_18);
		contentPane.add(lblCodeEingeben);
		
		txtCodeEingeben = new JTextField();
		txtCodeEingeben.setFont(MainView.font_15);
		txtCodeEingeben.setForeground(new Color(128, 128, 128));
		txtCodeEingeben.setText("Neues Passwort (f\u00FCr disCount)");
		txtCodeEingeben.setBorder(new LineBorder(purple, 2, true));
		txtCodeEingeben.setBorder(BorderFactory.createCompoundBorder(txtCodeEingeben.getBorder(), BorderFactory.createEmptyBorder(3, 6, 3, 3)));
		txtCodeEingeben.setBackground(Color.WHITE);
		txtCodeEingeben.setText("Code eingeben");
		txtCodeEingeben.setBounds(387, 139, 116, 43);
		contentPane.add(txtCodeEingeben);
		txtCodeEingeben.setColumns(10);
		
		JLabel lblCode = new JLabel("Code:");
		lblCode.setBounds(210, 139, 109, 43);
		lblCode.setForeground(purple);
		lblCode.setFont(MainView.font_17_bold);
		contentPane.add(lblCode);
		
		JLabel lblValid = new JLabel("Sie sind nun als Lehrer angemeldet!");
		lblValid.setForeground(Color.DARK_GRAY);
		lblValid.setFont(MainView.font_17_bold);
		lblValid.setBounds(220, 216, 287, 43);
		lblValid.setVisible(false);
		contentPane.add(lblValid);
		
		JLabel lblInvalid = new JLabel("Dieser Code ist ung\u00FCltig!");
		lblInvalid.setForeground(Color.DARK_GRAY);
		lblInvalid.setFont(MainView.font_17_bold);
		lblInvalid.setBounds(216, 216, 287, 43);
		lblInvalid.setVisible(false);
		contentPane.add(lblInvalid);
		
		JButton btnAbschicken = new JButton("Abschicken");
		btnAbschicken.setBackground(purple);
		btnAbschicken.setForeground(Color.WHITE);
		btnAbschicken.setContentAreaFilled(false);
		btnAbschicken.setOpaque(true);
		btnAbschicken.setFont(new Font("Roboto", Font.BOLD, 15));
		btnAbschicken.setBorder(new LineBorder(purple, 2, true));
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
