import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Movie {

    private ArrayList<String> actors;
    private int size;

    /**
     * Adds each actor in actorsArray to ArrayList of actors
     * @param actorsArray JSONArray of actors for a single movie
     */
    public Movie(JSONArray actorsArray){
        this.actors = new ArrayList<String>();
        for (int i = 0; i < actorsArray.size(); i++) {
            JSONObject jsonobject = (JSONObject) actorsArray.get(i);
            actors.add(jsonobject.get("name").toString());
            size++;
        }
    }

    public int getSize(){
        return size;
    }

    public ArrayList<String> getActors() {
        return actors;
    }
}
