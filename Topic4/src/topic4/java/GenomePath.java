package topic4.java;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class GenomePath {

    private List<String> dnas = new ArrayList<>();
    private String genome;

    public GenomePath(List<String> dnas) {
        this.dnas = dnas;
    }

    public void simpleGenomeConstruct(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<dnas.size();i++){
            if (i == dnas.size()-1){
                sb.append(dnas.get(i));
            } else {
                sb.append(dnas.get(i).substring(0,1));
            }
        }
        genome = sb.toString();
    }

    public String getGenome() {
        return genome;
    }

    public static void execute () throws IOException {

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\resources");
        File file1 = new File(dir, "simpleGenomePath.txt");
        Path filepath = file1.toPath();

        List<String> lines = Files.readAllLines(filepath);
        GenomePath gp = new GenomePath(lines);
        gp.simpleGenomeConstruct();
        System.out.println(gp.getGenome());

        PrintWriter writer = new PrintWriter("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\out\\simpleGenomePathOut.txt", "UTF-8");
        writer.println(gp.getGenome());
        writer.close();


    }
}
