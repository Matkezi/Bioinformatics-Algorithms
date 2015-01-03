package topic5.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public abstract class LoadAndExecute {

    public abstract void execute() throws IOException;

    protected File loadFromFiles(String filename){
        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic5\\src\\topic5\\resources");
        return(new File(dir, filename));
    }
}
