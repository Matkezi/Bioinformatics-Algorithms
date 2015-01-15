package topic6.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matko
 * @version 1.0
 */
public class TwoBreakDistance extends LoadAndExecute {

    List<Integer> p = new ArrayList<>();

    @Override
    public void execute(String fileName) {
        List<String> lines = loadFromFiles(fileName);

        //fill list p
        String inputLine = lines.get(0);
        inputLine = inputLine.replaceAll("\\(","");
        inputLine = inputLine.replaceAll("\\)","");
        String[] inputLineArray = inputLine.split(" ");
        for (String lineComp : inputLineArray) p.add(Integer.parseInt(lineComp));
    }
}
