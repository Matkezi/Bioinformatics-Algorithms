package topic4.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class StringComposition {

    private int k;
    private String dna;
    private List<String> composition = new ArrayList<>();

    public StringComposition(int k, String dna) {
        this.k = k;
        this.dna = dna;
    }

    public void makeComposition(){

        for (int i=0;i<=dna.length()-k;i++){
            composition.add(dna.substring(i,i+k));
        }

        Collections.sort(composition);
    }

    public List<String> getComposition() {
        return composition;
    }

    public static void execute () throws IOException{


        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\resources");
        File file1 = new File(dir, "StringComposition.txt");
        Path filepath = file1.toPath();

        List<String> lines = Files.readAllLines(filepath);
        StringComposition sc = new StringComposition(Integer.parseInt(lines.get(0)),lines.get(1));
        sc.makeComposition();

        for(String component : sc.getComposition()){
            System.out.println(component);
        }


    }

}
