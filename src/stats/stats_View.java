package stats;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import View.MainView;

public class stats_View {
	
	private JPanel contentPane;
	
	private final int MAX_HEIGHT = 180;
	private final int MAX_WIDTH = 30;
	
	private final int LEFT = 440;
	
	
	private Color orange = new Color(255, 149, 1);
	private Color türkis = new Color(90, 200, 251);
	private Color grün = new Color(0, 122, 255);
	
	
	
	
	
	public void setMainPanel(JPanel panelMiddle) {
		this.contentPane = panelMiddle;
	}

	public void paintLabels(LinkedList<JLabel> labels) {
		
		JLabel skill = labels.get(0);
		JLabel dependence = labels.get(1);
		JLabel community = labels.get(2);
		dependence.setBounds(20, 340, 960, 30); // contentPane height = 950
		community.setBounds(20, 650, 960, 30);
		
	}

	public void paintGraphs(double[][] stats) {
		
		double[] userStats = stats[0];
		double[] averageStats = stats[1];
		
		double skillHeights[] = calcBarHeights(userStats[0], averageStats[0]);
		double dependenceHeights[] = calcBarHeights(userStats[1], averageStats[1]);
		double communityHeights[] = calcBarHeights(userStats[2], averageStats[2]);
		
		paintGraph((int) skillHeights[0], (int) skillHeights[1], 20);
		paintGraph((int) dependenceHeights[0], (int) dependenceHeights[1], 335);
		paintGraph((int) communityHeights[0], (int) communityHeights[1], 645);
		
		
	}
	
	private double[] calcBarHeights(double userVal, double averageVal) {
		
		double temp[];
		
		double heightUserVal = (MAX_HEIGHT / averageVal) * userVal;
		temp = new double[] {heightUserVal, MAX_HEIGHT};
		
		if(userVal > averageVal) {
			temp[0] = MAX_HEIGHT;
			temp[1] = (MAX_HEIGHT / userVal) * averageVal;
		}
		
		return temp;	
		
	}

	private void paintGraph(int heightUserBar, int heightAverageBar, int margin) {
		
		JPanel userBar = new JPanel();
		userBar.setBounds(LEFT, margin + 90 + (MAX_HEIGHT - heightUserBar), MAX_WIDTH, heightUserBar);
		userBar.setBackground(orange);
		contentPane.add(userBar);
		
		JPanel averageBar = new JPanel();
		averageBar.setBounds(LEFT + 50, margin + 90 + (MAX_HEIGHT - heightAverageBar), MAX_WIDTH, heightAverageBar);
		averageBar.setBackground(türkis);
		contentPane.add(averageBar);
		
		JPanel xAchse = new JPanel();
		xAchse.setBounds(LEFT - 85, margin + 270, 250, 3);
		xAchse.setBackground(Color.WHITE);
		contentPane.add(xAchse);
		
		JPanel yAchse = new JPanel();
		yAchse.setBounds(LEFT - 85, margin + 60, 3, 210);
		yAchse.setBackground(Color.WHITE);
		contentPane.add(yAchse);
		
		JLabel lblYou = new JLabel();
		lblYou.setText("Du");
		lblYou.setFont(MainView.font_18);
		lblYou.setBounds(LEFT + 3, margin + 256, 30, 70);
		lblYou.setForeground(Color.WHITE);
		contentPane.add(lblYou);
		
		JLabel lblAverage = new JLabel();
		lblAverage.setText("<html>&Oslash;</html>");
		lblAverage.setFont(MainView.font_18);
		lblAverage.setBounds(LEFT + 59, margin + 256, 30, 70);
		lblAverage.setForeground(Color.WHITE);
		contentPane.add(lblAverage);
		
	}
	
	


}
