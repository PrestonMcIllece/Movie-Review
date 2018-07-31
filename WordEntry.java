/*
 * Preston McIllece's Homework6
 * 
 * This class creates a WordEntry object that has a word, a score, and the number of 
 * times that word has appeared
 */

public class WordEntry {
	private String word;
	private double score;
	private int occurences;

	//constructor initializing word, score, and number of occurences
	public WordEntry(String word, double score, int occurences) {
		// TODO Auto-generated constructor stub
		this.word = word;
		this.score = score;
		this.occurences = occurences;
	}

	//sets the word value
	public void setWord(String word) {
		this.word = word;
	}

	//sets the score
	public void setScore(double score) {
		this.score = score;
	}

	//sets the occurences
	public void setOccurences(int occurences) {
		this.occurences = occurences;
	}

	//returns the word
	public String getWord() {
		return word;
	}

	//returns the score
	public double getScore() {
		return score;
	}

	//returns the occurences
	public int getOccurences() {
		return occurences;
	}
}
