package myProj;

import simpleGA.Individual;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 *
 * @author junhui 2018-10-9
 */
public class GeneticAlgorithmTSP {

    IndividualTSP father;
    IndividualTSP mother;
    PopulationTSP pop;

    /**
     * constructor
     * @param popSize: the size of this population
     * @param cityCandidate: cities that can be selected from to create individuals
     */
    public GeneticAlgorithmTSP(int popSize, ArrayList<myCity> cityCandidate) {
        this.pop = new PopulationTSP(popSize, cityCandidate);
    }

    /**
     * get parents for this generation.
     */
    public void selection() {
        father = pop.getFatherMother()[0];
        mother = pop.getFatherMother()[1];
    }

    /**
     * utility for crossover operation: get one child
     * @param father: THIS father
     * @param mother: THIS mother
     * @return the first child
     */
    public  void crossoverUtility(IndividualTSP father,
                                        IndividualTSP mother,
                                        IndividualTSP child,
                                        int crossPoint) {

        //1)
        //check list
        ArrayList<myCity> checkList = new ArrayList<>();  //must be empty

        //put genes from 0 to cross point to child.   (checked!)
        for (int i=0; i < crossPoint; i++ ) {
            //put this gene to check list
            checkList.add(i, father.path.get(i));

            //put this gene to child1
            child.path.add(i, father.path.get(i));
        }

        //iterate mother, add the rest gene from mother
        for (int i=0; i < mother.path.size(); i++ ){
            //flag receive signal
            boolean flag = false;
            //
            for (int j = 0; j< checkList.size(); j++) {
                if (checkList.get(j) == mother.path.get(i)) {
                    flag = true;
                    break;
                }
                if (checkList.get(j) != mother.path.get(i)) {
                    flag = false;
                }
            }

            //if gene is not exit in the check list, put it in both checklist and child1
            if (!flag) {

                checkList.add(checkList.size(), mother.path.get(i));

                child.path.add(child.path.size(), mother.path.get(i));
            }
            //if gene is exist, do nothing, iterate the next mother gene.
            else if (flag) { continue; }
        }
        /* check the checklist
        for (int i=0;i<checkList.size();i++) {
            System.out.println(checkList.get(i).name);
        }
        */
    }

    /**
     * pick the good child
     * @param child1:
     * @param child2:
     * @return the better child
     */
    public  IndividualTSP getBestChild(IndividualTSP child1, IndividualTSP child2) {

        if(child1.getterFitness() > child2.getterFitness() )
            return child2;
        return child1;

    }

    public void swap(IndividualTSP child, int mp1, int mp2) {
        myCity tmp = child.path.get(mp1);
        child.path.set(mp1, child.path.get(mp2));
        child.path.set(mp2, tmp);
    }

    public void mutation(IndividualTSP child) {

        //generate two mutation points
        Random rn = new Random();
        int mp1 = rn.nextInt(child.path.size());
        int mp2 = rn.nextInt(child.path.size());

        //mutate
        if(mp1 != mp2) {
            //swap
            swap(child, mp1, mp2);

        } else if (mp1 == mp2 && mp1 == 0) {
            //move one bit
            mp1 = mp1 + 1;

            swap(child, mp1, mp2);

        } else {
            //move one bit
            mp1 = mp1 - 1;

            swap(child, mp1, mp2);

        }
    }



}
