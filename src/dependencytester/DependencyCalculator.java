package dependencytester;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author annamitchell
 *
 */

//I think it would be more efficient to require the add_direct method to
//take a string item and a HashSet of its dependencies; then you could just
//assign the HashSet of dependencies to the value in the map. The spec required 
//me to include the item as the FIRST item in the collection, however.

//I used a HashSet to store dependencies because according to 
//http://stackoverflow.com/questions/3267572/fastest-data-structure-for-contains-in-java, 
//a HashSet has O(1) complexity for contains(), an operation we are using frequently.


public class DependencyCalculator {
    
    /*
    * Empty constructor. Originally I'd included a main method in this class but I decided
    * it would be clearer to have the tester in a separate class/file. I'm not sure which 
    * is better style. 
    */
    public DependencyCalculator(){
        
    }
    
    /*
    * Takes as parameters a list of values, where the first value is the item whose 
    * dependencies we want to find and the following values are its direct dependencies, 
    * and a map mapping each value to a list of its direct dependencies. Adds the value 
    * and its direct dependencies to the map. Returns nothing.
    */
    public static void add_direct(ArrayList<String> values, 
            HashMap<String, HashSet<String>> dependencyMap) {
            //first value in the list is the value in question
            String item = values.get(0);
            //creates a list of dependencies
            HashSet<String> d = new HashSet();
            for (int i = 1; i < values.size(); i++) {
                d.add(values.get(i));
            }
            //adds the first value as key and the list of dependencies as the value
            dependencyMap.put(item, d);
    }
    
     /*
    * Finds all the dependencies, direct and indirect, of a given string
    * name. Takes as parameters a string item and a map mapping string values 
    * to sets of all of their direct dependencies and returns a set of the
    * dependencies. (This set will be empty if there are no dependencies.) 
    * Wrapper function for the real recursive work in findDependencies. 
    */
    public static HashSet<String> dependencies_for(String item,
            HashMap<String, HashSet<String>> dependencyMap) {
        HashSet<String> dependencies = new HashSet();
        HashSet<String> visited = new HashSet();
        findDependencies(item, dependencyMap, dependencies, item);
        return dependencies;
    }
    
    
    /*
    * Recursive function that finds all the dependencies, direct and indirect, 
    * of a given string name. Takes as parameters a string item, a map mapping 
    * string values to sets of all of their direct dependencies, a set of the
    * item's dependencies, a set of visited items, and a string keeping track
    * of the original item. Returns nothing.
    */
    public static void findDependencies(String item, 
            HashMap<String, HashSet<String>> dependencyMap, 
            HashSet<String>dependencies, String originalItem) {
        //base case - return if the map does not contain the item (AKA, the item
        //has no direct dependencies
        if (!dependencyMap.containsKey(item)) {
            return;
        }
        //if map contains the item, add all of the dependencies to the list and then 
        //check those dependencies.
        else {
            HashSet<String> d = dependencyMap.get(item);
            for (String s : d) {
                //check if item is already in the dependencies list or is equal to  
                //the original item (in which case there would be a cycle)
                if (!dependencies.contains(s) && !(s.equals(originalItem))) {
                    //add each dependency to the list
                    dependencies.add(s);
                    //recursively check each of these items and add their dependencies
                    //to the set
                    findDependencies(s, dependencyMap, dependencies, originalItem);
                }   
            }
        }
        return;
    }
    
    /*
    * Useful for testing - takes as a parameter a map mapping each string value 
    * to a set of its direct dependencies and prints the map neatly to the console. 
    */
    public static void printMap (HashMap<String, HashSet<String>> dependencyMap) {    
        Iterator iterator = dependencyMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            String value = dependencyMap.get(key).toString();
            System.out.println(key + " " + value);
        }
    }
    
}

