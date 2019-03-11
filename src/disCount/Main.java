package disCount;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import View.MainView;
import extraViews.RegistrationQuestion;
import extraViews.Setup_View;
import extraViews.SplashScreen_View;
import extraViews.UpdateView;

public class Main {
	
	public static boolean alreadyDone = false;
	public static boolean execdisCount = true;
	public static boolean retireveLocalInfo = false;
	private static Setup_View setupView;
	private static Instant startTime;
	
	public static void main(String[] args) {
		
		
		try {

			startTime = Instant.now();
			
			BufferedReader br = new BufferedReader(new FileReader("src/setup.txt"));
			String setup = br.readLine();
			
			if(setup != null && setup.equals("alreadyDone"))
				opendisCoutRoutine();

			else {

				RegistrationQuestion regq = new RegistrationQuestion();
				regq.setVisible(true);
				setupView = regq.getSetupView();

			}
			
			shutDownRoutine();
			
			UpdateView uv = new UpdateView();
			uv.setVisible(false);

		} catch(IOException e) {e.printStackTrace();}

	}
	
	
	
	
	private static void opendisCoutRoutine() {
		
		alreadyDone = true;
		SplashScreen_View splash_View = new SplashScreen_View();
		splash_View.setVisible(true);

		try {

			Thread.sleep(1500);//1500

		} catch(InterruptedException ex) {ex.printStackTrace();}

		splash_View.setVisible(false);
		
		setupView = new Setup_View();
		setupView.startApp();
		
	}
	
	
	
	
	private static void shutDownRoutine() {
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	        	
	        	if(MainView.databaseIsActive)
	        		logOut();
	        	
	        	getUseTime();
	        	
	        }
	    }, "Shutdown-thread"));
		
	}
	
	
	private static void logOut() {
		setupView.getML_db().initLogOut();
	}
	
	private static void getUseTime() {

		Instant endTime = Instant.now();
		Duration timeElapsed = Duration.between(startTime, endTime);
		
		setupView.getML_db().initSetUseTime(timeElapsed.toMinutes());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
