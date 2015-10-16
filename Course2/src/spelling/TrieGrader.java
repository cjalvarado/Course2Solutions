package spelling;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TrieGrader {
    
    private int incorrect = 0;
    private int tests = 0;
    String feedback = ""; 
    
    public static void main(String[] args) {
        TrieGrader g = new TrieGrader();

        PrintWriter out;
        try {
            out = new PrintWriter("grader_output/module4.part2.out");
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
            out.println(g.getFeedback() + "\n\nError during runtime: " + e );
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
        feedback += "TESTING ADDING WORDS (addWord)  \n";
        feedback += "//*  TEST #1 : Adding first word to dictionary...  *// \n";
        feedback += "addWord returned " + ac.addWord("dog") + ".\n";

        feedback += "//*  \nTEST #2 : Adding two more words and testing size...  *// \n";
        ac.addWord("downhill");
        ac.addWord("downhiller");
        
        feedback += "Size is " + ac.size() + ".\n";

        feedback += "//* \nTEST #3 : Adding more words to dictionary trie... (testing size after insertions)  *// \n";

        ac.addWord("doge");
        ac.addWord("dogg");
        ac.addWord("dawg");
        ac.addWord("dage");
        ac.addWord("doggo");
        ac.addWord("doggie");
        ac.addWord("doggos");
        ac.addWord("doggoes");
        ac.addWord("doggies");
        ac.addWord("test");
        ac.addWord("tester");
        ac.addWord("testing");
        ac.addWord("tested");
        ac.addWord("testin");
        ac.addWord("teston");
        ac.addWord("testone");
        ac.addWord("testine");
        ac.addWord("testell");
        ac.addWord("testcase");
        ac.addWord("testbase");
        ac.addWord("testcases");

        feedback += "Dict size is " + ac.size() + ".\n";

        // get current size before trying to add duplicate word
        int expectedSize = ac.size();

        feedback += "\n//* TEST #4 : Adding duplicate word...  *// \n";
        feedback += "Adding duplicate word returned " + ac.addWord("dog") + ".\n";

        feedback += "\n//* TEST #5 : Checking size after try to add duplicate word...  *// \n";
        feedback += "Dict size is " + ac.size() + ".\n";

    }

    private void testWordsInOut(AutoCompleteDictionaryTrie ac) {
        // TEST WORDS IN/OUT OF DICTIONARY

        feedback += "\n\nTESTING FOR WORDS IN/OUT OF DICTIONARY (isWord) \n";
        feedback += "//*  TEST #6 : Checking empty string...  *// \n";
        // test empty string
        feedback += "Empty string in dictionary: " + ac.isWord("") + ".\n";

        feedback += "\n//*  TEST #7 : Checking for word inserted from list...  *// \n";
        feedback += "'doggoes' in dictionary: " + ac.isWord("doggoes") + ".\n";

        // test word only missing last letter
        feedback += "\n//*  TEST #8 : Testing word only missing last letter...  *// \n";
        feedback += "'downhil' in dictionary: " + ac.isWord("downhil") + ".\n";

        //test word with added letter
        feedback += "\n//*  TEST #9 : Testing word with one extra letter...  *// \n";
        feedback += "'downhille' in dictionary: " + ac.isWord("downhille") + ".\n";

        feedback += "\n//*  TEST #10 : Testing for more words in dictionary...  *// \n";
        feedback += "'test' in dictionary: " + ac.isWord("test") + ". 'testcases' in dictionary: " + ac.isWord("testcases") + ". 'testone' in dictionary: " + ac.isWord("testone") + ".\n";

        feedback += "\n//*  TEST #11 : Testing word with capital letters...  *// \n"; 
        feedback += "'TeSt' in dictionary: " + ac.isWord("TeSt") + ".\n";

    }

    private void testPredictions(AutoCompleteDictionaryTrie ac) {

        feedback += "\n\nTESTING AUTO COMPLETE FUNCTIONALITY (predictCompletions)\n";
        List<String> auto = ac.predictCompletions("dog", 3);
        
        feedback += "\n//*  TEST #12 : completions requested...  *// \n";
        feedback += "Autocomplete returned the following: \n";
        for (String s : auto) {
            feedback += s + ", ";
        }

        feedback += "\n//*  TEST #13 : Testing size of list...  *// \n";
        feedback += "predictCompletions returned " + auto.size() + " elements.\n";

        auto = ac.predictCompletions("soup", 6);
        feedback += "\n//*  TEST #14 : 6 completions requested...  *// \n";
        if (auto.size() != 0) {
            feedback += "predictCompletions found " + auto.size() + " words.\n";
        }

        auto = ac.predictCompletions("dogg", 10);
        feedback += "\n//*  TEST #15 : 10 completions requested...  *// ";
        feedback += "predictCompletions found " + auto.size() + " elements.\n";
        
        feedback += "\n//*  TEST #16 : Testing for correctness of last words...  *//\n";
        feedback += "Words returned by predictCompletions: ";
        for (String s : auto) {
            feedback += s + ", ";
        }

        auto = ac.predictCompletions("test", 7);

        feedback += "\n\n//*  TEST #17 : 7 completions requested... (test for size)  *// ";
        feedback += "predictCompletions returned " + auto.size() + " elements.\n";

        feedback += "\n//*  TEST #18 : Testing if list is sorted from shortest to longest...  *// \n";
        feedback += "Check above output.\n";
        

        List<String> partialList = auto.subList(0, 5);
        
        feedback += "\n//*  TEST #19 : Testing if list contains correct shorter words...  *// \n";
        feedback += "Check above output.\n";

        feedback += "\n//*  TEST #20 : Testing for remaining words...  *// \n";
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

