package topic6.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class TwoBreakDistance extends LoadAndExecute {


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

    private List<List<Integer>> coloredEdges(List<List<Integer>> genome){
        List<List<Integer>> edges = new ArrayList<>();

        for (List<Integer> chromosome : genome){
            Integer[] nodes = chromosomeToCycle(chromosome);
            for (int i = 2;i<nodes.length-1;i+=2){
                List<Integer> edge = new ArrayList<>();
                edge.add(nodes[i]);
                edge.add(nodes[i+1]);
                edges.add(edge);
            }

            List<Integer> edge = new ArrayList<>();
            edge.add(nodes[nodes.length-1]);
            edge.add(nodes[1]);
            edges.add(edge);
        }

        return edges;
    }

    /**
     * Finds cycles from connected nodes.
     * @param genomegraph containing small lists of individual nodes
     * @return bigger lists of nodes
     */
    private List<List<Integer>> findCycle(List<List<Integer>> genomegraph){
        List<List<Integer>> foundCycles = new ArrayList<>();


        int i = 0, startCycle = 0;
        while(i<genomegraph.size()){
            List<Integer> node = genomegraph.get(i);
            if (node.get(0) > node.get(1)){
                List<Integer> cycle = new ArrayList<>();
                for (int j = startCycle;j<i+1;j++,startCycle++){
                    cycle.add(genomegraph.get(j).get(0));

                    //if last put it in the beginning
                    if(j == i) cycle.add(0,genomegraph.get(j).get(1));
                    else cycle.add(genomegraph.get(j).get(1));
                }

                foundCycles.add(cycle);
            }
            i++;
        }

        return foundCycles;
    }

    private List<List<Integer>> graphToGenome(List<List<Integer>> genomegraph){
        List<List<Integer>> genome = new ArrayList<>();
        List<List<Integer>> foundCycles = findCycle(genomegraph);

        for (List<Integer> nodes : foundCycles){
            List<Integer> chromosome = cycleToChromosome(nodes);
            genome.add(chromosome);
        }

        return genome;
    }

    private int blocksPQ=0, cyclesPQ=0;

    private void findCycles(List<List<List<Integer>>> genomes){
        List<List<Integer>> red = genomes.get(0);
        List<List<Integer>> blue = genomes.get(1);

        List<List<Integer>> nodes = new ArrayList<>();
        nodes.addAll(red);
        nodes.addAll(blue);

        List<List<Integer>> cycles = new ArrayList<>();

        while (!nodes.isEmpty()){
            List<Integer> cycle = new ArrayList<>();
            List<Integer> toRemove = new ArrayList<>();

            List<Integer> redNode = nodes.get(0);
            nodes.remove(0);
            int red1 = redNode.get(0);
            int red2 = redNode.get(1);

            cycle.add(red1);
            cycle.add(red2);

            for (int i = 0;i<nodes.size();i++){
                for (int j = 0;j<cycle.size();j++){
                    if (nodes.get(i).contains(cycle.get(j))){
                        cycle.add(nodes.get(i).get(0));
                        cycle.add(nodes.get(i).get(1));
                        nodes.remove(i);
                        i=0;
                        break;
                    }
                }
            }
            cycles.add(cycle);

        }

    }

    @Override
    public void execute(String fileName) {
        List<String> linesRaw = loadFromFiles(fileName);

        List<List<List<Integer>>> genomes = new ArrayList<>();

        for (String lineR : linesRaw) {
            String[] line = lineR.split("\\)\\(");
            List<List<Integer>> genome = new ArrayList<>();

            //fill list p
            for (String comp : line) {
                List<Integer> p = new ArrayList<>();
                comp = comp.replaceAll("\\(", "");
                comp = comp.replaceAll("\\)", "");
                String[] inputLineArray = comp.split(" ");
                for (String lineComp : inputLineArray) p.add(Integer.parseInt(lineComp));
                genome.add(p);
            }
            genomes.add(coloredEdges(genome));
        }

        findCycles(genomes);

        System.out.println(genomes);

//        String[] line = linesRaw.get(0).split(", \\(");
//        List<List<Integer>> genomeGraph = new ArrayList<>();
//
//        for (String comp : line){
//            List<Integer> edge = new ArrayList<>();
//            comp = comp.replaceAll("\\(", "");
//            comp = comp.replaceAll("\\)", "");
//            comp = comp.replaceAll(" ","");
//            String[] components = comp.trim().split(",");
//            edge.add(Integer.parseInt(components[0]));
//            edge.add(Integer.parseInt(components[1]));
//            genomeGraph.add(edge);
//        }
//
//        System.out.println(graphToGenome(genomeGraph));

    }
}
