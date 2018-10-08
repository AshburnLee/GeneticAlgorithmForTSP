package simpleGA;

import java.util.Random;

/**
 * A demo
 * @author junhui 2018-10-7
 */
public class dome {

    GeneticAlgorithm ga;
    int generationCount = 0;
    Individual fittestInd;

    public static void main(String[] args) {

        Random rn = new Random();

        dome demo = new dome();

        //initialize population
        demo.ga = new GeneticAlgorithm(10, 50);

        //calculate fitness for each individual
        demo.ga.pop.calculateFitnesses();

        //update population fittest
        demo.fittestInd = demo.ga.pop.getFittest();

        //show that individual with highest fitness
        demo.fittestInd.showIndividual();

        //the initial generation:
        System.out.println("Generation: "+demo.generationCount+" Fittest: -> "+ demo.ga.pop.fittest);


        while (demo.ga.pop.fittest < 40) {
            System.out.println();

            demo.generationCount += 1;

            //select parents
            demo.ga.selection();

            //crossover
            demo.ga.crossover();

            //mutation
            if (rn.nextInt()%7 < 5) {
                demo.ga.mutation();
            }

            //update population
            demo.ga.addFittestOffSpring();

            //calculate fitness for new population
            demo.ga.pop.calculateFitnesses();

            //get the fittest new individual
            demo.fittestInd = demo.ga.pop.getFittest();
            demo.fittestInd.showIndividual();

            System.out.println("Generation: " + demo.generationCount + " Fitness: -> " + demo.ga.pop.fittest);
        }

        System.out.println("Solution found in generation: " + demo.generationCount);
        System.out.println("teh fittest: " + demo.fittestInd.fitness);
        System.out.println("individual: ");
        demo.fittestInd.showIndividual();





    }


}
