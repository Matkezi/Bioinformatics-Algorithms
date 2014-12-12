package topic4.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

    public  void execute() throws IOException{

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\resources");
        File file1 = new File(dir, "simpleDeBruijnFromKmers.txt");
        Path filepath = file1.toPath();

        lines = Files.readAllLines(filepath);

        simpleDeBruijnFromKmers();


    }

}
