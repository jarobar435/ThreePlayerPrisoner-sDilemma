package pl.polsl.biai.controllers;

import pl.polsl.biai.models.Evolution;
import pl.polsl.biai.models.Prisoner;
import pl.polsl.biai.views.EvolutionView;

import java.lang.reflect.Array;
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
            evolutionView.printGenerationStamp(i + 1);
            createGames();
            evolutionView.confirmGamePairsCreated(evolution.getGames().size());
            playGames();
            evolutionView.confirmGamesCompleted(evolution.getGames().size() * gamesInPair);
            clearGamesList();
            updateScores();
            evolutionView.showScoresAfterGeneration(evolution.getPopulation());
            determineCrossoverPartnersAmountAndSortPopulation();

            //krzyżowanie, mutacja
            ArrayList<PrisonerController> nextGeneration = new ArrayList<PrisonerController>(populationSize);
            for (int j = 0; j < populationSize; ) {
//              nextGeneration.add(
//                        crossover(evolution.getPopulation().get(i),
//                                randomlySelectCrossoverPartner(),
//                                crossoverType,
//                                mutationMode)
//              );
                evolution.getPopulation().get(j).decrementPrisonerCrossoverPartnersAmount();
                if (!(evolution.getPopulation().get(j).checkIfWaitingForCrossoverPartner())) {
                    ++j;
                }
            }
            evolution.getPopulation().clear();
            evolution.setPopulation(nextGeneration);
        }
    }

//PrisonerController middlePrisoner = population.get(population.size() / 2);
//double deviationPlayer = pow(middlePrisoner.getPrisoner().getScore() - calculateAverageOfPopulationScore(population),2);
//double StandardDeviation = calculateStandardDeviation(population);

//    private double calculateStandardDeviation(ArrayList<PrisonerController> population) {
//        double average = calculateAverageOfPopulationScore(population);
//        double sum = 0;
//        for (PrisonerController iterator : population) {
//            sum += pow(iterator.getPrisoner().getScore() - average, 2);
//        }
//        return sqrt(sum / population.size());
//    }
//
//    private double calculateAverageOfPopulationScore(ArrayList<PrisonerController> population) {
//        int sum = 0;
//        for (PrisonerController iterator : population) {
//            sum += iterator.getPrisoner().getScore();
//        }
//        return sum / population.size();
//    }

    private void generatePopulation(int populationSize) {
        for (int i = 0; i < populationSize; ++i) {
            evolution.addToPopulation(new PrisonerController());
        }
    }

    private void createGames() {
        int combElemAmount = 3;
        int arraySize = evolution.getPopulation().size();
        ArrayList<PrisonerController> temp = new ArrayList<PrisonerController>();
        pairRecursively(evolution.getPopulation(), temp, 0, arraySize - 1, 0, combElemAmount);
    }

    private void pairRecursively(ArrayList<PrisonerController> population, ArrayList<PrisonerController> temp, int start, int end,
                                 int index, int combElemAmount) {
        if (index == combElemAmount) {
            evolution.addGame(new GameController());
            for (int i = 0; i < combElemAmount; ++i)
                evolution.getGames().get(evolution.getGames().size() - 1)     //getting last added game
                        .addGameMember(temp.get(i));    //adding members to it
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= combElemAmount - index; ++i) {
            temp.add(index, population.get(i));
            pairRecursively(population, temp, i + 1, end, index + 1, combElemAmount);
        }
    }

    private void playGames() {

        for (int j = 0; j < evolution.getGames().size(); ++j) {
            for (int i = 0; i < gamesInPair; ++i) {
                System.out.println("PAIR " + (j + 1) + " - " + "GAME " + (i + 1));
                evolution.getGames().get(j).playGameRound();
            }
        }
//            for (GameController gameController : evolution.getGames()) {
//                gameController.playGameRound();
//            }
    }

    private void clearGamesList() {
        evolution.getGames().clear();
    }

    private void updateScores() {
        for (PrisonerController prisonerController : evolution.getPopulation()) {
            prisonerController.updateScore(calculateAmountOfPairsWithEachPlayer() * gamesInPair);
        }
    }

    private int calculateAmountOfPairsWithEachPlayer() {
        return (populationSize - 2) * (populationSize - 1) / 2;
    }

    private void determineCrossoverPartnersAmountAndSortPopulation() {
        ArrayList<PrisonerController> population = evolution.getPopulation();
        Collections.sort(population);
        int i;
        for (i = 0; i < Math.round(populationSize / 3.0); ++i) {
            population.get(i).setPrisonerCrossoverPartnersAmount(2);
        }
        for (int j = 2 * i; j < populationSize; ++j) {
            population.get(j).setPrisonerCrossoverPartnersAmount(1);
        }
        evolution.setPopulation(population);
    }

    void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    void setGamesInPair(int gamesInPair) {
        this.gamesInPair = gamesInPair;
    }

    void setGenerationsAmount(int generationsAmount) {
        this.generationsAmount = generationsAmount;
    }

    void setCrossoverType(int crossoverType) {
        this.crossoverType = crossoverType;
    }

    void setMutationMode(int mutationMode) {
        this.mutationMode = mutationMode;
    }

    void setDuelMode(int duelMode) {
        this.duelMode = duelMode;
    }
}
