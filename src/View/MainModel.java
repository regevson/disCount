package View;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Personalverrechnung.Tagesgeld_View;

public class MainModel {
	

	public LinkedList<Double> llAfaPrice = new LinkedList<Double>();
	public LinkedList<String> llYear = new LinkedList<String>();
	
	public static LinkedList<String> llAufgabe = new LinkedList<String>();
	
	public static LinkedList<Double> bruttoValuesFromAbrechnung = new LinkedList<Double>();

	private static LinkedList<Character> llChar;
	
	public static double biggestNetto = 0;
	private static String tempYear;
	
	public static LinkedList<String> abrAW_Konto = new LinkedList<String>();
	public static ArrayList<Double> solutions_AUSG = new ArrayList<Double>();

	public static boolean saving = false;
	public static boolean printErrorMessages = true;

	
	
	
	
	
	

	

	public static void setllAufgabe(LinkedList<String> aufgabe) {
		llAufgabe = aufgabe;
	}

	
	public String executeFWRoutine(String price, String satz) {
		return Double.toString((MainModel.parseDouble(price) / MainModel.parseDouble(satz)));
	}
	
	
//------------------------------------------------rounding-------------------------------------------------------------------------
	
	
	public static Double roundDouble_giveBack_Double(Double num) {
		
		String str = Double.toString(num);
        BigDecimal decimal = new BigDecimal(str);
        decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        
	    return MainModel.parseDouble(decimal.toString());
	    
	}
	
	public static String roundDouble_giveBack_String(Double num) {
		
		String str = Double.toString(num);
        BigDecimal decimal = new BigDecimal(str);
        decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        return decimal.toString();
        
	}
	
	

//------------------------------------------------paintSolutions-------------------------------------------------------------------------
	

	

	
	public double calculateErwerbsteuerbetrag(String price) {
	
		double tempPrice = MainModel.parseDouble(price);
		
		return tempPrice / 100 * 20;
		
	}
		
	
	public double nettoToBrutto(String price, String percent) {
	
		double nettoPrice = MainModel.parseDouble(price);
		double bruttoPrice = MainModel.roundDouble_giveBack_Double(calculateBrutto(percent, nettoPrice));
		
		return bruttoPrice;
		
	}
	
	
	public double bruttoToNetto(String price, String percent) {

		double bruttoPrice = MainModel.parseDouble(price);
		double nettoPrice = MainModel.roundDouble_giveBack_Double(calculateNetto(percent, bruttoPrice));
		
		return nettoPrice;
		
	}
	


	
	public Double[] nettoToBrutto(String nettoPrice_str, String otherPrice_str, String percent) {

		double otherPrice = MainModel.parseDouble(otherPrice_str); //emballagenpreis
		double nettoPrice = MainModel.parseDouble(nettoPrice_str);
		
		double steuer = (nettoPrice + otherPrice) / 100 * 20;
		double bruttoPrice = nettoPrice + otherPrice + steuer;
		
		return new Double[] {nettoPrice, bruttoPrice, otherPrice, steuer};
		
	}
	
	
	public Double[] bruttoToNetto(String bruttoPrice_str, String otherPrice_str, String percent) {
			
		double otherPrice = MainModel.parseDouble(otherPrice_str);
		double bruttoPrice = MainModel.parseDouble(bruttoPrice_str);
		double nettoPrice = calculateNetto(percent, bruttoPrice) - otherPrice;
		
		double steuer = bruttoPrice - nettoPrice - otherPrice;
		
		return new Double[] {nettoPrice, bruttoPrice, otherPrice, steuer};
		
	}

	
	public double calculateBrutto(String percent, Double price) {
		return (price / 100) * (100 + Integer.parseInt(percent));
	}
	
