package simpleGA;

import java.util.Random;

public class GeneticAlgorithm {

    Individual father;
    Individual mother;
    Population pop;

    public GeneticAlgorithm(int popSize, int geneSize) {
        this.pop = new Population(popSize, geneSize);
    }
    /**
     * get father and mother (check)
     */
    public void selection() {
        father = pop.get1st2ndFittest()[0];
        mother = pop.get1st2ndFittest()[1];
    }

    /**
     * crossover among parents (check)
     */
    public void crossover() {
        Random rn = new Random();

        //select a crossover point
        int crossPoint = rn.nextInt(pop.group[0].geneLength);

        //swap values among parents
        for (int i=0; i<crossPoint; i++) {
            int tmp = father.genes[i];
            father.genes[i] = mother.genes[i];
            mother.genes[i] = tmp;
        }
    }

    /**
     * mutation in a individual (check)
     */
    public void mutation() {
        Random rn = new Random();

        //select a mutation point;
        int mutationPoint = rn.nextInt(pop.group[0].geneLength);

        //flip values at the mutation point
        if (father.genes[mutationPoint] == 0) {
            father.genes[mutationPoint] = 1;
        } else {
            father.genes[mutationPoint] = 0;
        }

        mutationPoint = rn.nextInt(pop.group[0].geneLength);

        if(mother.genes[mutationPoint] == 0) {
            mother.genes[mutationPoint] = 1;
        } else {
            mother.genes[mutationPoint] = 0;
        }

    }

    /**
     * check two children, save the one with highest fitness
     * @return the child with the highest fitness
     */
    public Individual getFittestOffspring() {
        if (father.fitness > mother.fitness)
            return father;
        return mother;
    }

    /**
     * replace the bad individual with the best child
     * Update the population
     */
    public void addFittestOffSpring() {
        //get the fitness of offspring
        father.calcFitness();
        mother.calcFitness();

        //get the index of least fit individual
        int replaceIndex = pop.getLeastFittestIndex();

        //Replace the least fittest from the child with highest fitness
        pop.group[replaceIndex] = getFittestOffspring();

    }

}
