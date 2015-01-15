package topic6.java;

import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class TwoBreakDistance extends LoadAndExecute {

    private List<List<Integer>> genome = new ArrayList<>();

    private Integer[] chromosomeToCycle(List<Integer> chromosome){
        Integer[] nodes = new Integer[1+chromosome.size()*2];

        for (int j = 0;j<chromosome.size();j++){
            int i = chromosome.get(j);
            if (i > 0){
                nodes[2*(j+1)-1] = 2*i-1;
                nodes[2*(j+1)] = 2*i;
            } else {
                nodes[2*(j+1)-1] = -2*i;
                nodes[2*(j+1)] = -2*i-1;
            }
        }

        return nodes;

//        List<Integer> nodesL = new ArrayList<>(Arrays.asList(nodes));
//        nodesL.remove(0);
//        return nodesL ;
    }

    private List<Integer> cycleToChromosome(List<Integer> nodes){
        List<Integer> chromosome = new ArrayList<>();
        Integer[] nodesA = new Integer[nodes.size()+1];

        for (int i = 1;i<nodesA.length;i++) nodesA[i] = nodes.get(i-1);


         for (int j = 1;j<nodesA.length/2 + 1;j++){
             if (nodesA[2*j-1] < nodesA[2*j]) chromosome.add(nodesA[2*j]/2);
             else chromosome.add(-1*nodesA[2*j-1]/2);
         }
         return chromosome;
    }

    private List<List<Integer>> coloredEdges(){
        List<List<Integer>> edges = new ArrayList<>();

        for (List<Integer> chromosome : genome){
            Integer[] nodes = chromosomeToCycle(chromosome);
            for (int j = 0;j<chromosome.size();j++){
                List<Integer> edge = new ArrayList<>();
                edge.add(nodes[2*(j+1)-1]);
                edge.add(nodes[2*(j+1)]);
                edges.add(edge);
            }
        }

        return edges;
    }

    @Override
    public void execute(String fileName) {
        List<String> linesRaw = loadFromFiles(fileName);

        String[] lines = linesRaw.get(0).split("\\)\\(");

        //fill list p
        for (String inputLine : lines) {
            List<Integer> p = new ArrayList<>();
            inputLine = inputLine.replaceAll("\\(", "");
            inputLine = inputLine.replaceAll("\\)", "");
            String[] inputLineArray = inputLine.split(" ");
            for (String lineComp : inputLineArray) p.add(Integer.parseInt(lineComp));
            genome.add(p);
        }

        System.out.println(coloredEdges());

    }
}
