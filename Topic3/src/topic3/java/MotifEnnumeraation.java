package topic3.java;

import topic1.java.FrequentWords;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Matko
 * @version 1.0
 */
public class MotifEnnumeraation {

    List<String> patterns = new ArrayList<>();
    List<String> dnas = new ArrayList<>();

    int k, d;

    public MotifEnnumeraation(List<String> dnas, int k, int d) {
        this.dnas = dnas;
        this.k = k;
        this.d = d;
        fillPatterns();
    }

    public void fillPatterns(){


    }
}
