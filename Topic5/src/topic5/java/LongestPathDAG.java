package topic5.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * @author Matko
 * @version 1.0
 */
public class LongestPathDAG {

    int source, sink;
    Map<Integer,Node> graph = new HashMap<>();
    List<Node> topologicalOrderedList = new ArrayList<>();

    private void topologicalOrdering(){
        List<Node> candidates = new ArrayList<>();

        for (Integer key : graph.keySet()){
            Node temp = graph.get(key);
            if (temp.incoming.isEmpty()) candidates.add(temp);
        }

        while (!candidates.isEmpty()){
            Node arbitrary = candidates.get(0);
            topologicalOrderedList.add(arbitrary);
            candidates.remove(arbitrary);
            for (int i = 0;i<arbitrary.dest.size();){
                int b = arbitrary.dest.get(i);
                Node temp = graph.get(b);

                for (int j = 0;j<temp.incoming.size();j++){
                    if (temp.incoming.get(j) == arbitrary.src){
                        temp.incoming.remove(j);
                        break;
                    }
                }

                arbitrary.dest.remove(i);

                if (temp.incoming.isEmpty()){
                    candidates.add(temp);
                }
            }
        }
//        still needs implementation
//        if Graph has edges that have not been removed
//        return "the input graph is not a DAG"

    }

    private void findIncoming(){
        for (Integer key : graph.keySet()){
            Node temp = graph.get(key);
            for (Integer dest : temp.dest){
                Node temp1 = graph.get(dest);
                temp1.updateIncoming(temp.src);
            }
        }
    }

    private void setSourceWeight(){
        Node temp = graph.get(source);
        temp.ownWeight = 0;
    }

    private void setWeights(){
        //setSourceWeight();
        refreshTopologicalList();
        Node temp = topologicalOrderedList.get(0);
        temp.ownWeight = 0;
        for (int i = 1;i<topologicalOrderedList.size();i++){
            Node currentNode = topologicalOrderedList.get(i);
            List<Integer> values = new ArrayList<>();
            for (int inc : currentNode.incoming){
                Node incNode = graph.get(inc);
                values.add(incNode.ownWeight+incNode.destWeightMap.get(currentNode.src));
            }

            if (currentNode.incoming.isEmpty()) values.add(Integer.MIN_VALUE);
            Collections.sort(values);
            currentNode.ownWeight = values.get(values.size()-1);
        }

    }

    private void refreshTopologicalList(){
        for (int i = 0;i<topologicalOrderedList.size();i++){
            Node temp = graph.get(topologicalOrderedList.get(i).src);
            topologicalOrderedList.remove(i);
            topologicalOrderedList.add(i,temp);
        }
    }

    private void loadFromFiles() throws IOException {

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic5\\src\\topic5\\resources");
        File file = new File(dir, "LongestPathDAG.txt");

        List<String> lines = Files.readAllLines(file.toPath());

        source = Integer.parseInt(lines.get(0));
        sink = Integer.parseInt(lines.get(1));

        for (int i = 2;i<lines.size();i++){
            String[] line = lines.get(i).split("->");
            int src = Integer.parseInt(line[0]);
            String[] tmp = line[1].split(":");
            int dest = Integer.parseInt(tmp[0]);
            int weight = Integer.parseInt(tmp[1]);

            if (!graph.containsKey(src)) graph.put(src, new Node(src,dest,weight));
            else {
                Node temp = graph.get(src);
                temp.updateDestinations(dest,weight);
            }

            if (!graph.containsKey(dest)) graph.put(dest, new Node(dest));

        }
    }

    private void outputLongestPathDAG(){
        System.out.println(graph.get(sink).ownWeight);
    }



    public void executeLongestPathDAG() throws IOException{
        loadFromFiles();
        findIncoming();
        topologicalOrdering();

        graph.clear();
        loadFromFiles();
        findIncoming();

        makeDestWeightMaps();
        setWeights();
        outputLongestPathDAG();
    }

    private void makeDestWeightMaps(){
        for (Integer key : graph.keySet()){
            Node temp = graph.get(key);
            temp.makeDestWeightMap();
        }
    }

    private class Node {
        int src;
        int ownWeight = Integer.MIN_VALUE;
        int backtrack;
        List<Integer> dest = new ArrayList<>();
        List<Integer> weight = new ArrayList<>();
        List<Integer> incoming = new ArrayList<>();
        Map<Integer,Integer> destWeightMap = new HashMap<>();

        private Node(int src){
            this.src = src;
        }

        private Node(int src, int dest, int weight) {
            this.src = src;
            this.dest.add(dest);
            this.weight.add(weight);
        }

        public void updateDestinations(int dest, int weight){
            this.dest.add(dest);
            this.weight.add(weight);
        }

        public void updateIncoming(int inc){
            this.incoming.add(inc);
        }

        public void makeDestWeightMap(){
            for (int i = 0;i<dest.size();i++){
                destWeightMap.put(dest.get(i), weight.get(i));
            }
        }
    }
}
