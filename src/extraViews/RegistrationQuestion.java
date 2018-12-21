package extraViews;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import View.MainView;
import disCount.Main;

public class RegistrationQuestion extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnNo;
	private JButton btnYes;
	private JTextField email;
	private JButton btnAnmelden;
	private Setup_View setup_View;

	public RegistrationQuestion() {
		
		setLocationRelativeTo(null);
		setUndecorated(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {e.printStackTrace();}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(MainView.darkDisCountBlue);
		
		JLabel lblHabenSieSich = new JLabel("Haben Sie sich bereits registriert?");
		lblHabenSieSich.setFont(new Font("Roboto", Font.BOLD, 20));
		lblHabenSieSich.setForeground(Color.WHITE);
		lblHabenSieSich.setBounds(299, 60, 421, 52);
		contentPane.add(lblHabenSieSich);
		
		btnNo = new JButton("Nein, jetzt registrieren");
		btnNo.setBounds(92, 236, 268, 52);
		btnNo.addActionListener(this);
		contentPane.add(btnNo);
		
		btnYes = new JButton("Ja, ich habe mich bereits registriert");
		btnYes.setBounds(562, 236, 268, 52);
		btnYes.addActionListener(this);
		contentPane.add(btnYes);
		
		email = new JTextField();
		email.setText("Deine E-Mail-Adresse");
		email.setBounds(562, 301, 268, 43);
		email.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.add(email);
		email.setVisible(false);
		email.setColumns(10);
		
		btnAnmelden = new JButton("Anmelden");
		btnAnmelden.setBounds(732, 373, 170, 52);
		btnAnmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setup_View.printAlreadyDoneText();
				setup_View.setLoginEmail(email.getText());
				setup_View.startApp();
				dispose();
			}
		});
		contentPane.add(btnAnmelden);
		btnAnmelden.setVisible(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		setup_View = new Setup_View();
		
		if(e.getSource() == btnYes) {
			
			Main.retireveLocalInfo = true;
			btnNo.setEnabled(false);
			btnYes.setEnabled(false);
			btnAnmelden.setVisible(true);
			email.setVisible(true);
			
		}
		
		else {
			
			dispose();
			setup_View.setVisible(true);
			
		}

	}

	
}
