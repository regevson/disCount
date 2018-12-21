package disCount;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import extraViews.RegistrationQuestion;
import extraViews.Setup_View;
import extraViews.SplashScreen_View;

public class Main {
	
	public static boolean alreadyDone = false;
	public static boolean execdisCount = true;
	public static boolean retireveLocalInfo = false;
	
	public static void main(String[] args) {
		
		
		try {

			BufferedReader br = new BufferedReader(new FileReader("src/setup.txt"));
			String setup = br.readLine();
			
			if(setup != null && setup.equals("alreadyDone")) {
				
				alreadyDone = true;
				SplashScreen_View splash_View = new SplashScreen_View();
				splash_View.setVisible(true);

				try {

					Thread.sleep(0);

				} catch(InterruptedException ex) {ex.printStackTrace();}

				splash_View.setVisible(false);
				Setup_View setup_View = new Setup_View();
				setup_View.startApp();

			}

			else {

				RegistrationQuestion regq = new RegistrationQuestion();
				regq.setVisible(true);

			}

		} catch(IOException e) {e.printStackTrace();}

	}

}
