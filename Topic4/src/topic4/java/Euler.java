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

        List<String> unExplored = new ArrayList<>();
        String edgeName;

        private Edge(String edgeName) {
            this.edgeName = edgeName;
        }
    }

    private HashMap<String, Edge> graph = new HashMap<>();

    private List<String> lines = new ArrayList<>();
    private List<String> cycle = new ArrayList<>();

    private void formGraph(){
        for (String line : lines){
            List<String> rightEdges = new ArrayList<>();
            String[] lineSplit = line.split("->");
            Edge leftEdge = new Edge(lineSplit[0].trim());
            if (lineSplit[1].contains(",")) {
                String[] toNodes = lineSplit[1].split(",");
                for (String toNode : toNodes) {
                    rightEdges.add(toNode.trim());
                }
            } else rightEdges.add(lineSplit[1].trim());
            graph.put(leftEdge.edgeName,leftEdge);
            leftEdge.unExplored = rightEdges;
        }
    }

    private boolean unExploredEdges(){
        for (String key : graph.keySet()){
            if (graph.get(key).unExplored.size() > 0){
                return true;
            }
        }
        return false;
    }

    private void findEulerCycle(){

        List<String> keysAsArray = new ArrayList<>(graph.keySet());
        Random r = new Random();

        String currentEdge = keysAsArray.get(r.nextInt(keysAsArray.size()));

        do {
            cycle.add(currentEdge);

            String next = graph.get(currentEdge).unExplored.get(0);
            graph.get(currentEdge).unExplored.remove(0);

            currentEdge = next;
        } while (!cycle.contains(currentEdge));

        while(unExploredEdges()){

        }

    }

    public void execute() throws IOException {

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\resources");
        File file1 = new File(dir, "EulerCycle.txt");
        Path filepath = file1.toPath();

        lines = Files.readAllLines(filepath);
        formGraph();
        findEulerCycle();
        System.out.print("");

    }

}
