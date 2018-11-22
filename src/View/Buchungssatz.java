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
	private JRadioButton radioButton;
	
	private int stufen;
	private boolean leftMore;
	
	JTextField bsNumberField;
	
	private int bsPanelMargin;
	
	
	
	//margins//
	
	private int marginTop = 10;

	private int marginLeft_KontenLeft1;
	private int marginLeft_KontenOther;
	private int marginLeft_KontenRight1;

	private int marginLeft_PricesLeft1;
	private int marginLeft_PricesOther;
	private int marginLeft_PricesRight1;
	
	private int marginLeft_singlePrice = 300;
	
	private int workPanel_Width = 620;

	
	private String[] kontoList;
	private String[] priceList;
	private String nettoPrice;
	
	private String solutionID;
	
	String code;
	String txtContent;
	
	private MouseListener ML;
	private JPanel infoPanel;

	
	

	
	
	
	public JPanel createBSContainer(JPanel workPanel) {
		
		bsPanelMargin = MainView.bsPanelMargin;

		bsPanel = new JPanel();
		bsPanel.setBounds(5, bsPanelMargin, 585, 150);
		bsPanel.setBackground(null);
		if(MainView.isUploading) {
			bsPanel.setBackground(MainView.disCountGreen);
			bsPanel.setBorder(new LineBorder(MainView.disCountBrown, 2));
		}
		else {
			bsPanel.setBackground(MainView.middleBlack);
			bsPanel.setBorder(new LineBorder(new Color(0, 117, 211), 2));
		}
		bsPanel.setLayout(null);
		
		workPanel.add(bsPanel);
		MainView.bsList.addLast(this);
		workPanel.repaint();
		
		
		bsNumberField = new JTextField();
		bsNumberField.setFont(new Font("Roboto", Font.BOLD, 20));
		bsNumberField.setForeground(Color.BLACK);
		bsNumberField.setBackground(MainView.disCountBrown);

		bsNumberField.setBounds(0, 0, 60, 150);
		bsNumberField.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		bsNumberField.setEditable(false);
		bsPanel.add(bsNumberField);
		
		if(!MainView.isUploading)
			makePink(bsNumberField, bsPanel);
		
		
		
		paintNumberOnBSNumberField();
		makeRadioButton();
		resizeWorkPanel(workPanel);
		
		addArrows();
		
		MainView.bsPanelMargin += 170;
		
		return bsPanel;
		
	}
	
	public void makePink(JTextField jl, JPanel jp) {
		jl.setBackground(new Color(104, 33, 122));
		jp.setBorder(new LineBorder(new Color(104, 33, 122), 2));
		jl.repaint();
		
		new java.util.Timer().schedule(
				new java.util.TimerTask(){
					@Override
					public void run() {
						jl.setBackground(new Color(0, 117, 211));
						jp.setBorder(new LineBorder(new Color(0, 117, 211), 2));
						jl.repaint();}}, 1000);
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
		//tempPanel.revalidate();
		//tempPanel.repaint();
	}
	
	public void modifyJPanel() {
		bsPanel.setBounds(5, bsPanelMargin, 585, 300);
		bsNumberField.setBounds(0, 0, 60, 300);
	}
	
	public void reModifyJPanel() {
		bsPanel.setBounds(5, bsPanelMargin, 585, 150);
		bsNumberField.setBounds(0, 0, 60, 150);
	}
	
	
	
	public void addCheckMark(ArrayList<Integer> examBSList, int bsNumber) {
		ImageIcon verifyIcon = new ImageIcon("src/verifyIt.gif");
		JLabel verifyLabel = new JLabel(verifyIcon);
		verifyLabel.setBounds(540, 4, 42, 40);
		bsPanel.add(verifyLabel);
		
		db_Model.skill++;
		
		if(examBSList != null)
			examBSList.set(bsNumber, 1);
	}
	
	public void addErrorIcon() {
		ImageIcon errorIcon = new ImageIcon("src/error.gif");
		JLabel errorLabel = new JLabel(errorIcon);
		errorLabel.setBounds(538, 6, 39, 39);
		bsPanel.add(errorLabel);
		
		db_Model.skill--;
	}
	
	private void addThumbs(JPanel infoPanel, MouseListener ML) {
		ArrayList<JLabel> llLabel = new ArrayList<JLabel>();
		ImageIcon thumbsUpIcon = new ImageIcon("src/thumbsup.png");
		JLabel thumbsUpLabel = new JLabel(thumbsUpIcon);
		thumbsUpLabel.setBounds(310, 12, 18, 18);
		
		ImageIcon thumbsDownIcon = new ImageIcon("src/thumbsdown.png");
		JLabel thumbsDownLabel = new JLabel(thumbsDownIcon);
		thumbsDownLabel.setBounds(370, 12, 18, 18);
		
		llLabel.add(thumbsUpLabel);
		llLabel.add(thumbsDownLabel);
		
		MainView.llThumbsGroups.getLast().add(llLabel);
		
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
		downLabel.setBounds(555, 123, 18, 12);
		bsPanel.add(downLabel);
		downLabel.addMouseListener(this);
		
		ImageIcon upIcon = new ImageIcon("src/upArrow.png");
		JLabel upLabel = new JLabel(upIcon);
		upLabel.setBounds(555, 103, 18, 12);
		bsPanel.add(upLabel);
		upLabel.addMouseListener(this);
		
	}
	
	public void addStar() {
		ImageIcon starIcon = new ImageIcon("src/star.png");
		JLabel starLabel = new JLabel(starIcon);
		starLabel.setBounds(20, 90, 60, 60);
		bsNumberField.add(starLabel);
	}
	
	public void addVerified() {
		ImageIcon verifiedIcon = new ImageIcon("src/verified.png");
		JLabel verifiedLabel = new JLabel(verifiedIcon);
		verifiedLabel.setBounds(20, 90, 60, 60);
		bsNumberField.add(verifiedLabel);
	}
	
	public void paintNumberOnBSNumberField() {
		for(int y = 0; y < MainView.bsList.size(); y++) {
			MainView.bsList.get(y).getBSNumberField().setText(Integer.toString(y + 1));
		}
	}
	
	private JLabel addFieldToContainer(String item, int x, int y, int width, int height) {
		JLabel label = new JLabel(item);
		label.setBounds(x, y, width, height);
		label.setFont(new Font("Roboto", Font.BOLD, 17));
		label.setForeground(new Color(218, 218, 218));
		bsPanel.add(label);
		bsPanel.repaint();

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
	
	public void addInfoToPanel(String name, String codeInfo, int upvotes, int downvotes, int commentCount, String uploader, String solutionID, MouseListener ML) {
		
		JLabel infoLabel = MainView.makeMenuLabels("src/info.png", 19, 120, 9, 18, 18);
		infoLabel.addMouseListener(this);
		bsNumberField.add(infoLabel);
		
		infoPanel = new JPanel();
		infoPanel.setBounds(60, 108, 523, 40);
		infoPanel.setBackground(MainView.darkBlack);
		infoPanel.setVisible(true);
		infoPanel.setLayout(null);
		bsPanel.add(infoPanel);
		infoPanel.setVisible(false);
		
		addThumbs(infoPanel, ML);
		addCommentIcon(infoPanel, ML);
		
		createBSInfoContents(10, 100, "@" + name, infoPanel);
		createBSInfoContents(150, 50, codeInfo, infoPanel);
		createBSInfoContents(332, 50, Integer.toString(upvotes), infoPanel);	//22
		createBSInfoContents(392, 100, Integer.toString(downvotes), infoPanel);
		createBSInfoContents(452, 100, Integer.toString(commentCount), infoPanel);
		
		setSolutionID(solutionID);
		
	}
	
	private void createBSInfoContents(int x, int width, String text, JPanel infoPanel) {
		JLabel label = new JLabel();
		label.setForeground(Color.WHITE);
		label.setFont(MainView.font_15);
		label.setText(text);
		label.setBounds(x, 2, width, 40);
		infoPanel.add(label);
	}
	
	
	
	
	
	
	
	
	
	
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
		
		
		
		if(kontoList.length == 2) //einstufig
			init2KontenAnd1Price();
		
		else {						//mehrstufig
			initMultipleFields();
		}
			
	}
	
	

	private void init2KontenAnd1Price() {
		stufen = 1;
		
		addFieldToContainer(kontoList[0], marginLeft_KontenLeft1, 60, 200, 30); //kontoLeft1
		addFieldToContainer(kontoList[1], marginLeft_KontenRight1, 60, 200, 30); //kontoRight1
		
		addFieldToContainer(priceList[0], marginLeft_singlePrice, 60, 200, 30); //priceLeft1
		
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
			adaptMargin = -(6 * (stufen - 1)) + 10;
		else
			adaptMargin = -(9 * (stufen - 1)) + 10;
		
		addFieldToContainer(kontoList[0], marginLeft_KontenLeft1, marginTop + adaptMargin, 100, 100);
		addFieldToContainer(kontoList[1], marginLeft_KontenRight1, marginTop + adaptMargin, 100, 100);
		
		addFieldToContainer(priceList[0], marginLeft_PricesLeft1, marginTop + adaptMargin, 100, 100);
		addFieldToContainer(priceList[1], marginLeft_PricesRight1, marginTop + adaptMargin, 100, 100);
		
		for(int x = 2; x < kontoList.length; x++) {
			
			addFieldToContainer(kontoList[x], marginLeft_KontenOther, marginTop + adaptMargin + 20, 120, 100);
			addFieldToContainer(priceList[x], marginLeft_PricesOther, marginTop + adaptMargin + 20, 120, 100);
			
			adaptMargin += 20;
		}
		
		addIntoList();
		
	}
	
	
	
	
	////////////////move/////////////////////
	
	private void moveBS(int upOrDown) { // up is -1
		int myIndex = MainView.bsList.indexOf(this);
		
		if((myIndex == 0 && upOrDown == -1) || (myIndex == MainView.bsList.size() - 1 && upOrDown == 1))
			return;
		
		Buchungssatz otherBS = MainView.bsList.get(myIndex + upOrDown);
		MainView.bsList.set(myIndex, otherBS);
		MainView.bsList.set(myIndex + upOrDown, this);
		
		int tempMargin = bsPanelMargin;
		realignBSPanel(otherBS.getBSPanelMargin());
		otherBS.realignBSPanel(tempMargin);
	}
	
	
	public void realignBSPanel(int newMargin) {
		bsPanel.setBounds(5, newMargin, 585, 150);
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
	
	public JTextField getBSNumberField() {
		return bsNumberField;
	}
	
	public String getNettoPrice() {
		return nettoPrice;
	}
	
	public String getSolutionID() {
		return solutionID;
	}
	
	public void setRadioButtonSelected(boolean selected) {
		radioButton.setSelected(selected);
	}
	
	private void setSolutionID(String solutionID) {
		this.solutionID = solutionID;
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
			if(infoPanel.isVisible())
				infoPanel.setVisible(false);
			else
				infoPanel.setVisible(true);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	





}
