package spelling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DictionaryLoader {

    public static void loadDictionary(Dictionary d, String filename)
    {
        // Dictionary files have 1 word per line
        BufferedReader reader = null;
        try {
            String nextWord;
            reader = new BufferedReader(new FileReader(filename));
            while ((nextWord = reader.readLine()) != null) {
                d.addWord(nextWord);
            }
        } catch (IOException e) {
            System.err.println("Problem loading dictionary file: " + filename);
            e.printStackTrace();
        }
        
    }
    
}
