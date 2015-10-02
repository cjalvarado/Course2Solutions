package master_do_not_use;


/**
 * @author Mia
 *
 */
public class DictionaryBST implements Dictionary {

  private MyBSTNode root;
  int size;
  
  public DictionaryBST () {
	  root = null;
	  size = 0;
  }

  /** Add this word to the dictionary.
  	* @param word The word to add
  	* @return true if the word was added to the dictionary 
  	* (it wasn't already there). */
  public boolean addWord(String word) {
    if (root == null) {
      root = new MyBSTNode(null, word);
      size = 1; 
      return true;
    }
    else {
      boolean newWord = root.addWord(word);
	  if (newWord) {
	    size ++;
	  }
	  return newWord;
	}
  }

  /** Is this a word according to this dictionary? */
  public boolean isWord(String s) {
	  return false;
  }
	
}
