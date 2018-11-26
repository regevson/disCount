package View;

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
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Personalverrechnung.Tagesgeld_View;
import extraViews.View_SuperClass;
import extraViews.View_SuperClass_2Outputs;

public class MainModel {
	

	public LinkedList<Double> llAfaPrice = new LinkedList<Double>();
	public LinkedList<String> llYear = new LinkedList<String>();
	
	public static LinkedList<String> llAufgabe = new LinkedList<String>();
	
	public static LinkedList<Double> bruttoValuesFromAbrechnung = new LinkedList<Double>();

	private static LinkedList<Character> llChar;
	
	private static int currentIndex;
	private static double abrechnung_netto = 0;
	private static double abrechnung_ust = 0;
	private static double abrechnung_brutto = 0;
	public static double biggestNetto = 0;
	private static String tempYear;
	
	public static LinkedList<String> abrAW_Konto = new LinkedList<String>();
	public static ArrayList<Double> solutions_AUSG = new ArrayList<Double>();

	private static boolean isSet = false;



	public static boolean saving = false;

	

	

	public static void setllAufgabe(LinkedList<String> aufgabe) {
		llAufgabe = aufgabe;
	}

	
	public String executeFWRoutine(String price, String satz) {
		return Double.toString((Double.parseDouble(price) / Double.parseDouble(satz)));
	}
	
	
//------------------------------------------------rounding-------------------------------------------------------------------------
	
	public static String round(double d) {
		String str = Double.toString(d);
		
        BigDecimal decimal = new BigDecimal(str);
        decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return decimal.toString();
    }
	
	
	public static Double roundDouble_giveBack_Double(Double num) {
		String str = Double.toString(num);
        BigDecimal decimal = new BigDecimal(str);
        decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
	    return Double.parseDouble(decimal.toString());
	}
	
