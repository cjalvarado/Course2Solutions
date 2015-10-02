/**
 * 
 */
package master_do_not_use;

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
	
	public DictionaryHashSet(String filename) {
		 buildDict(filename);
	}
	
	@Override
	public boolean addWord(String word) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see master.Dictionary#isWord(java.lang.String)
	 */
	@Override
	public boolean isWord(String s) {
		return words.contains(s);
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
