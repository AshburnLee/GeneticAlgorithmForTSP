package myProj;

/**
 * the City data type
 */
public class myCity {

    // x and y
    int x;
    int y;
    String name;

    //constructor
    public myCity(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    //get the name of this instance
    public String getName() { return this.name; }

    //get the x
    public int getX() { return this.x; }

    //get the y
    public int getY() { return this.y; }

}