	public static String roundDouble_giveBack_String(Double num) {
		String str = Double.toString(num);
		
        BigDecimal decimal = new BigDecimal(str);
        decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return decimal.toString();
	}
	
	

//------------------------------------------------paintSolutions-------------------------------------------------------------------------
	

	

	
	public Double calculateErwerbsteuerbetrag(String price) {
		Double tempPrice = Double.parseDouble(price);
		return tempPrice / 100 * 20;
	}
		
	
	public Double nettoToBrutto(String price, String percent) {
		Double nettoPrice = null;
		try {
			nettoPrice = Double.parseDouble(price);
		}catch(NumberFormatException numFormatEx) {
			price = "00.00";
			JOptionPane.showMessageDialog(null, "Das Komma muss als Punkt geschrieben werden!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		nettoPrice = Double.parseDouble(price);
		return MainModel.roundDouble_giveBack_Double(calculateBrutto(percent, nettoPrice));
	}
	
	public Double[] nettoToBrutto(String nettoPrice_str, String otherPrice_str, String percent) {
		Double otherPrice = Double.parseDouble(otherPrice_str); //emballagenpreis
		Double nettoPrice = Double.parseDouble(nettoPrice_str);
		
		Double steuer = (nettoPrice + otherPrice) / 100 * 20;
		Double bruttoPrice = nettoPrice + otherPrice + steuer;
		
		return new Double[] {nettoPrice, bruttoPrice, otherPrice, steuer};
	}
	
	public Double bruttoToNetto(String price, String percent) {
		Double bruttoPrice = null;
		try {
			bruttoPrice = Double.parseDouble(price);
		}catch(NumberFormatException numFormatEx) {
			price = "00.00";
			JOptionPane.showMessageDialog(null, "Das Komma muss als Punkt geschrieben werden!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		bruttoPrice = Double.parseDouble(price);
		double nettoPrice = MainModel.roundDouble_giveBack_Double(calculateNetto(percent, bruttoPrice));
		return nettoPrice;
	}
	
	public Double[] bruttoToNetto(String bruttoPrice_str, String otherPrice_str, String percent) {
		Double otherPrice = Double.parseDouble(otherPrice_str);
		Double bruttoPrice = Double.parseDouble(bruttoPrice_str);
		Double nettoPrice = calculateNetto(percent, bruttoPrice) - otherPrice;
		
		Double steuer = bruttoPrice - nettoPrice - otherPrice;
		
		return new Double[] {nettoPrice, bruttoPrice, otherPrice, steuer};
	}

	
	public Double calculateBrutto(String percent, Double price) {
		return (price / 100) * (100 + Integer.parseInt(percent));
	}
	
	public Double calculateNetto(String percent, Double price) {
		return (price / (100 + Integer.parseInt(percent))) * 100;
	}
	

	
	


	
	public Double[] calcNettoKreditkarten(String bruttoPrice_str, String spesen_str) {
		Double bruttoPrice = Double.parseDouble(bruttoPrice_str);
		Double spesen = Double.parseDouble(spesen_str);
		Double steuer = roundDouble_giveBack_Double(spesen / 100 * 20);
		Double nettoPrice = bruttoPrice - spesen - steuer;
		return new Double[] {nettoPrice, bruttoPrice, spesen, steuer};
	}
	
	
	public Double[] calculateRabattPricesFromNetto(int percent, Double nettoPrice, String rabattPercentarge) {
		Double rabattPercent = Double.parseDouble(rabattPercentarge);
		
		Double newNettoRabattPrice = MainModel.roundDouble_giveBack_Double((nettoPrice/100)*rabattPercent);
		Double tax = MainModel.roundDouble_giveBack_Double((newNettoRabattPrice/100)*percent);
		Double newBruttoRabattPrice = newNettoRabattPrice + tax;
		
		return new Double[] {newNettoRabattPrice, newBruttoRabattPrice, tax};
	}
	
	
	
	
	

	
	
	
	
	public Double nettoToSkontoBrutto(String price, String skPercent) {
		double nettoPrice = Double.parseDouble(price);
		double bruttoPrice = MainModel.roundDouble_giveBack_Double(calculateBrutto("20", nettoPrice));
		double skontoPercent = Double.parseDouble(skPercent);
		double skontoBruttoPrice = MainModel.roundDouble_giveBack_Double((bruttoPrice/100)*skontoPercent);
		bruttoPrice = bruttoPrice - skontoBruttoPrice;
		
		return skontoBruttoPrice;
	}
	
	public Double bruttoToSkontoBrutto(String price, String skPercent) {
		double bruttoPrice = Double.parseDouble(price);
		double skontoPercent = Double.parseDouble(skPercent);
		double skontoBruttoPrice = (bruttoPrice/100)*skontoPercent;
		
		return skontoBruttoPrice;
	}
	
	
	
	
//------------------------------------------------Personalverrechnung-------------------------------------------------------------------------
	
	public Double calcGehaltsPercent(String ausgangsPreis_str, String percent_str) {
		Double ausgangsPreis = Double.parseDouble(ausgangsPreis_str);
		Double percent = Double.parseDouble(percent_str);
		
		return (ausgangsPreis/100)*percent;
	}
	
	
	public Double calcTagesgeld(String anzTage_str, String startZeit, String endZeit, String essen) {
		
		int anzTage = Integer.parseInt(anzTage_str) - 1;
		System.out.println(anzTage);

		String[] temp1 = startZeit.split(":");
		Double part1Start = Double.parseDouble(temp1[0]);
		Double part2Start =  Double.parseDouble(temp1[1]);
		
		String[] temp2 = endZeit.split(":");
		Double part1End =  Double.parseDouble(temp2[0]);
		Double part2End =  Double.parseDouble(temp2[1]);
		
		if(part2Start > part2End) {
			++part1Start;
			part2Start = part2End;
		}
		
		else
			part2Start = part2End;
		
		
		Double newPart = part1End - part1Start;
		Double resTG = ((anzTage*Tagesgeld_View.tagesGeld) + (Tagesgeld_View.tagesGeld*(++newPart/12))) - (Tagesgeld_View.essensGeld * Integer.parseInt(essen));
		
		return resTG;
		
	}
	
	
	
	
//------------------------------------------------Abschreibung-------------------------------------------------------------------------
	

	public Double calcAbschreibung(String anlKonto, String IBN_Monat_str, String IBN_Year, String ND_str, Double AW, String command) {
		tempYear = IBN_Year;
		
		Double nd = Double.parseDouble(ND_str);
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
	

	
	
	
	
//----------------------------------Angabenanalyse------------------------------------------------------------------------	
	
	private static String replaceChars(String input, String[] replaceObj) {
		for(int x = 0; x < replaceObj.length; x++) {
			input = input.replace(replaceObj[x], "");
		}
		
	    return input;
	}
	

	
	
	private static int convert_String_to_Month_Int(String month) {
		 Date date = null;
			try {
				date = new SimpleDateFormat("MMM", Locale.GERMAN).parse(month);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(date);
			    return (cal.get(Calendar.MONTH)) + 1;
	}
	
	
	
	
	
	
	public static double getnext€(String search, String[] replaceObj, boolean isAbrechnung) {
		int indexSearch = llAufgabe.indexOf(search);
		System.out.println(llAufgabe.get(indexSearch) + " theone");
		
		if(isAbrechnung) {
			abrechnung_netto = getNextValue(indexSearch, replaceObj, isAbrechnung);
			removeOffList();
			
			abrechnung_ust = getNextValue(currentIndex, replaceObj, isAbrechnung);
			removeOffList();
			
			abrechnung_brutto = getNextValue(currentIndex, replaceObj, isAbrechnung);
			removeOffList();
		
		
			if(abrechnung_netto > biggestNetto)
				biggestNetto = abrechnung_netto;
			
			bruttoValuesFromAbrechnung.add(abrechnung_brutto);
			
			
			return 0.00;
		}
		
		else {
			double res = getNextValue(indexSearch, replaceObj, isAbrechnung);
			 return res;
		}
		
		
		
	}
	
	private static double getNextValue(int indexSearch, String[] replaceObj, boolean isAbrechnung) {
		int size = abrAW_Konto.size();
		
		for(int x = ++indexSearch; x < llAufgabe.size(); x++) {
			
			String currentString = llAufgabe.get(x).toLowerCase();
			
			if(isAbrechnung && currentString.contains("renovierung") || currentString.contains("instand") || currentString.contains("reperatur"))
				MainModel.abrAW_Konto.add("7200");
			
			
			if(currentString.equals("€")) {
				currentIndex = x + 1;
				break;
			}
			
		}
		
		if(isAbrechnung && size != abrAW_Konto.size())
			abrAW_Konto.add("0300");
		
		
		
		String tempString = llAufgabe.get(currentIndex);
		tempString = replaceChars(tempString, replaceObj);
		
		return Double.parseDouble(tempString);
	}
	
	
	

	
	private static void removeOffList() {
		for(int x = 0; x < currentIndex; x++) {
			 llAufgabe.removeFirst();
		 }
		
		currentIndex = 0;
	}
	
	

	
	public static int getNearMonth(int index) {
		System.out.println(index);
		System.out.println(llAufgabe.get(index) + "  !!indexNB");
		DateFormatSymbols dfs = new DateFormatSymbols(Locale.GERMANY);
		String[] months = dfs.getMonths();
		int[] occuredIndexes = {9999, 9999};
		String[] occuredMonths = {"", ""};
		String searchedMonth = null;

		 outer:
		 for(int x = index; x < llAufgabe.size(); x++) {

			 for(int y = 0; y < months.length; y++) {
				 if(!(months[y].toLowerCase().contains(llAufgabe.get(x).toLowerCase()))) {
					 if(llAufgabe.get(x).length() == 4 && llAufgabe.get(x).contains(".")) {
						String substring =  llAufgabe.get(x).substring(0, 3);
						
						if(!(months[y].contains(substring))) 
							continue;

					 }
					 else
						 continue;
				 }
					 
					 
					 
					 occuredMonths[0] = months[y];
					 occuredIndexes[0] = x;
					 break outer;
				 
			 }
		 }
		 
		 
		 outerminus:
			 for(int x = index; x <= 0; x--) {
				 for(int y = 0; y < months.length; y++) {
					 if(!(months[y].toLowerCase().contains(llAufgabe.get(x).toLowerCase()))) {
						 if(llAufgabe.get(x).length() == 4 && llAufgabe.get(x).contains(".")) {
							String substring =  llAufgabe.get(x).substring(0, 3);
							
							if(!(months[y].contains(substring)))
								continue;
							
						 }
						 else
							 continue;
					 }
						 
						 
						 
						 occuredMonths[0] = months[y];
						 occuredIndexes[0] = x;
						 break outerminus;
				} 
			 }
		 
				 
		
			 if(occuredIndexes[0] < occuredIndexes[1])
				 searchedMonth = occuredMonths[0];
			 else
				 searchedMonth = occuredMonths[1];
		
		 
		return convert_String_to_Month_Int(searchedMonth);
		
		
	}
	
	//sucht einen String und sucht dann ab diesem index nach dem nächsten String und gibt dessen Value beim dessen index - 1 zurück
	
	public static String getString_nearOtherString(String searchedKeyword, String searchedValue, String[] replaceObj, int offset) {
		boolean wasSuccessful = false;
		int stopHere = MainModel.llAufgabe.size();
		
		int indexRND = llAufgabe.indexOf(searchedKeyword);
		
		if(offset != 0)
			stopHere = indexRND + offset;
		
		 for(int x = ++indexRND; x < stopHere; x++) {
			 if(llAufgabe.get(x).equals(searchedValue)) {
				 indexRND = x - 1;
				 wasSuccessful = true;
				 break;
			 }
			 
		 }
		 
		 if(wasSuccessful == false)
			 return "-1";
		 
		 else {
			 String restND = replaceChars(llAufgabe.get(indexRND), replaceObj);
			 return restND.replace(",", ".");
		 }
	}
	
	
	
	
	
	public static ArrayList<Double> erweiterungGeb_abrechnung_ausgleich(String[] replaceObj) {
		
		ArrayList<Double> solutions_ABR = new ArrayList<Double>();
		String stringUsed;
		
		int indexAUSG = llAufgabe.indexOf("Ausgleich");
		int indexBA = llAufgabe.indexOf("Bankausgang");
		
		System.out.println(indexAUSG + "      indexAUSG");
		System.out.println(indexBA + "      indexBA");
		
		if(llAufgabe.get(indexBA + 1).equals("€"))
			stringUsed = "Bankausgang";
		
		else
			stringUsed = "Ausgleich";
		
		
		
		while(true) {
			System.out.println("here");
			System.out.println(stringUsed);
			
			int indexABR = llAufgabe.indexOf("Abrechnung");
			int indexAG_BAG = llAufgabe.indexOf(stringUsed);
			

			
			
			if(indexABR < indexAG_BAG && indexABR != -1) {
				getnext€("Abrechnung", replaceObj, true);
				isSet = false;
				solutions_ABR = addAbrechnungen(solutions_ABR);
				solutions_AUSG.add(0.00);
			}
			
			else if(indexAG_BAG != -1) {
				System.out.println(stringUsed + "  str_UsedbyAngabe");
				System.out.println(llAufgabe);
				String isTeilausgleich = MainModel.getString_nearOtherString(stringUsed, "Teilausgleich", replaceObj, 5);
				
				if(isTeilausgleich.equals("-1"))
					solutions_AUSG.add(-1.00);
				
				else {
					System.out.println(llAufgabe);
					solutions_AUSG.add(getnext€(stringUsed, replaceObj, false));
				}
				
				currentIndex = indexAG_BAG + 1;
				removeOffList();
			}
			else
				break;
			
		}
		
		
		
		return solutions_ABR;
		
		
		
	}
	
	private static ArrayList<Double> addAbrechnungen(ArrayList<Double> solutions_ABR) {
		solutions_ABR.add(abrechnung_netto);
		solutions_ABR.add(abrechnung_brutto);
		solutions_ABR.add(abrechnung_ust);
		
		return solutions_ABR;
	}
	
	
	
	public static String getCodeOnWorkPanel() {
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
	
	
	
	public void saveProject() {
		
		MainModel.saving = true;
		
		String fileName = openFileChooser_Save("disCount-Dateien", "dc", "disCount");
		saveEncodedContent(fileName);
		saveTxtFile(fileName);
		
		MainModel.saving = false;
		
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
	
	
	
	
	
	
	
	
	
	

	
}
	

