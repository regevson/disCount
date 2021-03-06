package dbActivity;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controls.ML_Controls;
import View.Buchungssatz;
import View.Controller_AbstractClass;
import View.MainController;
import View.MainModel;
import View.MainView;
import extraViews.ExamLobby;
import extraViews.ExamSetupView;
import extraViews.MessageBox;
import extraViews.Setup_View;
import extraViews.WaitForStartSignalView;

public class Controller_dbActivity extends Controller_AbstractClass{
	
	db_Model myModel;
	private ExamLobby lobby;
	private int studentCount;
	private int pid;
	private WaitForStartSignalView waitView;
	private Timer timer;
	public static boolean inExam = false;
	public ArrayList<Integer> cheaterList;
	private int seenResults;
	
	
	

	public Controller_dbActivity(MainController MC, Setup_View setupView) {
		super(MC);
		myModel = new db_Model(setupView.getConn());
	}
	
	
	


	public void initOpenProject() {
		MC.execOpenProject();
	}
	

	public void initPrintProject() {
		MC.execPrintProject();
	}
	
	public void initOpenProject(LinkedList<Character> char_LL) {
		MC.execOpenProject(char_LL);
	}
	
	public JPanel initGetWorkPanel() {
		return MC.execGetWorkPanel();
	}

	public boolean execChangeRatio(int value, String solutionID) {
		return myModel.changeRatio(value, solutionID);
	}

	public ArrayList<MessageBox> execConnect(Setup_View setupView) {
		return myModel.connect(setupView);
	}
	
	public String execGetSolutionID() {
		return myModel.getSolutionID();
	}

	public String[] execOpenDB_content(String codeInfo, boolean onlyTS) {
		return myModel.getBestARMatch(codeInfo, onlyTS);
	}
	
	public String execGetEmail() {
		return myModel.getEmail();
	}

	public String execGetName() {
		return myModel.getName();
	}

	public String execGetCodeInfo() {
		return myModel.getCodeInfo();
	}

	public int execGetUpVotes() {
		return myModel.getUpVotes();
	}

	public int execGetDownVotes() {
		return myModel.getDownVotes();
	}

	public int execGetBScount() {
		return myModel.getBScount();
	}
	
	public String execGetPartnerEmail() {
		return myModel.getPartnerEmail();
	}
	
	public void initAddInfoToPanel(String name, String codeInfo, String upvotes, String downvotes, String commentCount, String uploader, String solutionID, Buchungssatz bs, MouseListener ML) {
		MC.execAddInfoToPanel(name, codeInfo, upvotes, downvotes, commentCount, uploader, solutionID, bs, ML);
	}

	public void execCheckBS(String codeInfo, boolean onlyTS) {
		
		if(MainModel.getCodeOnWorkPanel().equals(""))
			return;
					
		String[] llSolution = myModel.getBestARMatch(codeInfo, onlyTS);
		
		if(llSolution != null) {
			
			LinkedList<Buchungssatz> unsureBSList = new LinkedList<Buchungssatz>();
			unsureBSList.addAll(MainView.bsList);
			
			MainModel.deleteAll(MC.execGetWorkPanel());
			
			MainView.noColorFade = true;
			initOpenProject(MainModel.convertStringToLL_Char(llSolution[0]));
			MainView.noColorFade = false;
			
			LinkedList<Buchungssatz> correctBSList = new LinkedList<Buchungssatz>();
			correctBSList.addAll(MainView.bsList);
			
			LinkedList<Buchungssatz> temp = myModel.checkBS(unsureBSList, correctBSList);
			
			MainModel.deleteAll(MC.execGetWorkPanel());
			
			MainView.bsList.addAll(temp);
			
			MC.execPaintBSListToWorkPanel();
			
		}
		
	}
	
	
	public void execUploadToDB(String jahrgang, String seite, String nummer) {
		myModel.uploadToDB(jahrgang, seite, nummer, MC.execGetGroupPanelList());
	}

	public void initSaveProject() {
		MC.execSaveProject();
	}

