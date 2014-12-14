package topic4.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class DeBruijnGraph {

    private List<String> lines = new ArrayList<>();

    private void simpleDeBruijn() throws IOException{
        StringComposition sc = new StringComposition(Integer.parseInt(lines.get(0)),lines.get(1));
        sc.makeComposition();

        GraphProblem gp = new GraphProblem(sc.getComposition());

        gp.findDebruijnGraph();
        gp.printDeBruijnGraph();
    }

    private void simpleDeBruijnFromKmers() throws IOException{

        GraphProblem gp = new GraphProblem(lines);
        gp.findDebruijnGraph();
        gp.printDeBruijnGraph();

    }

    private void  pairedDeBruijnFromKmers() throws IOException{
        String[] line1 = lines.get(0).split("\\s+");
        int k = Integer.parseInt(line1[0]);
        int d = Integer.parseInt(line1[1]);
        List<String>  pairs1 = new ArrayList<>();
        List<String>  pairs2 = new ArrayList<>();

        for (int i = 1;i<lines.size();i++){
            String[] line = lines.get(i).split("\\|");
            pairs1.add(line[0]);
            pairs2.add(line[1]);
        }

        GraphProblem gp = new GraphProblem(pairs1,pairs2,k,d);
        gp.findPairedDebruijnGraph();
        gp.printDeBruijnGraph();
    }

    private void MaximalNonBranchingPaths() throws IOException{
        Euler eu = new Euler();
        eu.execute();
        HashMap<String, List<String>> graph = eu.getGraph();
        HashMap<String,List<Integer>> connections = eu.getConnections();

        List<List<String>> paths = new ArrayList<>();
        for (String node : graph.keySet()){
            if (!(connections.get(node).get(0) == 1 && connections.get(node).get(1) == 1) ){
                if (connections.get(node).get(0) > 0){
                    for (String w : graph.get(node)){
                        List<String> nonBranchingPath = new ArrayList<>();
                        nonBranchingPath.add(node);
                        nonBranchingPath.add(w);
                        while (connections.get(node).get(0) == 1 && connections.get(node).get(1) == 1){
                            nonBranchingPath.add(graph.get(w).get(0));
                            w = graph.get(w).get(0);
                        }
                        paths.add(nonBranchingPath);
                    }
                }
            }
        }
        System.out.println("");
    }

    public  void execute() throws IOException{

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\resources");
        File file1 = new File(dir, "simpleDeBruijnFromKmers.txt");

//        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\out");
//        File file1 = new File(dir, "universalStringKmersOut.txt");

        Path filepath = file1.toPath();

        lines = Files.readAllLines(filepath);

        simpleDeBruijnFromKmers();
        MaximalNonBranchingPaths();


    }

}
