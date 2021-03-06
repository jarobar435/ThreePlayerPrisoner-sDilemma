package pl.polsl.biai.models;

import java.util.ArrayList;

public class Prisoner {

    private ArrayList<Decision> lastThreeMoves = new ArrayList<Decision>(9);
    private ArrayList<Decision> strategy = new ArrayList<Decision>(512);

    private double score = 0;
    private int crossoverPartnersAmount = 0;

    public ArrayList<Decision> getLastThreeMoves() {
        return lastThreeMoves;
    }

    public void setLastThreeMoves(ArrayList<Decision> lastThreeMoves) {
        this.lastThreeMoves = lastThreeMoves;
    }

    public ArrayList<Decision> getStrategy() {
        return strategy;
    }

    public void setStrategy(ArrayList<Decision> strategy) {
        this.strategy = strategy;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getCrossoverPartnersAmount() {
        return crossoverPartnersAmount;
    }

    public void setCrossoverPartnersAmount(int crossoverPartnersAmount) {
        this.crossoverPartnersAmount = crossoverPartnersAmount;
    }
}
