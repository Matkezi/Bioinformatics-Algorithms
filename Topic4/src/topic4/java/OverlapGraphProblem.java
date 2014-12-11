package topic4.java;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

//    public void findOverlapGraph(){
//        Collections.sort(dnas);
//
//        for (int i=0;i<dnas.size();i++){
//            List<String> element = new ArrayList<>();
//            String suffix = dnas.get(i).substring(1,k);
//            for (int j=0;j<dnas.size();j++){
//                String prefx = dnas.get(j).substring(0,k-1);
//                if (prefx.equals(suffix)){
//                    element.add(dnas.get(i));
//                    element.add(dnas.get(j));
//                    overlapGraph.add(element);
//                    break;
//                }
//            }
//
//        }
//    }

    public void findOverlapGraph(){
        Collections.sort(dnas);

        for (int i=0;i<dnas.size();i++){
            List<String> element = new ArrayList<>();
            String suffix = dnas.get(i).substring(1,k);
            for (int j=0;j<dnas.size();j++){
                String prefix = dnas.get(j).substring(0,k-1);
                if (prefix.equals(suffix)){
                    element.add(dnas.get(i));
                    element.add(dnas.get(j));
                    overlapGraph.add(element);
                    break;
                }
            }

        }
    }

    public void printOverLapGraph() throws IOException{
        PrintWriter writer = new PrintWriter("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\out\\overlapGraphProblemOut.txt", "UTF-8");
        for (int i = 0;i<overlapGraph.size();i++){
            writer.println(overlapGraph.get(i).get(0)+" -> "+overlapGraph.get(i).get(1));
        }

        writer.close();
    }

    public static void execute () throws IOException {

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\resources");
        File file1 = new File(dir, "overlapGraphProblem.txt");
        Path filepath = file1.toPath();

        List<String> lines = Files.readAllLines(filepath);
        OverlapGraphProblem ogp = new OverlapGraphProblem(lines);
        ogp.findOverlapGraph();
        ogp.printOverLapGraph();


    }
}
