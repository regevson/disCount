package extraViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import View.MainView;
import dbActivity.ML_db;

public abstract class TextBox extends JFrame{

	
	private JPanel contentPane;
	protected JButton btn;
	protected JTextField textField;
	private final int WINDOWWIDTH = 601;
	
	
	public TextBox(ML_db ML, String heading, String placeholder, String button) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, WINDOWWIDTH, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(MainView.darkBlack);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, MainView.disCountBlue));
		
		JLabel headingLabel = new JLabel(heading);
		headingLabel.setFont(MainView.font_20);
		headingLabel.setForeground(MainView.disCountBlue);
		headingLabel.setBounds(0, 33, WINDOWWIDTH, 55);
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(headingLabel);
		
		textField = new JTextField();
		textField.setText(placeholder);
		textField.setBounds(211, 154, 180, 39);
		View_SuperClass.txtFieldDesign(textField);
		contentPane.add(textField);
		
		btn = new JButton(button);
		btn.setBackground(MainView.darkBlack);
		btn.setForeground(Color.WHITE);
		btn.setContentAreaFilled(false);
		btn.setOpaque(true);
		btn.setFont(MainView.font_17_bold);
		btn.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		btn.setBounds(446, 266, 109, 39);
		contentPane.add(btn);
		
		JLabel escapeLabel = MainView.makeMenuLabels("src/escape.png", "Schlieﬂen", 555, 10, -1, 23, 23);
		escapeLabel.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	              dispose();
	            } 
	          }); 
		contentPane.add(escapeLabel);
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
