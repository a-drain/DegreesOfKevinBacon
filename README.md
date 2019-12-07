# Degrees of Kevin Bacon

## How to Use:
1. Clone the repo and cd into it
2. `cd target`
3. `java -jar DegreesOfKBacon-1.0-SNAPSHOT-jar-with-dependencies.jar <path to tmdb_5000_credits.csv`

## Runtime Analysis:
- The most time complex method in this project is the createGraph method in DataParser
    - This method runs with O(n^3) time complexity because we get the actors in each movie, 
    then add an edge between each actor and all of the other actors in that movie