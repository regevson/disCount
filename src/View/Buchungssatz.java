package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Controls.ML_Controls;
import dbActivity.ML_db;
import dbActivity.db_Model;

public class Buchungssatz implements MouseListener{

	private JPanel bsPanel;
	public static int bsPanelHeight = 170;
	public static int bsPanelHeight_MAX = 300;
	private int bsPanelWidth = 585;
	private JRadioButton radioButton;
	
	private int stufen;
	private boolean leftMore;
	
	private JPanel bsNumberPanel;
	private JLabel bsNumberLabel;
	
	private int bsPanelMargin;
	
	
	
	//margins//
	
	private int marginTop = 10;

	private int marginLeft_KontenLeft1;
	private int marginLeft_KontenOther;
	private int marginLeft_KontenRight1;

	private int marginLeft_PricesLeft1;
	private int marginLeft_PricesOther;
	private int marginLeft_PricesRight1;
	
	private int marginLeft_singlePrice = 350;
	
	private int workPanel_Width = 620;

	
	private String[] kontoList;
	private String[] priceList;
	private ArrayList<JLabel> llKontos;
	private ArrayList<JLabel> llPrices;
	private String nettoPrice;
	
	private ArrayList<JLabel> fieldList = new ArrayList<JLabel>();
	
	private String solutionID;
	
	String code;
	String txtContent;
	
	private MouseListener ML;
	private JPanel infoPanel;
	private JLabel thumbsUpLabel;
	private JLabel thumbsDownLabel;
	private RoundedBorder roundedBorder;
	private JPanel workPanel;
	

	
	

	
	
	
	public JPanel createBSContainer(JPanel workPanel) {
		
		bsPanelMargin = MainView.bsPanelMargin;
		this.workPanel = workPanel;

		bsPanel = new JPanel();
		bsPanel.setBounds(5, bsPanelMargin, 585, bsPanelHeight);
		roundedBorder = new RoundedBorder();
		
		if(MainView.isUploading)
			roundedBorder.paintBorder(bsPanel, MainView.disCountDarkGreen, MainView.middleBlack, 5, 24, workPanel);
		else
			roundedBorder.paintBorder(bsPanel, MainView.disCountBlue, MainView.middleBlack, 5, 24, workPanel);
		
		workPanel.add(bsPanel);
		
		if(MainView.isUploading) {
			bsPanel.setBackground(MainView.disCountGreen);
		}
		else {
			bsPanel.setBackground(MainView.middleBlack);
		}
		bsPanel.setLayout(null);
		
		workPanel.add(bsPanel);
		MainView.bsList.addLast(this);
		workPanel.repaint();
			
		
		bsNumberPanel = new JPanel();
		
		if(MainView.isUploading)
			bsNumberPanel.setBackground(MainView.disCountDarkGreen);
		else
			bsNumberPanel.setBackground(MainView.disCountBlue);
		
		bsNumberPanel.setBounds(5, 5, 60, bsPanelHeight - 15);
		bsNumberPanel.setLayout(null);
		bsPanel.add(bsNumberPanel);
		
		bsNumberLabel = new JLabel();
		bsNumberLabel.setForeground(Color.LIGHT_GRAY);
		bsNumberLabel.setBounds(20, bsPanelHeight - 115, 60, 50);
		bsNumberLabel.setFont(new Font("Roboto", Font.BOLD, 20));
		bsNumberPanel.add(bsNumberLabel);
		
		makeRadioButton();
		
		if(!MainView.isUploading && !MainView.noColorFade)
			makePink();
		
		
		paintNumberOnBSNumberPanel();
		resizeWorkPanel(workPanel);
		
		addArrows();
		
		MainView.bsPanelMargin += 170;
		
		return bsPanel;
		
	}
	
	public void paintBorder(Color borderColor) {
		roundedBorder.paintBorder(bsPanel, borderColor, MainView.middleBlack, 5, 24, workPanel);
	}

