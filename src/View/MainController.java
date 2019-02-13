package View;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Tabellenkalkulation.ML_Tabellenkalkulation;
import Tabellenkalkulation.Table_Model;
import dbActivity.Controller_dbActivity;
import dbActivity.QuestionComment;
import disCount.Main;
import extraViews.MessageBox;

public class MainController{
	
	static MainView mainView; 
	MainModel mainModel;
	Table_Model tab_Model;
	
	//private boolean leftMore;
	
	private LinkedList<Controller_AbstractClass> controllerList;

	public MainController(MainView mainView_par, MainModel mainModel, Table_Model tab_Model) {
		
		controllerList = new LinkedList<Controller_AbstractClass>();
		mainView = mainView_par;
		this.mainModel = mainModel;
		this. tab_Model = tab_Model;
		
	}
	
	public void tell_View_To_setUpBasicStuff(ArrayList<MessageBox> messageBoxList) {
		
		if(MainView.isBANNED)
			executeBannedRoutine();
		
		else if(!Main.execdisCount)
			executeErrorRoutine();
		
		else {
			mainView.setUpBasicStuff();
			mainView.setVisible(true);
			displayMessages(messageBoxList);
		}
		
	}
	
	public static void tellViewToSetCusor(Cursor cursor) {
		mainView.setYourCursor(cursor);
	}
	
	public void tellViewToDispose() {
		mainView.shutDownFrame();
	}
	
	public void tellViewToBoot() {
		mainView.bootUpFrame();
	}
	
	public void tellViewToAddExerciseSelectionPanel(boolean check) {
		mainView.addExerciseSelectionPanel(check);
	}
	
	public void tellViewToRemoveExerciseSelectionPanel() {
		mainView.removeExerciseSelectionPanel();
	}
	
	private void displayMessages(ArrayList<MessageBox> messageBoxList) {
		
		if(messageBoxList != null) {
			
			for(int x = 0; x < messageBoxList.size(); x++) {
				messageBoxList.get(x).setVisible(true);
			}
			
		}
		
	}
	
	
	private void executeBannedRoutine() {
		MessageBox mb = new MessageBox("Programmstart verweigert", "Sie wurden verbannt!", "Bei Fragen bezüglich dem Grund des Banns, kontaktieren Sie bitte:\n"
				+ "discount-software@hotmail.com", "badMessage");
		
		mb.setVisible(true);
	}
	
	private void executeErrorRoutine() {
		MessageBox mb = new MessageBox("Programmstart fehlgeschlagen", "Fehler 000x001", "Kontaktieren Sie juwal.regev@hotmail.com", "smallMessage");
		mb.setVisible(true);
	}

	public LinkedList<Controller_AbstractClass> getControllerList() {
		return controllerList;
	}

	public void addMLList(LinkedList<MouseListener> llML) {
		
		((ML_Tabellenkalkulation) llML.get(10)).getControllerandView(this, mainView);
		mainView.getMLList(llML);
		
		tell_View_To_setUpBasicStuff(mainView.getPotentialMessages());
		
	}
	
	public Double[] execCalculateRabattPricesFromNetto(String percent, String price, String rabattPercentarge) {
		return mainModel.calculateRabattPricesFromNetto(Integer.parseInt(percent), Double.parseDouble(price), rabattPercentarge);
	}
	
	public Double execNettoToBrutto(String price, String percent) {
		return mainModel.nettoToBrutto(price, percent);
	}
	
	public Double[] execNettoToBrutto(String nettoPrice, String otherPrice, String percent) {
		return mainModel.nettoToBrutto(nettoPrice, otherPrice, percent);
	}
	
	public Double execBruttoToNetto(String price, String percent) {
		return mainModel.bruttoToNetto(price, percent);
	}
	
	public Double[] execBruttoToNetto(String bruttoPrice, String otherPrice, String percent) {
		return mainModel.bruttoToNetto(bruttoPrice, otherPrice, percent);
	}
	
	public Double execCalculateBrutto(String percent, Double price) {
		return mainModel.calculateBrutto(percent, price);
	}
	
	public Double execCalculateNetto(String percent, Double price) {
		return mainModel.calculateNetto(percent, price);
	}
	
