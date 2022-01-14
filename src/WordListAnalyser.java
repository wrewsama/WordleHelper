import java.util.ArrayList;
import java.util.HashMap;

public class WordListAnalyser {
    private ArrayList<String> wordList;
    private HashMap<Character, Integer> counts;

    public WordListAnalyser(ArrayList<String> wordList) {
        this.wordList = wordList;
        this.counts = new HashMap<>();

        calcCounts();
    }

    /**
     * Counts the number of times each char appears and adds it to the hashmap counts
     */
    private void calcCounts() {
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (counts.containsKey(c)) {
                    counts.replace(c, counts.get(c) + 1);
                } else {
                    counts.put(c, 1);
                }
            }
        }
    }

    /**
     * Returns counts
     * @return
     */
    public HashMap<Character, Integer> getCounts() {
        return counts;
    }

    /**
     * Prints counts
     */
    public void printCounts() {
        System.out.println(counts);
    }

}
