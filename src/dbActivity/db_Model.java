package dbActivity;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import View.Buchungssatz;
import View.MainModel;
import View.MainView;
import disCount.Main;
import extraViews.MessageBox;

public class db_Model {
	
	
	
	
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	public LinkedList<Integer> ll_wrongSolutions = new LinkedList<Integer>();
	public String solutionID;
	private String user_name;
	private String codeInfo;
	public String name;
	private int upvotes;
	private int downvotes;
	private String commentID;
	private String email;
	private String schoolType;
	private Statement st2;
	public static boolean allowSolutions = false;
	public static final int MINIMUMADDED = 5;
	public static final int MAXSTUDENTS = 5;
	public static final int MAXBS = 5;
	private final int minLikesToBeVerified = 5;
	
	private int BScount;
	private int commentCount;
	private String comments;
	private String uploaderInfo;
	
	private int added = 0;
	public static double skill;
	private double dependence;
	private double community;
	private boolean alreadyPunished = false; // wurde dependence schon erhöht? -- Wird nämlich nur 1 Mal pro Session vermerkt
	private int pid;
	private int resultID;
	protected int scanStart = 1;
	
	private ArrayList<Integer> scanList;
	private String solution = "";
	private int myID;
	private int studentNumber; //tells you which column you are in exam-database
	private String myTier;
	private String uploaderTier;
	private boolean inputToSuggestionIsIncorrect;
	private String schoolClass;
	private String uploaderID;
	private ArrayList<Integer> examBSList;
	
	
	
	
	
	
	
	
	public db_Model(Connection conn) {
		this.conn = conn;
	}




	public boolean changeRatio(int value, String solutionID) {
		
		 try {
			 
			 rs = st.executeQuery("SELECT evaluated FROM users WHERE email='" + email + "'");
			
			 String evaluated = null;
			    
		     if(rs.next())
		        evaluated = rs.getString("evaluated");
		        
		    
		     if(!evaluated.contains("|" + solutionID + "|")) {
		    	 
		    	 evaluated = evaluated + "|" + solutionID + "|";
		    	 st.executeUpdate("UPDATE users SET evaluated='" + evaluated + "' WHERE email='" + email + "'");
		    	 
		    	 String votes_str = "upVotes";
		    	 
		    	 if(value == -1)
		    		 votes_str = "downVotes";
		    	 
		    	 rs = st.executeQuery("SELECT " + votes_str + " FROM usersolutions" + schoolType.toLowerCase() + " WHERE id='" + solutionID + "'");
		    	 
	    		 int votes = 0;
	    		 
	    		 if(rs.next())
	    			 votes = rs.getInt(votes_str);
	    		 
	    		 votes += 1;
	    		 st.executeUpdate("UPDATE usersolutions" + schoolType.toLowerCase() + " SET " + votes_str + "='" + votes + "' WHERE id='" + solutionID + "'");
	    		 
	    		 increase_decreaseUploaderLikes(value);
	    		 
	    		 return true;
		    	
		     }
		        
		        
		} catch (SQLException e) {System.out.println("dbModel - changeRatio - didnt work");e.printStackTrace();}
	        
	    return false;
		
	}
	
	
	private void increase_decreaseUploaderLikes(int value) {
		
		try {

			rs = st.executeQuery("SELECT totalLikes, tier FROM users WHERE id='" + uploaderID + "'");

			int totalLikes = 0;
			String uploaderTier = "";

			if(rs.next()) {
				totalLikes = rs.getInt("totalLikes");
				uploaderTier = rs.getString("tier");
			}
			
			if(totalLikes + value == minLikesToBeVerified && uploaderTier.equals("student"))
				uploaderTier = "verified";
			
			else if(totalLikes + value < minLikesToBeVerified && uploaderTier.equals("verified"))
				uploaderTier = "student";
			
			st.executeUpdate("UPDATE users SET totalLikes ='" + (totalLikes += value) + "', tier='" + uploaderTier + "' WHERE id='" + uploaderID + "'");
			
		} catch(SQLException e) {System.out.println("dbModel - increase_decreaseUploaderLikes - didnt work");e.printStackTrace();}

	}
	
	
	
	
	
	public ArrayList<MessageBox> connect() {
		
		if(!MainView.databaseIsActive)
			return null;
		
       try { 
           
           BufferedReader br = new BufferedReader(new FileReader("src/info.txt"));
   		   name = br.readLine();
   		   schoolType = br.readLine();
   		   email = br.readLine();
   		   schoolClass = br.readLine();
   		   myTier = br.readLine();
   		   
   		   
   		   st = conn.createStatement();
   		   
           if(!Main.alreadyDone) {
	           st.executeUpdate("INSERT INTO users (name, school, email, tier, class, banned, added, evaluated, mac, skill, dependence, community) VALUES ('" + name + "'," + "'" + schoolType + "'," + "'" + email + "','" + "student" + "','" + schoolClass + "',"+ "'" + "" + "'," + "'" + 0 + "'," + "'" + "" + "'," + "'" + "" + "'," + "'" + 0 + "'," + "'" + 0 + "'," + "'" + 0 + "'" + ")");                                                       
	           setUpMACAddress();
           }
           
           checkIfBanned();
	           
           compareMACAddress();
   		
           getStats();
           
           getMyID();
           
       }
       catch (Exception e)
       {
    	   e.printStackTrace();
    	   System.out.println("ERROR Connection OPENSAVE-CLASS");
    	   return null;
       }
      
       return showPotentialMessages();
	       
	}
	
