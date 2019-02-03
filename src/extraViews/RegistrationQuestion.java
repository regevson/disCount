package extraViews;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import View.MainView;
import disCount.Main;

public class RegistrationQuestion extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnNo;
	private JButton btnYes;
	private JTextField email;
	private Setup_View setup_View;
	private JTextField pass;
	private JLabel emailIncorr;
	private JLabel passIncorr;
	
	private Color purple = new Color(128, 0, 128);
	private JButton btnAnmelden;
	private JLabel myPicture;

	public RegistrationQuestion() {
		
		setup_View = new Setup_View();
		
		setLocationRelativeTo(null);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {e.printStackTrace();}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		
		JLabel lblHabenSieSich = new JLabel("Haben Sie sich bereits registriert?");
		lblHabenSieSich.setFont(MainView.font_20);
		lblHabenSieSich.setForeground(new Color(128, 128, 128));
		lblHabenSieSich.setBounds(302, 30, 310, 52);
		contentPane.add(lblHabenSieSich);

		ImageIcon icon = new ImageIcon("src/introLogo.png");
		myPicture = new JLabel(icon);
		myPicture.setBounds(500, 160, 285, 81);
		getContentPane().add(myPicture);

		btnNo = new JButton("Nein, jetzt registrieren");
		btnNo.setBackground(Color.WHITE);
		btnNo.setForeground(purple);
		btnNo.setContentAreaFilled(false);
		btnNo.setOpaque(true);
		btnNo.setFont(MainView.font_17_bold);
		btnNo.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		btnNo.setBounds(92, 129, 238, 55);
		btnNo.addActionListener(this);
		contentPane.add(btnNo);
		
		btnYes = new JButton("Ja, ich bin bereits registriert");
		btnYes.setBackground(Color.WHITE);
		btnYes.setForeground(purple);
		btnYes.setContentAreaFilled(false);
		btnYes.setOpaque(true);
		btnYes.setFont(MainView.font_17_bold);
		btnYes.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		btnYes.setBounds(92, 205, 238, 55);
		btnYes.addActionListener(this);
		contentPane.add(btnYes);
		
		email = new JTextField();
		email.setForeground(new Color(128, 128, 128));
		email.setBackground(Color.WHITE);
		email.setFont(MainView.font_15);
		email.setBounds(550, 129, 233, 55);
		email.setBorder(new LineBorder(new Color(128, 0, 128), 1, true));
		email.setBorder(BorderFactory.createCompoundBorder(email.getBorder(), BorderFactory.createEmptyBorder(3, 6, 3, 3)));
		contentPane.add(email);
		email.setText("Deine E-Mail-Adresse");
		email.setColumns(10);
		email.setVisible(false);
		
		pass = new JTextField();
		pass.setForeground(new Color(128, 128, 128));
		pass.setBackground(Color.WHITE);
		pass.setFont(MainView.font_15);
		pass.setBounds(550, 205, 233, 55);
		pass.setBorder(new LineBorder(new Color(128, 0, 128), 1, true));
		pass.setBorder(BorderFactory.createCompoundBorder(pass.getBorder(), BorderFactory.createEmptyBorder(3, 6, 3, 3)));
		contentPane.add(pass);
		pass.setText("Dein Passwort");
		pass.setColumns(10);
		pass.setVisible(false);
		
		btnAnmelden = new JButton("Anmelden");
		btnAnmelden.setBackground(purple);
		btnAnmelden.setForeground(Color.WHITE);
		btnAnmelden.setContentAreaFilled(false);
		btnAnmelden.setOpaque(true);
		btnAnmelden.setFont(MainView.font_20);
		btnAnmelden.setBorder(new LineBorder(purple, 2, true));
		btnAnmelden.setBounds(550, 291, 233, 48);
		btnAnmelden.setVisible(false);
		btnAnmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				emailIncorr.setVisible(false);
				passIncorr.setVisible(false);
				
				if(setup_View.checkIFEmail_PasswordOK(email.getText(), "email")) {
					
					emailIncorr.setVisible(true);
					return;
					
				}
				
				else if(setup_View.checkIFEmail_PasswordOK(pass.getText(), "password")) {
					
					passIncorr.setVisible(true);
					return;
					
				}
				
				setup_View.setLoginEmail(email.getText());
				setup_View.startApp();
				dispose();
			}
		});
		contentPane.add(btnAnmelden);
		
		emailIncorr = new JLabel("Es gibt kein disCount-Konto mit dieser Adresse!");
		emailIncorr.setForeground(Color.BLACK);
		emailIncorr.setFont(MainView.font_17_bold);
		emailIncorr.setBounds(92, 343, 421, 52);
		emailIncorr.setVisible(false);
		contentPane.add(emailIncorr);
		
		passIncorr = new JLabel("Das Passwort ist falsch");
		passIncorr.setForeground(Color.BLACK);
		passIncorr.setFont(MainView.font_17_bold);
		passIncorr.setBounds(92, 343, 421, 52);
		contentPane.add(passIncorr);
		passIncorr.setVisible(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnYes) {
			
			Main.retireveLocalInfo = true;
			myPicture.setVisible(false);
			btnNo.setEnabled(false);
			btnYes.setEnabled(false);
			btnAnmelden.setVisible(true);
			email.setVisible(true);
			pass.setVisible(true);
			
		}
		
		else {
			
			dispose();
			setup_View.setVisible(true);
			
		}

	}

	public Setup_View getSetupView() {
		return setup_View;
	}

	
}


