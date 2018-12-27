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

public class NewGroupView extends JFrame {

	private JPanel contentPane;


	
	
	public NewGroupView(String labelText, String textFieldText, String buttonText, ML_db ML) {
		
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
		
		JLabel label = new JLabel(labelText);
		label.setFont(MainView.font_20);
		label.setForeground(MainView.disCountBlue);
		label.setBounds(151, 33, 400, 55);
		contentPane.add(label);
		
		JTextField tf = new JTextField();
		tf.setText(textFieldText);
		tf.setBounds(221, 154, 140, 39);
		View_SuperClass.txtFieldDesign(tf);
		contentPane.add(tf);
		
		JButton btnJoin = new JButton(buttonText);
		btnJoin.setBackground(MainView.darkBlack);
		btnJoin.setForeground(Color.WHITE);
		btnJoin.setContentAreaFilled(false);
		btnJoin.setOpaque(true);
		btnJoin.setFont(new Font("Roboto", Font.BOLD, 20));
		btnJoin.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		btnJoin.setBounds(446, 236, 109, 39);
		btnJoin.addActionListener(new ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				ML.initCreateNewGroup(tf.getText());
				dispose();
				
			}

		});
		contentPane.add(btnJoin);
		
	}

}
