package extraViews;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Anlagenbewertung.ML_Anlagenbewertung;
import Ausland.ML_Ausland;
import Controls.ML_Controls;
import Kalkulationen.ML_Kalkulationen;
import Personalverrechnung.ML_Personalverrechnung;
import Rechnungsausgleich.ML_Rechnungsausgleich;
import Standard_Einkauf_Verkauf.ML_Standard_Einkauf_Verkauf;
import Steuer.ML_Steuer;
import Tabellenkalkulation.ML_Tabellenkalkulation;
import Tabellenkalkulation.ML_TableSelectionTopics;
import Tabellenkalkulation.Table_Model;
import Tourismus.ML_Tourismus;
import View.MainController;
import View.MainModel;
import View.MainView;
import dbActivity.ML_db;
import stats.ML_Stats;

public class Setup_View extends JFrame{

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtJahrgangzahl;
	private JTextField txtEmail;
	private JTextField txtPasswortfrDiscount;
	private Color purple = new Color(128, 0, 128);
	private BufferedImage myPicture;

	private JTextField txtJahrgang;
	private JComboBox cbSchule;
	private Connection conn;
	private Statement st;
	private ResultSet rs;

	private String loginEmail;
	private ML_db MLdb;


	public Setup_View() {
		
		connect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 609);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		try {
			
			myPicture = ImageIO.read(new File("src/introLogo.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBounds(100, 230, 285, 81);
			getContentPane().add(picLabel);
			
		} catch (IOException e1) {e1.printStackTrace();}
		
		
		txtName = new JTextField();
		txtName.setForeground(new Color(128, 128, 128));
		txtName.setBackground(Color.WHITE);
		txtName.setFont(MainView.font_15);
		txtName.setBounds(565, 63, 233, 55);
		txtName.setBorder(new LineBorder(purple, 2, true));
		txtName.setBorder(BorderFactory.createCompoundBorder(txtName.getBorder(), BorderFactory.createEmptyBorder(3, 6, 3, 3)));
		contentPane.add(txtName);
		txtName.setText("Name (Vor + Nach)");
		txtName.setColumns(10);
		
		UIManager.put("ComboBox.background", Color.WHITE);
		UIManager.put("ComboBox.foreground", new Color(128, 128, 128));
		UIManager.put("ComboBox.selectionBackground", purple);
		UIManager.put("ComboBox.selectionForeground", new Color(128, 128, 128));

		String arryaSchoolTypes[] = { "HBLA", "HAK" };
		JComboBox cbSchule = new JComboBox(arryaSchoolTypes);
		cbSchule.setBorder(new LineBorder(purple, 2, true));
		cbSchule.setBorder(BorderFactory.createCompoundBorder(cbSchule.getBorder(), BorderFactory.createEmptyBorder(3, 6, 3, 3)));
		cbSchule.setEditable(true);
		cbSchule.setFont(MainView.font_15);
		cbSchule.setBounds(565, 143, 233, 55);
		contentPane.add(cbSchule);
		
		txtJahrgangzahl = new JTextField();
		txtJahrgangzahl.setForeground(new Color(128, 128, 128));
		txtJahrgangzahl.setText("Jahrgang (Zahl)");
		txtJahrgangzahl.setColumns(10);
		txtJahrgangzahl.setBorder(new LineBorder(purple, 2, true));
		txtJahrgangzahl.setBorder(BorderFactory.createCompoundBorder(txtJahrgangzahl.getBorder(), BorderFactory.createEmptyBorder(3, 6, 3, 3)));
		txtJahrgangzahl.setBackground(Color.WHITE);
		txtJahrgangzahl.setBounds(565, 221, 233, 55);
		txtJahrgangzahl.setFont(MainView.font_15);
		contentPane.add(txtJahrgangzahl);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(128, 128, 128));
		txtEmail.setText("Email");
		txtEmail.setColumns(10);
		txtEmail.setBorder(new LineBorder(purple, 2, true));
		txtEmail.setBorder(BorderFactory.createCompoundBorder(txtEmail.getBorder(), BorderFactory.createEmptyBorder(3, 6, 3, 3)));
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setBounds(565, 299, 233, 55);
		txtEmail.setFont(MainView.font_15);
		contentPane.add(txtEmail);
		
		txtPasswortfrDiscount = new JTextField();
		txtPasswortfrDiscount.setForeground(new Color(128, 128, 128));
		txtPasswortfrDiscount.setText("Neues Passwort (f\u00FCr disCount)");
		txtPasswortfrDiscount.setColumns(10);
		txtPasswortfrDiscount.setBorder(new LineBorder(purple, 2, true));
		txtPasswortfrDiscount.setBorder(BorderFactory.createCompoundBorder(txtPasswortfrDiscount.getBorder(), BorderFactory.createEmptyBorder(3, 6, 3, 3)));
		txtPasswortfrDiscount.setBackground(Color.WHITE);
		txtPasswortfrDiscount.setBounds(565, 377, 233, 55);
		txtPasswortfrDiscount.setFont(MainView.font_15);
		contentPane.add(txtPasswortfrDiscount);
		
		
		JButton btnLos = new JButton("Los geht's!");
		btnLos.setBackground(purple);
		btnLos.setForeground(Color.WHITE);
		btnLos.setContentAreaFilled(false);
		btnLos.setOpaque(true);
		btnLos.setFont(new Font("Roboto", Font.BOLD, 20));
		btnLos.setBorder(new LineBorder(purple, 2, true));
		btnLos.setBounds(565, 460, 233, 48);
		btnLos.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				if(!checkIFEmail_PasswordOK(txtEmail.getText(), "email")) {
					JOptionPane.showMessageDialog(null, "Diese Email-Addresse wird bereits verwendet!",
							"Nachricht", JOptionPane.PLAIN_MESSAGE);
					return;
				}

				writeInfoIntoFile(txtName.getText(), (String) cbSchule.getSelectedItem(), txtEmail.getText(), txtPasswortfrDiscount.getText(),
						txtJahrgangzahl.getText(), "student");
				dispose();
				startApp();

			}

		});
		contentPane.add(btnLos);
		
	}
	
	
	public void writeInfoIntoFile(String name, String school, String email, String password, String schoolClass, String tier) {

		PrintStream fileStream;

		try {

			fileStream = new PrintStream(new File("src/setup.txt"));
			fileStream.println("alreadyDone");
			fileStream = new PrintStream(new File("src/info.txt"));
			fileStream.println(name);
			fileStream.println(school);
			fileStream.println(email);
			fileStream.println(password);
			fileStream.println(schoolClass);
			fileStream.println(tier);
			fileStream.close();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

	public static void labelDesign(JLabel jl) {
		jl.setForeground(Color.WHITE);
		jl.setFont(new Font("Roboto", Font.BOLD, 18));
		jl.setForeground(Color.WHITE);
	}

	public boolean checkIFEmail_PasswordOK(String email_pass, String column) {

		if(!MainView.databaseIsActive)
			return false;

		try {
			
			st = conn.createStatement();
			rs = st.executeQuery("SELECT " + column + " FROM users WHERE " + column + " ='" + email_pass +"'");

			if(rs.next())
				return false;

			return true;

		} catch (SQLException e) {e.printStackTrace();}
		
		return false;

	}

	public void startApp() {

		if(!netIsAvailable()) {
			
			JOptionPane.showMessageDialog(null,
					"Sie brauchen eine Internetverbindung um das Programm zu starten!", "Nachricht",
					JOptionPane.PLAIN_MESSAGE);
			return;
			
		}

		MainView view = new MainView();

		MainModel model = new MainModel();

		Table_Model tab_Model = new Table_Model();

		MainController mainController = new MainController(view, model, tab_Model);

		LinkedList<MouseListener> llML = new LinkedList<MouseListener>();
		
		llML.addLast(new ML_Standard_Einkauf_Verkauf(mainController));
		llML.addLast(new ML_Rechnungsausgleich(mainController));
		llML.addLast(new ML_Steuer(mainController));
		llML.addLast(new ML_Tourismus(mainController));
		llML.addLast(new ML_Personalverrechnung(mainController));
		llML.addLast(new ML_Ausland(mainController));
		llML.addLast(new ML_Anlagenbewertung(mainController));
		llML.addLast(new ML_Kalkulationen(mainController));
		llML.addLast(new ML_Controls(mainController)); // is not in controllerList!!!
		MLdb = new ML_db(mainController, this);
		llML.addLast(MLdb);
		
		ML_Tabellenkalkulation ML_TABKALK = new ML_Tabellenkalkulation();
		llML.addLast(ML_TABKALK);
		
		llML.addLast(new ML_TableSelectionTopics(ML_TABKALK));
		llML.addLast(new ML_Stats(mainController));
		
		System.out.println("MouseListener-Size: " + llML.size());

		mainController.addMLList(llML);

	}

	public void connect() {

		if(!MainView.databaseIsActive)
			return;

		try {

			String url = "jdbc:mysql://localhost/db_usersolutions?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "");
			System.out.println("Database connection established");

			/*
			 * String url =
			 * "jdbc:mysql://serverrw.ferrari.tsn.at:3306/db_usersolutions?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			 * Class.forName ("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection
			 * (url,"root","juwalRegev1"); System.out.println
			 * ("Database connection established");
			 * 
			 * System.out.println("AFTER");
			 */

		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Es konnte keine Verbindung mit der Datenbank hergestellt werden.\nDas Programm wird ohne Datenbankverbindung gestartet.\n"
					+ "Einige Funktionen werden deshalb deaktiviert.",
					"Nachricht", JOptionPane.PLAIN_MESSAGE);
			e.printStackTrace();
			MainView.databaseIsActive = false;
			return;

		}

	}

	private static boolean netIsAvailable() {

		try {
			
			final URL url = new URL("http://www.google.com");
			final URLConnection conn = url.openConnection();
			conn.connect();
			conn.getInputStream().close();
			return true;
			
		} catch (Exception e) {e.printStackTrace();}

		return false;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public Connection getConn() {
		return conn;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public ML_db getML_db() {
		return MLdb;
	}

}
