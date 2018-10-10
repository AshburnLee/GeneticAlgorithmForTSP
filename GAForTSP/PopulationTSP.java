package myProj;

import java.util.ArrayList;

import static myProj.myTour.showTour;

/**
 * class of population
 * methods execution ORDER: 1)->2)-> the rest
 * @author junhui 2018-10-8
 */
public class PopulationTSP {

    // the size of this population
    int popSize;

    // stores all the individuals
    public ArrayList<IndividualTSP> group; //group represents the Population

    // the fittest value in this population
    double fittest;

    /**
     * 1) constructor
     * @param popSize: number of individuals in the population
     * @param cityCandidate: all the cities that are needed to be traveled.
     */
    public PopulationTSP(int popSize, ArrayList<myCity> cityCandidate) {
        this.popSize = popSize;
        this.fittest = 0.0;
        this.group = new ArrayList<>(popSize);

        //create individuals in this pop
        for(int i =0;i < popSize;i++) {
            //set
            group.add(i, new IndividualTSP(cityCandidate));
        }
    }

//    public int getterPopSize() { return popSize; }
//    public double getterFittest() { return fittest; }

    /**
     * 2) get all the fitness of all individuals & UPDATE fitness (checked)
     */
    public void calculateAllFitness() {
        for (int i=0; i<group.size(); i++) {
            group.get(i).calFitness();
//            System.out.println("distance : \t\t" + group.get(i).fitness);
        }
    }

    /**
     * get the individual (& index) with SHORTEST distance
     * @return the individual (& index) with highest distance
     */
    public void getFittestOfShortest() {
        int index = 0;
        double fitness = group.get(index).fitness;

        //iterate every individual
        for(int i=0; i < group.size(); i++)  {
            if (fitness > group.get(i).fitness) {
                fitness = group.get(i).fitness;
                index = i;
            }
        }
        fittest = group.get(index).fitness;
    }

    /**
     * Selection: pick father and mother for the first generation (checked)
     * @return an array contains father and mother
     */
    public IndividualTSP[] getFatherMother() {

        //initialize father and mother
        int fatherId = 0;
        double fatherFitness = group.get(fatherId).fitness;
        int motherId = 0;
        double motherFitness = group.get(motherId).fitness;

        //find the two shortest distance
        for(int i=0; i<group.size();i++) {
            if(group.get(i).fitness < fatherFitness) {
                //update second shortest
                motherFitness = fatherFitness;
                motherId = fatherId;

                //update the shortest
                fatherFitness = group.get(i).fitness;
                fatherId = i;
            } else if (group.get(i).fitness < motherFitness) {
                motherFitness = group.get(i).fitness;
                motherId = i;
            }
        }
        return new IndividualTSP[] {group.get(fatherId), group.get(motherId)};

    }

    /**
     * get the index of the individual with the longest distance (checked)
     * @return index
     */
    public int getLongestIndex() {
        int index = 0;
        double fitness = group.get(index).fitness;

        //iterate every individual
        for(int i=0; i < group.size(); i++)  {
            if (fitness <= group.get(i).fitness) {
                fitness = group.get(i).fitness;
                index = i;
            }
        }
        return index;
    }
    /**
     * show the population, and calculate (checked)
     */
    public void showPopulation() {
        for(int i=0;i<group.size();i++) {
            //show this tour (path)
            showTour(group.get(i).path);
            //calculate this fitness (total distance of that path) and update this fitness
//            group.get(i).fitness = accumulatedDistance(group.get(i).path);
        }
    }

}