	public double calculateNetto(String percent, Double price) {
		return (price / (100 + Integer.parseInt(percent))) * 100;
	}
	

	
	


	
	public Double[] calcNettoKreditkarten(String bruttoPrice_str, String spesen_str) {
	
		double bruttoPrice = MainModel.parseDouble(bruttoPrice_str);
		double spesen = MainModel.parseDouble(spesen_str);
		double steuer = roundDouble_giveBack_Double(spesen / 100 * 20);
		double nettoPrice = bruttoPrice - spesen - steuer;
		
		return new Double[] {nettoPrice, bruttoPrice, spesen, steuer};
		
	}
	
	
	public Double[] calculateRabattPricesFromNetto(int percent, Double nettoPrice, String rabattPercentarge) {
	
		double rabattPercent = MainModel.parseDouble(rabattPercentarge);
		double newNettoRabattPrice = MainModel.roundDouble_giveBack_Double((nettoPrice/100)*rabattPercent);
		double tax = MainModel.roundDouble_giveBack_Double((newNettoRabattPrice/100)*percent);
		double newBruttoRabattPrice = newNettoRabattPrice + tax;
		
		return new Double[] {newNettoRabattPrice, newBruttoRabattPrice, tax};
		
	}
	
	
	
	
	
	
	public double nettoToSkontoBrutto(String price, String skPercent) {
	
		double nettoPrice = MainModel.parseDouble(price);
		double bruttoPrice = MainModel.roundDouble_giveBack_Double(calculateBrutto("20", nettoPrice));
		double skontoPercent = MainModel.parseDouble(skPercent);
		double skontoBruttoPrice = MainModel.roundDouble_giveBack_Double((bruttoPrice/100)*skontoPercent);
		
		return skontoBruttoPrice;
		
	}
	
	public double bruttoToSkontoBrutto(String price, String skPercent) {
		
		double bruttoPrice = MainModel.parseDouble(price);
		double skontoPercent = MainModel.parseDouble(skPercent);
		double skontoBruttoPrice = (bruttoPrice/100)*skontoPercent;
		
		return skontoBruttoPrice;
		
	}
	
	
	
	
//------------------------------------------------Personalverrechnung-------------------------------------------------------------------------
	
	public double calcGehaltsPercent(String ausgangsPreis_str, String percent_str) {
	
		double ausgangsPreis = MainModel.parseDouble(ausgangsPreis_str);
		double percent = MainModel.parseDouble(percent_str);
		double newPercent = (ausgangsPreis/100)*percent; 
		
		return newPercent;
		
	}
	
	
	public double calcTagesgeld(String anzTage_str, String startZeit, String endZeit, String essen) {
	
		int anzTage = Integer.parseInt(anzTage_str) - 1;

		String[] temp1 = startZeit.split(":");
		double part1Start = MainModel.parseDouble(temp1[0]);
		double part2Start =  MainModel.parseDouble(temp1[1]);
		
		String[] temp2 = endZeit.split(":");
		double part1End =  MainModel.parseDouble(temp2[0]);
		double part2End =  MainModel.parseDouble(temp2[1]);
		
		if(part2Start > part2End) {
			++part1Start;
			part2Start = part2End;
		}
		
		else
			part2Start = part2End;
		
		
		double newPart = part1End - part1Start;
		double resTG = ((anzTage*Tagesgeld_View.tagesGeld) + (Tagesgeld_View.tagesGeld*(++newPart/12))) - (Tagesgeld_View.essensGeld * Integer.parseInt(essen));
	
		return resTG;
		
	}
	
	
	
	
//------------------------------------------------Abschreibung-------------------------------------------------------------------------
	

	public Double calcAbschreibung(String anlKonto, String IBN_Monat_str, String IBN_Year, String ND_str, Double AW, String command) {
		tempYear = IBN_Year;
		
		Double nd = MainModel.parseDouble(ND_str);
		int IBN_Monat = Integer.parseInt(IBN_Monat_str);
		
		Double afaBetrag = AW/nd;
		
		
		
		llYear.addLast(tempYear);
							//9
		for(int x = 0; x < nd-1; x++) {
			llYear.addLast(getDate());
			llAfaPrice.addLast(afaBetrag);
		}
		
		llAfaPrice.set(llAfaPrice.size()-1, afaBetrag-1);
		
		if(IBN_Monat > 6) {// dann halbjahres AFA
			llAfaPrice.set(0, afaBetrag/2);
			llAfaPrice.set(llAfaPrice.size()-1, afaBetrag);
			llAfaPrice.addLast(afaBetrag/2-1);
			llYear.addLast(getDate());
			afaBetrag = afaBetrag/2;
		}
		
		return afaBetrag;
		
	}
	
