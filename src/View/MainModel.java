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
import extraViews.BS_Editor;
import extraViews.View_SuperClass;
import extraViews.View_SuperClass_2Outputs;

public class MainModel {
	

	private static LinkedList<Double> llAfaPrice = new LinkedList<Double>();
	private static LinkedList<String> llYear = new LinkedList<String>();
	public static LinkedList<String> contentForTxt  = new LinkedList<String>();
	
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
	
	public void netto_to_paintBrutto(String percent, String price) {
		Double nettoPrice = null;
		try {
			nettoPrice = Double.parseDouble(price);
		}catch(NumberFormatException numFormatEx) {
			price = "00.00";
			JOptionPane.showMessageDialog(null, "Das Komma muss als Punkt geschrieben werden!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		nettoPrice = Double.parseDouble(price);
		Double bruttoPrice = calculateBrutto(percent, nettoPrice);

		paint1Price(bruttoPrice);
	}
	
	public String brutto_to_paintBrutto(String price) {
		Double bruttoPrice = null;
		try {
			bruttoPrice = Double.parseDouble(price);
		}catch(NumberFormatException numFormatEx) {
			price = "00.00";
			JOptionPane.showMessageDialog(null, "Das Komma muss als Punkt geschrieben werden!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		bruttoPrice = Double.parseDouble(price);
		
		paint1Price(bruttoPrice);
		return round(bruttoPrice);
	}
	
	public void calculateErwerbsteuerbetrag_andPaintIt(String price) {
		Double tempPrice = Double.parseDouble(price);
		paint1Price((tempPrice/100)*20);
	}
		
	
	public void netto_to_paintAll3(String percent, String price) {
		Double nettoPrice = null;
		try {
			nettoPrice = Double.parseDouble(price);
		}catch(NumberFormatException numFormatEx) {
			price = "00.00";
			JOptionPane.showMessageDialog(null, "Das Komma muss als Punkt geschrieben werden!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		nettoPrice = Double.parseDouble(price);
		Double bruttoPrice = MainModel.roundDouble_giveBack_Double(calculateBrutto(percent, nettoPrice));
		Double steuer = bruttoPrice - nettoPrice;
		

		
		paint3Prices(nettoPrice, bruttoPrice, steuer);
		
	}
	
	public String brutto_to_paintAll3(String percent, String price) {
		Double bruttoPrice = null;
		try {
			bruttoPrice = Double.parseDouble(price);
		}catch(NumberFormatException numFormatEx) {
			price = "00.00";
			JOptionPane.showMessageDialog(null, "Das Komma muss als Punkt geschrieben werden!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		bruttoPrice = Double.parseDouble(price);
		Double nettoPrice = MainModel.roundDouble_giveBack_Double(calculateNetto(percent, bruttoPrice));
		Double steuer = bruttoPrice - nettoPrice;
		
		paint3Prices(nettoPrice, bruttoPrice, steuer);
		return Double.toString(nettoPrice);
	}

	
	public Double calculateBrutto(String percent, Double price) {
		return (price / 100) * (100 + Integer.parseInt(percent));
	}
	
	public Double calculateNetto(String percent, Double price) {
		return (price / (100 + Integer.parseInt(percent))) * 100;
	}
	

	public void paint1Price(double price) {
		
		MainView.addToPanel(MainView.addBasicToPanel(round(price), 350, MainView.margin+10, 100, 100));
		
		MainView.llNettoPrices.addLast((double) 0);
		
		String tempCode = MainView.hmPanelToCode.get(MainView.llJPanel.getLast());
		tempCode += "&" + round(price);
		MainView.hmPanelToCode.put(MainView.llJPanel.getLast(), tempCode);
		
	}

	
	public void paint3Prices(Double price1, Double price2, Double price3) {
		
		MainView.addToPanel(MainView.addBasicToPanel(round(Math.abs(price1)), View_SuperClass.pricesLeftWP, MainView.margin, 100, 100));
		MainView.addToPanel(MainView.addBasicToPanel(round(Math.abs(price2)), View_SuperClass.pricesRightWP, MainView.margin, 100, 100));
		MainView.addToPanel(MainView.addBasicToPanel(round(Math.abs(price3)), View_SuperClass.pricesLeftWP, MainView.margin+20, 100, 100));
		
		if(View_SuperClass.pricesLeftWP == 350) {
			String tempCode = MainView.hmPanelToCode.get(MainView.llJPanel.getLast());
			tempCode += "&" + round(price1) + "/" + round(price2) + "/" + round(price3);
			MainView.hmPanelToCode.put(MainView.llJPanel.getLast(), tempCode);
			
			MainView.llNettoPrices.addLast(price1);
		}
		
		else {
			String tempCode = MainView.hmPanelToCode.get(MainView.llJPanel.getLast());
			tempCode += "&" + round(price2) + "/" + round(price1) + "/" + round(price3);
			MainView.hmPanelToCode.put(MainView.llJPanel.getLast(), tempCode);
			
			MainView.llNettoPrices.addLast(Double.parseDouble("-" + Double.toString(price1)));
		}
			
	}
	
	
	public void paintUpTo7(LinkedList<String> kontoList, LinkedList<String> priceList, boolean leftMore, boolean moreThan4) {

		JPanel jp;
		int stufen = kontoList.size() - 1;
		int adaptMargin;
		
		if(!moreThan4) {
			jp = MainView.createJPanel();
			adaptMargin = -(6 * (stufen - 1));
		}
		else {
			jp = MainView.createJPanel();
			MainView.reModifyJPanel(jp);
			adaptMargin = -(9 * (stufen - 1));
		}


		int marginLeftKontos;
		int marginLeftPrices;
		if(leftMore) {
			marginLeftKontos = View_SuperClass.kontenLeftWP;
			marginLeftPrices = View_SuperClass.pricesLeftWP;
		}
		else {
			marginLeftKontos = View_SuperClass.kontenRightWP;
			marginLeftPrices = View_SuperClass.pricesRightWP;
		}
		
		jp.add(MainView.addBasicToPanel(round(Math.abs(Double.parseDouble(kontoList.get(0)))), View_SuperClass.kontenLeftWP, MainView.margin + adaptMargin, 100, 100));
		MainView.addToPanel(MainView.addBasicToPanel(round(Math.abs(Double.parseDouble(kontoList.get(1)))), View_SuperClass.kontenRightWP, MainView.margin + adaptMargin, 100, 100));
		
		MainView.addToPanel(MainView.addBasicToPanel(round(Math.abs(Double.parseDouble(priceList.get(0)))), View_SuperClass.pricesLeftWP, MainView.margin + adaptMargin, 100, 100));
		MainView.addToPanel(MainView.addBasicToPanel(round(Math.abs(Double.parseDouble(priceList.get(1)))), View_SuperClass.pricesRightWP, MainView.margin + adaptMargin, 100, 100));
		
		adaptMargin += 20;
		
		for(int x = 2; x < kontoList.size(); x++) {
			jp.add(MainView.addBasicToPanel(round(Math.abs(Double.parseDouble(kontoList.get(x)))), marginLeftKontos, MainView.margin + adaptMargin, 100, 100));
			adaptMargin += 20;
		}
		
		if(!moreThan4)
			adaptMargin = -(6 * (stufen - 1)) + 20;
		else
			adaptMargin = -(9 * (stufen - 1)) + 20;
		
		for(int x = 2; x < priceList.size(); x++) {
			MainView.addToPanel(MainView.addBasicToPanel(round(Math.abs(Double.parseDouble(priceList.get(x)))), marginLeftPrices, MainView.margin + adaptMargin, 100, 100));
			adaptMargin += 20;
		}
		
		addIntoLists(jp, leftMore, stufen, kontoList, priceList);
	}
	
	
	
	private void addIntoLists(JPanel jp, boolean leftMore, int stufen, LinkedList<String> kontoList, LinkedList<String> priceList) {
		String code = "#";
		if(leftMore) {
			code += "-" + (stufen + 1) + "*" + kontoList.get(0) + "/" + kontoList.get(1);
		}
		else
			code += "+" + (stufen + 1) + "*" + kontoList.get(0) + "/" + kontoList.get(1);
		
		for(int x = 2; x < kontoList.size(); x++) {
			code += "/" + kontoList.get(x);
		}
		
		MainView.hmPanelToCode.put(jp, code);
		
		
		code = MainView.hmPanelToCode.get(MainView.llJPanel.getLast());
		code += "&";
		
		if(leftMore) {
			code += round(Double.parseDouble(priceList.get(0))) + "/" + round(Double.parseDouble(priceList.get(1)));
		}
		else
			code += round(Double.parseDouble(priceList.get(0))) + "/" + round(Double.parseDouble(priceList.get(1)));
		
		for(int x = 2; x < priceList.size(); x++) {
			code += "/" + round(Double.parseDouble(priceList.get(x)));
		}
		MainView.hmPanelToCode.put(MainView.llJPanel.getLast(), code);
		
		MainView.llNettoPrices.addLast((double) 0);
	}

	
	
	public void paint4Prices(Double price1, Double price2, Double price3, Double price4) {
		
		MainView.addToPanel(MainView.addBasicToPanel(round(price1), View_SuperClass.pricesLeftWP, MainView.margin, 100, 100));
		MainView.addToPanel(MainView.addBasicToPanel(round(price2), View_SuperClass.pricesRightWP, MainView.margin, 100, 100));
		MainView.addToPanel(MainView.addBasicToPanel(round(price3), View_SuperClass.pricesLeftWP, MainView.margin+20, 100, 100));
		MainView.addToPanel(MainView.addBasicToPanel(round(price4), View_SuperClass.pricesLeftWP, MainView.margin+40, 100, 100));
		
		String tempCode = MainView.hmPanelToCode.get(MainView.llJPanel.getLast());
		tempCode += "&" + round(price1) + "/" + round(price2) + "/" + round(price3) + "/" + round(price4);
		MainView.hmPanelToCode.put(MainView.llJPanel.getLast(), tempCode);
		
		MainView.llNettoPrices.addLast((double) 0);
		
	}
	
	public void netto_to_paintAll4(String percent, String price1, String price2) {
		Double secondPrice = Double.parseDouble(price2); //emballagenpreis
		Double nettoPrice = Double.parseDouble(price1);
		
		Double steuer = ((nettoPrice + secondPrice)/100)*20;
		Double bruttoPrice = nettoPrice + secondPrice + steuer;
		
		paint4Prices(nettoPrice, bruttoPrice, secondPrice, steuer);
	}
	
	public void brutto_to_paintAll4(String percent, String price1, String price2) {
		Double secondPrice = Double.parseDouble(price2);
		Double bruttoPrice = Double.parseDouble(price1);
		Double nettoPrice = calculateNetto(percent, bruttoPrice) - secondPrice;
		
		Double steuer = bruttoPrice - nettoPrice - secondPrice;
		
		paint4Prices(nettoPrice, bruttoPrice, secondPrice, steuer);
	}
	
	public void calcNettoKreditkarten(String bruttoPrice_str, String spesen_str) {
		Double bruttoPrice = Double.parseDouble(bruttoPrice_str);
		Double spesen = Double.parseDouble(spesen_str);
		Double steuer = roundDouble_giveBack_Double((spesen/100)*20);
		Double nettoPrice = bruttoPrice - spesen - steuer;
		
		paint4Prices(nettoPrice, bruttoPrice, spesen, steuer);
	}
	

	
	
	public void netto_to_paintAllRabatt(String percent, String price, String rabattPercentarge) {
		Double nettoPrice = Double.parseDouble(price);
		calculateAndPrintRabattPrices(Integer.parseInt(percent), nettoPrice, rabattPercentarge);
	}
	
	public void brutto_to_paintAllRabatt(String percent, String price, String rabattPercentarge) {
		Double rabattPercent = Double.parseDouble(rabattPercentarge);
		Double bruttoPrice = Double.parseDouble(price);
		
		double newBruttoRabattPrice = MainModel.roundDouble_giveBack_Double((bruttoPrice / 100) * rabattPercent);
		double newNettoRabattPrice = (calculateNetto("20", newBruttoRabattPrice));
		double tax = newBruttoRabattPrice - newNettoRabattPrice;
		paint3Prices(newNettoRabattPrice, newBruttoRabattPrice, tax);
	}
	
	
	
	private void calculateAndPrintRabattPrices(int percent, Double nettoPrice, String rabattPercentarge) {
		Double rabattPercent = Double.parseDouble(rabattPercentarge);
		
		Double newNettoRabattPrice = MainModel.roundDouble_giveBack_Double((nettoPrice/100)*rabattPercent);
		Double tax = MainModel.roundDouble_giveBack_Double((newNettoRabattPrice/100)*percent);
		
		Double newBruttoRabattPrice = newNettoRabattPrice + tax;
		
		paint3Prices(newNettoRabattPrice, newBruttoRabattPrice, tax);
	}
	
	
	
	public JPanel paint2Konten_mitzyk(String konto4, String konto5) {
		JPanel jp = MainView.createJPanel();
		
		jp.add(MainView.addBasicToPanel(konto4, View_SuperClass.kontenLeftWP, View_SuperClass_2Outputs.yZKWP, 100, 100));
		jp.add(MainView.addBasicToPanel(konto5, View_SuperClass.kontenRightWP, View_SuperClass_2Outputs.yZKWP, 100, 100));
		
		if(View_SuperClass.pricesLeftWP == 350)
			MainView.hmPanelToCode.put(MainView.llJPanel.getLast(), "#-2*" + konto4 + "/" + konto5);
		
		else
			MainView.hmPanelToCode.put(MainView.llJPanel.getLast(), "#-2*" + konto5 + "/" + konto4);
		
		return jp;
	}
	
	
	public JPanel paint2Konten(String konto1, String konto2) {
		JPanel jp = MainView.createJPanel();
		
		jp.add(MainView.addBasicToPanel(konto1, View_SuperClass.kontenLeftWP, MainView.margin+10, 100, 100));
		jp.add(MainView.addBasicToPanel(konto2, View_SuperClass.kontenRightWP, MainView.margin+10, 100, 100));
		
		if(View_SuperClass.pricesLeftWP == 350)
			MainView.hmPanelToCode.put(jp, "#-2*" + konto1 + "/" + konto2);
		
		else
			MainView.hmPanelToCode.put(jp, "#-2*" + konto2 + "/" + konto1);

		return jp;
	}
	
	
	public JPanel paint3Konten(String konto1, String konto2, String konto3) {
		JPanel jp = MainView.createJPanel();
		
		jp.add(MainView.addBasicToPanel(konto1, View_SuperClass.kontenLeftWP, MainView.margin, 100, 100));
		jp.add(MainView.addBasicToPanel(konto2, View_SuperClass.kontenRightWP, MainView.margin, 100, 100));
		jp.add(MainView.addBasicToPanel(konto3, View_SuperClass.kontenLeftWP, MainView.margin+20, 100, 100));
		
		if(View_SuperClass.pricesLeftWP == 350)
			MainView.hmPanelToCode.put(jp, "#-3*" + konto1 + "/" + konto2 + "/" + konto3);

		else
			MainView.hmPanelToCode.put(jp, "#+3*" + konto2 + "/" + konto1 + "/" + konto3);

		return jp;
	}
	
	public void paint4Konten(String konto1, String konto2, String konto3, String konto4) {
		JPanel jp = MainView.createJPanel();
		
		jp.add(MainView.addBasicToPanel(konto1, View_SuperClass.kontenLeftWP, MainView.margin, 100, 100));
		jp.add(MainView.addBasicToPanel(konto2, View_SuperClass.kontenRightWP, MainView.margin, 100, 100));
		jp.add(MainView.addBasicToPanel(konto3, View_SuperClass.kontenLeftWP, MainView.margin+20, 100, 100));
		jp.add(MainView.addBasicToPanel(konto4, View_SuperClass.kontenLeftWP, MainView.margin+40, 100, 100));
		
		MainView.hmPanelToCode.put(jp, "#-4*" + konto1 + "/" + konto2 + "/" + konto3 + "/" + konto4);

	}
	
	
	//----------Skonto
	
	public void createKontos1stufig_Skonto(String kontoLinks, String kontoRechts) {
		JPanel jp = MainView.createJPanel();
		
		jp.add(MainView.addBasicToPanel(kontoLinks, View_SuperClass.kontenLeftWP, MainView.margin+10, 100, 100));
		jp.add(MainView.addBasicToPanel(kontoRechts, View_SuperClass.kontenRightWP, MainView.margin+10, 100, 100));
		
		if(View_SuperClass.pricesLeftWP == 350) {
			MainModel.contentForTxt.addLast("\r\n\r\n\r\n\r\n" + kontoLinks + "    " + kontoRechts);
			MainView.hmPanelToCode.put(jp, "#-2*" + kontoLinks + "/" + kontoRechts);
		}
		else {
			MainModel.contentForTxt.addLast("\r\n\r\n\r\n\r\n" + kontoRechts + "    " + kontoLinks);
			MainView.hmPanelToCode.put(jp, "#-2*" + kontoRechts + "/" + kontoLinks);
		}
	}
	
	
	
	
	public String netto_to_newBrutto_Skonto(String price, String skPercent) {
		double nettoPrice = Double.parseDouble(price);
		double bruttoPrice = MainModel.roundDouble_giveBack_Double(calculateBrutto("20", nettoPrice));
		double skontoPercent = Double.parseDouble(skPercent);
		double skontoBruttoPrice = MainModel.roundDouble_giveBack_Double((bruttoPrice/100)*skontoPercent);
		bruttoPrice = bruttoPrice - skontoBruttoPrice;
		
		paint1Price(bruttoPrice);
		return Double.toString(skontoBruttoPrice);
	}
	
	public String brutto_to_newBrutto_Skonto(String price, String skPercent) {
		double bruttoPrice = Double.parseDouble(price);
		double skontoPercent = Double.parseDouble(skPercent);
		double skontoBruttoPrice = MainModel.roundDouble_giveBack_Double((bruttoPrice/100)*skontoPercent);
		bruttoPrice = bruttoPrice - skontoBruttoPrice;
		
		paint1Price(bruttoPrice);
		return Double.toString(skontoBruttoPrice);
	}
	
	public String brutto_to_skBrutto_Skonto(String price, String skPercent) {
		double bruttoPrice = Double.parseDouble(price);
		double skontoPercent = Double.parseDouble(skPercent);
		double skontoBruttoPrice = (bruttoPrice/100)*skontoPercent;
		
		paint1Price(skontoBruttoPrice);
		return Double.toString(skontoBruttoPrice);
	}
	
	
	
	
//------------------------------------------------Personalverrechnung-------------------------------------------------------------------------
	
	public Double calcGehaltsPercent_andPrintIt(String ausgangsPreis_str, String percent_str) {
		Double ausgangsPreis = Double.parseDouble(ausgangsPreis_str);
		Double percent = Double.parseDouble(percent_str);
		
		Double res = (ausgangsPreis/100)*percent;
		
		paint1Price(res);
		return res;
	}
	
	
	public static String calcTagesgeld(String anzTage_str, String startZeit, String endZeit, String essen) {
		
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
		System.out.println(newPart);
		Double resTG = ((anzTage*Tagesgeld_View.tagesGeld) + (Tagesgeld_View.tagesGeld*(++newPart/12))) - (Tagesgeld_View.essensGeld * Integer.parseInt(essen));
		return round(resTG);
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
		
		
		if(command.equals("erste zwei Jahre"))
			addAfAToPanel(0, 1, anlKonto);
		
		else if(command.equals("letzte zwei Jahre"))
			addAfAToPanel(llAfaPrice.size()-2, llAfaPrice.size()-1, anlKonto);
		
		else if(command.equals("alle Jahre"))
			addAfAToPanel(0, llAfaPrice.size()-1, anlKonto);
		
		else if(command.equals("erstes Jahr"))
			addAfAToPanel(0, 0, anlKonto);
		
		llAfaPrice.removeAll(llAfaPrice);
		llYear.removeAll(llYear);
		
		return afaBetrag;
	}
	
	private static String getDate() {
		int year = Integer.parseInt(tempYear);
		tempYear = Integer.toString(++year);
		return tempYear;
	}
	
	private void addAfAToPanel(int from, int to, String konto) {
		int y = 1;
		for(int x = from; x <= to; x++) {
			JPanel tempPanel = paint2Konten("7010", konto);
			paint1Price(llAfaPrice.get(x));
			MainView.addNoteToPanel("Datum:     " + "31." + "12" + "." + llYear.get(x), tempPanel, 375);
		}
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
	
	// callen, wenn man die encodierte Form der BS auf dem workpanel braucht
	public static String sortHashMapPanelToCodeandGetCode() {
		String code = "";
		ArrayList<String> tempAL = new ArrayList<String>();
		
		for(int x = 0; x < MainView.llJPanel.size(); x++) {
			tempAL.add("");
		}
		
		for(int y = 0; y < MainView.llJPanel.size(); y++) {
			JPanel panel = MainView.llJPanel.get(y);
			tempAL.set(Integer.parseInt(((JTextField) panel.getComponent(0)).getText()) - 1, MainView.hmPanelToCode.get(panel));
		}
		
		for(int z = 0; z < tempAL.size(); z++) {
			code += tempAL.get(z);
		}

		return code;
		
	}
	
	
	
	
//----------------------------------deleteBS------------------------------------------------------------------------	

	public static void deleteChecked(boolean alsoNettoPrices) {
		try {
			MainView.numOfPanels = 1;
			MainView.jpMargin = 10;
			int x = 0;
			while(x < MainView.llRadioButton.size()) {
				if(MainView.llRadioButton.get(x).isSelected()) {
					MainView.workPanel.remove(x);
					MainView.llRadioButton.remove(x);
					if(alsoNettoPrices)
						MainView.llNettoPrices.remove(x);
					MainView.hmPanelToCode.remove(MainView.llJPanel.get(x));
					MainView.llJPanel.remove(x);
					x = 0;
				}//if
				else
					x++;
			}//while
			
			MainView.workPanel.removeAll();
			for(int z = 0; z < MainView.llJPanel.size(); z++) {
				JPanel jp = MainView.llJPanel.get(z);
				jp.setBounds(5, MainView.jpMargin, 700, 150);
				MainView.workPanel.add(jp);
				MainView.jpMargin = MainView.jpMargin + 170;
			}//for
			
			MainView.workPanel.repaint();
			
			MainView.paintNumberOnNumberLabel();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Bitte starten Sie das Programm erneut! Es ist ein Fehler aufgetreten.\n Gehen Sie sicher, dass Sie legitime Eingaben tätigen!", "Warning", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
	}//method
	
	
	
	public static void deleteAll() {
		MainView.numOfPanels = 1;
		MainView.jpMargin = 10;
		
		MainView.workPanel.removeAll();
		MainView.llRadioButton.removeAll(MainView.llRadioButton);
		MainView.llNettoPrices.removeAll(MainView.llNettoPrices);
		MainModel.contentForTxt.removeAll(MainModel.contentForTxt);
		for(int x = 0; x < MainView.llJPanel.size(); x++) 
			MainView.hmPanelToCode.remove(MainView.llJPanel.get(x));
		MainView.llJPanel.removeAll(MainView.llJPanel);
		
		MainView.workPanel.repaint();
		
		
	}
	
	
	
	
	
	
	
	
	
	
//----------------------------------openFiles------------------------------------------------------------------------
	
	
	public LinkedList<Character> openFile(File file) {
		
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
	
	
	public void openFile(LinkedList<Character> char_LL) {
		llChar = char_LL;
		prepareCollection(0);
		
	}
	
	public void prepareCollection(int index) {
		

		
		boolean leftMore = false;
		
		if(index == -1)
			return;
		
		else {
			
			LinkedList<String> llKontos = new LinkedList<String>();
			LinkedList<String> llPrices = new LinkedList<String>();
			
			char currentChar = llChar.get(index);
			int kontoCount = 0;
			
			if(currentChar == '#') {
				currentChar = llChar.get(++index);
				
				if(currentChar == '-')
					leftMore = true;
				
				currentChar = llChar.get(++index);
				
				kontoCount = Integer.parseInt(Character.toString(currentChar));
				
				index = collectKontos_Prices(llPrices, collectKontos_Prices(llKontos, ++index));

				printToWorkPanel_putIntoTxtList(kontoCount, llKontos, llPrices, leftMore);


				llKontos = null;
				llPrices = null;
				
				prepareCollection(index);
			}
			
		}
	}
	
	
	private void printToWorkPanel_putIntoTxtList(int kontoCount, LinkedList<String> llKontos, LinkedList<String> llPrices, boolean leftMore) {
		
		String firstRowMargin = "                ";
		String secondRowMargin = "                        ";
		
		if(kontoCount == 2) { //einstufig
			if(!saving) { //öffnen
				paint2Konten(llKontos.get(0), llKontos.get(1));
				paint1Price(Double.parseDouble(llPrices.get(0)));
			}
			
			else //speichern
				MainModel.contentForTxt.addLast("\r\n\r\n\r\n\r\n" + llKontos.get(0) + "    " + llKontos.get(1) + firstRowMargin + round(Double.parseDouble(llPrices.get(0))));
		}
		
		
		
		if(kontoCount > 2) { //mehrstufig
			if(!saving) {
				if(kontoCount < 5)
					paintUpTo7(llKontos, llPrices, leftMore, false);
				else
					paintUpTo7(llKontos, llPrices, leftMore, true);
			}
			
			else {
				if(leftMore) {
					String output = "\r\n\r\n\r\n\r\n" + llKontos.get(0) + "    " + llKontos.get(1) + firstRowMargin + round(Double.parseDouble(llPrices.get(0))) + "    " + round(Double.parseDouble(llPrices.get(1)));
					for(int x = 2; x < llKontos.size(); x++) {
						output += "\r\n" + llKontos.get(x) + secondRowMargin + round(Double.parseDouble(llPrices.get(x)));
					}
					
					MainModel.contentForTxt.addLast(output);
				}
				else {
					String margin = "";
					for(int x = 0; x < round(Double.parseDouble(llPrices.get(1))).length(); x++) {
						margin += " ";
					}
					
					String output = "\r\n\r\n\r\n\r\n" + llKontos.get(0) + "    " + llKontos.get(1) + firstRowMargin + round(Double.parseDouble(llPrices.get(0))) + "    " + round(Double.parseDouble(llPrices.get(1)));
					for(int x = 2; x < llKontos.size(); x++) {
						output += "\r\n        " + llKontos.get(x) + firstRowMargin + margin + "    " + round(Double.parseDouble(llPrices.get(x)));
					}
					
					MainModel.contentForTxt.addLast(output);
				}
					
			}
		}
		
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
	
	
	
	
	
	public void saveAstxt() {

		String filename = save("disCount-Dateien", "dc", "disCount");
		
		try{
			MainModel.contentForTxt.removeAll(MainModel.contentForTxt);
			MainModel.saving = true;
			PrintWriter writer = new PrintWriter(filename + ".txt", "UTF-8");
			PrintWriter writer_system = new PrintWriter(filename + ".dc", "UTF-8");
			
			LinkedList<Character> llChar = new LinkedList<Character>();
			
			
			String code = MainModel.sortHashMapPanelToCodeandGetCode();
			
			for(int y = 0; y < code.length(); y++) {
				llChar.addLast(code.charAt(y));
			}
			
			System.out.println(llChar + "    before openfile");
			openFile(llChar);
			
			MainModel.contentForTxt.addFirst(filename + "- disCount");
			
			for(int i = 0; i < MainModel.contentForTxt.size(); i++) {
				 writer.print(MainModel.contentForTxt.get(i));
			}
			writer.close();
			MainModel.saving = false;
			
			writer_system.printf(code);

			writer_system.close();
		   
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public String save(String filetype, String prefix, String desc) {
		
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
	
	
	
	
	
	
	
	
	
	public LinkedList<Character> open() {
		
		 File file = null;
			try {	
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("disCount-Dateien", "dc", "disCount");
			fc.setFileFilter(filter);
			
			 int returnVal = fc.showOpenDialog(MainView.workPanel);
			 file = fc.getSelectedFile();
			}catch(Exception e) {
				System.out.println("CLOSED");
			}
			
			return openFile(file);
	}
	
	
	public String convertLL_CharToString(LinkedList<Character> llChar) {
		String str = "";
		for(int x = 0; x < llChar.size(); x++) {
			str = str + llChar.get(x);
		}
		
		return str;
	}


	public LinkedList<Character> convertStringToLL_Char(String code) {
		
		LinkedList<Character> llChar = new LinkedList<Character>();
		
		for(int x = 0; x < code.length(); x++) {
			llChar.addLast(code.charAt(x));
		}
		
		return llChar;
	}
	
	
	public LinkedList<String> convertTFListToStringList(LinkedList<JTextField> tfList) {
		LinkedList<String> strList = new LinkedList<String>();
		
		for(int x = 0; x < tfList.size(); x++) {
			strList.add(tfList.get(x).getText());
		}
		
		return strList;
	}
	
	
	
	
	
	
	
	
	
	
	

	
}
	

