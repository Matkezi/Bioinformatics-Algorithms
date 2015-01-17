package topic6.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 *
 * CODE CHALLENGE: Solve the 2-Break Distance Problem.
Input: Genomes P and Q.
Output: The 2-break distance d(P, Q).

Sample Input:
(+1 +2 +3 +4 +5 +6)
(+1 -3 -6 -5)(+2 -4)

Sample Output:
3
 */
public class TwoBreakDistance extends GreedySorting {


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

    List<List<Integer>> blackEdges;
    List<List<Integer>> coloredEdges;

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

        coloredEdges = edges;
        return edges;
    }

    private List<List<Integer>> blackEdges(List<List<Integer>> genome){
        List<List<Integer>> edges = new ArrayList<>();


        for (List<Integer> chromosome : genome){
            Integer[] nodes = chromosomeToCycle(chromosome);
            for (int i = 1;i<nodes.length-1;i+=2){
                List<Integer> edge = new ArrayList<>();
                int node1 = nodes[i];
                int node2 = nodes[i+1];
//                if (node1 < node2) {
//                    edge.add(node1);
//                    edge.add(node2);
//                } else {
//                    edge.add(node2);
//                    edge.add(node1);
//                }

                edge.add(node1);
                edge.add(node2);

                edges.add(edge);
            }
        }

        blackEdges = edges;
        return edges;
    }

    /**
     * Finds cycles from connected nodes.
     * @param genomeGraph containing small lists of individual nodes
     * @return bigger lists of nodes
     */
    private List<List<Integer>> findCycle(List<List<Integer>> genomeGraph){
        List<List<Integer>> foundCycles = new ArrayList<>();

        while (!blackEdges.isEmpty()){
            List<Integer> cycle = new ArrayList<>();
            List<Integer> blackNode = blackEdges.get(0);
            blackEdges.remove(0);
            cycle.add(blackNode.get(0));
            cycle.add(blackNode.get(1));

            for (int i = 0;i<coloredEdges.size();i++){
                List<Integer> coloredNode = coloredEdges.get(i);
                if (coloredNode.contains(cycle.get(cycle.size()-1))){
                    for (int k = 0;k<blackEdges.size();k++){
                        if (blackEdges.get(k).contains(cycle.get(cycle.size()-1))){
                            cycle.add(blackEdges.get(k).get(0));
                            cycle.add(blackEdges.get(k).get(1));
                            blackEdges.remove(k);
                            break;
                        }
                    }
                }
            }


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

    private void find2BreakDistance(List<List<List<Integer>>> genomes){
        List<List<Integer>> red = genomes.get(0);
        List<List<Integer>> blue = genomes.get(1);

        blocksPQ = red.size();

        List<List<Integer>> nodes = new ArrayList<>();
        nodes.addAll(red);
        nodes.addAll(blue);

        List<List<Integer>> cycles = new ArrayList<>();

        while (!nodes.isEmpty()){
            List<Integer> cycle = new ArrayList<>();

            List<Integer> mainNode = nodes.get(0);
            nodes.remove(0);

            int node1 = mainNode.get(0);
            int node2 = mainNode.get(1);

            cycle.add(node1);
            cycle.add(node2);

            for (int i = 0;i<cycle.size();i++){
                Integer node = cycle.get(i);
                for (int j = 0;j<nodes.size();j++){
                    if (nodes.get(j).contains(node)) {
                        if (!cycle.contains(nodes.get(j).get(0))) cycle.add(nodes.get(j).get(0));
                        if (!cycle.contains(nodes.get(j).get(1))) cycle.add(nodes.get(j).get(1));
                        nodes.remove(j);
                        break;
                    }
                }
            }
            cycles.add(cycle);
        }

        cyclesPQ = cycles.size();
        System.out.println("blocks "+blocksPQ);
        System.out.println("cycles "+cyclesPQ);
        System.out.println(blocksPQ-cyclesPQ);

    }

    private List<List<Integer>> twoBreakOnGenomeGraph(List<List<Integer>> genomegraph, int i1, int i2, int j1, int j2){
        List<List<Integer>> arrangment = new ArrayList<>();

        for (int i = 0;i<genomegraph.size();i++){
            List<Integer> node = genomegraph.get(i);
            if (node.contains(i1) && node.contains(i2)){
                List<Integer> toAdd = new ArrayList<>();
                toAdd.add(i1);
                toAdd.add(j1);
                arrangment.add(toAdd);
            } else if (node.contains(j1) && node.contains(j2)){
                List<Integer> toAdd = new ArrayList<>();
                toAdd.add(i2);
                toAdd.add(j2);
                arrangment.add(toAdd);

            } else {
                arrangment.add(node);
            }
        }

        return arrangment;
    }

    private List<List<Integer>> twoBreakOnGenome(List<List<Integer>> genome){
        List<List<Integer>> genomeGraph = coloredEdges(genome);
        genomeGraph.addAll(blackEdges(genome));
        genomeGraph = twoBreakOnGenomeGraph(genomeGraph,1,6,3,8);
        return graphToGenome(genomeGraph);
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
            System.out.println(twoBreakOnGenome(genome));
            genomes.add(coloredEdges(genome));
        }



        //find2BreakDistance(genomes);

//        System.out.println(genomes);
//        List<List<Integer>> genome = genomes.get(0);
//        for (List<Integer> node : genome){
//            System.out.print("("+node.get(0)+", "+node.get(1)+"), ");
//        }

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

//        List<List<Integer>> list = twoBreakOnGenomeGraph(genomeGraph,69,71,91,93);
//
//
//        for (List<Integer> comp : list){
//            List<String> listS = addPlusToPositive(comp);
//            System.out.print("(");
//            for (String s : listS){
//                System.out.print(s);
//            }
//            System.out.print("), ");
//        }
//
    }

    protected List<String> addPlusToPositive(List<Integer> p){
        List<String> retList = new ArrayList<>();
        for (int i = 0;i<p.size();i++){
            Integer num = p.get(i);
            if (i == p.size()-1) retList.add(num.toString());
            else retList.add(num.toString()+", ");

        }
        return retList;
    }
}
