package dbActivity;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import View.MainController;
import View.MainModel;
import extraViews.ExamEvaluationView;
import extraViews.ExamLobby;
import extraViews.ExamSetupView;
import extraViews.InsertExamIDView;
import extraViews.MessageBox;
import extraViews.NewGroupView;
import extraViews.Setup_View;
import extraViews.WaitForStartSignalView;

public class ML_db implements MouseListener{
	
	private Controller_dbActivity myController;
	private JButton button = null;
	private int studentCount;
	private String solution;
	private Setup_View setupView;
	private int bsCount;
	private ExamLobby lobby;
	
	
	
	
	public ML_db(MainController mainController, Setup_View setupView) {
		
		myController = new Controller_dbActivity(mainController, setupView);
		mainController.getControllerList().addLast(myController);
		this.setupView = setupView;
		
	}

	
	public void initOpenProject() {
		myController.initOpenProject();
	}
	
	public void initOpenProject(LinkedList<Character> char_LL) {
		myController.initOpenProject(char_LL);
	}
	
	public void initPrintProject() {
		myController.initPrintProject();
	}
	
	public ArrayList<MessageBox> initConnect() {
		return myController.execConnect(setupView);
	}
	
	public String initGetSolutionID() {
		return myController.execGetSolutionID();
	}
	
	public void initCheckBS(String codeInfo, boolean onlyTS) {
		myController.execCheckBS(codeInfo, onlyTS);
	}
	
	public void initShowSuggestions(String codeInfo, boolean onlyTS, String solutionCount) {
		myController.execShowSuggestions(codeInfo, onlyTS, this, solutionCount);
	}
	
	public void initUploadToDB(String jahrgang, String seite, String nummer) {
		myController.initRefreshLists();
		myController.execUploadToDB(jahrgang, seite, nummer);
	}

	public void initSaveProject() {
		myController.initSaveProject();
	}
	
	public boolean initCheckExerciseAvailability(String jahrgang, String seite, String nummer) {
		return myController.execCheckExerciseAvailability(jahrgang, seite, nummer);
	}
	
	public void initPrintComments(String solutionID) {
		
		myController.initTellViewToRemoveExerciseSelectionPanel();
		myController.execPrintComments(solutionID);
		
	}
	
	public int initGetCommentCount() {
		return myController.execGetCommentCount();
	}
	
	public ExamSetupView startExam() {
		return myController.execExamSetupView(this);
	}
	