	private String getDate() {
		int year = Integer.parseInt(tempYear);
		tempYear = Integer.toString(++year);
		return tempYear;
	}
	

	
	
	
	
	
	
	
	public static String getCodeOnWorkPanel() {
		
		refreshAllBSLists();
		
		String code = "";
		
		for(int x = 0; x < MainView.bsList.size(); x++) {
			code += MainView.bsList.get(x).getCode();
		}

		return code;
	}
	
	public static String getTxtContent() {
		String content = "";
		
		for(int x = 0; x < MainView.bsList.size(); x++) {
			content += MainView.bsList.get(x).getTxtContent();
		}

		return content;
	}
	
	
	
//----------------------------------deleteBS------------------------------------------------------------------------	

	public static void deleteChecked(JPanel workPanel) {
		
		LinkedList<Buchungssatz> bsList = MainView.bsList;
		ArrayList<Buchungssatz> deleteList = new ArrayList<Buchungssatz>(); 
		
		for(int x = 0; x < bsList.size(); x++) {
			if(bsList.get(x).getRadioButtonStatus() == true) {
				bsList.get(x).removeBS(workPanel);
				workPanel.remove(bsList.get(x).getBSPanel());
				deleteList.add(bsList.get(x));
			}
		}
		
		deleteFromBSList(deleteList);
		
		if(bsList.size() != 0)
			bsList.getLast().paintNumberOnBSNumberPanel();
		
	}
	
	
	
	public static void deleteAll(JPanel workPanel) {
		
		LinkedList<Buchungssatz> bsList = MainView.bsList;
		
		workPanel.removeAll();
		bsList.removeAll(bsList);
		
		MainView.bsPanelMargin = 20;
		
		workPanel.repaint();
		
	}
	
	private static void deleteFromBSList(ArrayList<Buchungssatz> list) {
		for(int x = 0; x < list.size(); x++) {
			MainView.bsList.remove(list.get(x));
		}
	}
	
	
	
	
	
	
	
	
//----------------------------------openFiles------------------------------------------------------------------------
	

	
	public File openFileChooser__Open() {
		
		File file = null;
		
		try {	
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("disCount-Dateien", "dc", "disCount");
			fc.setFileFilter(filter);
			
			fc.showOpenDialog(MainView.workPanel);
			file = fc.getSelectedFile();
		}catch(Exception e) {System.out.println("MainModel - openFileChooser__Open - didnt work!");}
		
		return file;
			
	}
	
	public void openProject_WithFile(File file, JPanel workPanel) {
		ArrayList<Buchungssatz> bsList = new ArrayList<Buchungssatz>();
		llChar = loadFileIntoLLChar(file);
		prepareCollection(0, workPanel, bsList);
	}
	
