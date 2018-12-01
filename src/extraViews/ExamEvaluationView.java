package extraViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import View.MainModel;
import View.MainView;

public class ExamEvaluationView extends JFrame {

	private JPanel contentPane;
	private final int bsYStandard = 126;
	private int bsY = 126;
	private int bsX = 12;
	private final int marginLeftColumns = 278;
	private final int marginLeftBSPercentage = 128;
	private final int maxY = 607;
	private final int marginY = 40;
	private JButton btnClose;
	
	

	public ExamEvaluationView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 919, 734);
		contentPane = new JPanel();
		contentPane.setBackground(MainView.darkBlack);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, MainView.disCountBlue));
		
		JLabel lblExamnumber = new JLabel("Auswertung");
		lblExamnumber.setFont(MainView.font_20);
		lblExamnumber.setForeground(MainView.disCountBlue);
		lblExamnumber.setBounds(390, 23, 137, 42);
		contentPane.add(lblExamnumber);
		
		JLabel lblBeigetreteneSchler = new JLabel("Alle Buchungss‰tze:");
		lblBeigetreteneSchler.setBounds(12, 74, 177, 26);
		lblBeigetreteneSchler.setFont(MainView.font_17_bold);
		lblBeigetreteneSchler.setForeground(MainView.disCountBlue);
		contentPane.add(lblBeigetreteneSchler);
		
		
		btnClose = new JButton("Schlieﬂen");
		btnClose.setBackground(MainView.darkBlack);
		btnClose.setForeground(Color.WHITE);
		btnClose.setContentAreaFilled(false);
		btnClose.setOpaque(true);
		btnClose.setFont(MainView.font_17_bold);
		btnClose.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		btnClose.setBounds(718, 634, 148, 38);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				dispose();
			}

				});
		contentPane.add(btnClose);
		
	}

	public void displayEvaluation(ArrayList<String> percentList) {
		for(int x = 0; x < percentList.size(); x++) {
			JLabel bs = new JLabel();
			JLabel bsPercentage = new JLabel();
			
			bs.setBounds(bsX, bsY, 148, 26);
			bsPercentage.setBounds(bsX + marginLeftBSPercentage, bsY, 63, 26);
			
			createStudentLabels(bs, bsPercentage, "Buchungssatz " + (x + 1), percentList.get(x));
			
			bsY += marginY;
			
			if(bsY > maxY) {
				bsY = bsYStandard;
				bsX += marginLeftColumns;
			}
			
			contentPane.repaint();
				
		}
		
	}
	
	
	private void createStudentLabels(JLabel student, JLabel studentStatus, String name, String percent) {
		student.setText(name);
		student.setFont(MainView.font_16);
		student.setForeground(Color.WHITE);
		contentPane.add(student);
		
		studentStatus.setText(percent + "%");
		if(MainModel.parseDouble(percent) <= 60)
			studentStatus.setForeground(Color.RED);
		else
			studentStatus.setForeground(Color.GREEN);
		studentStatus.setFont(new Font("Roboto", Font.PLAIN, 16));
		contentPane.add(studentStatus);
	}


	


}
