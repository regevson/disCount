package extraViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import View.MainModel;
import View.MainView;
import dbActivity.ML_db;

public class ExamLobby extends JFrame {

	private JPanel contentPane;
	private final int studentYStandard = 129;
	private int studentY = 129;
	private int studentX = 12;
	private final int marginLeftColumns = 310;
	private final int maxY = 653;
	private final int marginY = 53;
	private JButton btnStartExam;
	
	private ArrayList<String> alStudentNames;
	private HashMap<String, JLabel> hmStringToJLabel; //studentName to studentStatusLabel
	private HashMap<String, JPanel> hmStringToJPanel; //studentName to studentStatusLabel
	private JPanel notificationPanel;
	private int counter = 1;
	private JLabel notificationText;
	
	

	//-----------------MAX 40 Students!!!!!!!!!!!-----------------------------
	
	public ExamLobby(ML_db ML, int pid) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 1231, 734);
		setTitle("Exam Lobby");
		contentPane = new JPanel();
		contentPane.setBackground(MainView.middleBlack);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, MainView.disCountBlue));
		
		hmStringToJLabel = new HashMap<String, JLabel>();
		hmStringToJPanel = new HashMap<String, JPanel>();
		alStudentNames = new ArrayList<String>();
		
		JLabel lblBeigetreteneSchler = new JLabel("Beigetretene Sch\u00FCler:");
		lblBeigetreteneSchler.setBounds(12, 74, 177, 26);
		lblBeigetreteneSchler.setFont(MainView.font_17_bold);
		lblBeigetreteneSchler.setForeground(MainView.disCountBlue);
		contentPane.add(lblBeigetreteneSchler);
		
		
		btnStartExam = new JButton("Prüfung starten");
		btnStartExam.setBackground(MainView.darkBlack);
		btnStartExam.setForeground(Color.WHITE);
		btnStartExam.setContentAreaFilled(false);
		btnStartExam.setOpaque(true);
		btnStartExam.setVisible(false);
		btnStartExam.setFont(MainView.font_17_bold);
		btnStartExam.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		btnStartExam.setBounds(1056, 666, 148, 38);
		btnStartExam.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(!btnStartExam.getText().equals("zur Auswertung")) {
					ML.initProvideStartSignal();
					btnStartExam.setVisible(false);
					
					setStudentStatusToRunning();
					ML.execScanForFinishedStudents();
				}
				
				else {
					dispose();
					ML.openAuswertungView();
				}
			}

				});
		contentPane.add(btnStartExam);
		
		JPanel topBanner = new JPanel();
		topBanner.setBorder(new MatteBorder(0, 0, 3, 0, (Color) MainView.disCountBlue));
		topBanner.setBounds(0, 0, 1231, 48);
		topBanner.setBackground(MainView.darkBlack);
		contentPane.add(topBanner);
		topBanner.setLayout(null);
		
		JLabel lblExamnumber = new JLabel("Pr\u00FCfung " + pid);
		lblExamnumber.setBounds(547, 5, 137, 42);
		topBanner.add(lblExamnumber);
		lblExamnumber.setFont(MainView.font_20);
		lblExamnumber.setForeground(MainView.disCountBlue);
		
		
		
	
		/*
		String st = "student";
		int c = 1;
		ArrayList<String> temp = new ArrayList<String>();
		for(int x = 0; x < 40; x++) {
			temp.add(st + c);
			c++;
		}
		displayJoinedStudents(temp);
		*/
		
		 
		 
		
		
	}
	
	

	public void displayJoinedStudents(ArrayList<String> alStudentNames) {
		
		for(int x = 0; x < alStudentNames.size(); x++) {
			
			JPanel student = new JPanel();
			JLabel studentStatus = new JLabel();
			
			this.alStudentNames.add(alStudentNames.get(x));
			
			student.setBounds(studentX, studentY, 271, 31);
			
			hmStringToJLabel.put(alStudentNames.get(x), createStudentLabels(student, studentStatus, alStudentNames.get(x), x));
			hmStringToJPanel.put(alStudentNames.get(x), student);

			studentY += marginY;
			
			if(studentY > maxY) {
				
				studentY = studentYStandard;
				studentX += marginLeftColumns;
				
			}
			
			contentPane.repaint();
				
		}
		
	}
	
	
	private JLabel createStudentLabels(JPanel student, JLabel studentStatus, String name, int x) {
		
		student.setBorder(new LineBorder(new Color(0, 122, 204), 2));
		student.setBackground(MainView.darkBlack);
		contentPane.add(student);
		student.setLayout(null);
		
		JLabel studentName = new JLabel(name);
		studentName.setBounds(51, 2, 151, 26);
		student.add(studentName);
		studentName.setFont(MainView.font_16);
		studentName.setForeground(Color.WHITE);
		
		studentStatus = new JLabel();
		studentStatus.setText("wartet");
		studentStatus.setBounds(204, 2, 67, 26);
		student.add(studentStatus);
		studentStatus.setForeground(Color.ORANGE);
		studentStatus.setFont(MainView.font_16);
		
		
		
		JPanel counterPanel = new JPanel();
		counterPanel.setBounds(0, 0, 38, 31);
		student.add(counterPanel);
		counterPanel.setBorder(null);
		counterPanel.setLayout(null);
		counterPanel.setBackground(MainView.disCountBlue);
		
		JLabel counterLabel = new JLabel();
		counterLabel.setText(Integer.toString(this.counter++));
		counterLabel.setBounds(8, 2, 26, 29);
		counterPanel.add(counterLabel);
		counterLabel.setForeground(Color.WHITE);
		counterLabel.setFont(MainView.font_18);
		
		return studentStatus;
		
	}
	

	public void notifyAllStudentsJoined() {
		
		notificationPanel = new JPanel();
		notificationPanel.setBounds(411, 655, 408, 48);
		notificationPanel.setBackground(MainView.disCountBlue);
		contentPane.add(notificationPanel);
		notificationPanel.setLayout(null);
		
		notificationText = new JLabel("Alle Sch\u00FCler sind beigetreten!");
		notificationText.setFont(MainView.font_18);
		notificationText.setBounds(81, 9, 242, 35);
		notificationText.setForeground(Color.WHITE);
		notificationPanel.add(notificationText);
		
		btnStartExam.setVisible(true);
		
	}
	
	
	public void notifyAllStudentsFinished() {
		
		notificationPanel.setVisible(true);
		notificationText.setText("Alle Schüler sind fertig!");
		
		btnStartExam.setText("zur Auswertung");
		btnStartExam.setVisible(true);
		
	}
	
	
	
	private void setStudentStatusToRunning() {
		
		notificationPanel.setVisible(false);
		
		for(int x = 0; x < this.alStudentNames.size(); x++) {
			hmStringToJLabel.get(this.alStudentNames.get(x)).setText("arbeitet");
		}
		
	}
	

	public void updateFinishedStudents(ArrayList<String> finishedStudentsNames, ArrayList<Double> alStudentResults) {
		
		for(int x = 0; x < finishedStudentsNames.size(); x++) {
			
			if(alStudentResults.get(x) == -31) {
				
				hmStringToJLabel.get(finishedStudentsNames.get(x)).setText("ungültig");
				hmStringToJLabel.get(finishedStudentsNames.get(x)).setForeground(Color.RED);
				
				paintEvictDecisionIcons(hmStringToJPanel.get(finishedStudentsNames.get(x)));
				
			}
			
			else
				hmStringToJLabel.get(finishedStudentsNames.get(x)).setText(MainModel.roundDouble_giveBack_String(alStudentResults.get(x)) + "%");
		}

		contentPane.repaint();
		
	}



	private void paintEvictDecisionIcons(JPanel cheaterPanel) {
		
		Rectangle cheaterRec = cheaterPanel.getBounds();
		cheaterPanel.setBounds((int) cheaterRec.getX(), (int) cheaterRec.getY(), (int) cheaterRec.getWidth() + 60, (int) cheaterRec.getHeight());

		JLabel evictLabel = MainView.makeMenuLabels("src/sadFace.png", "Schüler entfernen", (int) cheaterRec.getWidth() + 5, 3, 9, 23, 23);
		cheaterPanel.add(evictLabel);
		
		JLabel continueLabel = MainView.makeMenuLabels("src/happyFace.png", "Schüler darf weitermachen", (int) cheaterRec.getWidth() + 25, 3, 9, 23, 23);
		cheaterPanel.add(continueLabel);
		
	}
	
	public JPanel changeStudentStatus(String status, String studentName) {
		
		JLabel cheaterLabel = hmStringToJLabel.get(studentName);
		cheaterLabel.setText(status);
		cheaterLabel.setForeground(Color.ORANGE);
		
		return hmStringToJPanel.get(studentName);
		
	}
	
	public void removeSmileys(JPanel cheaterPanel) {
		
		cheaterPanel.remove(cheaterPanel.getComponent(cheaterPanel.getComponentCount() - 1));
		cheaterPanel.remove(cheaterPanel.getComponent(cheaterPanel.getComponentCount() - 1));
		
		Rectangle cheaterRec = cheaterPanel.getBounds();
		cheaterPanel.setBounds((int) cheaterRec.getX(), (int) cheaterRec.getY(), (int) cheaterRec.getWidth() - 60, (int) cheaterRec.getHeight());
		
	}
	
	
	
}
