package pl.polsl.biai.views;

public class EvolutionView {

    public void confirmPopulationGenerated(int populationSize) {
        System.out.println(
                "Successfully created population of " + populationSize + " prisoners."
        );
    }

    public void confirmGamesCreated(int gamesAmount) {
        System.out.println(
                "Successfully created " + gamesAmount + " games."
        );
    }
}
