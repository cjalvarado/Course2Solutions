package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBST implements Dictionary {

    TreeSet<String> theDict;
    
  public DictionaryBST () {
	  theDict = new TreeSet<String>();
  }

  /** Add this word to the dictionary.
  	* @param word The word to add
  	* @return true if the word was added to the dictionary 
  	* (it wasn't already there). */
  public boolean addWord(String word) {
      return theDict.add(word);
  }
  
  
  /** Return the number of words in the dictionary */
  public int size()
  {
      return theDict.size();
  }

  /** Is this a word according to this dictionary? */
  public boolean isWord(String s) {
	  return theDict.contains(s);
  }
	
}
