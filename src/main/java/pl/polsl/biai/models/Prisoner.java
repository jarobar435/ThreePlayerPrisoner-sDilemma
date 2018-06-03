package pl.polsl.biai.models;

import java.util.ArrayList;

public class Prisoner {

	private ArrayList<Decision> lastThreeMoves = new ArrayList<Decision>(9);
	private ArrayList<Decision> strategy = new ArrayList<Decision>(512);

	private int score = 0;

	public void addMove(Decision move) {
		lastThreeMoves.add(move);
	}

	public void addStrategyElement(Decision elem) {
		strategy.add(elem);
	}

	public ArrayList<Decision> getLastThreeMoves() {
		return lastThreeMoves;
	}

	public void setLastThreeMoves(ArrayList<Decision> lastThreeMoves) {
		this.lastThreeMoves = lastThreeMoves;
	}

	public ArrayList<Decision> getStrategy() {
		return strategy;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int score) {
		this.score += score;
	}


//	// Class variables
//	private double firstMove;
//	private double conform;
//	private double defect;
//	private int points = 0;
//	private String currentChoice = null;
//	private String lastChoice = null;
//	private boolean first = true;
//
//	/*
//	 * pl.polsl.biai.models.Prisoner(double, double, double) - pl.polsl.biai.models.Prisoner constructor that creates an agent
//	 * given 3 double parameters. A points variable is initially set to 0
//	 */
//	public Prisoner(double firstMoveP, double conformP, double defectP) {
//		this.firstMove = firstMoveP;
//		this.conform = conformP;
//		this.defect = defectP;
//		this.points = 0;
//	}
//
//	/*
//	 * play(pl.polsl.biai.models.Prisoner) - Will play one agent vs. another. Generates random numbers
//	 * to choose conform or defect. Also calls methods to calculate how many
//	 * points each agent gets
//	 */
//	public void play(Prisoner opponent) {
//		// Will store a random double
//		double number;
//
//		// Resets first variables
//		this.first = true;
//		opponent.first = true;
//
//		// Plays 10 rounds
//		for (int i = 0; i < 10; i++) {
//			// Gets current choices for agents
//			// And adds to their point totals
//			number = secureRandom.nextDouble();
//			getChoices(opponent, number);
//			calculatePoints(opponent);
//		}
//	}
//
//	/*
//	 * getChoices(pl.polsl.biai.models.Prisoner, double) - Gets the current choice for each agent based
//	 * on the opponent's last move and the agent's random variables
//	 */
//	public void getChoices(Prisoner opponent, double number) {
//		// If this is the first move of the game
//		if (this.first == true || opponent.first == true) {
//			// If the random number is greater than the agent's firstMove
//			// variable
//			if (number > this.firstMove)
//				this.currentChoice = "defect";
//			// Else if it's less than or equal
//			else if (number <= this.firstMove)
//				this.currentChoice = "conform";
//			// Otherwise, error
//			else
//				this.currentChoice = "error";
//
//			// If the random number is greater than the opponent's firstMove
//			// variable
//			if (number > opponent.firstMove)
//				opponent.currentChoice = "defect";
//			// Else if it's less than or equal
//			else if (number <= opponent.firstMove)
//				opponent.currentChoice = "conform";
//			// Otherwise, error
//			else
//				opponent.currentChoice = "error";
//
//			// Turn first boolean off
//			this.first = false;
//			opponent.first = false;
//
//			return;
//		}
//
//		// If the opponent last chose conform
//		if (opponent.lastChoice.equalsIgnoreCase("conform")) {
//			// If the random number is greater than the agent's conform variable
//			if (number > this.conform)
//				this.currentChoice = "defect";
//			// Else if it's less than or equal
//			else if (number <= this.conform)
//				this.currentChoice = "conform";
//			// Otherwise, error
//			else
//				this.currentChoice = "error";
//		}
//
//		// If the opponent last chose defect
//		else if (opponent.lastChoice.equalsIgnoreCase("defect")) {
//			// If the random number is greater than the agent's defect variable
//			if (number > this.defect)
//				this.currentChoice = "conform";
//			// Else if it's less than or equal
//			else if (number <= this.defect)
//				this.currentChoice = "defect";
//			// Otherwise, error
//			else
//				this.currentChoice = "error";
//		}
//
//		// If the agent last chose conform
//		if (this.lastChoice.equalsIgnoreCase("conform")) {
//			// If the random number is greater than the agent's conform variable
//			if (number > opponent.conform)
//				opponent.currentChoice = "defect";
//			// Else if it's less than or equal
//			else if (number <= opponent.conform)
//				opponent.currentChoice = "conform";
//			// Otherwise, error
//			else
//				opponent.currentChoice = "error";
//		}
//
//		// If the agent last chose defect
//		else if (this.lastChoice.equalsIgnoreCase("defect")) {
//			// If the random number is greater than the agent's defect variable
//			if (number > opponent.defect)
//				opponent.currentChoice = "conform";
//			// Else if it's less than or equal
//			else if (number <= opponent.defect)
//				opponent.currentChoice = "defect";
//			// Otherwise, error
//			else
//				opponent.currentChoice = "error";
//		}
//	}
//
//	/*
//	 * calculatePoints(pl.polsl.biai.models.Prisoner) - Calculates how many points each agent gets and
//	 * changes the lastChoice variable
//	 */
//	public void calculatePoints(Prisoner opponent) {
//		// If both agents choose to conform, adjust points and last choice
//		// variables
//		if (this.currentChoice.equalsIgnoreCase("conform")
//				&& opponent.currentChoice.equalsIgnoreCase("conform")) {
//			this.points += 3;
//			opponent.points += 3;
//			this.lastChoice = "conform";
//			opponent.lastChoice = "conform";
//		}
//		// If this.agent conforms and opponent defects, adjust points and last
//		// choice variables
//		else if (this.currentChoice.equalsIgnoreCase("conform")
//				&& opponent.currentChoice.equalsIgnoreCase("defect")) {
//			this.points += 0;
//			opponent.points += 5;
//			this.lastChoice = "conform";
//			opponent.lastChoice = "defect";
//		}
//		// If this.agent defects and opponent conforms, adjust points and last
//		// choice variables
//		else if (this.currentChoice.equalsIgnoreCase("defect")
//				&& opponent.currentChoice.equalsIgnoreCase("conform")) {
//			this.points += 5;
//			opponent.points += 0;
//			this.lastChoice = "defect";
//			opponent.lastChoice = "conform";
//		}
//		// If both agents choose to defect, adjust points and last choice
//		// variables
//		else if (this.currentChoice.equalsIgnoreCase("defect")
//				&& opponent.currentChoice.equalsIgnoreCase("defect")) {
//			this.points += 1;
//			opponent.points += 1;
//			this.lastChoice = "defect";
//			opponent.lastChoice = "defect";
//		}
//		// If either agent had an error, print error message and exit
//		else if (this.currentChoice.equalsIgnoreCase("error")
//				|| opponent.currentChoice.equalsIgnoreCase("error")) {
//			System.out.println("Error with current choice.");
//			System.exit(0);
//		}
//	}
//
//	/*
//	 * mutate(pl.polsl.biai.models.Prisoner) - Copies an agent's variables into another agent index,
//	 * mutates each variable by +- .05, and resets points to 0
//	 */
//	public void mutate(Prisoner toPrisoner) {
//		// The range of the random mutation
//		double randLow = -.005;
//		double randHigh = .005;
//
//		// Generates a random value for each parameter
//		double mutateFirstMove = randLow + (randHigh - randLow)
//				* secureRandom.nextDouble();
//		double mutateConform = randLow + (randHigh - randLow)
//				* secureRandom.nextDouble();
//		double mutateDefect = randLow + (randHigh - randLow)
//				* secureRandom.nextDouble();
//
//		// Mutates the copied agent
//		if (this.firstMove + mutateFirstMove < 1
//				&& this.firstMove + mutateFirstMove > 0)
//			toPrisoner.firstMove = this.firstMove + mutateFirstMove;
//		if (this.conform + mutateConform < 1
//				&& this.conform + mutateConform > 0)
//			toPrisoner.conform = this.conform + mutateConform;
//		if (this.defect + mutateDefect < 1 && this.defect + mutateDefect > 0)
//			toPrisoner.defect = this.defect + mutateDefect;
//
//		toPrisoner.points = 0;
//
//		// If any percentages are negative, take the absolute value
//		if (toPrisoner.firstMove < 0)
//			toPrisoner.firstMove = Math.abs(toPrisoner.firstMove);
//		if (toPrisoner.conform < 0)
//			toPrisoner.conform = Math.abs(toPrisoner.conform);
//		if (toPrisoner.defect < 0)
//			toPrisoner.defect = Math.abs(toPrisoner.defect);
//	}
//
//	/*
//	 * Getter for points
//	 */
//	public int getPoints() {
//		return points;
//	}
//
//	/*
//	 * Setter for points
//	 */
//	public void setPoints(int points) {
//		this.points = points;
//	}
//
//	/*
//	 * Getter for firstMove
//	 */
//	public double getFirstMove() {
//		return firstMove;
//	}
//
//	/*
//	 * Setter for firstMove
//	 */
//	public void setFirstMove(double firstMove) {
//		this.firstMove = firstMove;
//	}
//
//	/*
//	 * Getter for conform
//	 */
//	public double getConform() {
//		return conform;
//	}
//
//	/*
//	 * Setter for conform
//	 */
//	public void setConform(double conform) {
//		this.conform = conform;
//	}
//
//	/*
//	 * Getter for defect
//	 */
//	public double getDefect() {
//		return defect;
//	}
//
//	/*
//	 * Setter for defect
//	 */
//	public void setDefect(double defect) {
//		this.defect = defect;
//	}
//
//	/*
//	 * Getter for currentChoice
//	 */
//	public String getCurrentChoice() {
//		return currentChoice;
//	}
//
//	/*
//	 * Setter for currentChoice
//	 */
//	public void setCurrentChoice(String currentChoice) {
//		this.currentChoice = currentChoice;
//	}
//
//	/*
//	 * Getter for lastChoice
//	 */
//	public String getLastChoice() {
//		return lastChoice;
//	}
//
//	/*
//	 * Setter for lastChoice
//	 */
//	public void setLastChoice(String lastChoice) {
//		this.lastChoice = lastChoice;
//	}
}