	public void makePink() {
		Color bg = bsPanel.getBackground();
		bsPanel.setBackground(MainView.disCountPurple);
		radioButton.setBackground(MainView.disCountPurple);
		bsPanel.repaint();
		
		new java.util.Timer().schedule(
				new java.util.TimerTask(){
					@Override
					public void run() {
						bsPanel.setBackground(bg);
						radioButton.setBackground(bg);
						bsPanel.repaint();}}, 1000);
	}
	
	
	private void makeRadioButton() {
		radioButton = new JRadioButton();
		radioButton.setText("");
		bsPanel.add(radioButton);
		radioButton.setBounds(550, 69, 30, 30);
		if(MainView.isUploading)
			radioButton.setBackground(MainView.disCountGreen);
		else
			radioButton.setBackground(new Color(37, 37, 38));
		
		if(MainView.isUploading)
			radioButton.setSelected(true);
	}
	
	
	private void resizeWorkPanel(JPanel workPanel) {
		int height = (int) workPanel.getSize().getHeight();
		workPanel.setPreferredSize(new Dimension(workPanel_Width - 20, height + 1000));
	}
	
	public void modifyJPanel() {
		bsPanel.setBounds(5, bsPanelMargin, bsPanelWidth, bsPanelHeight_MAX);
		bsNumberPanel.setBounds(5, 5, 60, bsPanelHeight_MAX - 15);
	}
	
	public void reModifyJPanel() {
		bsPanel.setBounds(5, bsPanelMargin, bsPanelWidth, bsPanelHeight);
		bsNumberPanel.setBounds(5, 5, 60, bsPanelHeight - 15);
	}
	
	
	
	public void addCheckMark(ArrayList<Integer> examBSList, int bsNumber) {
		ImageIcon verifyIcon = new ImageIcon("src/checkIt.png");
		JLabel verifyLabel = new JLabel(verifyIcon);
		verifyLabel.setBounds(528, 9, 42, 42);
		bsPanel.add(verifyLabel);

		db_Model.skill++;
		
		if(examBSList != null)
			examBSList.set(bsNumber, 1);
	}
	
	public void addErrorIcon() {
		ImageIcon errorIcon = new ImageIcon("src/errorIcon.png");
		JLabel errorLabel = new JLabel(errorIcon);
		errorLabel.setBounds(533, 9, 42, 42);
		bsPanel.add(errorLabel);
		
		db_Model.skill--;
	}
	
	private void addThumbs(JPanel infoPanel, MouseListener ML) {
		ImageIcon thumbsUpIcon = new ImageIcon("src/thumbsup.png");
		thumbsUpLabel = new JLabel(thumbsUpIcon);
		thumbsUpLabel.setBounds(310, 12, 18, 18);
		
		ImageIcon thumbsDownIcon = new ImageIcon("src/thumbsdown.png");
		thumbsDownLabel = new JLabel(thumbsDownIcon);
		thumbsDownLabel.setBounds(370, 12, 18, 18);
		
		thumbsUpLabel.addMouseListener(this);
		thumbsDownLabel.addMouseListener(this);
		
		infoPanel.add(thumbsUpLabel);
		infoPanel.add(thumbsDownLabel);
	}
	
	private void addCommentIcon(JPanel infoPanel, MouseListener ML) {
		ImageIcon commentIcon = new ImageIcon("src/comment.png");
		JLabel commentLabel = new JLabel(commentIcon);
		commentLabel.setBounds(430, 12, 18, 18);
		infoPanel.add(commentLabel);
		
		this.ML = ML;
		commentLabel.addMouseListener(this);
	}
	
	private void addArrows() {
		ImageIcon downIcon = new ImageIcon("src/downArrow.png");
		JLabel downLabel = new JLabel(downIcon);
		downLabel.setToolTipText("nach unten verschieben");
		downLabel.setBounds(555, 123, 18, 12);
		bsPanel.add(downLabel);
		downLabel.addMouseListener(this);
		
		ImageIcon upIcon = new ImageIcon("src/upArrow.png");
		JLabel upLabel = new JLabel(upIcon);
		upLabel.setToolTipText("nach oben verschieben");
		upLabel.setBounds(555, 103, 18, 12);
		bsPanel.add(upLabel);
		upLabel.addMouseListener(this);
		
	}
	
	public void addStar() {
		ImageIcon starIcon = new ImageIcon("src/star.png");
		JLabel starLabel = new JLabel(starIcon);
		starLabel.setToolTipText("Lehrerlösung");
		starLabel.setBounds(16, 20, 23, 25);
		bsNumberPanel.add(starLabel);
	}
	
	public void addVerified() {
		ImageIcon verifiedIcon = new ImageIcon("src/verified.png");
		JLabel verifiedLabel = new JLabel(verifiedIcon);
		verifiedLabel.setToolTipText("verifizierter Schüler");
		verifiedLabel.setBounds(13, 20, 26, 28);
		bsNumberPanel.add(verifiedLabel);
	}
	
