package Kalkulationen;

import View.MainModel;

public class Kalkulation_Model {
	
	
	
	private double PE = 0;
	private double SV;
	private double gewerkschaftsBeitrag = 0;
	private double Akonto = 0;
	private double eCard = 0;
	
	
//---------------------------Bezugsspesenkalkulation-----------------------------------------------------------------------
	
	
public void calcBezugsspesenKalkulation_Pro(Bezugskalkulation_View view, String rechnP_str, String rabattPercent_str, String faktSp_str, String skontoPercent_str, String eigBezSp_str)  {
		
		MainModel.printErrorMessages = true;
	
		Double rechnPr = MainModel.parseDouble(rechnP_str);
		view.txtRechnungspreis.setText(MainModel.roundDouble_giveBack_String(rechnPr));
		
		Double rabattPercent = MainModel.parseDouble(rabattPercent_str);
		Double faktSp = MainModel.parseDouble(faktSp_str);
		view.txtFakturenspesen.setText(MainModel.roundDouble_giveBack_String(faktSp));
		
		Double skontoPercent = MainModel.parseDouble(skontoPercent_str);
		Double eigBezSp = MainModel.parseDouble(eigBezSp_str);
		view.txtEigeneBezugsspesen.setText(MainModel.roundDouble_giveBack_String(eigBezSp));
		
		
		Double tempPrice = MainModel.roundDouble_giveBack_Double((rechnPr/100)*rabattPercent); //rabattPreis
		view.txtRabatt.setText(Double.toString(tempPrice));
		
		tempPrice = MainModel.roundDouble_giveBack_Double(rechnPr - tempPrice); //rabattierter Preis
		view.txtRabattierterPreis.setText(Double.toString(tempPrice));
		
		tempPrice = MainModel.roundDouble_giveBack_Double(tempPrice + faktSp); //Zielpreis
		view.txtZielpreis.setText(Double.toString((tempPrice)));
		
		Double skontoBetrag = MainModel.roundDouble_giveBack_Double((tempPrice/100) * skontoPercent); //skontoPreis
		view.txtSkonto.setText(Double.toString((skontoBetrag)));
		
		tempPrice = MainModel.roundDouble_giveBack_Double(tempPrice - skontoBetrag); //Kassapreis
		view.txtKassapreis.setText(Double.toString((tempPrice)));
		
		tempPrice = MainModel.roundDouble_giveBack_Double(tempPrice + eigBezSp); //Einsatndspreis
		view.txtEinstandspreis.setText(Double.toString((tempPrice)));
		
	}
	
	
	
