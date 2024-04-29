import java.io.Serializable;
import java.util.Random;

/**
 * This program creates a Tree object
 * to be used to create a forest
 * @author Vivienne Clarke
 * @version 1.0
 */
public class Tree implements Serializable {
    public enum Species {
        Birch, Maple, Fir

    } // end of enum


    // declare variables
    private Species species;
    private int yearPlanted;
    private double height;
    private double growthRate;

    //declare constants
    final static int YEAR_MAXIMUM = 25;
    final static int YEAR_START = 2000;
    final static double HEIGHT_MAXIMUM = 20.0;

    final static double HEIGHT_MINIMUM = 10.0;
    final static double GROWTH_MINIMUM = 10.0;
    final static double GROWTH_MAXIMUM = 20.0;


    /**
     * constructor of Tree
     *
     * @param species species of Tree object
     * @param yearPlanted year the Tree was planted
     * @param height height of the Tree (in feet)
     * @param growthRate growth rate of the Tree
     *
     */

    // constructor
    public Tree (Species species, int yearPlanted,
                 double height, double growthRate) {
        this.species = species;
        this.yearPlanted = yearPlanted;
        this.height = height;
        this.growthRate = growthRate;

    } // end of the constructor method

    /**
     * generates a random tree
     *
     * @return Tree New tree with random data (species, yearPlanted, height, growthRate)
     */
    public static Tree generateRandNewTree() {
        Random random = new Random();
        Species[] speciesArray = Species.values();
        Species species = speciesArray[random.nextInt(speciesArray.length)];
        int yearPlanted = random.nextInt(YEAR_MAXIMUM) + YEAR_START;
        double height = random.nextDouble() * HEIGHT_MAXIMUM + HEIGHT_MINIMUM;
        double growthRate = GROWTH_MINIMUM + random.nextDouble() * GROWTH_MAXIMUM;

        return new Tree(species, yearPlanted, height, growthRate);
    } // end of generateRandNewTree constructor

    /**
     * simulates growth of tree using growth rate
     */
    public void simulateGrowth() {
        height *= 1 + (growthRate / 100);
    } // end of simulateGrowth method (simulates a year of growth)

    /**
     * get the height of the tree
     *
     * @return height Height of the tree (in feet)
     */
    public double getHeight() {
        return height;
    } // end of getHeight


    /**
     * Create a string with all of the Tree data.
     *
     * @return string Tree (species, yearPlanted, height, and growthRate)
     */
    //override toString to use in print method of Forest
    @Override
    public String toString() {
        String speciesString = species.toString();
        if (speciesString.length() == 3) {
            speciesString += "  "; // Add two spaces to align with longer species names
        }
        return speciesString + " " + yearPlanted + " "
                + String.format("%.2f", height) + "' "
                + String.format("%.1f", growthRate) + "%";
    }

} // end of tree class
