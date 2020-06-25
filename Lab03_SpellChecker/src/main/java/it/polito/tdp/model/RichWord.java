package it.polito.tdp.model;

public class RichWord {
	
	private String word;
	private boolean correct;
	
	
	public RichWord(String word) {
		super();
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	@Override
	public String toString() {
		return this.word;
	}
	
	
}
