package pl.polsl.biai.controllers;

import pl.polsl.biai.models.Evolution;
import pl.polsl.biai.views.EvolutionView;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class EvolutionController {

    private final Evolution evolution = new Evolution();
    private final EvolutionView evolutionView = new EvolutionView();

    private int populationSize;
    private int gamesInPair;
    private int generationsAmount;
    private int crossoverType;
    private int mutationMode;
    private int duelMode;

    public void beginEvolution() {
        generatePopulation(populationSize);
        evolutionView.confirmPopulationGenerated(populationSize);
        for (int i = 0; i < generationsAmount; ++i) {
            createGames();
            evolutionView.confirmGamesCreated(evolution.getGames().size());
            playGames();
            updateScores();

            //TODO:
            //sortowanie, znalezienie środkowego, podzielenie arraya na 2 grupy odchyleń standardowych

            ArrayList<PrisonerController> population = evolution.getPopulation();
            Collections.sort(population);
            PrisonerController middlePrisoner = population.get(population.size() / 2);
//            double deviationPlayer = pow(middlePrisoner.getPrisoner().getScore() - calculateAverageOfPopulationScore(population),2);
            double StandardDeviation = calculateStandardDeviation(population);

            //krzyżowanie, mutacja

            //evolution.getGames().clear();
            //evolution.getPopulation().clear();
        }
    }

    private double calculateStandardDeviation(ArrayList<PrisonerController> population) {
        double average = calculateAverageOfPopulationScore(population);
        double sum = 0;
        for (PrisonerController iterator : population) {
            sum += pow(iterator.getPrisoner().getScore() - average, 2);
        }
        return sqrt(sum / population.size());
    }

    private double calculateAverageOfPopulationScore(ArrayList<PrisonerController> population) {
        int sum = 0;
        for (PrisonerController iterator : population) {
            sum += iterator.getPrisoner().getScore();
        }
        return sum / population.size();
    }

    public void generatePopulation(int populationSize) {
        for (int i = 0; i < populationSize; ++i) {
            evolution.addToPopulation(new PrisonerController());
        }
    }

    public void createGames() {
        int combElemAmount = 3;
        int arraySize = evolution.getPopulation().size();
        ArrayList<PrisonerController> temp = new ArrayList<PrisonerController>();
        pairRecursively(evolution.getPopulation(), temp, 0, arraySize - 1, 0, combElemAmount);
    }

    public void pairRecursively(ArrayList<PrisonerController> population, ArrayList<PrisonerController> temp, int start, int end,
                                int index, int combElemAmount) {
        if (index == combElemAmount) {
            evolution.addGame(new GameController());
            for (int i = 0; i < combElemAmount; ++i)
                evolution.getGames().get(evolution.getGames().size() - 1)     //getting last added game
                        .addGameMember(population.get(i));    //adding members to it
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= combElemAmount - index; ++i) {
            temp.add(index, population.get(i));
            pairRecursively(population, temp, i + 1, end, index + 1, combElemAmount);
        }
    }

    public void playGames() {
        for (int i = 0; i < gamesInPair; ++i) {
            for (GameController gameController : evolution.getGames()) {
                gameController.playGameRound();
            }
        }
    }

    public void updateScores() {
        for (PrisonerController prisonerController : evolution.getPopulation()) {
            prisonerController.updateScore(calculateAmountOfPairsWithEachPlayer() * gamesInPair);
            System.out.println("Score: " + prisonerController.getPrisoner().getScore());
        }
    }

    public int calculateAmountOfPairsWithEachPlayer() {
        return ((populationSize -2) * (populationSize - 1) / 2);
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public void setGamesInPair(int gamesInPair) {
        this.gamesInPair = gamesInPair;
    }

    public void setGenerationsAmount(int generationsAmount) {
        this.generationsAmount = generationsAmount;
    }

    public void setCrossoverType(int crossoverType) {
        this.crossoverType = crossoverType;
    }

    public void setMutationMode(int mutationMode) {
        this.mutationMode = mutationMode;
    }

    public void setDuelMode(int duelMode) {
        this.duelMode = duelMode;
    }
}
