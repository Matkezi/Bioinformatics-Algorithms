package topic5.java;

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
public class DpChange {

    int money;
    int[] coins;
    int[] minNumCoins;

    public void execute() throws IOException{


        File dir = new File("C:\\Users\\Matko\\IntelliJProjects\\Bioinformatics-Algorithms\\Topic5\\src\\topic5\\resources");
        File file = new File(dir, "DpChange.txt");


        List<String> lines = new ArrayList<>();
        lines = Files.readAllLines(file.toPath());

        money = Integer.parseInt(lines.get(0));

        String[] readCoins = lines.get(1).split(",");
        coins = new int[readCoins.length];
        for (int i = 0;i<coins.length;i++){
            coins[i] = Integer.parseInt(readCoins[i]);
        }

        minNumCoins = new int[money+1];


        System.out.println(lines);
    }
}