	public Double execCalcAbschreibung(String anlKonto, String IBN_Monat_str, String IBN_Year, String ND_str, Double AW, String command, boolean leftMore) {
		double afaBetrag = mainModel.calcAbschreibung(anlKonto, IBN_Monat_str, IBN_Year, ND_str, AW, command);
		
		if(command.equals("erste zwei Jahre"))
			addAfAToPanel(0, 1, anlKonto, leftMore);
		
		else if(command.equals("letzte zwei Jahre"))
			addAfAToPanel(mainModel.llAfaPrice.size()-2, mainModel.llAfaPrice.size()-1, anlKonto, leftMore);
		
		else if(command.equals("alle Jahre"))
			addAfAToPanel(0, mainModel.llAfaPrice.size()-1, anlKonto, leftMore);
		
		else if(command.equals("erstes Jahr"))
			addAfAToPanel(0, 0, anlKonto, leftMore);
		
		mainModel.llAfaPrice.removeAll(mainModel.llAfaPrice);
		mainModel.llYear.removeAll(mainModel.llYear);
		
		return afaBetrag;
		
	}
	
	private void addAfAToPanel(int from, int to, String konto, boolean leftMore) {
		for(int x = from; x <= to; x++) {
			String kontos[] = {"7010", konto};
			Double prices[] = {mainModel.llAfaPrice.get(x)};
			
			Buchungssatz bs = execpaintUpTo7(kontos, prices, leftMore);
			bs.addNoteToPanel("Datum:     " + "31." + "12" + "." + mainModel.llYear.get(x), 375);
		}
	}
	
	public Double[] execCalcNettoKreditkarten(String bruttoPrice_str, String spesen_str) {
		return mainModel.calcNettoKreditkarten(bruttoPrice_str, spesen_str);
	}
	
	public Double execNettoToSkontoBrutto(String price, String skPercent) {
		return mainModel.nettoToSkontoBrutto(price, skPercent);
	}
	
	public Double execBruttoToSkontoBrutto(String price, String skPercent) {
		return mainModel.bruttoToSkontoBrutto(price, skPercent);
	}

	public void execOpenProject() {
		File file = mainModel.openFileChooser__Open();
		mainModel.openProject_WithFile(file, mainView.getWorkPanel());
	}
	
	public ArrayList<Buchungssatz> execOpenProject(LinkedList<Character> char_LL) {
		return mainModel.openProject_WithList(char_LL, mainView.getWorkPanel());
	}
	
	public String execExecuteFWRoutine(String price, String satz) {
		return mainModel.executeFWRoutine(price, satz);
	}
	
	public Double execCalcTagesgeld(String days, String start, String end, String food) {
		return mainModel.calcTagesgeld(days, start, end, food);
	}

	public Double execCalculateErwerbsteuerbetrag(String price) {
		return mainModel.calculateErwerbsteuerbetrag(price);
	}

	public void execSaveProject() {
		mainModel.saveProject();
	}
	
	public void execPrintProject() {
		mainModel.printProject();
	}

	public Double execCalcGehaltsPercent(String ausgangsPreis_str, String percent_str) {
		return mainModel.calcGehaltsPercent(ausgangsPreis_str, percent_str);
	}

	public void execCommentsView(String heading, LinkedList<String> commentsList) {
		mainView.openCommentsView(heading, commentsList);
	}

	public LinkedList<JButton> execGetAnswerButtonList() {
		return mainView.getAnswerButtonList();
	}

	public void execProvideAnswerPanel(String type) {
		mainView.provideAnswerPanel(type);
	}

	public LinkedList<QuestionComment> execGetQuestionList() {
		return mainView.getQuestionList();
	}

	public void execCloseAnswerPanel() {
		mainView.closeAnswerPanel();
	}

	public JPanel execGetCommentPanel() {
		return mainView.getCommentPanel();
	}

	public void execRemoveNewQuestionPanel() {
		mainView.removeNewQuestionPanel();
	}

	public double[][] execGetGraphInfo() {
		Controller_dbActivity dbController = (Controller_dbActivity) controllerList.get(8);
		return dbController.execGetGraphInfo();
	}

	public void execSetupExamEnvironment() {
		mainView.setupExamEnvironment();
	}
	
	public void execRemoveExamEnvironment() {
		mainView.removeExamEnvironment();
	}

