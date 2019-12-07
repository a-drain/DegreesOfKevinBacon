import DataStructures.Graph;

import java.util.Scanner;

/**
 * This is the main class that reads the csv file location from the command line and outputs the correct output
 */
public class Driver {

    public static void main(String[] args) {
        String pathToCSV;
        if (args.length < 1) {
            System.out.println("Please provide path to csv.");
            return;
        }

        pathToCSV = args[0];

        DataParser parser = new DataParser(pathToCSV);

        try {
            parser.getAllMovies();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Graph actorGraph = parser.createGraph();

        Scanner s = new Scanner(System.in);

        System.out.print("Actor 1: ");
        String actor1 = s.nextLine();
        if (!actorGraph.contains(actor1)){
            System.out.println("No such actor");
            return;
        }

        System.out.print("Actor 2: ");
        String actor2 = s.nextLine();

        if(!actorGraph.contains(actor2)) {
            System.out.println("No Such Actor");
            return;
        }

        System.out.println(actorGraph.findPath(actor1, actor2));
    }
}


