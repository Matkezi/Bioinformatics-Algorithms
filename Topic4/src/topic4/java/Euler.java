package topic4.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * @author Matko
 * @version 1.0
 */
public class Euler {

    private class Edge {

        String edgeName;


    }

    private HashMap<Edge, List<Edge>> graph = new HashMap<>();

    private List<String> lines = new ArrayList<>();

    private void formGraph(){
        for (String line : lines){
            List<String> rightSide = new ArrayList<>();
            String[] lineSplit = line.split("->");
            if (lineSplit[1].contains(",")) {
                String[] toNodes = lineSplit[1].split(",");
                for (String toNode : toNodes) {
                    rightSide.add(toNode.trim());
                }
            } else rightSide.add(lineSplit[1].trim());

            graph.put(lineSplit[0].trim(),null);




        }
    }

    private void findEulerCycle(){


    }

    public void execute() throws IOException {

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\resources");
        File file1 = new File(dir, "EulerCycle.txt");
        Path filepath = file1.toPath();

        lines = Files.readAllLines(filepath);
        findEulerCycle();
        formGraph();

    }

}
