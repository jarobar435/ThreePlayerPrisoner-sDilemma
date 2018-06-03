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
}
