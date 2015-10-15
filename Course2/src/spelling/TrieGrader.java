package spelling;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TrieGrader {
    private static final int LENGTH = 500;

    private int incorrect = 0;
    private int tests = 0;
    String feedback = ""; 
    
    public static void main(String[] args) {
        TrieGrader g = new TrieGrader();

        PrintWriter out;
        try {
            out = new PrintWriter("Course2/grader_output/module4.part2.out");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        try {
            AutoCompleteDictionaryTrie ac = new AutoCompleteDictionaryTrie();
            
            g.testAddWords(ac);

            g.testWordsInOut(ac);

            g.testPredictions(ac);

        } catch (Exception e) {
            out.println(g.getFeedback() + "Error during runtime: " + e );
            out.close();
            return;
        }

        String feedback = g.getFeedback();
        int tests = g.getTests();
        int incorrect = g.getIncorrect();
            
            
        out.println(feedback);
        out.close();
    }


    private void testAddWords(AutoCompleteDictionaryTrie ac) {
        feedback += "TESTING ADDING WORDS (addWord, insert)  ";
        feedback += "//*  TEST #1 : Adding first word to dictionary...  *// ";
        feedback += "addWord returned " + ac.addWord("dog") + ".\n";

        feedback += "//*  TEST #2 : Adding two more words and testing size...  *// ";
        ac.addWord("downhill");
        ac.addWord("downhiller");
        
        feedback += "Size is " + ac.size() + ".\n";

        feedback += "//* TEST #3 : Adding list of words to dictionary trie... (testing size after insert)  *// ";
        feedback += "Populating List of words... ";


        List<String> words = new ArrayList<String>();
        words.add("doge");
        words.add("dogg");
        words.add("dawg");
        words.add("dage");
        words.add("doggo");
        words.add("doggie");
        words.add("doggos");
        words.add("doggoes");
        words.add("doggies");
        words.add("test");
        words.add("tester");
        words.add("testing");
        words.add("tested");
        words.add("testin");
        words.add("teston");
        words.add("testone");
        words.add("testine");
        words.add("testell");
        words.add("testcase");
        words.add("testbase");
        words.add("testcases");

        ac.insert(words);

        feedback += "Dict size is " + ac.size() + ".\n";

        // get current size before trying to add duplicate word
        int expectedSize = ac.size();

        feedback += "//* TEST #4 : Adding duplicate word...  *// ";
        feedback += "Adding duplicate word returned " + ac.addWord("dog") + ".\n";

        feedback += "//* TEST #5 : Checking size after try to add duplicate word...  *// ";
        feedback += "Dict size is " + ac.size() + ".\n";

    }

    private void testWordsInOut(AutoCompleteDictionaryTrie ac) {
        // TEST WORDS IN/OUT OF DICTIONARY

        feedback += "TESTING FOR WORDS IN/OUT OF DICTIONARY (isWord) ";
        feedback += "//*  TEST #6 : Checking empty string...  *// ";
        // test empty string
        feedback += "Empty string in dictionary: " + ac.isWord("") + ".\n";

        feedback += "//*  TEST #7 : Checking for word inserted from list...  *// ";
        feedback += "'doggoes' in dictionary: " + ac.isWord("doggoes") + ".\n";

        // test word only missing last letter
        feedback += "//*  TEST #8 : Testing word only missing last letter...  *// ";
        feedback += "'downhil' in dictionary: " + ac.isWord("downhil") + ".\n";

        //test word with added letter
        feedback += "//*  TEST #9 : Testing word with one extra letter...  *// ";
        feedback += "'downhille' in dictionary: " + ac.isWord("downhille") + ".\n";

        feedback += "//*  TEST #10 : Testing for more words in dictionary...  *// ";
        feedback += "'test' in dictionary: " + ac.isWord("test") + ". 'testcases' in dictionary: " + ac.isWord("testcases") + ". 'testone' in dictionary: " + ac.isWord("testone") + ".\n";

        feedback += "//*  TEST #11 : Testing word with capital letters...  *// "; 
        feedback += "'TeSt' in dictionary: " + ac.isWord("TeSt") + ".\n";

    }

    private void testPredictions(AutoCompleteDictionaryTrie ac) {

        feedback += "//*  TEST #12 : TESTING AUTO COMPLETE FUNCTIONALITY (predictCompletions)  *// \n";
        List<String> auto = ac.predictCompletions("dog", 3);
        
        feedback += "//*  TEST #13 : completions requested...  *// ";
        feedback += "Autocomplete returned the following: ";
        for (String s : auto) {
            feedback += s + ", ";
        }

        feedback += "\n//*  TEST #14 : Testing size of list...  *// ";
        feedback += "predictCompletions returned " + auto.size() + " elements.\n";

        auto = ac.predictCompletions("soup", 6);
        feedback += "//*  TEST #14 : 6 completions requested...  *// ";
        if (auto.size() != 0) {
            feedback += "predictCompletions found " + auto.size() + " words.\n";
        }

        auto = ac.predictCompletions("dogg", 10);
        feedback += "//*  TEST #15 : 10 completions requested...  *// ";
        feedback += "predictCompletions found " + auto.size() + " elements.\n";
        
        feedback += "//*  TEST #16 : Testing for correctness of last words...  *//";
        feedback += "Words returned by predictCompletions: ";
        for (String s : auto) {
            feedback += s + ", ";
        }

        auto = ac.predictCompletions("test", 7);

        feedback += "\n//*  TEST #17 : 7 completions requested... (test for size)  *// ";
        feedback += "predictCompletions returned " + auto.size() + " elements.\n";

        feedback += "//*  TEST #18 : Testing if list is sorted from shortest to longest...  *// ";
        feedback += "Check above output.\n";
        

        List<String> partialList = auto.subList(0, 5);
        
        feedback += "//*  TEST #19 : Testing if list contains correct shorter words...  *// ";
        feedback += "Check above output.\n";

        feedback += "//*  TEST #20 : Testing for remaining words...  *// ";
        partialList = auto.subList(5, auto.size());

        int count = 0;

        count = partialList.contains("testone") ? ++count:count ;
        count = partialList.contains("testine") ? ++count:count;
        count = partialList.contains("testell") ? ++count:count;
        count = partialList.contains("testing") ? ++count:count;

        feedback += "Out of 'testone', 'testine', 'testell', and 'testing', " + count + " words were found.\n";
            
    }

    private int getIncorrect() {
        return this.incorrect;
    }
    private int getTests() {
        return this.tests;
    }
    private String getFeedback() {
        return this.feedback;
    }


}

