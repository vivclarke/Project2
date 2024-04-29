import java.util.Scanner;
/**
 * This program simulates the
 * growth and pruning of forests.
 * @author Vivienne Clarke
 * @version 1.0
 */
public class Forestry {
    //------------------------------------------------------------------------------------------------------
    private static final Scanner keyboard = new Scanner(System.in);

    //------------------------------------------------------------------------------------------------------
    /**
     * Displays welcome statement and menu for function selection
     *
     * @param args command-line arguments
     */
    public static void main(String[] args){

        // display welcome statement
        System.out.println("Welcome to the Forestry Simulation");
        System.out.println("----------------------------------");

        if (args.length == 0) {
            System.out.println("No forests provided.");
            return;
        } // end of if statement

        int forestIndex = 0;

        while (forestIndex < args.length) {

            // open CSV file from forest name
            String forestName = args[forestIndex];
            System.out.println("Initializing from " + forestName);
            Forest currForest = new Forest(forestName);

            // check file
            if (!currForest.load(args[forestIndex] + ".csv")) {

                System.out.println("Error opening/reading " + forestName + ".csv");
                forestIndex++;
                continue;

            } // end of if-statement

            boolean exitLoop = false; // check menu loop

            // create menu
            do {

                char choice;
                System.out.print("\n(P)rint, (A)dd, (C)ut, (G)row, (R)eap, (S)ave, (L)oad, (N)ext, e(X)it : ");
                choice = keyboard.next().charAt(0);
                keyboard.nextLine();

                switch (Character.toUpperCase(choice)) {

                    case 'P':
                        currForest.print(); // call print
                        break;

                    case 'A':
                        currForest.addRandTree(); // call addRandTree
                        break;

                    case 'C':
                        System.out.print("Tree number to cut down: ");
                        int index;

                        while (!keyboard.hasNextInt()) {
                            System.out.println("That is not an integer");
                            System.out.print("Tree number to cut down: ");
                            keyboard.next();
                        }

                        index = keyboard.nextInt();
                        keyboard.nextLine();
                        currForest.cut(index); // call cut
                        break;

                    case 'G':
                        currForest.simulateGrowth(); // call simulateGrowth
                        break;

                    case 'R':
                        System.out.print("Height to reap from: ");
                        double height;

                        while (!keyboard.hasNextDouble()) {
                            System.out.println("That is not an integer");
                            System.out.print("Height to reap from: ");
                            keyboard.next();
                        }

                        height = keyboard.nextDouble();
                        keyboard.nextLine();
                        currForest.reap(height); // call reap
                        break;

                    case 'S':
                        currForest.save(); // call save
                        break;

                    case 'L':
                        System.out.print("Enter forest name: ");

                        Forest oldForest = currForest; // keep old forest

                        String newForestName = keyboard.nextLine();
                        currForest = new Forest(newForestName);

                        if (!currForest.loadDB(newForestName + ".db")) {

                            // display error method if load fails
                            System.out.println("Error opening/reading " + newForestName + ".db");
                            System.out.println("Old forest retained");
                            currForest = oldForest; // reload  oldForest

                        }
                        break;

                    case 'N':
                        System.out.println("Moving to the next forest");
                        exitLoop = true; // next Forest
                        break;

                    case 'X':
                        System.out.println("\nExiting the Forestry Simulation");
                        return;

                    default:
                        System.out.println("Invalid menu option, try again");
                }

            } while (!exitLoop);

            forestIndex++; // move to the next forest

        } // end of while-loop

    } // end of main method

} // end of Forestry class
