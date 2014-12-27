package topic5.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class LongestPathDAG {

    int source, sink;
    List<node> graph;

    public void loadFromFiles() throws IOException {

        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic5\\src\\topic5\\resources");
        File file = new File(dir, "LongestPathDAG.txt");

        List<String> lines = Files.readAllLines(file.toPath());

        source = Integer.parseInt(lines.get(0));
        sink = Integer.parseInt(lines.get(1));

        for (int i = 2;i<lines.size();i++){
            String[] line = lines.get(i).split("->");
            int src = Integer.parseInt(line[0]);
            String[] tmp = line[1].split(":");
            int dest = Integer.parseInt(tmp[0]);
            int weight = Integer.parseInt(tmp[1]);
            graph.add(new node(src,dest,weight));

        }

    }

    private class node {
        int src,dest,weight;

        private node(int src, int dest, int weigth) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
}
