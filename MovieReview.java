/**
 *  @author Preston McIllece
 *  
 *  @description: This program reads in a file of movie reviews and adds 
 *  			  all of the words to the dictionary and changes the average 
 *  			  score of each word along the way
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MovieReview {
	public static void main(String args[]) {
		if (args.length != 1) {
			System.err.println("Must pass name of movie reviews file");
			System.exit(0);
		}

		// varaibles for keeping track of the input file
		String line;
		int score;
		List<String> fileLines;

		// the movie review entered by the user
		String review = " ";

		Scanner keyboard = new Scanner(System.in);

		// open input file
		try {
			// Read each line into a list
			fileLines = Files.readAllLines(Paths.get(args[0]));
		} catch(IOException e) {
			System.err.println("File " + args[0] + " not found.");
			return;
		}

		/**
		 * This logic reads through each input line and extracts each separate word.
		 */

		Iterator<String> itr = fileLines.iterator();
		HashMap dictionary = new HashMap(); //HashDictionaryLinear class wouldn't work for whatever reason

		while (itr.hasNext()) {
			// get the next line in the movie reviews
			line = itr.next();

			// obtain the score
			score = Integer.parseInt(line.substring(0, 1));

			// remove the score from the review
			line = line.substring(2).trim();

			// now remove all non alphanumeric characters
			line = line.replaceAll("[^a-zA-Z]+", " ");

			// splits the line into each word
			String tokens[] = line.split(" ");

			/**
			 * At this point, tokens[0] is the first word in the review,
			 * tokens[1] is the second word, and so forth.
			 */

			/**
			 * Apply the algorithm whereby you construct the dictionary that maps
			 * each word to a WordEntry object. At this point, each WordEntry object
			 * contains the total score and number of appearances of each word.
			 */

			for (int i = 0; i < tokens.length; i++) {
				//if the dictionary doesn't contain the word yet,
				//add the word
				if (!dictionary.containsKey(tokens[i])) {
					WordEntry value = new WordEntry(tokens[i], score, 1);
					dictionary.put(tokens[i], value);
				}
				//if it does, change the score and occurences of the word
				else {
					WordEntry temp = (WordEntry) dictionary.get(tokens[i]);
					double tempScore = temp.getScore();
					int occurences = temp.getOccurences();
					tempScore += score;
					occurences += 1;

					temp.setOccurences(occurences);
					temp.setScore(tempScore);

					dictionary.replace(tokens[i], temp);
				}
			}
		}

		System.out.println("Just read " + fileLines.size() + " reviews.");

		// After movie reviews have been entered in the dictionary, prompt the user for a new movie review

		while (review.length() > 0) {
			System.out.println("Enter a review -- Press return to exit");
			review = keyboard.nextLine();


			// parse message
			review = review.replaceAll("[^a-zA-Z]+", " ");

			// split the tokens
			String tokens[] = review.split(" ");

			/**
			 * Now apply the algorithm that gets the average score for each of the
			 * keywords, and calculates an average score for the review.
			 */

			/**
			 * Now report the review based upon the idea that:
			 * 
			 * > 2 positive review
			 * 
			 * == 2 neutral review
			 * 
			 * < 2 negative review
			 */

			double totalScore = 0;
			int numberOfWords = 0;
			double wordScore;
			int dummy;

			for (int i = 0; i < tokens.length; i++) {
				
				//if the user enters a word that isn't in
				//the dictionary, assume its score is 2
				if (!dictionary.containsKey(tokens[i])) {
					wordScore = 2;
					totalScore += wordScore;
					numberOfWords++;	
				}
				
				//do nothing, these words shouldn't be included
				else if (tokens[i].equals("The") || tokens[i].equals("the") || tokens[i].equals("a") || tokens[i].equals("an") || tokens[i].equals("A") || tokens[i].equals("An")) {
					 dummy = -1;
				}
				
				//These words should be weighted positively
				else if (tokens[i].equals("Best") || tokens[i].equals("best")) {
					WordEntry temp = (WordEntry) dictionary.get(tokens[i]);
					double tempScore = temp.getScore();
					int occurences = temp.getOccurences();
					wordScore = 1.5 * (tempScore / occurences); //likely a positive review
					totalScore += wordScore;
					numberOfWords++;
				}
				
				//these words should be weighted negatively
				else if (tokens[i].equals("Worst") || tokens[i].equals("worst")) {
					WordEntry temp = (WordEntry) dictionary.get(tokens[i]);
					double tempScore = temp.getScore();
					int occurences = temp.getOccurences();
					wordScore = .75 * (tempScore / occurences); //likely a negative review
					totalScore += wordScore;
					numberOfWords++;
				}
				
				//regular words, just add their averages up
				else {
					WordEntry temp = (WordEntry) dictionary.get(tokens[i]);
					double tempScore = temp.getScore();
					int occurences = temp.getOccurences();
					wordScore = tempScore / occurences;

					totalScore += wordScore;
					numberOfWords++;	
				}
				
			}

			System.out.println("The review has an average value of " + totalScore/numberOfWords);

			if (totalScore/numberOfWords > 2) 
				System.out.println("Positive Sentiment");
			else if (totalScore/numberOfWords < 2)
				System.out.println("Negative Sentiment");
			else
				System.out.println("Neutral Sentiment");

		}
	}
}
