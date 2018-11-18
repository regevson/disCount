package disCount;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import extraViews.Setup_View;
import extraViews.SplashScreen_View;

public class Main {
	
	public static boolean alreadyDone = false;
	public static boolean execdisCount = true;
	
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
				    }
				    catch(InterruptedException ex) {
				      System.out.println(ex);
				    }
				 
				 splash_View.setVisible(false);
				 Setup_View setup_View = new Setup_View();
				 setup_View.startApp();
			}
			
			else {
				Setup_View setup_View = new Setup_View();
				setup_View.setVisible(true);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		 
		
		
	}

}
