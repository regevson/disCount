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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import View.MainView;
import dbActivity.ML_db;
import javax.swing.DefaultComboBoxModel;

public class MessageBox extends JFrame {

	private JPanel contentPane;

	public MessageBox(String title, String heading1, String text) {
		
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 843, 356);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(MainView.darkDisCountBlue);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeading = new JLabel(heading1);
		lblHeading.setFont(new Font("Roboto", Font.BOLD, 19));
		lblHeading.setBounds(22, 13, 782, 46);
		lblHeading.setForeground(Color.WHITE);
		contentPane.add(lblHeading);
		
		JTextArea textArea = new JTextArea(text);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(22, 72, 791, 157);
		textArea.setBackground(MainView.darkDisCountBlue);
		textArea.setFont(new Font("Roboto", Font.PLAIN, 16));
		textArea.setForeground(Color.WHITE);
		contentPane.add(textArea);
		
		JButton btnAllesKlar = new JButton("Alles klar!");
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
				dispose();
			}
		});
		btnAllesKlar.setBounds(674, 237, 123, 40);
		btnAllesKlar.setFont(new Font("Roboto", Font.BOLD, 16));
		btnAllesKlar.setContentAreaFilled(false);
		btnAllesKlar.setOpaque(true);
		btnAllesKlar.setForeground(Color.WHITE);
		btnAllesKlar.setBackground(MainView.darkBlack);
		contentPane.add(btnAllesKlar);
	}
	
	
	public MessageBox(String title, String heading1, String heading2, String text) {
		MainView.grantWindowChange();
		
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 843, 356);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(MainView.darkDisCountBlue);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeading1 = new JLabel(heading1);
		lblHeading1.setFont(new Font("Roboto", Font.BOLD, 19));
		lblHeading1.setBounds(22, 13, 782, 46);
		lblHeading1.setForeground(Color.WHITE);
		contentPane.add(lblHeading1);
		
		JLabel lblHeading2 = new JLabel(heading2);
		lblHeading2.setForeground(Color.WHITE);
		lblHeading2.setFont(new Font("Roboto", Font.BOLD, 17));
		lblHeading2.setBounds(32, 54, 782, 46);
		contentPane.add(lblHeading2);
		
		JTextArea textArea = new JTextArea(text);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(58, 100, 737, 131);
		textArea.setBackground(MainView.darkDisCountBlue);
		textArea.setFont(new Font("Roboto", Font.PLAIN, 16));
		textArea.setForeground(Color.WHITE);
		contentPane.add(textArea);
		
		JButton btnAllesKlar = new JButton("Alles klar!");
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
				dispose();
				MainView.forbidWindowChange();
			}
		});
		btnAllesKlar.setBounds(674, 237, 123, 40);
		btnAllesKlar.setFont(new Font("Roboto", Font.BOLD, 16));
		btnAllesKlar.setContentAreaFilled(false);
		btnAllesKlar.setOpaque(true);
		btnAllesKlar.setForeground(Color.WHITE);
		btnAllesKlar.setBackground(MainView.darkBlack);
		contentPane.add(btnAllesKlar);
		
		
		
	}
	

	
	public MessageBox(ML_db MLdb, String title, String heading1, String text, JTextField txtSeite, JTextField txtNummer) {
		
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 843, 356);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(MainView.darkDisCountBlue);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblHeading1 = new JLabel(heading1);
		lblHeading1.setFont(new Font("Roboto", Font.BOLD, 19));
		lblHeading1.setBounds(22, 23, 782, 46);
		lblHeading1.setForeground(Color.WHITE);
		contentPane.add(lblHeading1);
		
		JTextArea textArea = new JTextArea(text);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(22, 82, 782, 59);
		textArea.setBackground(MainView.darkDisCountBlue);
		textArea.setFont(new Font("Roboto", Font.PLAIN, 16));
		textArea.setForeground(Color.WHITE);
		contentPane.add(textArea);
		
		
		
		UIManager.put("ComboBox.background", new Color(37, 37, 38));
		UIManager.put("ComboBox.foreground", Color.WHITE);
		UIManager.put("ComboBox.selectionBackground", new Color(0, 117, 211));
		UIManager.put("ComboBox.selectionForeground", Color.WHITE);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1. Rechnungswesen-Buch", "2. Rechnungswesen-Buch", "3. Rechnungswesen-Buch", "4. Rechnungswesen-Buch", "5. Rechnungswesen-Buch"}));
		comboBox.setFont(new Font("Roboto", Font.BOLD, 16));
		comboBox.setBounds(284, 150, 219, 40);
		comboBox.setBorder(new LineBorder(new Color(37, 37, 38), 2));
		comboBox.setForeground(Color.BLACK);
		contentPane.add(comboBox);
		
		txtSeite.setFont(new Font("Roboto", Font.BOLD, 16));
		txtSeite.setText("Seite");
		txtSeite.setBounds(525, 149, 90, 42);
		contentPane.add(txtSeite);
		txtSeite.setColumns(10);
		
		txtNummer.setFont(new Font("Roboto", Font.BOLD, 16));
		txtNummer.setText("Nummer");
		txtNummer.setBounds(639, 150, 90, 42);
		contentPane.add(txtNummer);
		txtNummer.setColumns(10);
		
		
		
		JButton btnAllesKlar = new JButton("Hochladen!");
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
				dispose();
				MLdb.initUploadToDB((String) comboBox.getSelectedItem(), txtSeite.getText(), txtNummer.getText());
				MLdb.initSaveAstxt();
			}
		});
		btnAllesKlar.setBounds(535, 237, 123, 40);
		btnAllesKlar.setFont(new Font("Roboto", Font.BOLD, 16));
		btnAllesKlar.setContentAreaFilled(false);
		btnAllesKlar.setOpaque(true);
		btnAllesKlar.setForeground(Color.WHITE);
		btnAllesKlar.setBackground(MainView.darkBlack);
		contentPane.add(btnAllesKlar);
		
		JButton btnLieberNicht = new JButton("Lieber nicht");
		btnLieberNicht.setBounds(697, 244, 102, 27);
		btnLieberNicht.setFont(new Font("Roboto", Font.PLAIN, 13));
		btnLieberNicht.setContentAreaFilled(false);
		btnLieberNicht.setOpaque(true);
		btnLieberNicht.setForeground(Color.WHITE);
		btnLieberNicht.setBackground(MainView.darkBlack);
		contentPane.add(btnLieberNicht);
		
		btnLieberNicht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				MLdb.initSaveAstxt();
			}
		});
		
		
		JLabel lblQuestion = new JLabel("Welche Aufgabe l\u00E4dst du hoch?");
		lblQuestion.setBounds(22, 163, 422, 16);
		lblQuestion.setForeground(Color.WHITE);
		lblQuestion.setFont(new Font("Roboto", Font.BOLD, 15));
		contentPane.add(lblQuestion);
	}
	
	
	public MessageBox(String title, String heading1, String text, boolean verwarnung) {
		
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 843, 356);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(MainView.darkBlack);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeading = new JLabel(heading1);
		lblHeading.setFont(new Font("Roboto", Font.BOLD, 19));
		lblHeading.setBounds(22, 13, 782, 46);
		lblHeading.setForeground(Color.RED);
		contentPane.add(lblHeading);
		
		JTextArea textArea = new JTextArea(text);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(175, 72, 448, 157);
		textArea.setBackground(MainView.darkBlack);
		textArea.setFont(new Font("Roboto", Font.PLAIN, 16));
		textArea.setForeground(Color.RED);
		contentPane.add(textArea);
		
		JButton btnAllesKlar = new JButton("Verstanden!");
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
				dispose();
			}
		});
		btnAllesKlar.setBounds(674, 237, 123, 40);
		btnAllesKlar.setFont(new Font("Roboto", Font.BOLD, 16));
		btnAllesKlar.setContentAreaFilled(false);
		btnAllesKlar.setOpaque(true);
		btnAllesKlar.setForeground(Color.BLACK);
		btnAllesKlar.setBackground(Color.RED);
		contentPane.add(btnAllesKlar);
	}

	
}
