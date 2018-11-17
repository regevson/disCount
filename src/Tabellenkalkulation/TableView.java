package Tabellenkalkulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import View.MainView;

public class TableView extends JFrame{
	
	private JTable table;
	private DefaultTableModel tableModel;
	private int screenHeight;
	public static LinkedList<JPanel> addRowList;
	private JTextField commandField;
	private static JPanel leftPanel;
	private static int yTabelleLeft;
	private JPanel contentPane;
	private ML_Tabellenkalkulation ML;
	private int screenWidth;
	private JFrame jf;
	
	public TableView(ML_Tabellenkalkulation ML, int screenWidth, int screenHeight, String topic) {
		this.ML = ML;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		jf = new JFrame();
		jf.setTitle("disCount Tabellenansicht");
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setBounds(0, 0, screenWidth, screenHeight);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, screenWidth, screenHeight);
		contentPane.setBackground(MainView.darkDisCountBlue);
		jf.setContentPane(contentPane);
		jf.setVisible(true);
		
		
		createTableView(topic);
		
		jf.setIconImages(MainView.icons);
	}
	
	
	public void createTableView(String topic) {
		
		
		tableModel = new DefaultTableModel();
		JTable table = new JTable(tableModel);
		this.table = table;
		
		makeCommandView();
		
		tableModel.addColumn("Datum");tableModel.addColumn("Text");tableModel.addColumn("Menge");tableModel.addColumn("€/Stück");tableModel.addColumn("€ insgesamt");tableModel.addColumn("Nebenrechnungen");
		
		if(topic.equals("Identitätspreisverfahren")) {
			tableModel.addRow(new Object[]{"", "", "", "", "", ""});tableModel.addRow(new Object[]{"", "AB", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "1. Zukauf", "", "", "", ""});tableModel.addRow(new Object[]{"", "ZWS", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "", "", "", "", ""});tableModel.addRow(new Object[]{"", "1. Abfassung", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "ZWS", "", "", "", ""});tableModel.addRow(new Object[]{"", "", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "Soll EB", "", "", "", ""});tableModel.addRow(new Object[]{"", "Schwund", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "Ist EB", "", "", "", ""});tableModel.addRow(new Object[]{"", "Abwertung", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "Bilanzansatz", "", "", "", ""});tableModel.addRow(new Object[]{"", "", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "", "", "", "", ""});tableModel.addRow(new Object[]{"", "", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "", "", "", "", ""});
		}
		
		else if(topic.equals("Durchschnittspreisverfahren")) {
			tableModel.addRow(new Object[]{"", "", "", "", "", ""});tableModel.addRow(new Object[]{"", "AB", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "Abfassung", "", "", "", ""});tableModel.addRow(new Object[]{"", "ZWS", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "", "", "", "", ""});tableModel.addRow(new Object[]{"", "Zukauf", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "Durchschnitt", "", "", "", ""});tableModel.addRow(new Object[]{"", "", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "Soll EB", "", "", "", ""});tableModel.addRow(new Object[]{"", "Schwund", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "Ist EB", "", "", "", ""});tableModel.addRow(new Object[]{"", "Abwertung", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "Bilanzansatz", "", "", "", ""});tableModel.addRow(new Object[]{"", "", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "", "", "", "", ""});tableModel.addRow(new Object[]{"", "", "", "", "", ""});
			tableModel.addRow(new Object[]{"", "", "", "", "", ""});
		}
		
		tableModel.addTableModelListener(ML);
		
		 JTableHeader header = table.getTableHeader();
		 header.setOpaque(false);
	     header.setBackground(MainView.disCountBlue);
	     header.setBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK));
	     header.setFont(MainView.font_17_bold);
	     ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer())
	     .setHorizontalAlignment(JLabel.CENTER);
	     header.setForeground(Color.WHITE);
		
		
		Action doNothing = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	System.out.println(table.getValueAt(3, 1));
		    	System.out.println(table.getSelectedColumn() + "SLECTED ROW");
		    	ML.triggerModelEnterRoutine(table.getSelectedColumns(), table.getSelectedRows());
		    }
		};
		
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "ENTER");
		table.getActionMap().put("ENTER", doNothing);
		table.setColumnSelectionAllowed(true);
		table.setBounds(0, 0, 3000, 4000);
		table.setRowHeight(40);
		table.setBackground(new Color(232,232,232));
		table.setModel(tableModel);

		
		Color red = new Color(166, 18, 23);
		Color green = new Color(86, 109, 55);
		Color pink = new Color(189, 41, 125);
		Color orange = new Color(224, 107, 28);
		Color brown = new Color(128, 82, 48);
		
		TableColorRenderer.relationColors.add(red);
		TableColorRenderer.relationColors.add(green);
		TableColorRenderer.relationColors.add(pink);
		TableColorRenderer.relationColors.add(orange);
		TableColorRenderer.relationColors.add(brown);
		
		
	
		
		
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(320, 40, screenWidth-320, screenHeight-160);
		tableScrollPane.setBorder(new LineBorder(Color.BLACK, 3));
		tableScrollPane.setVisible(true);
		contentPane.add(tableScrollPane);
		
		table.setDefaultRenderer(Object.class, new TableColorRenderer());
		
		
		makeAddRowsFeature();
		makeAlgebraView();
		makeExitBar();
		
	}
	
	private void makeAddRowsFeature() {
		addRowList = new LinkedList<JPanel>();
		int y = 70;
		
		for(int x = 0; x < 22; x++) {
			JPanel panel = new JPanel();
			panel.setBackground(MainView.darkDisCountBlue);
			panel.setBorder(new MatteBorder(0,0,2,0, Color.BLACK));
			panel.setBounds(300, y, 20, 47);
			panel.addMouseListener(ML);
			addRowList.addLast(panel);
			
			ImageIcon barIcon = new ImageIcon("src/addRowIcon.png");
			JLabel barLabel = new JLabel(barIcon);
			barLabel.setBounds(10, 34, 10, 10);
			panel.add(barLabel);
			
			
			y = y + 40;
			contentPane.add(panel);
		}
	}
	
	private void makeAlgebraView() {
		leftPanel = new JPanel();
		leftPanel.setBounds(0, 0, 300, screenHeight-119);
		leftPanel.setLayout(null);
		contentPane.add(leftPanel);
		leftPanel.setBackground(MainView.middleBlack);
		yTabelleLeft = 30;
		contentPane.repaint();
	}
	
	private void makeCommandView() {
		JPanel commandPanel = new JPanel();
		commandPanel.setBounds(0, screenHeight-119, screenWidth, 40);
		commandPanel.setBackground(MainView.disCountBlue);
		commandPanel.setLayout(null);
		contentPane.add(commandPanel);
		
		JLabel eingabeLabel = new JLabel();
		eingabeLabel.setText("Eingabe:");
		eingabeLabel.setFont(MainView.font_18);
		eingabeLabel.setBounds(20, 10, 100, 20);
		commandPanel.add(eingabeLabel);
		
		JTextField commandField = new JTextField();
		this.commandField = commandField;
		commandField.setBounds(140, 5, screenWidth-200, 30);
		commandField.setBackground(MainView.darkBlack);
		commandField.setForeground(Color.WHITE);
		commandField.setCaretColor(Color.WHITE);
		commandField.setFont(MainView.font_15);
		commandField.addActionListener(ML);
		commandPanel.add(commandField);
	}
	
	public static void addToLeftPanel(String cmd) {
		JLabel label = new JLabel();
		label.setText(cmd);
		label.setBackground(MainView.darkBlack);
		label.setForeground(Color.WHITE);
		label.setOpaque(true);
		Border empty = new EmptyBorder(15,60,15,15);
		Border line = new LineBorder(MainView.disCountBlue, 2);
		label.setBorder(new CompoundBorder(line, empty));
		label.setBounds(1, yTabelleLeft, 297, 60);
		label.setFont(MainView.font_16);
		leftPanel.add(label);
		
		JRadioButton btn = new JRadioButton();
		btn.setBounds(0, 0, 50, 60);
		btn.setEnabled(false);
		btn.setBackground(MainView.disCountBlue);
		btn.setBorder(new EmptyBorder(0,15,0,0));
		label.add(btn);
		
		leftPanel.repaint();
		yTabelleLeft = yTabelleLeft + 80;
	}
	
	private void makeExitBar() {
		JPanel exitPanel = new JPanel();
		exitPanel.setBounds(0, 0, screenWidth, 40);
		exitPanel.setBackground(Color.BLACK);
		exitPanel.setLayout(null);
		contentPane.add(exitPanel);
		
		ImageIcon barIcon = new ImageIcon("src/exitTable.png");
		JLabel barLabel = new JLabel(barIcon);
		barLabel.setBounds(screenWidth-100, 0, 40, 40);
		barLabel.addMouseListener(ML);
			

		exitPanel.add(barLabel);
	}
	
	
	public void addToEingabeField(String cmd) {
		commandField.setText(cmd);
		commandField.repaint();
	}
	
	public JTable getTable() {
		return table;
	}
	
	public DefaultTableModel getDefaultTableModel() {
		return tableModel;
	}
	
	public String getCommand() {
		return commandField.getText();
	}
	
	public void doADispose() {
		jf.setVisible(false);
	}
	

}
