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

        List<String> pointsTo = new ArrayList<>();
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
            leftEdge.pointsTo.addAll(rightEdges);
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

    private String newStart(){
        for (String edge : cycle){
            if (graph.get(edge).unExplored.size() > 0) return edge;
        }
        return null;
    }

    private List<String> resolveCycle(){
        String newStart = newStart();
        List<String> cycle1 = new ArrayList<>();
        int newStartIndex = -1;
        for (int i = 0;i<cycle.size();i++){
            if (cycle.get(i).equals(newStart)){
                newStartIndex = i;
                cycle1.add(newStart);
            } else if(newStartIndex != -1){
                cycle1.add(cycle.get(i));
            }
        }

        for (int j = 0;j<newStartIndex;j++){
            cycle1.add(cycle.get(j));
        }

        return cycle1;
    }

    private void findEulerCycle(){

        List<String> keysAsArray = new ArrayList<>(graph.keySet());
        Random r = new Random();

        String currentEdge = keysAsArray.get(r.nextInt(keysAsArray.size()));

        //String currentEdge = "4";
        String start = currentEdge;

        do {
            cycle.add(currentEdge);

            String next = graph.get(currentEdge).unExplored.get(0);
            graph.get(currentEdge).unExplored.remove(0);

            currentEdge = next;
        } while (!currentEdge.equals(start));

        while(unExploredEdges()){
            List<String> cycle1 = resolveCycle();

            currentEdge = cycle1.get(0);
            start = currentEdge;

            do {
                cycle1.add(currentEdge);
                String next = "";
                boolean allExplored = true;
                for (int i = 0;i<graph.get(currentEdge).pointsTo.size();i++){
                    if (graph.get(currentEdge).unExplored.contains(graph.get(currentEdge).pointsTo.get(i))){
                        next = graph.get(currentEdge).unExplored.get(0);
                        graph.get(currentEdge).unExplored.remove(0);
                        allExplored = false;
                        break;
                    }
                }

                if (allExplored){
                    next = graph.get(currentEdge).pointsTo.get(r.nextInt(graph.get(currentEdge).pointsTo.size()));

                }


                currentEdge = next;
            } while (!currentEdge.equals(start));
            cycle = cycle1;
        }

        cycle.add(currentEdge);

    }

    public void execute() throws IOException {

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\resources");
        File file1 = new File(dir, "EulerCycle.txt");
        Path filepath = file1.toPath();

        lines = Files.readAllLines(filepath);
        formGraph();
        findEulerCycle();
        System.out.print(cycle);

    }

}
