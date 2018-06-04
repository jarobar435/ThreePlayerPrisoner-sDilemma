package pl.polsl.biai.views;

public class MenuView {

    public void printHello() {
        System.out.println(
                "Welcome to Three-Player Prisoners Dilemma project.\n" +
                        "Please set up the following parameters."
        );
    }

    public void printPopulationSizeQuestion() {
        System.out.println(
                "Population size: "
        );
    }

    public void printGamesInPairQuestion() {
        System.out.println(
                "How many games in each pair: "
        );
    }

    public void printGenerationsAmountQuestion() {
        System.out.println(
                "Generations: "
        );
    }

    public void printCrossoverTypeQuestion() {
        System.out.println(
                "Crossover type: (0 - single point [middle], 1 - multi point [once per 10 genes])"
        );
    }

    public void printMutationsModeQuestion() {
        System.out.println(
                "Mutations (0 - Off, 1 - On): "
        );
    }

    public void printDuelQuestion() {
        System.out.println(
                "Want to play vs algorithm at the beginning and after learning? (0 - No, 1 - Yes) "
        );
    }

    public void printDataConfirmation() {
        System.out.println(
                "I've received all needed data. Proceeding."
        );
    }
}
