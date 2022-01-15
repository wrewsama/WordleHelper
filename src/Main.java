import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        WordSearcher ws = new WordSearcher();
        ws.readInputs();
        WordRanker wrInit = new WordRanker(ws.generateWordList(), analyseWords());
        wrInit.printBestWords();

        while (!ws.getSolved()) {
            ws.updateInputs();
            if (!ws.getSolved()) {
                WordRanker wr = new WordRanker(ws.generateWordList(), analyseWords());
                wr.printBestWords();
            }
        }


    }

    /**
     * Returns a hashmap with the ranks of each alphabet after analysing a words.txt file
     * @return
     */
    private static HashMap<Character, Integer> analyseWords() {
        ArrayList<String> wordList = new ArrayList<>();
        try {
            File words = new File("/Users/andre/IdeaProjects/WordleHelper/src/words.txt");
            Scanner sc = new Scanner(words);
            while (sc.hasNextLine()) {
                wordList.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        WordListAnalyser wla = new WordListAnalyser(wordList);
        return wla.getCounts();

    }
}
