package pl.polsl.biai.views;

public class DuelView {

    public void printDuelModeHeader() {
        System.out.println(
                "\n-------------------\n" +
                        "DUEL MODE ACTIVATED\n" +
                        "-------------------\n"
        );
    }

    public void printPreEvolutionDuelSettings(int gamesAmount) {
        System.out.println(
                "Playing " + gamesAmount + " games vs opponents with random strategy."
        );
    }

    public void printPostEvolutionDuelSettings(int gamesAmount) {
        System.out.println(
                "Playing " + gamesAmount + " games vs opponents with genetically improved strategy."
        );
    }

    public void printNextMoveEnquiry() {
        System.out.println(
                "Please enter your next move: (0 - Betray, 1 - Collaborate)"
        );
    }

    public void printDuelScore(double user, double secondPlayer, double thirdPlayer) {
        System.out.println(
                "Results: \n" +
                        "You scored: " + user + "\n" +
                        "Player 2 scored: " + secondPlayer + "\n" +
                        "Player 3 scored: " + thirdPlayer + "\n"
        );
    }

}
