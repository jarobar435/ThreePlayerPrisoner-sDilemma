package pl.polsl.biai.controllers;

import pl.polsl.biai.views.MenuView;

import java.util.Scanner;

public class MenuController {

    private final Scanner scanner = new Scanner(System.in);

    private final MenuView menuView = new MenuView();

    private final EvolutionController evolutionController = new EvolutionController();

    public MenuController() {
        gatherConfigParameters();
    }

    private void gatherConfigParameters() {
        menuView.printHello();
        //TODO:
        //secure inputs in case of invalid data
        menuView.printPopulationSizeQuestion();
        evolutionController.setPopulationSize(scanner.nextInt());
        menuView.printGamesInPairQuestion();
        evolutionController.setGamesInPair(scanner.nextInt());
        menuView.printGenerationsAmountQuestion();
        evolutionController.setGenerationsAmount(scanner.nextInt());
        menuView.printCrossoverTypeQuestion();
        evolutionController.setCrossoverType(scanner.nextInt());
        menuView.printMutationsModeQuestion();
        evolutionController.setMutationMode(scanner.nextInt());
        menuView.printDuelQuestion();
        evolutionController.setDuelMode(scanner.nextInt());
        menuView.printDataConfirmation();
        evolutionController.runEvolution();
    }
}