	private void getMyID() throws SQLException {
		
		rs = st.executeQuery("SELECT id FROM users WHERE email='" + email + "'");

        if(rs.next())
     	   myID = rs.getInt("id");
		
	}
	
	private void getStats() throws SQLException {
		
		rs = st.executeQuery("SELECT added, skill, dependence, community FROM users WHERE email='" + email + "'");

        if(rs.next()) {
     	   added = rs.getInt("added");
     	   skill = rs.getDouble("skill");
     	   dependence = rs.getDouble("dependence");
     	   community = rs.getDouble("community");
        }
		    
        if(added >= MINIMUMADDED)
     	   allowSolutions = true;
		
	}




	private void checkIfBanned() {
		try {
			 st =  conn.createStatement();
			 rs = st.executeQuery("SELECT banned FROM users WHERE email='" + email + "'");
	 		 String banned = "";
	
	         if(rs.next())
	      	   banned = rs.getString("banned");
	         
	         if(banned.equals("yes"))
	        	 MainView.isBANNED = true;
	         
		}catch(Exception ex) {
			
		}
 		   
 		   
	}
	
	
	public void uploadToDB(String jahrgang, String seite, String nummer) {
		
		if(checkExerciseAvailability(jahrgang, seite, nummer)) {
			MessageBox mb = new MessageBox("Fehler beim Hochladen", "Eine Lösung dieser Aufgabe existiert bereits!", "Bitte versuche eine andere Lösung hochzuladen.\n"
					+ "Du kannst auch oben im Menü unter ,,Verfügbarkeit'' testen, ob eine Lösung noch gebraucht wird.");
			mb.setVisible(true);
			return;
		}
			
		
		jahrgang = jahrgang.substring(0, 1);
		String exercise = jahrgang + "/" + seite + "/" + nummer;
		
		if(exercise == null || exercise.equals(""))
			return;
		
		
		String code_str = MainModel.getCodeOnWorkPanel();
		
		 try {
			st = conn.createStatement();
			st.executeUpdate("INSERT INTO usersolutions" + schoolType.toLowerCase() + " (uploaderID, code, codeInfo) VALUES ('" + myID + "','" + code_str + "'," + "'" + exercise + "'" + ")");
			
			increaseCommunityStats(1);
			checkAdded();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		
	}
	
	
	
	private void increaseSkillStats() throws SQLException {
		st = conn.createStatement();
		st.executeUpdate("UPDATE users SET skill='" + skill + "' WHERE email='" + email + "'");
	}
	
	private void increaseDependenceStats(double value) {
		try {
			
			if(alreadyPunished)
				return;
			
			dependence += value;
			st = conn.createStatement();
			
			st.executeUpdate("UPDATE users SET dependence='" + dependence + "' WHERE email='" + email + "'");
			
		} catch (SQLException e) {System.out.println("dbModel - increaseDependenceStats - not working");e.printStackTrace();}
		
		alreadyPunished = true;
	}
	
	private void increaseCommunityStats(double value) throws SQLException {
		community += value;
		st = conn.createStatement();
		st.executeUpdate("UPDATE users SET community='" + community + "' WHERE email='" + email + "'");
	}




	private void checkAdded() {
		//check how many BS added by user
		int addedOLD = added;
		try {
			st.executeUpdate("UPDATE users SET added='" + (++added) + "' WHERE email='" + email + "'");
			if(added >= MINIMUMADDED) {
				allowSolutions = true;
				if(addedOLD < MINIMUMADDED) {
					MessageBox mb = new MessageBox("Bereit zum Aktivieren", "Gratulation! Sie haben eben neue Funktionen freigeschaltet!\n", "Sie können jetzt "
							+ "Lösungsvorschläge und automatische Korrektur aktivieren! \n\nKlicken Sie dazu auf die Wolke oder das vorletzte Symbol rechts oben!" + "\n\n"
							+ "Für weitere Informationen besuchen Sie bitte: www.discount-solutions.tk/loesungsvorschlaege oder \nwww.discount-solutions.tk/fehlerkorrektur");
					
					mb.setVisible(true);
				}
	
			}
			
			
			
			  System.out.println(added + "   adeddededAFTER");
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int createDialogWindow(String buttons, String message) {
		 int reply = 0;
		 
		if(buttons.equals("yesno"))
			reply = JOptionPane.showConfirmDialog(MainView.getConPane(), message, "Hinweis", JOptionPane.YES_NO_OPTION);
		
		else
			reply = JOptionPane.showConfirmDialog(MainView.getConPane(), message, "Hinweis", JOptionPane.OK_OPTION);
		 
		 if(reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION)
			 return -1;
		 else
			  return 0;
	}


	
	public LinkedList<Character> cutSuggestion(String suggestionCode, String currentContentOnWP, String solutionID, int currentBSCount) {
		
		int fromIndex = 0;
		LinkedList<Character> llSolution = null;

		for(int x = 0; x <= currentBSCount; x++) {
			fromIndex = suggestionCode.indexOf('#', fromIndex) + 1;
		}

		if(suggestionCode.substring(0, fromIndex - 1).equals(currentContentOnWP)) {

			String suggestion = suggestionCode.substring(fromIndex - 1);

			llSolution = new LinkedList<Character>();

			for(int x = 0; x < suggestion.length(); x++) {
				llSolution.addLast(suggestion.charAt(x));
			}

			ll_wrongSolutions.addLast(Integer.parseInt(solutionID));

			increaseDependenceStats(1);

		}

		return llSolution;

	}
	
	
	
	
	public ArrayList<MessageBox> showPotentialMessages() {
		try {
		    st = conn.createStatement();
		    st2 = conn.createStatement();
	        rs = st.executeQuery("SELECT * FROM messages");
	        
	        String receiver = null;
		    String heading1 = null;
		    String heading2 = null;
		    String content = null;
		    String msgType = null;
		    
		    ArrayList<MessageBox> list = new ArrayList<MessageBox>();
		    
	        while(rs.next()) {
	        	receiver = rs.getString("receiver");
	        	heading1 = rs.getString("heading1");
	        	heading2 = rs.getString("heading2");
	        	content = rs.getString("msgcontent");
	        	msgType = rs.getString("msgType");
		        
		        if(receiver.equals("allusers") || receiver.equals(email)) {
		        	MessageBox mb = null;
		        	if(msgType.equals("1heading")) {
		        		mb = new MessageBox("Nachricht vom Admin", heading1, content);
		        	}
		        	
		        	else if(msgType.equals("2headings")) {
		        		mb = new MessageBox("Nachricht vom Admin", heading1, heading2, content);
		        	}
		        	
		        	else if(msgType.equals("warning")) {
		        		mb = new MessageBox("Nachricht vom Admin", heading1, content, true);
		        	}
					
		        	st2.executeUpdate("UPDATE messages SET received='yes' WHERE id=" + rs.getString("id"));
		        	
		        	list.add(mb);
		        }
	        
	        }
	        
	        return list;
	    }
		catch(Exception ex) {
		    	ex.printStackTrace();
		    }
		return null;
		
		
		
	}
	
	
	
	
	//-------------------------------------------Fehlerkorrektur-------------------------------------------
	

	public String[] getBestARMatch(String codeInfo, boolean onlyTS) {
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT id, uploaderID, code, codeInfo, upVotes, downVotes, commentCount, comments FROM usersolutions" + schoolType.toLowerCase() + " WHERE codeInfo='" + codeInfo + "' ORDER BY upVotes DESC");
			
			
			String highestCode = "";
			String highestUsername = "";
			int highestUpvotes = -1;
			int highestDownvotes = -1;
			String highestSolutionID = "";
			int highestCommentCount = -1;
			String highestUploaderTier = "";


			while(rs.next()) {
				
				uploaderID = rs.getString("uploaderID");
				String info[] = getUsernameAndUploaderTier(rs.getString("uploaderID"));
				highestUsername = info[0];
				highestUploaderTier = info[1];
				
				if(rs.getInt("upVotes") > highestUpvotes || highestUploaderTier.equals("teacher")) {
					highestCode = rs.getString("code");
					highestUpvotes = rs.getInt("upVotes");
					highestDownvotes = rs.getInt("downVotes");
					highestCommentCount = rs.getInt("commentCount");
					highestSolutionID = rs.getString("id");

					if(highestUploaderTier.equals("teacher"))
						break;
				}

			}
			

			if(highestCode.equals(""))
				MainView.addNoteToCheckPanel("Diese Aufgabe ist noch nicht verfügbar!");
			
			else if(onlyTS && !highestUploaderTier.equals("teacher"))
				MainView.addNoteToCheckPanel("Es gibt noch keine Leherlösung!");

			else {
				MainView.addNoteToCheckPanel("\u00a9" + highestUsername + "  " + "(" + highestUpvotes + " Like(s))");
				return new String[] {highestCode, highestUsername, Integer.toString(highestUpvotes), Integer.toString(highestDownvotes), Integer.toString(highestCommentCount), highestSolutionID, highestUploaderTier};
			}

		} catch(Exception e) {e.printStackTrace();}
		
		return null;

	}
	
	
	
	
		
		
	private String[] getUsernameAndUploaderTier(String uploaderID) {
		
		try {
			
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT name, tier FROM users WHERE id='" + uploaderID + "'");
	
			if(rs.next())
				return new String[] {rs.getString("name"), rs.getString("tier")};
			
			rs.close();
			
		} catch (SQLException e) {System.out.println("dbModel - getUsernameAndUploaderTier - doesnt work");e.printStackTrace();}
		
		return null;
		
	}



	public LinkedList<Buchungssatz> checkBS(LinkedList<Buchungssatz> unsureBSList, LinkedList<Buchungssatz> correctBSList) {
		
		LinkedList<Buchungssatz> newBSList = new LinkedList<Buchungssatz>();
		
		try {
           
           for(int y = 0; y < correctBSList.size(); y++) {
        	   
        	   if(y == unsureBSList.size())
        		   unsureBSList.addLast(createBSWithNote("Dieser Buchungssatz wurde automatisch hinzugefügt"));
        	   
        	   if(correctBSList.get(y).getCode().equals(unsureBSList.get(y).getCode()))
        		   sortNewBSList(newBSList, correctBSList.get(y), unsureBSList.get(y), true, null, -1);
        	   
        	   else {
        		   
        		   int tempY = checkIfBSmissing(newBSList, y, unsureBSList, correctBSList, null);
        		   
        		   if(tempY == y)
        			   checkIfBSOverload(newBSList, y, unsureBSList, correctBSList, null);

        		   else
        			   y = tempY;
        		   
        	   }

           }

           increaseSkillStats();
           
        	   
		}catch(Exception e) {e.printStackTrace();}
		
		 return newBSList;
		 
	}
	
	
	
		
	public LinkedList<Buchungssatz> checkBSExam(LinkedList<Buchungssatz> unsureBSList, LinkedList<Buchungssatz> correctBSList) {

		LinkedList<Buchungssatz> newBSList = new LinkedList<Buchungssatz>();
		int overloadSize = 0;
		String temp = solution;

		int bsNumber = temp.length() - temp.replace("#", "").length();

		examBSList = new ArrayList<Integer>();
		for(int x = 0; x < bsNumber; x++) {
			examBSList.add(0);
		}

		try {

			for(int y = 0; y < correctBSList.size(); y++) {
				
				if(y == unsureBSList.size())
					unsureBSList.addLast(createBSWithNote("Dieser Buchungssatz wurde automatisch hinzugefügt"));
				
				if(correctBSList.get(y).getCode().equals(unsureBSList.get(y).getCode()))
					sortNewBSList(newBSList, correctBSList.get(y), unsureBSList.get(y), true, examBSList, y);

				else {

					int tempY = checkIfBSmissing(newBSList, y, unsureBSList, correctBSList, null);

					if(tempY == y)
						checkIfBSOverload(newBSList, y, unsureBSList, correctBSList, null);
					
					else
						y = tempY;

				}

			}

			increaseSkillStats();

		} catch(Exception e) {e.printStackTrace(); System.out.println("db_Model - checkBSExam - didint work!");}

		examBSList.add(-overloadSize);

		return newBSList;
		
	}
		
		
	
	
	
	private int checkIfBSmissing(LinkedList<Buchungssatz> newBSList, int index, LinkedList<Buchungssatz> unsureBSList,  LinkedList<Buchungssatz> correctBSList, ArrayList<Integer> examBSList) {

		 ArrayList<Buchungssatz> missingPanel = new ArrayList<Buchungssatz>();
		
   		   for(int i = index; i < correctBSList.size(); i++) {
   			   
   			   if(correctBSList.get(i).getCode().equals(unsureBSList.get(index).getCode())) {
   				   
   				   for(int k = 0; k < missingPanel.size(); k++) {
   					   
   					   unsureBSList.add(index, createBSWithNote("Dieser Buchungssatz fehlte bei Ihnen"));
   					   sortNewBSList(newBSList, missingPanel.get(k), unsureBSList.get(index), false, null, 0);
   					   
   				   }
   				   
   				   sortNewBSList(newBSList, correctBSList.get(i), unsureBSList.get(index + missingPanel.size()), true, examBSList, i);
   				   return i;
   				   
   			   }
   			   
   			   else
   				   missingPanel.add(correctBSList.get(i));

   		   }
		
   		   return index;
   		   
	}
	
	
	private int checkIfBSOverload(LinkedList<Buchungssatz> newBSList, int index, LinkedList<Buchungssatz> unsureBSList, LinkedList<Buchungssatz> correctBSList, ArrayList<Integer> examBSList) {
		
		int overloadSize = 0;
		ArrayList<Buchungssatz> overloadList = new ArrayList<Buchungssatz>(); 
		
		   for(int l = index; l < unsureBSList.size(); l++) {
			   
			   if(correctBSList.get(index).getCode().equals(unsureBSList.get(l).getCode())) {
				   
				   for(int k = 0; k < overloadList.size(); k++) {
					   
					   unsureBSList.remove(overloadList.get(k));
   					   sortNewBSList(newBSList, null, overloadList.get(k), false, null, 0);
   					   
   				   }
				   
				   sortNewBSList(newBSList, correctBSList.get(index), unsureBSList.get(l - overloadList.size()), true, examBSList, index);
				   return overloadSize;
				   
			   }
			   
			   else {
				   overloadList.add(unsureBSList.get(l));
				   overloadSize++;
			   }

		   }
		   
		   //wenn ich bis hier komme, ist es ein struktureller Fehler im Buchungssatz und kein Fehlen bzw. zu viel sein eines BS.
		   investigateError(index, correctBSList, unsureBSList, false);
		   sortNewBSList(newBSList, correctBSList.get(index), unsureBSList.get(index), false, examBSList, -1);
		   
		   return 0;
		   
	}
	
	private Buchungssatz createBSWithNote(String note) {
		Buchungssatz bs = new Buchungssatz();
		bs.createBSContainer(MainView.workPanel);
		bs.addNoteToPanel(note, 100);
		return bs;
	}


	private void sortNewBSList(LinkedList<Buchungssatz> newBSList, Buchungssatz correctSolution, Buchungssatz unsureSolution, boolean checkMark, ArrayList<Integer> examBSList, int value) {
		
		if(correctSolution == null)
			unsureSolution.addNoteToPanel("Zu viele BS", 100);

		else
			newBSList.addLast(correctSolution);

		 newBSList.addLast(unsureSolution);
		 
		 if(checkMark)
			 unsureSolution.addCheckMark(examBSList, value);
		 else
			 unsureSolution.addErrorIcon();
		
	}
	
	private void investigateError(int index,  LinkedList<Buchungssatz> correctBSList, LinkedList<Buchungssatz> unsureBSList, boolean allRed) {

		ArrayList<JLabel> unsureKontoList = unsureBSList.get(index).getKontoList();
		ArrayList<JLabel> unsurePriceList = unsureBSList.get(index).getPriceList();
		
		if(unsureKontoList == null)
			return;
		
		if(allRed) {
			paintAllRed(unsureKontoList);
			paintAllRed(unsurePriceList);
			return;
		}
		
		ArrayList<JLabel> correctKontoList = correctBSList.get(index).getKontoList();
		ArrayList<JLabel> correctPriceList = correctBSList.get(index).getPriceList();

		
		if((correctKontoList.size() != unsureKontoList.size()))
			paintAllRed(unsureKontoList);
		
		if((correctPriceList.size() != unsurePriceList.size()))
			paintAllRed(unsurePriceList);
		
		else if((correctBSList.get(index).isLeftMore() != unsureBSList.get(index).isLeftMore())) {
			unsureBSList.get(index).addNoteToPanel("leftMore Konflikt", 100);
			paintAllRed(unsurePriceList);
			paintAllRed(unsureKontoList);
		}
		
		else {
			
			for(int x = 0; x < correctKontoList.size(); x++) {
				checkAndHighlightIfWrong(unsureKontoList, correctKontoList, x);
			}
			
			for(int x = 0; x < correctPriceList.size(); x++) {
				checkAndHighlightIfWrong(unsurePriceList, correctPriceList, x);
			}
			
		}
	}
	
	private void paintAllRed(ArrayList<JLabel> labelList) {
		for(int x = 0; x < labelList.size(); x++) {
			labelList.get(x).setForeground(Color.RED);
		}
	}
	
	private void checkAndHighlightIfWrong(ArrayList<JLabel> unsureList, ArrayList<JLabel> correctList, int index) {
		if(!correctList.get(index).getText().equals(unsureList.get(index).getText())) {
			unsureList.get(index).setForeground(Color.RED);
		}
	}

	public boolean checkExerciseAvailability(String jahrgang, String seite, String nummer) {
		
		try {
			
		rs = st.executeQuery("SELECT codeInfo FROM usersolutions" + schoolType.toLowerCase());
         
  	   	jahrgang = jahrgang.substring(0,1);
  	   	String info = jahrgang + "/" + seite + "/" + nummer;
         
         while(rs.next()) {
      	   if(rs.getString("codeInfo").equals(info))
      		   return true;
      	  
         }

         
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
         
		
	}
	
	
	
	
	
//--------------------------------------------MAC-Address + InternetConnection---------------------------------------
	
	private void setUpMACAddress() {

		 try {
			 
			 InetAddress ip;
			 ip = InetAddress.getLocalHost();
			 NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			
			 byte[] mac = network.getHardwareAddress();
	
	
			 StringBuilder sb = new StringBuilder();
			 for (int i = 0; i < mac.length; i++) {
				 sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			 }
				
				
			 st = conn.createStatement();
			 st.executeUpdate("UPDATE users SET mac='" + sb + "' WHERE email='" + email + "'");
		         
		         
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
	}
	
	private void compareMACAddress() {
		
		String storedMAC = null;
			
		try {
			
			rs = st.executeQuery("SELECT mac FROM users WHERE email='" + email + "'");
			
			if(rs.next())
				storedMAC = rs.getString("mac");
			
			InetAddress ip;
			ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			
			byte[] mac = network.getHardwareAddress();
			
			StringBuilder currentMAC = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				currentMAC.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}

			
			if(!(storedMAC.equals(currentMAC.toString())))
				Main.execdisCount = false;
		
		
		} catch (Exception e) {
			String msg = "Du bist wahrscheinlich nicht mit dem Internet verbunden!\n"
			+ "Ohne Internetverbindung kann das Programm nicht gestartet werden.\n";
		
			JOptionPane.showMessageDialog(null, msg, "disCount konnte nicht gestartet werden", JOptionPane.ERROR_MESSAGE);
			Main.execdisCount = false;
		}
		
	}
	
	
	
	
	
	//--------------------------------------Comments------------------------------------------------------------------------------------------------
	
	private void retrieveCodeInfoAndCommentsFromDB(String id) {
		commentID = id;
		try {
			
			rs = st.executeQuery("SELECT comments, codeinfo FROM usersolutions" + schoolType.toLowerCase() + " WHERE id='" + id + "'");
			
			if(rs.next()) {
				comments = rs.getString("comments");
				codeInfo = rs.getString("codeInfo");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	private LinkedList<String> splitComments() {
		
		LinkedList<String> ll = new LinkedList<String>();
		System.out.println(comments);
		commentCount = comments.length() - comments.replace("#", "").length();
		System.out.println(commentCount + "commentcount");
		
		for(int x = 0; x < commentCount; x++) {
			ll.addLast(comments.substring(1, 2)); // ! or ?
			comments = comments.substring(2, comments.length());
			if(comments.indexOf('#') == -1) {
				ll.addLast(comments.substring(0, comments.length()));
				break;
			}
			
			else {
				ll.addLast(comments.substring(0, comments.indexOf('#')));
				comments = comments.substring(comments.indexOf('#'), comments.length());
			}
		}
		
		System.out.println(ll);
		
		return ll;
		
	}
	
	
	
	
	public String uploadNewComment(LinkedList<QuestionComment> questionList) {
		try {
			String comments = "";
			
			for(int qIndex = questionList.size() - 1; qIndex >= 0; qIndex--) {
				
				QuestionComment questionObj = questionList.get(qIndex);
				comments = comments + "#?" + questionObj.getContent();
				ArrayList<String> answers = questionObj.getAnswers();
				
				for(int aIndex = 0; aIndex < answers.size(); aIndex++) {
					
					String answer = answers.get(aIndex);
					comments = comments + "#!" + answer;
					
				}
				
			}
		
		
			st.executeUpdate("UPDATE usersolutions" + schoolType.toLowerCase() + " SET comments='" + comments + "' WHERE id='" + commentID + "'");
			
			incrementCommentCount();
			
			increaseCommunityStats(0.5);
			
		} catch (Exception e) {
			System.out.println("ERROR: db_Model - uploadAnswer");
			e.printStackTrace();
		}
		
		return commentID;
	}
	
	
	
	
	private void incrementCommentCount() {
		this.commentCount++;
		
		try {
			st.executeUpdate("UPDATE usersolutions" + schoolType.toLowerCase() + " SET commentCount='" + getCommentCount() + "' WHERE id='" + commentID + "'");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//------------------------------------------Stats----------------------------------------------------------------------------
	
	private double[] retrieveGraphInfo(String email) {
		double stats[] = null;
		
		try {
			st = conn.createStatement();
			
			rs = st.executeQuery("SELECT skill, dependence, community FROM users WHERE email='" + email + "'");
			
			if(rs.next())
				stats = new double[] {rs.getDouble("skill"), rs.getDouble("dependence"), rs.getDouble("community")};

		}catch(Exception e) {e.printStackTrace();}
		
		return stats;
		
	}
	
	
	
	
	//---------------------------------------Exam----------------------------------------
	
	public int setUpExamOnDB(String solution, int studentCount, boolean enableBSHelp) {
		setUpResults(solution);
		setUpExam(solution);
		setUpBSHelp(enableBSHelp);
		
		return pid;
	}
	

	private void setUpExam(String solution) {
		try {
			
			st.executeUpdate("INSERT INTO exams (resultID, solution) VALUES ('" + resultID + "', " + "'" + solution + "')");
			
			rs = st.executeQuery("SELECT id FROM exams WHERE resultID='" + resultID + "'");
			if(rs.next())
				pid = rs.getInt("id");
			
			st.executeUpdate("UPDATE exams SET resultID='" + resultID + "', solution='' WHERE id='" + resultID + "'");
		
		}catch(SQLException e) {System.out.println("setUpExam didnt work -- db_Model");e.printStackTrace();}
	}
	
	
	
	private void setUpResults(String solution) {
		try {
			
			st.executeUpdate("INSERT INTO exams (resultID, solution) VALUES ('" + -1 + "', " + "'" + solution + "')");
		
			rs = st.executeQuery("SELECT id FROM exams WHERE resultID='" + -1 + "' AND solution='" + solution + "'");
			if(rs.next())
				resultID = rs.getInt("id");
		
		}catch(SQLException e) {System.out.println("setUpResults didnt work -- db_Model");e.printStackTrace();}
	}
	

	private void setUpBSHelp(boolean enableBSHelp) {
		int value = 1;
		
		if(!enableBSHelp)
			value = 0;
		
		try {
			
			st.executeUpdate("UPDATE exams SET BShelp='" + value + "' WHERE id='" + pid + "'");
		
		}catch(SQLException e) {System.out.println("setUpBSHelp didnt work -- db_Model");e.printStackTrace();}
		
	}


	
	
	public ArrayList<Double> scanForLobbyJoins(int studentCount) {
		double studentID = -1;
		ArrayList<Double> alStudentID = new ArrayList<Double>();
		
    	try {

    		do {
    			
    			if(scanStart > studentCount) {
    				alStudentID.add((double) -studentCount);
    				scanStart = 1;
    				return alStudentID;
    			}
    			
    			rs = st.executeQuery("SELECT student" + scanStart + " FROM exams WHERE id='" + pid + "'");

				if(rs.next())
					studentID = rs.getDouble("student" + scanStart);
				
				if(studentID != -1) {
					alStudentID.add(studentID);
					scanStart++;
				}
				
    		}while(studentID != -1);
    		
    		
		
		}catch(SQLException e) {System.out.println("setUpResults didnt work -- db_Model");e.printStackTrace();}
	    	
	    	
	    return alStudentID;
	    	
    }
	
	public ArrayList<Double> scanForFinishedStudents(int studentCount) {
		scanList = new ArrayList<Integer>();
		ArrayList<Double> alResults = new ArrayList<Double>();
		double result = -1;
		
		for(int x = 1; x <= studentCount; x++) {
			
			try {
    			
    			rs = st.executeQuery("SELECT student" + x + " FROM exams WHERE id='" + resultID + "'");

				if(rs.next())
					result = rs.getDouble("student" + x);
				
				if(result != -1) {
					if(result == -99)
						result = MainModel.parseDouble("-99");
					alResults.add(result);
					scanList.add(x);
					setResultBackToMinusOne(x);
				}
					
			}catch(SQLException e) {System.out.println("scanForFinishedStudents didnt work -- db_Model");e.printStackTrace();}
			
		}

	    return alResults;
	    	
    }
	
	private void setResultBackToMinusOne(int studentCount) {
		try {
			
			st.executeUpdate("UPDATE exams SET student" + studentCount + "='" + -1 + "' WHERE id='" + resultID + "'");
			
		}catch(SQLException e) {System.out.println("setResultBackToMinusOne didnt work -- db_Model");e.printStackTrace();}
	}
	
	
	public ArrayList<String> retrieveStudentNamesBasedOnID(ArrayList<Double> alStudentID) {
		int studentIDIndex = 0;
		ArrayList<String> alStudentNames = new ArrayList<String>();
		
		try {

    		while(studentIDIndex < alStudentID.size() && alStudentID.get(studentIDIndex) >= 0) {
    			
				rs = st.executeQuery("SELECT name FROM users WHERE id='" + alStudentID.get(studentIDIndex) + "'");
				if(rs.next())
					alStudentNames.add(rs.getString("name"));
				
				studentIDIndex++;
				
    		}

		}catch(SQLException e) {System.out.println("setUpResults didnt work -- db_Model");e.printStackTrace();}
		
		return alStudentNames;
		
	}
	
	
	public ArrayList<String> getStudentsNamesBasedOnScanList() {
		ArrayList<Double> finishedStudentsIDs = new ArrayList<Double>();
		
		for(int x = 0; x < scanList.size(); x++) {
			
			try {
				
				rs = st.executeQuery("SELECT student" + scanList.get(x) + " FROM exams WHERE id='" + pid + "'");
				if(rs.next())
					finishedStudentsIDs.add(rs.getDouble("student" + scanList.get(x)));
	
			}catch(SQLException e) {System.out.println("setUpResults didnt work -- db_Model");e.printStackTrace();}
			
		}
		
		return retrieveStudentNamesBasedOnID(finishedStudentsIDs);
		
	}
	
	
	
	
	public void provideStartSignal() {
		try {
			
			st.executeUpdate("UPDATE exams SET pStart='" + 1 + "' WHERE id='" + pid + "'");
		
		} catch (SQLException e) {System.out.println("provideStartSignal didnt work -- db_Model");e.printStackTrace();}
	}
	
	public int checkStartSignal(int pid) {
		this.pid = pid;
		int signal = -1;
		
		try {
			
			rs = st.executeQuery("SELECT pStart FROM exams WHERE id='" + pid + "'");
			if(rs.next())
				signal = rs.getInt("pStart");

		}catch(SQLException e) {System.out.println("checkStartSignal didnt work -- db_Model");e.printStackTrace();}
		
		return signal;
		
	}
	
	
	public void getExamSolutionsFromDB() {
		
		try {
			
			rs = st.executeQuery("SELECT solution FROM exams WHERE id='" + pid + "'");
			if(rs.next())
				solution = rs.getString("solution");
			
		}catch(SQLException e) {System.out.println("getExamSolutionsFromDB didnt work -- db_Model");e.printStackTrace();}
		
		
	}
	
	public int signInStudent() {
		int examIsFull = 0;
		int nextIn = getNextIn();
		
		if(nextIn == -1)
			examIsFull = -1;
		
		else if(nextIn <= MAXSTUDENTS)
			insertStudentIDIntoExam(nextIn);
		
		else
			examIsFull = 1;
			
		return examIsFull;
			
	}
	
	private int getNextIn() {
		int nextIn = -1;
		System.out.println("PID: " + pid);
		
		try {
			
			rs = st.executeQuery("SELECT nextIn FROM exams WHERE id='" + pid + "'");
			if(rs.next())
				nextIn = rs.getInt("nextIn");
			
			st.executeUpdate("UPDATE exams SET nextIn='" + (nextIn + 1) + "' WHERE id='" + pid + "'");
			
		}catch(SQLException e) {nextIn = -1; System.out.println("getNextIn didnt work -- db_Model");e.printStackTrace();}
		
		return nextIn;
		
	}
	
	private void insertStudentIDIntoExam(int nextIn) {
		try {
			
			st.executeUpdate("UPDATE exams SET student" + nextIn + "='" + myID + "' WHERE id='" + pid + "'");
			
			studentNumber = nextIn;
		
		} catch (SQLException e) {System.out.println("insertStudentIDIntoExam didnt work -- db_Model");e.printStackTrace();}
	}
	
	
	public void getResultIDFromDB() {
		try {
			
			rs = st.executeQuery("SELECT resultID FROM exams WHERE id='" + pid + "'");
			if(rs.next())
				resultID = rs.getInt("resultID");
			
		}catch(SQLException e) {System.out.println("getResultIDFromDB didnt work -- db_Model");e.printStackTrace();}
	}
	
	
	public int getBSHelpPermission() {
		int allow = 0;
		
		try {
			
			rs = st.executeQuery("SELECT BShelp FROM exams WHERE id='" + pid + "'");
			if(rs.next())
				allow = rs.getInt("BShelp");
			
		}catch(SQLException e) {System.out.println("getBSHelpPermission didnt work -- db_Model");e.printStackTrace();}
		
		return allow;
	}

	
	
	public void handInResult(double av) {
		try {
			
			st.executeUpdate("UPDATE exams SET student" + studentNumber + "='" + av + "' WHERE id='" + resultID + "'");
		
		} catch (SQLException e) {System.out.println("handInResult didnt work -- db_Model");e.printStackTrace();}
	}

	
	public void handInEvaluation(ArrayList<Integer> outcomeList) {
		for(int x = 1; x <= outcomeList.size(); x++) {
			
			int currentBSEval = getCurrentBSFaults(x);
			adjustBSFaults(x, (currentBSEval + outcomeList.get(x - 1)));
			
		}
	}
	
	public int getCurrentBSFaults(int x) {
		
		int bsEval = -1;
		try {
			
			rs = st.executeQuery("SELECT bs" + x + " FROM exams WHERE id='" + resultID + "'");
			if(rs.next())
				bsEval = rs.getInt("bs" + x);
		
		} catch (SQLException e) {bsEval = -1; System.out.println("getCurrentBSFaults didnt work -- db_Model");e.printStackTrace();}
		
		return bsEval;
				
	}
	
	public void adjustBSFaults(int x, int newValue) {
		try {
			
			st.executeUpdate("UPDATE exams SET bs" + x + "='" + newValue + "' WHERE id='" + resultID + "'");
		
		} catch (SQLException e) {System.out.println("adjustBSFaults didnt work -- db_Model");e.printStackTrace();}
	}
	
	public ArrayList<Integer> getEvaluationFromDB() {
		
		int nextBS = 1;
		int currentBSEval;
		ArrayList<Integer> evalList = new ArrayList<Integer>();
		
		do {
			
			currentBSEval = getCurrentBSFaults(nextBS);
			evalList.add(currentBSEval);
			nextBS++;
			
		}while(nextBS <= MAXBS && currentBSEval != -1);
		
		return evalList;
		
	}
	
	public void deleteExamFromDB() {
		try {
			
			st.executeUpdate("DELETE FROM exams WHERE id='" + pid + "'");
			st.executeUpdate("DELETE FROM exams WHERE id='" + resultID + "'");
		
		} catch (SQLException e) {System.out.println("deleteExamFromDB didnt work -- db_Model");e.printStackTrace();}
	}
	
	public void notifyTeacherOfLeave() {
		try {
			
			st.executeUpdate("UPDATE exams SET student" + studentNumber + "='" + -99 + "' WHERE id='" + resultID + "'");
			System.out.println("UPDATED");
		
		} catch (SQLException e) {System.out.println("notifyTeacherOfLeave didnt work -- db_Model");e.printStackTrace();}

	}
	
	
	
//---------------------------------------checkTeacherCode----------------------------------------	
	
	
	public boolean checkTeacherCode(String enteredCode) {
		
		try {
			
			rs = st.executeQuery("SELECT code FROM teacherCodes WHERE code='" + enteredCode + "'");
			
			if(rs.next()) {
				myTier = "teacher";
				updateTxtInfoFile();
				updateTierOnDB();
				deleteCodeFromDB(enteredCode);
				return true;
			}

			
		} catch (SQLException e) {System.out.println("dbModel - checkTeacherCode - doesnt work");e.printStackTrace();}
		
		return false;
		
	}



	private void updateTxtInfoFile() {
		
		PrintStream fileStream;
		
		try {
			
			fileStream = new PrintStream(new File("src/info.txt"));
			fileStream.println(name);
			fileStream.println(schoolType);
			fileStream.println(email);
			fileStream.println(schoolClass);
			fileStream.println(myTier);
			fileStream.close();
			
		} catch (FileNotFoundException e) {System.out.println("dbModel - updateTxtInfoFile - doesnt work");e.printStackTrace();}
		
	}
	
	private void updateTierOnDB() {
		
		try {
			
			st.executeUpdate("UPDATE users SET tier='" + myTier + "' WHERE id='" + myID + "'");
		
		} catch (SQLException e) {System.out.println("updateTierOnDB didnt work -- db_Model");e.printStackTrace();}
		
	}
	
	private void deleteCodeFromDB(String enteredCode) {
		
		try {
			
			st.executeUpdate("DELETE FROM teachercodes WHERE code='" + enteredCode + "'");
		
		} catch (SQLException e) {System.out.println("deleteCodeFromDB didnt work -- db_Model");e.printStackTrace();}
		
	}




//---------------------------------------Getter----------------------------------------

	public String getSolutionID() {
		return solutionID;
	}
	
	public String getName() {
		return user_name;
	}
	
	public String getUploaderInfo() {
		return uploaderInfo;
	}
	
	public String getCodeInfo() {
		return codeInfo;
	}
	
	public int getBScount() {
		return BScount;
	}
	
	public String getUploaderTier() {
		return uploaderTier;
	}
	
	public int getUpVotes() {
		return upvotes;
	}
	
	public int getDownVotes() {
		return downvotes;
	}

	public int getCommentCount() {
		return commentCount;
	}
	
	public LinkedList<String> getComments(String id) {
		retrieveCodeInfoAndCommentsFromDB(id);
		LinkedList<String> commentList = splitComments();
		return commentList;
	}




	public double[][] getGraphInfo() {
		double userStats[] = retrieveGraphInfo(this.email);
		double averageStats[] = retrieveGraphInfo("everyone@gmail.com");
		double[][] stats = {userStats, averageStats};
		
		return stats;
	}
	
	public int getExamID() {
		return pid;
	}
	
	public int getResultID() {
		return resultID;
	}
	
	public ArrayList<Integer> getScanList() {
		return scanList;
	}

	public void setScanStart(int val) {
		scanStart = val;
	}

	public String getSolution() {
		return solution;
	}

	public void setPID(int pid) {
		this.pid = pid;
	}

	public boolean getInputToSuggestionIsIncorrect() {
		return inputToSuggestionIsIncorrect;
	}
	
	public ArrayList<Integer> getExamBSList() {
		return examBSList;
	}




	




	






}
