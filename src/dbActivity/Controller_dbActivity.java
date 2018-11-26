package dbActivity;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import View.Buchungssatz;
import View.Controller_AbstractClass;
import View.MainController;
import View.MainModel;
import View.MainView;
import extraViews.ExamLobby;
import extraViews.ExamSetupView;
import extraViews.MessageBox;
import extraViews.WaitForStartSignalView;

public class Controller_dbActivity extends Controller_AbstractClass{
	
	db_Model myModel;
	private ExamLobby lobby;
	private int studentCount;
	private int pid;
	private WaitForStartSignalView waitView;
	private Timer timer;
	public static boolean inExam = false;
	
	
	

	public Controller_dbActivity(MainController MC, Connection conn) {
		super(MC);
		myModel = new db_Model(conn);
	}
	
	
	


	public void initOpenProject() {
		MC.execOpenProject();
	}
	
	public void initOpenProject(LinkedList<Character> char_LL) {
		MC.execOpenProject(char_LL);
	}
	
	public JPanel initGetWorkPanel() {
		return MC.execGetWorkPanel();
	}

	public void execChangeRatio(int value, JPanel panel) {
		myModel.changeRatio(value, panel);
	}

	public ArrayList<MessageBox> execConnect() {
		return myModel.connect();
	}
	
	public String execGetSolutionID() {
		return myModel.getSolutionID();
	}

	public String[] execOpenDB_content(String codeInfo, boolean onlyTS) {
		return myModel.getBestARMatch(codeInfo, onlyTS);
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
	
	public String execGetUploader() {
		return myModel.getUploader();
	}
	
	public void initAddInfoToPanel(String name, String codeInfo, String upvotes, String downvotes, String commentCount, String uploader, String solutionID, Buchungssatz bs, MouseListener ML) {
		MC.execAddInfoToPanel(name, codeInfo, upvotes, downvotes, commentCount, uploader, solutionID, bs, ML);
	}

	public void execCheckBS(String codeInfo, boolean onlyTS) {
		if(MainModel.getCodeOnWorkPanel().equals(""))
			return;
					
		String[] llSolution = myModel.getBestARMatch(codeInfo, onlyTS);
		
		if(llSolution != null) {
			LinkedList<String> oldBSList = new LinkedList<String>();
			MainModel.getWorkPanelCodeIntoList(oldBSList);
			
			MainModel.deleteAll(MC.execGetWorkPanel());
			MC.execOpenProject(MainModel.convertStringToLL_Char(llSolution[0]));
			myModel.checkBS(oldBSList);
		}
	}
	
	public void execUploadToDB(String jahrgang, String seite, String nummer) {
		myModel.uploadToDB(jahrgang, seite, nummer);
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

	public int execSetUpExamOnDB(String solution, int studentCount, boolean enableBSHelp) {
		return myModel.setUpExamOnDB(solution, studentCount, enableBSHelp);
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
		int seenResults = 0;

        public void run() {
        	timer.scheduleAtFixedRate(new TimerTask() {
        		  @Override
        		  public void run() {
        			  ArrayList<Double> alStudentResults = myModel.scanForFinishedStudents(studentCount);
        			  seenResults += alStudentResults.size();
        			  
        			  if(alStudentResults.size() >= 1) {
        				  ArrayList<String> finishedStudentsNames = myModel.getStudentsNamesBasedOnScanList();
        				  lobby.updateFinishedStudents(finishedStudentsNames, alStudentResults);
        				  
	        			  if(seenResults == studentCount) {
	        				  cancelTimer();
	        				  
	        				  allStudentsFinished = true;
	        				  lobby.notifyAllStudentsFinished();
	        				  myModel.getScanList().removeAll(myModel.getScanList());
	        				  return;
	        			  }
        			  }
        				  
        			 
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
        			 
					System.out.println("SCANNING FOR STARTSIGNAL");
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
		int allow = myModel.getBSHelpPermission();
		if(allow == 0)
			MC.execRemoveLeftPanelAndMenu();
		waitView.examOpened();
		inExam = true;
    }
    
    private void examWarning(String string1, String string2) {
		waitView.setVisible(false);
		MessageBox msgB = new MessageBox(string1, string2, "");
		msgB.setVisible(true);
    }
    
    private void cancelTimer() {
    	timer.cancel();
		timer.purge();
    }

	public double execHandInExam(JPanel workPanel) {
		inExam = false;
		LinkedList<String> studentSolution = MainModel.getWorkPanelCodeIntoList(new LinkedList<String>());
		MainModel.deleteAll(workPanel);
		initOpenProject(MainModel.convertStringToLL_Char(myModel.getSolution()));
		ArrayList<Integer> outcomeList = myModel.checkBSExam(studentSolution);
		
		double percentage = calcPercentage(outcomeList);
		
		myModel.getResultIDFromDB();
		myModel.handInResult(percentage);
		
		myModel.handInEvaluation(outcomeList);
		
		
		MC.execShowLeftPanel();
		
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

		return MainModel.roundDouble_giveBack_Double((double) ((100 / outcomeList.size()) * points));
		
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
		inExam = false;
		MC.execShowLeftPanel();
	}
	
	
	
	public void execShowSuggestions(String codeInfo, boolean onlyTS, MouseListener ML) {
		
		String currentContentOnWP = "";
		LinkedList<Character> llSuggestion = null;
		
		if(MainView.bsList.size() > 0) {
						
			currentContentOnWP = MainModel.getCodeOnWorkPanel();
			
			MainView.isUploading = true;
			
			
			String suggestionInfo[] = execOpenDB_content(codeInfo, onlyTS);
			
			if(suggestionInfo == null)
				return;
			
			llSuggestion = myModel.cutSuggestion(suggestionInfo[0], currentContentOnWP, suggestionInfo[5], MainModel.countOccurencesOfChar(currentContentOnWP, "#"));
			
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
			String uploader = suggestionInfo[6];
				
			for(int x = 0; x < bsList.size(); x++) {
				bsList.get(x).addInfoToPanel(name, codeInfo, upvotes, downvotes, commentCount, uploader, solutionID, ML);
				
				if(uploader.equals("teacher"))
					bsList.get(x).addStar();
				else if(uploader.equals("verified"));
					bsList.get(x).addVerified();
			}
			
		}
		
	}
	
	private void displayWarningMessage() {
		MessageBox msg = new MessageBox("Warnung", "Keine �bereinstimmung", "Deine bisherigen Buchungss�tze weisen Fehler auf!");
		msg.setVisible(true);
	}




	
}
