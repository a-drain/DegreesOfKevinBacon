import DataStructures.Graph;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;


/**
 * Gets data from csv into a linked list of string arrays, parses cast column to json, then get each actor and place it into a
 */
public class DataParser {

    private String pathToCsv;
    private ArrayList<Movie> allMovies;

    /**
     * Constructs DataParser Object
     * @param pathToCsv Path of csv file to read
     */
    public DataParser(String pathToCsv){
        this.pathToCsv = pathToCsv;
        this.allMovies = new ArrayList<Movie>();
    }

    /**
     * Parses the csv file at location of pathToCsv
     * @return A representation of the csv file as a LinkedList of String arrays
     * @throws Exception When file cannot be found
     */
    private LinkedList<String[]> parseCSV() throws Exception {
        CSVReader reader = new CSVReader(new FileReader(pathToCsv));
        LinkedList<String[]> data = (LinkedList<String[]>)reader.readAll();
        return data;
    }

    /**
     * Adds actors of each movie to a movie object and appends this Movie to allMovies ArrayList.
     * Additionally stores the number of non-parsable rows and empty rows
     * @throws Exception when csv csv cannot parse
     */
    public void getAllMovies() throws Exception {
        JSONParser parser = new JSONParser();
        LinkedList<String[]> data = parseCSV();

        int missedEntries = 0;
        int nilRows = 0;

        for (int i = 1; i < data.size(); i++) {
            try {
                JSONArray actors = (JSONArray) parser.parse(data.get(i)[2]);
                if(actors.size() == 0){
                    nilRows++;
                }
                Movie movie = new Movie(actors);
                allMovies.add(movie);
            } catch (Exception e){
                missedEntries++;
            }
        }
        System.out.println("Badly formatted rows: " + missedEntries);
        System.out.println("Rows without actors: " + nilRows);
        System.out.println("Total missed rows: " + (missedEntries+nilRows));
    }

    /**
     * Connects each actor in each movie to the other actors in that movie to create a graph
     * @return An undirected graph of all actors in every movie
     */
    Graph createGraph() {
        Graph graph = new Graph();
        for(int i=0; i < allMovies.size(); i++){
            Movie movie = allMovies.get(i);
            for(int j=0; j < movie.getSize(); j++){
                for(int k=0; k < movie.getSize(); k++){
                    String actor1 = movie.getActors().get(j).toLowerCase();
                    String actor2 = movie.getActors().get(k).toLowerCase();
                    graph.addEdge(actor1, actor2);
                }
            }
        }
        return graph;
    }
}
