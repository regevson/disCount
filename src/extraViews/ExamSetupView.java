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
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import View.MainView;
import dbActivity.ML_db;
import dbActivity.db_Model;
import javax.swing.JCheckBox;

public class ExamSetupView extends JFrame {

	private JPanel contentPane;

	public ExamSetupView(ML_db ML) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 560, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(MainView.darkBlack);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, MainView.disCountBlue));
		
		JLabel lblPrfungStarten = new JLabel("Pr\u00FCfung starten");
		lblPrfungStarten.setForeground(MainView.disCountBlue);
		lblPrfungStarten.setBounds(194, 28, 153, 25);
		lblPrfungStarten.setFont(MainView.font_20);
		contentPane.add(lblPrfungStarten);
		
		SpinnerModel sm = new SpinnerNumberModel(1, 1, db_Model.MAXSTUDENTS, 1); //default value, lower bound, upper bound, increment by
		JSpinner spinner = new JSpinner(sm);
		spinner.setBounds(194, 91, 153, 45);
		contentPane.add(spinner);
		
		JLabel lblLsungAngehngt = new JLabel("L\u00F6sung angeh\u00E4ngt!");
		lblLsungAngehngt.setForeground(Color.GREEN);
		lblLsungAngehngt.setFont(new Font("Roboto", Font.BOLD, 16));
		lblLsungAngehngt.setBounds(194, 169, 153, 33);
		lblLsungAngehngt.setVisible(false);
		contentPane.add(lblLsungAngehngt);
		
		
		JButton btnLsungAnhngen = new JButton("L\u00F6sung anh\u00E4ngen");
		btnLsungAnhngen.setBounds(194, 164, 153, 45);
		btnLsungAnhngen.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				ML.initOpenSolution();
				btnLsungAnhngen.setVisible(false);
				lblLsungAngehngt.setVisible(true);
				
				ML.setStudentCount((int) spinner.getValue());
			}

				});
		contentPane.add(btnLsungAnhngen);

		
		JCheckBox bsVorlagen = new JCheckBox("BS-Vorlagen erlauben");
		bsVorlagen.setBackground(MainView.darkBlack);
		bsVorlagen.setForeground(Color.WHITE);
		bsVorlagen.setBounds(194, 229, 153, 25);
		contentPane.add(bsVorlagen);		
		
		JButton btnPrfungStarten = new JButton("Prüfung starten");
		btnPrfungStarten.setBackground(MainView.darkBlack);
		btnPrfungStarten.setForeground(Color.WHITE);
		btnPrfungStarten.setContentAreaFilled(false);
		btnPrfungStarten.setOpaque(true);
		btnPrfungStarten.setFont(MainView.font_17_bold);
		btnPrfungStarten.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		btnPrfungStarten.setBounds(382, 283, 147, 45);
		btnPrfungStarten.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				ML.continueWithStudentLogin(bsVorlagen.isSelected());
				dispose();
			}

				});
		contentPane.add(btnPrfungStarten);
		
		
		
		
		
	}
}