	public ArrayList<Buchungssatz> openProject_WithList(LinkedList<Character> char_LL, JPanel workPanel) {
		ArrayList<Buchungssatz> bsList = new ArrayList<Buchungssatz>();
		llChar = char_LL;
		return prepareCollection(0, workPanel, bsList);
	}
	
	
	private LinkedList<Character> loadFileIntoLLChar(File file) {
		
		//Add characters to char-LinkedList
		try {
			llChar = new LinkedList<Character>();
			
			BufferedReader reader;
		
			reader = new BufferedReader(
				    new InputStreamReader(
				        new FileInputStream(file),
				        Charset.forName("UTF-8")));
		
			int c;
			while((c = reader.read()) != -1) {
			  char character = (char) c;
			  llChar.addLast(character);
			}
			
			
			reader.close();
		} catch (FileNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
		
		return llChar;
		
	}
	
	
	
	
	public String openFileChooser_Save(String filetype, String prefix, String desc) {
		
		  JFileChooser fileChooser = new JFileChooser();
		  FileNameExtensionFilter filter = new FileNameExtensionFilter(filetype, prefix, desc);
		    fileChooser.setFileFilter(filter);
		    int status = fileChooser.showSaveDialog(MainView.workPanel);

		    String fileName = null;
		    
		    if (status == JFileChooser.APPROVE_OPTION) {
		        File selectedFile = fileChooser.getSelectedFile();

		        
		        try {
		            fileName = selectedFile.getCanonicalPath();
		            if (!fileName.endsWith("dc")) {
		                selectedFile = new File(fileName + "prefix");
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		    
		    return fileName;

	}
	
	
	
	public String saveProject() {
		
		MainModel.saving = true;
		
		String fileName = openFileChooser_Save("disCount-Dateien", "dc", "disCount");
		saveEncodedContent(fileName);
		saveTxtFile(fileName);
		
		MainModel.saving = false;
		
		return fileName;
		
	}
	
	private void saveEncodedContent(String fileName) {

		PrintWriter writer;
		
		try {
			
			writer = new PrintWriter(fileName + ".dc", "UTF-8");
			
			writer.printf(getCodeOnWorkPanel());
			writer.close();
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {System.out.println("MainModel - saveEncodedContent - DIDNT WORK");e.printStackTrace();}
		
	}
	

	public void saveTxtFile(String fileName) {
		
		PrintWriter writer;
		
		try {
			
			writer = new PrintWriter(fileName + ".txt", "UTF-8");
			
			writer.print(getTxtContent());
			writer.close();
		
		} catch (FileNotFoundException | UnsupportedEncodingException e) {System.out.println("MainModel - saveEncodedContent - DIDNT WORK");e.printStackTrace();}
		
	}
	
	
	
	public ArrayList<Buchungssatz> prepareCollection(int index, JPanel workPanel, ArrayList<Buchungssatz> bsList) {
		
		boolean leftMore = false;
		
		if(index != -1) {
			
			LinkedList<String> llKontos = new LinkedList<String>();
			LinkedList<String> llPrices = new LinkedList<String>();
			
			char currentChar = llChar.get(index);
			
			if(currentChar == '#') {
				currentChar = llChar.get(++index);
				
				if(currentChar == '-')
					leftMore = true;
				
				currentChar = llChar.get(++index);
				
				index = collectKontos_Prices(llPrices, collectKontos_Prices(llKontos, ++index));
				
				bsList.add(createNewBS(llKontos, llPrices, leftMore, workPanel));
				
				prepareCollection(index, workPanel, bsList);

			}
			
		}
		
		return bsList;
	}
	
	private Buchungssatz createNewBS(LinkedList<String> llKontos, LinkedList<String> llPrices, boolean leftMore, JPanel workPanel) {
		Buchungssatz bs = new Buchungssatz();
		
		String[] kontoList = convertLLStringToStringArray(llKontos);
		String[] priceList = convertLLStringToStringArray(llPrices);
		
		bs.initBS(kontoList, priceList, leftMore, workPanel);
		
		return bs;
	}
	
	
	
	public static String[] convertLLStringToStringArray(LinkedList<String> ll) {
		String[] array = new String[ll.size()];
		return ll.toArray(array);
	}
	
	
	
	
	private static int collectKontos_Prices(LinkedList<String> llKontos_Prices, int currentIndex_arg) {
		
		String konto = "";
		llKontos_Prices.addLast("");
		
		for(int currentIndex = ++currentIndex_arg; currentIndex < llChar.size(); currentIndex++) {
			char currentChar = llChar.get(currentIndex);
			
			if(currentChar == '/')
				return collectKontos_Prices(llKontos_Prices, currentIndex);

			else {
				
				if(currentChar == '&' || currentChar == '#' || currentChar == -1) {
					return currentIndex;
				}
				
				else {
					konto = konto + Character.toString(currentChar);
					llKontos_Prices.set(llKontos_Prices.size() - 1, konto);
				}
			}
		}
		
		return -1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String convertLL_CharToString(LinkedList<Character> llChar) {
		String str = "";
		for(int x = 0; x < llChar.size(); x++) {
			str = str + llChar.get(x);
		}
		
		return str;
	}


	public static LinkedList<Character> convertStringToLL_Char(String code) {
		
		LinkedList<Character> llChar = new LinkedList<Character>();
		
		for(int x = 0; x < code.length(); x++) {
			llChar.addLast(code.charAt(x));
		}
		
		return llChar;
	}
	
	
	public static LinkedList<String> convertTFListToStringList(LinkedList<JTextField> tfList) {
		LinkedList<String> strList = new LinkedList<String>();
		
		for(int x = 0; x < tfList.size(); x++) {
			strList.add(tfList.get(x).getText());
		}
		
		return strList;
	}

	/////////////////////////////////////////
	
	

	public static String[] convertDoubleArrayToStringArray(Double[] doubleArray) {
		String[] a=Arrays.toString(doubleArray).split("[\\[\\]]")[1].split(", "); 
		return a;
	}
	
	public static int countOccurencesOfChar(String str, String item) {
		return str.length() - str.replaceAll(item,"").length();
	}
	
	public static LinkedList<String> getWorkPanelCodeIntoList(LinkedList<String> list) {
        for(int x = 0; x < MainView.bsList.size(); x++) {
        	list.add(MainView.bsList.get(x).getCode());
	    }
        
        return list;
	}
	
	
	public static void printErrorMessage() {
		if(printErrorMessages) {
			JOptionPane.showMessageDialog(null, "Die eingegebenen Werte verursachen Fehler! \n\nHäufige Fehlerquellen: \n- Kein Punkt als Komma \n- Prozentzeichen nicht entfernt"
					+ "  \n- Unbenutzte Felder nicht 0 gesetzt", "Warning", JOptionPane.WARNING_MESSAGE);
			printErrorMessages = false;
		}
	}
	
	public static double parseDouble(String str) {
		
		double val = 0.00;
		
		try {
			
			val = Double.parseDouble(str);
			
		}catch(NumberFormatException numFormatEx) {val = 0.00; printErrorMessage();}
		
		return val;
		
	}


	public void printProject() {
		
		try {

			JTextPane jtp = new JTextPane();
			jtp.setBackground(Color.white);
			jtp.setText(getTxtContent());
			boolean show = true;
			jtp.print(null, null, show, null, null, show);

		}catch(Exception e) {e.printStackTrace(); System.out.println("MainModel - printProject - didnt work!!!");}	
		
	}


	public void paintBSListToWorkPanel(JPanel workPanel) {
		
		for(int x = 0; x < MainView.bsList.size(); x++) {
			
			MainView.bsList.get(x).getBSPanel().setBounds(5, MainView.bsPanelMargin, 585, Buchungssatz.bsPanelHeight);
			MainView.bsList.get(x).setBSPanelMargin(MainView.bsPanelMargin);
			workPanel.add(MainView.bsList.get(x).getBSPanel());
			MainView.bsPanelMargin += 170;
			
		}
		
		MainView.bsList.getLast().paintNumberOnBSNumberPanel();
		
		MainView.workPanel.revalidate();
		MainView.workPanel.repaint();
		
	}
	
	
	public static void refreshAllBSLists() {
		
		for(int x = 0; x < MainView.bsList.size(); x++) {
			
			MainView.bsList.get(x).refreshLists();
			
		}
		
	}


	public ArrayList<String> decodeHashTag(String content, int oldCount, int newCount) {
		
		ArrayList<String> contentList = new ArrayList<String>();
		int index = -1;
		
		for(int x = 0; x <= oldCount; x++) {	
			index = content.indexOf("#", index + 1);
		}
		
		content = content.substring(index + 1);
		
		for(int x = 0; x < newCount; x++) {
			
			if(content.indexOf("#") == -1) {
				
				contentList.add(content);
				break;
				
			}

			contentList.add(content.substring(0, content.indexOf("#")));
			content = content.substring(content.indexOf("#") + 1);
			
		}
		
		return contentList;
		
	}


	public void changeBSColorDueToCodeCommitHistory(ArrayList<String> codeCommitList, String myEmail) {
		
		for(int x = 0; x < MainView.bsList.size(); x++) {
			
			if(codeCommitList.get(x).equals(myEmail))
				MainView.bsList.get(x).getBSNumberPanel().setBackground(MainView.disCountBlue);

			else {
				
				MainView.bsList.get(x).getBSNumberPanel().setBackground(MainView.disCountBrown);
				MainView.bsList.get(x).paintBorder(MainView.disCountBrown);
				
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	

	
}
	

