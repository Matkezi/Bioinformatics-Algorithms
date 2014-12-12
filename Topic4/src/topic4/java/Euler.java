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

        List<Edge> unExplored = new ArrayList<>();
        String edgeName;

        private Edge(String edgeName) {
            this.edgeName = edgeName;
        }
    }

    private HashMap<Edge, List<Edge>> graph = new HashMap<>();

    private List<String> lines = new ArrayList<>();
    private List<Edge> cycle = new ArrayList<>();

    private void formGraph(){
        for (String line : lines){
            List<Edge> rightEdges = new ArrayList<>();
            String[] lineSplit = line.split("->");
            Edge leftEdge = new Edge(lineSplit[0].trim());
            if (lineSplit[1].contains(",")) {
                String[] toNodes = lineSplit[1].split(",");
                for (String toNode : toNodes) {
                    rightEdges.add(new Edge (toNode.trim()));
                }
            } else rightEdges.add(new Edge(lineSplit[1].trim()));
            graph.put(leftEdge,rightEdges);
            leftEdge.unExplored = rightEdges;
        }
    }

    private void findEulerCycle(){

        List<Edge> keysAsArray = new ArrayList<>(graph.keySet());
        Random r = new Random();

        Edge currentEdge = keysAsArray.get(r.nextInt(keysAsArray.size()));
        cycle.add(currentEdge);



    }

    public void execute() throws IOException {

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\resources");
        File file1 = new File(dir, "EulerCycle.txt");
        Path filepath = file1.toPath();

        lines = Files.readAllLines(filepath);
        formGraph();
        findEulerCycle();

    }

}