	public void calcBezugsspesenKalkulation_Ret(Bezugskalkulation_View view, String einstPreis_str, String rabattPercent_str, String faktSp_str, String skontoPercent_str, String eigBezSp_str)  {
		
		MainModel.printErrorMessages = true;
		
		Double einstPreis = MainModel.parseDouble(einstPreis_str);
		view.txtEinstandspreis.setText(MainModel.roundDouble_giveBack_String(einstPreis));
		
		Double rabattPercent = MainModel.parseDouble(rabattPercent_str);
		Double faktSp = MainModel.parseDouble(faktSp_str);
		view.txtFakturenspesen.setText(MainModel.roundDouble_giveBack_String(faktSp));
		
		Double skontoPercent = MainModel.parseDouble(skontoPercent_str);
		Double eigBezSp = MainModel.parseDouble(eigBezSp_str);
		view.txtEigeneBezugsspesen.setText(MainModel.roundDouble_giveBack_String(eigBezSp));
		
		
		Double tempPrice = MainModel.roundDouble_giveBack_Double(einstPreis -  eigBezSp);
		view.txtKassapreis.setText(Double.toString(tempPrice));
		
		Double skontoBetrag = MainModel.roundDouble_giveBack_Double((tempPrice/(100-skontoPercent))*skontoPercent); //skontoPreis
		view.txtSkonto.setText(Double.toString(skontoBetrag));
		
		tempPrice = MainModel.roundDouble_giveBack_Double(tempPrice + skontoBetrag); //Zielpreis
		view.txtZielpreis.setText(Double.toString(tempPrice));
		
		tempPrice = MainModel.roundDouble_giveBack_Double(tempPrice - faktSp); //rabattierter Preis
		view.txtRabattierterPreis.setText(Double.toString(tempPrice));
		
		Double rabattBetrag = MainModel.roundDouble_giveBack_Double((tempPrice/(100-rabattPercent))*rabattPercent); //rabattPrice
		view.txtRabatt.setText(Double.toString(rabattBetrag));
		
		tempPrice = MainModel.roundDouble_giveBack_Double(tempPrice + rabattBetrag); //Rechnungspreis
		view.txtRechnungspreis.setText(Double.toString(tempPrice));
		
	}
	
	
	
	
	
	
	
//---------------------------Lohnverrechnung-----------------------------------------------------------------------
	
	
	
	
	public Double calcSV_LS(Double gehalt_lohn, Double fahrtstrecke, Double freibetrag, Double eCardGeb¸hr, Double gewerkschaftsbeitrag, Double akonto, boolean zumutbar) {
		gewerkschaftsBeitrag = gewerkschaftsbeitrag;
		Akonto = akonto;
		eCard = eCardGeb¸hr;
		
		SV = MainModel.roundDouble_giveBack_Double(calcSV(gehalt_lohn));
		Double BG = MainModel.roundDouble_giveBack_Double(calcLSBGL(gehalt_lohn, SV, freibetrag, fahrtstrecke, zumutbar, eCardGeb¸hr, gewerkschaftsbeitrag));
		System.out.println(SV);
		System.out.println(BG);
		return BG;
		
		
	}
	
	private Double calcSV(Double gehalt_lohn) {
		double SV = 0;
		
		if(gehalt_lohn <= Personalverrechnung_View.txtGeringf¸gigkeitsgrenze)
			SV = (Personalverrechnung_View.txtGeringf¸gigkeitsgrenze/100) * Personalverrechnung_View.txtCurrentSV1;
		
		else if(gehalt_lohn >= Personalverrechnung_View.txtHˆchstbeitragsgrundlage)
			SV = (Personalverrechnung_View.txtHˆchstbeitragsgrundlage/100) * Personalverrechnung_View.txtCurrentSV4;
		
		else if(gehalt_lohn > 1609)
			SV = (gehalt_lohn/100) * Personalverrechnung_View.txtCurrentSV4;
		
		else if(gehalt_lohn >  1430)
			SV = (gehalt_lohn/100) * Personalverrechnung_View.txtCurrentSV3;
		
		else if(gehalt_lohn >   1311)
			SV = (gehalt_lohn/100) * Personalverrechnung_View.txtCurrentSV2;
		
		else
			SV = (gehalt_lohn/100) * Personalverrechnung_View.txtCurrentSV1;
		
		return MainModel.roundDouble_giveBack_Double(SV);
	}
	
	
	
	private Double calcLSBGL(Double gehalt_lohn, Double SV, Double freibetrag, Double fahrtstrecke, boolean zumutbar, Double eCardGeb¸hr, Double gewerkschaftsbeitrag) {
		Double PP = calcPP_PE(fahrtstrecke, zumutbar);
		
		return gehalt_lohn - SV - freibetrag - PP - eCardGeb¸hr - gewerkschaftsbeitrag;
	}
	
	
	
