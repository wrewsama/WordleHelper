import java.util.ArrayList;
import java.util.HashMap;


public class WordRanker {
    /**
     * Fields
     */
    private HashMap<String, Integer> ranks;
    private HashMap<Character, Integer> alphabetRanks;


    /**
     * Constructor
     * @param words
     */
    public WordRanker(ArrayList<String> words, HashMap<Character,Integer> alphabetRanks) {
        // Initialising fields
        ranks = new HashMap<String,Integer>();
        this.alphabetRanks = alphabetRanks;

        // Setting wordRanks
        for (String word : words) {
            ranks.put(word, getRank(word));
        }
    }


    /**
     * Returns at most the 5 highest ranked words
     * @return
     */
    public HashMap<String,Integer> getBestWords() {
        HashMap<String,Integer> bestWords = new HashMap<String,Integer>();
        // Takes at most the 10 best words
        if (ranks.size() > 10) {
            for (int i = 0; i < 10; i++) {
                String maxKey = getMaxKey();
                bestWords.put(maxKey, ranks.get(maxKey));
                ranks.remove(maxKey);
            }
        } else {
            bestWords = ranks;
        }
        return bestWords;

    }


    /**
     * Gets the word with the maximum rank in ranks
     * @return
     */
    private String getMaxKey() {
        String output = null;
        for (String word : ranks.keySet()) {
            if (output == null || ranks.get(word) > ranks.get(output)) {
                output = word;
            }
        }
        return output;
    }

    /**
     * Get the rank of a given word
     * @param word
     * @return output
     */
    private int getRank(String word) {
        int output = 0;
        ArrayList<Character> memo = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (!memo.contains(word.charAt(i))) {
                output += alphabetRanks.get(word.charAt(i));
                memo.add(word.charAt(i));
            }
        }
        return output;
    }




    public void printRanks() {
        for (String word : ranks.keySet()) {
            System.out.print(word + " ");
            System.out.println(ranks.get(word));
        }
    }
    public void printBestWords() {
        HashMap<String,Integer> hm = getBestWords();
        for (String word : hm.keySet()) {
            System.out.print(word + " ");
            System.out.println(hm.get(word));
        }

    }
}
