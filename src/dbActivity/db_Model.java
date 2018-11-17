package dbActivity;

import java.io.BufferedReader;
import java.io.FileReader;
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

import View.MainModel;
import View.MainView;
import disCount.Main;
import extraViews.MessageBox;

public class db_Model {
	
	
	
	
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	public LinkedList<Integer> ll_wrongSolutions = new LinkedList<Integer>();
	public ArrayList<String> currentImport = new ArrayList<String>();
	private String user_name;
	private String codeInfo;
	public String name;
	private int upvotes;
	private int downvotes;
	private String commentID;
	private String email;
	private String schoolType;
	private LinkedList<String> oldBSList;
	private Statement st2;
	public static boolean allowSolutions = false;
	public static final int MINIMUMADDED = 5;
	public static final int MAXSTUDENTS = 5;
	public static final int MAXBS = 5;
	
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
	
	
	
	
	
	
	
	
	public db_Model(Connection conn) {
		this.conn = conn;
	}




	public void changeRatio(int value, JPanel panel) {
		
		 try {
			 
			 rs = st.executeQuery("SELECT evaluated FROM users WHERE email='" + email + "'");
			
			 String evaluated = null;
			    
		     if(rs.next())
		        evaluated = rs.getString("evaluated");
		        
		     String id = MainView.hmPanelCodeID.get(panel);
		    
		     System.out.println(id + "  id");
		     if(!evaluated.contains("|" + id + "|")) {
		    	 
		    	 evaluated = evaluated + "|" + id + "|";
		    	 st.executeUpdate("UPDATE users SET evaluated='" + evaluated + "' WHERE email='" + email + "'");
		    	 
		    	 String votes_str = "upVotes";
		    	 
		    	 if(value == -1)
		    		 votes_str = "downVotes";
		    	 
		    	 rs = st.executeQuery("SELECT " + votes_str + " FROM usersolutions" + schoolType.toLowerCase() + " WHERE id='" + id + "'");
		    	 
	    		 int votes = 0;
	    		 
	    		 if(rs.next())
	    			 votes = rs.getInt(votes_str);
	    		 
	    		 votes += 1;
	    		 st.executeUpdate("UPDATE usersolutions" + schoolType.toLowerCase() + " SET " + votes_str + "='" + votes + "' WHERE id='" + id + "'");
		    	
		    	 JLabel upPanel = (JLabel) panel.getComponent(3);
		    	 JLabel downPanel = (JLabel) panel.getComponent(4);
		    	 
		    	 ArrayList<JLabel> alJL = new  ArrayList<JLabel>();
		    	 alJL.add(upPanel);
		    	 alJL.add(downPanel);
		    	 
		    	
		    	
		    	 for(int x = 0; x < MainView.llThumbsGroups.size(); x++) {
		    			 System.out.println(MainView.llThumbsGroups.get(x).size() + "  size");
		    			 if(MainView.llThumbsGroups.get(x).contains(alJL)) {
		    			 for(int y = 0; y < MainView.llThumbsGroups.get(x).size(); y++) {
		    				 
			    				 if(value == 1)
			    					 MainView.llThumbsGroups.get(x).get(y).get(1).setEnabled(false);
			    				 
			    				 else
			    					 MainView.llThumbsGroups.get(x).get(y).get(0).setEnabled(false);
			    				 
			    				 MainView.llThumbsGroups.get(x).get(y).get(0).addMouseListener(null);
			    				 MainView.llThumbsGroups.get(x).get(y).get(1).addMouseListener(null);
			    				 
		    			 }
		    		 }
		    	 }
		    	 
		    	 System.out.println("done");
		    	
		     }
		        
		        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	       
		
	}
	
	
	
	
	public ArrayList<MessageBox> connect() {
		
       try { 
           
           BufferedReader br = new BufferedReader(new FileReader("src/info.txt"));
   		   name = br.readLine();
   		   schoolType = br.readLine();
   		   email = br.readLine();
   		   String schoolClass = br.readLine();
   		   
   		   
   		   st = conn.createStatement();
   		   
           if(!Main.alreadyDone) {
	           st.executeUpdate("INSERT INTO users (name, school, email, class, banned, added, evaluated, mac, skill, dependence, community) VALUES ('" + name + "'," + "'" + schoolType + "'," + "'" + email + "'," + "'" + schoolClass + "',"+ "'" + "" + "'," + "'" + 0 + "'," + "'" + "" + "'," + "'" + "" + "'," + "'" + 0 + "'," + "'" + 0 + "'," + "'" + 0 + "'" + ")");                                                       
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
		
		
		String code_str = MainModel.sortHashMapPanelToCodeandGetCode();
		System.out.println(code_str);
		
		 try {
			st = conn.createStatement();
			st.executeUpdate("INSERT INTO usersolutions" + schoolType.toLowerCase() + " (name, email, code, codeInfo) VALUES ('" + name + "'," + "'" + email + "'," + "'" + code_str + "'," + "'" + exercise + "'" + ")");
			
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
	
	private void increaseDependenceStats(double value) throws SQLException {
		if(alreadyPunished)
			return;
		
		dependence += value;
		st = conn.createStatement();
		st.executeUpdate("UPDATE users SET dependence='" + dependence + "' WHERE email='" + email + "'");
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




	public LinkedList<Character> openDB_content(String currentContentOnWP) {
		if(!MainView.suggestionsEnabled)
			return null;
		
		currentImport.removeAll(currentImport);
		String code_str = "";
		 BScount = -1;
		 LinkedList<Character> char_LL = null;
		try {
			   st = conn.createStatement();
	           rs = st.executeQuery("SELECT id, name, code, codeInfo, upVotes, downVotes, commentCount, comments FROM usersolutions" + schoolType.toLowerCase());
	           
	           while(rs.next()) {
	        	   int currentBSCount = MainView.llJPanel.size();
	        	   int fromIndex = 0;
	        	   String dbBS = rs.getString("code");
	        	   String solutionID = rs.getString("id");
	        	   user_name = rs.getString("name");
	        	   codeInfo = rs.getString("codeInfo");
	        	   upvotes = rs.getInt("upVotes");
	        	   downvotes = rs.getInt("downVotes");
	        	   commentCount = rs.getInt("commentCount");
	        	   comments = rs.getString("comments");
	        	   
	        	   
	        	   if(ll_wrongSolutions.contains(Integer.parseInt(solutionID)) || (dbBS.length() - dbBS.replaceAll("#","").length() <= currentBSCount))
	        		   continue;
	        	   
	        	   for(int x = 0; x <= currentBSCount; x++) {
	        		   fromIndex = dbBS.indexOf('#', fromIndex) + 1;
	        		  // System.out.println(fromIndex + "    fromIndex");
	        	   }
	        	   
	        	  
	        	   if(dbBS.substring(0, fromIndex - 1).equals(currentContentOnWP)) {
		        	   code_str = dbBS.substring(fromIndex - 1);
		        	//   System.out.println(code_str + "     code-Str");
			           
			           BScount = code_str.length() - code_str.replaceAll("#","").length();
			           
			           char_LL  = new LinkedList<Character>();
			           for(int x = 0; x < code_str.length(); x++) {
			        	   char_LL.addLast(code_str.charAt(x));
			           }
			         //  System.out.println(char_LL);
			           
			           currentImport.add(solutionID);
			           currentImport.add(dbBS);
			           
			           ll_wrongSolutions.addLast(Integer.parseInt(solutionID));
			           
			           if(char_LL != null)
			        	   increaseDependenceStats(1);
			         
			           
			           return char_LL;
	        	   }

	           
	           }
	           
	           return null;
		         
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 return char_LL;
		
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
	
	
	
	public LinkedList<Character> setUpCheckBS(String klasse, String page, String number) {
		try {
			   st = conn.createStatement();
	           rs = st.executeQuery("SELECT id, name, code, codeInfo, upVotes, downVotes FROM usersolutions" + schoolType.toLowerCase());
	           
	           String highestCode = "";
	           String highestUser_name = "";
        	   int highestUpvotes = -1;
        	   
        	   String info = klasse + "/" + page + "/" + number;
	           
	           while(rs.next()) {
	        	   if(rs.getString("codeInfo").equals(info) && rs.getInt("upVotes") > highestUpvotes) {
	        		   highestCode = rs.getString("code");
		        	   highestUser_name = rs.getString("name");
		        	   highestUpvotes = rs.getInt("upVotes");
	        	   }
	        	  
	           }
	           
	           if(highestCode.equals("")) {
	        	   MainView.addNoteToCheckPanel("Diese Aufgabe ist noch nicht verfügbar.");
	        	   return null;
	           }
	           
	           else
	        	   MainView.addNoteToCheckPanel("\u00a9" + highestUser_name + "  " + "(" + highestUpvotes + " Like)");
	           
	           LinkedList<Character> char_LL = new LinkedList<Character>();
	           for(int x = 0; x < highestCode.length(); x++) {
	        	   char_LL.addLast(highestCode.charAt(x));
	           }
	           
	          oldBSList = new LinkedList<String>();
	          getWorkPanelCodeIntoList(oldBSList);
	           
	           
	           if(oldBSList.isEmpty())
	        	   return null;
	           
	           MainModel.deleteAll();
	           
	           return char_LL;
	           
	           
		}catch(Exception e) {e.printStackTrace(); return null;}
		
	}
	
	public LinkedList<String> getWorkPanelCodeIntoList(LinkedList<String> list) {
		MainModel.sortHashMapPanelToCodeandGetCode();
        for(int x = 0; x < MainView.llJPanel.size(); x++) {
        	list.add(MainView.hmPanelToCode.get(MainView.llJPanel.get(x)));
	    }
        return list;
	}
		
		
	public void checkBS() {
		try {
           MainModel.sortHashMapPanelToCodeandGetCode();
           
           for(int y = 0; y < MainView.llJPanel.size(); y++) {
        	   if(y == oldBSList.size())
        		   oldBSList.addLast(null);
        	   if(MainView.hmPanelToCode.get(MainView.llJPanel.get(y)).equals(oldBSList.get(y)))
        		   MainView.addCheckMark(MainView.llJPanel.get(y), null, -1);
        	   
        	   else {
        		   int tempY = checkIfBSmissing(y, oldBSList, null);
        		   
        		   if(tempY == y)
        			   checkIfBSOverload(y, oldBSList, null);
        		   else
        			   y = tempY;
        		   
        	   }

           }

           increaseSkillStats();
        	   
		}catch(Exception e) {e.printStackTrace();}
	}
		
	public ArrayList<Integer> checkBSExam(LinkedList<String> studentSolution) {
		
		//save();
		int overloadSize = 0;
		int oldSize = studentSolution.size();
		String temp = solution;
		int bsNumber = temp.length() - temp.replace("#", "").length();
		ArrayList<Integer> examBSList = new ArrayList<Integer>();
		for(int x = 0; x < bsNumber; x++) {
			examBSList.add(0);
		}
		
		try {
           MainModel.sortHashMapPanelToCodeandGetCode();
           
           for(int y = 0; y < MainView.llJPanel.size(); y++) {
        	   if(y == studentSolution.size())
        		   studentSolution.addLast(null);
        	   if(MainView.hmPanelToCode.get(MainView.llJPanel.get(y)).equals(studentSolution.get(y)))
        		   MainView.addCheckMark(MainView.llJPanel.get(y), examBSList, y);
        	   
        	   else {
        		   int tempY = checkIfBSmissing(y, studentSolution, examBSList);
        		   
        		   if(tempY == y)
        			   overloadSize += checkIfBSOverload(y, studentSolution, examBSList);
        		   else
        			   y = tempY;
        		   
        	   }

           }

           increaseSkillStats();
        	   
		}catch(Exception e) {e.printStackTrace();}
		
		examBSList.add(-overloadSize);
		
		return examBSList;
	}
		
		
	
	
	
	private int checkIfBSmissing(int index, LinkedList<String> compareToList,  ArrayList<Integer> examBSList) {

		 ArrayList<JPanel> wrongPanel = new ArrayList<JPanel>();
		
   		   for(int i = index; i < MainView.llJPanel.size(); i++) {
   			   if(MainView.hmPanelToCode.get(MainView.llJPanel.get(i)).equals(compareToList.get(index))) {
   				   for(int k = 0; k < wrongPanel.size(); k++) {
   					   compareToList.add(index, null);
   					   MainView.addErrorIcon(wrongPanel.get(k));
   				   }
   				   
   				   MainView.addCheckMark(MainView.llJPanel.get(i), examBSList, i);
   				   return i;
   			   }
   			   else
   				   wrongPanel.add(MainView.llJPanel.get(i));

   		   }
		
   		   return index;
	}
	
	
	private int checkIfBSOverload(int index, LinkedList<String> compareToList, ArrayList<Integer> examBSList) {
		
		int overloadSize = 0;
		String messageSingular = " Buchungssatz zu viel!";
		String messagePlural = " Buchungssätze zu viel!";
		
		   for(int l = index; l < compareToList.size(); l++) {
			   
			   if(MainView.hmPanelToCode.get(MainView.llJPanel.get(index)).equals(compareToList.get(l))) {
				   if(overloadSize == 1)
					   MainView.addNoteToPanel("Darüber " + overloadSize + messageSingular, MainView.llJPanel.get(index), 220, 5);
				   else
					   MainView.addNoteToPanel("Darüber " + overloadSize + messagePlural, MainView.llJPanel.get(index), 220, 5);
				   
				   compareToList.remove(index);
				   MainView.addCheckMark(MainView.llJPanel.get(index), examBSList, index);
				   return overloadSize;
			   }
			   
			   else {
				   overloadSize++;
				   System.out.println(MainView.hmPanelToCode.get(MainView.llJPanel.get(index)) + "  doesnt equal:  " + compareToList.get(l));
			   }
		   }
		   //wenn ich bis hier komme, ist es ein struktureller Fehler im Buchungssatz und kein Fehlen bzw. zu viel sein eines BS.
		   MainView.addErrorIcon(MainView.llJPanel.get(index));
		   
		   return 0;
		   
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
						result = Double.parseDouble("-99");
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
	


	
	
//---------------------------------------Getter----------------------------------------

	public ArrayList<String> getCurrentImport() {
		return currentImport;
	}
	
	public String getName() {
		return this.user_name;
	}
	
	public String getUploaderInfo() {
		return this.uploaderInfo;
	}
	
	public String getCodeInfo() {
		return this.codeInfo;
	}
	
	public int getBScount() {
		return BScount;
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
		return this.scanList;
	}

	public void setScanStart(int val) {
		this.scanStart = val;
	}

	public String getSolution() {
		return this.solution;
	}

	public void setPID(int pid) {
		this.pid = pid;
	}




	






}
