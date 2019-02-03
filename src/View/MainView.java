package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import extraViews.EnterEmailForSession;
import extraViews.ExamSetupView;
import extraViews.ExerciseAvailabilityChecker_View;
import extraViews.InfoView;
import extraViews.InsertExamIDView;
import extraViews.MessageBox;
import extraViews.Personalverrechnung_Settings_View;
import extraViews.SearchForSessions;
import extraViews.TeacherRegistration;
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
	private JLabel entryOnSB;


	public static Font font_30;
	public static Font font_20;
	public static Font font_18;
	public static Font font_19_bold;
	public static Font font_17;
	public static Font font_17_bold;
	public static Font font_16;
	public static Font font_16_bold;
	public static Font font_15;
	public static Font font_14;

	/*
	public static Font font_15 = new Fonnt("Roboto", Font.PLAIN, 17);
	 */
	
	private static JTextArea txtAreaHints;

	public static int bsPanelMargin = 20;


	public static LinkedList<Buchungssatz> bsList = new LinkedList<Buchungssatz>();
	public static LinkedList<JLabel> llSearchNames = new LinkedList<JLabel>();
	public static HashMap<JPanel, String> hmPanelCodeID = new HashMap<JPanel, String>();
	
	public static ArrayList<Image> icons = new ArrayList<Image>();


	public static int jpMargin = 20;

	public static int numOfPanels = 1;
	private static JPanel panelMiddleTemp;
	public static JTextField numberLabel;
	

	public static final Color lightBlack = new Color(66, 66, 66);
	public static final Color middleBlack = new Color(45, 45, 45);
	public static final Color darkBlack = new Color(37, 37, 38);
	public static final Color darkDisCountBlue = new Color(2,92,153);
	public static final Color disCountBlue = new Color(0, 122, 204);
	public static final Color disCountGreen = new Color(9, 110, 109);
	public static final Color disCountPurple = new Color(105, 51, 166);
	public static final Color disCountBrown = new Color(197, 146, 119);
	public static final Color disCountDarkGreen = new Color(0, 92, 92);
	
	
	public static boolean isUploading = false;
	public static boolean noColorFade = false;
	public static boolean suggestionsEnabled = false;

	private static JPanel commentPanel;
	
	private JMenuBar menuBar;

	private JMenuBar tempMenuBar;

	public ArrayList<JPanel> groupPanelList;

	public static boolean inSession = false;

	private int topMargin_SessionComment = 20;

	private static JRadioButton onlyTeacherSolutions;
	
	public static boolean databaseIsActive = true;
	
	
	
	public MainView() {
		
		icons.add(Toolkit.getDefaultToolkit().getImage("src/taskbar16black.png"));
		icons.add(Toolkit.getDefaultToolkit().getImage("src/taskbar32.png"));
		icons.add(Toolkit.getDefaultToolkit().getImage("src/taskbar64.png"));
		setIconImages(icons);
		setFonts();
		
	}
	
	private void setFonts() {
		
		try {
			
			GraphicsEnvironment ge;
			
			font_14 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Roboto-Regular.ttf")).deriveFont(Font.PLAIN, 14f);
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			
			font_15 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Roboto-Regular.ttf")).deriveFont(Font.PLAIN, 15f);
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			font_16 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Roboto-Regular.ttf")).deriveFont(Font.PLAIN, 16f);
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			
			font_16_bold = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Roboto-Bold.ttf")).deriveFont(Font.PLAIN, 16f);
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			font_17 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Roboto-Regular.ttf")).deriveFont(Font.PLAIN, 17f);
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			font_17_bold = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Roboto-Bold.ttf")).deriveFont(17f);
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			font_18 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Roboto-Bold.ttf")).deriveFont(18f);
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			
			font_19_bold = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Roboto-Bold.ttf")).deriveFont(19f);
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			font_20 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Roboto-Bold.ttf")).deriveFont(20f);
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			font_30 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Roboto-Bold.ttf")).deriveFont(30f);
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    
		} catch (IOException|FontFormatException e) {e.printStackTrace();}
		
	}

	public void setUpBasicStuff() {
		
		setTitle("disCount");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {e.printStackTrace();}
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screen.width, screen.height);
		screenWidth = screen.width;
		screenHeight = screen.height;
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(MainView.middleBlack);
		
		
		setupSuchen();
		

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
		tempPanel.setPreferredSize(new Dimension(workPanel_Width, 0));
		tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		tempPanel.setBorder(new LineBorder(Color.BLACK, 2));
		contentPane.add(tempPanel, BorderLayout.LINE_END);
		tempPanel.setVisible(true);
		
		makeMenuBarWP();
		
	
		
		
		
		String[] namesEKVK = {"~  Handelswareneinkauf", "~  Handelswarenverkauf",
				"~  sonstiger Aufwand (20%)", "~  Wir gewähren nachtr. Rabatt", "~  Lieferant gewährt nachtr. Rabatt",
				"~  Wir senden Waren zurück", "~  Kunde sendet Waren zurück", "~  Wir lassen uns die Waren liefern",
				"~  Wir senden dem Kunden die Waren"};

		makeNewEntryOnSideBar("Standard Ein-/Verkauf  ", panelLeft, contentPane, screen, namesEKVK, llML.get(0));
		
		
		String[] namesRechnungsausgleich = {"~  allgemeiner Verbindlichkeitenausgleich", "~  allgemeiner Forderungsausgleich", "~  Wir zahlen Mahnspesen",
				"~  Wir verlangen Mahnspesen", "~  Wir zahlen Verzugszinsen", "~  Wir verlangen Verzugszinsen", "~  Kundenskonto",
				"~  Lieferantenskonto", "~  Überweisung der Kreditkartengesellschaft", "~  Abbuchung der Bankomatkartengesellschaft", "~  Buchungen mit Bankkonto"};

		makeNewEntryOnSideBar("Rechnungsausgleich      ", panelLeft, contentPane, screen, namesRechnungsausgleich, llML.get(1));
		
		String[] namesSteuer = {"~  Einkommensteuer/KESt/Grundsteuer Privatgrundstücke", "~  USt-Zahllast", "~  Lohnsteuer",
				"~  Grunderwerbssteuer (unbebaut)", "~  Kammerumlage"};

		makeNewEntryOnSideBar("Steuern/sonst. Spesen  ", panelLeft, contentPane, screen, namesSteuer, llML.get(2));
		
		
		String[] namesTourismus = {"~  Getränkeeinkauf", "~  Wareneinkauf mit Emballagen", "~  Lebensmitteleinkauf"};

		makeNewEntryOnSideBar("Tourismus                ", panelLeft, contentPane, screen, namesTourismus, llML.get(3));
	

		String[] namesPersonalverrechnung = {"~  Gehaltsbuchungen", "~  Geschäftsessen", "~  Fahrtkosten", "~  Kilometergeld Unternehmer", "~  Tagesgeld", "~  Nächtigungsgeld"};
		makeNewEntryOnSideBar("Personalverrechnung    ", panelLeft, contentPane, screen, namesPersonalverrechnung, llML.get(4));
		
		
		String[] namesAusland = {"~  IG Erwerb", "~  IG Erwerb Bezugskosten", "~  IG Erwerb Ausgleich",
				"~  IG Lieferung", "~  IG Lieferung Versandkosten", "~  IG Lieferung Ausgleich", "~  Import",
				 "~  Import Bezugskosten", "~  Import Ausgleich", "~  Export", "~  Export Bezugskosten", "~  Export Ausgleich", "~  Kursdifferenz"};
		makeNewEntryOnSideBar("Auslandsgeschäfte", panelLeft, contentPane, screen, namesAusland, llML.get(5));
		
		String[] namesAnlagenbewertung = {"~  Teilzahlung", "~  Anlagen in Bau Umbuchung", "~  Instandhaltung", "~  Anlagenaktivierung", "~  Anlagenausscheidung durch Verkauf"};
		makeNewEntryOnSideBar("Anlagenbewertung", panelLeft, contentPane, screen, namesAnlagenbewertung, llML.get(6));
		
		String[] namesKalkulationen = {"~  Bezugskalkulation", "~  Personalverrechnung", "~  Absatzkalkulation"};
		panel_GiveAway = makeNewEntryOnSideBar("Kalkulationen", panelLeft, contentPane, screen, namesKalkulationen, llML.get(7));
		
		
		String[] namesGroups = {"~  Meine Gruppen"};
		makeNewEntryOnSideBar("Gruppen", panelLeft, contentPane, screen, namesGroups, null);
		
		String[] namesStats = {"~  Skill", "~  Abhängigkeit", "~  Beirag zur Community"};
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
	
	private void setupSuchen() {
		
		if(suchenPanel == null) {
			
			suchenPanel = new JPanel();
			suchenPanel.setOpaque(false);
			suchenPanel.setBackground(new Color(0,0,0,0));
			suchenPanel.setPreferredSize(new Dimension(screenWidth, 210));
			suchenPanel.setLayout(null);
			contentPane.add(suchenPanel, BorderLayout.SOUTH);
			suchenPanel.setVisible(false);
			
		}
		
		else {
			
			suchenPanel.removeAll();
			ySearch = 0;
			
		}
		
		innerSuchenPanel = new JPanel();
		innerSuchenPanel.setBackground(MainView.darkDisCountBlue);
		innerSuchenPanel.setBounds(0, 170, screenWidth-25, 40);
		innerSuchenPanel.setLayout(null);
		suchenPanel.add(innerSuchenPanel);
		
		searchTempPanel = new JPanel();
		searchTempPanel.setBackground(MainView.darkBlack);
		searchTempPanel.setBounds(0, 0, screenWidth, 170);
		searchTempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		suchenPanel.add(searchTempPanel);
		searchTempPanel.setVisible(false);
		
		recommendPanel = new JPanel();
		recommendPanel.setBackground(MainView.darkBlack);
		recommendPanel.setPreferredSize(new Dimension(screenWidth-40, 3000));
		recommendPanel.setLayout(null);
		
		JScrollPane searchJSP = new JScrollPane(recommendPanel);
		searchJSP.setBorder(null);
		searchJSP.setPreferredSize(new Dimension(screenWidth, 170));
		searchTempPanel.add(searchJSP);
		searchJSP.setVisible(true);
		
		JLabel suchenLabel = new JLabel("Suchen:");
		suchenLabel.setBounds(20, 5, 400, 33);
		suchenLabel.setFont(font_18);
		innerSuchenPanel.add(suchenLabel);
		
		suchenField = new JTextField();
		View_SuperClass.txtFieldDesign(suchenField);
		suchenField.setBounds(100, 4, screenWidth - 700, 33);
		suchenField.setFont(font_16);
		suchenField.setBorder(new EmptyBorder(0, 10, 0, 0));
		suchenField.setText("Eingabe...");
		innerSuchenPanel.add(suchenField);
		
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
	
	
	

	
	public ArrayList<MessageBox> getPotentialMessages(){
		return ((ML_db) llML.get(9)).initConnect();
	}
	
	
	
	
	
	private JPanel makeNewEntryOnSideBar(String title, JPanel panelLeft, JPanel contentPane, Dimension screen, String[] links, MouseListener ML) {

		JPanel panelMiddle = makeMiddlePanel();
		JLabel sideBarSpacers = new JLabel();
		LinkedList<JLabel> labels = makeEntryOnSidebar(panelMiddle, title, links, ML, panelLeft, sideBarSpacers);
		
		if(!title.equals("Stats") && !title.equals("Gruppen"))
			makeSearchIcon(panelMiddle);
		
		else if(title.equals("Gruppen") && databaseIsActive)
			addAddGroupIcon(labels);
		
		else
			labels.get(0).removeAll();
		
		makeSBLabels(panelMiddle, title, links, ML, panelLeft, sideBarSpacers, labels);
		
		return panelMiddle;
		
	}
	
	
	private void addAddGroupIcon(LinkedList<JLabel> labels) {

		JLabel studentAdd = makeMenuLabels("src/addGroup.png", "Gruppe erstellen", 900, 4, 9, 21, 21);
		labels.get(0).add(studentAdd);
		
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
					
					if((title.equals("Stats") || title.equals("Gruppen")) && !databaseIsActive) {
						
						MessageBox mb = new MessageBox("Hinweis", "disCount wurde ohne Datenbankverbindung gestartet!\n", "Ohne funktionierende Verbindung zur Datenbank"
								+ "ist diese Funktion nicht funktionsfähig.", "smallMessage");
						mb.setVisible(true);
						return;
						
					}
					
					if(title.equals("Stats"))
						((ML_Stats) llML.get(12)).paintGraphs(panelMiddle, labels);
					
					else if(title.equals("Gruppen"))
							paintGroups(((ML_db) llML.get(9)).initGetAllGroups(), db_Model.myTier.equals("teacher"));
					
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
			    "Bitte haken Sie mind. einen gültigen Buchungssatz an",
			    "Abschreibungsregeln verletzt",
			    JOptionPane.WARNING_MESSAGE);
		
	}
	
	
	private void makeMenu(String[] namesEKVK, String[] namesRechnungsausgleich, String[] namesSteuer, String[] namesTourismus, String[] namesPersonalverrechnung, String[] namesAusland, String[] namesAnlagenbwertung, String[] namesKalkulationen) {
		
		JMenu menu;
		JMenuItem menuItem;
		JMenuItem menuItem2;
		JMenuItem menuItemEinst;
		JMenuItem menuItemAbs;
		JMenuItem menuItemSpeichern;
		JMenuItem menuItemÖffnen;
		JMenuItem menuItemPrint;
		JMenuItem menuItemAvailability;
		JMenuItem menuItemExam;
		JMenuItem menuItemInfo;
		JMenuItem menuItemRegisterAsTeacher;
		JMenuItem menuItemStartSession;
		JMenuItem menuItemJoinSession;

		menuBar = new JMenuBar();
		
		menuBar.setBorderPainted(false);
		menuBar.add(Box.createRigidArea(new Dimension(20, 35)));
		
		
		
		menu = new JMenu("DATEI");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
		menuBar.add(menu);

		setJMenuBar(menuBar);
		

		menuItemSpeichern = new JMenuItem("Projekt speichern", KeyEvent.VK_T);
		
		menuItemSpeichern.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemSpeichern.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	MessageBox mb = new MessageBox((ML_db) llML.get(9), "Auf Datenbank hochladen", "Hochladen!", "Damit die Lösungsvorschläge-Funktion und "
		    			+ "die Feherkorrektur gut funktionieren, müssen "
						+ "so viele Buchungssätze wie möglich vom Server abrufbar sein. Hilf deinen MitschülerInnen und veröffentliche deine Lösung! ", 
						new JTextField(), new JTextField());
		    	
		    	
				mb.setVisible(true);
		    }
		});
		
		menuItemSpeichern.getAccessibleContext().setAccessibleDescription(
				"Alle Buchungssätze löschen");
		menu.add(menuItemSpeichern);
		
		
		menu.addSeparator();
		

		menuItemÖffnen = new JMenuItem("Projekt öffnen", KeyEvent.VK_T);
		
		menuItemÖffnen.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemÖffnen.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	((ML_db) llML.get(9)).initOpenProject();
		    }
		});
		
		menuItemÖffnen.getAccessibleContext().setAccessibleDescription(
				"Alle Buchungssätze löschen");
		menu.add(menuItemÖffnen);
		
		
		menuItemPrint = new JMenuItem("Projekt drucken", KeyEvent.VK_T);
		
		menuItemPrint.setAccelerator(KeyStroke.getKeyStroke('P', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemPrint.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	((ML_db) llML.get(9)).initPrintProject();
		    }
		});
		
		menuItemPrint.getAccessibleContext().setAccessibleDescription(
				"Alle Buchungssätze löschen");
		menu.add(menuItemPrint);
		
		
		
		
		menu = new JMenu("LÖSCHEN");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "disCout menu");
		menuBar.add(menu);

		menuItem = new JMenuItem("Ausgewählte Buchungssätze löschen", KeyEvent.VK_T);
		menuBar.setForeground(Color.WHITE);
		menuItem.setAccelerator(KeyStroke.getKeyStroke('D', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	MainModel.deleteChecked(workPanel);
		    	
		    	if(MainView.inSession)
		    		((ML_db) llML.get(9)).initUpdateSessionContent(MainModel.getCodeOnWorkPanel());
		    	
		    	workPanel.repaint();
		    	
		    }
		});
		
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Ausgewählte Buchungssätze löschen");
		menu.add(menuItem);

		
		menu.addSeparator();
		

		menuItem2 = new JMenuItem("Alle Buchungssätze löschen", KeyEvent.VK_T);
		
		menuItem2.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItem2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	MainModel.deleteAll(workPanel);
		    	if(MainView.inSession)
		    		((ML_db) llML.get(9)).initUpdateSessionContent(MainModel.getCodeOnWorkPanel());
		    	workPanel.repaint();
		    }
		});
		
		menuItem2.getAccessibleContext().setAccessibleDescription(
				"Alle Buchungssätze löschen");
		menu.add(menuItem2);
		
		

		


		menu = new JMenu("ABSCHREIBEN");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
		menuBar.add(menu);

		setJMenuBar(menuBar);
		
		menuItemAbs = new JMenuItem("Ausgewählte Buchungssätze abschreiben", KeyEvent.VK_T);
		
		menuItemAbs.setAccelerator(KeyStroke.getKeyStroke('W', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemAbs.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	((ML_Anlagenbewertung) llML.get(6)).makeAbschreibung();
		    }
		});
		
		menuItemAbs.getAccessibleContext().setAccessibleDescription(
				"Ausgewählte Buchungssätze abschreiben");
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
		    	removeSuchenPanel();
		    }
		});
		
		menuItemSuchen2.getAccessibleContext().setAccessibleDescription(
				"Alle Buchungssätze löschen");
		menu.add(menuItemSuchen2);
		
		
		
		if(databaseIsActive) {
			
			menu = new JMenu("VERFÜGBARKEIT");
			menu.setMnemonic(KeyEvent.VK_N);
			menu.getAccessibleContext().setAccessibleDescription(
			        "Prüfe ob eine Aufgabe bereits hochgeladen wurde");
			menuBar.add(menu);
	
			setJMenuBar(menuBar);
			
	
			menuItemAvailability = new JMenuItem("Prüfen ob eine Aufgabe bereits hochgeladen wurde", KeyEvent.VK_T);
			
			menuItemAvailability.setAccelerator(KeyStroke.getKeyStroke('U', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			menuItemAvailability.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	ExerciseAvailabilityChecker_View ac = new ExerciseAvailabilityChecker_View((ML_db) llML.get(9));
					ac.setVisible(true);
			    }
			});
	
			menu.add(menuItemAvailability);
			
			
			menu = new JMenu("PRÜFUNG");
			menu.setMnemonic(KeyEvent.VK_N);
			menu.getAccessibleContext().setAccessibleDescription(
			        "Starte eine neue Prüfung");
			menuBar.add(menu);
	
			setJMenuBar(menuBar);
			
	
			menuItemExam = new JMenuItem("Neue Prüfung starten", KeyEvent.VK_T);
			
			menuItemExam.setAccelerator(KeyStroke.getKeyStroke('G', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			menuItemExam.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	ExamSetupView esv = ((ML_db) llML.get(9)).startExam();
					esv.setVisible(true);
			    }
			});
	
			menu.add(menuItemExam);
			
			menuItemExam = new JMenuItem("Prüfung beitreten", KeyEvent.VK_T);
			
			menuItemExam.setAccelerator(KeyStroke.getKeyStroke('B', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			menuItemExam.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	InsertExamIDView ieidv = ((ML_db) llML.get(9)).joinExam();
			    	ieidv.setVisible(true);
			    }
			});
	
			menu.add(menuItemExam);
			
			
			menuItemExam = new JMenuItem("Prüfung abgeben", KeyEvent.VK_T);
			
			menuItemExam.setAccelerator(KeyStroke.getKeyStroke('I', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			menuItemExam.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	((ML_db) llML.get(9)).initHandInExam();
			    }
			});
	
			menu.add(menuItemExam);
			
			
			
			
			
			
			menu = new JMenu("LEHRER");
			menu.setMnemonic(KeyEvent.VK_N);
			menu.getAccessibleContext().setAccessibleDescription(
			        "Als Lehrer registrieren");
			menuBar.add(menu);
	
			setJMenuBar(menuBar);
			
			menuItemRegisterAsTeacher = new JMenuItem("Als Lehrer registrieren", KeyEvent.VK_T);
			
			menuItemRegisterAsTeacher.setAccelerator(KeyStroke.getKeyStroke('L', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
			menuItemRegisterAsTeacher.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	TeacherRegistration tr = new TeacherRegistration((ML_db) llML.get(9));
			    	tr.setVisible(true);
			    }
			});
	
			menu.add(menuItemRegisterAsTeacher);
		
		}
		
		
		
		menu = new JMenu("SESSION");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Session starten");
		menuBar.add(menu);

		setJMenuBar(menuBar);
		
		menuItemStartSession = new JMenuItem("Übungssession starten", KeyEvent.VK_T);
		
		menuItemStartSession.setAccelerator(KeyStroke.getKeyStroke('T', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemStartSession.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	EnterEmailForSession eefs = new EnterEmailForSession((ML_db) llML.get(9), "Geben Sie die E-Mail-Addresse Ihres Partners ein!", "E-Mail", "Kontaktieren");
		    	eefs.setVisible(true);
		    }
		});

		menu.add(menuItemStartSession);
		
		menuItemJoinSession = new JMenuItem("Übungssession beitreten", KeyEvent.VK_T);
		
		menuItemJoinSession.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemJoinSession.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	SearchForSessions sfs = new SearchForSessions((ML_db) llML.get(9), "Geben Sie die E-Mail-Addresse Ihres Partners ein!", "E-Mail", "Suchen");
		    	sfs.setVisible(true);
		    }
		});

		menu.add(menuItemJoinSession);
	

		
		
		
		
		menu = new JMenu("INFO");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Informationen über das Programm");
		menuBar.add(menu);

		setJMenuBar(menuBar);
		
		menuItemInfo = new JMenuItem("Entwicklung", KeyEvent.VK_T);
		
		menuItemInfo.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItemInfo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	InfoView infView = new InfoView();
		    	infView.setVisible(true);
		    }
		});

		menu.add(menuItemInfo);
		
	}
	
	public void removeSuchenPanel() {
		suchenPanel.setVisible(false);
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
		
		setupSuchen();
		
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
	
	public void makeSuchenWorkSpace() {
		
		setupSuchen();
		
		searchTempPanel.setVisible(true);
		suchenPanel.setVisible(true);
		JRadioButton searchByClassButton = new JRadioButton();
		searchByClassButton.setBounds(screenWidth - 500, 6, 300, 30);
		searchByClassButton.setFont(font_17);
		searchByClassButton.setForeground(Color.WHITE);
		searchByClassButton.setBackground(MainView.darkDisCountBlue);
		searchByClassButton.setText("nach Klassen suchen");
		innerSuchenPanel.add(searchByClassButton);
		addXImage();
		suchenField.addKeyListener(new KeyListener() {
			
		    public void keyPressed(KeyEvent e) {
		    	
		    	if(e.getKeyChar() == 27)
		    		suchenPanel.setVisible(false);
		    	
		    	else {
		    		
		    		recommendPanel.removeAll();
		    		ySearch = 0;
		    		recommendPanel.repaint();
		    		ArrayList<String> resultList = ((ML_db) llML.get(9)).initSearchForStudents(suchenField.getText(), searchByClassButton.isSelected());
		    		
		    		for(int x = 0; x < resultList.size(); x++) {
		    			
		    			JLabel studentLabel = drawHits(((ML_db) llML.get(9)), resultList.get(x), "");
		    			addIconsToStudentLabel(studentLabel);
		    			addGroupInfo(studentLabel, resultList.get(x));
		    			
		    		}
		    		
		    	}
		    	
		    }

			@Override
			public void keyReleased(KeyEvent arg0) {}@Override public void keyTyped(KeyEvent arg0) {;}});
		
	}
	
	public void makeAllStudentsWorkSpace(ArrayList<String> studentList) {
		
		setupSuchen();
		
		searchTempPanel.setVisible(true);
		suchenPanel.setVisible(true);
		suchenField.setEditable(false);
		addXImage();
		
		for(int x = 0; x < studentList.size(); x++) {
			drawHits(null, studentList.get(x), "");
		}
		
	}

	private void doASearch(String searchText, String[] namesEKVK, String[] namesRechnungsausgleich, String[] namesSteuer, String[] namesTourismus, String[] namesPersonalverrechnung, String[] namesAusland, String[] namesAnlagenbwertung, String[] namesKalkulationen) {
		
		LinkedList<LinkedList<String>> allCalcs = new LinkedList<LinkedList<String>>();
		
		LinkedList<String> temp1 = new LinkedList<String>(Arrays.asList(namesEKVK));
		temp1.addLast("   :  Untermenü von EKVK:   Pfad://  EKVK/");
		LinkedList<String> temp2 = new LinkedList<String>(Arrays.asList(namesRechnungsausgleich));
		temp2.addLast("   :  Untermenü von Rechnungsausgleich:   Pfad://  Rechnungsausgleich/");
		LinkedList<String> temp3 = new LinkedList<String>(Arrays.asList(namesSteuer));
		temp3.addLast("   :  Untermenü von Steuer:   Pfad://  Steuer/");
		LinkedList<String> temp4 = new LinkedList<String>(Arrays.asList(namesTourismus));
		temp4.addLast("   :  Untermenü von Tourismus:   Pfad://  Tourismus/");
		LinkedList<String> temp5 = new LinkedList<String>(Arrays.asList(namesPersonalverrechnung));
		temp5.addLast("   :  Untermenü von Personalverrechnung:   Pfad://  EKVK/");
		LinkedList<String> temp6 = new LinkedList<String>(Arrays.asList(namesAusland));
		temp6.addLast("   :  Untermenü von Ausland:   Pfad://  Ausland/");
		LinkedList<String> temp7 = new LinkedList<String>(Arrays.asList(namesAnlagenbwertung));
		temp7.addLast("   :  Untermenü von Anlagenbewertung:   Pfad://  Anlagenbewertung/");
		LinkedList<String> temp8 = new LinkedList<String>(Arrays.asList(namesKalkulationen));
		temp8.addLast("   :  Untermenü von Kalkulationen:   Pfad://  Kalkulationen/");
		
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
					
			}
			
		}
			
	}
	
	
	
	private JLabel drawHits(MouseListener ML, String target, String path) {

		JLabel label = new JLabel(target + path);
		label.addMouseListener(ML);
		label.setBackground(lightBlack);
		label.setOpaque(true);
		label.setForeground(Color.WHITE);
		label.setFont(font_16);
		label.setBorder(new EmptyBorder(0, 30, 0, 0));
		label.setBounds(20,  ySearch+10, screenWidth/2+300, 30);
		recommendPanel.add(label);
		
		label.add(makeDecorationPanels(0, darkDisCountBlue));
		label.add(makeDecorationPanels(screenWidth/2+290, Color.CYAN));
		
		recommendPanel.add(label);
		ySearch = ySearch + 50;
		recommendPanel.repaint();
		
		return label;
		
	}
	
	private void addXImage() {
		
		JLabel escape = makeMenuLabels("src/escapeSmall.png", "Schließen", screenWidth - 60, 14, 8, 14, 14);
		innerSuchenPanel.add(escape);
		
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
		
		addPic = makeMenuLabels("src/addPic.png", "Buchungssatz erstellen", 20, -1, 8, 60, 60);
		menuPanel.add(addPic);
		
		checkedPic = makeMenuLabels("src/checkedPic.png", "Buchungssatz fertig", 90, -4, 8, 60, 60);
		menuPanel.add(checkedPic);
		
		tableViewPic = makeMenuLabels("src/table_icon.png", "Tabellenansicht", 195, -4, 10, 60, 60);
		menuPanel.add(tableViewPic);
		
		cloudPic = makeMenuLabels("src/cloudOFF.png", "Lösungsvorschläge", 310, 1, 8, 52, 51);
		menuPanel.add(cloudPic);
		
		bsCheck = makeMenuLabels("src/bsCheck.png", "Fehlerkorrektur", 410, 1, 8, 76, 68);
		menuPanel.add(bsCheck);
		bsCheck.setEnabled(false);
		
		JLabel collapsePic = makeMenuLabels("src/collapse.png", "Panel minimieren", 540, -5, 8, 60, 60);
		menuPanel.add(collapsePic);
		collapsePic.addMouseListener(new MouseListener() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
					collapseTempPanel(menuPanel);
			}
			
			@Override public void mouseExited(java.awt.event.MouseEvent e) {}@Override public void mousePressed(java.awt.event.MouseEvent e) {}@Override public void mouseReleased(java.awt.event.MouseEvent e) {	}});
		
	}
	
	
	public static JLabel makeMenuLabels(String source, String toolTipText, int x, int y, int mouseListener, int width, int height) {
		
		ImageIcon icon = new ImageIcon(source);
		JLabel picLabel = new JLabel(icon);
		picLabel.setBounds(x, y, width, height);
		picLabel.setFont(font_17);
		picLabel.addMouseListener(llML.get(mouseListener));
		picLabel.setToolTipText(toolTipText);
		return picLabel;
		
	}
	
	
	public static void showPopUp() {
		JOptionPane.showMessageDialog(contentPane, "Stellen Sie den Buchungssatz zuerst fertig!");
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
		tableLabelList.add(new JLabel("Identitätspreisverfahren"));
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
		
		final String code = MainModel.getCodeOnWorkPanel();
		MainModel.deleteAll(workPanel);
		
		if(grammarCheckPanel != null)
			removeExerciseSelectionPanel();
		
		grammarCheckPanel = new JPanel();
		grammarCheckPanel.setPreferredSize(new Dimension(MainView.workPanel_Width, 55));
		grammarCheckPanel.setBackground(Color.BLACK);
		grammarCheckPanel.setLayout(null);
		grammarCheckPanel.setBorder(new MatteBorder(3,0,0,0, MainView.disCountBlue));
		tempPanel.add(grammarCheckPanel);
		
		jSP.setPreferredSize(new Dimension(workPanel_Width + 1, screenHeight - 250));

		tempPanel.revalidate();
		tempPanel.repaint();
		
		JTextField txtClass = new JTextField("Jahrgang");
		tfDesignDBPanel(txtClass);
		txtClass.setBounds(10, 10, 70, 40);
		grammarCheckPanel.add(txtClass);
		txtClass.addMouseListener(((ML_Controls) llML.get(8)));

		
		JTextField txtPage = new JTextField("Seite");
		tfDesignDBPanel(txtPage);
		txtPage.setBounds(100, 10, 70, 40);
		grammarCheckPanel.add(txtPage);
		
		JTextField txtNumber = new JTextField("Nummer");
		tfDesignDBPanel(txtNumber);
		txtNumber.setBounds(190, 10, 70, 40);
		grammarCheckPanel.add(txtNumber);
		
		onlyTeacherSolutions = new JRadioButton();
		onlyTeacherSolutions.setBounds(workPanel_Width - 310, 20, 160, 20);
		onlyTeacherSolutions.setBackground(null);
		onlyTeacherSolutions.setForeground(Color.WHITE);
		onlyTeacherSolutions.setFont(MainView.font_15);
		onlyTeacherSolutions.setText("nur Lehrerlösungen");
		grammarCheckPanel.add(onlyTeacherSolutions);
		
		UIManager.put("ComboBox.background", MainView.darkDisCountBlue);
		UIManager.put("ComboBox.foreground", Color.WHITE);
		UIManager.put("ComboBox.selectionBackground", MainView.darkBlack);
		UIManager.put("ComboBox.selectionForeground", Color.WHITE);
		String count[] = {"1", "2", "3", "alle"};
		
		JComboBox solutionCount = new JComboBox();
		
		if(!check) {
			
			onlyTeacherSolutions.setBounds(workPanel_Width - 350, 20, 160, 20);

			solutionCount.setModel(new DefaultComboBoxModel(count));
			solutionCount.setBorder(new LineBorder(new Color(37, 37, 38), 2));
			solutionCount.setEditable(true);
			solutionCount.setFont(MainView.font_20);
			solutionCount.setBounds(workPanel_Width - 180, 12, 70, 30);
			grammarCheckPanel.add(solutionCount);
			
		}
		
		JLabel checkTHEMPic = makeMenuLabels("src/checkTHEM.png", "Los", workPanel_Width-90, 2, 8, 88, 55);
		menuPanel.add(checkTHEMPic);
		checkTHEMPic.addMouseListener(new MouseListener() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				
				if(check)
					((ML_db) llML.get(9)).initCheckBS(txtClass.getText() + "/" + txtPage.getText() + "/" + txtNumber.getText(), onlyTeacherSolutions.isSelected());
				else
					((ML_db) llML.get(9)).initShowSuggestions(txtClass.getText() + "/" + txtPage.getText() + "/" + txtNumber.getText(), onlyTeacherSolutions.isSelected(), (String) solutionCount.getSelectedItem());
			
			}

			@Override public void mouseEntered(java.awt.event.MouseEvent e) {}@Override public void mouseExited(java.awt.event.MouseEvent e) {}@Override public void mousePressed(java.awt.event.MouseEvent e) {}@Override public void mouseReleased(java.awt.event.MouseEvent e) {	}});
	
		grammarCheckPanel.add(checkTHEMPic);
		
		new Thread(new Runnable() {
		    private String code;
		    public Runnable init(String code) {
		        this.code = code;
		        return this;
		    }
		    @Override
		    public void run() {
		    	
		    	MainView.noColorFade = true;
		        ((ML_db) llML.get(9)).initOpenProject(MainModel.convertStringToLL_Char(this.code));
		        MainView.noColorFade = false;
		        
		    }
		}.init(code)).start();
		
	}
	
	
	
	
	private JTextField tfDesignDBPanel(JTextField tf) {
		
		tf.setBounds(10, 10, 70, 40);
		tf.setBackground(Color.BLACK);
		tf.setForeground(Color.WHITE);
		tf.setCaretColor(Color.WHITE);
		tf.setFont(font_15);
		View_SuperClass.highlightContent(tf);
		tf.setBorder(new MatteBorder(2,2,2,2, MainView.disCountBlue));
		tf.setBorder(BorderFactory.createCompoundBorder(tf.getBorder(), BorderFactory.createEmptyBorder(0, 3, 0, 0)));
		tf.addMouseListener(((ML_Controls) llML.get(8)));
		
		return tf;
		
	}
	
	public void removeExerciseSelectionPanel() {
		
		tempPanel.remove(grammarCheckPanel);
		jSP.setPreferredSize(new Dimension(workPanel_Width-3, screenHeight));
		tempPanel.revalidate();
		tempPanel.repaint();
		
	}
	
	
	public static void addNoteToCheckPanel(String msg) {
		
		grammarCheckPanel.remove(onlyTeacherSolutions);
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
		txtAnonymous.setFont(MainView.font_18);
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
		txtHeading.setFont(MainView.font_18);
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
		
		
		JMenu menu = new JMenu("PRÜFUNG");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Prüfung");
		menuBar.add(menu);

		
		JMenuItem menuItemExam = new JMenuItem("Prüfung abgeben", KeyEvent.VK_T);
		
		menuItemExam.setAccelerator(KeyStroke.getKeyStroke('I', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
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
	        		 
		            MessageBox msgBox = new MessageBox("Achtung!", "Du hast die Prüfungsumgebung verlassen", "Dein Lehrer wurde verständigt", "smallMessage");
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
	}
	
	
	
	
	
//----------------------------------GROUPS----------------------

	
	public void paintGroups(ArrayList<String> groupList, boolean isTeacher) {
		
		if(!isTeacher) {
			
			((JLabel) turnedOnPanel.getComponent(0)).setText("Gruppen können nur von Lehrern erstellt werden!");
			((JLabel) turnedOnPanel.getComponent(0)).remove(((JLabel) turnedOnPanel.getComponent(0)).getComponent(0));
			return;
			
		}
		
		if(groupPanelList == null)
			groupPanelList = new ArrayList<JPanel>();
		
		int margin = 70;
		
		for(int x = 0; x < groupList.size(); x++) {

			JLabel groupAdd = makeMenuLabels("src/addStudents.png", "Schüler hinzufügen", 665, 8, 9, 24, 14);
			
			JLabel allStudents = makeMenuLabels("src/allStudentsInGroup.png", "Alle Mitglieder dieser Gruppe anzeigen", 700, 3, 9, 24, 24);
			
			JLabel trashCan = makeMenuLabels("src/trashCan.png", "Gruppe löschen", 735, 6, 9, 17, 20);
			
			JRadioButton jrb = new JRadioButton();
			jrb.setBackground(MainView.lightBlack);
			jrb.setBounds(770, 0, 50, 30);
			
			JPanel groupPanel = new JPanel();
			groupPanel.setBounds(40, margin, 800, 30);
			groupPanel.setBackground(MainView.lightBlack);
			groupPanel.setLayout(null);
			turnedOnPanel.add(groupPanel);
			groupPanelList.add(groupPanel);
			
			JLabel groupLabel = new JLabel();
			groupLabel.setBounds(10, 1, 300, 30);
			groupLabel.setForeground(Color.WHITE);
			groupLabel.setFont(font_16);
			groupLabel.setText(groupList.get(x));
			groupPanel.add(groupLabel);
			
			groupPanel.add(groupAdd);
			groupPanel.add(allStudents);
			groupPanel.add(trashCan);
			groupPanel.add(jrb);
			
			margin += 40;
			
		}
		
		turnedOnPanel.repaint();
		
	}
	
	private void addIconsToStudentLabel(JLabel studentLabel) {
		
		JLabel studentAdd = makeMenuLabels("src/studentAdd.png", "Diesen Schüler hinzufügen", 900, 5, 9, 24, 18);
		studentLabel.add(studentAdd);
		
	}
	
	private void addGroupInfo(JLabel studentLabel, String studentInfo) {
		
		JLabel groupInfo = new JLabel();
		groupInfo.setBounds(500, 0, 200, 30);
		groupInfo.setFont(font_16);
		groupInfo.setForeground(Color.ORANGE);
		studentLabel.add(groupInfo);
		
		 String message = ((ML_db) llML.get(9)).initCheckIfStudentInGroup(studentInfo);
		 groupInfo.setText(message);
		
	}
	
	
	
	
//-----------------------------------------Sessions-----------------------------------------
	
	public void buildSessionEnvironment() {
		
		inSession = true;
		
		contentPane.remove(panelLeft);
		contentPane.setLayout(null);
		
		if(turnedOnPanel == null) { //then still startMiddlePanel
			
			JPanel startPanel = makeMiddlePanel();
			contentPane.add(startPanel);
			startPanel.setVisible(true);
			turnedOnPanel = startPanel;
			
		}
		
		turnedOnPanel.removeAll();
		turnedOnPanel.setPreferredSize(new Dimension(screenWidth - workPanel_Width - 30, 80000));
		
		JScrollPane middleSessionSP = new JScrollPane(turnedOnPanel);
		middleSessionSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		middleSessionSP.setBorder(null);
		middleSessionSP.setBounds(0, 0, screenWidth - workPanel_Width, screenHeight - 200);
		middleSessionSP.setVisible(true);
		contentPane.add(middleSessionSP);
		
		JPanel insertCommentPanel = new JPanel();
		insertCommentPanel.setBounds(0, screenHeight - 200, screenWidth - workPanel_Width, 80);
		insertCommentPanel.setLayout(null);
		insertCommentPanel.setBackground(MainView.darkBlack);
		contentPane.add(insertCommentPanel);
		
		JTextArea insertCommentArea = new JTextArea();
		insertCommentArea.setBounds(10, 10, screenWidth - workPanel_Width - 100, 60);
		insertCommentArea.setForeground(Color.BLACK);
		insertCommentArea.setCaretColor(Color.BLACK);
		insertCommentArea.setLineWrap(true);
		insertCommentArea.setWrapStyleWord(true);
		insertCommentArea.setText("...");
		
		JScrollPane insertCommentSP = new JScrollPane(insertCommentArea);
		insertCommentSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		insertCommentSP.setBorder(null);
		insertCommentSP.setBounds(10, 10, screenWidth - workPanel_Width - 100, 60);
		insertCommentSP.setVisible(true);
		insertCommentPanel.add(insertCommentSP);
		
		JLabel sendComment = makeMenuLabels("src/sendSessionComment.png", "Kommentar senden", screenWidth - workPanel_Width - 50, 29, 9, 24, 24);
		insertCommentPanel.add(sendComment);
		
		menuPanel.remove(menuPanel.getComponent(5));
		JLabel refreshLabel = makeMenuLabels("src/refresh.png", "Push Changes", 510, 5, 8, 39, 39);
		menuPanel.add(refreshLabel);
		
		JLabel exitLabel = makeMenuLabels("src/exitTable.png", "Session beenden", 580, 10, 8, 23, 28);
		menuPanel.add(exitLabel);
		
		contentPane.revalidate();
		contentPane.repaint();
		
	}
	
	public void printSessionComments(ArrayList<String> commentList, ArrayList<String> commitHistoryList, String myEmail) {
		
		boolean myComment = false;
		
		for(int x = 0; x < commentList.size(); x++) {
			
			if(commitHistoryList.get(x).equals(myEmail))
				myComment = true;
			
			printSessionComment(commentList.get(x), myComment);
			
			myComment = false;
			
		}

	}
	
	private void printSessionComment(String comment, boolean myComment) {
		
		int marginLeft;
		int marginOwnComment = 400;
		int marginOtherComment = 20;
		
		JScrollPane commentContainer = createAnswer(comment);
		
		if(myComment) {
			
			commentContainer.setBackground(MainView.disCountBlue);
			marginLeft = marginOwnComment;
			
		}
		
		else {
			
			commentContainer.setBackground(MainView.disCountPurple);
			marginLeft = marginOtherComment;
		
		}
		
		commentContainer.setBounds(marginLeft, topMargin_SessionComment , 300, 200);
		
		topMargin_SessionComment += 230;
		
		turnedOnPanel.add(commentContainer);
		
		contentPane.revalidate();
		contentPane.repaint();
		
	}
	




	public JPanel getWorkPanel() {
		return workPanel;
	}

	public void getMLList(LinkedList<MouseListener> llML) {
		this.llML = llML;
	}

	public void removeGroupFromMiddlePanel(JPanel groupPanel) {
		
		for(int x = 0; x < groupPanelList.size(); x++) {
			turnedOnPanel.remove(groupPanelList.get(x));
		}
		
	}

	public ArrayList<JPanel> getGroupPanelList() {
		return groupPanelList;
	}

	

	

}