	public boolean execCheckExerciseAvailability(String jahrgang, String seite, String nummer) {
		return myModel.checkExerciseAvailability(jahrgang, seite, nummer);
	}

	public int execGetCommentCount() {
		return myModel.getCommentCount();
	}
	
	public void initTellViewToRemoveExerciseSelectionPanel() {
		MC.tellViewToRemoveExerciseSelectionPanel();
	}
	
	public void execPrintComments(String id) {
		LinkedList<String> commentsList = myModel.getComments(id);
		MC.execCommentsView("Kommentare zur L�sung der Aufgabe " + myModel.getCodeInfo(), commentsList);
	}

	public LinkedList<JButton> initGetAnswerButtonList() {
		return MC.execGetAnswerButtonList();
	}

	public void initProvideAnswerPanel(String type) {
		MC.execProvideAnswerPanel(type);
	}

	public LinkedList<QuestionComment> initGetQuestionList() {
		return MC.execGetQuestionList();
	}
	
	public String execGetCodeID() {
		return myModel.getCodeInfo();
	}

	public String execUploadNewComment(LinkedList<QuestionComment> questionList) {
		return myModel.uploadNewComment(questionList);
	}

	public void initCloseAnswerPanel() {
		MC.execCloseAnswerPanel();
	}

	public JPanel initGetCommentPanel() {
		return MC.execGetCommentPanel();
	}

	public void initRemoveNewQuestionPanel() {
		MC.execRemoveNewQuestionPanel();
	}

	public double[][] execGetGraphInfo() {
		return myModel.getGraphInfo();
	}

	public ExamSetupView execExamSetupView(ML_db ML) {
		ExamSetupView esv = new ExamSetupView(ML);
		return esv;
		
	}

