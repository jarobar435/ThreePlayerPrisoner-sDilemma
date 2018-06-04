package pl.polsl.biai.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Kombinator {

    ArrayList<Character> arr = new ArrayList<Character>();
    int arrSize = 6;

    public Kombinator() {
        arr.addAll(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'));
        int combElemAmount = 3;
        ArrayList<Character> temp = new ArrayList<Character>();
        kombinujRekursywnie(arr, temp, 0, 0, combElemAmount);
    }

    public void kombinujRekursywnie(ArrayList<Character> arr, ArrayList<Character> temp, int start,
                                    int index, int arraySize) {
        if (index == arraySize) {
            //tutaj tworzę grę
            for (int j = 0; j < arraySize; j++)
                System.out.print(temp.get(j)); //tutaj dodaję zawodników do gier
            System.out.println();
            return;
        }

        for (int i = start; i <= arrSize-1 && arrSize - i >= arraySize - index; i++) {
            temp.add(index, arr.get(i));
            kombinujRekursywnie(arr, temp, i + 1, index + 1, arraySize);
        }
    }
}
