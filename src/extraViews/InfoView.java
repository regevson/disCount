package extraViews;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import View.MainView;

public class InfoView extends JFrame {

	private JPanel contentPane;
	
	private Color purple = new Color(128, 0, 128);

	public InfoView() {
		
		setTitle("Entwicklung");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 741, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDiscountEin = new JLabel("disCount - Ein Rechnungswesen-Programm f\u00FCr Sch\u00FClerInnen");
		lblDiscountEin.setForeground(purple);
		lblDiscountEin.setFont(new Font("Roboto", Font.BOLD, 22));
		lblDiscountEin.setBounds(56, 36, 655, 68);
		contentPane.add(lblDiscountEin);
		
		JLabel lblEntwickler = new JLabel("Entwickler:");
		lblEntwickler.setFont(MainView.font_18);
		lblEntwickler.setForeground(Color.DARK_GRAY);
		lblEntwickler.setBounds(46, 192, 180, 30);
		contentPane.add(lblEntwickler);
		
		JLabel lblEmailaddresse = new JLabel("E-Mail-Addresse:");
		lblEmailaddresse.setFont(MainView.font_18);
		lblEmailaddresse.setForeground(Color.DARK_GRAY);
		lblEmailaddresse.setBounds(46, 239, 187, 35);
		contentPane.add(lblEmailaddresse);
		
		JLabel lblTel = new JLabel("Tel:");
		lblTel.setFont(MainView.font_18);
		lblTel.setForeground(Color.DARK_GRAY);
		lblTel.setBounds(48, 287, 149, 35);
		contentPane.add(lblTel);
		
		JTextField lblJuwalRegev = new JTextField("Juwal Regev");
		lblJuwalRegev.setEditable(false);
		lblJuwalRegev.setBorder(new MatteBorder(0,0,2,0, purple));
		lblJuwalRegev.setFont(MainView.font_18);
		lblJuwalRegev.setForeground(Color.DARK_GRAY);
		lblJuwalRegev.setBounds(244, 192, 299, 35);
		contentPane.add(lblJuwalRegev);
		
		JTextField lblJuwalregevhotmailcom = new JTextField("juwal.regev@hotmail.com");
		lblJuwalregevhotmailcom.setEditable(false);
		lblJuwalregevhotmailcom.setBorder(new MatteBorder(0,0,2,0, purple));
		lblJuwalregevhotmailcom.setFont(MainView.font_18);
		lblJuwalregevhotmailcom.setForeground(Color.DARK_GRAY);
		lblJuwalregevhotmailcom.setBounds(245, 239, 298, 35);
		contentPane.add(lblJuwalregevhotmailcom);
		
		JTextField telTF = new JTextField("069910983630");
		telTF.setEditable(false);
		telTF.setBorder(new MatteBorder(0,0,2,0, purple));
		telTF.setFont(MainView.font_18);
		telTF.setForeground(Color.DARK_GRAY);
		telTF.setBounds(244, 287, 299, 35);
		contentPane.add(telTF);
		
	}
}
