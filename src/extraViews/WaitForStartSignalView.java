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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import View.MainView;

public class WaitForStartSignalView extends JFrame {

	private JPanel contentPane;
	private JLabel lblLosGehts;
	private JButton btnLoslegen;
	private JLabel lblWartenSie;

	
	public WaitForStartSignalView() {
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
		
		lblWartenSie = new JLabel("Warten Sie noch!!");
		lblWartenSie.setFont(MainView.font_20);
		lblWartenSie.setForeground(MainView.disCountBlue);
		lblWartenSie.setBounds(218, 146, 200, 55);
		contentPane.add(lblWartenSie);
		
		lblLosGehts = new JLabel("Los geht's!");
		lblLosGehts.setForeground(new Color(0, 122, 204));
		lblLosGehts.setFont(MainView.font_20);
		lblLosGehts.setVisible(false);
		lblLosGehts.setBounds(250, 146, 200, 55);
		contentPane.add(lblLosGehts);

		
		btnLoslegen = new JButton("Viel Glück!");
		btnLoslegen.setBackground(MainView.darkBlack);
		btnLoslegen.setForeground(Color.WHITE);
		btnLoslegen.setContentAreaFilled(false);
		btnLoslegen.setOpaque(true);
		btnLoslegen.setFont(MainView.font_18);
		btnLoslegen.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		btnLoslegen.setBounds(246, 263, 109, 39);
		btnLoslegen.setVisible(false);
		btnLoslegen.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				dispose();
			}

				});
		contentPane.add(btnLoslegen);
		
		
		
		
	}


	public void examOpened() {
		lblWartenSie.setVisible(false);
		lblLosGehts.setVisible(true);
		btnLoslegen.setVisible(true);
	}

}