	public void execSetWindowListener() {
		mainView.setWindowListener();
	}

	public LinkedList<String> execConvertTFListToStringList(LinkedList<JTextField> tfList) {
		return mainModel.convertTFListToStringList(tfList);
	}

	public void execAddInfoToPanel(String name, String codeInfo, String upvotes, String downvotes, String commentCount, String uploader, String solutionID, Buchungssatz bs, MouseListener ML) {
		bs.addInfoToPanel(name, codeInfo, upvotes, downvotes, commentCount, uploader, solutionID, ML);
	}

	public JPanel execGetWorkPanel() {
		return mainView.getWorkPanel();
	}
	
	public void execPaintBSListToWorkPanel() {
		mainModel.paintBSListToWorkPanel(execGetWorkPanel());
	}

	public Buchungssatz execpaintUpTo7(String kontos[], Double prices[], boolean leftMore) {

		Buchungssatz bs = new Buchungssatz();
		
		for(int x = 0; x < prices.length; x++) {
			prices[x] = MainModel.roundDouble_giveBack_Double(prices[x]);
		}
		
		String[] pricesStr = mainModel.convertDoubleArrayToStringArray(prices);
		bs.initBS(kontos, pricesStr, leftMore, mainView.getWorkPanel());

		return bs;
		
	}


	public void execpaintUpTo7(String kontos[], String prices[], boolean leftMore) {
		MainModel.printErrorMessages = true;
		Buchungssatz bs = new Buchungssatz();
		bs.initBS(kontos, prices, leftMore, mainView.getWorkPanel());
	}

	public void execSetupSuchenWorkSpace() {
		mainView.makeSuchenWorkSpace();
	}

	public void execRemoveSuchenPanel() {
		mainView.removeSuchenPanel();
	}

	public void execPaintGroups(ArrayList<String> groupList) {
		mainView.paintGroups(groupList, true);
	}

	public void execRemoveGroupFromMiddlePanel(JPanel parent) {
		mainView.removeGroupFromMiddlePanel(parent);
	}

	public void execMakeAllStudentsWorkSpace(ArrayList<String> studentList) {
		mainView.makeAllStudentsWorkSpace(studentList);
	}

	public ArrayList<JPanel> execGetGroupPanelList() {
		return mainView.getGroupPanelList();
	}

	public void execRefreshLists() {
		mainModel.refreshAllBSLists();
	}

	public void execBuildSessionEnvironment() {
		mainView.buildSessionEnvironment();
	}

	public void execPrintSessionComments(String comments, int oldCommentCount, int newCommentCount, String chatCommitHistory, String myEmail) {

		mainView.printSessionComments(mainModel.decodeChar("#", comments, oldCommentCount, newCommentCount),
				mainModel.decodeChar("#", chatCommitHistory, oldCommentCount, newCommentCount), myEmail);

	}

	public void initUpdateSessionContent(String code) {
		
		Controller_dbActivity dbController = (Controller_dbActivity) controllerList.get(8);
		dbController.execUpdateSessionContent(code);
		
	}
	
	public String createCodeCommitHistory() {
		
		Controller_dbActivity dbController = (Controller_dbActivity) controllerList.get(8);
		return mainModel.createCodeCommitHistory(dbController.execGetEmail(), dbController.execGetPartnerEmail());
		
	}

	public void execPrintSessionCode(String newCode, String codeCommitHistory, String myEmail) {
		
		execOpenProject(MainModel.convertStringToLL_Char(newCode));
		ArrayList<String> codeCommitList = mainModel.decodeChar("#", codeCommitHistory, 0, MainModel.countOccurencesOfChar(codeCommitHistory, "#"));
		mainModel.changeBSColorDueToCodeCommitHistory(codeCommitList, myEmail);
		
	}

	public void initRemoveSessionFromDB() {
		
		Controller_dbActivity dbController = (Controller_dbActivity) controllerList.get(8);
		dbController.execSendSessionComment("Ich bin offline!");
		
		try {
			Thread.sleep(1000); // so partner has time to get comment from db
		} catch (InterruptedException e) {e.printStackTrace();}
		
		dbController.initRemoveSessionFromDB();
		
	}

	

	

	

	

	

	
	
}
