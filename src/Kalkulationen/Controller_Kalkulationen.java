package Kalkulationen;

import View.Controller_AbstractClass;
import View.MainController;

public class Controller_Kalkulationen extends Controller_AbstractClass{
	
	Kalkulation_Model myModel;

	public Controller_Kalkulationen(MainController MC) {
		super(MC);
		myModel = new Kalkulation_Model();
		
	}
	
	public void exec_Bezugskalkulation_View() {
		Bezugskalkulation_View BEZKALK = new Bezugskalkulation_View(this);
		BEZKALK.setVisible(true);
	}
	
	public void exec_Personalverrechnung_View() {
		Personalverrechnung_View PersVER = new Personalverrechnung_View(this);
		PersVER.setVisible(true);
	}
	
	public void exec_Absatzkalkulation_View() {
		Absatzkalkulation_View ABSKALK = new Absatzkalkulation_View(this);
		ABSKALK.setVisible(true);
	}
	
	
	
	
	
	public void execCalcAbsatzkalkulation_Pro(Absatzkalkulation_View view, String selbstK_str, String gewinn_str, String nomSK_str, String verk_Provision_str, String skonto_str, String mengenr_str, String sonderr_str, String großr_str, String einzelr_str) {
		myModel.calcAbsatzkalkulation_Pro(view, selbstK_str, gewinn_str, nomSK_str, verk_Provision_str, skonto_str, mengenr_str, sonderr_str, großr_str, einzelr_str);
	}
	
	public void execCalcAbsatzkalkulation_Ret(Absatzkalkulation_View view, String bruttoVP_inkl_str, String gewinn_str, String nomSK_str, String verk_Provision_str, String skonto_str, String mengenr_str, String sonderr_str, String großr_str, String einzelr_str) {
		myModel.calcAbsatzkalkulation_Ret(view, bruttoVP_inkl_str, gewinn_str, nomSK_str, verk_Provision_str, skonto_str, mengenr_str, sonderr_str, großr_str, einzelr_str);
	}

	public void execCalcBezugsspesenKalkulation_Pro(Bezugskalkulation_View view, String rechnP_str, String rabattPercent_str, String faktSp_str, String skontoPercent_str, String eigBezSp_str) {
		myModel.calcBezugsspesenKalkulation_Pro(view, rechnP_str, rabattPercent_str, faktSp_str, skontoPercent_str, eigBezSp_str);
	}
	
	public void execCalcBezugsspesenKalkulation_Ret(Bezugskalkulation_View view, String einstPreis_str, String rabattPercent_str, String faktSp_str, String skontoPercent_str, String eigBezSp_str) {
		myModel.calcBezugsspesenKalkulation_Ret(view, einstPreis_str, rabattPercent_str, faktSp_str, skontoPercent_str, eigBezSp_str);
	}
	
	public Double execCalcSV_LS(Double gehalt_lohn, Double fahrtstrecke, Double freibetrag, Double eCardGebühr, Double gewerkschaftsbeitrag, Double akonto, boolean zumutbar) {
		return myModel.calcSV_LS(gehalt_lohn, fahrtstrecke, freibetrag, eCardGebühr, gewerkschaftsbeitrag, akonto, zumutbar);
	}
	
	public Double execCalcLS(Double BG, String Gss_str, String AVAB_str) {
		return myModel.calcLS(BG, Gss_str, AVAB_str);
	}
	
	public Double execCalcNewGehalt(Double LS, String gehalt_lohn_str) {
		return myModel.calcNewGehalt(LS, gehalt_lohn_str);
	}
	
	public Double execGetSV() {
		return myModel.getSV();
	}
	
	
	
	
	
	
	
	

}