	private Double calcPP_PE(Double fahrtstrecke, boolean zumutbar) {
		if(fahrtstrecke != 0) {//wenn f¸r Fahrtstrecke etwas eingesetzt wurde
			
			PE = (fahrtstrecke*2)/12;
			
			if(zumutbar) {
				if(fahrtstrecke > 60)
					return Personalverrechnung_View.textField_mehrals60KPP;
				else if(fahrtstrecke > 40)
					return Personalverrechnung_View.txtmehrals40KPP;
				else
					return Personalverrechnung_View.txtmind20KPP;	
			}
			
			else {//unzumutbar
				if(fahrtstrecke > 60)
					return Personalverrechnung_View.textField_mehrals60GPP;
				else if(fahrtstrecke > 40)
					return Personalverrechnung_View.txtmehrals40GPP;
				else if(fahrtstrecke > 20)
					return Personalverrechnung_View.txtmind20GPP;
				else
					return (double) 31;
			}
					
		}
		
		else
			return (double) 0;
	}
	
	public Double calcLS(Double BG, String Gss_str, String AVAB_str) {
		Double Gss = MainModel.parseDouble(Gss_str);
		Double AVAB = MainModel.parseDouble(AVAB_str);
		
		Double LS = MainModel.roundDouble_giveBack_Double((BG/100)*Gss);
		return LS - AVAB - PE;
	}
	
	public Double calcNewGehalt(Double LS, String gehalt_lohn_str) {
		Double gehalt_lohn = MainModel.parseDouble(gehalt_lohn_str);
		
		return gehalt_lohn - SV - LS - gewerkschaftsBeitrag - Akonto - eCard;
	}
	
