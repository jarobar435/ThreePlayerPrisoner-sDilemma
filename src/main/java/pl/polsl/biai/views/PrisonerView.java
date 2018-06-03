package pl.polsl.biai.views;

import pl.polsl.biai.models.Decision;

import java.util.ArrayList;

public class PrisonerView {

    //only fast-check purpose
    public void printLastMoves(ArrayList<Decision> arr) {
        System.out.println("Move 1:");
        for(int i = 1; i <= arr.size(); ++i) {
            System.out.print("Prisoner " + i + ": " + arr.get(i-1) + ", ");
            if(i%3==0) {
                System.out.println("\nMove " + (i/3+1) + ":");
            }
        }
        System.out.println();
    }
}