	public int execSetUpExamOnDB(String solution, int studentCount, int bsCount, boolean enableBSHelp) {
		seenResults = 0;
		cheaterList = new ArrayList<Integer>();
		return myModel.setUpExamOnDB(solution, studentCount, bsCount, enableBSHelp);
	}
	
	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}
	
	public void setExamLobby(ExamLobby lobby) {
		this.lobby = lobby;
	}
	
	public void setPID(int pid) {
		this.pid = pid;
		myModel.setPID(pid);
	}
	
	public void setWaitView(WaitForStartSignalView waitView) {
		this.waitView = waitView;
	}
	
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	 public void execProvideStartSignal() {
	    myModel.provideStartSignal();
	}

	
	
	public final Runnable scanForLobbyJoins = new Runnable() {
		boolean allStudentsJoined = false;
		
        public void run() {
        	timer.scheduleAtFixedRate(new TimerTask() {
        		  @Override
        		  public void run() {
        			  ArrayList<Double> alStudentID = myModel.scanForLobbyJoins(studentCount);
        			  
        			  ArrayList<String> alStudentNames = myModel.retrieveStudentNamesBasedOnID(alStudentID);
        			  
        			  if(alStudentNames.size() >= 1) {
        				  lobby.displayJoinedStudents(alStudentNames);
        				  
	        			  if(alStudentID.get(alStudentID.size() - 1) == -studentCount) {
	        				  cancelTimer();
	        				  
	        				  allStudentsJoined = true;
	        				  lobby.notifyAllStudentsJoined();
	        				  return;
	        			  }
        			  }
        				  
        			 
        		  }

        		}, 3*1000, 3*1000);
        	
        	 if(allStudentsJoined)
        		 return;
        		 
        }
        
        
    };
    


	
	public final Runnable scanForFinishedStudents = new Runnable() {
		
		boolean allStudentsFinished = false;

        public void run() {
        	
        	timer.scheduleAtFixedRate(new TimerTask() {
        		  @Override
        		  public void run() {
        			  
        			  ArrayList<Double> alStudentResults = myModel.scanForFinishedStudents(studentCount, cheaterList);
        			  seenResults += alStudentResults.size();
        			  
        			  if(alStudentResults.size() >= 1 || seenResults == studentCount) {
        				  
        				  ArrayList<String> finishedStudentsNames = myModel.getStudentsNamesBasedOnScanList();
        				  int cheaterCount = lobby.updateFinishedStudents(finishedStudentsNames, alStudentResults);
        				  seenResults -= cheaterCount;
        				  
        				  
        				  if(seenResults == studentCount) {
    	        				
        					  cancelTimer();
	        				  
	        				  allStudentsFinished = true;
	        				  lobby.notifyAllStudentsFinished();
	        				  myModel.getScanList().removeAll(myModel.getScanList());
	        				  return;
	        			  
        				  }
        				  
        			  }
        			  
        			  System.out.println(seenResults +"  seenresults");
        				  
        		  }
        		  
        		  
        		  
        		}, 3*1000, 3*1000);
        	
        	 if(allStudentsFinished)
        		 return;
        		 
        }
        
    };




    public final Runnable scanForStartSignal = new Runnable() {
		
        public void run() {
        	timer.scheduleAtFixedRate(new TimerTask() {

				@Override
        		  public void run() {
        			 
        			  int signal = myModel.checkStartSignal(pid);
        			  
        			  if(signal == 1) {
        				  startExam();
        				  return;
        			  }
        			  
        			  else if(signal == 0 && !waitView.isVisible())
        				  waitView.setVisible(true);

        		  }
        		  
        		 
        		}, 3*1000, 3*1000);
        		 
        }
    };
    
    private void startExam() {
    	cancelTimer();
		myModel.getExamSolutionsFromDB();
		int allowHelp = myModel.getBSHelpPermission();
		if(allowHelp == 0)
			MC.execSetupExamEnvironment();
		waitView.examOpened();
		inExam = true;
    }
    
    private void examWarning(String string1, String string2) {
		waitView.setVisible(false);
		MessageBox msgB = new MessageBox(string1, string2, "", "smallMessage");
		msgB.setVisible(true);
    }
    
    private void cancelTimer() {
    	timer.cancel();
		timer.purge();
    }

	public double execHandInExam(JPanel workPanel) {
		
		inExam = false;
		
		LinkedList<Buchungssatz> unsureBSList = new LinkedList<Buchungssatz>();
		unsureBSList.addAll(MainView.bsList);
		
		MainModel.deleteAll(MC.execGetWorkPanel());
		
		initOpenProject(MainModel.convertStringToLL_Char(myModel.getSolution()));
		
		LinkedList<Buchungssatz> correctBSList = new LinkedList<Buchungssatz>();
		correctBSList.addAll(MainView.bsList);
		
		LinkedList<Buchungssatz> temp = myModel.checkBSExam(unsureBSList, correctBSList);
		
		MainModel.deleteAll(MC.execGetWorkPanel());
		
		MainView.bsList.addAll(temp);
		
		MC.execPaintBSListToWorkPanel();
		
		
		ArrayList<Integer> outcomeList = myModel.getExamBSList();
		
		double percentage = calcPercentage(outcomeList);
		
		myModel.getResultIDFromDB();
		myModel.handInResult(percentage);
		
		myModel.handInEvaluation(outcomeList);
		
		
		MC.execRemoveExamEnvironment();
		ML_Controls.windowListererIsActive = false;
		
		return percentage;
		
	}
	
	private double calcPercentage(ArrayList<Integer> outcomeList) {
		
		int points = 0;
		for(int x = 0; x < outcomeList.size(); x++) {
			points += outcomeList.get(x);
		}
		
		/*MessageBox msg = new MessageBox("points", "Du hast " + points + "% erreicht!", "Gratulation!");
		msg.setVisible(true);
		
		MessageBox msg1 = new MessageBox("overload", "Du hast " + (outcomeList.get((outcomeList.size()-1))) + "% erreicht!", "Gratulation!");
		msg1.setVisible(true);*/
		
		outcomeList.remove((outcomeList.size() - 1));
		
		if(points < 0)
			points = 0;

		return MainModel.roundDouble_giveBack_Double((double) ((100 / myModel.getBScount()) * points));
		
	}

	public void execSignInStudent() {
		
		int examIsFull = myModel.signInStudent();
		
		if(examIsFull == 1)
			examWarning("Pr�fung ist voll", "Die Pr�fung " + pid + " ist leider schon voll!.");
		
		else if(examIsFull == -1)
			examWarning("Fehler beim Verbinden!", "Die Pr�fung " + pid + " ist nicht vorhanden!.");
			
	}

	public ArrayList<String> execGetEvaluation() {
		
		ArrayList<Integer> evalList = myModel.getEvaluationFromDB();
		return calcSuccessRate(evalList);
		
	}
	
	private ArrayList<String> calcSuccessRate(ArrayList<Integer> evalList) {
		
		ArrayList<String> percentList = new ArrayList<String>();
		
		for(int x = 0; x < evalList.size(); x++) {
			percentList.add(MainModel.roundDouble_giveBack_String((double) ((100 / studentCount) * evalList.get(x))));
		}
		
		return percentList;
		
	}

	
	public void execDeleteExam() {
		myModel.deleteExamFromDB();
	}

	public void execNotifyTeacherOfLeave() {
		
		myModel.getResultIDFromDB();
		myModel.notifyTeacherOfLeave();
		setTimer(new Timer());
		new Thread(scanForCheatingDecision).start();
		
	}
	
	public final Runnable scanForCheatingDecision = new Runnable() {

        public void run() {
        	timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					
					int decision = myModel.retrieveCheatingDecision();
					
					if(decision == 1) {
						
						cancelTimer();
						paintDecisionInfo("Du darfst weitermachen!");
						
					}
					
					else if(decision == -1) {

						cancelTimer();
						inExam = false;
						paintDecisionInfo("Die Pr�fung ist leider ung�ltig!");
						MC.execRemoveExamEnvironment();
						
					}
					
					
				}
        		
        		  
        		 
        		}, 5*1000, 5*1000);
        		 
        }
    };
    
    
    
    public final Runnable scanForSessionComments = new Runnable() {
    	
    	int oldCommentCount = 0;

        public void run() {
        	timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					
					String comments = myModel.getColumnFromSession("chat");
					int newCommentCount = MainModel.countOccurencesOfChar(comments, "#");
					
					if(newCommentCount == oldCommentCount)
						return;
			
					String chatCommitHistory = myModel.getColumnFromSession("chatCommitHistory");
					
					MC.execPrintSessionComments(comments, oldCommentCount, newCommentCount - oldCommentCount, chatCommitHistory, myModel.getEmail());
					oldCommentCount = newCommentCount;
					
				}
        		
        		  
        		 
        		}, 1*1000, 1*1000);
        		 
        }
    };
    
    
    
    public final Runnable scanForSessionCodeChanges = new Runnable() {
    	
    	String oldCode = "-1";

        public void run() {
        	timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					
					String newCode = myModel.getColumnFromSession("code");
					
					if(oldCode.equals(newCode))
						return;
					
					MainModel.deleteAll(MC.execGetWorkPanel());
					oldCode = newCode;
					
					if(newCode.equals(""))
						return;
					
					String codeCommitHistory = myModel.getColumnFromSession("codeCommitHistory");
					
					MC.execPrintSessionCode(newCode, codeCommitHistory, myModel.getEmail());
					
				}
        		
        		  
        		 
        		}, 1*1000, 1*1000);
        		 
        }
    };
    
    
    private void paintDecisionInfo(String decision) {
    	
    	 MessageBox msgBox = new MessageBox("Achtung!", "Dein Lehrer hat eine Entscheidung gef�llt!", decision, "cheating");
         msgBox.setVisible(true);
    	
    }
	
	
	
	public void execShowSuggestions(String codeInfo, boolean onlyTS, MouseListener ML, String solutionCount) {
		
		String currentContentOnWP = "";
		LinkedList<Character> llSuggestion = null;
		
		if(MainView.bsList.size() > 0) {
						
			currentContentOnWP = MainModel.getCodeOnWorkPanel();
			
			MainView.isUploading = true;
			
			
			String suggestionInfo[] = execOpenDB_content(codeInfo, onlyTS);
			
			if(suggestionInfo == null)
				return;
			
			llSuggestion = myModel.cutSuggestion(suggestionInfo[0], currentContentOnWP, suggestionInfo[5], MainModel.countOccurencesOfChar(currentContentOnWP, "#"), solutionCount);
			
			if(llSuggestion == null) {
				
				displayWarningMessage();
				return;

			}
			
			ArrayList<Buchungssatz> bsList = MC.execOpenProject(llSuggestion);
			
			MainView.isUploading = false;
			
			String name = suggestionInfo[1];
			String solutionID = suggestionInfo[5];
			String upvotes = suggestionInfo[2];
			String downvotes = suggestionInfo[3];
			String commentCount = suggestionInfo[4];
			String uploaderTier = suggestionInfo[6];
				
			for(int x = 0; x < bsList.size(); x++) {
				
				bsList.get(x).addInfoToPanel(name, codeInfo, upvotes, downvotes, commentCount, uploaderTier, solutionID, ML);
				
				if(uploaderTier.equals("teacher"))
					bsList.get(x).addStar();
				
				else if(uploaderTier.equals("verified"))
					bsList.get(x).addVerified();
				
			}
			
		}
		
	}
	
	private void displayWarningMessage() {
		
		MessageBox msg = new MessageBox("Warnung", "Keine �bereinstimmung", "Deine bisherigen Buchungss�tze weisen Fehler auf!", "smallMessage");
		msg.setVisible(true);
		
	}

	public boolean execCheckTeacherCode(String enteredCode) {
		return myModel.checkTeacherCode(enteredCode);
	}

	public ArrayList<String> execGetAllGroups() {
		return myModel.getAllGroups();
	}
	
	public void initSetupSuchenWorkSpace() {
		MC.execSetupSuchenWorkSpace();
	}
	
	public ArrayList<String> execSearchForStudents(String searchString, boolean byClass) {
		return myModel.searchForStudents(searchString, byClass);
	}
	
	public void execAddStudentToGroup(String studentEmail) {
		myModel.addStudentToGroup(studentEmail);
	}

	public void execRemoveGroup(String groupName) {
		myModel.removeGroup(groupName);
	}

	public void initPaintGroups(ArrayList<String> groupList) {
		MC.execPaintGroups(groupList);
	}

	public void initRemoveGroupFromMiddlePanel(JPanel parent) {
		MC.execRemoveGroupFromMiddlePanel(parent);
	}

	public String execCheckIfStudentInGroup(String studentInfo) {
		return myModel.checkIfStudentInGroup(studentInfo);
	}

	public void execCreateNewGroup(String groupName) {
		myModel.createNewGroup(groupName);
	}

	public ArrayList<String> execGetAllStudentsInGroup(String groupName) {
		return myModel.getAllStudentsInGroup(groupName);
	}

	public void initMakeAllStudentsWorkSpace(ArrayList<String> studentList) {
		MC.execMakeAllStudentsWorkSpace(studentList);
	}
	
	public void execLogOut() {
		myModel.setLoggedOut();
	}

	public void execSetUseTime(long minutes) {
		myModel.setUseTime(minutes);
	}

	public void execChangeResultValue(int cheaterIndex, int value) {
		
		if(value == -2)
			seenResults++;
		
		myModel.changeResultValue(++cheaterIndex, value);
		
	}

	public void initRefreshLists() {
		MC.execRefreshLists();
	}

	public int execContactPartner(String partnerEmail) {
		return myModel.createSession(partnerEmail);
	}

	public int execSearchForSession(String partnerEmail) {
		myModel.setPartnerEmail(partnerEmail);
		return myModel.searchForSession(partnerEmail, execGetEmail());
	}

	public void initBuildSessionEnvironment() {
		MC.execBuildSessionEnvironment();
	}

	public void execSendSessionComment(String comment) {
		myModel.sendSessionComment(comment);
	}

	public void execUpdateSessionContent(String code) {
		myModel.updateSessionContent(code, MC.createCodeCommitHistory());
	}
	
	public void initRemoveSessionFromDB() {
		myModel.removeSessionFromDB();
	}

	public String execGetStringColumnFromDatabase(String query, String column) {
		return myModel.getStringColumnFromDatabase(query, column);
	}




}
