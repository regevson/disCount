package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import Anlagenbewertung.ML_Anlagenbewertung;
import Controls.ML_Controls;
import Kalkulationen.Absatzkalkulation_View;
import Kalkulationen.Bezugskalkulation_View;
import Kalkulationen.Personalverrechnung_View;
import Tabellenkalkulation.ML_TableSelectionTopics;
import dbActivity.Controller_dbActivity;
import dbActivity.ML_db;
import dbActivity.QuestionComment;
import dbActivity.db_Model;
import extraViews.ExamSetupView;
import extraViews.ExerciseAvailabilityChecker_View;
import extraViews.InfoView;
import extraViews.InsertExamIDView;
import extraViews.MessageBox;
import extraViews.Personalverrechnung_Settings_View;
import extraViews.View_SuperClass;
import stats.ML_Stats;



public class MainView extends JFrame {
	
public static boolean isBANNED = false;
	
	private static JPanel panel_GiveAway;
	private static JPanel contentPane;
	
	private int screenWidth;
	
	public static JPanel workPanel;
	public static int kalktxtWidth = 250;
	
	public static int kalkulationsOpen = 0;

	private static LinkedList<MouseListener> llML;
	private JPanel activePanel;
	private JLabel activeLabel;
	
	private int ySearch = 0;
	
	private JPanel turnedOnPanel;
	private JPanel suchenPanel;
	private JTextField suchenField;
	private JPanel innerSuchenPanel;
	private JPanel recommendPanel;
	private JPanel searchTempPanel;
	protected JMenuItem menuItemSuchen;
	private JMenuItem menuItemSuchen2;
	private static JScrollPane jSP;
	private JLabel tableViewPic;
	private int screenHeight;
	private JPanel selectionPanel;
	private JLabel addSolutions;
	private JLabel cloudPic;
	private JLabel bsCheck;

	private LinkedList<QuestionComment> questionList;

	private LinkedList<JButton> answerButtonList;

	private JPanel qnaPanel;

	private JPanel newQuestionPanel;

	private JPanel panelLeft;

	private static boolean legalWindowChange = false;
	private static JPanel grammarCheckPanel;
	public static JPanel menuPanel;
	
	public static boolean waitOK = true;

	private static JPanel tempPanel;
	public static JLabel addPic;
	public static JLabel checkedPic;
	public static int workPanel_Width;
	
	public static JLabel deleteSymbol;
	public static JLabel abschreibungSymbol;
	public static JLabel entryOnSB;


	public static Font font_30 = new Font("Roboto", Font.BOLD, 30);
	public static Font font_20 = new Font("Roboto", Font.BOLD, 20);
	public static Font font_18 = new Font("Roboto", Font.BOLD, 18);
	public static Font font_17 = new Font("Roboto", Font.PLAIN, 17);
	public static Font font_17_bold = new Font("Roboto", Font.BOLD, 17);
	public static Font font_16 = new Font("Roboto", Font.PLAIN, 16);
	public static Font font_15 = new Font("Roboto", Font.PLAIN, 15);
	
	private static JTextArea txtAreaHints;

	public static int margin = 20;


	public static LinkedList<JPanel> llJPanel = new LinkedList<JPanel>();
	public static LinkedList<JRadioButton> llRadioButton = new LinkedList<JRadioButton>();
	public static LinkedList<Double> llNettoPrices = new LinkedList<Double>();
	public static LinkedList<JLabel> llSearchNames = new LinkedList<JLabel>();
	public static LinkedList<ArrayList<ArrayList<JLabel>>> llThumbsGroups = new LinkedList<ArrayList<ArrayList<JLabel>>>();
	public static HashMap<JPanel, String> hmPanelCodeID = new HashMap<JPanel, String>();
	public static HashMap<JPanel, String> hmPanelToCode = new HashMap<JPanel, String>();
	
	public static ArrayList<Image> icons = new ArrayList<Image>();


	public static int jpMargin = 20;

	public static int numOfPanels = 1;
	private static JPanel panelMiddleTemp;
	public static JTextField numberLabel;

	//public static final Color lightBlack = new Color(36, 36, 36);
	public static final Color lightBlack = new Color(66, 66, 66);
	//public static final Color middleBlack = new Color(33, 33, 33);
	public static final Color middleBlack = new Color(45, 45, 45);
	public static final Color darkBlack = new Color(37, 37, 38);
	//public static final Color darkDisCountBlue = new Color(0, 0, 0);
	public static final Color darkDisCountBlue = new Color(2,92,153);
	//public static final Color disCountBlue = new Color(36, 36, 36);
	public static final Color disCountBlue = new Color(0, 122, 204);
	public static final Color disCountGreen = new Color(9, 110, 109);
	public static final Color disCountPurple = new Color(105, 51, 166);
	public static final Color disCountBrown = new Color(197, 146, 119);
	
	
	public static boolean isUploading = false;
	public static boolean suggestionsEnabled = false;

	private static JPanel commentPanel;
	
	private JMenuBar menuBar;

	private JMenuBar tempMenuBar;
	
	
	
	public MainView() {
		icons.add(Toolkit.getDefaultToolkit().getImage("src/taskbar16black.png"));
		icons.add(Toolkit.getDefaultToolkit().getImage("src/taskbar32.png"));
		icons.add(Toolkit.getDefaultToolkit().getImage("src/taskbar64.png"));
		setIconImages(icons);
	}
	
	



	public void setUpBasicStuff() {
		setTitle("disCount");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screen.width, screen.height);
		screenWidth = screen.width;
		screenHeight = screen.height;
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(lightBlack);
		
		suchenPanel = new JPanel();
		suchenPanel.setOpaque(false);
		suchenPanel.setBackground(new Color(0,0,0,0));
		suchenPanel.setPreferredSize(new Dimension(screen.width, 210));
		suchenPanel.setLayout(null);
		contentPane.add(suchenPanel, BorderLayout.SOUTH);
		suchenPanel.setVisible(false);
		
		innerSuchenPanel = new JPanel();
		innerSuchenPanel.setBackground(MainView.darkDisCountBlue);
		innerSuchenPanel.setBounds(0, 170, screen.width-25, 40);
		innerSuchenPanel.setLayout(null);
		suchenPanel.add(innerSuchenPanel);
		
		searchTempPanel = new JPanel();
		searchTempPanel.setBackground(MainView.darkBlack);
		searchTempPanel.setBounds(0, 0, screen.width, 170);
		searchTempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		suchenPanel.add(searchTempPanel);
		searchTempPanel.setVisible(false);
		
		recommendPanel = new JPanel();
		recommendPanel.setBackground(MainView.darkBlack);
		recommendPanel.setPreferredSize(new Dimension(screen.width-40, 3000));
		recommendPanel.setLayout(null);
		
		JScrollPane searchJSP = new JScrollPane(recommendPanel);
		searchJSP.setBorder(null);
		searchJSP.setPreferredSize(new Dimension(screen.width, 170));
		searchTempPanel.add(searchJSP);
		searchJSP.setVisible(true);
		
		JLabel suchenLabel = new JLabel("Suchen:");
		suchenLabel.setBounds(20, 5, 400, 33);
		suchenLabel.setFont(font_18);
		innerSuchenPanel.add(suchenLabel);
		
