package topic4.java;

import topic1.java.Stopwatch;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * @author Matko
 * @version 1.0
 */
public class Euler {


    private HashMap<String, List<String>> graph = new HashMap<>();

    private List<String> lines = new ArrayList<>();
    private List<String> cycle = new ArrayList<>();
    String start,end;

    private void formGraph(){
        for (String line : lines){
            List<String> rightEdges = new ArrayList<>();
            String[] lineSplit = line.split("->");
            if (lineSplit[1].contains(",")) {
                String[] toNodes = lineSplit[1].split(",");
                for (String toNode : toNodes) {
                    rightEdges.add(toNode.trim());
                }
            } else rightEdges.add(lineSplit[1].trim());
            graph.put(lineSplit[0].trim(),rightEdges);
        }
    }

    private void findEulerCycle(){
        List<String> keysAsArray = new ArrayList<>(graph.keySet());
        Random r = new Random();

        String currentEdge = keysAsArray.get(r.nextInt(keysAsArray.size()));
        cycle.add(currentEdge);

        while (true){
            cycle.add(graph.get(currentEdge).get(0));

            if (graph.get(currentEdge).size() == 1){
                graph.remove(currentEdge);
            } else {
                graph.get(currentEdge).remove(0);
            }

            if (graph.containsKey(cycle.get(cycle.size() - 1))){
                currentEdge = cycle.get(cycle.size()-1);
            } else break;
        }

        while (!graph.isEmpty()){
            for (int i = 0;i<cycle.size();i++){
                if (graph.containsKey(cycle.get(i))){
                    currentEdge = cycle.get(i);
                    List<String> cycle1 = new ArrayList<>();
                    cycle1.add(currentEdge);
                    while (true){
                        cycle1.add(graph.get(currentEdge).get(0));

                        if (graph.get(currentEdge).size() == 1){
                            graph.remove(currentEdge);
                        } else {
                            graph.get(currentEdge).remove(0);
                        }

                        if (graph.containsKey(cycle1.get(cycle1.size() - 1))){
                            currentEdge = cycle1.get(cycle1.size()-1);
                        } else break;
                    }
                    List<String> newList = new ArrayList<String>();
                    newList.addAll(cycle.subList(0,i));
                    newList.addAll(cycle1);
                    newList.addAll(cycle.subList(i+1,cycle.size()));
                    cycle.clear();
                    cycle.addAll(newList);
                    break;
                }
            }
        }

    }

    private void findEulerPath(){

        String currentEdge = start;
        cycle.add(currentEdge);

        while (true){
            cycle.add(graph.get(currentEdge).get(0));

            if (graph.get(currentEdge).size() == 1){
                graph.remove(currentEdge);
            } else {
                graph.get(currentEdge).remove(0);
            }

            if (graph.containsKey(cycle.get(cycle.size() - 1))){
                currentEdge = cycle.get(cycle.size()-1);
            } else break;
        }

        do{
            for (int i = 0;i<cycle.size();i++){
                if (graph.containsKey(cycle.get(i))){
                    currentEdge = cycle.get(i);
                    List<String> cycle1 = new ArrayList<>();
                    cycle1.add(currentEdge);
                    while (true){
                        cycle1.add(graph.get(currentEdge).get(0));

                        if (graph.get(currentEdge).size() == 1){
                            graph.remove(currentEdge);
                        } else {
                            graph.get(currentEdge).remove(0);
                        }

                        if (graph.containsKey(cycle1.get(cycle1.size() - 1))){
                            currentEdge = cycle1.get(cycle1.size()-1);
                        } else break;
                    }
                    List<String> newList = new ArrayList<String>();
                    newList.addAll(cycle.subList(0,i));
                    newList.addAll(cycle1);
                    newList.addAll(cycle.subList(i+1,cycle.size()));
                    cycle.clear();
                    cycle.addAll(newList);
                    break;
                }
            }
        } while (!graph.isEmpty());

    }

    /**
     * Finds start, and end edge in for Euler path, can also be used to balance the graph
     */
    private void findStartEnd(){
        HashMap<String,List<Integer>> connections = new HashMap<>();
        for (String key : graph.keySet()){
            List<Integer> outIn = new ArrayList<>();
            outIn.add(graph.get(key).size());
            outIn.add(0);
            connections.put(key,outIn);
        }

        for (String key : graph.keySet()){
            List<String> rightSide = graph.get(key);
            for (String right : rightSide){
                if (graph.containsKey(right)) {
                    connections.get(right).set(1, connections.get(right).get(1) + 1);
                } else {
                    if (connections.containsKey(right)){
                        connections.get(right).set(1, connections.get(right).get(1) + 1);
                    } else {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(0);
                        tmp.add(1);
                        connections.put(right, tmp);
                    }
                }
            }
        }

        for (String key : connections.keySet()){
            if (connections.get(key).get(0) > connections.get(key).get(1)){
                start = key;
            } else if (connections.get(key).get(0) < connections.get(key).get(1)){
                end = key;
            }
        }
    }

    public void printEuler() throws IOException{
        PrintWriter writer = new PrintWriter("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\out\\EulerPathOut.txt", "UTF-8");
        for (int i = 0;i<cycle.size();i++) {
            writer.println(cycle.get(i));

//            if (i != cycle.size()-1){
//                writer.print(cycle.get(i)+"->");
//            } else {
//                writer.print(cycle.get(i));
//            }
        }
        writer.close();
    }

    public void execute() throws IOException {

//        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\resources");
//        File file1 = new File(dir, "EulerPath.txt");

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\out");
        File file1 = new File(dir, "DeBruijnGraphProblemOut.txt");

        Path filepath = file1.toPath();

        lines = Files.readAllLines(filepath);
        formGraph();
        findStartEnd();
        findEulerPath();
        printEuler();
    }

}
