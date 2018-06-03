package pl.polsl.biai.views;

import pl.polsl.biai.controllers.PrisonerController;
import pl.polsl.biai.models.Decision;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EvolutionView {

    public void printGenerationStamp(int generation) {
        System.out.println(
                "\nGeneration " + generation + ":"
        );
    }

    public void confirmPopulationGenerated(int populationSize) {
        System.out.println(
                "Successfully created population of " + populationSize + " prisoners."
        );
    }

    public void confirmGamePairsCreated(int pairsAmount) {
        System.out.println(
                "Successfully created " + pairsAmount + " game pairs."
        );
    }

    public void confirmGamesCompleted(int gamesAmount) {
        System.out.println(
                "Successfully completed " + gamesAmount + " games."
        );
    }

    public void showScoresAfterGeneration(ArrayList<PrisonerController> prisonerControllers) {
        for(int i = 0; i < prisonerControllers.size(); ++i) {
            System.out.println(
                "Prisoner " + (i + 1) + " avg. score: " + prisonerControllers.get(i).getPrisoner().getScore());
        }
    }
}
