package topic6.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public abstract class LoadAndExecute {

    public abstract void execute(String fileName);

    protected List<String> loadFromFiles(String filename){
        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic6\\src\\topic6\\resources");
        File file = new File(dir, filename);
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e){
            e.printStackTrace();
        }
        return lines;
    }
}
