/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetictsp;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;

/**
 *
 * @author Piet
 *
 * Main, executive class for the Traveling Salesman Problem.
 *
 * We don't have a real list of cities, so we randomly generate a number of them
 * on a 100x100 map.
 *
 * The TSP requires that each city is visited once and only once, so we have to
 * be careful when initializing a random Individual and also when applying
 * crossover and mutation. Check out the GeneticAlgorithm class for
 * implementations of crossover and mutation for this problem.
 *
 * @author bkanber
 *
 */
public class TSP {

    public static int maxGenerations = 50;
    public static City startAndEndCity;

    public static void main(List<String> cityNames, List<String> distances, int startAndEndIndex) {

        // check for consistency
        if (cityNames.size() * cityNames.size() != distances.size()) {
            throw new RuntimeException("data do not match!");
        }

        int numberOfCities = cityNames.size();
        
        // Create cities
        // distances from startCity
        int from = startAndEndIndex * cityNames.size();
        int to = (startAndEndIndex + 1) * cityNames.size();
        List<String> d = new ArrayList<>();
        for (int i = from; i < to; i++) d.add(distances.get(i));
//        List<String> d = IntStream.range(from, to)
//            .mapToObj(i -> distances.get(i))
//            .collect(toList())
//        ;
        startAndEndCity = new City(
            cityNames.size(),
            cityNames.get(startAndEndIndex),
            d,
            startAndEndIndex
        );
        
        cityNames.remove(startAndEndIndex);
        int numCities = cityNames.size();
        City[] cities = new City[numCities];
        
        int begin = startAndEndIndex * numberOfCities;
        int eind = from + numberOfCities;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < distances.size(); i++) {
            if (i < begin || i >= eind) list.add(distances.get(i));
        }
//        var list = IntStream.range(0, distances.size())
//            .filter(i -> i < begin || i >= eind)
//            .mapToObj(distances::get)
//            .collect(toList())
//        ;

        // Loop to create random cities
        for (int i = 0; i < numCities; i++) {
            // Add city
            from = i * numberOfCities;
            to = (i + 1) * numberOfCities;
            List<String> lijst = new ArrayList<>();
            for (int j = from; j < to; j++) lijst.add(list.get(j));
//            var lijst = IntStream.range(from, to).mapToObj(list::get).collect(toList());
            cities[i] = new City(i, cityNames.get(i), lijst, startAndEndIndex);
        }

        // Initial GA
//        GeneticAlgorithm ga = new GeneticAlgorithm(numCities, 0.001, 0.9, 2, 5);
        GeneticAlgorithm ga = new GeneticAlgorithm(numCities, 0.001, 0.9, 2, Math.min(numCities, 5));
        // Initialize population
        Population population = ga.initPopulation(cities.length);

        // Evaluate population
        ga.evalPopulation(population, cities);

        Route startRoute = new Route(population.getFittest(0), cities);
        System.out.println("Start Distance: " + startRoute.getDistance());

        // Keep track of current generation
        int generation = 1;
        // Start evolution loop
        while (ga.isTerminationConditionMet(generation, maxGenerations) == false) {
            // Print fittest individual from population
            Route route = new Route(population.getFittest(0), cities);
            System.out.println("G" + generation + "  route: " + route + " Best distance: " + route.getDistance());

            // Apply crossover
            population = ga.crossoverPopulation(population);

            // Apply mutation
            population = ga.mutatePopulation(population);

            // Evaluate population
            ga.evalPopulation(population, cities);

            // Increment the current generation
            generation++;
        }

        System.out.println("Stopped after " + maxGenerations + " generations.");
        Route route = new Route(population.getFittest(0), cities);
        System.out.print("So the shortest route is: ");
        System.out.println(startAndEndCity.name + " " + route.toName() + startAndEndCity.name);
        System.out.println("Best distance: " + route.getDistance());
//        for (int i = 0; i < route.)

    }
}
