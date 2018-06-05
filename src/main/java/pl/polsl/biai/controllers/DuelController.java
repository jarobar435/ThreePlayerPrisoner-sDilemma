package pl.polsl.biai.controllers;

import pl.polsl.biai.views.DuelView;

class DuelController {

    private static final DuelView duelView = new DuelView();

    private GameController gameController = new GameController();

    GameController getGameController() {
        return gameController;
    }

    DuelView getDuelView() {
        return duelView;
    }

    void updateDuelScores(int gamesInPair) {
        for (int i = 0; i < 3; ++i)
            gameController.getGame().getGameMembers().get(i).updateScore(gamesInPair);
    }

    void printDuelScores() {
        duelView.printDuelScore(
                gameController.getGame().getGameMembers().get(0).getPrisoner().getScore(),
                gameController.getGame().getGameMembers().get(1).getPrisoner().getScore(),
                gameController.getGame().getGameMembers().get(2).getPrisoner().getScore()
        );
    }

    void resetPopulationScores() {
        gameController.getGame().getGameMembers().get(1).getPrisoner().setScore(0);
        gameController.getGame().getGameMembers().get(2).getPrisoner().setScore(0);
    }

}
