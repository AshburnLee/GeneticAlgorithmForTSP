package myProj;

import simpleGA.Individual;

import java.util.ArrayList;
import java.util.Collections;

import static myProj.myTour.accumulatedDistance;
import static myProj.myTour.showTour;

/**
 * @author junhui 2018-10-8
 */
public class IndividualTSP {

    double fitness;
    ArrayList<myCity> path;  //path represents the individual

    /**
     * constructor, randomly pick cities to create the individual. (check)
     * @param cityCandidate: list of cities that can be picked from
     */
    public IndividualTSP(ArrayList<myCity> cityCandidate) {

        this.fitness = 0.0;
        this.path = new ArrayList<>();

        //randomly pick city to create individual
        //1) copy
        path = new ArrayList<>(cityCandidate);
        //2) shuffle
        Collections.shuffle(path);
    }

    /**
     * constructor with no initial value
     */
    public IndividualTSP() {
        this.fitness = 0.0;
        this.path = new ArrayList<>();
    }



    /**
     *calculate the whole distance,
     * write it to the fitness UPDATE fitness(check)
     */
    public void calFitness() {
        fitness = accumulatedDistance(path);
    }

    /**
     * get the fitness
     * @return
     */
    public double getterFitness() { return fitness; }

    /**
     * print this individual (check)
     */
    public void showIndividual() {
        showTour(path);
    }

}
