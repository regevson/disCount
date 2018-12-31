package extraViews;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import View.MainView;

public class UpdateView extends JFrame {

	private JPanel contentPane;

	public UpdateView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1506, 1054);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		ImageIcon icon = new ImageIcon("src/disCount_onePager_small.jpg");
		JLabel picLabel = new JLabel(icon);
		picLabel.setBounds(0, 0, 1466, 1044);
		picLabel.setFont(MainView.font_17);
		contentPane.add(picLabel);
		
		
		
		
	}

}
