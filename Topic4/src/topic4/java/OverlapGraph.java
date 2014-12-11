package topic4.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class OverlapGraph {

    public static void execute () throws IOException {

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\resources");
        File file1 = new File(dir, "overlapGraphProblem.txt");
        Path filepath = file1.toPath();

        List<String> lines = Files.readAllLines(filepath);
        GraphProblem ogp = new GraphProblem(lines);
        ogp.findOverlapGraph();
        ogp.printOverLapGraph();
    }
}
