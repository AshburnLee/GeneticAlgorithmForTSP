package simpleGA;

/**
 *
 */
public class Population {

    //declaration
    int popSize;
    Individual[] group;  //a group of individuals
    int fittest;  //initial value

    /**
     * initialize population
     * @param popSize: number of individuals of the pop
     * @param geneSize: number of genes in each individual
     */
    public Population(int popSize, int geneSize) {
        // initialization
        this.popSize = popSize;
        this.group = new Individual[popSize];
        this.fittest = 0;
        for (int i=0; i<group.length; i++) {
            group[i] = new Individual(geneSize);
        }
    }

    /**
     * write new value to fittest
     */
    public Individual getFittest() {
        int index = 0;
        int fitness = group[index].fitness;

        //iterate every individual
        for (int i=0;i<group.length; i++) {
            if(fitness <= group[i].fitness) {
                fitness = group[i].fitness;
                index = i;
            }
        }
        fittest = group[index].fitness;
        return group[index];

    }

    /**
     * find the first and second fittest individuals in the population
     * @return first and second individuals
     */
    public Individual[] get1st2ndFittest() {
        int fitnessMax = 0;
        int fitnessSec = 0;
        int indexMax = 0;
        int indexSec = 0;

        for (int i = 0; i < group.length; i++) {

            if (group[i].fitness > fitnessMax) {

                //second update
                fitnessSec = fitnessMax;
                indexSec = indexMax;
                //max update
                fitnessMax = group[i].fitness;
                indexMax = i;
            }
            else if (group[i].fitness > fitnessSec ){
                fitnessSec = group[i].fitness;
                indexSec = i;
            }
            else if (group[i].fitness == fitnessMax) {
                fitnessSec = group[i].fitness;
                indexSec = i;
            }
        }
        //get the fittest of this population
        this.fittest = group[indexMax].fitness;
        return new Individual[]{group[indexMax], group[indexSec]};
    }

    /**
     * call this method after calculating fitness of all members in the group
     * @return the index individual with lowest fitness
     */
    public int getLeastFittestIndex() {
        int index = 0;
        int fitness = group[index].fitness;

        //iterate every individual
        for (int i=0;i<group.length; i++) {
            if(fitness > group[i].fitness) {
                fitness = group[i].fitness;
                index = i;
            }
        }
        return index;
    }

    /**
     * calculate the fitness of all individuals
     */
    public void calculateFitnesses() {
        for (int i=0; i<group.length; i++) {
            group[i].calcFitness();
        }
    }
}
