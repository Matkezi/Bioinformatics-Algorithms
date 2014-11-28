package topic1.java;

import java.util.regex.*;

/**
 * @author Matko
 * @version 1.0
 */
public class PatternCount {

    public static int countPattern(String kMer, String text){

        int count = 0;
        Matcher m = Pattern.compile(kMer, Pattern.LITERAL).matcher(text);
        boolean bFound = m.find();
        while (bFound) {
            count++;
            bFound = m.find(m.start() + 1);
        }

        return count;
    }
}
