package dependencytester;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author annamitchell
 */
public class DependencyCalculatorTester {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     //Example with values from spec
        DependencyCalculator depcalc = null; 
        HashMap dependencyMap1 = new HashMap();
        ArrayList<String> values1 = new ArrayList();
        values1.add("A");
        values1.add("B");
        values1.add("C");
        depcalc.add_direct(values1, dependencyMap1);
        ArrayList<String> values2 = new ArrayList();
        values2.add("B");
        values2.add("C");
        values2.add("E");
        depcalc.add_direct(values2, dependencyMap1);
        ArrayList<String> values3 = new ArrayList();
        values3.add("C");
        values3.add("G");
        depcalc.add_direct(values3, dependencyMap1);
        ArrayList<String> values4 = new ArrayList();
        values4.add("D");
        values4.add("A");
        values4.add("F");
        depcalc.add_direct(values4, dependencyMap1);
        ArrayList<String> values5 = new ArrayList();
        values5.add("E");
        values5.add("F");
        depcalc.add_direct(values5, dependencyMap1);
        ArrayList<String> values6 = new ArrayList();
        values6.add("F");
        values6.add("H");
        depcalc.add_direct(values6, dependencyMap1);
        depcalc.printMap(dependencyMap1);
        System.out.println(depcalc.dependencies_for("A", dependencyMap1));
        System.out.println(depcalc.dependencies_for("B", dependencyMap1));
        System.out.println(depcalc.dependencies_for("C", dependencyMap1));
        System.out.println(depcalc.dependencies_for("D", dependencyMap1));
        System.out.println(depcalc.dependencies_for("E", dependencyMap1));
        System.out.println(depcalc.dependencies_for("F", dependencyMap1));
        System.out.println(depcalc.dependencies_for("G", dependencyMap1));
        System.out.println("DONE");
        
        //Example with cycles
        HashMap dependencyMap2 = new HashMap();
        ArrayList<String> values7 = new ArrayList();
        values7.add("A");
        values7.add("B");
        depcalc.add_direct(values7, dependencyMap2);
        ArrayList<String> values8 = new ArrayList();
        values8.add("B");
        values8.add("C");
        depcalc.add_direct(values8, dependencyMap2);
        ArrayList<String> values9 = new ArrayList();
        values9.add("C");
        values9.add("A");
        depcalc.add_direct(values9, dependencyMap2); 
        depcalc.printMap(dependencyMap2);
        System.out.println(depcalc.dependencies_for("A", dependencyMap2));
        System.out.println(depcalc.dependencies_for("B", dependencyMap2));
        System.out.println(depcalc.dependencies_for("C", dependencyMap2));
        System.out.println("DONE");
    }
}
