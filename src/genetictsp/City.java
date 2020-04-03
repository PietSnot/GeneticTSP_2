/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetictsp;

import java.util.List;

/**
 *
 * @author Piet A simple abstraction of a city. This class maintains Cartesian
 * coordinates and also knows the Pythagorean theorem.
 *
 * @author bkanber
 *
 */
public class City {
    int index;
    String name;
    double[] distances;
    double distanceTo;

    public City(int index, String name, List<String> distanceData, int startIndex) {
        this.index = index;
        this.name = name;
        distanceTo = parseDistance(distanceData.get(startIndex));
        distanceData.remove(startIndex);
        processDistances(distanceData);
    }
    
    private void processDistances(List<String> distanceData) {
        distances = new double[distanceData.size()];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = parseDistance(distanceData.get(i));
        }
    }

    /**
     * Calculate distance from another city
     * @param city The city to calculate the distance from
     * @return distance The distance from the given city
     */
    public double distanceFrom(City city) {
        return distances[city.index];
    }
    
    public static double parseDistance(String d) {
        String[] e = d.split("\\s+");
        if (e[1].equalsIgnoreCase("km")) return Double.parseDouble(e[0]);
        else return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
