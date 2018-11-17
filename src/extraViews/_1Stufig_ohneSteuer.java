package extraViews;

public class _1Stufig_ohneSteuer extends _1Stufig_Fixed_View{

	@Override
	public void build(String Konto1, String[] Konten2, String Konto3, boolean fixed) {
		makeKonto1(Konto1);
		
		if(fixed == true)
			makeKonto2Fixed(Konten2[0]);
		else
			makeKonto2Variable(Konten2);
		makePrice();
		netto = null;
	}
	
}
