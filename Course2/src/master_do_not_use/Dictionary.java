/**
 * Dictionary interface, representing and old school word-lookup dictionary
 */
package master_do_not_use;

/**
 * @author Christine
 *
 */
public interface Dictionary {
	/** Add this word to the dictionary.
	 * @param word The word to add
	 * @return true if the word was added to the dictionary 
	 * (it wasn't already there). */
	public boolean addWord(String word);

	/** Is this a word according to this dictionary? */
	public boolean isWord(String s);
}
