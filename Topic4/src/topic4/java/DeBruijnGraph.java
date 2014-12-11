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
public class DeBruijnGraph {

    public static void execute() throws IOException{

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic4\\src\\topic4\\resources");
        File file1 = new File(dir, "DeBruijn.txt");
        Path filepath = file1.toPath();

        List<String> lines = Files.readAllLines(filepath);

        StringComposition sc = new StringComposition(Integer.parseInt(lines.get(0))-1,lines.get(1));
        sc.makeComposition();

    }

}