	public void paintNumberOnBSNumberPanel() {
		for(int y = 0; y < MainView.bsList.size(); y++) {
			MainView.bsList.get(y).getBSNumberLabel().setText(Integer.toString(y + 1));
		}
	}
	
	private JLabel addFieldToContainer(String item, int x, int y, int width, int height) {
		JLabel label = new JLabel(item);
		label.setBounds(x, y, width, height);
		label.setFont(new Font("Roboto", Font.BOLD, 17));
		label.setForeground(new Color(218, 218, 218));
		bsPanel.add(label);
		fieldList.add(label);

		return label;
	}
	
	public void addNoteToPanel(String note, int x) {
		JLabel label = new JLabel(note);
		label.setBounds(x, 120, 500, 30);
		label.setFont(new Font("Roboto", Font.ITALIC, 16));
		label.setForeground(new Color(218, 218, 218));
		bsPanel.add(label);
		bsPanel.repaint();
		MainView.workPanel.repaint();
	}
	
	public void addInfoToPanel(String name, String codeInfo, String upvotes, String downvotes, String commentCount, String uploader, String solutionID, MouseListener ML) {
		
		JLabel infoLabel = MainView.makeMenuLabels("src/info.png", "Informationen", 17, 120, 9, 18, 18);
		infoLabel.addMouseListener(this);
		bsNumberPanel.add(infoLabel);
		
		infoPanel = new JPanel();
		infoPanel.setBounds(60, 118, 520, 40);
		infoPanel.setBackground(MainView.darkBlack);
		infoPanel.setLayout(null);
		bsPanel.add(infoPanel);
		infoPanel.setVisible(false);
		
		addThumbs(infoPanel, ML);
		addCommentIcon(infoPanel, ML);
		
		createBSInfoContents(10, 100, "@" + name, infoPanel);
		createBSInfoContents(150, 50, codeInfo, infoPanel);
		createBSInfoContents(332, 50, upvotes, infoPanel);	//22
		createBSInfoContents(392, 100, downvotes, infoPanel);
		createBSInfoContents(452, 100, commentCount, infoPanel);
		
		setSolutionID(solutionID);
		
		paintNumberOnBSNumberPanel();
		
		bsPanel.revalidate();
		bsPanel.repaint();
		
	}
	
	public void addInfoToPanel(String message) {
		
		JLabel infoLabel = MainView.makeMenuLabels("src/info.png", "Informationen", 17, 120, 9, 18, 18);
		infoLabel.addMouseListener(this);
		bsNumberPanel.add(infoLabel);
		
		infoPanel = new JPanel();
		infoPanel.setBounds(60, 118, 520, 40);
		infoPanel.setBackground(MainView.darkBlack);
		infoPanel.setLayout(null);
		bsPanel.add(infoPanel);
		infoPanel.setVisible(false);
		
		createBSInfoContents(10, 500, message, infoPanel);
		
		bsPanel.revalidate();
		bsPanel.repaint();
		
	}
	
	private void createBSInfoContents(int x, int width, String text, JPanel infoPanel) {
		JLabel label = new JLabel();
		label.setForeground(Color.WHITE);
		label.setFont(MainView.font_15);
		label.setText(text);
		label.setBounds(x, 2, width, 40);
		infoPanel.add(label);
	}
	
	/*public insertBS() {
		
	}*/
	
	
	
	
	
	
	
	
	
	
	public void initBS(String[] kontoList, String[] priceList, boolean leftMore, JPanel workPanel) {
		this.kontoList = kontoList;
		this.priceList = priceList;
		this.leftMore = leftMore;
		
		createBSContainer(workPanel);
		
		initFields();
	}
	
	
	
	////////////////1-stufig/////////////////////////////
	
	private void initFields() {
		
		if(leftMore) {
			marginLeft_KontenLeft1 = 100;
			marginLeft_KontenRight1 = 180;
			
			marginLeft_PricesLeft1 = 350;
			marginLeft_PricesRight1 = 450;
			
			marginLeft_KontenOther = 100;
			marginLeft_PricesOther = 350;
		}
		
		else {
			marginLeft_KontenLeft1 = 180;
			marginLeft_KontenRight1 = 100;
			
			marginLeft_PricesLeft1 = 450;
			marginLeft_PricesRight1 = 350;
			
			marginLeft_KontenOther = 180;
			marginLeft_PricesOther = 450;
		}
		
		llKontos = new ArrayList<JLabel>();
		llPrices = new ArrayList<JLabel>();
		
		if(kontoList.length == 2) //einstufig
			init2KontenAnd1Price();
		
		else {						//mehrstufig
			initMultipleFields();
		}
			
	}
	
	

