package pl.polsl.biai.models;

import pl.polsl.biai.controllers.PrisonerController;

import java.util.*;

public class Game {

    private ArrayList<PrisonerController> gameMembers = new ArrayList<PrisonerController>(3);

    public void addGameMember(PrisonerController gameMember) {
        gameMembers.add(gameMember);
    }

    public ArrayList<PrisonerController> getGameMembers() {
        return gameMembers;
    }
}
