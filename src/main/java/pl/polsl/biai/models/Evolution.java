package pl.polsl.biai.models;

import pl.polsl.biai.controllers.GameController;
import pl.polsl.biai.controllers.PrisonerController;

import java.util.ArrayList;

public class Evolution {

    private ArrayList<PrisonerController> population = new ArrayList<PrisonerController>();
    private ArrayList<GameController> games = new ArrayList<GameController>();

    public void addToPopulation(PrisonerController prisonerController) {
        population.add(prisonerController);
    }

    public void addGame(GameController gameController) {
        games.add(gameController);
    }

    public ArrayList<PrisonerController> getPopulation() {
        return population;
    }

    public ArrayList<GameController> getGames() {
        return games;
    }
}
