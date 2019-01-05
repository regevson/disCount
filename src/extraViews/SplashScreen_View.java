package extraViews;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SplashScreen_View extends JFrame {

	private JPanel contentPane;
	 private BufferedImage image;

	public SplashScreen_View() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
		setBounds(0, 0, 789, 395);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(new Color(51, 51, 51));
		setContentPane(contentPane);
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("src/splash_v2_0.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			getContentPane().add(picLabel, BorderLayout.CENTER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	


}
