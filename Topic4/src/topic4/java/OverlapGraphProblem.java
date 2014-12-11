package topic4.java;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class OverlapGraphProblem {

    private List<String> dnas = new ArrayList<>();
    int k;
    private List<List<String>> overlapGraph = new ArrayList<>();

    public OverlapGraphProblem(List<String> dnas) {
        this.dnas = dnas;
        k = dnas.get(0).length();
    }

    private String findFirst(){
        String first = "";
        for (int i = 0;i<dnas.size();i++){
            String prefix = dnas.get(i).substring(0,k-1);
            boolean found = false;
            for (int j = 0;j<dnas.size();j++){
                String suffix = dnas.get(j).substring(1,k);
                if (prefix.equals(suffix)){
                    found = true;
                    break;
                }
            }
            if (!found){
                first = dnas.get(i);
                //System.out.print(first);
                break;
            }
        }
        return first;
    }

    public void findOverlapGraph(){
        String left = findFirst();
        List<String> element = new ArrayList<>();
        String prefix, suffix, right;
        while(!dnas.isEmpty()){
            element.add(left);
            suffix = left.substring(1,k);
            for (int i = 0;i<dnas.size();i++){
                prefix = dnas.get(i).substring(0,k-1);
                if (prefix.equals(suffix)){
                    right = dnas.get(i);
                    element.add(right);
                    suffix = right.substring(1,k);
                    overlapGraph.add(element);
                    break;
                }
            }
            for (int i = 0;i<dnas.size();i++){
                prefix = dnas.get(i).substring(0,k-1);
                if (prefix.equals(suffix)){
                    left = dnas.get(i);
                    break;
                }
            }
            element.clear();
        }
    }

    public void printOverLapGraph() throws IOException{
        PrintWriter writer = new PrintWriter("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\out\\overlapGraphProblemOut.txt", "UTF-8");
        for (int i = 0;i<overlapGraph.size();i++){
            writer.println(overlapGraph.get(i).get(0)+" -> "+overlapGraph.get(i).get(1));
        }

        writer.close();
    }

    public static void main (String[] args) throws IOException {

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\resources");
        File file1 = new File(dir, "overlapGraphProblem.txt");
        Path filepath = file1.toPath();

        List<String> lines = Files.readAllLines(filepath);
        OverlapGraphProblem ogp = new OverlapGraphProblem(lines);
        ogp.findOverlapGraph();
        ogp.printOverLapGraph();


    }
}
