package simpleGA;

import java.util.Random;

public class Individual {

    int fitness;  //initial value
    int geneLength;
    int[] genes;

    /**
     * create individual
     * @param length: the length of the genes
     */
    public Individual(int length) {

        // the attributes
        this.geneLength = length;
        this.fitness = 0;
        this.genes = new int[geneLength];

        //randomly create individual
        Random rn = new Random();
        for(int i=0; i < genes.length; i++ ) {
//            genes[i] = rn.nextInt() % 7;
            genes[i] = rn.nextInt(10);
        }

    }

    /**
     * calculate fitness: the number of "1" in each gene
     */
    public void calcFitness() {
        fitness = 0;
        for ( int i=0;i<geneLength; i++) {
            if (genes[i] == 1) {
                fitness += 1;
            }
        }
    }

    public void showIndividual() {
        if (genes.length != 0) {
            for (int i=0;i < geneLength; i++) {
                System.out.print(" " + genes[i]);
            }
            System.out.println();
        } else {
            System.out.println("Empty!");
        }

    }
}
