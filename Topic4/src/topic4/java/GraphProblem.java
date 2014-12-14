package topic4.java;

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
public class GraphProblem {

    private List<String> dnas = new ArrayList<>();
    private List<String> pairs1 = new ArrayList<>();
    private List<String> pairs2 = new ArrayList<>();
    int k,d;
    private List<List<String>> overlapGraph = new ArrayList<>();
    private List<List<String>> deBruijnGraph = new ArrayList<>();

    public GraphProblem(List<String> dnas) {
        this.dnas = dnas;
        k = dnas.get(0).length();
    }

    public GraphProblem(List<String> pairs1, List<String> pairs2, int k, int d) {
        this.pairs1 = pairs1;
        this.pairs2 = pairs2;
        this.k = k;
        this.d = d;
    }

    public void findOverlapGraph(){
        Collections.sort(dnas);

        for (int i=0;i<dnas.size();i++){
            List<String> element = new ArrayList<>();
            String suffix = dnas.get(i).substring(1,k);
            for (int j=0;j<dnas.size();j++){
                String prefx = dnas.get(j).substring(0,k-1);
                if (prefx.equals(suffix)){
                    element.add(dnas.get(i));
                    element.add(dnas.get(j));
                    overlapGraph.add(element);
                    break;
                }
            }

        }
    }

    /**
     * Checks if a kMer is in the "left" position in a graph and returns index.
     * @param kMer kMer to be checked if in the graph.
     * @return -1 if kMer not in a graph, else a position of kMer in a graph.
     */
    private int graphContains(String kMer){

        for (int i = 0;i<deBruijnGraph.size();i++){
            if (deBruijnGraph.get(i).get(0).equals(kMer)){
                return i;
            }
        }
        return -1;
    }

    public void findDebruijnGraph(){

        Collections.sort(dnas);

        for (int i=0;i<dnas.size();i++){
            List<String> element = new ArrayList<>();
            String left = dnas.get(i).substring(0,k-1);
            String right = dnas.get(i).substring(1,k);

            int index = graphContains(left);
            if (index >= 0){
                element = deBruijnGraph.get(index);
                deBruijnGraph.remove(index);
                element.add(right);
                deBruijnGraph.add(index,element);
            } else {
                element.add(left);
                element.add(right);
                deBruijnGraph.add(element);
            }
        }
    }

    public void findPairedDebruijnGraph(){

        for (int i = 0;i<pairs1.size();i++){
            List<String> element = new ArrayList<>();
            String left = pairs1.get(i).substring(0,k-1)+"|"+pairs2.get(i).substring(0,k-1);
            String right = pairs1.get(i).substring(1,k)+"|"+pairs2.get(i).substring(1,k);

            int index = graphContains(left);
            if (index >= 0){
                element = deBruijnGraph.get(index);
                deBruijnGraph.remove(index);
                element.add(right);
                deBruijnGraph.add(index,element);
            } else {
                element.add(left);
                element.add(right);
                deBruijnGraph.add(element);
            }

        }

    }


    public void printOverLapGraph() throws IOException{
        PrintWriter writer = new PrintWriter("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\out\\overlapGraphProblemOut.txt", "UTF-8");
        for (int i = 0;i<overlapGraph.size();i++) {
            writer.println(overlapGraph.get(i).get(0) + " -> " + overlapGraph.get(i).get(1));
        }
        writer.close();
    }

    public void printDeBruijnGraph() throws IOException{
        PrintWriter writer = new PrintWriter("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\out\\pairedDeBruijnGraphProblemOut.txt", "UTF-8");
        for (int i = 0;i<deBruijnGraph.size();i++){
            if (deBruijnGraph.get(i).size() == 2) writer.println(deBruijnGraph.get(i).get(0)+" -> "+deBruijnGraph.get(i).get(1));
            else {
                writer.print(deBruijnGraph.get(i).get(0)+" -> "+deBruijnGraph.get(i).get(1));
                for (int j = 2;j<deBruijnGraph.get(i).size();j ++){
                    writer.print(","+deBruijnGraph.get(i).get(j));
                }
                writer.println();
            }
        }

        writer.close();
    }


}
