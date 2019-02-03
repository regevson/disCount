package extraViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

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

public abstract class TextBox extends JFrame{

	
	private JPanel contentPane;
	protected JButton btn;
	protected JTextField textField;
	
	
	public TextBox(ML_db ML, String heading, String placeholder, String button) {
		
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
		
		JLabel lblGebenSieDie = new JLabel(heading);
		lblGebenSieDie.setFont(MainView.font_20);
		lblGebenSieDie.setForeground(MainView.disCountBlue);
		lblGebenSieDie.setBounds(151, 33, 400, 55);
		contentPane.add(lblGebenSieDie);
		
		textField = new JTextField();
		textField.setText(placeholder);
		textField.setBounds(221, 154, 140, 39);
		View_SuperClass.txtFieldDesign(textField);
		contentPane.add(textField);
		
		btn = new JButton(button);
		btn.setBackground(MainView.darkBlack);
		btn.setForeground(Color.WHITE);
		btn.setContentAreaFilled(false);
		btn.setOpaque(true);
		btn.setFont(MainView.font_20);
		btn.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		btn.setBounds(446, 236, 109, 39);
		contentPane.add(btn);
		
	}
	
	
	protected void printWarning(String warningText) {
		
		JLabel warningLabel = new JLabel(warningText);
		warningLabel.setBounds(10,305,500,30);
		warningLabel.setForeground(Color.WHITE);
		warningLabel.setFont(MainView.font_16_bold);
		contentPane.add(warningLabel);
		contentPane.revalidate();
		contentPane.repaint();
		
	}
	
	
}
