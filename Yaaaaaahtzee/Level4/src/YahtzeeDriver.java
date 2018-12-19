import java.util.Scanner;

public class YahtzeeDriver
{
	/*
	1. Creates a new instance of the YahtzeeGame class
	2. Calls the playGame method on the Yahtzee object
	3. Asks user if they would like to play again
	4. When the user is done playing, prints the number of games played, min, max, and average score
	*/
	public static void main(String [] args)
	{
			int scores = 0;
			boolean repeat = true;
			Scanner s = new Scanner(System.in);
			int score;
			int minScore = 0;
			int maxScore = 0;
			double avgScore = 0;
			int scoreTotal = 0;
			do {
				YahtzeeGame myGame = new YahtzeeGame();
				System.out.println("Welcome to Josiah's Gay Gay Gay Yahtzee Emporium!");
				score = myGame.playGame();
				scoreTotal += score;
				scores++;
				if (score > maxScore) {
					maxScore = score;
				}
				if (scores ==1) {
					minScore = score;
				} else if (minScore>score) {
					minScore = score;
				}
				avgScore = (scoreTotal*1.0)/scores;
				System.out.println("Congratulations! Your score for this game was " + score + ",\n" +
						"your minimum score was " + minScore + ", your maximum score was " + maxScore +
						",and your average score was " + avgScore + ". Would you like to play again? (y/n)");
				if (s.nextLine().charAt(0)=='n') {
					repeat = false;
				}
			} while (repeat);
		System.out.println("ono you made them sad, now they'll be coming for you òvó");
	}
}
