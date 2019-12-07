package DataStructures;
import java.util.*;

public class Graph {
    private Map<String, LinkedList<String>> adj;

    /**
     * Initializes a HashMap to hold each actor and the actors they are connected to
     */
    public Graph() {
        adj = new HashMap<String, LinkedList<String>>();
    }

    /**
     * Checks if actor is a key in HashMap of actors
     * @param actor Actor to check
     * @return True when actor is a key, false otherwise
     */
    public boolean contains(String actor){
        return adj.get(actor.toLowerCase()) != null;
    }

    public void addEdge(String s1, String s2) {
        if(s1.equals(s2)){
            return;
        }

        if(adj.get(s1) == null)
            adj.put(s1, new LinkedList<String>());
        if(adj.get(s2) == null)
            adj.put(s2, new LinkedList<String>());

        adj.get(s1).add(s2);
        adj.get(s2).add(s1);
    }

    /**
     * Finds the closest path between two given actors by performing breadth first search
     * @param string1 Actor 1
     * @param string2 Actor 2
     * @return String representation of the nearest path between two actors
     */
    public String findPath(String string1, String string2){
        return findPath(string1.toLowerCase(), string2.toLowerCase(), "");
    }

    private String findPath(String string1, String string2, String path){
        path += string1 + " --> ";
        LinkedList<String> temp = adj.get(string1);

        if(temp.contains(string2)){
            return path + string2;
        }

        for(String actor : temp){
            if(adj.get(actor).contains(string2)){
                return findPath(actor, string2, path);
            }
        }

        for(String actor : temp){
            if(!path.contains(actor)){
                return findPath(actor, string2, path);
            }
        }
        return "Could not find path between " + string1 + " and " + string2;
    }
}