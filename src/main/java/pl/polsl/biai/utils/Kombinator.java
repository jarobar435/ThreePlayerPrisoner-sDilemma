package pl.polsl.biai.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Kombinator {

    ArrayList<Character> arr = new ArrayList<Character>();

    public Kombinator() {
        arr.addAll(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'));
        int combElemAmount = 3;
        int arraySize = arr.size();
        ArrayList<Character> temp = new ArrayList<Character>();
        kombinujRekursywnie(arr, temp, 0, arraySize - 1, 0, combElemAmount);
    }

    public void kombinujRekursywnie(ArrayList<Character> arr, ArrayList<Character> temp, int start, int end,
                                    int index, int arraySize) {
        if (index == arraySize) {
            //tutaj tworzę grę
            for (int j = 0; j < arraySize; j++)
                System.out.print(temp.get(j)); //tutaj dodaję zawodników do gier
            System.out.println();
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= arraySize - index; i++) {
            temp.add(index, arr.get(i));
            kombinujRekursywnie(arr, temp, i + 1, end, index + 1, arraySize);
        }
    }
}
