package pl.polsl.biai.controllers;

import pl.polsl.biai.models.Decision;
import pl.polsl.biai.models.Game;
import pl.polsl.biai.views.GameView;

public class GameController {

    private Game game = new Game();
    private GameView gameView = new GameView();

    void addGameMember(PrisonerController gameMember) {
        game.addGameMember(gameMember);
    }

    void playGameRound() {
        if(game.getGameMembers().get(0).determineDecision() == Decision.BETRAYED) {
            System.out.println("Player1: betrayed");
            if(game.getGameMembers().get(1).determineDecision() == Decision.BETRAYED){
                System.out.println("Player2: betrayed");
                if(game.getGameMembers().get(2).determineDecision() == Decision.BETRAYED){
                    System.out.println("Player3: betrayed");
                    setScores(8, 8, 8);
                    setMoves(Decision.BETRAYED,Decision.BETRAYED, Decision.BETRAYED);
                }else {
                    System.out.println("Player3: collaborated");
                    setScores(6, 6, 12);
                    setMoves(Decision.BETRAYED,Decision.BETRAYED, Decision.COLLABORATED);
                }
            }else {
                System.out.println("Player2: collaborated");
                if(game.getGameMembers().get(2).determineDecision() == Decision.BETRAYED){
                    System.out.println("Player3: betrayed");
                    setScores(6, 12, 6);
                    setMoves(Decision.BETRAYED,Decision.COLLABORATED, Decision.BETRAYED);
                }else {
                    System.out.println("Player3: collaborated");
                    setScores(0, 12, 12);
                    setMoves(Decision.BETRAYED,Decision.COLLABORATED, Decision.COLLABORATED);
                }
            }
        }
        else {
            System.out.println("Player1: collaborated");
            if(game.getGameMembers().get(1).determineDecision() == Decision.BETRAYED){
                System.out.println("Player2: betrayed");
                if(game.getGameMembers().get(2).determineDecision() == Decision.BETRAYED){
                    System.out.println("Player3: betrayed");
                    setScores(12, 6, 6);
                    setMoves(Decision.COLLABORATED,Decision.BETRAYED, Decision.BETRAYED);
                }else {
                    System.out.println("Player3: collaborated");
                    setScores(12, 0, 12);
                    setMoves(Decision.COLLABORATED,Decision.BETRAYED, Decision.COLLABORATED);
                }
            }else {
                System.out.println("Player2: collaborated");
                if(game.getGameMembers().get(2).determineDecision() == Decision.BETRAYED){
                    System.out.println("Player3: betrayed");
                    setScores(12, 12, 0);
                    setMoves(Decision.COLLABORATED,Decision.COLLABORATED, Decision.BETRAYED);
                }else {
                    System.out.println("Player3: collaborated");
                    setScores(4, 4, 4);
                    setMoves(Decision.COLLABORATED,Decision.COLLABORATED, Decision.COLLABORATED);
                }
            }
        }
    }

    private void setScores(int playerOneScore, int playerTwoScore, int playerThreeScore) {
        game.getGameMembers().get(0).addScore(playerOneScore);
        game.getGameMembers().get(1).addScore(playerTwoScore);
        game.getGameMembers().get(2).addScore(playerThreeScore);
    }

    //TODO:
    //ustawiam tutaj ruchy każdemu w tej samej kolejności
    //warto potestować, czy może lepsze wyniki będą jeśli zawsze to pierwszy wynik dotyczyć będzie playera
    private void setMoves(Decision playerOneDecision, Decision playerTwoDecision, Decision playerThreeDecision) {
        game.getGameMembers().get(0).addMove(playerOneDecision, playerTwoDecision, playerThreeDecision);
        game.getGameMembers().get(1).addMove(playerOneDecision, playerTwoDecision, playerThreeDecision);
        game.getGameMembers().get(2).addMove(playerOneDecision, playerTwoDecision, playerThreeDecision);
    }
}
