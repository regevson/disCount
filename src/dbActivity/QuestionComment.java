package dbActivity;

import java.util.ArrayList;

public class QuestionComment {
	
	private String content;
	private ArrayList<String> answers = new ArrayList<String>();
	
	public QuestionComment() {
		
	}

	
	public void addAnswer(String answer) {
		answers.add(answer);
	}




	
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public ArrayList<String> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}
	
	

}
