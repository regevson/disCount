package dbActivity;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import View.Buchungssatz;
import View.MainModel;
import View.MainView;
import disCount.Main;
import extraViews.MessageBox;
import extraViews.Setup_View;

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
	public static int MINIMUMADDED = 99;
	public static int MINIMUMRATIO = 99;
	public static final int MAXSTUDENTS = 40;
	public static final int MAXBS = 40;
	private final int minLikesToBeVerified = 5;
	
	private int bsCount;
	private int commentCount;
	private String comments;
	private String uploaderInfo;
	
	private int added = 0;
	public static double skill;
	public static String currentGroup;
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
	public static String myTier;
	private String uploaderTier;
	private boolean inputToSuggestionIsIncorrect;
	private String schoolClass;
	private String uploaderID;
	private ArrayList<Integer> examBSList = null;
	private Color bsColor = MainView.disCountPurple;
	private int sessionID;
	private String partnerEmail;
	
	
	
	
	
	
	
	
	public db_Model(Connection conn) {
		this.conn = conn;
	}




	
	
	public ArrayList<MessageBox> connect(Setup_View setupView) {
		
		 try {
			 
			st = conn.createStatement();
			
			if(!MainView.databaseIsActive)
				return null;
		
           
    	   if(Main.retireveLocalInfo)
        	   retireveLocalInfo(setupView);
    	   
			else {

				BufferedReader br = new BufferedReader(new FileReader("src/info.txt"));
				name = br.readLine();
				schoolType = br.readLine();
				email = br.readLine();
				String password = br.readLine();
				schoolClass = br.readLine();
				myTier = br.readLine();

				if(!Main.alreadyDone) {
					
					st.executeUpdate(
							"INSERT INTO users (name, school, email, password, tier, class, banned, added, evaluated, skill, dependence, community) VALUES ('"
									+ name + "'," + "'" + schoolType + "'," + "'" + email + "','" + password + "','" + "student" + "','"
									+ schoolClass + "'," + "'" + "" + "'," + "'" + 0 + "'," + "'" + "" + "'," +
									"'" + 0 + "'," + "'" + 0 + "'," + "'" + 0 + "'" + ")");
					
				}

			}
   		   
   		   
      
           checkIfBanned();
           
          // setLoggedIn();
           
           getMinimumConstants();
   		
           getStats();
           
           myID = getIDByEmail(email);
           
       }
		 
       catch (Exception e){e.printStackTrace();return null;}
      
       return null;//showPotentialMessages();
	       
	}
	


	private void setLoggedIn() {
		
		try {

			int loggedIn = 0;
			
			rs = st.executeQuery("SELECT loggedIn FROM users WHERE email='" + email + "'");
	
	        if(rs.next())
	     	   loggedIn = rs.getInt("loggedIn");
	        
	        if(loggedIn == 0) {
	        	
	        	st.executeUpdate("UPDATE users SET loggedIn='1' WHERE email='" + email + "'");
	        	return;
	        	
	        }
	    
		} catch(SQLException e) {e.printStackTrace();}
		
		JOptionPane.showMessageDialog(null, "Sie melden sich mit einem Account an, welcher bereits in Verwendung ist.\nBitte laden Sie sich disCount selbst herunter und registrieren Sie sich wie"
				+ " gewöhnlich.\nEin anderer Weg bringt nur Probleme mit sich und führt zu unvorhersehbarem Verhalten!",
				"Nachricht", JOptionPane.PLAIN_MESSAGE);
		MainView.databaseIsActive = false;
		
	}
	
	private void getMinimumConstants() {
		
		try {
			
			rs = st.executeQuery("SELECT minAdded, minRatio FROM settings");
	
	        if(rs.next()) {
	     	   
	        	MINIMUMADDED = rs.getInt("minAdded");
	        	MINIMUMRATIO = rs.getInt("minRatio");
	        	
	        }
	    
		} catch(SQLException e) {e.printStackTrace();}
		
	}
	
	
	
	public void setLoggedOut() {
		
		try {
			
        	st.executeUpdate("UPDATE users SET loggedIn='0' WHERE email='" + email + "'");
        	return;
	    
		} catch(SQLException e) {e.printStackTrace();}
		
	}
	
	
	//------------------------------------sendStats------------------------------------
	
	public void setUseTime(long minutes) {
		
		try {
			
			double useTime = 0;
			
			rs = st.executeQuery("SELECT useTime FROM stats");
			
	        if(rs.next())
	     	   useTime = rs.getDouble("useTime") + minutes;
	        
        	st.executeUpdate("UPDATE stats SET useTime='" + useTime + "'");
	    
		} catch(SQLException e) {e.printStackTrace();}
		
	}
	
	
	




	private void retireveLocalInfo(Setup_View setupView) {

		email = setupView.getLoginEmail();
		
		try {

			rs = st.executeQuery("SELECT name, school, class, password, tier FROM users WHERE email='" + email + "'");
	
	        if(rs.next()) {
	        	
	     	   name = rs.getString("name");
	     	   schoolType = rs.getString("school");
	     	   schoolClass = rs.getString("class");
	     	   myTier = rs.getString("tier");
	     	   String password = rs.getString("password");
	     	   
	     	   setupView.writeInfoIntoFile(name, schoolType, email, password, schoolClass, myTier);
	     	   Main.retireveLocalInfo = false;
	     	   
	        }
        
		} catch(SQLException e) {e.printStackTrace();}
		
	}


	private int getIDByEmail(String email) {
		
		try {
		
			rs = st.executeQuery("SELECT id FROM users WHERE email='" + email + "'");

	        if(rs.next())
	     	   return rs.getInt("id");
	        
		} catch(Exception e) {e.printStackTrace();}
		
		return -1;
		
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
	         
		}catch(Exception e) {e.printStackTrace();}
 		   
	}
	
	
	public void uploadToDB(String jahrgang, String seite, String nummer, ArrayList<JPanel> groupPanelList) {
		
		if(!checkExerciseAvailability(jahrgang, seite, nummer)) {
			
			MessageBox mb = new MessageBox("Upload abgelehnt", "Eine Lösung dieser Aufgabe mit einer guten Bewertung existiert bereits!", "Eine andere Version wird deshalb nicht mehr benötigt.\n"
					+ "Bitte versuche eine andere Lösung hochzuladen.\n"
					+ "Du kannst auch oben im Menü unter ,,Verfügbarkeit'' testen, ob eine Lösung noch gebraucht wird.", "smallMessage");
			mb.setVisible(true);
			return;
			
		}
			
		
		jahrgang = jahrgang.substring(0, 1);
		String exercise = jahrgang + "/" + seite + "/" + nummer;
		
		if(exercise == null || exercise.equals(""))
			return;
		
		String groupIDs = checkForGroups(groupPanelList);
		
		String code_str = MainModel.getCodeOnWorkPanel();
		
		 try {
			 
			st = conn.createStatement();
			st.executeUpdate("INSERT INTO usersolutions" + schoolType.toLowerCase() + " (uploaderID, code, codeInfo, comments, groupIDs) VALUES ('" + myID + "','" + code_str + "'," + "'" + exercise + "'," + "'" + "', '" + groupIDs + "'" + ")");
			
			increaseCommunityStats(1);
			checkAdded();
			
		} catch(SQLException e) {e.printStackTrace();}
		
	}
	
	
	
	private String checkForGroups(ArrayList<JPanel> groupPanelList) {
		
		String groupIDs = "";
		
		if(groupPanelList == null)
			return "";
		
		for(int x = 0; x < groupPanelList.size(); x++) {
			
			if(((JRadioButton) groupPanelList.get(x).getComponent(groupPanelList.get(x).getComponentCount() - 1)).isSelected())
				groupIDs += getGroupIDByName(((JLabel) groupPanelList.get(x).getComponent(0)).getText()) + "#";
			
		}
		
		return groupIDs;
		
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
							+ "Für weitere Informationen besuchen Sie bitte: www.discount-solutions.tk/loesungsvorschlaege oder \nwww.discount-solutions.tk/fehlerkorrektur", "smallMessage");
					
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


	
	public LinkedList<Character> cutSuggestion(String suggestionCode, String currentContentOnWP, String solutionID, int currentBSCount, String solutionCount) {
		
		int fromIndex = 0;
		LinkedList<Character> llSolution = null;

		for(int x = 0; x <= currentBSCount; x++) {
			fromIndex = suggestionCode.indexOf('#', fromIndex) + 1;
		}

		if(suggestionCode.substring(0, fromIndex - 1).equals(currentContentOnWP)) {

			String suggestion = suggestionCode.substring(fromIndex - 1);
			
			suggestion = extendCut(suggestion, solutionCount);

			llSolution = new LinkedList<Character>();

			for(int x = 0; x < suggestion.length(); x++) {
				llSolution.addLast(suggestion.charAt(x));
			}

			ll_wrongSolutions.addLast(Integer.parseInt(solutionID));

			increaseDependenceStats(1);

		}

		return llSolution;

	}
	
	
	
	
	private String extendCut(String suggestion, String solutionCount) {
		
		if(solutionCount.equals("alle") || MainModel.countOccurencesOfChar(suggestion, "#") <= Integer.parseInt(solutionCount))
			return suggestion;
		
		int count = Integer.parseInt(solutionCount);
		int fromIndex = -1;
		
		for(int x = 0; x < count; x++) {
			
			fromIndex = suggestion.indexOf("#", fromIndex + 1);
		
		}
		
		suggestion = suggestion.substring(0, suggestion.indexOf("#", fromIndex + 1));
		
		return suggestion;
		
	}






	public ArrayList<MessageBox> showPotentialMessages() {
		
		try {
			
			ArrayList<MessageBox> list = new ArrayList<MessageBox>();

			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM messages");

			while (rs.next()) {
	        	
	        	String receiver = rs.getString("receiver");
	        	String heading1 = rs.getString("heading1");
	        	String heading2 = rs.getString("heading2");
	        	String content = rs.getString("msgcontent");
	        	String msgType = rs.getString("msgType");
		        
		        if(receiver.equals("allusers") || receiver.equals(email)) {
		        	
		        	MessageBox mb = null;
		        	
		        	if(msgType.equals("1heading"))
		        		mb = new MessageBox("Nachricht von regevson", heading1, content, "smallMessage");
		        	
		        	else if(msgType.equals("2headings"))
		        		mb = new MessageBox("Nachricht vom Admin", heading1, heading2, content);
		        	
		        	else if(msgType.equals("warning"))
		        		mb = new MessageBox("Nachricht vom Admin", heading1, content, "badMessage");
					
		        	else if(msgType.equals("update"))
		        		mb = new MessageBox("Nachricht von regevson", heading1, content, "bigMessage");
					
		        	st = conn.createStatement();
		        	st.executeUpdate("UPDATE messages SET received='yes' WHERE id=" + rs.getString("id"));
		        	
		        	list.add(mb);
		        	
		        }
	        
	        }
	        
	        return list;
	        
	    } catch(Exception ex) {ex.printStackTrace();}
		
		return null;
		
	}
	
	
	
	
	//-------------------------------------------Fehlerkorrektur-------------------------------------------
	

	public String[] getBestARMatch(String codeInfo, boolean onlyTS) {
		
		try {
			
			st = conn.createStatement();
			rs = st.executeQuery("SELECT id, uploaderID, code, codeInfo, upVotes, downVotes, commentCount, comments, groupIDs FROM usersolutions" + schoolType.toLowerCase() + " WHERE codeInfo='" + codeInfo + "' ORDER BY upVotes DESC");
			
			
			String highestCode = "";
			String highestUsername = "";
			int highestUpvotes = -1;
			int highestDownvotes = -1;
			String highestSolutionID = "";
			int highestCommentCount = -1;
			String highestUploaderTier = "";
			int bestRatio = -99;
			boolean abortOnTeacher = false;


			while(rs.next() && !abortOnTeacher) {
				
				uploaderID = rs.getString("uploaderID");
				String info[] = getUsernameAndUploaderTier(rs.getString("uploaderID"));
				String userName = info[0];
				String uploaderTier = info[1];
				
				int ratio = rs.getInt("upVotes") - rs.getInt("downVotes");
				
				if(ratio > bestRatio || uploaderTier.equals("teacher")) {

					if(uploaderTier.equals("teacher")) {
						
						if(!checkIfStudentInAllowedGroup(rs.getString("groupIDs")))
							continue;
						
						abortOnTeacher = true;
						
					}
					
					highestCode = rs.getString("code");
					highestUpvotes = rs.getInt("upVotes");
					highestDownvotes = rs.getInt("downVotes");
					highestCommentCount = rs.getInt("commentCount");
					highestSolutionID = rs.getString("id");
					highestUsername = userName;
					highestUploaderTier = uploaderTier;
					bestRatio = ratio;
					
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
	
	
	
	
		
		
	private boolean checkIfStudentInAllowedGroup(String groupIDs) {
		
		if(groupIDs.equals(""))
			return true;
		
		int groupID = getGroupIDByEmail(email);
		
		int startIndex = 0;

		for(int x = 0; x < MainModel.countOccurencesOfChar(groupIDs, "#"); x++) {
			
			String allowedGroup = groupIDs.substring(startIndex, groupIDs.indexOf("#"));
			
			if(groupID == Integer.parseInt(allowedGroup))
				return true;
			
			groupIDs = groupIDs.substring(groupIDs.indexOf("#") + 1);
		
		}
		
		return false;
		
	}




	private String[] getUsernameAndUploaderTier(String uploaderID) {
		
		try {
			
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT name, tier FROM users WHERE id='" + uploaderID + "'");
	
			if(rs.next())
				return new String[] {rs.getString("name"), rs.getString("tier")};
			
			rs.close();
			
		} catch (SQLException e) {e.printStackTrace();}
		
		return null;
		
	}



	public LinkedList<Buchungssatz> checkBS(LinkedList<Buchungssatz> unsureBSList, LinkedList<Buchungssatz> correctBSList) {
		
		LinkedList<Buchungssatz> newBSList = new LinkedList<Buchungssatz>();
		
		try {
           
           for(int y = 0; y < correctBSList.size(); y++) {
        	   
        	   if(y == unsureBSList.size())
        		   unsureBSList.addLast(createBSWithNote("Dieser Buchungssatz fehlte bei Ihnen!"));
        	   
        	   if(correctBSList.get(y).getCode().equals(unsureBSList.get(y).getCode()))
        		   sortNewBSList(newBSList, correctBSList.get(y), unsureBSList.get(y), true, null, -1);
        	   
        	   else {
        		   
        		   int[] missingArray = checkIfBSmissing(newBSList, y, unsureBSList, correctBSList);
        		   int tempY = missingArray[0];
        		   
        		   if(tempY == y)
        			   checkIfBSOverload(newBSList, y, unsureBSList, correctBSList);

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
		int missingSize = 0;
		String temp = solution;

		bsCount = temp.length() - temp.replace("#", "").length();

		examBSList = new ArrayList<Integer>();
		for(int x = 0; x < bsCount; x++) {
			examBSList.add(-1);
		}

		try {

			for(int y = 0; y < correctBSList.size(); y++) {
				
				if(y == unsureBSList.size())
					unsureBSList.addLast(createBSWithNote("Dieser Buchungssatz fehlte bei Ihnen!"));
				
				if(correctBSList.get(y).getCode().equals(unsureBSList.get(y).getCode()))
					sortNewBSList(newBSList, correctBSList.get(y), unsureBSList.get(y), true, examBSList, y);

				else {

					int[] missingArray = checkIfBSmissing(newBSList, y, unsureBSList, correctBSList);
					int tempY = missingArray[0];
					missingSize += missingArray[1];

					if(tempY == y)
						overloadSize += checkIfBSOverload(newBSList, y, unsureBSList, correctBSList);
					
					else
						y = tempY;
					
					

				}

			}

			increaseSkillStats();

		} catch(Exception e) {e.printStackTrace(); System.out.println("db_Model - checkBSExam - didint work!");}

		examBSList.add(-(overloadSize + missingSize));

		return newBSList;
		
	}
		
		
	
	
	
	private int[] checkIfBSmissing(LinkedList<Buchungssatz> newBSList, int index, LinkedList<Buchungssatz> unsureBSList,  LinkedList<Buchungssatz> correctBSList) {

		 ArrayList<Buchungssatz> missingPanel = new ArrayList<Buchungssatz>();
		
   		   for(int i = index; i < correctBSList.size(); i++) {
   			   
   			   if(correctBSList.get(i).getCode().equals(unsureBSList.get(index).getCode())) {
   				   
   				   for(int k = 0; k < missingPanel.size(); k++) {
   					   
   					   unsureBSList.add(index, createBSWithNote("Dieser Buchungssatz fehlte bei Ihnen"));
   					   sortNewBSList(newBSList, missingPanel.get(k), unsureBSList.get(index), false, null, 0);
   					   
   				   }
   				   
   				   sortNewBSList(newBSList, correctBSList.get(i), unsureBSList.get(index + missingPanel.size()), true, examBSList, i);
   				   return new int[] {i, missingPanel.size()};
   				   
   			   }
   			   
   			   else
   				   missingPanel.add(correctBSList.get(i));

   		   }
		
   		   return new int[] {index, 0};
   		   
	}
	
	
	private int checkIfBSOverload(LinkedList<Buchungssatz> newBSList, int index, LinkedList<Buchungssatz> unsureBSList, LinkedList<Buchungssatz> correctBSList) {
		
		ArrayList<Buchungssatz> overloadList = new ArrayList<Buchungssatz>(); 
		
		   for(int l = index; l < unsureBSList.size(); l++) {
			   
			   if(correctBSList.get(index).getCode().equals(unsureBSList.get(l).getCode())) {
				   
				   for(int k = 0; k < overloadList.size(); k++) {
					   
					   unsureBSList.remove(overloadList.get(k));
   					   sortNewBSList(newBSList, null, overloadList.get(k), false, null, 0);
   					   
   				   }
				   
				   sortNewBSList(newBSList, correctBSList.get(index), unsureBSList.get(l - overloadList.size()), true, examBSList, index);
				   return overloadList.size();
				   
			   }
			   
			   else
				   overloadList.add(unsureBSList.get(l));

		   }
		   
		   //wenn ich bis hier komme, ist es ein struktureller Fehler im Buchungssatz und kein Fehlen bzw. zu viel sein eines BS.
		   investigateError(index, correctBSList, unsureBSList, false);
		   sortNewBSList(newBSList, correctBSList.get(index), unsureBSList.get(index), false, examBSList, -1);
		   
		   return 0;
		   
	}
	
	private Buchungssatz createBSWithNote(String note) {
		Buchungssatz bs = new Buchungssatz();
		bs.createBSContainer(MainView.workPanel);
		bs.addInfoToPanel(note);
		return bs;
	}


	private void sortNewBSList(LinkedList<Buchungssatz> newBSList, Buchungssatz correctSolution, Buchungssatz unsureSolution, boolean checkMark, ArrayList<Integer> examBSList, int value) {

		Color color = MainView.disCountBlue;
		
		if(bsColor == color)
			color = MainView.disCountPurple;
		else
			color = MainView.disCountBlue;
		
		bsColor = color;
		
		if(correctSolution == null)
			unsureSolution.addInfoToPanel("Dieser Buchungssatz ist in der Lösung nicht vorhanden!");

		else {
			newBSList.addLast(correctSolution);
			correctSolution.paintBorder(color);
			correctSolution.getBSNumberPanel().setBackground(color);
		}

		 newBSList.addLast(unsureSolution);
		 unsureSolution.paintBorder(color);
		 unsureSolution.getBSNumberPanel().setBackground(color);
		 
		 if(checkMark)
			 unsureSolution.addCheckMark(examBSList, value);
		 else
			 unsureSolution.addErrorIcon();
		
	}
	
	private void investigateError(int index,  LinkedList<Buchungssatz> correctBSList, LinkedList<Buchungssatz> unsureBSList, boolean allRed) {

		ArrayList<JTextField> unsureKontoList = unsureBSList.get(index).getKontoList();
		ArrayList<JTextField> unsurePriceList = unsureBSList.get(index).getPriceList();
		
		if(unsureKontoList == null)
			return;
		
		if(allRed) {
			paintAllRed(unsureKontoList);
			paintAllRed(unsurePriceList);
			return;
		}
		
		ArrayList<JTextField> correctKontoList = correctBSList.get(index).getKontoList();
		ArrayList<JTextField> correctPriceList = correctBSList.get(index).getPriceList();

		
		if((correctKontoList.size() != unsureKontoList.size()))
			paintAllRed(unsureKontoList);
		
		if((correctPriceList.size() != unsurePriceList.size()))
			paintAllRed(unsurePriceList);
		
		else if((correctBSList.get(index).isLeftMore() != unsureBSList.get(index).isLeftMore())) {
			unsureBSList.get(index).addInfoToPanel("Der Buchungssatz wäre umgedreht richtig!");
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
	
	private void paintAllRed(ArrayList<JTextField> labelList) {
		
		for(int x = 0; x < labelList.size(); x++) {
			labelList.get(x).setForeground(Color.RED);
		}
		
	}
	
	private void checkAndHighlightIfWrong(ArrayList<JTextField> unsureList, ArrayList<JTextField> correctList, int index) {
		
		if(!correctList.get(index).getText().equals(unsureList.get(index).getText()))
			unsureList.get(index).setForeground(Color.RED);
		
	}

	public boolean checkExerciseAvailability(String jahrgang, String seite, String nummer) {
		
		try {

			jahrgang = jahrgang.substring(0, 1);
			String info = jahrgang + "/" + seite + "/" + nummer;

			rs = st.executeQuery("SELECT upVotes, downVotes FROM usersolutions" + schoolType.toLowerCase()
					+ " WHERE codeInfo=" + info);

			while(rs.next()) {

				if(!checkLikeDislikeRatio(rs.getInt("upVotes"), rs.getInt("downVotes")))
					return false;

			}

		} catch(Exception ex) {ex.printStackTrace(); return false;}

		return true;

	}
	
	private boolean checkLikeDislikeRatio(int likes, int dislikes) {
		
		int ratio = likes - dislikes;
		
		if(ratio >= MINIMUMRATIO)
			return false;
		
		return true;
		
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
	
	public int setUpExamOnDB(String solution, int studentCount, int bsCount, boolean enableBSHelp) {
		
		this.bsCount = bsCount;
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
	
	public ArrayList<Double> scanForFinishedStudents(int studentCount, ArrayList<Integer> cheaterList) {
		scanList = new ArrayList<Integer>();
		ArrayList<Double> alResults = new ArrayList<Double>();
		double result = -1;
		
		for(int x = 1; x <= studentCount; x++) {
			
			System.out.println("scanning student" + x);
			
			try {
    			
    			ResultSet rs = st.executeQuery("SELECT student" + x + " FROM exams WHERE id='" + resultID + "'");

				if(rs.next())
					result = rs.getDouble("student" + x);
				
				if(result != -1 && result != -99 && result != -2) { // -1 -> student not finished yet; -99 -> student cheated, now waiting for teacher decision; -2 -> student evicted due to cheating
					
					System.out.println("student" + x + "  is inside");
					alResults.add(result);
					scanList.add(x);
					
					if(result == -31) {
						
						changeResultValue(x, -99);
						cheaterList.add(x);
						
					}

					else
						changeResultValue(x, -1);
					
				}
					
			}catch(SQLException e) {e.printStackTrace();}
			
		}

	    return alResults;
	    	
    }
	
	
	public void changeResultValue(int student, int value) {
		
		try {
			
			st.executeUpdate("UPDATE exams SET student" + student + "='" + value + "' WHERE id='" + resultID + "'");
			
		}catch(SQLException e) {e.printStackTrace();}
		
	}

	public void changeResultValue(ArrayList<Integer> cheaterList, int value) {
		
		try {
			
			for(int x = 0; x < cheaterList.size(); x++) {
			
				st.executeUpdate("UPDATE exams SET student" + cheaterList.get(x) + "='" + value + "' WHERE id='" + resultID + "'");
			
			}
			
		}catch(SQLException e) {e.printStackTrace();}
		
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
		
		if(newValue < 0)
			newValue = 0;
		
		try {
			
			st.executeUpdate("UPDATE exams SET bs" + x + "='" + newValue + "' WHERE id='" + resultID + "'");
		
		} catch (SQLException e) {e.printStackTrace();}
		
	}
	
	public ArrayList<Integer> getEvaluationFromDB() {
		
		int nextBS = 1;
		int currentBSEval;
		ArrayList<Integer> evalList = new ArrayList<Integer>();
		
		do {
			
			currentBSEval = getCurrentBSFaults(nextBS);
			evalList.add(currentBSEval);
			++nextBS;
			
		}while(nextBS <= bsCount);
		
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
			
			st.executeUpdate("UPDATE exams SET student" + studentNumber + "='" + -31 + "' WHERE id='" + resultID + "'");
		
		} catch(SQLException e) {System.out.println("notifyTeacherOfLeave didnt work -- db_Model");e.printStackTrace();}

	}
	
	public int retrieveCheatingDecision() {
		
		try {
			
			rs = st.executeQuery("SELECT student" + studentNumber + " FROM exams WHERE id='" + resultID + "'");
			
			if(rs.next()) {
				
				int decision = rs.getInt("student" + studentNumber);
				System.out.println("decision: " + decision);
				
				if(decision == -1) // cheater is allowed to continue
					return 1;
				
				else if(decision == -2) // cheater is not allowed to continue
					return -1;
				
			}
		
		} catch (SQLException e) {e.printStackTrace();}
		
		return 0; // decision not ready yet
		
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
	
	

	
//---------------------------------------Groups----------------------------------------
	
	
	
	public ArrayList<String> getAllGroups() {
		
		ArrayList<String> groupList = new ArrayList<String>(); 
		
		try {
			
			rs = st.executeQuery("SELECT groupName FROM `groups` WHERE groupAdmin='" + myID + "'");
			
			while(rs.next())
				groupList.add(rs.getString("groupName"));

			
		} catch(SQLException e) {e.printStackTrace();}
		
		return groupList;
		
	}
	
	public void removeGroup(String groupName) {
		
		try {
			
			st.executeUpdate("DELETE FROM `groups` WHERE groupName='" + groupName + "' AND groupAdmin='" + myID + "'");
		
		} catch (SQLException e) {e.printStackTrace();}
		
		
	}
	
	public ArrayList<String> searchForStudents(String searchString, boolean byClass) {
		
		ArrayList<String> resultList = new ArrayList<String>(); 
		
		try {
			
			if(byClass)
				rs = st.executeQuery("SELECT name, email, class FROM users WHERE class LIKE '" + searchString + "%'");
			
			else
				rs = st.executeQuery("SELECT name, email, class FROM users WHERE name LIKE '" + searchString + "%'");
			
			while(rs.next())
				resultList.add(rs.getString("email") + "  |  " + rs.getString("name") + "  |  " + rs.getString("class"));

			
		} catch(SQLException e) {e.printStackTrace();}
		
		return resultList;
		
	}
	
	public void addStudentToGroup(String studentEmail) {
		
		studentEmail = studentEmail.substring(0, studentEmail.indexOf("|") - 1);
		studentEmail = studentEmail.replace(" ", "");

		int groupID = 0;
		
		try {
			
			groupID = getGroupIDByName(db_Model.currentGroup);
			
			st.executeUpdate("UPDATE users SET groupID='" + groupID + "' WHERE email='" + studentEmail + "'");
		
		} catch (SQLException e) {e.printStackTrace();}
		
	}
	
	public int getGroupIDByName(String groupName) {
		
		try {
			
			rs = st.executeQuery("SELECT id FROM `groups` WHERE groupName='" + groupName + "' AND groupAdmin='" + myID + "'");
			
			if(rs.next())
				return rs.getInt("id");
			
		} catch (SQLException e) {e.printStackTrace();}
		
		return 0;
		
	}
	
	
	
	public int getGroupIDByEmail(String email) {
		
		try {
			
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT groupID FROM users WHERE email='" + email + "'");
			
			if(rs.next())
				return rs.getInt("groupID");
			
		} catch (SQLException e) {e.printStackTrace();}
		
		return 0;
		
	}
	
	public String checkIfStudentInGroup(String studentInfo) {
		
		studentInfo = studentInfo.substring(0, studentInfo.indexOf("|") - 1);
		studentInfo = studentInfo.replace(" ", "");

		int groupID = 0;
		String groupName = "";
		
		try {
			
			rs = st.executeQuery("SELECT groupID FROM users WHERE email='" + studentInfo + "'");
			
			if(rs.next())
				groupID = rs.getInt("groupID");
			
			if(groupID == 0)
				return "ist in keiner Gruppe";
			
			rs = st.executeQuery("SELECT groupName FROM `groups` WHERE id='" + groupID + "'");
			
			if(rs.next())
				groupName = rs.getString("groupName");
			
			return "ist Mitglied bei " + groupName;
			
		
		} catch(SQLException e) {e.printStackTrace();}
		
		return groupName;
		
	}
	

	public void createNewGroup(String groupName) {
		
		try {
			
			st.executeUpdate("INSERT INTO `groups` (groupName, groupAdmin) VALUES ('" + groupName + "', '" + myID + "')");
		
		} catch(SQLException e) {e.printStackTrace();}
		
	}
	

	public ArrayList<String> getAllStudentsInGroup(String groupName) {
	
		ArrayList<String> studentList = new ArrayList<String>();
		
		try {
			
			int groupID = getGroupIDByName(groupName);

			rs = st.executeQuery("SELECT name, email, class FROM users WHERE groupID='" + groupID + "'");
			
			while(rs.next())
				studentList.add(rs.getString("email") + "  |  " + rs.getString("name") + "  |  " + rs.getString("class"));

		} catch(SQLException e) {e.printStackTrace();}
		
		return studentList;
		
	}


	
	
	
//---------------------------------------Sessions----------------------------------------
	


	public int createSession(String partnerEmail) {

		try {
			
			closeOpenedSessions(email);
			
			if(getIDByEmail(partnerEmail) == -1)
				return -1;

			st.executeUpdate(
					"INSERT INTO sessions (student1, student2, code, chat, codeCommitHistory, chatCommitHistory) VALUES ('"
							+ email + "', '" + partnerEmail + "', '" + "-1" + "', '" + "" + "', '" + "" + "', '" + ""
							+ "')");

			return searchForSession(partnerEmail);

		} catch (SQLException e) {e.printStackTrace();}

		return -1;

	}
	
	private void closeOpenedSessions(String email) {
		
		try {
			
			ResultSet rs = st.executeQuery("SELECT id FROM sessions WHERE student1='" + email + "' OR student2='" + email + "'");
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
				removeSessionFromDB(id);
			
			}
			
		} catch(SQLException e) {e.printStackTrace();}

	}
	
	public int searchForSession(String partnerEmail) {
		
		this.partnerEmail = partnerEmail;
		
		try {
			
			ResultSet rs = st.executeQuery("SELECT id FROM sessions WHERE student1='" + partnerEmail + "' AND student2='" + email + "'");
			
			if(rs.next())
				sessionID = rs.getInt("id");
			
		} catch(SQLException e) {sessionID = -1; e.printStackTrace();}
		
		return sessionID;

	}
	
	public void sendSessionComment(String comment) {
		
		try {
			
			String chat = getColumnFromSession("chat");
			chat += "#" + comment;
			
			String chatCommitHistory = getColumnFromSession("chatCommitHistory");
			chatCommitHistory += "#" + email;
			
			st.executeUpdate("UPDATE sessions SET chat='" + chat + "'");
			st.executeUpdate("UPDATE sessions SET chatCommitHistory='" + chatCommitHistory + "'");
			
		} catch(SQLException e) {e.printStackTrace();}

	}
	
	
	public String getColumnFromSession(String column) {
		
		String content = "";
		
		try {
			
			ResultSet rs = st.executeQuery("SELECT " + column + " FROM sessions WHERE student1='" + email + "' OR student2='" + email + "'");
			
			if(rs.next())
				content += rs.getString(column);
			
		} catch(SQLException e) {e.printStackTrace();}
		
		return content;
		
	}
	
	public void updateSessionContent(String code) {
		
		try {
			
			String codeCommitHistory = getColumnFromSession("codeCommitHistory");
			codeCommitHistory += "#" + email;
			
			st.executeUpdate("UPDATE sessions SET code='" + code + "'");
			st.executeUpdate("UPDATE sessions SET codeCommitHistory='" + codeCommitHistory + "'");
			
		} catch(SQLException e) {e.printStackTrace();}
		
	}
	
	public void removeSessionFromDB() {
		
		try {
			
			st.executeUpdate("DELETE FROM sessions WHERE student1='" + email + "' OR student2='" + email + "'");
		
		} catch (SQLException e) {e.printStackTrace();}
		
		
	}
	
	
	
	public void removeSessionFromDB(int id) {
		
		try {
			
			st.executeUpdate("DELETE FROM sessions WHERE id='" + id + "'");
		
		} catch (SQLException e) {e.printStackTrace();}
		
		
	}
	
	
	
	

//---------------------------------------Getter----------------------------------------

	public String getSolutionID() {
		return solutionID;
	}
	
	public String getName() {
		return user_name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getUploaderInfo() {
		return uploaderInfo;
	}
	
	public String getCodeInfo() {
		return codeInfo;
	}
	
	public int getBScount() {
		return bsCount;
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
	
	public int getStudentNumber() {
		return studentNumber;
	}





}
