package extraViews;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.imageio.ImageIO;
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
import disCount.Main;
import stats.ML_Stats;

public class Setup_View extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtName;

	private JTextField txtJahrgang;
	private JComboBox cbSchule;
	private JTextField txtEmail;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	
	private String loginEmail;
	
	
	

	public Setup_View() {
		connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
		setBounds(0, 0, 1200, 600);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(51, 51, 51));
		setContentPane(contentPane);
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("src/logo.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBounds(50,  0,  500, 600);
			getContentPane().add(picLabel);
			
			txtName = new JTextField();
			txtName.setText("");
			contentPane.add(txtName);
			txtName.setBounds(701,  210,  371, 45);
			View_SuperClass.txtFieldDesign(txtName);
			txtName.setColumns(10);
			
			UIManager.put("ComboBox.background", Color.WHITE);
			UIManager.put("ComboBox.foreground", Color.BLACK);
			UIManager.put("ComboBox.selectionBackground", new Color(0, 117, 211));
			UIManager.put("ComboBox.selectionForeground", Color.WHITE);
			
			String arryaSchoolTypes[] = {"HBLA", "HAK"};
			cbSchule = new JComboBox(arryaSchoolTypes);
			cbSchule.setBorder(new LineBorder(new Color(37, 37, 38), 2));
			cbSchule.setEditable(true);
			cbSchule.setFont(new Font("Roboto", Font.BOLD, 20));
			cbSchule.setBounds(701, 300, 371, 45);
			contentPane.add(cbSchule);
			
			txtJahrgang = new JTextField();
			txtJahrgang.setText("");
			txtJahrgang.setColumns(10);
			View_SuperClass.txtFieldDesign(txtJahrgang);
			txtJahrgang.setBounds(701, 390, 371, 45);
			contentPane.add(txtJahrgang);
			
			txtEmail = new JTextField();
			txtEmail.setText("");
			txtEmail.setColumns(10);
			View_SuperClass.txtFieldDesign(txtEmail);
			txtEmail.setBounds(701, 475, 371, 45);
			contentPane.add(txtEmail);
			
			JLabel lblName = new JLabel("Name");
			labelDesign(lblName);
			lblName.setBounds(701, 185, 100, 16);
			contentPane.add(lblName);
			
			JLabel lblSchule = new JLabel("Schultyp");
			labelDesign(lblSchule);
			lblSchule.setBounds(701, 278, 100, 16);
			contentPane.add(lblSchule);
			
			JLabel lblJahrgang = new JLabel("Jahrgang");
			labelDesign(lblJahrgang);
			lblJahrgang.setBounds(701, 364, 100, 16);
			contentPane.add(lblJahrgang);
			
			JLabel lblEmail = new JLabel("Email:");
			labelDesign(lblEmail);
			lblEmail.setBounds(701, 450, 100, 16);
			contentPane.add(lblEmail);
			
			JLabel lblDis = new JLabel("dis");
			lblDis.setBounds(715, 41, 300, 38);
			lblDis.setForeground(Color.WHITE);
			lblDis.setFont(new Font("Roboto", Font.BOLD, 45));
			lblDis.setForeground(Color.WHITE);
			contentPane.add(lblDis);
			
			
			JLabel lblCount = new JLabel("Count - Setup");
			lblCount.setBounds(776, 41, 300, 40);
			lblCount.setForeground(Color.WHITE);
			lblCount.setFont(new Font("Roboto", Font.BOLD, 45));
			lblCount.setForeground(MainView.disCountBlue);
			contentPane.add(lblCount);
			
			JButton btnLos = new JButton("Los geht's!");
			btnLos.setBackground(new Color(37, 37, 38));
			btnLos.setForeground(Color.WHITE);
			btnLos.setContentAreaFilled(false);
			btnLos.setOpaque(true);
			btnLos.setFont(new Font("Roboto", Font.BOLD, 20));
			btnLos.setBorder(new LineBorder(new Color(104, 33, 122), 2));
			btnLos.setBounds(701, 540, 371, 48);
			btnLos.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PrintStream fileStream;
					try {
						if(checkIFEmailOK() == -1) {
							JOptionPane.showMessageDialog(null, "Diese Email-Addresse wurde bereits verwendet!", "Nachricht", JOptionPane.PLAIN_MESSAGE);
							return;
						}

						fileStream = new PrintStream(new File("src/setup.txt"));
						fileStream.println("alreadyDone");
						fileStream = new PrintStream(new File("src/info.txt"));
						fileStream.println(txtName.getText());
						fileStream.println(cbSchule.getSelectedItem());
						fileStream.println(txtEmail.getText());
						fileStream.println(txtJahrgang.getText());
						fileStream.println("student");
						fileStream.close();
						dispose();
						startApp();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					
				}

			});
			contentPane.add(btnLos);
			
			
			myPicture = ImageIO.read(new File("src/escape.png"));
			JLabel picLabelw = new JLabel(new ImageIcon(myPicture));
			picLabelw.setBounds(1165,  8,  23, 23);
			picLabelw.addMouseListener((new MouseListener() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					dispose();
				}



				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

			}));
			contentPane.add(picLabelw);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	
	public static void labelDesign(JLabel jl) {
		jl.setForeground(Color.WHITE);
		jl.setFont(new Font("Roboto", Font.BOLD, 18));
		jl.setForeground(Color.WHITE);
	}
	
	private int checkIFEmailOK() {
		
		if(!MainView.databaseIsActive)
			return 0;
		
		 try {
			st = conn.createStatement();
			 rs = st.executeQuery("SELECT email FROM users");
			 
	         while(rs.next()) {
	      	  if(rs.getString(1).equals(txtEmail.getText()))
	      		  return -1;
	         }
	         
	         return 1;
	         
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
        
         
        
	}
	
	public void startApp() {
		
		if(!netIsAvailable()) {
			JOptionPane.showMessageDialog(null, "Sie brauchen eine Internetverbindung um das Programm zu starten-sorry!", "Nachricht", JOptionPane.PLAIN_MESSAGE);
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
		llML.addLast(new ML_db(mainController, conn, loginEmail));
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
		
	       try
	       {

	    	   String url = "jdbc:mysql://localhost/db_usersolutions?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	           Class.forName ("com.mysql.jdbc.Driver");
	           conn = DriverManager.getConnection (url,"root","");
	           System.out.println ("Database connection established");
	    	   
	    	   
	    	  /* String url = "jdbc:mysql://serverrw.ferrari.tsn.at:3306/db_usersolutions?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	           Class.forName ("com.mysql.jdbc.Driver");
	           conn = DriverManager.getConnection (url,"root","juwalRegev1");
	           System.out.println ("Database connection established");
	    	   
	         System.out.println("AFTER");*/
	   	    
	   		
	   		    
	   		    
	       }
	       catch (Exception e)
	       {
	    	   JOptionPane.showMessageDialog(null, "Sie brauchen eine Internetverbindung um das Programm zu startenconn!", "Nachricht", JOptionPane.PLAIN_MESSAGE);
	    	   System.out.println("ERROR Connection setupview-classrrr");
	    	   e.printStackTrace();
	    	   return;

	       }
	       
	}
	
	
	private static boolean netIsAvailable() {
		/*
	    try {
	        final URL url = new URL("http://www.google.com");
	        final URLConnection conn = url.openConnection();
	        conn.connect();
	        conn.getInputStream().close();
	        return true;
	    } catch (MalformedURLException e) {
	        throw new RuntimeException(e);
	    } catch (IOException e) {
	        return false;
	    }*/
		return true;
	}


	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}


	public void printAlreadyDoneText() {
		
		PrintStream fileStream;

			try {
				
				fileStream = new PrintStream(new File("src/setup.txt"));
				fileStream.println("alreadyDone");
				
				Main.alreadyDone = true;
				
			} catch(FileNotFoundException e) {e.printStackTrace();}
			
	}
	

	
}
