/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package genetictsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Piet
 */
public class GeneticTSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> cities = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        List<String> distances = new ArrayList<>(Arrays.asList(
            "1 m", "2.0 km", "4.5 km", "3.2 km",
            "2.5 km", "1 m", "4.6 km", "3.3 km",
            "5 km", "6 km", "1 m", "3 km",
            "3 km", "1 km", "3 km", "1 m"
        ));
        int startingIndex = 0;
        TSP.main(cities, distances, startingIndex);
    }
}
