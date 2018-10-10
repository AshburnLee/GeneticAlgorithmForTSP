package myProj;

import java.util.ArrayList;
import java.util.Random;


public class demoGATSP {

    public static void main(String[] args) {

        ArrayList<myCity> container = new ArrayList<>();
        myCity city1 = new myCity("1", 3,6);
        container.add(city1);
        myCity city2 = new myCity("2",2,7);
        container.add(city2);
        myCity city3 = new myCity("3",2,8);
        container.add(city3);
        myCity city4 = new myCity("4",1,4);
        container.add(city4);
        myCity city5 = new myCity("5",6,9);
        container.add(city5);
        myCity city6 = new myCity("6",12,3);
        container.add(city6);
        myCity city7 = new myCity("7",11,12);
        container.add(city7);
        myCity city8 = new myCity("8",6,15);
        container.add(city8);
        myCity city9 = new myCity("9",1,14);
        container.add(city9);
        myCity city10 = new myCity("10",9,16);
        container.add(city10);
        myCity city11 = new myCity("11",3,16);
        container.add(city11);
        myCity city12 = new myCity("12",5,15);
        container.add(city12);
        myCity city13 = new myCity("13",7,13);
        container.add(city13);
        myCity city14 = new myCity("14",8,12);
        container.add(city14);
        myCity city15 = new myCity("15",20,19);
        container.add(city15);

        //initialize population
        GeneticAlgorithmTSP ga = new GeneticAlgorithmTSP(10, container);
        ga.pop.showPopulation();
        ga.pop.calculateAllFitness();

        //get the strongest
        ga.pop.getFittestOfShortest();
        System.out.println("the fitness of the strongest: "+ga.pop.fittest);


        //print info of the first generation
        int generationCount = 1;
        System.out.println("Generation: \t" + generationCount + " Fitness: \t"+ ga.pop.fittest);

        //while the pop.fittest > 75 re-product
        while (ga.pop.fittest > 75) {


            /*selection*/
            ga.selection();
            IndividualTSP father = ga.father;
            IndividualTSP mother = ga.mother;


            /*crossover*/
            IndividualTSP child1 = new IndividualTSP();
            IndividualTSP child2 = new IndividualTSP();

            //cross over point
            Random rn = new Random();
            int crossPoint = rn.nextInt(container.size());


            //get two children
            ga.crossoverUtility(father, mother, child1,crossPoint);
            ga.crossoverUtility(mother, father, child2,crossPoint);

            child1.calFitness();
            child2.calFitness();

            //save the good one
            IndividualTSP goodBoy = ga.getBestChild(child1, child2);

            //mutation of this good boy
            int prob = rn.nextInt(2); //50%
            if (prob == 0) {
                ga.mutation(goodBoy);

            }
            int prob2 = rn.nextInt(5);  //20%
            if (prob2 == 0) {
                ga.mutation(goodBoy);

            }


            //replace the worst one with the new child
            int worstIndex =  ga.pop.getLongestIndex();
            ga.pop.group.set(worstIndex, goodBoy);

            //update the fitness.
            ga.pop.calculateAllFitness();

            //update the fittest of this population
            ga.pop.getFittestOfShortest();


            generationCount ++;
            System.out.println("Generation: \t" + generationCount + " Fitness: \t"+ ga.pop.fittest);
        }
        System.out.println("Fittest fitness: " + ga.pop.fittest);



    }
}
