package topic4.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author Matko
 * @version 1.0
 */
public class Euler {

    private List<String> lines = new ArrayList<>();
    private HashMap<String,List<String>> graph = new HashMap<>();
    private List<String> visitedEdges = new ArrayList<>();
    private List<String> cycle = new ArrayList<>();

    private void formGraph(){
        for (String line : lines){
            List<String> rightSide = new ArrayList<>();
            String[] lineSplit = line.split("->");
            if (lineSplit[1].contains(",")){
                String[] toNodes = lineSplit[1].split(",");
                for (String toNode : toNodes){
                    rightSide.add(toNode.trim());
                }
            } else rightSide.add(lineSplit[1].trim());
            graph.put(lineSplit[0].trim(),rightSide);
        }
    }

    private void refreshUnExploredEdges(){

    }

    private void findEulerCycle(){
        HashMap<String,List<String>> unExploredEdges = new HashMap<>(graph);

        List<String> keysAsArray = new ArrayList<>(graph.keySet());
        Random r = new Random();

        String currentEdge = keysAsArray.get(r.nextInt(keysAsArray.size()));
        cycle.add(currentEdge);
        String next ="";
        for (String possibleNext : graph.get(currentEdge)){
            if (!cycle.contains(possibleNext)){
                next = possibleNext;
                break;
            }
        }

        while(!cycle.contains(next)){
            currentEdge = next;
            cycle.add(currentEdge);

            String previousEdge = cycle.get(cycle.size()-2);
            for (int i = 0; i<unExploredEdges.get(previousEdge).size();i++){
                if (currentEdge.equals(unExploredEdges.get(previousEdge).get(i))){
                    unExploredEdges.get(previousEdge).remove(i);
                    if (unExploredEdges.get(previousEdge).size() == 0){
                        unExploredEdges.remove(previousEdge);
                        break;
                    }
                }
            }

            for (String possibleNext : graph.get(currentEdge)){
                if (!cycle.contains(possibleNext)){
                    next = possibleNext;
                    break;
                }
            }
        }

        currentEdge = next;
        for (String possibleNext : graph.get(currentEdge)){
            next = possibleNext;
            break;
        }
        currentEdge = next;
        String previousEdge = cycle.get(cycle.size()-1);
        for (int i = 0; i<unExploredEdges.get(previousEdge).size();i++){
            if (currentEdge.equals(unExploredEdges.get(previousEdge).get(i))){
                unExploredEdges.get(previousEdge).remove(i);
                if (unExploredEdges.get(previousEdge).size() == 0){
                    unExploredEdges.remove(previousEdge);
                    break;
                }
            }
        }

        System.out.println(cycle);


//        while(!unExploredEdges.isEmpty()){
//
//        }

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
