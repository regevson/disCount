package extraViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import View.MainView;
import dbActivity.ML_db;

public class ExerciseAvailabilityChecker_View extends JFrame {

	private JPanel contentPane;

	public ExerciseAvailabilityChecker_View(ML_db MLdb) {
		setTitle("Verfügbarkeit prüfen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 843, 356);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(MainView.darkDisCountBlue);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblHeading1 = new JLabel("Prüfe, ob es bereits eine Lösung für diese Aufgabe gibt!");
		lblHeading1.setFont(MainView.font_19_bold);
		lblHeading1.setBounds(22, 23, 782, 46);
		lblHeading1.setForeground(Color.WHITE);
		contentPane.add(lblHeading1);
		
		String array[] = {"1. Rechnungswesen-Buch", "2. Rechnungswesen-Buch", "3. Rechnungswesen-Buch", "4. Rechnungswesen-Buch", "5. Rechnungswesen-Buch"};
		
		JComboBox comboBox = new JComboBox(array);
		comboBox.setFont(MainView.font_16_bold);
		comboBox.setBounds(169, 152, 217, 40);
		contentPane.add(comboBox);
		
		JTextField txtSeite = new JTextField();
		txtSeite.setFont(MainView.font_16_bold);
		txtSeite.setText("Seite");
		txtSeite.setBounds(408, 151, 90, 42);
		contentPane.add(txtSeite);
		txtSeite.setColumns(10);
		
		JTextField txtNummer = new JTextField();
		txtNummer.setFont(MainView.font_16_bold);
		txtNummer.setText("Nummer");
		txtNummer.setBounds(522, 152, 90, 42);
		contentPane.add(txtNummer);
		txtNummer.setColumns(10);
		
		JLabel lblUmWelcheAufgabe = new JLabel("Um welche Aufgabe geht es denn?");
		lblUmWelcheAufgabe.setForeground(Color.WHITE);
		lblUmWelcheAufgabe.setFont(MainView.font_17);
		lblUmWelcheAufgabe.setBounds(22, 82, 280, 46);
		contentPane.add(lblUmWelcheAufgabe);
		
		JLabel lblVorhanden = new JLabel("Eine L\u00F6sung zu dieser Aufgabe mit guter Bewertung ist bereits vorhanden!");
		lblVorhanden.setForeground(Color.ORANGE);
		lblVorhanden.setFont(MainView.font_17_bold);
		lblVorhanden.setBounds(22, 234, 700, 46);
		contentPane.add(lblVorhanden);
		lblVorhanden.setVisible(false);
		
		
		JLabel lblNichtVorhanden = new JLabel("Es ist noch keine zufriedenstellende Lösung vorhanden. Lade doch du eine hoch!");
		lblNichtVorhanden.setForeground(Color.ORANGE);
		lblNichtVorhanden.setFont(MainView.font_17_bold);
		lblNichtVorhanden.setBounds(22, 234, 800, 46);
		contentPane.add(lblNichtVorhanden);
		lblNichtVorhanden.setVisible(false);
		

		
		
		JButton btnAllesKlar = new JButton("Pr\u00FCfen!");
		btnAllesKlar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(MainView.handCursor());
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				setCursor(MainView.normalCursor());
			}
		});
		btnAllesKlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isNeeded = MLdb.initCheckExerciseAvailability((String) comboBox.getSelectedItem(), txtSeite.getText(), txtNummer.getText());
				if(!isNeeded)
					lblVorhanden.setVisible(true);
				else
					lblNichtVorhanden.setVisible(true);
				
				btnAllesKlar.setVisible(false);
			}
		});
		btnAllesKlar.setBounds(674, 237, 123, 40);
		btnAllesKlar.setFont(MainView.font_16_bold);
		btnAllesKlar.setContentAreaFilled(false);
		btnAllesKlar.setOpaque(true);
		btnAllesKlar.setForeground(Color.WHITE);
		btnAllesKlar.setBackground(MainView.darkBlack);
		contentPane.add(btnAllesKlar);
		
		

	}
}
