/**
 * 
 */
package spelling;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;


/**
 * @author leporter
 *
 */
public class DictionaryHashSet implements Dictionary {

	/* (non-Javadoc)
	 * @see master.Dictionary#addWord(java.lang.String)
	 */
	
	HashSet<String> words;
	
	public DictionaryHashSet()
	{
	    words = new HashSet<String>();
	}
	
	public DictionaryHashSet(String filename) {
		 buildDict(filename);
	}
	
	@Override
	public boolean addWord(String word) {
		
		return words.add(word.toLowerCase());
	}

	@Override
	public int size()
	{
	    return words.size();
	}
	
	/* (non-Javadoc)
	 * @see master.Dictionary#isWord(java.lang.String)
	 */
	@Override
	public boolean isWord(String s) {
	    return words.contains(s.toLowerCase());
	}
	
	private boolean buildDict(String filename) {
		  words = new HashSet<String>();
		  Scanner sc;
		  try {
		   sc = new Scanner(new File(filename));
		   while(sc.hasNext()) {
		      words.add(sc.next());
		   }
		   sc.close();
		  }
		  catch (Exception e) {
			  System.out.println(e);
			  System.exit(0);
		  }
		  System.out.println("The loaded dictionary contains "+words.size()+" words.");
		  return true;
	  }

}
