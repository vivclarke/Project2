import java.util.ArrayList;
import java.io.*;

/**
 * This program simulates a
 * forest using multiple tree objects.
 *
 * @author Vivienne Clarke
 * @version 1.0
 */

public class Forest {

    /**
     * creates a forest with a specified name
     *
     * @param name name of forest
     */

    // declarations
    private String name;
    private ArrayList<Tree> trees;

    public Forest(String name) {

        this.name = name;
        this.trees = new ArrayList<>();

    } // end of constructor

    /**
     * read data from given csv files to make the forest
     *
     * @param csvFilePath path to csv file holding Tree data
     * @return true if method works
     * @return false if error
     * @throws IOException if IOexception encountered
     * @throws NumberFormatException if file format is wrong
     */
    public boolean load(String csvFilePath) {

        // read CSV with try-catch block
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

            String line;

            // read data separated by commas in CSV file
            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");
                Tree.Species species = stringToEnum(data[0]);

                if (species == null) {
                    System.out.println("Invalid species in the CSV file.");
                    return false;
                } // end of if statement

                // convert CSV to tree data
                int yearPlanted = Integer.parseInt(data[1]);
                double height = Double.parseDouble(data[2]);
                double growthRate = Double.parseDouble(data[3]);

                Tree tree = new Tree(species, yearPlanted, height, growthRate);
                trees.add(tree);
            } // end of while loo[

            return true;

        } catch (IOException | NumberFormatException e) {
            return false;

        } // end of try-catch block

    } // end of load method

    /**
     * convert species string to enum
     *
     * @param speciesString species as string
     * @return species enum value
     * @return null if enum is not found
     */
    private Tree.Species stringToEnum(String speciesString) {

        // converts Strings to Enums
        for (Tree.Species species : Tree.Species.values()) {

            if (species.toString().equalsIgnoreCase(speciesString)) {
                return species;
            } // end of if statement

        }
        return null;

    } // end of stringToEnum method

    /**
     * Print information about the forest
     */
    public void print() {
        System.out.println("Forest name: " + name);

        int index;
        for (index = 0; index < trees.size(); index++) {
            System.out.println("     " + index + " " + trees.get(index));
        } // end of for loop

        System.out.println("There are " + trees.size() + " trees, with an average height of " + calcAvgHeight());

    } // end of print method

    /**
     * add a randomly generated tree
     *
     */
    public void addRandTree() {

        Tree tree = Tree.generateRandNewTree();
        trees.add(tree);

    } // end of addRandTree method

    /**
     * cut a tree from the forest
     *
     * @param index index of cut tree
     */
    public void cut(int index) {

        // check tree number
        if (index >= 0 && index < trees.size()) {
            trees.remove(index);

        } else {
            System.out.println("Tree number " + index + " does not exist");
        } // end of if-else

    } // end of cut method

    /**
     * simulate a year of growth in the forest array
     */
    public void simulateGrowth() {

        for (Tree tree : trees) {
            tree.simulateGrowth();
        } // end of for loop

    }// end of simulateGrowth method

    /**
     * reaps trees based on input height and replaces with a randomly generated tree
     *
     * @param height height to be reaped
     */
    public void reap(double height) {

        int index;
        for (index = 0; index < trees.size(); index++) {

            // check height
            if (trees.get(index).getHeight() > height) {

                System.out.println("Reaping the tall tree " + trees.get(index));

                // replace reaped trees with new random trees
                Tree newTree = Tree.generateRandNewTree();
                trees.set(index, newTree);
                System.out.println("Replaced with new tree " + newTree);
            } // end of if-statement

        } // end of for-loop

    } // end of reap method

    /**
     * save current forest to a DB file
     *
     * @throws IOException if the file doesn't save
     */
    public void save() {

        // convert currForest to DB file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(name + ".db"))) {

            outputStream.writeObject(trees);

        } catch (IOException exception) {

            System.out.println("Unable to save file - try again");;

        } // end of try-catch block

    } // end of save method

    /**
     * load a previously saved DB file
     *
     * @param dbfileName DB file
     * @return true if file loads
     * @return false if file doesn't load
     */
    public boolean loadDB(String dbfileName) {

        // read DB file
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(dbfileName))) {

            trees = (ArrayList<Tree>) inputStream.readObject();
            return true;

        } catch (IOException | ClassNotFoundException exception) {
            return false;

        } // end of try-catch block

    } // end of loadDB method

    /**
     * calc the average height of trees in the current forest
     *
     * @return averageHeight avg height of trees
     */
    private double calcAvgHeight() {

        // calc total height
        double totalHeight = 0;
        for (Tree tree : trees) {
            totalHeight += tree.getHeight();
        }

        double averageHeight = trees.isEmpty() ? 0 : totalHeight / trees.size();

        return Math.round(averageHeight * 100.0) / 100.0;

    } // end of calcAvgHeight method

} //end of Forest class
