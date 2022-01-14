import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordSearcher {

    /**
     *  Fields
     */
    private char[] greenList;
    private ArrayList<Character> yellowList;
    private ArrayList<Character> blackList;
    private ArrayList<String> wordList;
    private boolean solved;

    /**
     * Constructor
     */
    public WordSearcher() {
        greenList = new char[5];
        yellowList = new ArrayList<Character>();
        blackList = new ArrayList<Character>();
        wordList = new ArrayList<String>();
        solved = false;

        try {
            File words = new File("/Users/andre/IdeaProjects/WordleHelper/src/words.txt");
            Scanner sc = new Scanner(words);
            while (sc.hasNextLine()) {
                wordList.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

    }

    /**
     * Handling Inputs
     */
    // Read the initial inputs
    public void readInputs() {
        readGreen();
        readYellow();
        readBlack();
    }

    // Update the colourlists with new inputs
    public void updateInputs() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Continue? Y/N");
        String ans = sc.nextLine();

        if (ans.toLowerCase().equals("y") || ans.toLowerCase().equals("yes")) {
            readInputs();
        } else {
            System.out.println("Exiting...");
            solved = true;
        }
    }

    // Handling green list
    private void readGreen() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your green letters in the following format:");
        System.out.println("<Letter1><Location1> <Letter2><Location2> etc.");
        String greenInput = sc.nextLine();
        int i = 0;
        try {
            for (i = 0; i < greenInput.length(); i += 3) {
                char letter = greenInput.charAt(i);
                int idx = Character.getNumericValue(greenInput.charAt(i + 1)) - 1;
                greenList[idx] = Character.toLowerCase(letter);
            }
        } catch (Exception e) {
            System.out.println("Error at " + i);
        }
    }

    // Handling yellow list
    private void readYellow() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your yellow letters in the following format:");
        System.out.println("<Letter1> <Letter2> etc.");
        String yellowInput = sc.nextLine();
        int i = 0;
        try {
            for (i = 0; i < yellowInput.length(); i += 2) {
                char letter = yellowInput.charAt(i);
                yellowList.add(letter);
            }
        } catch (Exception e) {
            System.out.println("Error at " + i);
        }
    }

    // Handling black list
    private void readBlack() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your black letters in the following format:");
        System.out.println("<Letter1> <Letter2> etc.");
        String blackInput = sc.nextLine();
        int i = 0;
        try {
            for (i = 0; i < blackInput.length(); i += 2) {
                char letter = blackInput.charAt(i);
                blackList.add(letter);
            }
        } catch (Exception e) {
            System.out.println("Error at " + i);
        }
    }

    /**
     * Word List Generator
     */
    public ArrayList<String> generateWordList() {
        // loop through wordList and add those that pass the 3 checks to an output arraylist to be returned
        /*
        for (String word : wordList) {
            if (!checkGreen(word) || !checkYellow(word) || !checkBlack(word)) {
                wordList.remove(word);
            }
        }
        return wordList;

         */
        // iterate through wordList and remove those that fail any of the 3 checks
        Iterator<String> it = wordList.iterator();
        while (it.hasNext()) {
            String word = it.next();
            if (!checkGreen(word) || !checkYellow(word) || !checkBlack(word)) {
                it.remove();
            }
        }
        return wordList;
    }

    /**
     * Word Checkers
     * @param word
     */
    // Green
    private boolean checkGreen(String word){
        // Take in a word, check that the word satisfies every check in greenList
        for (int i = 0; i < greenList.length; i++) {
            if (greenList[i] != '\u0000' && greenList[i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Yellow
    private boolean checkYellow(String word){
        for (char c : yellowList) {
            if (word.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }

    // Black
    private boolean checkBlack(String word){
        for (char c : blackList) {
            if (word.indexOf(c) != -1) {
                return false;
            }
        }
        return true;
    }

    /**
     *  return whether or not the puzzle has been solved
     */
    public boolean getSolved() {
        return solved;
    }


    public void printColourLists() {
        System.out.println(greenList);
        System.out.println(yellowList);
        System.out.println(blackList);
    }

    public void printWordList(){
        System.out.println("SIZE OF WORDLIST: " + wordList.size());
    }
}
