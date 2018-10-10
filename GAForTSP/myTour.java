package myProj;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author junhui 2018-10-1
 * manage the tour including :
 *  1) distance between two cities
 *  2) distance of the tour
 *  3) show the tour
 *  4) randomly select another tour
 */
public class myTour {

    /**
     * calculate the distance of two cities
     * @param city1:
     * @param city2:
     * @return teh distance between two cities
     */
    public static double distance(myCity city1, myCity city2) {

        int distX = Math.abs(city1.getX() - city2.getX());
        int distY = Math.abs(city1.getY() - city2.getY());
        double distance = Math.sqrt(distX*distX + distY*distY);

        return distance;
    }

    /**
     * calculate the distance of the tour (the energy of the current solution)
     * @param container: the current tour
     */
    public static double accumulatedDistance(ArrayList<myCity> container) {

        double sum = 0;
        if(container.size() != 0) {

            for (int i=0; i< container.size()-1; i++) {
                sum += distance(container.get(i), container.get(i+1));
            }
            sum += distance(container.get(0), container.get(container.size()-1));
//            System.out.println("The distance is: \t\t" + sum);
        }else {
            System.out.println("Empty container !");
        }
        return sum;

    }

    /**
     * Show the tour (solution)
     * @param al: the tour
     */
    public static void showTour(ArrayList<myCity> al) {

        if(al.size() != 0) {
            System.out.print("(| ");
            for (int i=0;i<al.size();i++) {

                System.out.print(al.get(i).getName() + "->");
            }
            System.out.println(al.get(0).getName() + " |)");
        }else {
            System.out.println("Empty container !");
        }

    }

    /**
     *randomly select another tour
     * used in simulated Annealing.
     * Alternatively use Collection.shuffle()
     *
     * @param citySpace: the cities that need to be traveled
     * @return the random tour consists of randomly pick cities
     */
    public static ArrayList<myCity> randomPickSolution(ArrayList<myCity> citySpace) {

        ArrayList<myCity> nextSolution = new ArrayList<>();

        if(citySpace.size() != 0) {

            ArrayList<myCity> containerRandomPick = (ArrayList<myCity>) citySpace.clone();

            //randomly pick an item from a container
            while (containerRandomPick.size() != 0) {

                //randomly pick one
                myCity tmp = containerRandomPick.get(new Random().nextInt(containerRandomPick.size()));

                //add it to solution container
                nextSolution.add(tmp);

                //remove it from cloned container:
                containerRandomPick.remove(tmp);
            }

        }else{
            System.out.println("Empty container ! ");
        }
        return nextSolution;

    }

}