	public Double getSV() {
		return SV;
	}
	
	
	
	
	
	
	
	
	
	
	
//---------------------------Absatzkalkulation-----------------------------------------------------------------------
	
	

	
	public void calcAbsatzkalkulation_Pro(Absatzkalkulation_View view, String selbstK_str, String gewinn_str, String nomSK_str, String verk_Provision_str, String skonto_str, String mengenr_str, String sonderr_str, String groﬂr_str, String einzelr_str) {
	
		MainModel.printErrorMessages = true;
		
		Double selbstK = MainModel.parseDouble(selbstK_str);
		view.txtSelbstkosten.setText(MainModel.roundDouble_giveBack_String(selbstK));
		
		Double gewinnPreis = MainModel.roundDouble_giveBack_Double((selbstK / 100) * MainModel.parseDouble(gewinn_str));
		view.txtGewinn.setText(Double.toString(gewinnPreis));
		
		Double nettoVP = MainModel.roundDouble_giveBack_Double(selbstK + gewinnPreis);
		view.txtNettoverkaufspreis.setText(Double.toString(nettoVP));
		
		Double nomSK = MainModel.parseDouble(nomSK_str);
		view.txtNomSK.setText(Double.toString(nomSK));
		System.out.println(nomSK);
		
		Double ZWS = MainModel.roundDouble_giveBack_Double(nettoVP + nomSK);
		view.txtZwischensumme.setText(Double.toString(ZWS));
		
		Double verk_Provision = MainModel.roundDouble_giveBack_Double((ZWS / (100 - MainModel.parseDouble(verk_Provision_str))) * MainModel.parseDouble(verk_Provision_str));
		view.txtVerkaufsprovision.setText(Double.toString(verk_Provision));
		
		Double kassaPreis = MainModel.roundDouble_giveBack_Double(ZWS + verk_Provision);
		view.txtKassapreis.setText(Double.toString(kassaPreis));
		
		Double skonto = MainModel.roundDouble_giveBack_Double((kassaPreis / (100 - MainModel.parseDouble(skonto_str)) * MainModel.parseDouble(skonto_str)));
		view.txtSkonto.setText(Double.toString(skonto));
		
		Double zielPreis = MainModel.roundDouble_giveBack_Double(kassaPreis + skonto);
		view.txtZielpreis.setText(Double.toString(zielPreis));
		
		
		if(mengenr_str != null) {
			Double mengenRabatt = MainModel.roundDouble_giveBack_Double((zielPreis / (100 - MainModel.parseDouble(mengenr_str)) * MainModel.parseDouble(mengenr_str)));
			view.txtMengenRabatt.setText(Double.toString(mengenRabatt));
			zielPreis = zielPreis + mengenRabatt;
		}
		else
			view.txtMengenRabatt.setText("0");
		
		
		if(sonderr_str != "0") {
			Double sonderRabatt = MainModel.roundDouble_giveBack_Double((zielPreis / (100 - MainModel.parseDouble(sonderr_str)) * MainModel.parseDouble(sonderr_str)));
			view.txtSonderRabatt.setText(Double.toString(sonderRabatt));
			zielPreis = zielPreis + sonderRabatt;
		}
		else
			view.txtSonderRabatt.setText("0");
		
		
		
		if(groﬂr_str != "0") {
			Double groﬂhandelsRabatt = MainModel.roundDouble_giveBack_Double((zielPreis / (100 - MainModel.parseDouble(groﬂr_str)) * MainModel.parseDouble(groﬂr_str)));
			view.txtGroﬂhandelsRabatt.setText(Double.toString(groﬂhandelsRabatt));
			zielPreis = zielPreis + groﬂhandelsRabatt;
		}
		else
			view.txtGroﬂhandelsRabatt.setText("0");
		
		
		if(einzelr_str != "0") {
			Double einzelhandelsRabatt = MainModel.roundDouble_giveBack_Double((zielPreis / (100 - MainModel.parseDouble(einzelr_str)) * MainModel.parseDouble(einzelr_str)));
			view.txtEinzelhandelsRabatt.setText(Double.toString(einzelhandelsRabatt));
			zielPreis = zielPreis + einzelhandelsRabatt;
		}
		else
			view.txtEinzelhandelsRabatt.setText("0");
		
		double tempVal = MainModel.roundDouble_giveBack_Double(MainModel.parseDouble(view.txtMengenRabatt.getText()) + MainModel.parseDouble(view.txtSonderRabatt.getText()) + MainModel.parseDouble(view.txtGroﬂhandelsRabatt.getText()) + MainModel.parseDouble(view.txtEinzelhandelsRabatt.getText()));
		Double bruttoVPexkl = MainModel.roundDouble_giveBack_Double(tempVal + MainModel.parseDouble(view.txtZielpreis.getText()));
		view.txtBruttoVPexkl.setText(Double.toString(bruttoVPexkl));
		
		Double USt = MainModel.roundDouble_giveBack_Double((bruttoVPexkl / 100) * 20);
		view.txtUSt.setText(Double.toString(USt));
		
		Double bruttoVPinkl = MainModel.roundDouble_giveBack_Double(bruttoVPexkl + USt);
		view.txtBruttoVP.setText(Double.toString(bruttoVPinkl));
		
	}
	
	
	public void calcAbsatzkalkulation_Ret(Absatzkalkulation_View view, String bruttoVP_inkl_str, String gewinn_str, String nomSK_str, String verk_Provision_str, String skonto_str, String mengenr_str, String sonderr_str, String groﬂr_str, String einzelr_str) {
	
		MainModel.printErrorMessages = true;
		
		Double bruttoVPinkl = MainModel.parseDouble(bruttoVP_inkl_str);
		view.txtBruttoVP.setText(MainModel.roundDouble_giveBack_String(bruttoVPinkl));
		
		Double USt = MainModel.roundDouble_giveBack_Double((bruttoVPinkl / 120) * 20);
		view.txtUSt.setText(Double.toString(USt));
		
		Double bruttoVPexkl = MainModel.roundDouble_giveBack_Double(bruttoVPinkl - USt);
		view.txtBruttoVPexkl.setText(Double.toString(bruttoVPexkl));
		
		
		if(einzelr_str != "0") {
			Double einzelhandelsRabatt = MainModel.roundDouble_giveBack_Double((bruttoVPexkl / 100) * MainModel.parseDouble(einzelr_str));
			view.txtEinzelhandelsRabatt.setText(Double.toString(einzelhandelsRabatt));
			bruttoVPexkl = bruttoVPexkl - einzelhandelsRabatt;
		}
		else
			view.txtEinzelhandelsRabatt.setText("0");
		
		
		if(groﬂr_str != "0") {
			Double groﬂhandelsRabatt = MainModel.roundDouble_giveBack_Double((bruttoVPexkl / 100) * MainModel.parseDouble(groﬂr_str));
			view.txtGroﬂhandelsRabatt.setText(Double.toString(groﬂhandelsRabatt));
			bruttoVPexkl = bruttoVPexkl - groﬂhandelsRabatt;
		}
		else
			view.txtGroﬂhandelsRabatt.setText("0");
		
		if(sonderr_str != "0") {
			Double sonderRabatt = MainModel.roundDouble_giveBack_Double((bruttoVPexkl / 100) * MainModel.parseDouble(sonderr_str));
			view.txtSonderRabatt.setText(Double.toString(sonderRabatt));
			bruttoVPexkl = bruttoVPexkl - sonderRabatt;
		}
		else
			view.txtSonderRabatt.setText("0");
		
		
		if(mengenr_str != null) {
			Double mengenRabatt = MainModel.roundDouble_giveBack_Double((bruttoVPexkl / (100 - MainModel.parseDouble(mengenr_str)) * MainModel.parseDouble(mengenr_str)));
			view.txtMengenRabatt.setText(Double.toString(mengenRabatt));
			bruttoVPexkl = bruttoVPexkl - mengenRabatt;
		}
		else
			view.txtMengenRabatt.setText("0");
		
		
		double tempVal = MainModel.roundDouble_giveBack_Double(MainModel.parseDouble(view.txtMengenRabatt.getText()) + MainModel.parseDouble(view.txtSonderRabatt.getText()) + MainModel.parseDouble(view.txtGroﬂhandelsRabatt.getText()) + MainModel.parseDouble(view.txtEinzelhandelsRabatt.getText()));
		
		Double zielPreis = MainModel.roundDouble_giveBack_Double(MainModel.parseDouble(view.txtBruttoVPexkl.getText()) - tempVal);
		view.txtZielpreis.setText(Double.toString(zielPreis));
		
		Double skonto = MainModel.roundDouble_giveBack_Double((zielPreis / 100) * MainModel.parseDouble(skonto_str));
		view.txtSkonto.setText(Double.toString(skonto));
		
		Double kassaPreis = MainModel.roundDouble_giveBack_Double(zielPreis - skonto);
		view.txtKassapreis.setText(Double.toString(kassaPreis));
		
		Double verkaufsProv = MainModel.roundDouble_giveBack_Double((kassaPreis / 100) * MainModel.parseDouble(verk_Provision_str));
		view.txtVerkaufsprovision.setText(Double.toString(verkaufsProv));
		
		Double ZWS = MainModel.roundDouble_giveBack_Double(kassaPreis - verkaufsProv);
		view.txtZwischensumme.setText(Double.toString(ZWS));
		
		Double nomSK = (double) 0;
		view.txtNomSK.setText("0");
		
		if(!(nomSK_str.equals("0"))) {
			nomSK = MainModel.roundDouble_giveBack_Double(MainModel.parseDouble(nomSK_str));
			view.txtNomSK.setText(Double.toString(nomSK));
		}
		
		Double nettoVP = ZWS - nomSK;
		view.txtNettoverkaufspreis.setText(Double.toString(nettoVP));
		
		Double gewinn = MainModel.roundDouble_giveBack_Double((nettoVP / (100 + MainModel.parseDouble(gewinn_str))) * MainModel.parseDouble(gewinn_str));
		view.txtGewinn.setText(Double.toString(gewinn));
		
		Double SK = nettoVP - gewinn;
		view.txtSelbstkosten.setText(Double.toString(SK));
	}

}
