package extraViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import View.MainView;
import dbActivity.ML_db;

public class InsertExamIDView extends JFrame {

	private JPanel contentPane;


	
	
	public InsertExamIDView(ML_db ML) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 601, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(MainView.darkBlack);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, MainView.disCountBlue));
		
		JLabel lblGebenSieDie = new JLabel("Geben Sie die Pr\u00FCfungs-ID ein!");
		lblGebenSieDie.setFont(MainView.font_20);
		lblGebenSieDie.setForeground(MainView.disCountBlue);
		lblGebenSieDie.setBounds(151, 33, 281, 55);
		contentPane.add(lblGebenSieDie);
		
		JTextField txtId = new JTextField();
		txtId.setText("ID");
		txtId.setBounds(221, 154, 140, 39);
		View_SuperClass.txtFieldDesign(txtId);
		contentPane.add(txtId);

		
		JButton btnJoin = new JButton("Beitreten");
		btnJoin.setBackground(MainView.darkBlack);
		btnJoin.setForeground(Color.WHITE);
		btnJoin.setContentAreaFilled(false);
		btnJoin.setOpaque(true);
		btnJoin.setFont(new Font("Roboto", Font.BOLD, 20));
		btnJoin.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		btnJoin.setBounds(446, 236, 109, 39);
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				ML.initSignInStudent(txtId.getText());
				ML.execScanForStartSignal();
				dispose();
			}

				});
		contentPane.add(btnJoin);
		
		
	}

}
