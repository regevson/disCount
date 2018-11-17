package View;
import java.awt.Cursor;
import java.awt.event.MouseListener;
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
import extraViews.BS_Editor;
import extraViews.MessageBox;

public class MainController{
	
	static MainView mainView; 
	MainModel mainModel;
	Table_Model tab_Model;
	
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
	
	public void tellViewToAddGrammarCheckPanel() {
		mainView.addGrammarCheckPanel();
	}
	
	public void tellViewToRemoveGrammarCheckPanel() {
		mainView.removeGrammarCheckPanel();
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
				+ "discount-software@hotmail.com", true);
		
		mb.setVisible(true);
	}
	
	private void executeErrorRoutine() {
		MessageBox mb = new MessageBox("Programmstart fehlgeschlagen", "Fehler 000x001", "Kontaktieren Sie j.regev@ferrari.tsn.at");
		mb.setVisible(true);
	}

	public LinkedList<Controller_AbstractClass> getControllerList() {
		
		return controllerList;
		
	}

	public void addMLList(LinkedList<MouseListener> llML) {
		((ML_Tabellenkalkulation) llML.get(10)).getControllerandView(this, mainView);
		tell_View_To_setUpBasicStuff(mainView.getPotentialMessages(llML));
		
	}

	public JPanel execPaint2Konten(String konto1, String zahlungsKonto) {
		return mainModel.paint2Konten(konto1, zahlungsKonto);
	}
	
	public JPanel execPaint3Konten(String konto1, String konto2, String konto3) {
		return mainModel.paint3Konten(konto1, konto2, konto3);
	}
	
	public void execPaint4Konten(String konto1, String konto2, String konto3, String konto4) {
		mainModel.paint4Konten(konto1, konto2, konto3, konto4);
	}
	
	public JPanel execPaint2Konten_mitzyk(String konto4, String konto5) {
		return mainModel.paint2Konten_mitzyk(konto4, konto5);
	}
	
	public void execBrutto_to_paintAllRabatt(String percent, String price, String rabattPercentarge) {
		mainModel.brutto_to_paintAllRabatt(percent, price, rabattPercentarge);
	}
	
	public void execNetto_to_paintAllRabatt(String percent, String price, String rabattPercentarge) {
		mainModel.netto_to_paintAllRabatt(percent, price, rabattPercentarge);
	}
	
	public void execBrutto_to_paintAll4(String percent, String price1, String price2) {
		mainModel.brutto_to_paintAll4(percent, price1, price2);
	}
	
	public void execNetto_to_paintAll4(String percent, String price1, String price2) {
		mainModel.netto_to_paintAll4(percent, price1, price2);
	}
	
	public void execNetto_to_paintBrutto(String percent, String price) {
		mainModel.netto_to_paintBrutto(percent, price);
	}
	
	public void execBrutto_to_paintBrutto(String price) {
		mainModel.brutto_to_paintBrutto(price);
	}
	
	public void execNetto_to_paintAll3(String percent, String price) {
		mainModel.netto_to_paintAll3(percent, price);
	}
	
	public String execBrutto_to_paintAll3(String percent, String price) {
		return mainModel.brutto_to_paintAll3(percent, price);
	}
	
	public Double execCalculateBrutto(String percent, Double price) {
		return mainModel.calculateBrutto(percent, price);
	}
	
	public Double execCalculateNetto(String percent, Double price) {
		return mainModel.calculateNetto(percent, price);
	}
	
	public void execPaint1Price(double price) {
		mainModel.paint1Price(price);
	}
	
	public void execPaint3Prices(Double price1, Double price2, Double price3) {
		mainModel.paint3Prices(price1, price2, price3);
	}
	
	public void execPaint4Prices(Double price1, Double price2, Double price3, Double price4) {
		mainModel.paint4Prices(price1, price2, price3, price4);
	}
	
	public Double execCalcAbschreibung(String anlKonto, String IBN_Monat_str, String IBN_Year, String ND_str, Double AW, String command) {
		return mainModel.calcAbschreibung(anlKonto, IBN_Monat_str, IBN_Year, ND_str, AW, command);
	}
	
	public void execCalcNettoKreditkarten(String bruttoPrice_str, String spesen_str) {
		mainModel.calcNettoKreditkarten(bruttoPrice_str, spesen_str);
	}
	
	public void execCreateKontos1stufig_Skonto(String kontoLinks, String kontoRechts) {
		mainModel.createKontos1stufig_Skonto(kontoLinks, kontoRechts);
	}
	
	public String execNetto_to_newBrutto_Skonto(String price, String skPercent) {
		return mainModel.netto_to_newBrutto_Skonto(price, skPercent);
	}
	
	public String execBrutto_to_newBrutto_Skonto(String price, String skPercent) {
		return mainModel.brutto_to_newBrutto_Skonto(price, skPercent);
	}
	
	public String execBrutto_to_skBrutto_Skonto(String price, String skPercent) {
		return mainModel.brutto_to_skBrutto_Skonto(price, skPercent);
	}

	public void execOpenFile(LinkedList<Character> ll_Char) {
		mainModel.openFile(ll_Char);
	}

	public String execOpen() {
		LinkedList<Character> llChar = mainModel.open();
		return mainModel.convertLL_CharToString(llChar);
	}
	
	public void execOpenFileAndPrintToWorkPanel() {
		mainModel.open();
		mainModel.prepareCollection(0);
	}
	
	public void execPrintStringWithCodeToWorkPanel(String code) {
		LinkedList<Character> llChar = mainModel.convertStringToLL_Char(code);
		mainModel.openFile(llChar);
	}

	public String execExecuteFWRoutine(String price, String satz) {
		return mainModel.executeFWRoutine(price, satz);
	}

	public void execCalculateErwerbsteuerbetrag_andPaintIt(String price) {
		mainModel.calculateErwerbsteuerbetrag_andPaintIt(price);
	}

	public void execSaveAstxt() {
		mainModel.saveAstxt();
	}

	public Double execCalcGehaltsPercent_andPrintIt(String ausgangsPreis_str, String percent_str) {
		return mainModel.calcGehaltsPercent_andPrintIt(ausgangsPreis_str, percent_str);
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

	public void execRemoveLeftPanelAndMenu() {
		mainView.removeLeftPanelAndMenu();
	}
	
	public void execShowLeftPanel() {
		mainView.showLeftPanel();
	}

	public void execSetWindowListener() {
		mainView.setWindowListener();
	}

	public void execPaintUpTo7(LinkedList<String> kontoList, LinkedList<String> priceList, boolean leftMore, boolean moreThan4) {
		mainModel.paintUpTo7(kontoList, priceList, leftMore, moreThan4);
	}

	public LinkedList<String> execConvertTFListToStringList(LinkedList<JTextField> tfList) {
		return mainModel.convertTFListToStringList(tfList);
	}

	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
}
