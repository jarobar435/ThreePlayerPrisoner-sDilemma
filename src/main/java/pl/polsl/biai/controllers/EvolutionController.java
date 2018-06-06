package pl.polsl.biai.controllers;

import pl.polsl.biai.models.Decision;
import pl.polsl.biai.models.Evolution;
import pl.polsl.biai.models.Prisoner;
import pl.polsl.biai.views.EvolutionView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class EvolutionController {

    private final Scanner scanner = new Scanner(System.in);
    private static final SecureRandom secureRandom = new SecureRandom();

    private final Evolution evolution = new Evolution();
    private final EvolutionView evolutionView = new EvolutionView();
    private DuelController duelController = new DuelController();

    private int populationSize;
    private int gamesInPair;
    private int generationsAmount;
    private int crossoverType;
    private int mutationMode;
    private int duelMode;

    void runEvolution() {
        generatePopulation(populationSize);
        evolutionView.confirmPopulationGenerated(populationSize);
        preDuelMode();
        for (int i = 0; i < generationsAmount; ++i) {
            evolutionView.printGenerationStamp(i + 1);
            createGames();
            evolutionView.confirmGamePairsCreated(evolution.getGames().size());
            playGames();
            evolutionView.confirmGamesCompleted(evolution.getGames().size() * gamesInPair);
            clearGamesList();
            updateScores();
            evolutionView.showScoresAfterGeneration(evolution.getPopulation());
            printOverallScore();
            sortPopulationDescendingOrder();
            determineCrossoverPartnersAmount();
            runCrossover();
            runMutation();
        }
        postDuelMode();
    }

    private void runMutation() {
        if (mutationMode == 1) {
            for (PrisonerController prisonerController : evolution.getPopulation()) {
                if (secureRandom.nextInt(100) == 0) {
                    prisonerController.changeGene(secureRandom.nextInt(521));
                }
            }
        }
    }

    private void runCrossover() {
        ArrayList<PrisonerController> nextGeneration = new ArrayList<PrisonerController>(populationSize);
        for (int j = 0; j < populationSize; ++j) {
            nextGeneration.add(
                    crossover(evolution.getPopulation().get(j),
                            randomlySelectCrossoverPartner(j))
            );
            evolution.getPopulation().get(j).decrementPrisonerCrossoverPartnersAmount();
        }
        evolution.getPopulation().clear();
        evolution.setPopulation(nextGeneration);
    }

    private PrisonerController randomlySelectCrossoverPartner(int secondParent) {
        PrisonerController randomPrisoner = new PrisonerController();
        while (!randomPrisoner.checkIfWaitingForCrossoverPartner() && randomPrisoner != evolution.getPopulation().get(secondParent)) {
            randomPrisoner = evolution.getPopulation().get(secureRandom.nextInt(evolution.getPopulation().size()));
        }
        return randomPrisoner;
    }

    private PrisonerController crossover(PrisonerController firstPrisoner, PrisonerController secondPrisoner) {
        switch (crossoverType) {
            case 1:
                return multiPointCrossover(firstPrisoner, secondPrisoner);
            case 0:
            default:
                return onePointCrossover(firstPrisoner, secondPrisoner);
        }
    }

    private PrisonerController onePointCrossover(PrisonerController firstPrisoner, PrisonerController secondPrisoner) {
        PrisonerController child = new PrisonerController();
        child.injectToChromosome(new ArrayList<Decision>(firstPrisoner.getChromosome().subList(0, 261)), 0);
        child.injectToChromosome(new ArrayList<Decision>(secondPrisoner.getChromosome().subList(261, 521)), 261);
        return child;
    }

    //TODO:
    //change this method to use additional param - pointsAmount
    private PrisonerController multiPointCrossover(PrisonerController firstPrisoner, PrisonerController secondPrisoner) {
        PrisonerController child = new PrisonerController();
        for (int i = 0; i < 51; ++i) {
            child.injectToChromosome(new ArrayList<Decision>(firstPrisoner.getChromosome().subList(i * 10, (i + 1) * 10)), i * 10);
            child.injectToChromosome(new ArrayList<Decision>(secondPrisoner.getChromosome().subList((i + 1) * 10, (i + 2) * 10)), (i + 1) * 10);
        }
        //521 is a prime number - loop setting only indexes from 0 to 519; so there is setting the last one
        child.injectToChromosome(new ArrayList<Decision>(firstPrisoner.getChromosome().subList(520, 521)), 520);
        return child;
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
            PrisonerController newPrisoner = new PrisonerController();
            newPrisoner.generateLastThreeMoves();
            newPrisoner.generateStrategy();
            evolution.addToPopulation(newPrisoner);
        }
    }

    //this can be simplified by using global fields like populationSize
    private void createGames() {
        int combElemAmount = 3;
        ArrayList<PrisonerController> temp = new ArrayList<PrisonerController>();
        pairRecursively(evolution.getPopulation(), temp, 0, 0, combElemAmount);
    }

    private void pairRecursively(ArrayList<PrisonerController> population, ArrayList<PrisonerController> temp, int start,
                                 int index, int combElemAmount) {
        if (index == combElemAmount) {
            evolution.addGame(new GameController());
            for (int i = 0; i < combElemAmount; ++i)
                evolution.getGames().get(evolution.getGames().size() - 1)
                        .addGameMember(temp.get(i));
            return;
        }

        for (int i = start; i <= populationSize - 1 && populationSize - i >= combElemAmount - index; ++i) {
            temp.add(index, population.get(i));
            pairRecursively(population, temp, i + 1, index + 1, combElemAmount);
        }
    }

    private void playGames() {
        for (int j = 0; j < evolution.getGames().size(); ++j) {
            for (int i = 0; i < gamesInPair; ++i) {
                System.out.println("PAIR " + (j + 1) + " - " + "GAME " + (i + 1));
                evolution.getGames().get(j).playGameRound();
            }
        }
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

    private void sortPopulationDescendingOrder() {
        ArrayList<PrisonerController> population = evolution.getPopulation();
        Collections.sort(population);
        evolution.setPopulation(population);
    }

    private void determineCrossoverPartnersAmount() {
        int i;
        for (i = 0; i < Math.round(populationSize / 3.0); ++i) {
            evolution.getPopulation().get(i).setPrisonerCrossoverPartnersAmount(2);
        }
        for (int j = 2 * i; j < populationSize; ++j, ++i) {
            evolution.getPopulation().get(i).setPrisonerCrossoverPartnersAmount(1);
        }
    }

    private void preDuelMode() {
        if (duelMode == 1) {
            PrisonerController user = new PrisonerController();
            duelController.getDuelView().printDuelModeHeader();
            addDuelMembers(user);
            duelController.getDuelView().printPreEvolutionDuelSettings(gamesInPair);
            for (int i = 0; i < gamesInPair; ++i) {
                duelController.getDuelView().printNextMoveEnquiry();
                duelController.getGameController().playGameRoundWithUser(Decision.values()[scanner.nextInt()]);
            }
            duelController.updateDuelScores(gamesInPair);
            duelController.printDuelScores();
            duelController.resetPopulationScores();
            scanner.next();
        }
    }

    private void postDuelMode() {
        if (duelMode == 1) {
            PrisonerController user = new PrisonerController();
            duelController.getDuelView().printDuelModeHeader();
            addDuelMembers(user);
            duelController.getDuelView().printPostEvolutionDuelSettings(gamesInPair);
            for (int i = 0; i < gamesInPair; ++i) {
                duelController.getDuelView().printNextMoveEnquiry();
                duelController.getGameController().playGameRoundWithUser(Decision.values()[scanner.nextInt()]);
            }
            duelController.updateDuelScores(gamesInPair);
            duelController.printDuelScores();
            duelController.resetPopulationScores();
            scanner.next();
        }
    }

    private void addDuelMembers(PrisonerController user) {
        user.generateLastThreeMoves();
        duelController.getGameController().addGameMember(user);
        for (int i = 0; i < 2; ++i) {
            duelController.getGameController().addGameMember(evolution.getPopulation().get(i));
        }
    }

    private void printOverallScore() {
        double overallScore = 0;
        for(PrisonerController prisonerController : evolution.getPopulation()) {
            overallScore += prisonerController.getPrisoner().getScore();
        }
        System.out.println("OVERALL AVG SCORE: " + overallScore/populationSize + ".");
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