	private void init2KontenAnd1Price() {
		stufen = 1;
		
		llKontos.add(addFieldToContainer(kontoList[0], marginLeft_KontenLeft1, bsPanelHeight - 100, 200, 30)); //kontoLeft1
		llKontos.add(addFieldToContainer(kontoList[1], marginLeft_KontenRight1, bsPanelHeight - 100, 200, 30)); //kontoRight1
		
		llPrices.add(addFieldToContainer(priceList[0], marginLeft_singlePrice, bsPanelHeight - 100, 200, 30)); //priceLeft1
		
		writeTxtFileEinstufig();
		encode();
		
	}

	
	private void writeTxtFileEinstufig() {
		String firstRowMargin = "                ";
		
		if(leftMore)
			txtContent = "\r\n\r\n\r\n\r\n" + kontoList[0] + "    " + kontoList[1] + firstRowMargin + priceList[0];
		else
			txtContent = "\r\n\r\n\r\n\r\n" + kontoList[1] + "    " + kontoList[0] + firstRowMargin + priceList[0];
		
		nettoPrice = "0";
	}
	
	
	public void initMultipleFields() {
		
		stufen = kontoList.length - 1;
		int adaptMargin;
		
		if(stufen < 5)
			adaptMargin = -(6 * (stufen - 1)) + 20;
		else
			adaptMargin = -(9 * (stufen - 1)) + 17;
		
		llKontos.add(addFieldToContainer(kontoList[0], marginLeft_KontenLeft1, marginTop + adaptMargin, 100, 100));
		llKontos.add(addFieldToContainer(kontoList[1], marginLeft_KontenRight1, marginTop + adaptMargin, 100, 100));
		
		llPrices.add(addFieldToContainer(priceList[0], marginLeft_PricesLeft1, marginTop + adaptMargin, 100, 100));
		llPrices.add(addFieldToContainer(priceList[1], marginLeft_PricesRight1, marginTop + adaptMargin, 100, 100));
		
		for(int x = 2; x < kontoList.length; x++) {
			
			llKontos.add(addFieldToContainer(kontoList[x], marginLeft_KontenOther, marginTop + adaptMargin + 20, 120, 100));
			llPrices.add(addFieldToContainer(priceList[x], marginLeft_PricesOther, marginTop + adaptMargin + 20, 120, 100));
			
			adaptMargin += 20;
		}
		
		addIntoList();
		
	}
	
	
	
	
	////////////////move/////////////////////
	
	private void moveBS(int upOrDown) { // up is -1
		makePink();
		int myIndex = MainView.bsList.indexOf(this);
		
		if((myIndex == 0 && upOrDown == -1) || (myIndex == MainView.bsList.size() - 1 && upOrDown == 1))
			return;
		
		Buchungssatz otherBS = MainView.bsList.get(myIndex + upOrDown);
		MainView.bsList.set(myIndex, otherBS);
		MainView.bsList.set(myIndex + upOrDown, this);
		
		int tempMargin = bsPanelMargin;
		realignBSPanel(otherBS.getBSPanelMargin());
		otherBS.realignBSPanel(tempMargin);
		
		paintNumberOnBSNumberPanel();
	}
	
	
	public void realignBSPanel(int newMargin) {
		bsPanel.setBounds(5, newMargin, bsPanelWidth, bsPanelHeight);
		bsPanelMargin = newMargin;
	}
	
	
	
	/////////////remove////////////////
	
	public void removeBS(JPanel workPanel) {
		int myIndex = MainView.bsList.indexOf(this);
		
		for(int x = myIndex + 1; x < MainView.bsList.size(); x++) {
			Buchungssatz otherBS = MainView.bsList.get(x);
			otherBS.realignBSPanel(otherBS.getBSPanelMargin() - 170);
		}
		
		MainView.bsPanelMargin -= 170;
	}
	
	private void removeFields() {
		for(int x = 0; x < fieldList.size(); x++) {
			fieldList.get(x).setVisible(false);
		}
	}
	
	private void displayFields() {
		for(int x = 0; x < fieldList.size(); x++) {
			fieldList.get(x).setVisible(true);
		}
	}
	
	
	
	
	
	
	
	
	
