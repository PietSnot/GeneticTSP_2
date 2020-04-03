/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetictsp;

/**
 *
 * @author Piet
 */
public class Route {

    private City[] route;
    private double distance = 0;

    /**
     * Initialize Route
     *
     * @param individual A GA individual
     * @param cities The cities referenced
     */
    public Route(Individual individual, City[] cities) {
        // Get individual's chromosome
        int[] chromosome = individual.getChromosome();
        // Create route
        this.route = new City[cities.length];
        for (int geneIndex = 0; geneIndex < chromosome.length; geneIndex++) {
            this.route[geneIndex] = cities[chromosome[geneIndex]];
        }
    }

    /**
     * Get route distance
     *
     * @return distance The route's distance
     */
    public double getDistance() {
        if (this.distance > 0) {
            return this.distance;
        }

        // Loop over cities in route and calculate route distance
        double totalDistance = 0;
        for (int cityIndex = 0; cityIndex + 1 < this.route.length; cityIndex++) {
            City c1 = this.route[cityIndex];
            City c2 = this.route[cityIndex + 1];
            double d = c1.distanceFrom(c2);
            totalDistance += d;
        }

        totalDistance += TSP.startAndEndCity.distanceFrom(this.route[0]);
        totalDistance += this.route[route.length - 1].distanceTo;
        this.distance = totalDistance;

        return totalDistance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < route.length; i++) {
            sb.append(route[i].name + " ");
        }
        sb.append(":  " + distance);
        return sb.toString();
    }

    public String toName() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < route.length; i++) {
            sb.append(route[i].name + " ");
        }
        return sb.toString();
    }
}
