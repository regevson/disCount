package extraViews;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import View.MainView;

public class InfoView extends JFrame {

	private JPanel contentPane;

	public InfoView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1107, 686);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(MainView.darkBlack);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDiscountEin = new JLabel("disCount - Ein Rechnungswesen-Programm f\u00FCr Sch\u00FClerInnen");
		lblDiscountEin.setForeground(MainView.disCountBlue);
		lblDiscountEin.setFont(new Font("Roboto", Font.BOLD, 22));
		lblDiscountEin.setBounds(248, 36, 800, 68);
		contentPane.add(lblDiscountEin);
		
		JLabel lblEntwickler = new JLabel("Entwickler:");
		lblEntwickler.setFont(new Font("Roboto", Font.BOLD, 18));
		lblEntwickler.setForeground(Color.WHITE);
		lblEntwickler.setBounds(46, 192, 119, 30);
		contentPane.add(lblEntwickler);
		
		JLabel lblEmailaddresse = new JLabel("E-Mail-Addresse:");
		lblEmailaddresse.setFont(new Font("Roboto", Font.BOLD, 18));
		lblEmailaddresse.setForeground(Color.WHITE);
		lblEmailaddresse.setBounds(46, 239, 157, 35);
		contentPane.add(lblEmailaddresse);
		
		JLabel lblTel = new JLabel("Tel:");
		lblTel.setFont(new Font("Roboto", Font.BOLD, 18));
		lblTel.setForeground(Color.WHITE);
		lblTel.setBounds(48, 287, 104, 35);
		contentPane.add(lblTel);
		
		JLabel lblJuwalRegev = new JLabel("Juwal Regev");
		lblJuwalRegev.setFont(new Font("Roboto", Font.BOLD, 18));
		lblJuwalRegev.setForeground(Color.WHITE);
		lblJuwalRegev.setBounds(244, 192, 800, 35);
		contentPane.add(lblJuwalRegev);
		
		JLabel lblJuwalregevhotmailcom = new JLabel("juwal.regev@hotmail.com");
		lblJuwalregevhotmailcom.setFont(new Font("Roboto", Font.BOLD, 18));
		lblJuwalregevhotmailcom.setForeground(Color.WHITE);
		lblJuwalregevhotmailcom.setBounds(245, 239, 800, 35);
		contentPane.add(lblJuwalregevhotmailcom);
		
		JLabel label = new JLabel("066910983630");
		label.setFont(new Font("Roboto", Font.BOLD, 18));
		label.setForeground(Color.WHITE);
		label.setBounds(244, 287, 800, 35);
		contentPane.add(label);
	}
}