		suchenField = new JTextField();
		View_SuperClass.txtFieldDesign(suchenField);
		suchenField.setBounds(100, 4, screen.width-200, 33);
		suchenField.setFont(font_16);
		suchenField.setBorder(new EmptyBorder(0, 10, 0, 0));
		suchenField.setText("Eingabe...");
		innerSuchenPanel.add(suchenField);
		
		
	
		
		panelLeft = new JPanel();
		panelLeft.setVisible(true);
		panelLeft.setBackground(darkDisCountBlue);
		panelLeft.setBorder(new EmptyBorder(20, -13, 0, 0));
		panelLeft.setBounds(0, 40, 350, 10);
		panelLeft.setPreferredSize(new Dimension(300, 0));
		contentPane.add(panelLeft, BorderLayout.LINE_START);
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.PAGE_AXIS));
		
	
		
	
		workPanel_Width = 620;
		
		tempPanel = new JPanel();
		tempPanel.setBackground(new Color(51, 51, 51));
		//tempPanel.setBounds(0, 40, 100, 100);
		tempPanel.setPreferredSize(new Dimension(workPanel_Width, 0));
		tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		tempPanel.setBorder(new LineBorder(Color.BLACK, 2));
		contentPane.add(tempPanel, BorderLayout.LINE_END);
		tempPanel.setVisible(true);
		
		makeMenuBarWP();
		
		

		
		

		
		
		String[] namesEKVK = {"~  Handelswareneinkauf", "~  Handelswarenverkauf",
				"~  sonstiger Aufwand (20%)", "~  Wir gew�hren nachtr. Rabatt", "~  Lieferant gew�hrt nachtr. Rabatt",
				"~  Wir senden Waren zur�ck", "~  Kunde sendet Waren zur�ck", "~  Wir lassen uns die Waren liefern",
				"~  Wir senden dem Kunden die Waren"};

		makeNewEntryOnSideBar("Standard Ein-/Verkauf  ", panelLeft, contentPane, screen, namesEKVK, llML.get(0));
		
		
		String[] namesRechnungsausgleich = {"~  allgemeiner Verbindlichkeitenausgleich", "~  allgemeiner Forderungskeitenausgleich", "~  Wir zahlen Mahnspesen",
				"~  Wir verlangen Mahnspesen", "~  Wir zahlen Verzugszinsen", "~  Wir verlangen Verzugszinsen", "~  Kundenskonto",
				"~  Lieferantenskonto", "~  �berweisung der Kreditkartengesellschaft", "~  Abbuchung der Bankomatkartengesellschaft", "~  Buchungen mit Bankkonto"};

		makeNewEntryOnSideBar("Rechnungsausgleich      ", panelLeft, contentPane, screen, namesRechnungsausgleich, llML.get(1));
		
		String[] namesSteuer = {"~  Einkommensteuer/KESt/Grundsteuer Privatgrundst�cke", "~  USt-Zahllast", "~  Lohnsteuer",
				"~  Grunderwerbssteuer (unbebaut)", "~  Kammerumlage"};

		makeNewEntryOnSideBar("Steuern/sonst. Spesen  ", panelLeft, contentPane, screen, namesSteuer, llML.get(2));
		
		
		String[] namesTourismus = {"~  Getr�nkeeinkauf", "~  Wareneinkauf mit Emballagen", "~  Lebensmitteleinkauf"};

		makeNewEntryOnSideBar("Tourismus                ", panelLeft, contentPane, screen, namesTourismus, llML.get(3));
	

		String[] namesPersonalverrechnung = {"~  Gehaltsbuchungen", "~  Gesch�ftsessen", "~  Fahrtkosten", "~  Kilometergeld Unternehmer", "~  Tagesgeld", "~  N�chtigungsgeld"};
		makeNewEntryOnSideBar("Personalverrechnung    ", panelLeft, contentPane, screen, namesPersonalverrechnung, llML.get(4));
		
		
		String[] namesAusland = {"~  IG Erwerb", "~  IG Erwerb Bezugskosten", "~  IG Erwerb Ausgleich",
				"~  IG Lieferung", "~  IG Lieferung Versandkosten", "~  IG Lieferung Ausgleich", "~  Import",
				 "~  Import Bezugskosten", "~  Import Ausgleich", "~  Export", "~  Export Bezugskosten", "~  Export Ausgleich", "~  Kursdifferenz"};
		makeNewEntryOnSideBar("Auslandsgesch�fte", panelLeft, contentPane, screen, namesAusland, llML.get(5));
		
		String[] namesAnlagenbewertung = {"~  Teilzahlung", "~  Anlagen in Bau Umbuchung", "~  Instandhaltung", "~  Anlagenaktivierung", "~  Anlagenausscheidung durch Verkauf"};
		makeNewEntryOnSideBar("Anlagenbewertung", panelLeft, contentPane, screen, namesAnlagenbewertung, llML.get(6));
		
		String[] namesKalkulationen = {"~  Bezugskalkulation", "~  Personalverrechnung", "~  Absatzkalkulation"};
		panel_GiveAway = makeNewEntryOnSideBar("Kalkulationen", panelLeft, contentPane, screen, namesKalkulationen, llML.get(7));
		
		String[] namesStats = {"~  Skill", "~  Abh�ngigkeit", "~  Beirag zur Community"};
		makeNewEntryOnSideBar("Stats", panelLeft, contentPane, screen, namesStats, null);
		
		
		makeMenu(namesEKVK, namesRechnungsausgleich, namesSteuer, namesTourismus, namesPersonalverrechnung, namesAusland, namesAnlagenbewertung, namesKalkulationen);
		
		
		JLabel lblTemp = new JLabel();
		lblTemp.setBounds(100, 100, 30, 30);
		lblTemp.setBorder(new EmptyBorder(0, 100, 5, 0));
		lblTemp.setForeground(new Color(218, 218, 218));
		lblTemp.setLayout(null);
		panelLeft.add(lblTemp);
		
		txtAreaHints = new JTextArea();
		txtAreaHints.setForeground(Color.WHITE);
		txtAreaHints.setFont(font_16);
		txtAreaHints.setBorder(new EmptyBorder(0, 20, 5, 0));
		txtAreaHints.setEditable(false);
		txtAreaHints.setBounds(100, 100, 30, 30);
		txtAreaHints.setBackground(MainView.darkDisCountBlue);
		
		JScrollPane hintSP = new JScrollPane(txtAreaHints);
		hintSP.setBorder(null);
		hintSP.setBounds(100, 100, 20, 10);
		panelLeft.add(hintSP);
		
		
		
		workPanel = new JPanel();
		workPanel.setBackground(darkBlack);
		workPanel.setPreferredSize(new Dimension(workPanel_Width-20, screen.height + 30));
		
		workPanel.setLayout(null);
		workPanel.setVisible(true);
		
		jSP = new JScrollPane(workPanel);
		tempPanel.add(jSP);
		jSP.setBorder(null);
		jSP.setPreferredSize(new Dimension(workPanel_Width-3, screen.height));
		jSP.setVisible(true);
		
		
	}
	
	
	
	
	private void expandTempPanel(JPanel menuPanel) {
		if(grammarCheckPanel != null)
			grammarCheckPanel.setVisible(true);
		jSP.setVisible(true);
		menuPanel.setVisible(true);
		tempPanel.setPreferredSize(new Dimension(workPanel_Width, 0));
		tempPanel.setBackground(new Color(51, 51, 51));
		tempPanel.addMouseListener(null);
		tempPanel.repaint();
		contentPane.repaint();
	}
	
	private void collapseTempPanel(JPanel menuPanel) {
		if(grammarCheckPanel != null)
			grammarCheckPanel.setVisible(false);
		tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		jSP.setVisible(false);
		menuPanel.setVisible(false);
		tempPanel.setPreferredSize(new Dimension(20,0));
		tempPanel.setBackground(MainView.disCountBlue);
		tempPanel.repaint();
		contentPane.repaint();
		tempPanel.addMouseListener(new MouseListener() {
			
			
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				expandTempPanel(menuPanel);
				
			}
			
			public void mouseClicked(java.awt.event.MouseEvent e) {}@Override public void mouseExited(java.awt.event.MouseEvent e) {}@Override public void mousePressed(java.awt.event.MouseEvent e) {}@Override public void mouseReleased(java.awt.event.MouseEvent e) {	}});
		
		
	}

	

	
	
	private void makeExpandLabels(LinkedList<JLabel> visibleLabels, JPanel visiblePanel) {
		if(turnedOnPanel != null)
			turnedOnPanel.setVisible(false);
		
		visiblePanel.setVisible(true);
		turnedOnPanel = visiblePanel;
			
		int y = 25;
			
		for(int x = 0; x < visibleLabels.size(); x++) {
			visibleLabels.get(x).add(makeDecorationPanels(0, darkDisCountBlue));
			visibleLabels.get(x).add(makeDecorationPanels(950, Color.CYAN));
			visibleLabels.get(x).setBorder(new EmptyBorder(0, 30, 0, 0));
			visibleLabels.get(x).setBackground(lightBlack);
			visibleLabels.get(x).setOpaque(true);
			visibleLabels.get(x).setBounds(20, y, 960, 30);
			y = y + 45;
			visibleLabels.get(x).setVisible(true);
		}
		
		repaint();
	}
	
	public LinkedList<JLabel> makeLabels(int count, String[] names, MouseListener ML, JPanel panel) {
		LinkedList<JLabel> ll = new LinkedList<JLabel>();
		for (int x = 0; x < count; x++) {
			JLabel label = new JLabel(names[x]);
			label.setForeground(Color.WHITE);
			label.setFont(font_16);
			
			label.addMouseListener(ML);
			label.setVisible(false);
			panel.add(label);
			ll.add(label);
		}
		
		return ll;
		
	}
	
	public static JLabel addBasicToPanel(String item, int x, int y, int width, int height) {
		JLabel label = new JLabel(item);
		label.setBounds(x, y, width, height);
		label.setFont(new Font("Roboto", Font.BOLD, 17));
		label.setForeground(new Color(218, 218, 218));
		llJPanel.getLast().add(label);
		llJPanel.getLast().repaint();

		return label;
	}



	public static void addNoteToPanel(String note, JPanel panel, int x) {
		JLabel label = new JLabel(note);
		label.setBounds(x, 120, 500, 30);
		label.setFont(new Font("Roboto", Font.ITALIC, 16));
		label.setForeground(new Color(218, 218, 218));
		panel.add(label);
		panel.repaint();
		MainView.workPanel.repaint();
	}
	
	public static void addNoteToPanel(String note, JPanel panel, int x, int y) {
		JLabel label = new JLabel(note);
		label.setBounds(x, y, 500, 30);
		label.setFont(new Font("Roboto", Font.BOLD, 17));
		label.setForeground(Color.RED);
		panel.add(label);
		panel.repaint();
		MainView.workPanel.repaint();
		
		if(note.contains("Dar�ber"))
			db_Model.skill--;
	}
	
	public void addInfoToPanel(String name, String codeInfo, int upvotes, int downvotes, int commentCount, String uploader) {
		//JLabel infoLabel = makeMenuLabels("src/infoPic.png", 5, 20, 9, 20, 20);
		//numberLabel.add(infoLabel);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(60, 108, 523, 40);
		infoPanel.setBackground(darkBlack);
		infoPanel.setVisible(true);
		infoPanel.setLayout(null);
		llJPanel.getLast().add(infoPanel);
		
		addThumbs(infoPanel);
		addCommentIcon(infoPanel);
		
		createBSInfoContents(10, 100, "@" + name, infoPanel);
		createBSInfoContents(150, 50, codeInfo, infoPanel);
		createBSInfoContents(332, 50, Integer.toString(upvotes), infoPanel);//22
		createBSInfoContents(392, 100, Integer.toString(downvotes), infoPanel);
		createBSInfoContents(452, 100, Integer.toString(commentCount), infoPanel);
		
		String id = ((ML_db) llML.get(9)).initGetSolutionID();
		System.out.println("INMAINMODEL THIS IS ID " + id);
		hmPanelCodeID.put(infoPanel, id);
	}
	
	private void createBSInfoContents(int x, int width, String text, JPanel infoPanel) {
		JLabel label = new JLabel();
		label.setForeground(Color.WHITE);
		label.setFont(font_15);
		label.setText(text);
		label.setBounds(x, 2, width, 40);
		infoPanel.add(label);
	}
	
	
	
	private static JPanel makeRadioButton(JPanel jp) {
		JRadioButton jrb = new JRadioButton();
		jrb.setText("");
		jp.add(jrb);
		jrb.setBounds(550, 69, 30, 30);
		if(isUploading)
			jrb.setBackground(MainView.disCountGreen);
		else
			jrb.setBackground(new Color(37, 37, 38));
		
		if(isUploading)
			jrb.setSelected(true);
		
		llRadioButton.addLast(jrb);
		return jp;
	}
	
	
	public static JPanel createJPanel() {
		JPanel jp = new JPanel();
		jp.setBounds(5, jpMargin, 585, 150);
		jp.setBackground(null);
		if(isUploading) {
			jp.setBackground(MainView.disCountGreen);
			jp.setBorder(new LineBorder(disCountBrown, 2));
		}
		else {
			jp.setBackground(middleBlack);
			jp.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		}
		jp.setLayout(null);
		
		MainView.workPanel.add(jp);
		llJPanel.addLast(jp);
		MainView.workPanel.repaint();
		
		
		numberLabel = new JTextField();
		numberLabel.setFont(new Font("Roboto", Font.BOLD, 20));
		numberLabel.setForeground(Color.BLACK);
		numberLabel.setBackground(disCountBrown);

		numberLabel.setBounds(0, 0, 60, 150);
		numberLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		numberLabel.setEditable(false);
		jp.add(numberLabel);
		
		if(!isUploading)
			makePink(numberLabel, jp);
		
		
		
		paintNumberOnNumberLabel();
		resizeWorkPanel();
		
		addArrows(jp);
		
		jpMargin = jpMargin + 170;
		
		return makeRadioButton(jp);
	}
	
	
	public static void modifyJPanel(JPanel jp) {
		jp.setBounds(5, jpMargin - 170, 585, 300);
		numberLabel.setBounds(0, 0, 60, 300);
		MainView.workPanel.repaint();
	}
	
	public static void reModifyJPanel(JPanel jp) {
		jp.setBounds(5, jpMargin - 170, 585, 150);
		numberLabel.setBounds(0, 0, 60, 150);
		MainView.workPanel.repaint();
	}
	
	
	
	public static void addCheckMark(JPanel jp, ArrayList<Integer> examBSList, int bsNumber) {
		ImageIcon verifyIcon = new ImageIcon("src/verifyIt.gif");
		JLabel verifyLabel = new JLabel(verifyIcon);
		verifyLabel.setBounds(540, 4, 42, 40);
		jp.add(verifyLabel);
		
		db_Model.skill++;
		
		if(examBSList != null)
			examBSList.set(bsNumber, 1);
	}
	
	public static void addErrorIcon(JPanel jp) {
		ImageIcon errorIcon = new ImageIcon("src/error.gif");
		JLabel errorLabel = new JLabel(errorIcon);
		errorLabel.setBounds(538, 6, 39, 39);
		jp.add(errorLabel);
		
		db_Model.skill--;
	}
	
	private static void addThumbs(JPanel jp) {
		ArrayList<JLabel> llLabel = new ArrayList<JLabel>();
		ImageIcon thumbsUpIcon = new ImageIcon("src/thumbsup.png");
		JLabel thumbsUpLabel = new JLabel(thumbsUpIcon);
		thumbsUpLabel.setBounds(310, 12, 18, 18);
		
		ImageIcon thumbsDownIcon = new ImageIcon("src/thumbsdown.png");
		JLabel thumbsDownLabel = new JLabel(thumbsDownIcon);
		thumbsDownLabel.setBounds(370, 12, 18, 18);
		
		llLabel.add(thumbsUpLabel);
		llLabel.add(thumbsDownLabel);
		
		MainView.llThumbsGroups.getLast().add(llLabel);
		
		thumbsUpLabel.addMouseListener(((ML_db) llML.get(9)));
		thumbsDownLabel.addMouseListener(((ML_db) llML.get(9)));
		
		jp.add(thumbsUpLabel);
		jp.add(thumbsDownLabel);
	}
	
	private static void addCommentIcon(JPanel jp) {
		ImageIcon commentIcon = new ImageIcon("src/comment.png");
		JLabel commentLabel = new JLabel(commentIcon);
		commentLabel.setBounds(430, 12, 18, 18);
		jp.add(commentLabel);
		
		commentLabel.addMouseListener(((ML_db) llML.get(9)));
	}
	
	private static void addArrows(JPanel jp) {
		ImageIcon downIcon = new ImageIcon("src/downArrow.png");
		JLabel downLabel = new JLabel(downIcon);
		downLabel.setBounds(555, 123, 18, 12);
		jp.add(downLabel);
		downLabel.addMouseListener(((ML_Controls) llML.get(8)));
		
		ImageIcon upIcon = new ImageIcon("src/upArrow.png");
		JLabel upLabel = new JLabel(upIcon);
		upLabel.setBounds(555, 103, 18, 12);
		jp.add(upLabel);
		upLabel.addMouseListener(((ML_Controls) llML.get(8)));
		
	}
	
	public static void paintNumberOnNumberLabel() {
		for(int y = 0; y < MainView.llJPanel.size(); y++) {
			JTextField jtf = (JTextField) MainView.llJPanel.get(y).getComponent(0);
			jtf.setText(Integer.toString(y + 1));
		}
	}
	
	private static void resizeWorkPanel() {
		int height = (int) workPanel.getSize().getHeight();
		workPanel.setPreferredSize(new Dimension(workPanel_Width-20, height + 170));
		tempPanel.revalidate();
		tempPanel.repaint();
	}
	
	public static void addToPanel(JLabel jl) {
		llJPanel.getLast().add(jl);
	}
	
	public ArrayList<MessageBox> getPotentialMessages(LinkedList<MouseListener> llML_p){
		this.llML = llML_p;
		return ((ML_db) llML.get(9)).initConnect();
	}
	
	
	
	public static void makePink(JTextField jl, JPanel jp) {
		jl.setBackground(new Color(104, 33, 122));
		jp.setBorder(new LineBorder(new Color(104, 33, 122), 2));
		jl.repaint();
		
		new java.util.Timer().schedule(
				new java.util.TimerTask(){
					@Override
					public void run() {
						jl.setBackground(new Color(0, 117, 211));
						jp.setBorder(new LineBorder(new Color(0, 117, 211), 2));
						jl.repaint();}}, 1000);
	}
	
	private JPanel makeNewEntryOnSideBar(String title, JPanel panelLeft, JPanel contentPane, Dimension screen, String[] links, MouseListener ML) {

		JPanel panelMiddle = makeMiddlePanel();
		JLabel sideBarSpacers = new JLabel();
		LinkedList<JLabel> labels = makeEntryOnSidebar(panelMiddle, title, links, ML, panelLeft, sideBarSpacers);
		
		if(!title.equals("Stats"))
			makeSearchIcon(panelMiddle);
			
		makeSBLabels(panelMiddle, title, links, ML, panelLeft, sideBarSpacers, labels);
		
		return panelMiddle;
		
	}
	
	
	private JPanel makeMiddlePanel() {
		
		JPanel panelMiddle = new JPanel();
		panelMiddle.setBackground(middleBlack);
		panelMiddle.setFont(font_18);
		panelMiddle.setBounds(300, 0, 1200, screenHeight);
		contentPane.add(panelMiddle);
		panelMiddle.setLayout(null);
		panelMiddle.setVisible(false);
		
		return panelMiddle;
		
	}
	
	private void makeSearchIcon(JPanel panelMiddle) {
		
		ImageIcon barIcon = new ImageIcon("src/iphonex.png");
		JLabel barLabel = new JLabel(barIcon);
		barLabel.setBounds(155, screenHeight-160, 664, 32);
		panelMiddle.add(barLabel);
		barLabel.addMouseListener(new MouseListener() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				
				if(!suchenPanel.isVisible())
					de_ac_tivateSuchen(barLabel, screenHeight - 360, menuItemSuchen);
				
				else
					de_ac_tivateSuchen(barLabel, screenHeight - 160, menuItemSuchen2);
				
			}
			
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				setCursor(MainView.handCursor());
			}
			
			@Override public void mouseExited(java.awt.event.MouseEvent e) {setCursor(MainView.normalCursor());}@Override public void mousePressed(java.awt.event.MouseEvent e) {}@Override public void mouseReleased(java.awt.event.MouseEvent e) {	}});
		
		
	}
	
	private LinkedList<JLabel> makeEntryOnSidebar(JPanel panelMiddle, String title, String[] links, MouseListener ML, JPanel panelLeft, JLabel sideBarSpacers) {
		
		LinkedList<JLabel> labels = makeLabels(links.length, links, ML, panelMiddle);
		entryOnSB = new JLabel(title);
		sideBarSpacers.setBorder(new EmptyBorder(20, 5, 5, 81));
		sideBarSpacers.setPreferredSize(new Dimension(10, 30));
		sideBarSpacers.setBackground(darkDisCountBlue);
		
		return labels;
		
	}
	
	private void makeSBLabels(JPanel panelMiddle, String title, String[] links, MouseListener ML, JPanel panelLeft, JLabel sideBarSpacers, LinkedList<JLabel> labels) {
		
		
	
		llSearchNames.add(entryOnSB);
		Dimension dimension = new Dimension(310, 40);
		entryOnSB.setMaximumSize(dimension);
		entryOnSB.setMinimumSize(dimension);
		Dimension labelSize = entryOnSB.getSize();
		entryOnSB.setBorder(new EmptyBorder(20, 30, 20, 0));
		if(title.equals("Stats"))
			entryOnSB.setBackground(MainView.disCountPurple);
		else
			entryOnSB.setBackground(MainView.disCountBlue);
		entryOnSB.setOpaque(true);
		entryOnSB.setForeground(new Color(218, 218, 218));
		entryOnSB.setFont(font_18);
		panelLeft.add(entryOnSB);
		panelLeft.add(sideBarSpacers);
		JPanel panelActive = new JPanel();
		panelActive.setBounds(285, 0, 20, 100);
		panelActive.setBackground(Color.WHITE);
		panelActive.setVisible(false);
		
		
		entryOnSB.addMouseListener(new MouseListener() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				
			}
				@Override
				public void mouseEntered(java.awt.event.MouseEvent e) {
					JLabel triggeredLabel = ((JLabel) e.getSource());
					triggeredLabel.setBackground(lightBlack);
					setYourCursor(handCursor());
					
				}
				@Override
				public void mouseExited(java.awt.event.MouseEvent e) {
					JLabel triggeredLabel = ((JLabel) e.getSource());
					if(activeLabel != triggeredLabel) {
						if(title.equals("Stats"))
							entryOnSB.setBackground(MainView.disCountPurple);
						else
							triggeredLabel.setBackground(disCountBlue);
					}
					
					setYourCursor(normalCursor());
					
				}
				@Override
				public void mousePressed(java.awt.event.MouseEvent e) {
					for(int x = 0; x < llSearchNames.size(); x++) {
						
						if(llSearchNames.get(x).getText().equals("Stats"))
							llSearchNames.get(x).setBackground(MainView.disCountPurple);
						else
							llSearchNames.get(x).setBackground(disCountBlue);
						
					}
					if(activePanel != null)
						activePanel.setVisible(false);
					
					JLabel clickedLabel = ((JLabel) e.getSource());
					clickedLabel.add(panelActive);
					clickedLabel.setBackground(lightBlack);
					activePanel = panelActive;
					activeLabel = clickedLabel;
					activePanel.setVisible(true);
					
					
					if(Bezugskalkulation_View.mainPane != null)
						Bezugskalkulation_View.mainPane.setVisible(false);
					
					if(Personalverrechnung_View.mainPane != null)
						Personalverrechnung_View.mainPane.setVisible(false);
					
					if(Absatzkalkulation_View.mainPane != null)
						Absatzkalkulation_View.mainPane.setVisible(false);
					
					makeExpandLabels(labels, panelMiddle);
					
					if(title.equals("Stats"))
						((ML_Stats) llML.get(12)).paintGraphs(panelMiddle, labels);
					
					
				}
				@Override
				public void mouseReleased(java.awt.event.MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				});
		
	}
	
	
	private void de_ac_tivateSuchen(JLabel barLabel, int height, JMenuItem menuItem) {
		ActionEvent event = new ActionEvent(menuItem, ActionEvent.ACTION_PERFORMED, "Anything", System.currentTimeMillis(), 0);
		for (ActionListener listener : menuItem.getActionListeners()) {
		    listener.actionPerformed(event);
		}
		
		barLabel.setBounds(155, height, 664, 32);
		barLabel.repaint();
	}
	
	
	public static void warning_CheckCBoxes() {
		JLabel lblWarning = new JLabel();
		lblWarning.setBounds(100, 100, 600, 20);
		
		JOptionPane.showMessageDialog(panelMiddleTemp,
			    "Bitte haken Sie mind. einen g�ltigen Buchungssatz an",
			    "Abschreibungsregeln verletzt",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	
	private void makeMenu(String[] namesEKVK, String[] namesRechnungsausgleich, String[] namesSteuer, String[] namesTourismus, String[] namesPersonalverrechnung, String[] namesAusland, String[] namesAnlagenbwertung, String[] namesKalkulationen) {
		JMenu menu;
		JMenuItem menuItem;
		JMenuItem menuItem2;
		JMenuItem menuItem3;
		JMenuItem menuItemEinst;
		JMenuItem menuItemAbs;
		JMenuItem menuItemSpeichern;
		JMenuItem menuItem�ffnen;
		JMenuItem menuItemEinlesen;
		JMenuItem menuItemAvailability;
		JMenuItem menuItemExam;
		JMenuItem menuItemInfo;

		menuBar = new JMenuBar();
		
		menuBar.setBorderPainted(false);
		menuBar.add(Box.createRigidArea(new Dimension(20, 35)));
		
		
		
		menu = new JMenu("DATEI");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
		menuBar.add(menu);

		setJMenuBar(menuBar);
		

		menuItemSpeichern = new JMenuItem("als Textdatei speichern", KeyEvent.VK_T);
		
		menuItemSpeichern.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemSpeichern.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	MessageBox mb = new MessageBox((ML_db) llML.get(9), "Auf Datenbank hochladen", "Hochladen!", "Damit die L�sungsvorschl�ge-Funktion und "
		    			+ "die Feherkorrektur gut funktionieren, m�ssen "
						+ "so viele Buchungss�tze wie m�glich vom Server abrufbar sein. Hilf deinen Mitsch�lerInnen und ver�ffentliche deine L�sung! ", 
						new JTextField(), new JTextField());
		    	
		    	
				mb.setVisible(true);
		    }
		});
		
		menuItemSpeichern.getAccessibleContext().setAccessibleDescription(
				"Alle Buchungss�tze l�schen");
		menu.add(menuItemSpeichern);
		
		
		menu.addSeparator();
		

		menuItem�ffnen = new JMenuItem("Projekt �ffnen", KeyEvent.VK_T);
		
		menuItem�ffnen.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItem�ffnen.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	((ML_db) llML.get(9)).initOpenFileAndPrintToWorkPanel();
		    }
		});
		
		menuItem�ffnen.getAccessibleContext().setAccessibleDescription(
				"Alle Buchungss�tze l�schen");
		menu.add(menuItem�ffnen);
		
		menu.addSeparator();
		
		
		menuItemEinlesen = new JMenuItem("Aufgabe einlesen", KeyEvent.VK_T);
		
		menuItemEinlesen.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemEinlesen.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	einlesen();
		    }
		});
		
		menuItemEinlesen.getAccessibleContext().setAccessibleDescription(
				"Alle Buchungss�tze l�schen");
		menu.add(menuItemEinlesen);
		
		
		
		menu = new JMenu("L�SCHEN");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "disCout menu");
		menuBar.add(menu);

		menuItem = new JMenuItem("Ausgew�hlte Buchungss�tze l�schen", KeyEvent.VK_T);
		menuBar.setForeground(Color.WHITE);
		menuItem.setAccelerator(KeyStroke.getKeyStroke('D', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItem.addActionListener(new ActionListener() {
			java.awt.Rectangle r = getBounds();
		    public void actionPerformed(ActionEvent e) {
		    	MainModel.deleteChecked(true);
		    }
		});
		
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Ausgew�hlte Buchungss�tze l�schen");
		menu.add(menuItem);

		
		menu.addSeparator();
		

		menuItem2 = new JMenuItem("Alle Buchungss�tze l�schen", KeyEvent.VK_T);
		
		menuItem2.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItem2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	MainModel.contentForTxt.removeAll(MainModel.contentForTxt);
		    	MainModel.deleteAll();
		    }
		});
		
		menuItem2.getAccessibleContext().setAccessibleDescription(
				"Alle Buchungss�tze l�schen");
		menu.add(menuItem2);
		
		

		


		menu = new JMenu("ABSCHREIBEN");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
		menuBar.add(menu);

		setJMenuBar(menuBar);
		
		menuItemAbs = new JMenuItem("Ausgew�hlte Buchungss�tze abschreiben", KeyEvent.VK_T);
		
		menuItemAbs.setAccelerator(KeyStroke.getKeyStroke('W', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemAbs.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	((ML_Anlagenbewertung) llML.get(6)).makeAbschreibung();
		    }
		});
		
		menuItemAbs.getAccessibleContext().setAccessibleDescription(
				"Ausgew�hlte Buchungss�tze abschreiben");
		menu.add(menuItemAbs);
		
		
		
		menu = new JMenu("EINSTELLUNGEN");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
		menuBar.add(menu);

		setJMenuBar(menuBar);
		
		menuItemEinst = new JMenuItem("Personalverrechnung Einstellungen", KeyEvent.VK_T);
		
		menuItemEinst.setAccelerator(KeyStroke.getKeyStroke('K', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemEinst.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Personalverrechnung_Settings_View PVS_V = new Personalverrechnung_Settings_View();
		    }
		});
		
		menuItemEinst.getAccessibleContext().setAccessibleDescription(
				"Einstellungen");
		menu.add(menuItemEinst);
		
		
		menu = new JMenu("SUCHEN");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
		menuBar.add(menu);

		setJMenuBar(menuBar);
		
		menuItemSuchen = new JMenuItem("Suchen", KeyEvent.VK_T);
		
		menuItemSuchen.setAccelerator(KeyStroke.getKeyStroke('F', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemSuchen.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	makeSuchenWorkSpace(namesEKVK, namesRechnungsausgleich, namesSteuer, namesTourismus, namesPersonalverrechnung, namesAusland, namesAnlagenbwertung, namesKalkulationen);
		    }
		});
		
		menuItemSuchen.getAccessibleContext().setAccessibleDescription(
				"Einstellungen");
		menu.add(menuItemSuchen);
		
		
		menu.addSeparator();
		

		menuItemSuchen2 = new JMenuItem("Suchen ausblenden", KeyEvent.VK_T);
		
		menuItemSuchen2.setAccelerator(KeyStroke.getKeyStroke('M', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemSuchen2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	suchenPanel.setVisible(false);
		    }
		});
		
		menuItemSuchen2.getAccessibleContext().setAccessibleDescription(
				"Alle Buchungss�tze l�schen");
		menu.add(menuItemSuchen2);
		
		
		
		
		menu = new JMenu("VERF�GBARKEIT");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Pr�fe ob eine Aufgabe bereits hochgeladen wurde");
		menuBar.add(menu);

		setJMenuBar(menuBar);
		

		menuItemAvailability = new JMenuItem("Pr�fen ob eine Aufgabe bereits hochgeladen wurde", KeyEvent.VK_T);
		
		menuItemAvailability.setAccelerator(KeyStroke.getKeyStroke('U', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemAvailability.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ExerciseAvailabilityChecker_View ac = new ExerciseAvailabilityChecker_View((ML_db) llML.get(9));
				ac.setVisible(true);
		    }
		});

		menu.add(menuItemAvailability);
		
		
		menu = new JMenu("PR�FUNG");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Starte eine neue Pr�fung");
		menuBar.add(menu);

		setJMenuBar(menuBar);
		

		menuItemExam = new JMenuItem("Neue Pr�fung starten", KeyEvent.VK_T);
		
		menuItemExam.setAccelerator(KeyStroke.getKeyStroke('P', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemExam.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ExamSetupView esv = ((ML_db) llML.get(9)).startExam();
				esv.setVisible(true);
		    }
		});

		menu.add(menuItemExam);
		
		menuItemExam = new JMenuItem("Pr�fung beitreten", KeyEvent.VK_T);
		
		menuItemExam.setAccelerator(KeyStroke.getKeyStroke('B', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemExam.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	InsertExamIDView ieidv = ((ML_db) llML.get(9)).joinExam();
		    	ieidv.setVisible(true);
		    }
		});

		menu.add(menuItemExam);
		
		
		menuItemExam = new JMenuItem("Pr�fung abgeben", KeyEvent.VK_T);
		
		menuItemExam.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemExam.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	((ML_db) llML.get(9)).initHandInExam();
		    }
		});

		menu.add(menuItemExam);
		
		
		
		
		
		
		menu = new JMenu("INFO");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Informationen �ber das Programm");
		menuBar.add(menu);

		setJMenuBar(menuBar);
		

		menuItemInfo = new JMenuItem("Entwicklung", KeyEvent.VK_T);
		
		menuItemInfo.setAccelerator(KeyStroke.getKeyStroke('I', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemInfo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	InfoView infView = new InfoView();
		    	infView.setVisible(true);
		    }
		});

		menu.add(menuItemInfo);
		

	}
	
	public static JPanel getPanel() {
		return panel_GiveAway;
	}
	
	public static JPanel getConPane() {
		return contentPane;
	}
	
	
	public static void appendHint(String text) {
		txtAreaHints.append(text + "\n");
	}
	
	private void makeSuchenWorkSpace(String[] namesEKVK, String[] namesRechnungsausgleich, String[] namesSteuer, String[] namesTourismus, String[] namesPersonalverrechnung, String[] namesAusland, String[] namesAnlagenbwertung, String[] namesKalkulationen) {
		searchTempPanel.setVisible(true);
		suchenPanel.setVisible(true);
		addXImage();

		suchenField.addKeyListener(new KeyListener() {
		    public void keyPressed(KeyEvent e) {
		    	if(e.getKeyChar() == 27)
		    		suchenPanel.setVisible(false);
		    	
		    	else {
		    		recommendPanel.removeAll();
		    		ySearch = 0;
		    		recommendPanel.repaint();
		    		doASearch(suchenField.getText(), namesEKVK, namesRechnungsausgleich, namesSteuer, namesTourismus, namesPersonalverrechnung, namesAusland, namesAnlagenbwertung, namesKalkulationen);
		    	}
		    }

			@Override
			public void keyReleased(KeyEvent arg0) {
				
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void doASearch(String searchText, String[] namesEKVK, String[] namesRechnungsausgleich, String[] namesSteuer, String[] namesTourismus, String[] namesPersonalverrechnung, String[] namesAusland, String[] namesAnlagenbwertung, String[] namesKalkulationen) {
		LinkedList<LinkedList<String>> allCalcs = new LinkedList<LinkedList<String>>();
		
		LinkedList<String> temp1 = new LinkedList<String>(Arrays.asList(namesEKVK));
		temp1.addLast("   :  Untermen� von EKVK:   Pfad://  EKVK/");
		LinkedList<String> temp2 = new LinkedList<String>(Arrays.asList(namesRechnungsausgleich));
		temp2.addLast("   :  Untermen� von Rechnungsausgleich:   Pfad://  Rechnungsausgleich/");
		LinkedList<String> temp3 = new LinkedList<String>(Arrays.asList(namesSteuer));
		temp3.addLast("   :  Untermen� von Steuer:   Pfad://  Steuer/");
		LinkedList<String> temp4 = new LinkedList<String>(Arrays.asList(namesTourismus));
		temp4.addLast("   :  Untermen� von Tourismus:   Pfad://  Tourismus/");
		LinkedList<String> temp5 = new LinkedList<String>(Arrays.asList(namesPersonalverrechnung));
		temp5.addLast("   :  Untermen� von Personalverrechnung:   Pfad://  EKVK/");
		LinkedList<String> temp6 = new LinkedList<String>(Arrays.asList(namesAusland));
		temp6.addLast("   :  Untermen� von Ausland:   Pfad://  Ausland/");
		LinkedList<String> temp7 = new LinkedList<String>(Arrays.asList(namesAnlagenbwertung));
		temp7.addLast("   :  Untermen� von Anlagenbewertung:   Pfad://  Anlagenbewertung/");
		LinkedList<String> temp8 = new LinkedList<String>(Arrays.asList(namesKalkulationen));
		temp8.addLast("   :  Untermen� von Kalkulationen:   Pfad://  Kalkulationen/");
		
		allCalcs.add(temp1);
		allCalcs.add(temp2);
		allCalcs.add(temp3);
		allCalcs.add(temp4);
		allCalcs.add(temp5);
		allCalcs.add(temp6);
		allCalcs.add(temp7);
		allCalcs.add(temp8);
		
		for(int x = 0; x < allCalcs.size(); x++) {
			LinkedList<String> unterMenuArray = allCalcs.get(x);
			
			for(int y = 0; y < unterMenuArray.size()-1; y++) {
				String unterMenu = allCalcs.get(x).get(y);
				
					if(unterMenu.toLowerCase().contains(searchText.toLowerCase()))
						drawHits(llML.get(x), unterMenu, unterMenuArray.getLast() + unterMenu);
					
						
			}//inner for
		}//outer for	
			
	}
	
	
	
	private void drawHits(MouseListener ML, String target, String path) {
		JLabel jb = new JLabel(target + path);
		jb.addMouseListener(ML);
		jb.setBackground(lightBlack);
		jb.setOpaque(true);
		jb.setForeground(Color.WHITE);
		jb.setFont(font_16);
		jb.setBorder(new EmptyBorder(0, 30, 0, 0));
		jb.setBounds(20,  ySearch+10, screenWidth/2+300, 30);
		recommendPanel.add(jb);
		jb.add(makeDecorationPanels(0, darkDisCountBlue));
		jb.add(makeDecorationPanels(screenWidth/2+290, Color.CYAN));
		
		ySearch = ySearch + 50;
		recommendPanel.repaint();
	}
	
	private void addXImage() {
		BufferedImage xPic;
		try {
			xPic = ImageIO.read(new File("src/escapeSmall.png"));
			JLabel picLabel = new JLabel(new ImageIcon(xPic));
			picLabel.setBounds(screenWidth-60,  14,  14, 14);
			innerSuchenPanel.add(picLabel);
			picLabel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(java.awt.event.MouseEvent arg0) {
					suchenPanel.setVisible(false);
				}

				@Override
				public void mouseEntered(java.awt.event.MouseEvent arg0) {
					setYourCursor(handCursor());
					
				}

				@Override
				public void mouseExited(java.awt.event.MouseEvent arg0) {
					setYourCursor(normalCursor());
					
				}

				@Override
				public void mousePressed(java.awt.event.MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(java.awt.event.MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		}catch(Exception e) {
			
		}
	}
	
	
	
	private JPanel makeDecorationPanels(int x, Color color) {
		JPanel decPanel = new JPanel();
		decPanel.setBounds(x, 0, 20, 30);
		decPanel.setBackground(color);
		return decPanel;
	}
	
	public static Cursor handCursor() {
		return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR); 
	}
	
	public static Cursor normalCursor() {
		return Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR); 
	}
	
	public void setYourCursor(Cursor cursor) {
		setCursor(cursor);
	}
	
	public void makeMenuBarWP() {
		menuPanel = new JPanel();
		menuPanel.setPreferredSize(new Dimension(workPanel_Width-3, 60));
		menuPanel.setBackground(new Color(45,45,48));
		menuPanel.setLayout(null);
		menuPanel.setVisible(true);
		menuPanel.setBorder(new MatteBorder(0,0,3,0, MainView.disCountBlue));
		tempPanel.add(menuPanel);
		
		addPic = makeMenuLabels("src/addPic.png", 20, -1, 8, 60, 60);
		
		checkedPic = makeMenuLabels("src/checkedPic.png", 90, -4, 8, 60, 60);
		
		tableViewPic = makeMenuLabels("src/table_icon.png", 195, -4, 10, 60, 60);
		
		cloudPic = makeMenuLabels("src/cloudOFF.png", 310, 1, 8, 52, 51);
		
		bsCheck = makeMenuLabels("src/bsCheck.png", 410, 1, 8, 76, 68);
		bsCheck.setEnabled(false);
		
		JLabel collapsePic = makeMenuLabels("src/collapse.png", 540, -5, 8, 60, 60);
		collapsePic.addMouseListener(new MouseListener() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
					collapseTempPanel(menuPanel);
			}
			
			@Override public void mouseExited(java.awt.event.MouseEvent e) {}@Override public void mousePressed(java.awt.event.MouseEvent e) {}@Override public void mouseReleased(java.awt.event.MouseEvent e) {	}});
	}
	
	
	public static JLabel makeMenuLabels(String source, int x, int y, int mouseListener, int width, int height) {
		ImageIcon icon = new ImageIcon(source);
		JLabel picLabel = new JLabel(icon);
		picLabel.setBounds(x, y, width, height);
		picLabel.setFont(font_17);
		menuPanel.add(picLabel);
		picLabel.addMouseListener(llML.get(mouseListener));
		return picLabel;
	}
	
	
	public static void showPopUp() {
		JOptionPane.showMessageDialog(contentPane, "Stellen Sie den Buchungssatz zuerst fertig!");
	}
	
	private void einlesen() {
		
		JTextArea jta = new JTextArea("Kopieren Sie hier die Angabe hinein.");
		jta.setBackground(MainView.darkBlack);
		jta.setForeground(Color.WHITE);
		jta.setBorder(new EmptyBorder(10,10,10,10));
		jta.setFont(font_16);
		jta.setBounds(0,0,1,1);
		jta.setVisible(true);
		
		
		JScrollPane AAJSP = new JScrollPane(jta);
		AAJSP.setBounds(10, 40, 500, 300);
		AAJSP.setBorder(new LineBorder(MainView.darkDisCountBlue, 2));
		AAJSP.setVisible(true);
		workPanel.add(AAJSP);
		workPanel.repaint();
		
		
		JButton jb = new JButton("OK");
		jb.setBackground(MainView.darkBlack);
		jb.setForeground(Color.WHITE);
		jb.setContentAreaFilled(false);
		jb.setOpaque(true);
		jb.setFont(MainView.font_16);
		jb.setBorder(new LineBorder(new Color(0, 117, 211), 1));
		jb.setBounds(520, 100, 70, 30);
		jb.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	String aufgabe = jta.getText();
		    	jta.setVisible(false);
		    	jb.setVisible(false);
		    	((ML_Anlagenbewertung) llML.get(6)).aufgabeAuswerten(aufgabe);
		    }
		});
		workPanel.add(jb);
		workPanel.repaint();
		
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
	public void offerSelection() {
	
		selectionPanel = new JPanel();
		selectionPanel.setBounds(0, 0, workPanel_Width, 400);
		selectionPanel.setBackground(MainView.disCountBlue);
		selectionPanel.setLayout(null);
		
		if(MainView.workPanel.getComponentCount() >= 1) {
			for(int x = 0; x < MainView.workPanel.getComponentCount(); x++) {
				Component comp = MainView.workPanel.getComponent(x);
				comp.setVisible(false);
			}
		}
		
		workPanel.add(selectionPanel);
		
		LinkedList<JLabel> tableLabelList = new LinkedList<JLabel>();
		tableLabelList.add(new JLabel("Identit�tspreisverfahren"));
		tableLabelList.add(new JLabel("Durchschnittspreisverfahren"));
		
		int y = 20;
		
		for(int x = 0; x < tableLabelList.size(); x++) {
			JLabel label = tableLabelList.get(x);
			label.setForeground(Color.WHITE);
			label.setBackground(MainView.darkBlack);
			label.setOpaque(true);
			label.setBounds(0, y, 620, 60);
			label.addMouseListener((ML_TableSelectionTopics) llML.get(11));
			label.setBorder(new EmptyBorder(0,20,0,0));
			label.setFont(font_17);
			selectionPanel.add(label);
			y = y + 80;
		}
		
		
		
		
		
		
		workPanel.repaint();

		
	}
	
	public void disableSelection() {
		workPanel.remove(selectionPanel);
		workPanel.repaint();
	}
	
	public void shutDownFrame() {
		setVisible(false);
	}
	
	public void bootUpFrame() {
		setVisible(true);
	}
	
	
	
	public void addExerciseSelectionPanel(boolean check) {
		grammarCheckPanel = new JPanel();
		grammarCheckPanel.setPreferredSize(new Dimension(MainView.workPanel_Width, 55));
		grammarCheckPanel.setBackground(new Color(51, 51, 51));
		grammarCheckPanel.setLayout(null);
		grammarCheckPanel.setBorder(new MatteBorder(3,0,0,0, MainView.disCountBlue));
		tempPanel.add(grammarCheckPanel);
		
		jSP.setPreferredSize(new Dimension(workPanel_Width + 1, screenHeight - 250));
		
		

		tempPanel.revalidate();
		tempPanel.repaint();
		
		
		JTextField txtClass = new JTextField("Jahrgang");
		View_SuperClass.txtFieldDesign_THIN(txtClass);
		txtClass.setBounds(10, 10, 70, 40);
		txtClass.setFont(font_15);
		txtClass.setBorder(new LineBorder(MainView.disCountBlue, 1));
		txtClass.setBorder(BorderFactory.createCompoundBorder(txtClass.getBorder(), BorderFactory.createEmptyBorder(0, 3, 0, 0)));
		grammarCheckPanel.add(txtClass);
		
		JTextField txtPage = new JTextField("Seite");
		View_SuperClass.txtFieldDesign_THIN(txtPage);
		txtPage.setBounds(100, 10, 70, 40);
		txtPage.setFont(font_15);
		txtPage.setBorder(new LineBorder(MainView.disCountBlue, 1));
		txtPage.setBorder(BorderFactory.createCompoundBorder(txtPage.getBorder(), BorderFactory.createEmptyBorder(0, 3, 0, 0)));
		grammarCheckPanel.add(txtPage);
		
		JTextField txtNumber = new JTextField("Nummer");
		View_SuperClass.txtFieldDesign_THIN(txtNumber);
		txtNumber.setBounds(190, 10, 70, 40);
		txtNumber.setFont(font_15);
		txtNumber.setBorder(new LineBorder(MainView.disCountBlue, 1));
		txtNumber.setBorder(BorderFactory.createCompoundBorder(txtNumber.getBorder(), BorderFactory.createEmptyBorder(0, 3, 0, 0)));
		grammarCheckPanel.add(txtNumber);
		
		JLabel checkTHEMPic = makeMenuLabels("src/checkTHEM.png", workPanel_Width-90, 2, 8, 88, 55);
		checkTHEMPic.addMouseListener(new MouseListener() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if(check)
					((ML_db) llML.get(9)).initCheckBS(txtClass.getText(), txtPage.getText(), txtNumber.getText());
				else
					((ML_db) llML.get(9)).initShowSuggestions(txtClass.getText(), txtPage.getText(), txtNumber.getText());
			}

			@Override public void mouseEntered(java.awt.event.MouseEvent e) {}@Override public void mouseExited(java.awt.event.MouseEvent e) {}@Override public void mousePressed(java.awt.event.MouseEvent e) {}@Override public void mouseReleased(java.awt.event.MouseEvent e) {	}});
	
		grammarCheckPanel.add(checkTHEMPic);
		
	}
	
	public void removeExerciseSelectionPanel() {
		tempPanel.remove(grammarCheckPanel);
		jSP.setPreferredSize(new Dimension(workPanel_Width-3, screenHeight));
		tempPanel.revalidate();
		tempPanel.repaint();
	}
	
	
	public static void addNoteToCheckPanel(String msg) {
		JLabel label = new JLabel(msg);
		label.setBounds(265, 2, 270, 50);
		label.setFont(new Font("Roboto", Font.ITALIC, 15));
		label.setForeground(Color.WHITE);
		grammarCheckPanel.add(label);
		grammarCheckPanel.repaint();
	}
	
	





	public void openCommentsView(String heading, LinkedList<String> commentsList) {
		
		commentPanel = new JPanel();
		commentPanel.setBackground(middleBlack);
		commentPanel.setPreferredSize(new Dimension(workPanel_Width - 20, 3000));
		
		commentPanel.setLayout(null);
		commentPanel.setVisible(true);
		
		jSP.remove(workPanel);
		jSP.setViewportView(commentPanel);
		
		addHeading(commentPanel, heading);
		
		questionList = setUpComments(commentsList);
		
		printComments(questionList, commentPanel);
		
		addNewQuestionPanel();
		
	}
	
	private void addNewQuestionPanel() {
		System.out.println("new questionpanel");
		
		jSP.setPreferredSize(new Dimension(workPanel_Width + 5, screenHeight - 235));
		
		newQuestionPanel = new JPanel();
		newQuestionPanel.setPreferredSize(new Dimension(MainView.workPanel_Width, 40));
		newQuestionPanel.setLayout(null);
		newQuestionPanel.setBorder(new MatteBorder(3,0,0,0, MainView.disCountBlue));
		tempPanel.add(newQuestionPanel);
		
		JButton btnNewQuestion = new JButton("Neue Frage");
		btnNewQuestion.setForeground(Color.WHITE);
		btnNewQuestion.setFont(font_17_bold);
		btnNewQuestion.setContentAreaFilled(false);
		btnNewQuestion.setOpaque(true);
		btnNewQuestion.setBackground(new Color(37, 111, 21));
		btnNewQuestion.setBounds(0, 0, MainView.workPanel_Width, 40);
		btnNewQuestion.addMouseListener(((ML_db) llML.get(9)));
		newQuestionPanel.add(btnNewQuestion);
		
		tempPanel.revalidate();
		tempPanel.repaint();
	}
	
	private LinkedList<QuestionComment> setUpComments(LinkedList<String> list) {

		questionList = new LinkedList<QuestionComment>();
		answerButtonList = new LinkedList<JButton>();
		
		for(int x = 0; x < list.size(); x++) {
			
			if(list.get(x).equals("?")) {
				QuestionComment question = new QuestionComment();
				question.setContent(list.get(x + 1));
				questionList.addFirst(question);
			}
			
			else
				questionList.getFirst().addAnswer(list.get(x + 1));
			
			
			x = x + 1;

		}
		
		return questionList;
		
	}
	
	private void printComments(LinkedList<QuestionComment> questionList, JPanel commentPanel) {
		
		int currentMargin = 100;
		int questionMargin = 50;
		int questionHeight = 253;
		
		
		for(int x = 0; x < questionList.size(); x++) {
			
			QuestionComment question = questionList.get(x);
			JPanel questionPanel = createQuestion(question.getContent());
			questionPanel.setBounds(0, currentMargin, 595, questionHeight);
			commentPanel.add(questionPanel);
			currentMargin = currentMargin + questionHeight;
			
			ArrayList<String> answersList = question.getAnswers();
			currentMargin = printAnswers(answersList, commentPanel, currentMargin);
			
			currentMargin = currentMargin + questionMargin;
			
		}
	}
	
	
	





	private JPanel createQuestion(String question) {
		
		JPanel questionPanel = new JPanel();
		questionPanel.setBackground(middleBlack);
		questionPanel.setLayout(null);
		
		JPanel anonymousPanel = new JPanel();
		anonymousPanel.setBackground(darkBlack);
		anonymousPanel.setBounds(0, 0, 595, 56);
		questionPanel.add(anonymousPanel);
		anonymousPanel.setLayout(null);
		
		JTextField txtAnonymous = new JTextField();
		txtAnonymous.setForeground(Color.WHITE);
		txtAnonymous.setBackground(darkBlack);
		txtAnonymous.setBounds(60, 13, 100, 30);
		txtAnonymous.setBorder(null);
		txtAnonymous.setFont(new Font("Roboto", Font.BOLD, 18));
		txtAnonymous.setText("Anonymous");
		anonymousPanel.add(txtAnonymous);
		txtAnonymous.setColumns(10);
		
		JButton btnAntworten = new JButton("Antworten");
		btnAntworten.setForeground(Color.WHITE);
		btnAntworten.setContentAreaFilled(false);
		btnAntworten.setOpaque(true);
		btnAntworten.setBackground(disCountBlue);
		btnAntworten.setBounds(450, 13, 119, 29);
		btnAntworten.addMouseListener(((ML_db) llML.get(9)));
		anonymousPanel.add(btnAntworten);
		answerButtonList.addLast(btnAntworten);
		
		JPanel decorationPanel = new JPanel();
		decorationPanel.setBounds(0, 0, 45, 56);
		anonymousPanel.add(decorationPanel);
		decorationPanel.setBackground(disCountBlue);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 56, 595, 197);
		questionPanel.add(scrollPane);
		scrollPane.setBackground(darkBlack);
		scrollPane.setBorder(new MatteBorder(0, 4, 0, 0, disCountBlue));
		
		JTextArea txtArea = new JTextArea();
		txtArea.setText(question);
		scrollPane.setViewportView(txtArea);
		
		txtArea.setBackground(darkBlack);
		txtArea.setForeground(Color.WHITE);
		txtArea.setFont(font_16);
		txtArea.setBorder(new EmptyBorder(10, 10, 10, 0));
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		
		
		return questionPanel;
	}
	
	
	
	private int printAnswers(ArrayList<String> answerList, JPanel commentPanel, int currentMargin) {
		
		int heightAnswer = 144;
		int marginRight = 44;
		boolean left = true;
		
		for(int x = 0; x < answerList.size(); x++) {
			
			JScrollPane answerScrollPane = createAnswer(answerList.get(x));
			
			if(left) {
				answerScrollPane.setBounds(0, currentMargin, 549, 144);
				left = false;
			}
			
			else {
				answerScrollPane.setBackground(disCountBlue);
				answerScrollPane.setBounds(marginRight, currentMargin, 549, 144);
				left = true;
			}
			
			commentPanel.add(answerScrollPane);
			currentMargin = currentMargin + heightAnswer;
		}
		
		return currentMargin;
		
	}
	
	
	
	private JScrollPane createAnswer(String answer) {
		
		JTextArea txtArea = new JTextArea();
		txtArea.setText(answer);
		txtArea.setForeground(Color.WHITE);
		txtArea.setFont(font_16);
		txtArea.setOpaque(false);
		txtArea.setFont(font_16);
		txtArea.setBounds(45, 13, 515, 118);
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(true);
		scrollPane.setBackground(disCountPurple);
		scrollPane.setBorder(new EmptyBorder(7, 5, 5, 0));
		scrollPane.setViewportView(txtArea);
		
		return scrollPane;
		
	}
	
	
	

	
	private void addHeading(JPanel commentPanel, String heading) {
		
		JPanel headingPanel = new JPanel();
		headingPanel.setLayout(null);
		headingPanel.setBackground(disCountBlue);
		headingPanel.setBounds(0, 5, workPanel_Width - 20, 55);
		commentPanel.add(headingPanel);
		
		JTextField txtHeading = new JTextField();
		txtHeading.setForeground(Color.WHITE);
		txtHeading.setBackground(disCountBlue);
		txtHeading.setFont(MainView.font_18);
		txtHeading.setBounds(12, 16, 400, 29);
		txtHeading.setBorder(null);
		headingPanel.add(txtHeading);
		txtHeading.setText(heading);
		txtHeading.setColumns(10);
		
		ImageIcon icon = new ImageIcon("src/exit_white.png");
		JLabel picLabel = new JLabel(icon);
		picLabel.setBounds(530, 10, 36, 36);
		picLabel.setFont(font_17);
		headingPanel.add(picLabel);
		picLabel.addMouseListener(new MouseListener() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				closeCommentsView();
			}

			@Override public void mouseEntered(java.awt.event.MouseEvent e) {MainView.handCursor();}@Override public void mouseExited(java.awt.event.MouseEvent e) {MainView.normalCursor();}@Override public void mousePressed(java.awt.event.MouseEvent e) {}@Override public void mouseReleased(java.awt.event.MouseEvent e) {	}});
		
	
	}
	
	
		
	
	
	public void provideAnswerPanel(String type) {
		
		tempPanel.remove(newQuestionPanel);
		
		jSP.setPreferredSize(new Dimension(workPanel_Width + 1, screenHeight - 500));
		
		qnaPanel = new JPanel();
		qnaPanel.setPreferredSize(new Dimension(MainView.workPanel_Width, 300));
		qnaPanel.setForeground(UIManager.getColor("Menu.selectionBackground"));
		qnaPanel.setBorder(new MatteBorder(6, 0, 0, 0, (Color) UIManager.getColor("Menu.selectionBackground")));
		qnaPanel.setBackground(Color.BLACK);
		tempPanel.add(qnaPanel);
		qnaPanel.setLayout(null);
		
		ImageIcon icon = new ImageIcon("src/exit_white.png");
		JLabel picLabel = new JLabel(icon);
		picLabel.setBounds(570, 10, 36, 36);
		picLabel.setFont(font_17);
		qnaPanel.add(picLabel);
		picLabel.addMouseListener(((ML_db) llML.get(9)));
		
		JTextField txtHeading = new JTextField();
		txtHeading.setText(type + ":");
		txtHeading.setBorder(null);
		txtHeading.setForeground(UIManager.getColor("Menu.selectionBackground"));
		txtHeading.setFont(new Font("Roboto", Font.BOLD, 18));
		txtHeading.setColumns(10);
		txtHeading.setBackground(Color.BLACK);
		txtHeading.setBounds(44, 13, 240, 30);
		qnaPanel.add(txtHeading);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(4, 4, 4, 4));
		scrollPane.setBounds(44, 56, 527, 172);
		scrollPane.setOpaque(true);
		scrollPane.setBackground(middleBlack);
		qnaPanel.add(scrollPane);
		
		JTextArea dynamicarea = new JTextArea();
		dynamicarea.setBackground(middleBlack);
		dynamicarea.setForeground(Color.WHITE);
		dynamicarea.setFont(font_16);
		dynamicarea.setCaretColor(Color.WHITE);
		dynamicarea.setLineWrap(true);
		dynamicarea.setWrapStyleWord(true);
		dynamicarea.setText("...");
		scrollPane.setViewportView(dynamicarea);
		
		JButton btnAbschicken = new JButton("Abschicken");
		btnAbschicken.setForeground(Color.WHITE);
		btnAbschicken.setContentAreaFilled(false);
		btnAbschicken.setOpaque(true);
		btnAbschicken.setBackground(disCountPurple);
		btnAbschicken.setBounds(0, 260, 620, 38);
		btnAbschicken.addMouseListener(((ML_db) llML.get(9)));
		qnaPanel.add(btnAbschicken);
		
		tempPanel.add(qnaPanel);
		
		tempPanel.revalidate();
		tempPanel.repaint();
	}




	public void closeCommentsView() {
		tempPanel.remove(newQuestionPanel);
		
		jSP.remove(commentPanel);
		jSP.setViewportView(workPanel);
		jSP.setPreferredSize(new Dimension(workPanel_Width-3, screenHeight));
		jSP.repaint();
		
		tempPanel.revalidate();
		tempPanel.repaint();
	}
	
	public void closeAnswerPanel() {
		tempPanel.remove(qnaPanel);
		
		addNewQuestionPanel();
		
		tempPanel.revalidate();
		tempPanel.repaint();
	}
	
	
	public LinkedList<JButton> getAnswerButtonList() {
		return answerButtonList;
	}
	
	public LinkedList<QuestionComment> getQuestionList() {
		return questionList;
	}
	
	public JPanel getCommentPanel() {
		return commentPanel;
	}
	
	public void removeNewQuestionPanel() {
		tempPanel.remove(newQuestionPanel);
	}

	public void removeLeftPanelAndMenu() {
		tempMenuBar = new JMenuBar();
		tempMenuBar = menuBar;
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.add(Box.createRigidArea(new Dimension(20, 35)));
		
		
		
		JMenu menu = new JMenu("PR�FUNG");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Pr�fung");
		menuBar.add(menu);

		
		
		
		JMenuItem menuItemExam = new JMenuItem("Pr�fung abgeben", KeyEvent.VK_T);
		
		menuItemExam.setAccelerator(KeyStroke.getKeyStroke('H', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemExam.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	((ML_db) llML.get(9)).initHandInExam();
		    	setJMenuBar(tempMenuBar);
		    }
		});

		menu.add(menuItemExam);
		setJMenuBar(menuBar);
		this.panelLeft.setVisible(false);
		
	}
	
	public void showLeftPanel() {
		menuBar.setVisible(true);
		this.panelLeft.setVisible(true);
	}

	public void setWindowListener() {
		
		this.addWindowListener(new WindowAdapter() {

	         @Override
	         public void windowIconified(WindowEvent wEvt) {
	            ;
	         }

	         @Override
	         public void windowDeactivated(WindowEvent wEvt) {
	        	 if(!legalWindowChange && Controller_dbActivity.inExam) {
		            MessageBox msgBox = new MessageBox("Achtung!", "Du hast die Pr�fungsumgebung verlassen", "Dein Lehrer wurde verst�ndigt");
		            msgBox.setVisible(true);
		            ((ML_db) llML.get(9)).initNotifyTeacherOfLeave();
		            removeMe();
		            setJMenuBar(tempMenuBar);
		         }
	        	 
	        	 else if(!legalWindowChange && !Controller_dbActivity.inExam)
	        		 removeMe();
	         }
	         
	         private void removeMe() {
	        	 removeWindowListener(this);
	         }

	      });
	}
	
	public static void grantWindowChange() {
		legalWindowChange = true;
	}
	
	public static void forbidWindowChange() {
		legalWindowChange = false;
		System.out.println("isFORBIDDEN!!");
	}



	
	
	
}