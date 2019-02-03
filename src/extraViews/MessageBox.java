package extraViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
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

public class MessageBox extends JFrame {

	private JPanel contentPane;
	
	private Color purple = new Color(128, 0, 128);

	public MessageBox(String title, String heading1, String text, String messageType) {
		
		if(messageType.equals("bigMessage"))
			makeBigView(title, heading1, text);
		
		else if(messageType.equals("smallMessage"))
			makeSmallMessage(title, heading1, text);
			
		else if(messageType.equals("badMessage"))
			makeBadMessage(title, heading1, text);
			
	}
	
	
	private void makeSmallMessage(String title, String heading1, String text) {
		
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 898, 290);
		setUndecorated(true);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, purple));
		
		JLabel lblHeading = new JLabel(heading1);
		lblHeading.setFont(MainView.font_19_bold);
		lblHeading.setBounds(22, 13, 782, 46);
		lblHeading.setForeground(Color.GRAY);
		contentPane.add(lblHeading);
		
		JTextArea textArea = new JTextArea(text);
		textArea.setEnabled(true);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBorder(new LineBorder(purple, 1, true));
		textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(), BorderFactory.createEmptyBorder(12, 12, 12, 12)));
		textArea.setWrapStyleWord(true);
		textArea.setBackground(Color.WHITE);
		textArea.setFont(MainView.font_16);
		textArea.setForeground(Color.DARK_GRAY);
		textArea.setBounds(22, 72, 833, 114);
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
		btnAllesKlar.setBounds(732, 227, 123, 40);
		btnAllesKlar.setFont(MainView.font_16_bold);
		btnAllesKlar.setFocusable(false);
		btnAllesKlar.setContentAreaFilled(false);
		btnAllesKlar.setBackground(purple);
		btnAllesKlar.setOpaque(true);
		btnAllesKlar.setForeground(Color.WHITE);
		contentPane.add(btnAllesKlar);
		
	}


	private void makeBigView(String title, String heading1, String text) {
		
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 898, 690);
		setUndecorated(true);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, purple));
		
		JLabel lblHeading = new JLabel(heading1);
		lblHeading.setFont(MainView.font_19_bold);
		lblHeading.setBounds(22, 13, 880, 46);
		lblHeading.setForeground(Color.GRAY);
		contentPane.add(lblHeading);
		
		text = text.replace("\\n", "\n");
		JTextArea textArea = new JTextArea(text);
		textArea.setEnabled(true);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBorder(new LineBorder(purple, 1, true));
		textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(), BorderFactory.createEmptyBorder(12, 12, 12, 12)));
		textArea.setWrapStyleWord(true);
		textArea.setBackground(Color.WHITE);
		textArea.setFont(MainView.font_16);
		textArea.setForeground(Color.DARK_GRAY);
		textArea.setBounds(22, 72, 833, 514);
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
		btnAllesKlar.setBounds(736, 614, 123, 40);
		btnAllesKlar.setFont(MainView.font_16_bold);
		btnAllesKlar.setFocusable(false);
		btnAllesKlar.setContentAreaFilled(false);
		btnAllesKlar.setBackground(purple);
		btnAllesKlar.setOpaque(true);
		btnAllesKlar.setForeground(Color.WHITE);
		contentPane.add(btnAllesKlar);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	

	public void makeTwoHeadingsMessage(String title, String heading1, String heading2, String text, String messageType) {
		
		MainView.grantWindowChange();
		
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 843, 356);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(purple);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeading1 = new JLabel(heading1);
		lblHeading1.setFont(MainView.font_19_bold);
		lblHeading1.setBounds(22, 13, 782, 46);
		lblHeading1.setForeground(Color.WHITE);
		contentPane.add(lblHeading1);
		
		JLabel lblHeading2 = new JLabel(heading2);
		lblHeading2.setForeground(Color.WHITE);
		lblHeading2.setFont(MainView.font_17_bold);
		lblHeading2.setBounds(32, 54, 782, 46);
		contentPane.add(lblHeading2);
		
		JTextArea textArea = new JTextArea(text);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(58, 100, 737, 131);
		textArea.setBackground(purple);
		textArea.setFont(MainView.font_16);
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
		btnAllesKlar.setFont(MainView.font_16_bold);
		btnAllesKlar.setContentAreaFilled(false);
		btnAllesKlar.setOpaque(true);
		btnAllesKlar.setForeground(Color.WHITE);
		btnAllesKlar.setBackground(MainView.darkBlack);
		contentPane.add(btnAllesKlar);
		
		
		
	}
	

	
	public MessageBox(ML_db MLdb, String title, String heading1, String text, JTextField txtSeite, JTextField txtNummer) {
		
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 845, 395);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblHeading1 = new JLabel(heading1);
		lblHeading1.setFont(MainView.font_19_bold);
		lblHeading1.setBounds(22, 23, 782, 46);
		lblHeading1.setForeground(Color.DARK_GRAY);
		contentPane.add(lblHeading1);
		
		JTextArea textArea = new JTextArea("Damit die L\u00F6sungsvorschl\u00E4ge-Funktion und die Feherkorrektur gut funktionieren, m\u00FCssen so viele Buchungss\u00E4tze wie m\u00F6glich vom Server abrufbar sein.\nHilf deinen Mitsch\u00FClerInnen und ver\u00F6ffentliche deine L\u00F6sung! ");
		textArea.setEnabled(true);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBorder(new LineBorder(purple, 2, true));
		textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(), BorderFactory.createEmptyBorder(12, 12, 12, 12)));
		textArea.setWrapStyleWord(true);
		textArea.setBackground(Color.WHITE);
		textArea.setFont(MainView.font_16_bold);
		textArea.setForeground(purple);
		textArea.setBounds(22, 82, 782, 83);
		contentPane.add(textArea);
		
		
		
		UIManager.put("ComboBox.background", Color.WHITE);
		UIManager.put("ComboBox.foreground", purple);
		UIManager.put("ComboBox.selectionBackground", purple);
		UIManager.put("ComboBox.selectionForeground", Color.WHITE);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1. Rechnungswesen-Buch", "2. Rechnungswesen-Buch", "3. Rechnungswesen-Buch", "4. Rechnungswesen-Buch", "5. Rechnungswesen-Buch"}));
		comboBox.setFont(MainView.font_16_bold);
		comboBox.setBounds(284, 191, 219, 40);
		comboBox.setBorder(new LineBorder(purple, 1, true));
		comboBox.setBorder(BorderFactory.createCompoundBorder(comboBox.getBorder(), BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		comboBox.setForeground(Color.BLACK);
		contentPane.add(comboBox);
		
		txtSeite.setFont(MainView.font_16_bold);
		txtSeite.setForeground(new Color(128, 128, 128));
		txtSeite.setBackground(Color.WHITE);
		txtSeite.setFont(MainView.font_17);
		txtSeite.setBorder(new LineBorder(new Color(128, 0, 128), 1, true));
		txtSeite.setBorder(BorderFactory.createCompoundBorder(txtSeite.getBorder(), BorderFactory.createEmptyBorder(3, 6, 3, 3)));
		txtSeite.setText("Seite");
		txtSeite.setBounds(526, 191, 90, 42);
		contentPane.add(txtSeite);
		txtSeite.setColumns(10);
		
		txtNummer.setForeground(new Color(128, 128, 128));
		txtNummer.setBackground(Color.WHITE);
		txtNummer.setFont(MainView.font_17);
		txtNummer.setBorder(new LineBorder(new Color(128, 0, 128), 1, true));
		txtNummer.setBorder(BorderFactory.createCompoundBorder(txtNummer.getBorder(), BorderFactory.createEmptyBorder(3, 6, 3, 3)));
		txtNummer.setText("Nummer");
		txtNummer.setBounds(640, 192, 90, 42);
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
				if(!MainView.databaseIsActive)
					return;
				
				dispose();
				MLdb.initUploadToDB((String) comboBox.getSelectedItem(), txtSeite.getText(), txtNummer.getText());
				MLdb.initSaveProject();
			}
		});
		btnAllesKlar.setBounds(540, 279, 123, 40);
		btnAllesKlar.setBackground(purple);
		btnAllesKlar.setForeground(Color.WHITE);
		btnAllesKlar.setContentAreaFilled(false);
		btnAllesKlar.setOpaque(true);
		btnAllesKlar.setFont(MainView.font_18);
		btnAllesKlar.setBorder(new LineBorder(purple, 2, true));
		contentPane.add(btnAllesKlar);
		
		JButton btnLieberNicht = new JButton("Lieber nicht");
		btnLieberNicht.setBounds(702, 286, 102, 27);
		btnLieberNicht.setBackground(Color.WHITE);
		btnLieberNicht.setForeground(purple);
		btnLieberNicht.setContentAreaFilled(false);
		btnLieberNicht.setOpaque(true);
		btnLieberNicht.setFont(MainView.font_14);
		btnLieberNicht.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		contentPane.add(btnLieberNicht);
		btnLieberNicht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				MLdb.initSaveProject();
			}
		});
		
		
		JLabel lblQuestion = new JLabel("Welche Aufgabe l\u00E4dst du hoch?");
		lblQuestion.setBounds(28, 205, 230, 16);
		lblQuestion.setForeground(Color.DARK_GRAY);
		lblQuestion.setFont(new Font("Roboto", Font.BOLD, 15));
		contentPane.add(lblQuestion);
	}
	
	
	public void makeBadMessage(String title, String heading1, String text) {
		
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
		lblHeading.setFont(MainView.font_19_bold);
		lblHeading.setBounds(22, 13, 782, 46);
		lblHeading.setForeground(Color.RED);
		contentPane.add(lblHeading);
		
		JTextArea textArea = new JTextArea(text);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(175, 72, 448, 157);
		textArea.setBackground(MainView.darkBlack);
		textArea.setFont(MainView.font_16);
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
		btnAllesKlar.setFont(MainView.font_16_bold);
		btnAllesKlar.setContentAreaFilled(false);
		btnAllesKlar.setOpaque(true);
		btnAllesKlar.setForeground(Color.BLACK);
		btnAllesKlar.setBackground(Color.RED);
		contentPane.add(btnAllesKlar);
	}

	
}
