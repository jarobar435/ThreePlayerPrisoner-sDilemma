package pl.polsl.biai.controllers;

import pl.polsl.biai.models.Decision;
import pl.polsl.biai.models.Prisoner;
import pl.polsl.biai.views.PrisonerView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

import static pl.polsl.biai.models.Decision.BETRAYED;
import static pl.polsl.biai.models.Decision.COLLABORATED;

public class PrisonerController implements Comparable<PrisonerController> {

    private static final SecureRandom secureRandom = new SecureRandom();

    private final PrisonerView prisonerView = new PrisonerView();

    private Prisoner prisoner = new Prisoner();

    void generateLastThreeMoves() {
        generateRandomDecisions(prisoner.getLastThreeMoves(), 9);
    }

    void generateStrategy() {
        generateRandomDecisions(prisoner.getStrategy(), 512);
    }

    private void generateRandomDecisions(ArrayList<Decision> arr, int amount) {
        for (int i = 0; i < amount; ++i) {
            arr.add(Decision.values()[secureRandom.nextInt(Decision.values().length)]);
        }
    }

    public Prisoner getPrisoner() {
        return prisoner;
    }

    Decision determineDecision() {
        int indexStep = 512;
        int indexOfDecisionTop = 511;
        int indexOfDecisionBottom = 0;
        for (int i = 0; i < 9; ++i) {
            indexStep /= 2;
            if (i == 8) {
                if (prisoner.getLastThreeMoves().get(8) == COLLABORATED)
                    return prisoner.getStrategy().get(indexOfDecisionTop);
                else
                    return prisoner.getStrategy().get(indexOfDecisionBottom);
            }
            if (prisoner.getLastThreeMoves().get(i) == COLLABORATED)
                indexOfDecisionBottom += indexStep;
            else
                indexOfDecisionTop -= indexStep;

        }
        return BETRAYED; //never reached
    }

    void addMove(Decision playerOne, Decision playerTwo, Decision playerThree) {
        ArrayList<Decision> newList = new ArrayList<Decision>(3);
        newList.addAll(prisoner.getLastThreeMoves().subList(3, 9));
        newList.addAll(Arrays.asList(playerOne, playerTwo, playerThree));
        prisoner.setLastThreeMoves(newList);
    }

    void addScore(double score) {
        prisoner.setScore(prisoner.getScore() + score);
    }

    void updateScore(int divider) {
        prisoner.setScore(prisoner.getScore() / divider);
    }

    void setPrisonerCrossoverPartnersAmount(int partnersAmount) {
        prisoner.setCrossoverPartnersAmount(partnersAmount);
    }

    boolean checkIfWaitingForCrossoverPartner() {
        return (prisoner.getCrossoverPartnersAmount() > 0);
    }

    void decrementPrisonerCrossoverPartnersAmount() {
        prisoner.setCrossoverPartnersAmount(prisoner.getCrossoverPartnersAmount() - 1);
    }

    public int compareTo(PrisonerController prisonerController) {
        if (prisoner.getScore() < prisonerController.getPrisoner().getScore())
            return 1;
        else if (prisoner.getScore() > prisonerController.getPrisoner().getScore())
            return -1;
        return 0;
    }

    ArrayList<Decision> getChromosome() {
        ArrayList<Decision> chromosome = new ArrayList<>(521);
        chromosome.addAll(prisoner.getLastThreeMoves());
        chromosome.addAll(prisoner.getStrategy());
        return chromosome;
    }

    private void setChromosome(ArrayList<Decision> chromosome) {
        prisoner.setLastThreeMoves(new ArrayList<Decision>(chromosome.subList(0, 9)));
        prisoner.setStrategy(new ArrayList<Decision>(chromosome.subList(9, chromosome.size())));
    }

    void injectToChromosome(ArrayList<Decision> chromosomeFragment, int fromIndex) {
        ArrayList<Decision> chromosome = getChromosome();
        chromosome.addAll(fromIndex, chromosomeFragment);
        setChromosome(chromosome);
    }

    void changeGene(int genIndex) {
        if(getGene(genIndex) == BETRAYED) {
            injectToChromosome(new ArrayList<Decision>(Arrays.asList(COLLABORATED)), genIndex);
        }else {
            injectToChromosome(new ArrayList<Decision>(Arrays.asList(BETRAYED)), genIndex);
        }
    }

    private Decision getGene(int genIndex) {
        return getChromosome().get(genIndex);
    }
}
