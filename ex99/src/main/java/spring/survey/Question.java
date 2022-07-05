package spring.survey;

import java.util.List;

public class Question {
	public Question(String title) {
		this.title = title;
	}
	
	public Question(String title, List<String> option) {
		this.title = title;
		this.option = option;
	}
	
	public boolean isChoice() { //option이 있다면 참, 없다면 거짓으로 반환
		return option != null && !option.isEmpty();
	}
	
	private String title;
	private List<String> option;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getOption() {
		return option;
	}
	public void setOption(List<String> option) {
		this.option = option;
	}	
}