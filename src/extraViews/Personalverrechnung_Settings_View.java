package extraViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import View.MainView;

public class Personalverrechnung_Settings_View extends JFrame {

	private JPanel contentPane;
	private JTextField txtmind20KPP;
	private JTextField txtmehrals40KPP;
	private JTextField textField_mehrals60KPP;
	private JTextField txtmind20GPP;
	private JTextField txtmehrals40GPP;
	private JTextField textField_mehrals60GPP;
	 
	private JTextField txtCurrentSV1;
	private JTextField txtCurrentSV2;
	private JTextField txtCurrentSV3;
	private JTextField txtCurrentSV4;
	private JTextField txtGeringfügigkeitsgrenze;
	private JTextField txtHöchstbeitragsgrundlage;
	
	
	public Personalverrechnung_Settings_View() {
			JFrame jf = new JFrame();
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jf.setBounds(400, 400, 661, 939);
			JPanel contentPane = new JPanel();
			contentPane.setBackground(new Color(51, 51, 51));
			jf.setContentPane(contentPane);
			contentPane.setLayout(null);
			jf.setVisible(true);
			
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			jf.setLocation(dim.width/2-jf.getSize().width/2, dim.height/2-jf.getSize().height/2);
			
			JLabel lblCurrentSV1 = new JLabel("SV bis bis € 1.311,00");
			lblCurrentSV1.setFont(MainView.font_16);
			lblCurrentSV1.setForeground(Color.WHITE);
			lblCurrentSV1.setBounds(57, 89, 240, 42);
			contentPane.add(lblCurrentSV1);
			
			
			txtCurrentSV1 = new JTextField();
			View_SuperClass.txtFieldDesign(txtCurrentSV1);
			txtCurrentSV1.setText("15.12");
			txtCurrentSV1.setBounds(468, 91, 100, 40);
			contentPane.add(txtCurrentSV1);
			
			
			JLabel lblCurrentSV2 = new JLabel("über € 1.311,00 - bis € 1.430,00");
			lblCurrentSV2.setFont(MainView.font_16);
			lblCurrentSV2.setForeground(Color.WHITE);
			lblCurrentSV2.setBounds(57, 149, 240, 42);
			contentPane.add(lblCurrentSV2);
			
			
			txtCurrentSV2 = new JTextField();
			View_SuperClass.txtFieldDesign(txtCurrentSV2);
			txtCurrentSV2.setText("16.12");
			txtCurrentSV2.setBounds(468, 151, 100, 40);
			contentPane.add(txtCurrentSV2);
			
			
			
			JLabel lblCurrentSV3 = new JLabel("über € 1.430,00 - bis € 1.609,00");
			lblCurrentSV3.setFont(MainView.font_16);
			lblCurrentSV3.setForeground(Color.WHITE);
			lblCurrentSV3.setBounds(57, 209, 240, 42);
			contentPane.add(lblCurrentSV3);
			
			txtCurrentSV3 = new JTextField();
			View_SuperClass.txtFieldDesign(txtCurrentSV3);
			txtCurrentSV3.setText("17.12");
			txtCurrentSV3.setBounds(468, 211, 100, 40);
			contentPane.add(txtCurrentSV3);
			
			
			
			JLabel lblCurrentSV4 = new JLabel("über € 1.609,00");
			lblCurrentSV4.setFont(MainView.font_16);
			lblCurrentSV4.setForeground(Color.WHITE);
			lblCurrentSV4.setBounds(57, 269, 240, 42);
			contentPane.add(lblCurrentSV4);
			
			txtCurrentSV4 = new JTextField();
			View_SuperClass.txtFieldDesign(txtCurrentSV4);
			txtCurrentSV4.setText("18.12");
			txtCurrentSV4.setBounds(468, 271, 100, 40);
			contentPane.add(txtCurrentSV4);
			
			JLabel lblSozialversicherung = new JLabel("Sozialversicherung");
			lblSozialversicherung.setFont(MainView.font_20);
			lblSozialversicherung.setForeground(Color.WHITE);
			lblSozialversicherung.setBounds(250, 22, 300, 40);
			contentPane.add(lblSozialversicherung);
			
			JPanel panel = new JPanel();
			panel.setBounds(12, 324, 619, 3);
			contentPane.add(panel);
			
			JLabel lblMindkm = new JLabel("mind. 20km");
			lblMindkm.setForeground(Color.WHITE);
			lblMindkm.setFont(MainView.font_16);
			lblMindkm.setBounds(57, 412, 240, 42);
			contentPane.add(lblMindkm);
			
			txtmind20KPP = new JTextField();
			View_SuperClass.txtFieldDesign(txtmind20KPP);
			txtmind20KPP.setText("58");
			txtmind20KPP.setBounds(317, 412, 100, 40);
			contentPane.add(txtmind20KPP);
			
			JLabel lblMehrals40 = new JLabel("mehr als 40km");
			lblMehrals40.setForeground(Color.WHITE);
			lblMehrals40.setFont(MainView.font_16);
			lblMehrals40.setBounds(57, 487, 240, 42);
			contentPane.add(lblMehrals40);
			
			txtmehrals40KPP = new JTextField();
			View_SuperClass.txtFieldDesign(txtmehrals40KPP);
			txtmehrals40KPP.setText("113");
			txtmehrals40KPP.setBounds(317, 487, 100, 40);
			contentPane.add(txtmehrals40KPP);
			
			JLabel lblMehrAlskm = new JLabel("mehr als 60km");
			lblMehrAlskm.setForeground(Color.WHITE);
			lblMehrAlskm.setFont(MainView.font_16);
			lblMehrAlskm.setBounds(57, 568, 240, 42);
			contentPane.add(lblMehrAlskm);
			
			textField_mehrals60KPP = new JTextField();
			View_SuperClass.txtFieldDesign(textField_mehrals60KPP);
			textField_mehrals60KPP.setText("168");
			textField_mehrals60KPP.setBounds(317, 568, 100, 40);
			contentPane.add(textField_mehrals60KPP);
			
			JLabel lblKleinesPp = new JLabel("kleines PP");
			lblKleinesPp.setFont(MainView.font_16_bold);
			lblKleinesPp.setForeground(Color.WHITE);
			lblKleinesPp.setBounds(330, 354, 100, 30);
			contentPane.add(lblKleinesPp);
			
			txtmind20GPP = new JTextField();
			View_SuperClass.txtFieldDesign(txtmind20GPP);
			txtmind20GPP.setText("123");
			txtmind20GPP.setBounds(496, 412, 100, 40);
			contentPane.add(txtmind20GPP);
			
			txtmehrals40GPP = new JTextField();
			View_SuperClass.txtFieldDesign(txtmehrals40GPP);
			txtmehrals40GPP.setText("214");
			txtmehrals40GPP.setBounds(496, 487, 100, 40);
			contentPane.add(txtmehrals40GPP);
			
			textField_mehrals60GPP = new JTextField();
			View_SuperClass.txtFieldDesign(textField_mehrals60GPP);
			textField_mehrals60GPP.setText("306");
			textField_mehrals60GPP.setBounds(496, 568, 100, 40);
			contentPane.add(textField_mehrals60GPP);
			
			JLabel grossesPP = new JLabel("großes PP");
			grossesPP.setFont(MainView.font_16_bold);
			grossesPP.setForeground(Color.WHITE);
			grossesPP.setBounds(510, 354, 100, 30);
			contentPane.add(grossesPP);
			
			JButton btnNewButton = new JButton("\u00C4nderungen speichern");
			btnNewButton.setBackground(new Color(37, 37, 38));
			btnNewButton.setForeground(Color.WHITE);
			btnNewButton.setContentAreaFilled(false);
			btnNewButton.setOpaque(true);
			btnNewButton.setFont(MainView.font_16);
			btnNewButton.setBorder(new LineBorder(new Color(0, 117, 211), 2));
			btnNewButton.setBounds(431, 837, 183, 42);
			contentPane.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						jf.dispose();
						saveToFile();
					} catch (IOException e1) {System.out.println("FAILED");}
				}

					});
			contentPane.add(btnNewButton);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(12, 647, 619, 3);
			contentPane.add(panel_1);
			
			JLabel lblGeringfgigkeitsgrenze = new JLabel("Geringf\u00FCgigkeitsgrenze");
			lblGeringfgigkeitsgrenze.setForeground(Color.WHITE);
			lblGeringfgigkeitsgrenze.setFont(MainView.font_16);
			lblGeringfgigkeitsgrenze.setBounds(57, 683, 240, 42);
			contentPane.add(lblGeringfgigkeitsgrenze);
			
			txtGeringfügigkeitsgrenze = new JTextField();
			View_SuperClass.txtFieldDesign(txtGeringfügigkeitsgrenze);
			txtGeringfügigkeitsgrenze.setText("415.72");
			txtGeringfügigkeitsgrenze.setBounds(317, 685, 100, 40);
			contentPane.add(txtGeringfügigkeitsgrenze);
			
			JLabel lblHchstbeitragsgrundlage = new JLabel("H\u00F6chstbeitragsgrundlage");
			lblHchstbeitragsgrundlage.setForeground(Color.WHITE);
			lblHchstbeitragsgrundlage.setFont(MainView.font_16);
			lblHchstbeitragsgrundlage.setBounds(57, 749, 240, 42);
			contentPane.add(lblHchstbeitragsgrundlage);
			
			txtHöchstbeitragsgrundlage = new JTextField();
			View_SuperClass.txtFieldDesign(txtHöchstbeitragsgrundlage);
			txtHöchstbeitragsgrundlage.setText("4860");
			txtHöchstbeitragsgrundlage.setBounds(317, 751, 100, 40);
			contentPane.add(txtHöchstbeitragsgrundlage);
	}
	
	private void saveToFile() throws IOException {
		Path settingsPath = FileSystems.getDefault().getPath("src", "settings.txt");
		try {
		    Files.delete(settingsPath);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", settingsPath);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", settingsPath);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
		PrintStream fileStream = new PrintStream(new File("src/settings.txt"));
		
		fileStream.println(txtCurrentSV1.getText());
		fileStream.println(txtCurrentSV2.getText());
		fileStream.println(txtCurrentSV3.getText());
		fileStream.println(txtCurrentSV4.getText());
		fileStream.println(txtmind20KPP.getText());
		fileStream.println(txtmehrals40KPP.getText());
		fileStream.println(textField_mehrals60KPP.getText());
		fileStream.println(txtmind20GPP.getText());
		fileStream.println(txtmehrals40GPP.getText());
		fileStream.println(textField_mehrals60GPP.getText());
		fileStream.println(txtGeringfügigkeitsgrenze.getText());
		fileStream.println(txtHöchstbeitragsgrundlage.getText());
		
	 
		fileStream.close();
		
		MainView.appendHint("Erfolgreich geändert!");
	}
}
