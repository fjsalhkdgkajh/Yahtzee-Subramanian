import java.util.Scanner;

public class YahtzeeGame
{
	/* instance data should include the five yahtzee dice, a scoreboard, and a CONSTANT (static final) variable NUM_SIDES
	which should be set to six (the number of sides on a yahtzee die) */
	private YahtzeeDie one;
	private YahtzeeDie two;
	private YahtzeeDie three;
	private YahtzeeDie four;
	private YahtzeeDie five;
	private Scanner s;
	private YahtzeeScorecard scorecard;
	private int NUM_SIDES = 6;

	/* initializes the dice and scoreboard */
	public YahtzeeGame()
	{
		one = new YahtzeeDie(NUM_SIDES);
		two = new YahtzeeDie(NUM_SIDES);
		three = new YahtzeeDie(NUM_SIDES);
		four = new YahtzeeDie(NUM_SIDES);
		five = new YahtzeeDie(NUM_SIDES);
		scorecard = new YahtzeeScorecard();
		s = new Scanner(System.in);
	}

	/* check to see if the game is over, and while the game is not over call the method takeTurn()
	once the game ends (hint: there are 13 turns in a game of Yahtzee), the method should print a
	final scorecard and return the grand total */
	public int playGame()
	{
		for (int turns = 0; turns<13; turns++) {
			takeTurn();
		}
		System.out.println(scorecard);
		return 0;
	}

	/* 	1. call rollDice()
		2. call printDice()
		3. Ask the user if they are satisfied with their roll, or if they would like to roll again.
		   If they are not satisfied continue, otherwise call markScore()
		4. call chooseFrozen()
		5. call rollDice()
		6. call printDice()
		7. Ask the user if they are satisfied with their roll, or if they would like to roll again.
		   If they are not satisfied continue, otherwise call markScore()
		8. call chooseFrozen()
		9. call rollDice()
		10. call printDice()
		11. call markScore()
	*/
	private void takeTurn()
	{
		rollDice();
		printDice();
		one.unfreezeDie();
		two.unfreezeDie();
		three.unfreezeDie();
		four.unfreezeDie();
		five.unfreezeDie();
		System.out.print("Are you satisfied with your roll (s) or would you like to roll again (r)?");
		String answers = s.nextLine();
		char answer = answers.charAt(0);
		if (answer=='s') {
			markScore();
		} else if (answer=='r') {
			chooseFrozen();
			rollDice();
			printDice();
			System.out.print("Are you satisfied with your roll (s) or would you like to roll again (r)?");
			char choice = s.nextLine().charAt(0);
			if (choice=='s') {
				markScore();
			} else if (choice=='r') {
				one.unfreezeDie();
				two.unfreezeDie();
				three.unfreezeDie();
				four.unfreezeDie();
				five.unfreezeDie();
				chooseFrozen();
				rollDice();
				printDice();
				markScore();
			}
		}
	}

	/* Rolls all unfrozen dice.  Also resets all dice to the unfrozen state after the roll */
	private void rollDice()
	{
		if (one.isFrozen()==false) {
			one.rollDie();
		}
		if (two.isFrozen()==false) {
			two.rollDie();
		}
		if (three.isFrozen()==false) {
			three.rollDie();
		}
		if (four.isFrozen()==false) {
			four.rollDie();
		}
		if (five.isFrozen()==false) {
			five.rollDie();
		}
	}

	/* Asks the user which dice should be frozen 1-5 (should be read in in one line) */
	private void chooseFrozen()
	{
		System.out.println("Which dice (1-5) would you like to keep?");
		String chosenDice = s.nextLine();
		for (int index = 0; index<chosenDice.length(); index+=2) {
			char choice = chosenDice.charAt(index);
			switch (choice) {
				case '1': one.freezeDie(); break;
				case '2': two.freezeDie(); break;
				case '3': three.freezeDie(); break;
				case '4': four.freezeDie(); break;
				case '5': five.freezeDie(); break;
			}
		}
	}

	/* Should print the value of all five dice separated by a tab (\t) to the console */
	private void printDice() {
		System.out.println(one.getValue() + "\t" + two.getValue() + "\t" + three.getValue()
				+ "\t" + four.getValue() + "\t" + five.getValue());
	}

	/* 1. Print a scoreboard
	   2. Ask the user where they would like to mark their score.
	   3. Call appropriate function
	   4. If false is returned the user entered an invalid number, so ask the user to try again	*/
	private void markScore()
	{
		scorecard.printScoreCard();
		System.out.println("Where would you like to mark your score?\n1. Ones\t2. Twos\t3. Threes" +
				"\n4. Fours\t5. Fives\t6. Sixes\n7. Three Of A Kind\t8. Four Of A Kind\n9. Full House" +
				"\t10. Small Straight\t11. Large Straight\n12. Yahtzee\t13. Chance");
		int choice = s.nextInt();
		s.nextLine();
		switch (choice) {
			case 1: scorecard.markOnes(one.getValue(), two.getValue(),
					three.getValue(), four.getValue(), five.getValue()); break;
			case 2: scorecard.markTwos(one.getValue(), two.getValue(),
					three.getValue(), four.getValue(), five.getValue()); break;
			case 3: scorecard.markThrees(one.getValue(), two.getValue(),
					three.getValue(), four.getValue(), five.getValue()); break;
			case 4: scorecard.markFours(one.getValue(), two.getValue(),
					three.getValue(), four.getValue(), five.getValue()); break;
			case 5: scorecard.markFives(one.getValue(), two.getValue(),
					three.getValue(), four.getValue(), five.getValue()); break;
			case 6: scorecard.markSixes(one.getValue(), two.getValue(),
					three.getValue(), four.getValue(), five.getValue()); break;
			case 7: scorecard.markThreeOfAKind(one.getValue(), two.getValue(),
					three.getValue(), four.getValue(), five.getValue()); break;
			case 8: scorecard.markFourOfAKind(one.getValue(), two.getValue(),
					three.getValue(), four.getValue(), five.getValue()); break;
			case 9: scorecard.markFullHouse(one.getValue(), two.getValue(),
					three.getValue(), four.getValue(), five.getValue()); break;
			case 10: scorecard.markSmallStraight(one.getValue(), two.getValue(),
					three.getValue(), four.getValue(), five.getValue()); break;
			case 11: scorecard.markLargeStraight(one.getValue(), two.getValue(),
					three.getValue(), four.getValue(), five.getValue()); break;
			case 12: scorecard.markYahtzee(one.getValue(), two.getValue(),
					three.getValue(), four.getValue(), five.getValue()); break;
			case 13: scorecard.markChance(one.getValue(), two.getValue(),
					three.getValue(), four.getValue(), five.getValue()); break;
		}
		//if (false) {
			//System.out.println("Invalid number, please try again.");
		//}
	}
}