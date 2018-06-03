package pl.polsl.biai.models;

import pl.polsl.biai.controllers.PrisonerController;

import java.util.*;

public class Game {

    private ArrayList<PrisonerController> gameMembers = new ArrayList<PrisonerController>(3);

    public void addGameMember(PrisonerController gameMember) {
        gameMembers.add(gameMember);
    }

    public ArrayList<PrisonerController> getGameMembers() {
        return gameMembers;
    }

//	public void proceedGames( ) {
//
//		// How many times the program will loop
//		int numRepeats = 1000;
//
//		// Declares random number generator and random variables
//		Random rand = new Random();
//		double randFirst, randConform, randDefect;
//
//		for (int i = 0; i < population.length; i++) {
//			randFirst = rand.nextDouble();
//			randConform = rand.nextDouble();
//			randDefect = rand.nextDouble();
//
//			// Initializes each instance of agents with random numbers
//			population[i] = new Prisoner(randFirst, randConform, randDefect);
//		}
//
//		// Loops through the routine 1000 times
//		for (int k = 0; k < numRepeats; k++) {
//			// Plays each agent against each other
//			for (int i = 0; i < population.length; i++) {
//				for (int j = 0; j < population.length; j++) {
//					if (i != j)
//						population[i].play(population[j]);
//				}
//			}
//
//			// Sorts the agents from highest to lowest points
//			sortAgents(population);
//
//			if (k < numRepeats - 1) {
//				// Mutates agents index 0-49 into 50-100
//				for (int i = 0; i < population.length / 2; i++)
//					population[i].mutate(population[i + 50]);
//
//				// Resets all agents' points to 0
//				for (int i = 0; i < population.length; i++)
//					population[i].setPoints(0);
//			}
//
//		}
//
//		// System.out.println(population[0].getFirstMove() + "\t" +
//		// population[0].getConform() + "\t" + population[0].getDefect());
//		// System.out.println(population[50].getFirstMove() + "\t" +
//		// population[50].getConform() + "\t" + population[50].getDefect());
//
//		// Outputs each agent's percentages and points
//		for (int i = 0; i < population.length; i++) {
//			System.out.println("Percentages for agent " + i + ":");
//			System.out.println(population[i].getFirstMove() + "\t"
//					+ population[i].getConform() + "\t" + population[i].getDefect());
//			System.out.println("Points:");
//			System.out.println(population[i].getPoints());
//			System.out.println("");
//		}
//	}
//
//	/*
//	 * sortAgents - Takes in an array of Agents and sorts them
//	 * according to points. tab[0] carries the agent with the most points.
//	 */
//	public static void sortAgents(Prisoner[] toSort) {
//		double x = 0, y = 0, z = 0;
//		Prisoner temp = new Prisoner(x, y, z);
//
//		// Loops through the pl.polsl.biai.models.Prisoner array
//		for (int i = 0; i < toSort.length; i++) {
//			for (int j = 0; j < toSort.length && j != i; j++) {
//				// If an pl.polsl.biai.models.Prisoner has more points than previous, switch them
//				if (toSort[i].getPoints() > toSort[j].getPoints()) {
//					temp = toSort[i];
//					toSort[i] = toSort[j];
//					toSort[j] = temp;
//				}
//			}
//		}
//	}
}