	private void addIntoList() {
		encode();
		writeTxtFile();
	}
	
	
	private void encode() {
		
		String code = "#";
		if(leftMore)
			code += "-" + (stufen + 1) + "*" + kontoList[0] + "/" + kontoList[1];
		
		else
			code += "+" + (stufen + 1) + "*" + kontoList[0] + "/" + kontoList[1];
		
		for(int x = 2; x < kontoList.length; x++) {
			code += "/" + kontoList[x];
		}
		

		code += "&";
		
		if(leftMore && priceList.length > 1) 
			code += priceList[0] + "/" + priceList[1];
		
		else if(!leftMore && priceList.length > 1)
			code += priceList[0] + "/" + priceList[1];
		
		else
			code += priceList[0];
		
		for(int x = 2; x < priceList.length; x++) {
			code += "/" + priceList[x];
		}
		
		this.code = code;
		
	}
	
	private void writeTxtFile() {
		
		String content;
		String firstRowMargin = "                ";
		String secondRowMargin = "                        ";
		
		if(leftMore) {
			
			content = "\r\n\r\n\r\n\r\n" + kontoList[0] + "    " + kontoList[1] + firstRowMargin + priceList[0] + "    " + priceList[1];
			
			for(int x = 2; x < kontoList.length; x++) {
				content += "\r\n" + kontoList[x] + secondRowMargin + priceList[x];
			}
			
			nettoPrice = priceList[0];
			
		}
		
		else {
			
			String margin = "";
			
			for(int x = 0; x < priceList[1].length(); x++) {
				margin += " ";
			}
			
			content = "\r\n\r\n\r\n\r\n" + kontoList[1] + "    " + kontoList[0] + firstRowMargin + priceList[1] + "    " + priceList[0];
			
			for(int x = 2; x < kontoList.length; x++) {
				content += "\r\n        " + kontoList[x] + firstRowMargin + margin + "    " + priceList[x];
			}
			
			nettoPrice = "-" + priceList[0];
			
		}
		
		txtContent = content;
		
		
		
	}
	
	
	
	
	
	public JPanel getBSPanel() {
		return bsPanel;
	}
	
	public int getBSPanelMargin() {
		return bsPanelMargin;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getTxtContent() {
		return txtContent;
	}
	
	public boolean getRadioButtonStatus() {
		return radioButton.isSelected();
	}
	
	public JPanel getBSNumberPanel() {
		return bsNumberPanel;
	}
	
	public JLabel getBSNumberLabel() {
		return bsNumberLabel;
	}
	
	public String getNettoPrice() {
		return nettoPrice;
	}
	
	public String getSolutionID() {
		return solutionID;
	}
	
	public ArrayList<JLabel> getKontoList() {
		return llKontos;
	}
	
	public ArrayList<JLabel> getPriceList() {
		return llPrices;
	}
	
	public boolean isLeftMore() {
		return leftMore;
	}
	
	public void setRadioButtonSelected(boolean selected) {
		radioButton.setSelected(selected);
	}
	
	private void setSolutionID(String solutionID) {
		this.solutionID = solutionID;
	}
	
	public void setBSPanelMargin(int margin) {
		bsPanelMargin = margin;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		MainController.tellViewToSetCusor(MainView.handCursor());
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		MainController.tellViewToSetCusor(MainView.normalCursor());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		JLabel triggerLabel = (JLabel) e.getSource();
		
		if(triggerLabel.getIcon().toString().equals("src/upArrow.png"))
			moveBS(-1);
		
		else if(triggerLabel.getIcon().toString().equals("src/downArrow.png"))
			moveBS(1);
		
		else if(triggerLabel.getIcon().toString().equals("src/comment.png"))
			((ML_db) ML).initPrintComments(solutionID);
		
		else if(triggerLabel.getIcon().toString().equals("src/info.png")) {
			if(infoPanel.isVisible()) {
				infoPanel.setVisible(false);
				displayFields();
			}
			else {
				infoPanel.setVisible(true);
				removeFields();
			}
		}
		
		else if(triggerLabel.getIcon().toString().equals("src/thumbsup.png")) {
			if(((ML_db) ML).initChangeRatio(1, solutionID))
				thumbsUpLabel.setEnabled(false);
		}
		
		else if(triggerLabel.getIcon().toString().equals("src/thumbsdown.png")) {
			if(((ML_db) ML).initChangeRatio(-1, solutionID))
				thumbsDownLabel.setEnabled(false);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	





}