	public String initOpenSolution() {
		
		myController.initOpenProject();
		solution = MainModel.getCodeOnWorkPanel();
		return solution;
		
	}
	
	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}
	
	public void setBSCount(int bsCount) {
		this.bsCount = bsCount;
	}
	
	public void continueWithStudentLogin(boolean enableBSHelp) {
		
		int pid = myController.execSetUpExamOnDB(solution, studentCount, bsCount, enableBSHelp);
		lobby = new ExamLobby(this, pid);
		lobby.setVisible(true);
		myController.setExamLobby(lobby);
		myController.setStudentCount(this.studentCount);
		myController.setTimer(new Timer());
		new Thread(myController.scanForLobbyJoins).start();
		
	}
	
	public void initProvideStartSignal() {
		myController.execProvideStartSignal();
	}
	
	public void execScanForFinishedStudents() {
		myController.setTimer(new Timer());
		new Thread(myController.scanForFinishedStudents).start();
	}
	
	public InsertExamIDView joinExam() {
		InsertExamIDView ieiv = new InsertExamIDView(this, "Prüfungs-ID eingeben!", "ID", "Beitreten");
		return ieiv;
	}
	
	public void execScanForStartSignal() {
		myController.setTimer(new Timer());
		new Thread(myController.scanForStartSignal).start();
	}
	
	public void initSignInStudent(String pid) {
		
		myController.setPID(Integer.parseInt(pid));
		System.out.println("Passed PID: " + Integer.parseInt(pid));
		WaitForStartSignalView waitView = new WaitForStartSignalView();
		waitView.setVisible(true);
		myController.setWaitView(waitView);
		
		myController.execSignInStudent();
		
	}
	
	public void initHandInExam() {
		
		double percentage = myController.execHandInExam(myController.initGetWorkPanel());
		MessageBox msg = new MessageBox("Resultat", "Du hast " + percentage + "% erreicht!", "Gratulation!", "smallMessage");
		msg.setVisible(true);
		
	}
	
	public void openAuswertungView() {
		
		ExamEvaluationView ee = new ExamEvaluationView();
		ee.setVisible(true);
		ArrayList<String> percentList = myController.execGetEvaluation();
		ee.displayEvaluation(percentList);
		
		myController.execDeleteExam();
		
	}
	
	public void initNotifyTeacherOfLeave() {
		myController.execNotifyTeacherOfLeave();
	}
	
	public boolean initChangeRatio(int value, String solutionID) {
		return myController.execChangeRatio(value, solutionID);
	}
	
	public ArrayList<String> initGetAllGroups() {
		return myController.execGetAllGroups();
	}
	
	
	
	
	
	
	


	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(!(e.getSource() instanceof JLabel)) { //dann Antwort-Button
			
			if(button == null) {
				
				button = (JButton) e.getSource();
				myController.initProvideAnswerPanel(button.getText());
				
			}
			
			else {
				
				String qORA = ((JTextArea)((JScrollPane) (((JButton) e.getSource()).getParent().getComponent(2))).getViewport().getView()).getText();
				LinkedList<QuestionComment> questionList = myController.initGetQuestionList();
				
				if(button.getText().equals("Antworten"))
					addAnswer(qORA, questionList);
				
				else
					addQuestion(qORA, questionList);
				
				String commentID = myController.execUploadNewComment(questionList);
				JPanel commentPanel = myController.initGetCommentPanel();
				commentPanel.removeAll();
				myController.execPrintComments(commentID);
				myController.initRemoveNewQuestionPanel();
				myController.initCloseAnswerPanel();
				button = null;
				
			}
			
		}
		
		else {
		
			JLabel jltemp = (JLabel) e.getSource();
			
			if(jltemp.getIcon().toString().equals("src/exit_white.png")) {
				
				myController.initCloseAnswerPanel();
				button = null;
				
			}
			
			else if(jltemp.getIcon().toString().equals("src/addStudents.png")) {
				
				db_Model.currentGroup = ((JLabel) jltemp.getParent().getComponent(0)).getText();
				myController.initSetupSuchenWorkSpace();
				
			}
			
			else if(jltemp.getIcon().toString().equals("src/allStudentsInGroup.png")) {
				ArrayList<String> studentList = myController.execGetAllStudentsInGroup(((JLabel) jltemp.getParent().getComponent(0)).getText());
				myController.initMakeAllStudentsWorkSpace(studentList);
			}
			
			
			else if(jltemp.getIcon().toString().equals("src/trashCan.png")) {
				
				myController.execRemoveGroup(((JLabel) jltemp.getParent().getComponent(0)).getText());
				
				myController.initRemoveGroupFromMiddlePanel((JPanel) jltemp.getParent());
				myController.initPaintGroups(initGetAllGroups());
				
			}
			
			else if(jltemp.getIcon().toString().equals("src/studentAdd.png"))
				myController.execAddStudentToGroup(((JLabel) jltemp.getParent()).getText());
			
			else if(jltemp.getIcon().toString().equals("src/addGroup.png")) {
				
				NewGroupView ngv = new NewGroupView(this, "Geben Sie einen Gruppennamen ein", "Gruppenname", "Erstellen");
				ngv.setVisible(true);
				
			}
			
			else if(jltemp.getIcon().toString().equals("src/happyFace.png")) {
				
				myController.execChangeResultValue(-1);
				
				lobby.removeSmileys(lobby.changeStudentStatus("arbeitet", ((JLabel) jltemp.getParent().getComponent(0)).getText()));
				
			}
			
			else if(jltemp.getIcon().toString().equals("src/sadFace.png")) {
				
				System.out.println("clicked sad face");
				myController.execChangeResultValue(-2);
				
				lobby.removeSmileys((JPanel) jltemp.getParent());
				
			}
			
			else if(jltemp.getIcon().toString().equals("src/sendSessionComment.png")) {
				System.out.println("inchat");
				myController.execSendSessionComment(((JTextArea) ((JScrollPane) jltemp.getParent().getComponent(0)).getViewport().getView()).getText());
			}
			
		}
		
		
	}
	
	private void addAnswer(String answer, LinkedList<QuestionComment> questionList) {
		
		LinkedList<JButton> answerButtonList = myController.initGetAnswerButtonList();
		int index = answerButtonList.indexOf(button);
		QuestionComment questionObj = questionList.get(index);
		questionObj.addAnswer(answer);
		
	}
	
	private void addQuestion(String question, LinkedList<QuestionComment> questionList) {
		
		QuestionComment newQuestion = new QuestionComment();
		newQuestion.setContent(question);
		questionList.addFirst(newQuestion);
		
	}
	
	
	
	public boolean initCheckTeacherCode(String enteredCode) {
		return myController.execCheckTeacherCode(enteredCode);
	}
	
	public ArrayList<String> initSearchForStudents(String searchString, boolean byClass) {
		return myController.execSearchForStudents(searchString, byClass);
	}
	
	public String initCheckIfStudentInGroup(String studentInfo) {
		return myController.execCheckIfStudentInGroup(studentInfo);
	}
	
	public void initCreateNewGroup(String groupName) {
		
		myController.execCreateNewGroup(groupName);
		myController.initPaintGroups(initGetAllGroups());
		
	}
	
	public void initLogOut() {
		myController.execLogOut();
	}
	
	public void initSetUseTime(long minutes) {
		myController.execSetUseTime(minutes);
	}
	
	public int initContactPartner(String partnerEmail) {
		return myController.execContactPartner(partnerEmail);
	}
	
	public int initSearchForSessions(String partnerEmail) {
		return myController.execSearchForSession(partnerEmail);
	}
	
	public void initBuildSessionEnvironment() {
		myController.initBuildSessionEnvironment();
	}
	
	public void initScanForSessionComments() {
		
		myController.setTimer(new Timer());
		new Thread(myController.scanForSessionComments).start();
		
	}
	
	public void initScanForSessionCodeChanges() {
		
		myController.setTimer(new Timer());
		new Thread(myController.scanForSessionCodeChanges).start();
		
	}
	
	public void initUpdateSessionContent(String code) {
		myController.execUpdateSessionContent(code);
	}
	
	public void initSendSessionComment(String comment) {
		myController.execSendSessionComment(comment);
	}
	
	public String initGetStringColumnFromDatabase(String query, String column) {
		return myController.execGetStringColumnFromDatabase(query, column);
	}

	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	


	




	


	


	


	


	


	




}
