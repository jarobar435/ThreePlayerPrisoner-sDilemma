package pl.polsl.biai.controllers;

import pl.polsl.biai.models.Decision;
import pl.polsl.biai.models.Prisoner;
import pl.polsl.biai.views.PrisonerView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

import static pl.polsl.biai.models.Decision.BETRAYED;
import static pl.polsl.biai.models.Decision.COLLABORATED;

public class PrisonerController implements Comparable<PrisonerController>{

    private static final SecureRandom secureRandom = new SecureRandom();

    private final PrisonerView prisonerView = new PrisonerView();

    private Prisoner prisoner = new Prisoner();

    public PrisonerController() {
        generateLastThreeMoves();
        generateStrategy();
        //prisonerView.printLastMoves(prisoner.getLastThreeMoves());
    }

    public void generateLastThreeMoves() {
        generateRandomDecisions(prisoner.getLastThreeMoves(), 9);
    }

    public void generateStrategy() {
        generateRandomDecisions(prisoner.getStrategy(), 512);
    }

    public void generateRandomDecisions(ArrayList<Decision> arr, int amount) {
        for(int i = 0; i < amount; ++i) {
            arr.add(Decision.values()[secureRandom.nextInt(Decision.values().length)]);
        }
    }

    public Prisoner getPrisoner() {
        return prisoner;
    }

    public Decision determineDecision() {
        int indexOfDecisionTop = 511;
        int indexOfDecisionBottom = 0;
        for(int i = 0; i < 9; ++i) {
            if(i == 8) {
                if(getPrisoner().getLastThreeMoves().get(8) == COLLABORATED)
                    return getPrisoner().getStrategy().get(indexOfDecisionTop);
                else
                    return getPrisoner().getStrategy().get(indexOfDecisionBottom);
            }
            if(getPrisoner().getLastThreeMoves().get(i) == COLLABORATED)
                indexOfDecisionTop = (indexOfDecisionTop % 2 == 0) ? (indexOfDecisionTop / 2) : (indexOfDecisionTop / 2 + 1);
            else
                indexOfDecisionBottom = (indexOfDecisionBottom % 2 == 0) ? (indexOfDecisionBottom / 2) : (indexOfDecisionBottom / 2 + 1);

        }
        return BETRAYED; //never reached
    }

    public void addMove(Decision playerOne, Decision playerTwo, Decision playerThree) {
        ArrayList<Decision> newList = new ArrayList<Decision>(3);
        newList.addAll(prisoner.getLastThreeMoves().subList(3, 9));
        newList.addAll(Arrays.asList(playerOne, playerTwo, playerThree));
        prisoner.setLastThreeMoves(newList);
    }

    public void addScore(int score) {
        prisoner.addScore(score);
    }

    public void updateScore(int divider) {
        prisoner.setScore(prisoner.getScore() / divider);
    }

    public int compareTo(PrisonerController prisonerController) {
        return prisoner.getScore() - prisonerController.prisoner.getScore();
    }
}
