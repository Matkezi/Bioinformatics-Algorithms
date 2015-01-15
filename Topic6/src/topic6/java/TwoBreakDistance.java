package topic6.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class TwoBreakDistance extends LoadAndExecute {

    List<Integer> p = new ArrayList<>();

    private List<Integer> chromosomeToCycle(List<Integer> chromosome){
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

        List<Integer> nodesL = new ArrayList<>(Arrays.asList(nodes));
        nodesL.remove(0);
        return nodesL ;
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

    @Override
    public void execute(String fileName) {
        List<String> lines = loadFromFiles(fileName);

        //fill list p
        String inputLine = lines.get(0);
        inputLine = inputLine.replaceAll("\\(","");
        inputLine = inputLine.replaceAll("\\)","");
        String[] inputLineArray = inputLine.split(" ");
        for (String lineComp : inputLineArray) p.add(Integer.parseInt(lineComp));

        System.out.println(cycleToChromosome(p));
    }
}
